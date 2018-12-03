package com.hedian.utils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.base.QuatzConstants;
import com.hedian.entity.*;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IMdUserService mdUserService;
    @Autowired
    private IMdResService mdResService;
    @Autowired
    private IMdDeptService mdDeptService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private IResSubtypeService resSubtypeService;


    public static HdywUtils hdywUtils;

    public HdywUtils() {

    }

    @PostConstruct
    public void init() {
        hdywUtils = this;
    }


    /**
     * 根据用户查询设备  1.先找改用户下面的管理域
     *
     * @param sysUser
     * @return
     */
    public static Set<Integer> getResidsByUserid(SysUser sysUser) {
        Set<Integer> resIds = new HashSet<>();
        Map<String, Object> map = new HashMap<>(16);
        //根据用户id获取该用户管理域列表
        List<MdUser> mdUsers = hdywUtils.mdUserService.selectList(new EntityWrapper<MdUser>().eq("user_id", sysUser.getUserId()));
        if (!ComUtil.isEmpty(mdUsers)) {
            map.put("mdIds", mdUsers.stream().map(MdUser::getMdId).collect(Collectors.toList()));
            resIds=HdywUtils.getResIds(map);
        } else {
            //如果该用户没有管理域，获取该用户的部门，并判断该部门是否有管理域
            Long deptId = sysUser.getDeptId();
            //获取该部门及其下的所有子部门的id
            List<SysDept> sysDeptList = hdywUtils.sysDeptService.getChildList(deptId);
            List<Long> deptIds = null;
            if (!ComUtil.isEmpty(sysDeptList)) {
                deptIds = sysDeptList.stream().map(SysDept::getDeptId).collect(Collectors.toList());
            }
            map.put("deptIds", deptIds);
            //获取管理域关联的组织机构
            List<MdDept> mdDepts = hdywUtils.mdDeptService.findByMap(map);
            if (!ComUtil.isEmpty(mdDepts)) {
                map.clear();
                //管理域ID集合
                map.put("mdIds", mdDepts.stream().map(MdDept::getMdId).collect(Collectors.toList()));
                resIds=HdywUtils.getResIds(map);
            }
        }
        return resIds;
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
     * 获得管理域关联终端下的resIds
     */
    public static Set getResIds( Map<String, Object> map){
        Set<Integer> resIds = new HashSet<>();
        //获取管理域关联的终端
        List<MdRes> mdTerminalResList = hdywUtils.mdResService.findByMap(map);
        //根据终端id查关联的设备
        List<ResTerminal> mdResList=hdywUtils.resTerminalService.selectList(new EntityWrapper<ResTerminal>().in("res_id_terminal",mdTerminalResList.stream().map(MdRes::getResId).collect(Collectors.toList())));
        resIds = mdResList.stream().map(ResTerminal::getResId).collect(Collectors.toSet());
        resIds.addAll(mdTerminalResList.stream().map(MdRes::getResId).collect(Collectors.toSet()));
        return  resIds;
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
        //封装掉电和离线异常
        if (flag == 0) {
            ResMoAbnormalInfo rootResMoAbnormalInfo = hdywUtils.resMoAbnormalInfoService.selectOne(
                    new EntityWrapper<ResMoAbnormalInfo>()
                            .where("res_abnormalstatus ={0} and res_id= {1} and mo_kpi_id = {2}",
                                    1, rootBase.getResId(), QuatzConstants.OFFLINE_KEY));
            if (null != rootResMoAbnormalInfo) {
                rootBase.getTerminalErrInfos().put(QuatzConstants.OFFLINE_KEY, rootResMoAbnormalInfo);
            }
        }
        if (null != resStypeKpiList && resStypeKpiList.size() > 0) {
             for (ResStypeKpi resStypeKpi : resStypeKpiList) {
                MoKpi moKpi = resStypeKpi.getMoKpi();
                //判断当前stypeId有没有匹配到kpi
                if (null != moKpi) {
                    ResMoAbnormalInfo resMoAbnormalInfo = hdywUtils.resMoAbnormalInfoService.selectOne(
                            new EntityWrapper<ResMoAbnormalInfo>()
                                    .where("res_abnormalstatus ={0} and res_id= {1} and mo_kpi_id = {2}",
                                            1, rootBase.getResId(), moKpi.getMoKpiId()));
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
                    MoKpi mokpiCopy = new MoKpi();
                    BeanUtils.copyProperties(moKpi, mokpiCopy);
                    rootBase.getKpiIdMap().put(moKpi.getMoKpiId(), mokpiCopy);
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
