package com.hedian.fault;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.hedian.base.QuatzConstants;
import com.hedian.entity.*;
import com.hedian.service.*;
import com.hedian.utils.HdywUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author why
 * 定时任务，将数据库 采集的数据  进行故障逻辑判断
 */
@Component
public class QuartzUtil {

    private static Logger log = Logger.getLogger(QuartzUtil.class.getName());

    @Autowired
    private IResBaseService resBaseService;
    @Autowired
    private ISysConfService sysConfService;
    @Autowired
    private IMqttTerminalService mqttTerminalService;
    @Autowired
    private IResMoAbnormalInfoService resMoAbnormalInfoService;
    @Autowired
    private IMoThresholdService moThresholdService;
    @Autowired
    private IMoAbnormalDefService moAbnormalDefService;
    @Autowired
    private IResAbnormallevelService resAbnormallevelService;


    /**
     * 获取当前日期
     *
     * @return
     */
    private Date getCurrentDate() {
        Date date = null;
        if (date == null) {
            date = new Date();
        }
        return date;
    }

    /**
     * 20S查询一次数据库更新 设备信息，检测报警  更新日志等
     */
//    @Scheduled(fixedDelay = QuatzConstants.ONE_MINUTE)
    @Transactional(rollbackFor = Exception.class)
    public void updateResBase() {
        //取出系统配置时间 c_type=20000 parakey为offline_count和offline_interval的配置值
        SysConf sysConf = sysConfService.selectOne(new EntityWrapper<SysConf>().where("c_type={0}", QuatzConstants.SYS_TIME_CONFIG));
        int timeFlag = 0;
        if (null != sysConf) {
            try {
                timeFlag = Integer.parseInt(sysConf.getParavalue()) * Integer.parseInt(sysConf.getParavalue());
            } catch (NumberFormatException e) {
                log.info("parse int failed");
            }
        }
        //根据1001查询所有系统下的终端
        List<ResBase> resBaseList = resBaseService.selectList(new EntityWrapper<ResBase>().where("res_mtype_id={0}", QuatzConstants.ZD_MAIN_TYPE));

        if (null != resBaseList && resBaseList.size() > 0) {
            for (ResBase resBase : resBaseList) {
                //根据终端序列号  找到采集数据
                MqttTerminal mqttTerminal = mqttTerminalService.selectOne(new EntityWrapper<MqttTerminal>().eq("res_serialnumber", resBase.getResSerialnumber()));
                if (null != mqttTerminal) {
                    //组装数据 阀值故障判断
                    Map<String, ResBase> mainMap = new LinkedHashMap<>();
                    //采集数据map
                    Map<String, String> dataMap = HdywUtils.getCollectionData(resBase.getResSerialnumber());
                    //封装Map
                    HdywUtils.getMainMap(mainMap, resBase.getResSerialnumber(), resBase, dataMap, 0);
                    if (mqttTerminal.getTerminalStatus().equals(QuatzConstants.TERMINAL_POWER_DOWN)) {
                        //判断掉电

                        if (!resBase.getResStatus().equals(QuatzConstants.OFFLINE)) {
                            offlineError(mainMap, resBase.getResSerialnumber(), 0);
                        }
                    } else if ((System.currentTimeMillis() - mqttTerminal.getCreatetime().getTime()) / 1000 > timeFlag) {
                        //判断离线
                        if (!resBase.getResStatus().equals(QuatzConstants.OFFLINE)) {
                            offlineError(mainMap, resBase.getResSerialnumber(), 1);
                        }
                    } else {
                        Map<String, Object> rootMapCache = new HashMap<>(16);
                        //记录终端机终端下面  异常模版定义 最小的一个
                        rootMapCache.put(QuatzConstants.ROOTPRIORITY, Integer.MAX_VALUE);
                        rootMapCache.put(QuatzConstants.ROOTCOLOR, null);
                        rootMapCache.put(QuatzConstants.ROOTBASE, null);
                        compareThresholds(mainMap, resBase.getResSerialnumber(), rootMapCache, 0);
                        //更新终端显示的最终颜色
                        if (null != rootMapCache.get(QuatzConstants.ROOTBASE)) {
                            //更新终端
                            ResBase rootbase = (ResBase) rootMapCache.get(QuatzConstants.ROOTBASE);
                            rootbase.setResColor((String) rootMapCache.get(QuatzConstants.ROOTCOLOR));
                            resBaseService.updateById(rootbase);
                            log.info("update rootBase" + rootbase.getResAlias() + " Successful!");
                        }
                    }
                }
            }
        }
    }

