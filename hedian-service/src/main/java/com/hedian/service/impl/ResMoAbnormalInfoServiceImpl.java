package com.hedian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
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
import com.hedian.service.IResTerminalService;
import com.hedian.util.ComUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    @Autowired
    private IResTerminalService iResTerminalService;
    @Autowired
    private IResMoAbnormalInfoService iResMoAbnormalInfoService;

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
        if (ComUtil.isEmpty(resMoAbnormalInfo)) {
            throw new BusinessException("找不到此条异常信息");
        }
        boolean result = cleanOrDelete(resAbnormalId, resMoAbnormalInfo);
        return result;
    }

    @Override
    public boolean cleanOrDelete(Long resAbnormalId, ResMoAbnormalInfo resMoAbnormalInfo) throws Exception {
        ResMoAbnormalInfoH resMoAbnormalInfoH = new ResMoAbnormalInfoH();
        BeanUtils.copyProperties(resMoAbnormalInfo, resMoAbnormalInfoH);
        boolean result = resMoAbnormalInfoHService.insert(resMoAbnormalInfoH);
        if (!result) {
            throw new BusinessException("删除告警信息失败");
        }
        result = this.deleteById(resAbnormalId);

        //组装数据
        Map<String, Object> dataMap = new HashMap<>();
        //当前设备base信息
        ResBase resBase = resBaseMapper.selectById(resMoAbnormalInfo.getResId());
        dataMap.put("currentResBase", resBase);
        //设备所属设备树base信息
        dataMap.put("currentResTree", this.getCurrentResTree(resBase));
        //当前故障信息
        dataMap.put("currentResAbnormalInfo", resMoAbnormalInfo);
        //设备所属设备树故障信息
        dataMap.put("currentResTreeAbnormalInfo", this.getCurrentResTreeAbnormalInfo(resMoAbnormalInfo));

        //判断是否为终端
        boolean isTerminal = this.isTerminal(resMoAbnormalInfo.getResId());
        //1.是终端
        if (isTerminal) {
            //1.1是否离线
            if (resBase.getResStatus().equals(QuatzConstants.OFFLINE)) {
                //1.1.1离线 未知设备恢复，故障设备不变;终端视故障条数而定
                //1.1.1.1更新未知设备
                TreeMap<Integer, ResBase> currentResTree = (TreeMap<Integer, ResBase>) dataMap.get("currentResTree");
                List<ResBase> unknownResBaseList = new ArrayList<>();
                for (Integer resId : currentResTree.keySet()) {
                    ResBase resBaseTemp = currentResTree.get(resId);
                    if (resBaseTemp.getResStatus().equals(QuatzConstants.UNKNOWN)) {
                        resBaseTemp.setResAbnormalId(null);
                        resBaseTemp.setResAbnormalcode(null);
                        resBaseTemp.setResAbnormallevelId(null);
                        resBaseTemp.setResAbnormalName(null);
                        resBaseTemp.setResAbnormaldesc(null);
                        resBaseTemp.setResAbnomaltime(null);
                        resBaseTemp.setResRecoverytime(null);
                        resBaseTemp.setResStatus(QuatzConstants.NORMAL);
                        resBaseTemp.setResColor(QuatzConstants.RES_COLOR_NORMAL);
                        unknownResBaseList.add(resBaseTemp);
                    }
                }
                if (unknownResBaseList.size() > 0) {
                    if (!iResBaseService.updateAllColumnBatchById(unknownResBaseList)) {
                        throw new BusinessException("更新未知设备失败");
                    }
                }
                //1.1.1.2 修改终端 同1.1.2终端逻辑
            }
            //1.1.2未离线 判断此时设备树故障数量
            Map<Long, ResMoAbnormalInfo> currentResTreeAbnormalInfoMap = (Map<Long, ResMoAbnormalInfo>) dataMap.get("currentResTreeAbnormalInfo");
            if (ComUtil.isEmpty(currentResTreeAbnormalInfoMap)) {
                //1.1.2.1 =0 恢复正常
                iResBaseService.transferResToNormal(resBase);
            } else {
                //1.1.2.2 >0 找下面设备故障最高的一条作为此终端故障
                this.findLowestAbnormalInfoAndChangeResBase(resBase, currentResTreeAbnormalInfoMap);
            }
        } else {
            //2 不是终端
            //2.1 判断此故障是否是设备树终端resBase保留的故障
            ResTerminal resTerminal = iResTerminalService.selectOne(new EntityWrapper<ResTerminal>().eq("res_id",
                    resBase.getResSerialnumber()));
            if (resTerminal.getResIdTerminal().equals(resMoAbnormalInfo.getResId())) {
                //2.1.1 是 判断终端是否离线
                ResBase terminalResBase = iResBaseService.selectOne(new EntityWrapper<ResBase>().eq("res_id", resTerminal.getResIdTerminal()));
                if (!terminalResBase.getResStatus().equals(QuatzConstants.OFFLINE)) {
                    //2.1.1.1 否 修改终端状态及设备状态
                    this.findLowestAbnormalInfoAndChangeResBase(terminalResBase, (Map<Long, ResMoAbnormalInfo>) dataMap.get("currentResTreeAbnormalInfo"));
                }
                //2.1.1.2 是 不修改终端状态
            }
            //2.1.2 否 更新设备状态
            List<ResMoAbnormalInfo> currentResAbnormalInfo =
                    resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(resBase.getResId());
            //当前设备的异常信息列表
            Integer lowestPriority = Integer.MAX_VALUE;
            ResMoAbnormalInfo lowestResAbnormalInfo = null;
            for (ResMoAbnormalInfo abnormalInfo : currentResAbnormalInfo) {
                if (lowestPriority > abnormalInfo.getResAbnormallevelPriority()) {
                    lowestPriority = abnormalInfo.getResAbnormallevelPriority();
                    lowestResAbnormalInfo = abnormalInfo;
                }
            }
            resBase.setResAbnormalId(lowestResAbnormalInfo.getMoAbnormalId());
            resBase.setResAbnormalcode(lowestResAbnormalInfo.getResAbnormalCode());
            resBase.setResAbnormallevelId(lowestResAbnormalInfo.getResAbnormallevelId());
            resBase.setResAbnormalName(lowestResAbnormalInfo.getResAbnormalName());
            resBase.setResAbnormaldesc(lowestResAbnormalInfo.getResAbnormaldesc());
            resBase.setResAbnomaltime(new Date());
            resBase.setResStatus(QuatzConstants.FAULT);
            resBase.setResColor(lowestResAbnormalInfo.getResAbnormallevelColor());
            if (!iResBaseService.updateById(resBase)) {
                throw new BusinessException("更新设备失败");
            }
        }
        return result;
    }

    /**
     * 从当前设备树异常信息中找到最高的一条并更新resBase
     *
     * @param resBase
     * @param currentResTreeAbnormalInfoMap
     */
    private void findLowestAbnormalInfoAndChangeResBase(ResBase resBase, Map<Long, ResMoAbnormalInfo> currentResTreeAbnormalInfoMap) throws BusinessException {
        Integer lowestPriority = Integer.MAX_VALUE;
        ResMoAbnormalInfo lowestResAbnormalInfo = null;
        for (Long resAbnormalIdTemp : currentResTreeAbnormalInfoMap.keySet()) {
            ResMoAbnormalInfo resMoAbnormalInfoTemp = currentResTreeAbnormalInfoMap.get(resAbnormalIdTemp);
            if (lowestPriority > resMoAbnormalInfoTemp.getResAbnormallevelPriority()) {
                lowestPriority = resMoAbnormalInfoTemp.getResAbnormallevelPriority();
                lowestResAbnormalInfo = resMoAbnormalInfoTemp;
            }
        }
        resBase.setResAbnormalId(lowestResAbnormalInfo.getMoAbnormalId());
        resBase.setResAbnormalcode(lowestResAbnormalInfo.getResAbnormalCode());
        resBase.setResAbnormallevelId(lowestResAbnormalInfo.getResAbnormallevelId());
        resBase.setResAbnormalName(lowestResAbnormalInfo.getResAbnormalName());
        resBase.setResAbnormaldesc(lowestResAbnormalInfo.getResAbnormaldesc());
        resBase.setResAbnomaltime(new Date());
        resBase.setResStatus(QuatzConstants.FAULT);
        resBase.setResColor(lowestResAbnormalInfo.getResAbnormallevelColor());
        if (!iResBaseService.updateById(resBase)) {
            throw new BusinessException("更新终端失败");
        }
    }

    /**
     * 获取当前设备树(排除当前设备)
     *
     * @param resBase 此设备resbase
     * @return key:设备id value：设备resBase
     */
    private Map<Integer, ResBase> getCurrentResTree(ResBase resBase) {
        Integer resId = resBase.getResId();
        Map<Integer, ResBase> currentResTreeMap = new TreeMap<>();
        Integer terminalResId = null;
        ResBase terminalResBase = new ResBase();
        if (!isTerminal(resId)) {
            ResTerminal resTerminal = iResTerminalService.selectOne(new EntityWrapper<ResTerminal>().eq("res_id",
                    resId));
            terminalResId = resTerminal.getResId();
        } else {
            terminalResId = resId;
        }
        terminalResBase = iResBaseService.selectById(terminalResId);
        List<ResBase> devResBaseList = resBaseMapper.selectDevListByTerminalId(terminalResId, null);
        devResBaseList.stream().forEach(devResBase -> {
            Integer devResBaseResId = devResBase.getResId();
            currentResTreeMap.put(devResBaseResId, resBase);
        });
        //放入终端设备
        currentResTreeMap.put(terminalResBase.getResId(), terminalResBase);
        //删除当前设备
        currentResTreeMap.remove(resBase.getResId());
        return currentResTreeMap;
    }

    /**
     * 根据当前设备或终端res_id获取当前设备树异常信息map(排除当前信息)
     *
     * @param resMoAbnormalInfo
     * @return 设备树异常信息map key:异常信息id value：异常信息
     */
    private Map<Long, ResMoAbnormalInfo> getCurrentResTreeAbnormalInfo(ResMoAbnormalInfo resMoAbnormalInfo) {
        Integer resId = resMoAbnormalInfo.getResId();
        Map<Long, ResMoAbnormalInfo> currentResTreeAbnormalInfoMap = new TreeMap<>(new Comparator<Long>() {
            //降序
            @Override
            public int compare(Long x, Long y) {
                return (x < y) ? 1 : ((x == y) ? 0 : -1);
            }
        });
        //终端resId
        Integer terminalResId = null;
        //终端相关异常信息list
        List<ResMoAbnormalInfo> terminalResMoAbnormalInfoList = new ArrayList<>();
        if (!isTerminal(resId)) {
            ResTerminal terminal = iResTerminalService.selectOne(new EntityWrapper<ResTerminal>().eq("res_id", resId));
            terminalResId = terminal.getResId();
        } else {
            terminalResId = resId;

        }
        //存放终端下设备相关异常信息
        List<ResBase> resBaseList = resBaseMapper.selectDevListByTerminalId(terminalResId, null);
        resBaseList.stream().forEach(resBase -> {
            List<ResMoAbnormalInfo> resMoAbnormalInfos = resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(resId);
            resMoAbnormalInfos.stream().forEach(resMoAbnormalInfoTemp -> {
                currentResTreeAbnormalInfoMap.put(resMoAbnormalInfoTemp.getResAbnormalId(), resMoAbnormalInfoTemp);
            });
        });
        //存放终端的相关异常信息
        terminalResMoAbnormalInfoList = resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(terminalResId);
        terminalResMoAbnormalInfoList.stream().forEach(resMoAbnormalInfoTemp -> {
            currentResTreeAbnormalInfoMap.put(resMoAbnormalInfoTemp.getResAbnormalId(), resMoAbnormalInfoTemp);
        });
        //清除当前的这条异常
        currentResTreeAbnormalInfoMap.remove(resMoAbnormalInfo.getResAbnormalId());
        return currentResTreeAbnormalInfoMap;
    }

    /**
     * 判断此设备设否是终端
     *
     * @param resId
     * @return
     */
    private boolean isTerminal(Integer resId) {
        ResBase resBase = resBaseMapper.selectById(resId);
        return resBase.getResMtypeId().equals(QuatzConstants.ZD_MAIN_TYPE);
    }

    @Override
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
