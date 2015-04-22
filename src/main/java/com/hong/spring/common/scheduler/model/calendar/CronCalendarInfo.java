package com.hong.spring.common.scheduler.model.calendar;

import org.quartz.CronExpression;
import org.quartz.impl.calendar.CronCalendar;

public class CronCalendarInfo extends AbstractCalendarInfo {

	private CronExpression cronExpression;

	public CronCalendarInfo() {
	}

	public CronCalendarInfo(CronCalendar calendar) {
		super(calendar);
		this.cronExpression = calendar.getCronExpression();
	}

	public CronExpression getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(CronExpression cronExpression) {
		this.cronExpression = cronExpression;
	}

}