    /**
     * 阀值故障判断
     *
     * @param mainMap
     * @param rootMapCache 记录终端机终端下面  异常模版定义 最小的一个 并记录颜色
     * @param flag         0 表示终端
     */
    private String compareThresholds(Map<String, ResBase> mainMap, String resBaseKey, Map<String, Object> rootMapCache, Integer flag) {
        if (null != mainMap && !mainMap.isEmpty()) {
            ResBase rootResBase = mainMap.get(resBaseKey);
            Map<Integer, MoKpi> moKpiMap = rootResBase.getKpiIdMap();
            Map<Integer, ResMoAbnormalInfo> tblResMoAbnormalInfos = rootResBase.getTerminalErrInfos();
            //记录base kpi 指标故障恢复个数
            int restoreNums = 0;
            //记录故障等级最小的颜色
            String color = "";
            //记录每个故障里面 level等级最小的
            int levelPriority = Integer.MAX_VALUE;
            //缓存当前故障等级定义
            MoAbnormalDef abnormalDefCache = null;
            //缓存 mokpi
            MoKpi errMoKpi = null;
            //缓存 dataValue
            String errDataValue = null;
            //缓存 MoThreshold
            MoThreshold errMoThreshold = null;
            //如果有离线故障 先恢复离线故障
            if (flag == 0 && null != tblResMoAbnormalInfos.get(QuatzConstants.OFFLINE_KEY)) {
                //恢复离线故障
                restoreNums++;
                ResMoAbnormalInfo resMoAbnormalInfo = tblResMoAbnormalInfos.get(10000);
                resMoAbnormalInfo.setResAbnormalstatus(0);
                resMoAbnormalInfo.setResRecoverytime(new Date());
                //修改
                resMoAbnormalInfoService.updateById(resMoAbnormalInfo);
            }
            if (null != moKpiMap && !moKpiMap.isEmpty()) {
                for (Integer kpiId : moKpiMap.keySet()) {
                    MoKpi moKpi = moKpiMap.get(kpiId);
                    //采集数据不为空
                    String targetValue = moKpi.getDataValue();
                    if (StringUtils.isNotEmpty(targetValue)) {
                        Map<String, Object> tblMoThresholdMap = new HashMap(16);
                        tblMoThresholdMap.put("res_stype_id", rootResBase.getResStypeId());
                        tblMoThresholdMap.put("mo_kpi_id", kpiId);
                        //查询当前对象当前kpi_id下有多少阀值规则
                        List<MoThreshold> tblMoThresholds = moThresholdService.selectByMap(tblMoThresholdMap);
                        if (null != tblMoThresholds && tblMoThresholds.size() > 0) {
                            MoThreshold moThresholdCache = getMoThreshold(targetValue, tblMoThresholds);
                            if (null != moThresholdCache) {
                                log.info(rootResBase.getResAlias() + "产生的最高异常警告为id为：" + moThresholdCache.getMoThId()
                                        + "类型为：" + moThresholdCache.getMoThType());
                                //有异常, 查询监控异常定义
                                MoAbnormalDef moAbnormalDef = moAbnormalDefService.selectById(moThresholdCache.getMoAbnormalId());
                                //异常定义level等级
                                ResAbnormallevel resAbnormalLevel = resAbnormallevelService.selectById(moAbnormalDef.getResAbnormallevelId());
                                //监控异常模版翻译
                                String errMainInfo = TranslateTemplateUtil.translateTemplate(moAbnormalDef.getMoAbnormalShowtemplate(), targetValue, moThresholdCache, moKpi,
                                        resAbnormalLevel, moAbnormalDef, rootResBase, rootResBase.getResMainType(), rootResBase.getResSubtype());
                                ResMoAbnormalInfo resMoAbnormalInfo = new ResMoAbnormalInfo();
                                resMoAbnormalInfo.setResId(rootResBase.getResId());
                                resMoAbnormalInfo.setMoAbnormalId(moAbnormalDef.getMoAbnormalId());
                                resMoAbnormalInfo.setMoThId(moThresholdCache.getMoThId());
                                resMoAbnormalInfo.setMoKpiId(moThresholdCache.getMoKpiId());
                                resMoAbnormalInfo.setResAbnormalCode(moAbnormalDef.getMoAbnormalcode());
                                resMoAbnormalInfo.setResAbnormalName(moAbnormalDef.getMoAbnormalName());
                                resMoAbnormalInfo.setResAbnormallevelId(moAbnormalDef.getResAbnormallevelId());
                                resMoAbnormalInfo.setResAbnomaltime(getCurrentDate());
                                //异常信息翻译
                                resMoAbnormalInfo.setResAbnormaldesc(errMainInfo);
                                resMoAbnormalInfo.setResAbnormalvalue(targetValue);
                                resMoAbnormalInfo.setResAbnormalstatus(1);
                                if (levelPriority > resAbnormalLevel.getResAbnormallevelPriority()) {
                                    levelPriority = resAbnormalLevel.getResAbnormallevelPriority();
                                    color = resAbnormalLevel.getResAbnormallevelColor();
                                    try {
                                        abnormalDefCache = (MoAbnormalDef) BeanUtils.cloneBean(moAbnormalDef);
                                        errMoKpi = (MoKpi) BeanUtils.cloneBean(moKpi);
                                        errDataValue = targetValue;
                                        errMoThreshold = (MoThreshold) BeanUtils.cloneBean(moThresholdCache);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        log.info("copy object failed");
                                    }
                                }
                                if (null != tblResMoAbnormalInfos.get(kpiId)) {
                                    resMoAbnormalInfo.setResAbnormalId(tblResMoAbnormalInfos.get(kpiId).getResAbnormalId());
                                    //修改
                                    resMoAbnormalInfoService.updateById(resMoAbnormalInfo);
                                } else {
                                    resMoAbnormalInfoService.insert(resMoAbnormalInfo);
                                }
                            } else {
                                //没有异常   判断当前kpi异常表有没有异常 有则恢复， 没有跳过
                                if (null != tblResMoAbnormalInfos.get(kpiId)) {
                                    restoreNums++;
                                    ResMoAbnormalInfo resMoAbnormalInfo = tblResMoAbnormalInfos.get(kpiId);
                                    resMoAbnormalInfo.setResRevoveryvalue(targetValue);
                                    resMoAbnormalInfo.setResAbnormalstatus(0);
                                    resMoAbnormalInfo.setResRecoverytime(getCurrentDate());
                                    //修改
                                    resMoAbnormalInfoService.updateById(resMoAbnormalInfo);
                                }
                            }
                        }
                    }
                }
            }
            //更新 base表  显示多有指标下面  最严重的一条
            updateOrRestorResbase(rootResBase, tblResMoAbnormalInfos, restoreNums, color, abnormalDefCache, rootMapCache, errMoKpi, errDataValue, errMoThreshold, flag);
            if (null != rootResBase.getTerminalObjct() && !rootResBase.getTerminalObjct().isEmpty()) {
                for (String linkPort : rootResBase.getTerminalObjct().keySet()) {
                    compareThresholds(rootResBase.getTerminalObjct(), linkPort, rootMapCache, 1);
                }
            }
        }
        return (String) rootMapCache.get(QuatzConstants.ROOTCOLOR);
    }

