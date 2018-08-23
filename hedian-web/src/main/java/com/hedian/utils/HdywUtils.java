package com.hedian.utils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.entity.*;
import com.hedian.fault.QuatzConstants;
import com.hedian.service.IMqttDevService;
import com.hedian.service.IResMoAbnormalInfoService;
import com.hedian.service.IResStypeKpiService;
import com.hedian.service.IResTerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class HdywUtils {

    @Autowired
    private IMqttDevService mqttDevService;
    @Autowired
    private IResStypeKpiService resStypeKpiService;
    @Autowired
    private IResTerminalService resTerminalService;
    @Autowired
    private IResMoAbnormalInfoService resMoAbnormalInfoService;

    public static HdywUtils hdywUtils;

    public HdywUtils() {

    }

    @PostConstruct
    public void init() {
        hdywUtils = this;
    }


    /**
     * 获取采集数据
     *
     * @param resSerialnumber
     * @return
     */
    public static Map<String, String> getCollectionData(String resSerialnumber) {
        //采集数据map
        Map<String, String> dataMap = new LinkedHashMap<>();
        List<MqttDev> tblMqttDevList = hdywUtils.mqttDevService.selectList(new EntityWrapper<MqttDev>().eq("res_serialnumber", resSerialnumber));
        if (null != tblMqttDevList && tblMqttDevList.size() > 0) {
            for (MqttDev mqttDev : tblMqttDevList) {
                dataMap.put(mqttDev.getDevId() + "_" + mqttDev.getDataType(), mqttDev.getDataValue());
            }
        }
        return dataMap;
    }

    /**
     * 封装终端及下面子集的数据结构
     * <>
     * Map<String,ResBase>
     * ResBase
     * Map<kpiId,MoKpi>
     * Map<kpiKey,MoKpi>
     * Map<KpiId,ResMoAbnormalInfo>
     * Map<LinkPort,ResBase>
     * </>
     *
     * @param mainMap    构造对象
     * @param resBaseKey 终端序列号 设备端口
     * @param rootBase   终端或设备对象
     * @param dataMap    采集数据
     * @param flag       终端0 设备 1
     */
    public static void getMainMap(Map<String, ResBase> mainMap, String resBaseKey, ResBase rootBase, Map<String, String> dataMap, Integer flag) {
        mainMap.put(resBaseKey, rootBase);
        List<ResStypeKpi> resStypeKpiList = hdywUtils.resStypeKpiService.selectByResStypeId(rootBase.getResStypeId());
        HashMap<String, Object> map = new HashMap<>(16);
        //封装掉电和离线异常
        if (flag == 0) {
            map.put("resId", rootBase.getResId());
            map.put("moKpiId", QuatzConstants.OFFLINE_KEY);

            ResMoAbnormalInfo rootResMoAbnormalInfo = hdywUtils.resMoAbnormalInfoService.selectByResIdAndkpiId(map);
            if (null != rootResMoAbnormalInfo) {
                rootBase.getTerminalErrInfos().put(QuatzConstants.OFFLINE_KEY, rootResMoAbnormalInfo);
            }
        }
        if (null != resStypeKpiList && resStypeKpiList.size() > 0) {
            for (ResStypeKpi resStypeKpi : resStypeKpiList) {
                MoKpi moKpi = resStypeKpi.getMoKpi();
                //判断当前stypeId有没有匹配到kpi
                if (null != moKpi) {
                    map.clear();
                    map.put("resId", rootBase.getResId());
                    map.put("moKpiId", moKpi.getMoKpiId());
                    ResMoAbnormalInfo resMoAbnormalInfo = hdywUtils.resMoAbnormalInfoService.selectByResIdAndkpiId(map);
                    if (null != resMoAbnormalInfo) {
                        rootBase.getTerminalErrInfos().put(moKpi.getMoKpiId(), resMoAbnormalInfo);
                    }
                    if (null != dataMap && !dataMap.isEmpty()) {
                        if (flag == 0) {
                            //终端采集的 kpi 数据
                            String dataValue = dataMap.get(QuatzConstants.TERMINAL_PORT + "_" + moKpi.getMoKpiKey());
                            moKpi.setDataValue(dataValue);
                        } else {
                            //设备采集的kpi数据
                            String dataValue = dataMap.get(resBaseKey + "_" + moKpi.getMoKpiKey());
                            moKpi.setDataValue(dataValue);
                        }
                    }
                    rootBase.getKpiKeyMap().put(moKpi.getMoKpiKey(), moKpi);
                    rootBase.getKpiIdMap().put(moKpi.getMoKpiId(), moKpi);
                }
            }
        }
        List<ResTerminal> resBaseEquipments = hdywUtils.resTerminalService.selectByResIdTerminal(rootBase.getResId());
        if (null != resBaseEquipments && resBaseEquipments.size() > 0) {
            //递归调用
            for (ResTerminal resTerminal : resBaseEquipments) {
                rootBase.getTerminalObjct().put(String.valueOf(resTerminal.getLinkPort()), resTerminal.getResBase());
                getMainMap(rootBase.getTerminalObjct(), String.valueOf(resTerminal.getLinkPort()), resTerminal.getResBase(), dataMap, 1);
            }
        }
    }

}
