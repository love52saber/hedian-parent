package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源异常级别
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_abnormallevel")
public class ResAbnormallevel extends Model<ResAbnormallevel> {

    private static final long serialVersionUID = 1L;

    @TableId("res_abnormallevel_id")
    private Integer resAbnormallevelId;
    @TableField("res_abnormallevel_name")
    private String resAbnormallevelName;
    /**
     * 告警级别对应颜色： 0 绿色 1 蓝色 2 黄色 3 橙色 4 红色 5 灰色
     */
    @TableField("res_abnormallevel_color")
    private String resAbnormallevelColor;
    /**
     * 告警级别优先级，数值越小优先级越高
     */
    @TableField("res_abnormallevel_priority")
    private Integer resAbnormallevelPriority;
    private Integer showorder;
    private Integer useflag;


    public Integer getResAbnormallevelId() {
        return resAbnormallevelId;
    }

    public void setResAbnormallevelId(Integer resAbnormallevelId) {
        this.resAbnormallevelId = resAbnormallevelId;
    }

    public String getResAbnormallevelName() {
        return resAbnormallevelName;
    }

    public void setResAbnormallevelName(String resAbnormallevelName) {
        this.resAbnormallevelName = resAbnormallevelName;
    }

    public String getResAbnormallevelColor() {
        return resAbnormallevelColor;
    }

    public void setResAbnormallevelColor(String resAbnormallevelColor) {
        this.resAbnormallevelColor = resAbnormallevelColor;
    }

    public Integer getResAbnormallevelPriority() {
        return resAbnormallevelPriority;
    }

    public void setResAbnormallevelPriority(Integer resAbnormallevelPriority) {
        this.resAbnormallevelPriority = resAbnormallevelPriority;
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
        return this.resAbnormallevelId;
    }

    @Override
    public String toString() {
        return "ResAbnormallevel{" +
        "resAbnormallevelId=" + resAbnormallevelId +
        ", resAbnormallevelName=" + resAbnormallevelName +
        ", resAbnormallevelColor=" + resAbnormallevelColor +
        ", resAbnormallevelPriority=" + resAbnormallevelPriority +
        ", showorder=" + showorder +
        ", useflag=" + useflag +
        "}";
    }
}