    /**
     * 阀值比较规则
     *
     * @param targetValue
     * @param tblMoThresholds
     * @return
     */
    private static MoThreshold getMoThreshold(String targetValue, List<MoThreshold> tblMoThresholds) {
        Map<String, Object> mapCache = new HashMap<>(16);
        //记录每个规则里面等级最小的一个
        mapCache.put(QuatzConstants.MOTHPRIORITY, Integer.MAX_VALUE);
        //缓存当前规则数据
        mapCache.put(QuatzConstants.MOTHRESHOLD, null);
        for (MoThreshold tblMoThreshold : tblMoThresholds) {
            if (tblMoThreshold.getMoThType() == 1 || tblMoThreshold.getMoThType() == 3) {
                BigDecimal dateValue = new BigDecimal(targetValue);
                //判断是否有基准值
                if (null != tblMoThreshold.getMoThBase()) {
                    dateValue = dateValue.divide(tblMoThreshold.getMoThBase(), 2, BigDecimal.ROUND_HALF_UP);
                }
                //mo_th_type = 1 上下限判断
                //若需要比较的值大于mo_th_up或者小于mo_th_down，（如果mo_th_inupdown=1则为大于等于或者小于等于），则匹配到该阈值规则。
                if (tblMoThreshold.getMoThInupdown() == 1) {
                    compareDownOrUp(mapCache, tblMoThreshold, dateValue, 1);
                } else {
                    compareDownOrUp(mapCache, tblMoThreshold, dateValue, 0);
                }
            } else if (tblMoThreshold.getMoThType() == 2 || tblMoThreshold.getMoThType() == 4) {
                BigDecimal dateValue = new BigDecimal(targetValue);
                //判断是否有基准值
                if (null != tblMoThreshold.getMoThBase()) {
                    dateValue = dateValue.divide(tblMoThreshold.getMoThBase(), 2, BigDecimal.ROUND_HALF_UP);
                }
                //若需要比较的值在mo_th_down和mo_th_up之间，（如果mo_th_inupdown=1则包含mo_th_down和mo_th_up），则匹配到该阈值规则
                if (tblMoThreshold.getMoThInupdown() == 1) {
                    if (dateValue.compareTo(tblMoThreshold.getMoThDown()) >= 0 && dateValue.compareTo(tblMoThreshold.getMoThUp()) <= 0) {
                        findMinMoThreshold(mapCache, tblMoThreshold);
                    }
                } else if (dateValue.compareTo(tblMoThreshold.getMoThDown()) > 0 && dateValue.compareTo(tblMoThreshold.getMoThUp()) < 0) {
                    findMinMoThreshold(mapCache, tblMoThreshold);
                }
            } else if (tblMoThreshold.getMoThType() == 5) {
                //mo_th_type = 5 等于判断
                //若需要比较的值等于mo_th_value，则匹配到该阈值规则
                if (targetValue.equals(tblMoThreshold.getMoThValue().trim())) {
                    findMinMoThreshold(mapCache, tblMoThreshold);
                }
            }
        }

        return (MoThreshold) mapCache.get(QuatzConstants.MOTHRESHOLD);
    }

