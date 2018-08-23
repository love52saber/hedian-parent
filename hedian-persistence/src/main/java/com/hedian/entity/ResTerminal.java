package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源和智能终端关系表
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_terminal")
public class ResTerminal extends Model<ResTerminal> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 智能终端资源ID
     */
    @TableField("res_id_terminal")
    private Integer resIdTerminal;
    /**
     * 智能终端连接设备资源ID
     */
    @TableField("res_id")
    private Integer resId;
    /**
     * 链接端口
     */
    @TableField("link_port")
    private Integer linkPort;
    private Integer useflag;


    @TableField(exist = false)
    private  ResBase resBase;

    public ResBase getResBase() {
        return resBase;
    }

    public void setResBase(ResBase resBase) {
        this.resBase = resBase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResIdTerminal() {
        return resIdTerminal;
    }

    public void setResIdTerminal(Integer resIdTerminal) {
        this.resIdTerminal = resIdTerminal;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getLinkPort() {
        return linkPort;
    }

    public void setLinkPort(Integer linkPort) {
        this.linkPort = linkPort;
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
        return "ResTerminal{" +
        "id=" + id +
        ", resIdTerminal=" + resIdTerminal +
        ", resId=" + resId +
        ", linkPort=" + linkPort +
        ", useflag=" + useflag +
        "}";
    }
}
