package com.hong.spring.common.scheduler.model.calendar;

import java.util.TimeZone;

import org.quartz.impl.calendar.BaseCalendar;

public abstract class AbstractCalendarInfo {

	private String description;
	private TimeZone timeZone;

	public AbstractCalendarInfo() {
	}

	public AbstractCalendarInfo(BaseCalendar calendar) {
		this.description = calendar.getDescription();
		this.timeZone = calendar.getTimeZone();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

}
