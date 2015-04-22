package com.hong.spring.common.scheduler.model.calendar;

import org.quartz.impl.calendar.WeeklyCalendar;

public class WeeklyCalendarInfo extends AbstractCalendarInfo {

	private boolean[] excludeDays;

	public WeeklyCalendarInfo() {
	}

	public WeeklyCalendarInfo(WeeklyCalendar calendar) {
		super(calendar);
		this.excludeDays = calendar.getDaysExcluded();
	}

	public boolean[] getExcludeDays() {
		return excludeDays;
	}

	public void setExcludeDays(boolean[] excludeDays) {
		this.excludeDays = excludeDays;
	}

}
