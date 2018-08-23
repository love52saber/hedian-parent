package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 用户组包含用户表
 * </p>
 *
 * @author hedian123
 * @since 2018-08-22
 */
@TableName("sys_grp_user")
public class SysGrpUser extends Model<SysGrpUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("grp_id")
    private Integer grpId;
    @TableField("user_id")
    private Long userId;
    private Integer showorder;
    private Integer useflag;

    public SysGrpUser() {
    }

    public SysGrpUser(Integer grpId, Long userId, Integer showorder, Integer useflag) {
        this.grpId = grpId;
        this.userId = userId;
        this.showorder = showorder;
        this.useflag = useflag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
        return "SysGrpUser{" +
        "id=" + id +
        ", grpId=" + grpId +
        ", userId=" + userId +
        ", showorder=" + showorder +
        ", useflag=" + useflag +
        "}";
    }
}
