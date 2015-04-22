package com.hong.spring.common.scheduler.model.calendar;

import org.quartz.impl.calendar.DailyCalendar;

public class DailyCalendarInfo extends AbstractCalendarInfo {

	private boolean invertTimeRange;

	public DailyCalendarInfo() {
	}

	public DailyCalendarInfo(DailyCalendar calendar) {
		super(calendar);
		this.invertTimeRange = calendar.getInvertTimeRange();
	}

	public boolean isInvertTimeRange() {
		return invertTimeRange;
	}

	public void setInvertTimeRange(boolean invertTimeRange) {
		this.invertTimeRange = invertTimeRange;
	}

}
