package com.hedian.base;

/**
 * @author why
 */
public class QuatzConstants {

    /**
     * 终端type类型 1001
     */
    public static final int ZD_MAIN_TYPE = 1001;
    /**
     * 离线故障normal_id 1000
     */
    public static final Integer LX_MO_ABNORMAL_ID = 1000;

    /**
     * 未知故障normal_id 1000
     */
    public static final Integer WZ_MO_ABNORMAL_ID = 1010;

    /**
     * 掉电故障normal_id 1000
     */
    public static final Integer PD_MO_ABNORMAL_ID = 1011;
    /**
     * 系统配置时间间隔类型 2000
     */
    public static final int SYS_TIME_CONFIG = 20000;

    /**
     * 定时任务  多久跑一次
     */
    public final static long ONE_MINUTE = 30 * 1000;

    /**
     * 采集终端端口 11
     */
    public final static String TERMINAL_PORT = "11";


    /**
     * 采集终端端口 掉电状态
     */
    public final static Integer TERMINAL_POWER_DOWN = 12;


    /**
     * 终端显示的状态  正常
     */
    public final static Integer NORMAL = 1;
    /**
     * 终端显示的状态  异常
     */
    public final static Integer FAULT = 2;
    /**
     * 终端显示的状态  未知
     */
    public final static Integer UNKNOWN = 3;
    /**
     * 终端显示的状态  离线
     */
    public final static Integer OFFLINE = 4;

    /**
     * 离线故障key 10000
     */
    public final static Integer OFFLINE_KEY = 10000;

    /**
     * rootMapCache key rootPriority  记录终端机设备下面 异常等级最大的标识
     */
    public final static String ROOTPRIORITY = "rootPriority";
    /**
     * rootMapCache key rootColor  记录终端及设备下面 异常等级最大颜色
     */
    public final static String ROOTCOLOR = "rootColor";

    /**
     * rootMapCache key rootBase  记录终端对象
     */
    public final static String ROOTBASE = "rootBase";

    /**
     * mapCache key moThPriority  记录设备 异常等级最大的标识
     */
    public final static String MOTHPRIORITY = "moThPriority";

    /**
     * mapCache key moThreshold  记录设备对象，异常规则最大的一个
     */
    public final static String MOTHRESHOLD = "moThreshold";

    public final static String RES_COLOR_NORMAL = "#04bbb7";

}