    /**
     * 区间三种情况判断
     *
     * @param mapCache
     * @param tblMoThreshold
     * @param dateValue
     * @param flag           1 包含=  0不包含
     */
    private static Map<String, Object> compareDownOrUp(Map<String, Object> mapCache, MoThreshold tblMoThreshold, BigDecimal dateValue, Integer flag) {
        if (null == tblMoThreshold.getMoThDown()) {
            //比较大值
            boolean flagResult = flag == 1 ? dateValue.compareTo(tblMoThreshold.getMoThUp()) >= 0 : dateValue.compareTo(tblMoThreshold.getMoThUp()) > 0;
            if (flagResult) {
                findMinMoThreshold(mapCache, tblMoThreshold);
            }
        } else if (null == tblMoThreshold.getMoThUp()) {
            //比较小值
            boolean flagResult = flag == 1 ? dateValue.compareTo(tblMoThreshold.getMoThDown()) <= 0 : dateValue.compareTo(tblMoThreshold.getMoThDown()) < 0;
            if (flagResult) {
                findMinMoThreshold(mapCache, tblMoThreshold);
            }
        } else {
            //都比较
            boolean flagResult = flag == 1 ?
                    (dateValue.compareTo(tblMoThreshold.getMoThDown()) <= 0 || dateValue.compareTo(tblMoThreshold.getMoThUp()) >= 0)
                    : (dateValue.compareTo(tblMoThreshold.getMoThDown()) < 0 || dateValue.compareTo(tblMoThreshold.getMoThUp()) > 0);
            if (flagResult) {
                findMinMoThreshold(mapCache, tblMoThreshold);
            }
        }
        return mapCache;
    }

    /**
     * 比较moThPriority 找到异常最严重的规则
     *
     * @param mapCache
     * @param moThreshold
     * @return
     */
    private static Map<String, Object> findMinMoThreshold(Map<String, Object> mapCache, MoThreshold moThreshold) {
        if ((Integer) mapCache.get(QuatzConstants.MOTHPRIORITY) > moThreshold.getMoThPriority()) {
            mapCache.put(QuatzConstants.MOTHPRIORITY, moThreshold.getMoThPriority());
            try {
                mapCache.put(QuatzConstants.MOTHRESHOLD, BeanUtils.cloneBean(moThreshold));
            } catch (Exception e) {
                e.printStackTrace();
                log.info("copy moThreshold object failed");
            }
        }
        return mapCache;
    }

