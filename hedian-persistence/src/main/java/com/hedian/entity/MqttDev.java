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
 * mqtt监控设备消息
 * </p>
 *
 * @author hedian123
 * @since 2018-08-23
 */
@TableName("tbl_mqtt_dev")
public class MqttDev extends Model<MqttDev> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Date createtime;
    /**
     * mqtt终端消息ID
     */
    @TableField("mqtt_terminal_id")
    private Integer mqttTerminalId;
    /**
     * 终端ID
     */
    @TableField("res_serialnumber")
    private String resSerialnumber;
    /**
     * 设备ID
     */
    @TableField("dev_id")
    private Integer devId;
    /**
     * 设备类型
     */
    @TableField("dev_type")
    private String devType;
    /**
     * 数据类型
     */
    @TableField("data_type")
    private String dataType;
    /**
     * 数据值
     */
    @TableField("data_value")
    private String dataValue;
    /**
     * 消息时间
     */
    @TableField("msg_time")
    private Date msgTime;


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

    public Integer getMqttTerminalId() {
        return mqttTerminalId;
    }

    public void setMqttTerminalId(Integer mqttTerminalId) {
        this.mqttTerminalId = mqttTerminalId;
    }

    public String getResSerialnumber() {
        return resSerialnumber;
    }

    public void setResSerialnumber(String resSerialnumber) {
        this.resSerialnumber = resSerialnumber;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MqttDev{" +
        "id=" + id +
        ", createtime=" + createtime +
        ", mqttTerminalId=" + mqttTerminalId +
        ", resSerialnumber=" + resSerialnumber +
        ", devId=" + devId +
        ", devType=" + devType +
        ", dataType=" + dataType +
        ", dataValue=" + dataValue +
        ", msgTime=" + msgTime +
        "}";
    }
}
