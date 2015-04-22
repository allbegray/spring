package com.hong.spring.common.scheduler.model.calendar;

import java.util.Date;
import java.util.Set;

import org.quartz.impl.calendar.HolidayCalendar;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.DateyyyyMMddHHmmssSerializer;

public class HolidayCalendarInfo extends AbstractCalendarInfo {

	private Set<Date> excludedDates;

	public HolidayCalendarInfo() {
	}

	public HolidayCalendarInfo(HolidayCalendar calendar) {
		super(calendar);
		this.excludedDates = calendar.getExcludedDates();
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Set<Date> getExcludedDates() {
		return excludedDates;
	}

	public void setExcludedDates(Set<Date> excludedDates) {
		this.excludedDates = excludedDates;
	}

}