    /**
     * 更新 或者 恢复 base表信息
     *
     * @param rootBase
     * @param tblResMoAbnormalInfos
     * @param restoreNums
     * @param color
     * @param abnormalDefCache
     * @param rootMapCache
     * @param errMoKpi
     * @param errMoThreshold
     * @param flag                  是否是终端  0是
     */
    protected void updateOrRestorResbase(ResBase rootBase, Map<Integer, ResMoAbnormalInfo> tblResMoAbnormalInfos, int restoreNums, String color,
                                         MoAbnormalDef abnormalDefCache, Map<String, Object> rootMapCache, MoKpi errMoKpi, String errDataValue,
                                         MoThreshold errMoThreshold, Integer flag) {
        //TODO 待优化
        //说明  base 下面有异常信息
        if (null != abnormalDefCache) {
            ResAbnormallevel resAbnormalLevel = resAbnormallevelService.selectById(abnormalDefCache.getResAbnormallevelId());
            String errMainInfo = TranslateTemplateUtil.translateTemplate(abnormalDefCache.getMoAbnormalShowtemplate(), errDataValue, errMoThreshold, errMoKpi, resAbnormalLevel, abnormalDefCache,
                    rootBase, rootBase.getResMainType(), rootBase.getResSubtype());
            //更新主设备表
            rootBase.setResStatus(QuatzConstants.FAULT);
            rootBase.setResAbnormalId(abnormalDefCache.getMoAbnormalId());
            rootBase.setResAbnormalcode(abnormalDefCache.getMoAbnormalcode());
            rootBase.setResAbnormallevelId(abnormalDefCache.getResAbnormallevelId());
            rootBase.setResAbnormaldesc(errMainInfo);
            rootBase.setResAbnomaltime(getCurrentDate());
            rootBase.setResRecoverytime(null);
            rootBase.setResAbnormalName(abnormalDefCache.getMoAbnormalName());
            if ((Integer) rootMapCache.get(QuatzConstants.ROOTPRIORITY) > resAbnormalLevel.getResAbnormallevelPriority()) {
                rootMapCache.put(QuatzConstants.ROOTPRIORITY, resAbnormalLevel.getResAbnormallevelPriority());
                rootMapCache.put(QuatzConstants.ROOTCOLOR, resAbnormalLevel.getResAbnormallevelColor());
            }
            //根节点缓存起来
            if (flag == 0) {
                rootMapCache.put(QuatzConstants.ROOTBASE, rootBase);
            } else {
                //其他正常更新
                rootBase.setResColor(color);
                resBaseService.updateById(rootBase);
            }
        } else if ((restoreNums == tblResMoAbnormalInfos.size() && tblResMoAbnormalInfos.size() > 0)
                || rootBase.getResStatus().equals(QuatzConstants.UNKNOWN)
                || rootBase.getResStatus().equals(QuatzConstants.OFFLINE)) {
            //如果当前设备下面所有指标  异常信息恢复完
            //恢复base 异常
            rootBase.setResStatus(QuatzConstants.NORMAL);
            rootBase.setResAbnormalId(null);
            rootBase.setResAbnormalcode(null);
            rootBase.setResAbnormallevelId(null);
            rootBase.setResAbnormaldesc(null);
            rootBase.setResAbnomaltime(null);
            rootBase.setResAbnormalName(null);
            rootBase.setResRecoverytime(getCurrentDate());
            if (flag == 0) {
                rootMapCache.put(QuatzConstants.ROOTBASE, rootBase);
            } else {
                rootBase.setResColor(null);
                resBaseService.updateById(rootBase);
            }
        }
        //终端正常没有异常数据是也要把对象放进去
        if (flag == 0 && null == rootMapCache.get(QuatzConstants.ROOTBASE)) {
            rootMapCache.put(QuatzConstants.ROOTBASE, rootBase);
        }
    }



