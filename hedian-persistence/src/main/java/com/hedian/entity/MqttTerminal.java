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
 * mqtt终端消息
 * </p>
 *
 * @author hedian123
 * @since 2018-08-23
 */
@TableName("tbl_mqtt_terminal")
public class MqttTerminal extends Model<MqttTerminal> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Date createtime;
    /**
     * 终端ID
     */
    @TableField("res_serialnumber")
    private String resSerialnumber;
    /**
     * 消息时间
     */
    @TableField("msg_time")
    private Date msgTime;
    /**
     * 主题
     */
    private String topic;
    /**
     * 消息体
     */
    private String payload;
    /**
     * 终端状态
     */
    @TableField("terminal_status")
    private Integer terminalStatus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getResSerialnumber() {
        return resSerialnumber;
    }

    public void setResSerialnumber(String resSerialnumber) {
        this.resSerialnumber = resSerialnumber;
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Integer getTerminalStatus() {
        return terminalStatus;
    }

    public void setTerminalStatus(Integer terminalStatus) {
        this.terminalStatus = terminalStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MqttTerminal{" +
        "id=" + id +
        ", createtime=" + createtime +
        ", resSerialnumber=" + resSerialnumber +
        ", msgTime=" + msgTime +
        ", topic=" + topic +
        ", payload=" + payload +
        ", terminalStatus=" + terminalStatus +
        "}";
    }
}
