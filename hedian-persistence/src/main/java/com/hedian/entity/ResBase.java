package com.hedian.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源基础信息表
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_base")
public class ResBase extends Model<ResBase> {

    private static final long serialVersionUID = 1L;

    @TableId("res_id")
    private Integer resId;
    /**
     * 资源名称
     */
    @TableField("res_name")
    private String resName;
    /**
     * 用户便于记忆自定义的名称，默认和资源名称一致，一般情况下展示该名称
     */
    @TableField("res_alias")
    private String resAlias;
    /**
     * 资源序列号，有些硬件设备通过该序列号进行关联，比如与智能终端的关联
     */
    @TableField("res_serialnumber")
    private String resSerialnumber;
    /**
     * 资源编号
     */
    @TableField("res_no")
    private String resNo;
    /**
     * 资源描述
     */
    @TableField("res_desc")
    private String resDesc;
    /**
     * 资源主类型
     */
    @TableField("res_mtype_id")
    private Integer resMtypeId;
    /**
     * 资源子类型
     */
    @TableField("res_stype_id")
    private Integer resStypeId;
    /**
     * 资源IPV4地址
     */
    @TableField("res_ipv4")
    private String resIpv4;
    /**
     * 资源所在的端口，一般和IP地址配合使用这样可以和资源通过TCP/IP协议进行通信
     */
    @TableField("res_port")
    private Integer resPort;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 资源所在地址
     */
    @TableField("res_address")
    private String resAddress;
    /**
     * 资源状态: 1 正常 ， 2 异常，3 未知，4 离线
     */
    @TableField("res_status")
    private Integer resStatus;
    @TableField("res_abnormal_id")
    private Integer resAbnormalId;
    /**
     * 资源异常码
     */
    @TableField("res_abnormalcode")
    private Integer resAbnormalcode;
    /**
     * 资源异常级别
     */
    @TableField("res_abnormallevel_id")
    private Integer resAbnormallevelId;
    /**
     * 告警名称
     */
    @TableField("res_abnormal_name")
    private String resAbnormalName;
    /**
     * 资源异常状态描述
     */
    @TableField("res_abnormaldesc")
    private String resAbnormaldesc;
    /**
     * 异常发生时间
     */
    @TableField("res_abnomaltime")
    private Date resAbnomaltime;
    /**
     * 异常恢复时间
     */
    @TableField("res_recoverytime")
    private Date resRecoverytime;
    /**
     * 资源展示时的告警级别定义，默认和res_abnormallevel_id一致，但是对于智能终端需要按照其本身以及其监测对象的最高故障展示。
     */
    @TableField("res_color")
    private String resColor;
    private Integer useflag;
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("user_id_mod")
    private Long userIdMod;
    @TableField("gmt_modified")
    private Date gmtModified;

    /**
     * 设备主类型实体类
     */
    @TableField(exist = false)
    private ResMaintype resMainType;
    /**
     * 设备子类型实体类
     */
    @TableField(exist = false)
    private ResSubtype resSubtype;
    /**
     * 告警等级实体类
     */
    @TableField(exist = false)
    private ResMoAbnormalInfo resAbnormalLevel;
    /**
     * 设备状态实体类
     */
    @TableField(exist = false)
    private ResStatus resStatusDO;

    /**
     * 采集kpi_id_MoKp数据
     */
    @TableField(exist = false)
    private Map<String,MoKpi> kpiKeyMap = new LinkedHashMap<>();

    /**
     * 终端下的每个kpi_key_MoKp异常数据
     * @return
     */
    @TableField(exist = false)
    private Map<Integer, MoKpi> kpiIdMap = new LinkedHashMap<>();

    /**
     * 终端数据下的对象
     */
    @TableField(exist = false)
    private Map<String,ResBase> terminalObjct = new LinkedHashMap<>();

    /**
     * 终端下的每个kpi异常数据
     * @return
     */
    @TableField(exist = false)
    private Map<Integer, ResMoAbnormalInfo> terminalErrInfos = new LinkedHashMap<>();

    public ResMaintype getResMainType() {
        return resMainType;
    }

    public void setResMainType(ResMaintype resMainType) {
        this.resMainType = resMainType;
    }

    public ResSubtype getResSubtype() {
        return resSubtype;
    }

    public void setResSubtype(ResSubtype resSubtype) {
        this.resSubtype = resSubtype;
    }

    public ResMoAbnormalInfo getResAbnormalLevel() {
        return resAbnormalLevel;
    }

    public void setResAbnormalLevel(ResMoAbnormalInfo resAbnormalLevel) {
        this.resAbnormalLevel = resAbnormalLevel;
    }

    public ResStatus getResStatusDO() {
        return resStatusDO;
    }

    public void setResStatusDO(ResStatus resStatusDO) {
        this.resStatusDO = resStatusDO;
    }

    public Map<String, MoKpi> getKpiKeyMap() {
        return kpiKeyMap;
    }

