package com.hong.spring.common.scheduler.model;

import org.quartz.SimpleTrigger;
import org.quartz.spi.OperableTrigger;

public class SimpleTriggerInfo extends AbstractTriggerInfo {

	private int repeatCount;
	private long repeatInterval;
	private int timesTriggered;

	public SimpleTriggerInfo() {
	}

	public SimpleTriggerInfo(SimpleTrigger trigger) {
		super((OperableTrigger) trigger);
		this.repeatCount = trigger.getRepeatCount();
		this.repeatInterval = trigger.getRepeatInterval();
		this.timesTriggered = trigger.getTimesTriggered();
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public long getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(long repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public int getTimesTriggered() {
		return timesTriggered;
	}

	public void setTimesTriggered(int timesTriggered) {
		this.timesTriggered = timesTriggered;
	}

}
