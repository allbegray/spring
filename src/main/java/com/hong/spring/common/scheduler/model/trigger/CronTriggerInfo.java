package com.hong.spring.common.scheduler.model.trigger;

import java.util.TimeZone;

import org.quartz.CronTrigger;
import org.quartz.spi.OperableTrigger;

public class CronTriggerInfo extends AbstractTriggerInfo {

	private String cronExpression;
	private TimeZone timeZone;

	public CronTriggerInfo() {
	}

	public CronTriggerInfo(CronTrigger trigger) {
		super((OperableTrigger) trigger);
		this.cronExpression = trigger.getCronExpression();
		this.timeZone = trigger.getTimeZone();
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

}
