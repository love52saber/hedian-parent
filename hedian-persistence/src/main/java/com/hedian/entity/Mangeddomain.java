package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 管理域
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_mangeddomain")
public class Mangeddomain extends Model<Mangeddomain> {

    private static final long serialVersionUID = 1L;

    @TableId("md_id")
    private Integer mdId;
    /**
     * 管理域名称
     */
    @TableField("md_name")
    private String mdName;
    /**
     * 管理域备注
     */
    @TableField("md_desc")
    private String mdDesc;
    /**
     * 显示顺序
     */
    private Integer showorder;
    /**
     * 删除标记：0 不能删除， 1可以删除， 默认 1
     */
    private Integer delflag;
    /**
     * 使用标记： 1 使用， 0 不使用
     */
    private Integer useflag;
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("user_id_mod")
    private Long userIdMod;
    @TableField("gmt_modified")
    private Date gmtModified;


    public Integer getMdId() {
        return mdId;
    }

    public void setMdId(Integer mdId) {
        this.mdId = mdId;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getMdDesc() {
        return mdDesc;
    }

    public void setMdDesc(String mdDesc) {
        this.mdDesc = mdDesc;
    }

    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
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
        return this.mdId;
    }

    @Override
    public String toString() {
        return "Mangeddomain{" +
        "mdId=" + mdId +
        ", mdName=" + mdName +
        ", mdDesc=" + mdDesc +
        ", showorder=" + showorder +
        ", delflag=" + delflag +
        ", useflag=" + useflag +
        ", userIdCreate=" + userIdCreate +
        ", gmtCreate=" + gmtCreate +
        ", userIdMod=" + userIdMod +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
