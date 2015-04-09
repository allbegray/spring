package com.hong.spring.common.scheduler.model;

import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronExpression;

public class CronTriggerInfo extends AbstractTriggerInfo {

	private CronExpression cronEx;
	private Date startTime;
	private Date endTime;
	private Date nextFireTime;
	private Date previousFireTime;
	private transient TimeZone timeZone;

	public CronExpression getCronEx() {
		return cronEx;
	}

	public void setCronEx(CronExpression cronEx) {
		this.cronEx = cronEx;
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

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

}