    public void setKpiKeyMap(Map<String, MoKpi> kpiKeyMap) {
        this.kpiKeyMap = kpiKeyMap;
    }

    public Map<Integer, MoKpi> getKpiIdMap() {
        return kpiIdMap;
    }

    public void setKpiIdMap(Map<Integer, MoKpi> kpiIdMap) {
        this.kpiIdMap = kpiIdMap;
    }

    public Map<String, ResBase> getTerminalObjct() {
        return terminalObjct;
    }

    public void setTerminalObjct(Map<String, ResBase> terminalObjct) {
        this.terminalObjct = terminalObjct;
    }

    public Map<Integer, ResMoAbnormalInfo> getTerminalErrInfos() {
        return terminalErrInfos;
    }

    public void setTerminalErrInfos(Map<Integer, ResMoAbnormalInfo> terminalErrInfos) {
        this.terminalErrInfos = terminalErrInfos;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAlias() {
        return resAlias;
    }

    public void setResAlias(String resAlias) {
        this.resAlias = resAlias;
    }

    public String getResSerialnumber() {
        return resSerialnumber;
    }

    public void setResSerialnumber(String resSerialnumber) {
        this.resSerialnumber = resSerialnumber;
    }

    public String getResNo() {
        return resNo;
    }

    public void setResNo(String resNo) {
        this.resNo = resNo;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public Integer getResMtypeId() {
        return resMtypeId;
    }

    public void setResMtypeId(Integer resMtypeId) {
        this.resMtypeId = resMtypeId;
    }

    public Integer getResStypeId() {
        return resStypeId;
    }

    public void setResStypeId(Integer resStypeId) {
        this.resStypeId = resStypeId;
    }

    public String getResIpv4() {
        return resIpv4;
    }

    public void setResIpv4(String resIpv4) {
        this.resIpv4 = resIpv4;
    }

    public Integer getResPort() {
        return resPort;
    }

    public void setResPort(Integer resPort) {
        this.resPort = resPort;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getResAddress() {
        return resAddress;
    }

    public void setResAddress(String resAddress) {
        this.resAddress = resAddress;
    }

    public Integer getResStatus() {
        return resStatus;
    }

    public void setResStatus(Integer resStatus) {
        this.resStatus = resStatus;
    }

    public Integer getResAbnormalId() {
        return resAbnormalId;
    }

    public void setResAbnormalId(Integer resAbnormalId) {
        this.resAbnormalId = resAbnormalId;
    }

    public Integer getResAbnormalcode() {
        return resAbnormalcode;
    }

    public void setResAbnormalcode(Integer resAbnormalcode) {
        this.resAbnormalcode = resAbnormalcode;
    }

    public Integer getResAbnormallevelId() {
        return resAbnormallevelId;
    }

    public void setResAbnormallevelId(Integer resAbnormallevelId) {
        this.resAbnormallevelId = resAbnormallevelId;
    }

    public String getResAbnormalName() {
        return resAbnormalName;
    }

    public void setResAbnormalName(String resAbnormalName) {
        this.resAbnormalName = resAbnormalName;
    }

    public String getResAbnormaldesc() {
        return resAbnormaldesc;
    }

    public void setResAbnormaldesc(String resAbnormaldesc) {
        this.resAbnormaldesc = resAbnormaldesc;
    }

    public Date getResAbnomaltime() {
        return resAbnomaltime;
    }

    public void setResAbnomaltime(Date resAbnomaltime) {
        this.resAbnomaltime = resAbnomaltime;
    }

    public Date getResRecoverytime() {
        return resRecoverytime;
    }

    public void setResRecoverytime(Date resRecoverytime) {
        this.resRecoverytime = resRecoverytime;
    }

    public String getResColor() {
        return resColor;
    }

    public void setResColor(String resColor) {
        this.resColor = resColor;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    protected Serializable pkVal() {
        return this.resId;
    }

    @Override
    public String toString() {
        return "ResBase{" +
        "resId=" + resId +
        ", resName=" + resName +
        ", resAlias=" + resAlias +
        ", resSerialnumber=" + resSerialnumber +
        ", resNo=" + resNo +
        ", resDesc=" + resDesc +
        ", resMtypeId=" + resMtypeId +
        ", resStypeId=" + resStypeId +
        ", resIpv4=" + resIpv4 +
        ", resPort=" + resPort +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", resAddress=" + resAddress +
        ", resStatus=" + resStatus +
        ", resAbnormalId=" + resAbnormalId +
        ", resAbnormalcode=" + resAbnormalcode +
        ", resAbnormallevelId=" + resAbnormallevelId +
        ", resAbnormalName=" + resAbnormalName +
        ", resAbnormaldesc=" + resAbnormaldesc +
        ", resAbnomaltime=" + resAbnomaltime +
        ", resRecoverytime=" + resRecoverytime +
        ", resColor=" + resColor +
        ", useflag=" + useflag +
        ", userIdCreate=" + userIdCreate +
        ", gmtCreate=" + gmtCreate +
        ", userIdMod=" + userIdMod +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