    /**
     * 离线异常记录
     *
     * @param mainMap
     * @param flag    0是掉电 1是离线
     */
    private void offlineError(Map<String, ResBase> mainMap, String resBaseKey, Integer flag) {
        //TODO　
        //查询离线异常定义
        ResBase rootResBase = mainMap.get(resBaseKey);
        MoAbnormalDef moAbnormalDef = null;
        ResAbnormallevel resAbnormallevel = null;

        if (flag == 0) {
            moAbnormalDef = moAbnormalDefService.selectById(QuatzConstants.PD_MO_ABNORMAL_ID);
            resAbnormallevel = resAbnormallevelService.selectById(moAbnormalDef.getResAbnormallevelId());
        } else {
            moAbnormalDef =  moAbnormalDefService.selectById(QuatzConstants.LX_MO_ABNORMAL_ID);
            resAbnormallevel = resAbnormallevelService.selectById(moAbnormalDef.getResAbnormallevelId());
        }
        String errMainInfo = TranslateTemplateUtil.translateTemplate(moAbnormalDef.getMoAbnormalShowtemplate(), null, null, null, resAbnormallevel,
                moAbnormalDef, rootResBase, rootResBase.getResMainType(), rootResBase.getResSubtype());
        ResMoAbnormalInfo resMoAbnormalInfo = new ResMoAbnormalInfo();
        resMoAbnormalInfo.setResId(rootResBase.getResId());
        resMoAbnormalInfo.setMoAbnormalId(moAbnormalDef.getMoAbnormalId());
        resMoAbnormalInfo.setResAbnormalCode(moAbnormalDef.getMoAbnormalcode());
        resMoAbnormalInfo.setResAbnormalName(moAbnormalDef.getMoAbnormalName());
        resMoAbnormalInfo.setResAbnormallevelId(moAbnormalDef.getResAbnormallevelId());
        resMoAbnormalInfo.setMoKpiId(QuatzConstants.OFFLINE_KEY);
        //异常信息翻译
        resMoAbnormalInfo.setResAbnormaldesc(errMainInfo);
        resMoAbnormalInfo.setResAbnomaltime(getCurrentDate());
        resMoAbnormalInfo.setResAbnormalstatus(1);
        resMoAbnormalInfoService.insert(resMoAbnormalInfo);
        //更新base表
        rootResBase.setResStatus(QuatzConstants.OFFLINE);
        rootResBase.setResAbnormalId(moAbnormalDef.getMoAbnormalId());
        rootResBase.setResAbnormalcode(moAbnormalDef.getMoAbnormalcode());
        rootResBase.setResAbnormallevelId(moAbnormalDef.getResAbnormallevelId());
        rootResBase.setResAbnormaldesc(errMainInfo);
        rootResBase.setResAbnomaltime(getCurrentDate());
        rootResBase.setResRecoverytime(null);
        rootResBase.setResColor(resAbnormallevel.getResAbnormallevelColor());
        resBaseService.updateById(rootResBase);
        //通过 base 查询下面所有终端设备  并显示未知
        Map<String, ResBase> terminalObjects = rootResBase.getTerminalObjct();
        if (null != terminalObjects && !terminalObjects.isEmpty()) {
            for (String devId : terminalObjects.keySet()) {
                ResBase resBaseInfo = terminalObjects.get(devId);
                if (!resBaseInfo.getResStatus().equals(QuatzConstants.FAULT)) {
                    MoAbnormalDef moAbnormalDefInfo = moAbnormalDefService.selectById(QuatzConstants.WZ_MO_ABNORMAL_ID);
                    ResAbnormallevel resAbnormallevelInfo = resAbnormallevelService.selectById(moAbnormalDefInfo.getResAbnormallevelId());
                    String errTerminalInfo = TranslateTemplateUtil.translateTemplate(moAbnormalDefInfo.getMoAbnormalShowtemplate(), null, null, null,
                            resAbnormallevelInfo, moAbnormalDefInfo, resBaseInfo, resBaseInfo.getResMainType(), resBaseInfo.getResSubtype());
                    //更新base表
                    resBaseInfo.setResStatus(QuatzConstants.UNKNOWN);
                    resBaseInfo.setResAbnormalId(moAbnormalDefInfo.getMoAbnormalId());
                    resBaseInfo.setResAbnormalcode(moAbnormalDefInfo.getMoAbnormalcode());
                    resBaseInfo.setResAbnormallevelId(moAbnormalDefInfo.getResAbnormallevelId());
                    resBaseInfo.setResAbnormaldesc(errTerminalInfo);
                    resBaseInfo.setResAbnomaltime(getCurrentDate());
                    resBaseInfo.setResRecoverytime(null);
                    resBaseInfo.setResColor(resAbnormallevelInfo.getResAbnormallevelColor());
                    resBaseService.updateById(resBaseInfo);
                }
            }
        }
    }
}
