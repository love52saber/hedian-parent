package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author hedian123
 * @since 2018-08-23
 */
@TableName("sys_file")
public class SysFile extends Model<SysFile> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 文件类型
     */
    private Integer type;
    /**
     * URL地址
     */
    private String url;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;

    public SysFile() {
    }

    public SysFile(Integer type, String url) {
        this.type = type;
        this.url = url;
    }

    public SysFile(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysFile{" +
        "id=" + id +
        ", type=" + type +
        ", url=" + url +
        ", gmtCreate=" + gmtCreate +
        "}";
    }
}
