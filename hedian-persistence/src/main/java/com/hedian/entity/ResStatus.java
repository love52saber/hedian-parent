package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源状态定义
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_status")
public class ResStatus extends Model<ResStatus> {

    private static final long serialVersionUID = 1L;

    @TableId("res_status")
    private Integer resStatus;
    @TableField("res_status_name")
    private String resStatusName;
    @TableField("res_status_color")
    private String resStatusColor;
    private Integer showorder;
    private Integer delflag;
    private Integer useflag;

    /**
     * 统计值
     */
    @TableField(exist = false)
    private Integer countNum;


    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getResStatus() {
        return resStatus;
    }

    public void setResStatus(Integer resStatus) {
        this.resStatus = resStatus;
    }

    public String getResStatusName() {
        return resStatusName;
    }

    public void setResStatusName(String resStatusName) {
        this.resStatusName = resStatusName;
    }

    public String getResStatusColor() {
        return resStatusColor;
    }

    public void setResStatusColor(String resStatusColor) {
        this.resStatusColor = resStatusColor;
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

    @Override
    protected Serializable pkVal() {
        return this.resStatus;
    }

    @Override
    public String toString() {
        return "ResStatus{" +
        "resStatus=" + resStatus +
        ", resStatusName=" + resStatusName +
        ", resStatusColor=" + resStatusColor +
        ", showorder=" + showorder +
        ", delflag=" + delflag +
        ", useflag=" + useflag +
        "}";
    }
}
