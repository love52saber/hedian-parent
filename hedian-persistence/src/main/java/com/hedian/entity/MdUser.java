package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 维护域和维护人员关系
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@TableName("tbl_md_user")
public class MdUser extends Model<MdUser> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("md_id")
    private Integer mdId;
    @TableField("user_id")
    private Integer userId;
    private Integer showorder;
    private Integer useflag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMdId() {
        return mdId;
    }

    public void setMdId(Integer mdId) {
        this.mdId = mdId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MdUser{" +
        "id=" + id +
        ", mdId=" + mdId +
        ", userId=" + userId +
        ", showorder=" + showorder +
        ", useflag=" + useflag +
        "}";
    }
}
