package com.hedian.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author hedian123
 * @since 2018-08-23
 */
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    private String name;
    /**
     * 密码
     */
    private String password;
    @TableField("dept_id")
    private Long deptId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;
    /**
     * 创建用户id
     */
    @TableField(value = "user_id_create", fill = FieldFill.INSERT)
    private Long userIdCreate;
    /**
     * 用户名或密码错误时间
     */
    @TableField("lastwrong_time")
    private Date lastwrongTime;
    /**
     * 在laswrong_time和当前时间相差30分钟内输入错误次数
     */
    @TableField("wrong_times")
    private Integer wrongTimes;
    /**
     * 锁定类型：1 自动锁定，2  手动锁定
     */
    private Integer locktype;
    /**
     * 锁定标记，1 锁定，0 不锁定，当前设计输入密码5次错误，锁定30分钟
     */
    private Integer lockflag;
    /**
     * 解锁时间，开始锁定时间+锁定时长=解锁时间，在验证解锁时间到达之后同时需要更新lockflag为解锁状态以及将锁定原因清空
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date unlocktime;
    /**
     * 被锁定原因
     */
    private String lockreason;
    /**
     * 创建时间
     */
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    /**
     * 性别
     */
    private Long sex;
    /**
     * 出身日期，为兼容现有系统，暂时不不用。
     */
    private Date birth;
    @TableField("pic_id")
    private Long picId;
    /**
     * 现居住地，为兼容现有系统，暂时不不用。
     */
    @TableField("live_address")
    private String liveAddress;
    /**
     * 爱好，为兼容现有系统，暂时不不用。
     */
    private String hobby;
    /**
     * 省份，为兼容现有系统，暂时不不用。
     */
    private String province;
    /**
     * 所在城市，为兼容现有系统，暂时不不用。
     */
    private String city;
    /**
     * 所在地区，为兼容现有系统，暂时不不用。
     */
    private String district;
    @TableField("u_order")
    private Integer uOrder;
    /**
     * 密码标记：1 正常 2  需要修改密码
     */
    @TableField("pwd_flag")
    private Integer pwdFlag;
    @TableField(value = "user_id_mod", fill = FieldFill.INSERT_UPDATE)
    private Long userIdMod;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;
    /**
     * 删除标记：0 不能删除， 1可以删除， 默认 1
     */
    @TableField(value = "delflag", fill = FieldFill.INSERT)
    private Integer delflag;

    @TableField(exist = false)
    private List<Long> roleIds;

    @TableField(exist = false)
    private SysDept sysDept;

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getLastwrongTime() {
        return lastwrongTime;
    }

    public void setLastwrongTime(Date lastwrongTime) {
        this.lastwrongTime = lastwrongTime;
    }

    public Integer getWrongTimes() {
        return wrongTimes;
    }

    public void setWrongTimes(Integer wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    public Integer getLocktype() {
        return locktype;
    }

    public void setLocktype(Integer locktype) {
        this.locktype = locktype;
    }

    public Integer getLockflag() {
        return lockflag;
    }

    public void setLockflag(Integer lockflag) {
        this.lockflag = lockflag;
    }

    public Date getUnlocktime() {
        return unlocktime;
    }

    public void setUnlocktime(Date unlocktime) {
        this.unlocktime = unlocktime;
    }

    public String getLockreason() {
        return lockreason;
    }

    public void setLockreason(String lockreason) {
        this.lockreason = lockreason;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getuOrder() {
        return uOrder;
    }

    public void setuOrder(Integer uOrder) {
        this.uOrder = uOrder;
    }

    public Integer getPwdFlag() {
        return pwdFlag;
    }

    public void setPwdFlag(Integer pwdFlag) {
        this.pwdFlag = pwdFlag;
    }

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", deptId=" + deptId +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status=" + status +
                ", userIdCreate=" + userIdCreate +
                ", lastwrongTime=" + lastwrongTime +
                ", wrongTimes=" + wrongTimes +
                ", locktype=" + locktype +
                ", lockflag=" + lockflag +
                ", unlocktime=" + unlocktime +
                ", lockreason='" + lockreason + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", sex=" + sex +
                ", birth=" + birth +
                ", picId=" + picId +
                ", liveAddress='" + liveAddress + '\'' +
                ", hobby='" + hobby + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", uOrder=" + uOrder +
                ", pwdFlag=" + pwdFlag +
                ", userIdMod=" + userIdMod +
                ", useflag=" + useflag +
                ", delflag=" + delflag +
                ", roleIds=" + roleIds +
                '}';
    }
}
