package com.hedian.fault;


import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.hedian.entity.*;
import org.apache.tools.ant.util.DateUtils;

import java.util.Date;

/**
 * @author why
 */
public class TranslateTemplateUtil {

    /**
     * 资源别名
     */
    public static final String RES_ALIAS = "$$res_alias";
    /**
     * 资源名称
     */
    public static final String RES_NAME = "$$res_name";
    /**
     * 资源主类型名称
     */
    public static final String RES_MTYPE_NAME = "$$res_mtype_name";
    /**
     * 资源子类型名称
     */
    public static final String RES_STYPE_NAME = "$$res_stype_name";
    /**
     * 资源IP地址
     */
    public static final String RES_IPV4 = "$$res_ipv4";
    /**
     * 故障码
     */
    public static final String MO_ABNORMALCODE = "$$mo_abnormalcode";
    /**
     * 故障名称
     */
    public static final String MO_ABNORMAL_NAME = "$$mo_abnormal_name";
    /**
     * 故障级别名称
     */
    public static final String RES_ABNORMALLEVEL_NAME = "$$res_abnormallevel_name";
    /**
     * 监测指标名称
     */
    public static final String MO_KPI_NAME = "$$mo_kpi_name";
    /**
     * 监测指标单位（中文）
     */
    public static final String UNIT_CH = "$$unit_ch";
    /**
     * 监测指标单位（英文）
     */
    public static final String UNIT_EN = "$$unit_en";
    /**
     * 阈值上限
     */
    public static final String MO_TH_UP = "$$mo_th_up";
    /**
     * 阈值下限
     */
    public static final String MO_TH_DOWN = "$$mo_th_down";
    /**
     * 阈值
     */
    public static final String MO_TH_VALUE = "$$mo_th_value";
    /**
     * 基准值
     */
    public static final String MO_TH_BASE = "$$mo_th_base";
    /**
     * 告警发生时间
     */
    public static final String RES_ABNOMALTIME = "$$res_abnomaltime";
    /**
     * 告警发生值
     */
    public static final String RES_ABNORMALVALUE = "$$res_abnormalvalue";
    /**
     * 告警恢复时间
     */
    public static final String RES_RECOVERYTIME = "$$res_recoverytime";
    /**
     * 告警恢复值
     */
    public static final String RES_REVOVERYVALUE = "$$res_revoveryvalue";

    /**
     * 翻译错误模版信息
     *
     * @param template         模版定义
     * @param dataValue        采集值
     * @param moThreshold      监控阈值配置
     * @param moKpi            监控阈值配置
     * @param resAbnormalLevel 资源异常级别
     * @param moAbnormalDef    监控异常定义
     * @param resBase          资源基础信息表
     * @param resMainType      资源主类型
     * @param resSubtype       资源子类型
     * @return
     */
    public static String translateTemplate(String template, String dataValue,
                                           MoThreshold moThreshold, MoKpi moKpi,
                                           ResAbnormallevel resAbnormalLevel, MoAbnormalDef moAbnormalDef,
                                           ResBase resBase, ResMaintype resMainType, ResSubtype resSubtype) {
        if (StringUtils.isNotEmpty(template)) {
            //获取当前  yyyy-MM-dd HH:mm:ss 时间
            String currentTime = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            /**
             * 资源别名
             */
            if (template.contains(RES_ALIAS)) {
                template = template.replace(RES_ALIAS, resBase.getResAlias());
            }
            /**
             * 资源名称
             */
            if (template.contains(RES_NAME)) {
                template = template.replace(RES_NAME, resBase.getResName());
            }
            /**
             * 资源IPV4地址
             */
            if (template.contains(RES_IPV4)) {
                template = template.replace(RES_IPV4, resBase.getResIpv4());
            }
            /**
             * 资源主类型名称
             */
            if (template.contains(RES_MTYPE_NAME)) {
                template = template.replace(RES_MTYPE_NAME, resMainType.getResMtypeName());
            }
            /**
             * 资源子类型名称
             */
            if (template.contains(RES_STYPE_NAME)) {
                template = template.replace(RES_STYPE_NAME, resSubtype.getResStypeName());
            }
            /**
             * 故障码
             */
            if (template.contains(MO_ABNORMALCODE)) {
                template = template.replace(MO_ABNORMALCODE, String.valueOf(moAbnormalDef.getMoAbnormalcode()));
            }
            /**
             * 故障名称
             */
            if (template.contains(MO_ABNORMAL_NAME)) {
                template = template.replace(MO_ABNORMAL_NAME, moAbnormalDef.getMoAbnormalName());
            }
            /**
             * 故障级别名称
             */
            if (template.contains(RES_ABNORMALLEVEL_NAME)) {
                template = template.replace(RES_ABNORMALLEVEL_NAME, resAbnormalLevel.getResAbnormallevelName());
            }
            /**
             * 监测指标名称
             */
            if (template.contains(MO_KPI_NAME)) {
                template = template.replace(MO_KPI_NAME, moKpi.getMoKpiName());
            }
            /**
             * 监测指标单位（中文）
             */
            if (template.contains(UNIT_CH)) {
                template = template.replace(UNIT_CH, moKpi.getUnitCh());
            }
            /**
             * 监测指标单位（英文）
             */
            if (template.contains(UNIT_EN)) {
                template = template.replace(UNIT_EN, moKpi.getUnitEn());
            }
            /**
             * 阈值上限
             */
            if (template.contains(MO_TH_UP)) {
                template = template.replace(MO_TH_UP, String.valueOf(moThreshold.getMoThUp()));
            }
            /**
             * 阈值下限
             */
            if (template.contains(MO_TH_DOWN)) {
                template = template.replace(MO_TH_DOWN, String.valueOf(moThreshold.getMoThDown()));
            }
            /**
             * 阈值
             */
            if (template.contains(MO_TH_VALUE)) {
                template = template.replace(MO_TH_VALUE, moThreshold.getMoThValue());
            }
            /**
             * 基准值
             */
            if (template.contains(MO_TH_BASE)) {
                template = template.replace(MO_TH_BASE, String.valueOf(moThreshold.getMoThBase()));
            }
            /**
             * 告警发生时间
             */
            if (template.contains(RES_ABNOMALTIME)) {
                template = template.replace(RES_ABNOMALTIME, currentTime);
            }
            /**
             * 告警发生值
             */
            if (template.contains(RES_ABNORMALVALUE)) {
                template = template.replace(RES_ABNORMALVALUE, dataValue);
            }
            /**
             * 告警恢复时间
             */
            if (template.contains(RES_RECOVERYTIME)) {
                template = template.replace(RES_RECOVERYTIME, currentTime);
            }
            /**
             * 告警恢复值
             */
            if (template.contains(RES_REVOVERYVALUE)) {
                template = template.replace(RES_REVOVERYVALUE, dataValue);
            }

        }
        return template;
    }


}
