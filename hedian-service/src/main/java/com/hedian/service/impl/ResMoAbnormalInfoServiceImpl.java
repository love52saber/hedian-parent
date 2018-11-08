package com.hedian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.base.Constant;
import com.hedian.base.QuatzConstants;
import com.hedian.entity.*;
import com.hedian.mapper.ResAbnormallevelMapper;
import com.hedian.mapper.ResBaseMapper;
import com.hedian.mapper.ResMoAbnormalInfoMapper;
import com.hedian.mapper.ResTerminalMapper;
import com.hedian.model.AbnormalLevelModel;
import com.hedian.model.AlarmInfoModel;
import com.hedian.model.ResMoAbnormalInfoModel;
import com.hedian.service.IResBaseService;
import com.hedian.service.IResMoAbnormalInfoHService;
import com.hedian.service.IResMoAbnormalInfoService;
import com.hedian.util.ComUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源监控异常信息 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResMoAbnormalInfoServiceImpl extends ServiceImpl<ResMoAbnormalInfoMapper, ResMoAbnormalInfo> implements IResMoAbnormalInfoService {

    @Resource
    private ResMoAbnormalInfoMapper resMoAbnormalInfoMapper;
    @Resource
    private IResMoAbnormalInfoHService resMoAbnormalInfoHService;
    @Autowired
    private IResBaseService iResBaseService;
    @Autowired
    private ResBaseMapper resBaseMapper;
    @Autowired
    private ResAbnormallevelMapper resAbnormallevelMapper;
    @Autowired
    private ResTerminalMapper resTerminalMapper;


    @Override
    public ResMoAbnormalInfo selectByResIdAndkpiId(Map<String, Integer> map) {
        return resMoAbnormalInfoMapper.selectByResIdAndkpiId(map);
    }

    @Override
    public List<AlarmInfoModel> selectAlarmByResId(String resId) {
        return resMoAbnormalInfoMapper.selectAlarmByResId(resId);
    }

    @Override
    public List<MoAbnormalDef> getTopAbnormal(Map<String, Object> map) {
        return resMoAbnormalInfoMapper.getTopAbnormal(map);
    }

    @Override
    public Page<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, String beginTime, String endTime,
                                                              String conStatus, String abnormalLevel, String abnormalType,
                                                              String abnormalName, String mokpiName, String resName, Integer resId,
                                                              String resAlias, boolean isAutoOrder, String resAbnormalId) {
        return page.setRecords(resMoAbnormalInfoMapper.selectPageByCondition(page, beginTime, endTime, conStatus, abnormalLevel, abnormalType,
                abnormalName, mokpiName, resName, resId, resAlias, isAutoOrder, resAbnormalId));
    }

    @Override
    public List<AbnormalLevelModel> selectAbnormalLevelCount() {
        return resMoAbnormalInfoMapper.selectAbnormalLevelCount();
    }

    @Override
    public boolean deleteResAbnoraml(Long resAbnormalId) throws Exception {
        ResMoAbnormalInfo resMoAbnormalInfo = this.selectById(resAbnormalId);
        boolean result = cleanOrDelete(resAbnormalId, resMoAbnormalInfo);
        return result;
    }

    @Transactional
    @Override
    public boolean cleanOrDelete(Long resAbnormalId, ResMoAbnormalInfo resMoAbnormalInfo) throws Exception {
        ResMoAbnormalInfoH resMoAbnormalInfoH = new ResMoAbnormalInfoH();
        BeanUtils.copyProperties(resMoAbnormalInfo, resMoAbnormalInfoH);
        boolean result = resMoAbnormalInfoHService.insert(resMoAbnormalInfoH);
        if (!result) {
            throw new BusinessException("删除告警信息失败");
        }
        result = this.deleteById(resAbnormalId);

        //判断是否是终端离线告警
        boolean flag = false;
        ResBase currentResBase = resBaseMapper.selectById(resMoAbnormalInfo.getResId());
        //是，则关联未知设备清除状态,设备有故障则不变
        if (currentResBase.getResMtypeId().equals(QuatzConstants.ZD_MAIN_TYPE) & resMoAbnormalInfo.getMoKpiId().equals(QuatzConstants.OFFLINE_KEY)) {
            //找到所有终端关联的状态未知设备
            List<ResBase> devList = resBaseMapper.selectDevListByTerminalId(currentResBase.getResId(),
                    QuatzConstants.UNKNOWN);
            if (!ComUtil.isEmpty(devList)) {
                devList.stream().forEach(resBase -> {
                    resBase.setResStatus(1);
                });
                flag = iResBaseService.updateBatchById(devList);
                if (!flag) {
                    throw new BusinessException("终端下设备更新失败");
                }
            }
        } else {
            //否 根据设备故障信息更新res_base表
            //获取当前设备故障信息，按优先级升序和id降序排序，
            List<ResMoAbnormalInfoModel> resMoAbnormalInfoModelList = resMoAbnormalInfoMapper.selectByResId(resMoAbnormalInfo.getResId());
            if (resMoAbnormalInfoModelList.size() == 0) {
                //没有故障，更改resBase状态为正常
                currentResBase.setResAbnormalId(null);
                currentResBase.setResAbnormalcode(null);
                currentResBase.setResAbnormallevelId(null);
                currentResBase.setResAbnormalName(null);
                currentResBase.setResAbnormaldesc(null);
                currentResBase.setResAbnomaltime(null);
                currentResBase.setResRecoverytime(null);
                currentResBase.setResStatus(QuatzConstants.NORMAL);
                flag = iResBaseService.updateAllColumnById(currentResBase);
            } else if (!ComUtil.isEmpty(resMoAbnormalInfoModelList)) {
                //删除后有若干条故障，找到级别最高的一条
                ResMoAbnormalInfoModel resMoAbnormalInfoModel = resMoAbnormalInfoModelList.get(0);
                Integer HighestResAbnormalLevelPriority = resMoAbnormalInfoModel.getResAbnormallevelPriority();
                //获取删除那条报警的优先级
                Integer deledResAbnormallevelPriority = resAbnormallevelMapper.selectById(resMoAbnormalInfo.getResAbnormallevelId()).getResAbnormallevelPriority();
                //删除的那条警告所属设备的base表中警告id
                Integer deledResAbnormalId = currentResBase.getResAbnormalId();
                if (HighestResAbnormalLevelPriority > deledResAbnormallevelPriority || (HighestResAbnormalLevelPriority.equals(deledResAbnormallevelPriority) & deledResAbnormalId.equals(resMoAbnormalInfo.getResAbnormalId().intValue()))) {
                    //最高条优先级大于删除的那条或两者相等且base表中原本保存的就是这条故障则要更新
                    ResBase resBase = new ResBase();
                    BeanUtils.copyProperties(resMoAbnormalInfoModel, resBase);
                    resBase.setResAbnormalId(resMoAbnormalInfoModel.getResAbnormalId().intValue());
                    resBase.setResAbnormalcode(resMoAbnormalInfoModel.getResAbnormalCode());
                    flag = iResBaseService.updateById(resBase);
                }
                //如果最高条小于那条则base表不变
            }
        }
        return result&flag;
    }

    @Override
    @Transactional
    public boolean cleanResAbnormal(JSONObject requestJson) throws Exception {
        Long resAbnormalId = requestJson.getLong("resAbnormalId");
        String cleanInfo = requestJson.getString("cleanInfo");
        ResMoAbnormalInfo resMoAbnormalInfo = this.selectById(resAbnormalId);
        resMoAbnormalInfo.setCleanInfo(cleanInfo);
        resMoAbnormalInfo.setCleanType(2);
        boolean result = this.updateById(resMoAbnormalInfo);
        if (!result) {
            throw new BusinessException("清除告警信息失败");
        }
        result = cleanOrDelete(resAbnormalId, resMoAbnormalInfo);

        return result;
    }
}
