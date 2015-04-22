package com.hong.spring.common.scheduler.model.trigger;

import java.util.TimeZone;

import org.quartz.CalendarIntervalTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.spi.OperableTrigger;

public class CalendarIntervalTriggerInfo extends AbstractTriggerInfo {

	private int repeatInterval;
	private IntervalUnit repeatIntervalUnit;
	private TimeZone timeZone;
	private boolean preserveHourOfDayAcrossDaylightSavings;
	private boolean skipDayIfHourDoesNotExist;
	private int timesTriggered;

	public CalendarIntervalTriggerInfo() {
	}

	public CalendarIntervalTriggerInfo(CalendarIntervalTrigger trigger) {
		super((OperableTrigger) trigger);
		this.repeatInterval = trigger.getRepeatInterval();
		this.repeatIntervalUnit = trigger.getRepeatIntervalUnit();
		this.timeZone = trigger.getTimeZone();
		this.preserveHourOfDayAcrossDaylightSavings = trigger.isPreserveHourOfDayAcrossDaylightSavings();
		this.skipDayIfHourDoesNotExist = trigger.isSkipDayIfHourDoesNotExist();
		this.timesTriggered = trigger.getTimesTriggered();
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

}
