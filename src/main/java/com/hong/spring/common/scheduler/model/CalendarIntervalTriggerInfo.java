package com.hong.spring.common.scheduler.model;

import java.util.Date;
import java.util.TimeZone;

import org.quartz.DateBuilder.IntervalUnit;

public class CalendarIntervalTriggerInfo extends AbstractTriggerInfo {

	private Date startTime;
	private Date endTime;
	private Date nextFireTime;
	private Date previousFireTime;
	private int repeatInterval;
	private IntervalUnit repeatIntervalUnit;
	private TimeZone timeZone;
	private boolean preserveHourOfDayAcrossDaylightSavings;
	private boolean skipDayIfHourDoesNotExist;
	private int timesTriggered;
	private boolean complete;

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

	public int getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public IntervalUnit getRepeatIntervalUnit() {
		return repeatIntervalUnit;
	}

	public void setRepeatIntervalUnit(IntervalUnit repeatIntervalUnit) {
		this.repeatIntervalUnit = repeatIntervalUnit;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isPreserveHourOfDayAcrossDaylightSavings() {
		return preserveHourOfDayAcrossDaylightSavings;
	}

	public void setPreserveHourOfDayAcrossDaylightSavings(boolean preserveHourOfDayAcrossDaylightSavings) {
		this.preserveHourOfDayAcrossDaylightSavings = preserveHourOfDayAcrossDaylightSavings;
	}

	public boolean isSkipDayIfHourDoesNotExist() {
		return skipDayIfHourDoesNotExist;
	}

	public void setSkipDayIfHourDoesNotExist(boolean skipDayIfHourDoesNotExist) {
		this.skipDayIfHourDoesNotExist = skipDayIfHourDoesNotExist;
	}

	public int getTimesTriggered() {
		return timesTriggered;
	}

	public void setTimesTriggered(int timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

}
