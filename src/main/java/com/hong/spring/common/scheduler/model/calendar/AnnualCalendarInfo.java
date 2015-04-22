package com.hong.spring.common.scheduler.model.calendar;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.quartz.impl.calendar.AnnualCalendar;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.DateyyyyMMddHHmmssSerializer;

public class AnnualCalendarInfo extends AbstractCalendarInfo {

	private List<Date> excludeDays;

	public AnnualCalendarInfo() {
	}

	public AnnualCalendarInfo(AnnualCalendar calendar) {
		super(calendar);

		this.excludeDays = calendar.getDaysExcluded().stream().map(cal -> cal.getTime()).collect(Collectors.toList());
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public List<Date> getExcludeDays() {
		return excludeDays;
	}

	public void setExcludeDays(List<Date> excludeDays) {
		this.excludeDays = excludeDays;
	}

}
