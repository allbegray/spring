package com.hong.spring.common.scheduler.model;

import java.util.Set;

import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.spi.OperableTrigger;
import org.quartz.TimeOfDay;

public class DailyTimeIntervalTriggerInfo extends AbstractTriggerInfo {

	private int repeatCount;
	private int repeatInterval;
	private IntervalUnit repeatIntervalUnit;
	private Set<Integer> daysOfWeek;
	private TimeOfDay startTimeOfDay;
	private TimeOfDay endTimeOfDay;
	private int timesTriggered;

	public DailyTimeIntervalTriggerInfo() {
	}

	public DailyTimeIntervalTriggerInfo(DailyTimeIntervalTrigger trigger) {
		super((OperableTrigger) trigger);
		this.repeatCount = trigger.getRepeatCount();
		this.repeatInterval = trigger.getRepeatInterval();
		this.repeatIntervalUnit = trigger.getRepeatIntervalUnit();
		this.daysOfWeek = trigger.getDaysOfWeek();
		this.startTimeOfDay = trigger.getStartTimeOfDay();
		this.endTimeOfDay = trigger.getEndTimeOfDay();
		this.timesTriggered = trigger.getTimesTriggered();
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
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

	public Set<Integer> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(Set<Integer> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public TimeOfDay getStartTimeOfDay() {
		return startTimeOfDay;
	}

	public void setStartTimeOfDay(TimeOfDay startTimeOfDay) {
		this.startTimeOfDay = startTimeOfDay;
	}

	public TimeOfDay getEndTimeOfDay() {
		return endTimeOfDay;
	}

	public void setEndTimeOfDay(TimeOfDay endTimeOfDay) {
		this.endTimeOfDay = endTimeOfDay;
	}

	public int getTimesTriggered() {
		return timesTriggered;
	}

	public void setTimesTriggered(int timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

}
