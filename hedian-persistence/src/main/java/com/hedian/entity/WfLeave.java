package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author gjyang
 * @since 2018-12-15
 */
@TableName("tbl_wf_leave")
public class WfLeave extends Model<WfLeave> {

    private static final long serialVersionUID = 1L;

    /**
     * 请假单id
     */
	@TableId(value="leave_id", type= IdType.AUTO)
	private Integer leaveId;
    /**
     * 请假人id
     */
	@TableField(value = "leave_user_id",fill = FieldFill.INSERT)
	private Long leaveUserId;
    /**
     * 请假开始时间
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 请假结束时间
     */
	@TableField("end_time")
	private Date endTime;
    /**
     * 实际的开始时间
     */
	@TableField("reality_start_time")
	private Date realityStartTime;
    /**
     * 请假结束时间
     */
	@TableField("reality_end_time")
	private Date realityEndTime;
    /**
     * 请假理由
     */
	private String reason;
    /**
     * 申请时间
     */
	@TableField("apply_time")
	private Date applyTime;


	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Long getLeaveUserId() {
		return leaveUserId;
	}

	public void setLeaveUserId(Long leaveUserId) {
		this.leaveUserId = leaveUserId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getRealityStartTime() {
		return realityStartTime;
	}

	public void setRealityStartTime(Date realityStartTime) {
		this.realityStartTime = realityStartTime;
	}

	public Date getRealityEndTime() {
		return realityEndTime;
	}

	public void setRealityEndTime(Date realityEndTime) {
		this.realityEndTime = realityEndTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.leaveId;
	}

	@Override
	public String toString() {
		return "WfLeave{" +
			", leaveId=" + leaveId +
			", leaveUserId=" + leaveUserId +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", realityStartTime=" + realityStartTime +
			", realityEndTime=" + realityEndTime +
			", reason=" + reason +
			", applyTime=" + applyTime +
			"}";
	}
}
