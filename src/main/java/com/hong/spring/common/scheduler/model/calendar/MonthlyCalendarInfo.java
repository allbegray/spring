package com.hong.spring.common.scheduler.model.calendar;

import org.quartz.impl.calendar.MonthlyCalendar;

public class MonthlyCalendarInfo extends AbstractCalendarInfo {

	private boolean[] excludeDays;

	public MonthlyCalendarInfo() {
	}

	public MonthlyCalendarInfo(MonthlyCalendar calendar) {
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
