package com.hedian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.base.QuatzConstants;
import com.hedian.entity.*;
import com.hedian.mapper.ResBaseMapper;
import com.hedian.mapper.ResMoAbnormalInfoHMapper;
import com.hedian.mapper.ResMoAbnormalInfoMapper;
import com.hedian.model.AbnormalLevelModel;
import com.hedian.model.AlarmInfoModel;
import com.hedian.model.NoticeModel;
import com.hedian.model.ResMoAbnormalInfoModel;
import com.hedian.service.*;
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
    @Resource
    private ResMoAbnormalInfoHMapper resMoAbnormalInfoHMapper;
    @Autowired
    private IResBaseService iResBaseService;
    @Autowired
    private ResBaseMapper resBaseMapper;
    @Autowired
    private IResAbnormallevelService iResAbnormallevelService;
    @Autowired
    private IResTerminalService iResTerminalService;
    @Autowired
    private IResStatusService iResStatusService;

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
        List<MoAbnormalDef> topAbnormal = resMoAbnormalInfoMapper.getTopAbnormal(map);
        List<MoAbnormalDef> topAbnormalH = resMoAbnormalInfoHMapper.getTopAbnormalH(map);
        Map<Integer, MoAbnormalDef> topAbnormalResultMap = new HashMap<>();
        if (!ComUtil.isEmpty(topAbnormalH)) {
            topAbnormalH.stream().forEach(moAbnormalDef -> {
                topAbnormalResultMap.put(moAbnormalDef.getMoAbnormalId(), moAbnormalDef);
            });
        }
        if (!ComUtil.isEmpty(topAbnormal) || !ComUtil.isEmpty(topAbnormalH)) {
            topAbnormal.stream().forEach(moAbnormalDef -> {
                topAbnormalH.stream().forEach(moAbnormalDefH -> {
                    if (moAbnormalDef.getMoAbnormalId().equals(moAbnormalDefH.getMoAbnormalId())) {
                        moAbnormalDef.setCountNum(moAbnormalDef.getCountNum() + moAbnormalDefH.getCountNum());
                    }
                });
                topAbnormalResultMap.put(moAbnormalDef.getMoAbnormalId(), moAbnormalDef);
            });
        }
        List<MoAbnormalDef> topAbnormalResult = new ArrayList<>();
        if (null != topAbnormalResultMap && !topAbnormalResultMap.isEmpty()) {
            topAbnormalResult.addAll(topAbnormalResultMap.values());
            Collections.sort(topAbnormalResult, new Comparator<MoAbnormalDef>() {
                @Override
                public int compare(MoAbnormalDef o1, MoAbnormalDef o2) {
                    return o2.getCountNum().compareTo(o1.getCountNum());
                }
            });
        }
        return topAbnormalResult;
    }

    @Override
    public Page<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, String beginTime, String endTime,
                                                              String conStatus, String abnormalLevel, String abnormalType,
                                                              String abnormalName, String mokpiName, String resName, Integer resId,
                                                              String resAlias, boolean isAutoOrder, String resAbnormalId, List<Integer> resIds) {
        return page.setRecords(resMoAbnormalInfoMapper.selectPageByCondition(page, beginTime, endTime, conStatus, abnormalLevel, abnormalType,
                abnormalName, mokpiName, resName, resId, resAlias, isAutoOrder, resAbnormalId, resIds));
    }

    @Override
    public List<AbnormalLevelModel> selectAbnormalLevelCount() {
        return resMoAbnormalInfoMapper.selectAbnormalLevelCount();
    }

    @Override
    public boolean deleteResAbnoraml(Long resAbnormalId, SysUser user) throws Exception {
        ResMoAbnormalInfo resMoAbnormalInfo = this.selectById(resAbnormalId);
        if (ComUtil.isEmpty(resMoAbnormalInfo)) {
            throw new BusinessException("找不到此条设备异常信息");
        }
        resMoAbnormalInfo.setUseflag(0);
        resMoAbnormalInfo.setDelUserId(user.getUserId());
        resMoAbnormalInfo.setDeltime(new Date());
        boolean result = this.updateById(resMoAbnormalInfo);
        if (!result) {
            throw new BusinessException("删除告警信息失败");
        }
        result = cleanOrDelete(resAbnormalId, resMoAbnormalInfo);
        return result;
    }

    @Override
    public boolean cleanResAbnormal(JSONObject requestJson, SysUser user) throws Exception {
        Long resAbnormalId = requestJson.getLong("resAbnormalId");
        String cleanInfo = requestJson.getString("cleanInfo");
        ResMoAbnormalInfo resMoAbnormalInfo = this.selectById(resAbnormalId);
        resMoAbnormalInfo.setCleanInfo(cleanInfo);
        resMoAbnormalInfo.setCleanType(2);
        resMoAbnormalInfo.setCleanUserId(user.getUserId());
        resMoAbnormalInfo.setCleanTime(new Date());
        resMoAbnormalInfo.setResRecoverytime(new Date());
        boolean result = this.updateById(resMoAbnormalInfo);
        if (!result) {
            throw new BusinessException("清除告警信息失败");
        }
        result = cleanOrDelete(resAbnormalId, resMoAbnormalInfo);
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

        //当前设备base信息
        ResBase currentResBase = resBaseMapper.selectById(resMoAbnormalInfo.getResId());
        //设备所属设备树base信息
        List<ResBase> currentResTreeResBaseList = this.getCurrentResTree(currentResBase);
        //当前设备产生的信息列表
        List<ResMoAbnormalInfo> currentResAbnormalInfoList = this.getResMoAbnormalInfoList(resMoAbnormalInfo);
        //设备所属设备树产生的故障信息
        List<ResMoAbnormalInfo> currentResTreeAbnormalInfoList = this.getCurrentResTreeAbnormalInfo(resMoAbnormalInfo);
        //判断删除的异常信息是否为终端所产生的
        boolean isTerminal = this.isTerminal(resMoAbnormalInfo.getResId());
        //1.是
        if (isTerminal) {
            //1.1终端是否离线
            if (currentResBase.getResStatus().equals(QuatzConstants.OFFLINE)) {
                //1.1.1离线 未知设备恢复，故障设备不变;终端视故障条数而定
                //1.1.1.1更新未知设备
                List<ResBase> unknownResBaseList = new ArrayList<>();
                for (ResBase currentResBaseTemp : currentResTreeResBaseList) {
                    if (currentResBaseTemp.getResStatus().equals(QuatzConstants.UNKNOWN)) {
                        currentResBaseTemp.setResAbnormalId(null);
                        currentResBaseTemp.setResAbnormalcode(null);
                        currentResBaseTemp.setResAbnormallevelId(null);
                        currentResBaseTemp.setResAbnormalName(null);
                        currentResBaseTemp.setResAbnormaldesc(null);
                        currentResBaseTemp.setResAbnomaltime(null);
                        currentResBaseTemp.setResRecoverytime(null);
                        currentResBaseTemp.setResStatus(QuatzConstants.NORMAL);
                        ResStatus resStatus = iResStatusService.selectOne(new EntityWrapper<ResStatus>().eq(
                                "res_status", QuatzConstants.NORMAL));
                        currentResBaseTemp.setResColor(resStatus.getResStatusColor());
                        unknownResBaseList.add(currentResBaseTemp);
                    }
                }
                if (unknownResBaseList.size() > 0) {
                    if (!iResBaseService.updateAllColumnBatchById(unknownResBaseList)) {
                        throw new BusinessException("更新未知设备失败");
                    }
                }
                //1.1.1.2 修改终端
                this.changeCurrentResBaseByAbnormalInfoList(currentResBase, currentResAbnormalInfoList,
                        currentResTreeAbnormalInfoList, true,
                        true);
            } else {
                //1.1.2未离线 只修改终端状态 判断此时设备树故障数量
                this.changeCurrentResBaseByAbnormalInfoList(currentResBase, currentResAbnormalInfoList,
                        currentResTreeAbnormalInfoList, false, true);
            }
        } else {
            //2.不是终端故障 判断被删除的故障优先级a与所属设备树剩下故障的最高优先级b的关系
            Integer lowestResAbnormalInfoPriority =
                    this.findLowestAbnormalInfoPriority(currentResTreeAbnormalInfoList);
            //被删除那条异常的优先级
            Integer deledAbnormalPriority = iResAbnormallevelService.selectById(resMoAbnormalInfo.getResAbnormallevelId()).getResAbnormallevelPriority();
            //设备所属终端resBase
            ResBase terminalResBase = iResBaseService.selectOne(new EntityWrapper<ResBase>().eq("res_id",
                    iResTerminalService.selectOne(new EntityWrapper<ResTerminal>().eq("res_id",
                            currentResBase.getResId())).getResIdTerminal()));
            //2.1 a>b 修改终端颜色 修改设备res_base
            if (lowestResAbnormalInfoPriority > deledAbnormalPriority) {
                //2.1.1修改终端颜色 修改设备同2.2
                //当前设备所属终端产生的异常信息列表
                List<ResMoAbnormalInfo> currentTerminalAbnormalInfoList =
                        resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(terminalResBase.getResId());
                this.changeCurrentResBaseByAbnormalInfoList(terminalResBase, currentTerminalAbnormalInfoList,
                        currentResTreeAbnormalInfoList, true, true);
            }
            //2.2 a<=b 修改设备res_base
            boolean terminalOffLineFlag = terminalResBase.getResStatus().equals(QuatzConstants.OFFLINE);
            this.changeCurrentResBaseByAbnormalInfoList(currentResBase, currentResAbnormalInfoList,
                    currentResTreeAbnormalInfoList, terminalOffLineFlag, false);
        }
        //通知客户短发送websocket请求
        NoticeModel noticeModel = new NoticeModel();
        noticeModel.setType(1);
        MyWebSocketService.sendMessageAll(JSONObject.toJSONString(noticeModel));
        return result;
    }

    /**
     * 从异常信息列表中找到优先级最低的一条
     *
     * @param currentResTreeAbnormalInfoList
     * @return
     */
    private Integer findLowestAbnormalInfoPriority(List<ResMoAbnormalInfo> currentResTreeAbnormalInfoList) {
        Integer lowestPriority = Integer.MAX_VALUE;
        if (!ComUtil.isEmpty(currentResTreeAbnormalInfoList)) {
            for (ResMoAbnormalInfo resMoAbnormalInfoTemp : currentResTreeAbnormalInfoList) {
                if (lowestPriority > resMoAbnormalInfoTemp.getResAbnormallevelPriority()) {
                    lowestPriority = resMoAbnormalInfoTemp.getResAbnormallevelPriority();
                }
            }
        }
        return lowestPriority;
    }

    /**
     * 获取此设备产生的异常信息列表
     *
     * @param resMoAbnormalInfo
     * @return
     */
    private List<ResMoAbnormalInfo> getResMoAbnormalInfoList(ResMoAbnormalInfo resMoAbnormalInfo) {
        List<ResMoAbnormalInfo> currentResAbnormalInfoList =
                resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(resMoAbnormalInfo.getResId());
        //清除当前的这条异常
        Iterator<ResMoAbnormalInfo> iterator = currentResAbnormalInfoList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getResAbnormalId().equals(resMoAbnormalInfo.getResAbnormalId())) {
                iterator.remove();
            }
        }
        Collections.sort(currentResAbnormalInfoList, new Comparator<ResMoAbnormalInfo>() {
            @Override
            public int compare(ResMoAbnormalInfo o1, ResMoAbnormalInfo o2) {
                return o1.getMoAbnormalId().compareTo(o2.getMoAbnormalId());
            }
        });
        return currentResAbnormalInfoList;
    }

    /**
     * 根据当前异常信息列表更新resBase
     *
     * @param currentResBase
     * @param currentResAbnormalInfoList
     * @param currentResTreeAbnormalInfoList
     */
    private void changeCurrentResBaseByAbnormalInfoList(ResBase currentResBase,
                                                        List<ResMoAbnormalInfo> currentResAbnormalInfoList,
                                                        List<ResMoAbnormalInfo> currentResTreeAbnormalInfoList,
                                                        boolean terminalOffLineFlag,
                                                        boolean isTerminalFlag) throws BusinessException {
        Integer lowestPriority = Integer.MAX_VALUE;
        //优先级最低的异常
        ResMoAbnormalInfo lowestResAbnormalInfo = null;
        //1判断此资源产生异常数量
        if (ComUtil.isEmpty(currentResAbnormalInfoList)) {
            //1.1 为0,判断所属终端是否是离线
            currentResBase.setResAbnormalId(null);
            currentResBase.setResAbnormalcode(null);
            currentResBase.setResAbnormallevelId(null);
            currentResBase.setResAbnormalName(null);
            currentResBase.setResAbnormaldesc(null);
            currentResBase.setResAbnomaltime(null);
            currentResBase.setResRecoverytime(null);
            if (!isTerminalFlag && terminalOffLineFlag) {
                //1.1.1 是,此设备状态修改为未知
                currentResBase.setResStatus(QuatzConstants.UNKNOWN);
                ResStatus resStatus = iResStatusService.selectOne(new EntityWrapper<ResStatus>().eq("res_status", QuatzConstants.UNKNOWN));
                currentResBase.setResColor(resStatus.getResStatusColor());
            } else {
                //1.1.2 否,设备状态修改为正常
                currentResBase.setResStatus(QuatzConstants.NORMAL);
                ResStatus resStatus = iResStatusService.selectOne(new EntityWrapper<ResStatus>().eq("res_status",
                        QuatzConstants.NORMAL));
                currentResBase.setResColor(resStatus.getResStatusColor());
            }

        } else {
            //1.2 不为0 修改为异常
            for (ResMoAbnormalInfo resMoAbnormalInfoTemp : currentResAbnormalInfoList) {
                if (lowestPriority > resMoAbnormalInfoTemp.getResAbnormallevelPriority()) {
                    lowestPriority = resMoAbnormalInfoTemp.getResAbnormallevelPriority();
                    lowestResAbnormalInfo = resMoAbnormalInfoTemp;
                }
            }
            currentResBase.setResAbnormalId(lowestResAbnormalInfo.getMoAbnormalId());
            currentResBase.setResAbnormalcode(lowestResAbnormalInfo.getResAbnormalCode());
            currentResBase.setResAbnormallevelId(lowestResAbnormalInfo.getResAbnormallevelId());
            currentResBase.setResAbnormalName(lowestResAbnormalInfo.getResAbnormalName());
            currentResBase.setResAbnormaldesc(lowestResAbnormalInfo.getResAbnormaldesc());
            currentResBase.setResAbnomaltime(new Date());
            currentResBase.setResStatus(QuatzConstants.FAULT);
            currentResBase.setResColor(lowestResAbnormalInfo.getResAbnormallevelColor());
        }
        //终端还要重新计算res_color
        if (isTerminalFlag) {
            //不用判断是否离线,如果删除的是终端离线告警,则该设备必然只有一条告警,只用更改颜色
            if (!ComUtil.isEmpty(currentResTreeAbnormalInfoList)) {
                for (ResMoAbnormalInfo resMoAbnormalInfoTemp : currentResTreeAbnormalInfoList) {
                    if (lowestPriority > resMoAbnormalInfoTemp.getResAbnormallevelPriority()) {
                        lowestPriority = resMoAbnormalInfoTemp.getResAbnormallevelPriority();
                    }
                }
                ResAbnormallevel lowestAbnormallevelInResTree =
                        iResAbnormallevelService.selectOne(new EntityWrapper<ResAbnormallevel>().eq("res_abnormallevel_priority", lowestPriority));
                currentResBase.setResColor(lowestAbnormallevelInResTree.getResAbnormallevelColor());
            }
        }
        if (!iResBaseService.updateAllColumnById(currentResBase)) {
            throw new BusinessException("更新基本信息失败");
        }
    }

    /**
     * 获取当前设备树(排除当前设备)
     * d
     *
     * @param currentResBase 此设备resbase
     * @return
     */
    private List<ResBase> getCurrentResTree(ResBase currentResBase) {
        Integer resId = currentResBase.getResId();
        List<ResBase> currentResTreeList = new ArrayList<>();
        Integer terminalResId = null;
        ResBase terminalResBase = new ResBase();
        if (!isTerminal(resId)) {
            ResTerminal resTerminal = iResTerminalService.selectOne(new EntityWrapper<ResTerminal>().eq("res_id",
                    resId));
            terminalResId = resTerminal.getResIdTerminal();
        } else {
            terminalResId = resId;
        }
        terminalResBase = iResBaseService.selectById(terminalResId);
        currentResTreeList = resBaseMapper.selectDevListByTerminalId(terminalResId, null);
        //放入终端设备
        currentResTreeList.add(terminalResBase);
        //删除当前设备
        Iterator<ResBase> iterator = currentResTreeList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getResId().equals(currentResBase.getResId())) {
                iterator.remove();
            }
        }
        return currentResTreeList;
    }

    /**
     * 根据当前设备或终端res_id获取当前设备树异常信息(排除当前信息)
     *
     * @param resMoAbnormalInfo
     * @return
     */
    private List<ResMoAbnormalInfo> getCurrentResTreeAbnormalInfo(ResMoAbnormalInfo resMoAbnormalInfo) {
        Integer resId = resMoAbnormalInfo.getResId();
        //终端resId
        Integer terminalResId = null;
        List<ResMoAbnormalInfo> currentResTreeAbnormalInfoList = new ArrayList<>();
        //终端相关异常信息list
        List<ResMoAbnormalInfo> terminalResMoAbnormalInfoList = new ArrayList<>();
        if (!isTerminal(resId)) {
            ResTerminal terminal = iResTerminalService.selectOne(new EntityWrapper<ResTerminal>().eq("res_id", resId));
            terminalResId = terminal.getResIdTerminal();
        } else {
            terminalResId = resId;
        }
        //存放终端下设备相关异常信息
        List<ResBase> devResBaseList = resBaseMapper.selectDevListByTerminalId(terminalResId, null);
        devResBaseList.stream().forEach(devResBaseTemp -> {
            List<ResMoAbnormalInfo> resMoAbnormalInfos =
                    resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(devResBaseTemp.getResId());
            resMoAbnormalInfos.stream().forEach(resMoAbnormalInfoTemp -> {
                currentResTreeAbnormalInfoList.add(resMoAbnormalInfoTemp);
            });
        });
        //存放终端的相关异常信息
        terminalResMoAbnormalInfoList = resMoAbnormalInfoMapper.findAbnormalAndPriorityInfoByResId(terminalResId);
        terminalResMoAbnormalInfoList.stream().forEach(resMoAbnormalInfoTemp -> {
            currentResTreeAbnormalInfoList.add(resMoAbnormalInfoTemp);
        });
        //清除当前的这条异常
        Iterator<ResMoAbnormalInfo> iterator = currentResTreeAbnormalInfoList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getResAbnormalId().equals(resMoAbnormalInfo.getResAbnormalId())) {
                iterator.remove();
            }
        }
        //按照resAbnormalId降序排列
        Collections.sort(currentResTreeAbnormalInfoList, new Comparator<ResMoAbnormalInfo>() {
            @Override
            public int compare(ResMoAbnormalInfo o1, ResMoAbnormalInfo o2) {
                return o1.getMoAbnormalId().compareTo(o2.getMoAbnormalId());
            }
        });
        return currentResTreeAbnormalInfoList;
    }

    /**
     * 判断此设备设否是终端
     *
     * @param resId
     * @return
     */
    private boolean isTerminal(Integer resId) {
        ResBase currentResBase = resBaseMapper.selectById(resId);
        return currentResBase.getResMtypeId().equals(QuatzConstants.ZD_MAIN_TYPE);
    }

}
