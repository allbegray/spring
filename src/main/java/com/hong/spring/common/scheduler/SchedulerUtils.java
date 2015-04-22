package com.hong.spring.common.scheduler;

import org.quartz.CalendarIntervalTrigger;
import org.quartz.CronTrigger;
import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import com.hong.spring.common.scheduler.model.AbstractTriggerInfo;
import com.hong.spring.common.scheduler.model.CalendarIntervalTriggerInfo;
import com.hong.spring.common.scheduler.model.CronTriggerInfo;
import com.hong.spring.common.scheduler.model.DailyTimeIntervalTriggerInfo;
import com.hong.spring.common.scheduler.model.SimpleTriggerInfo;

public class SchedulerUtils {

	private SchedulerUtils() {
	}

	public static AbstractTriggerInfo toTriggerInfo(Trigger trigger) {
		AbstractTriggerInfo abstractTriggerInfo = null;

		if (trigger instanceof SimpleTrigger) {
			abstractTriggerInfo = new SimpleTriggerInfo((SimpleTrigger) trigger);

		} else if (trigger instanceof CronTrigger) {
			abstractTriggerInfo = new CronTriggerInfo((CronTrigger) trigger);

		} else if (trigger instanceof DailyTimeIntervalTrigger) {
			abstractTriggerInfo = new DailyTimeIntervalTriggerInfo((DailyTimeIntervalTrigger) trigger);

		} else if (trigger instanceof CalendarIntervalTrigger) {
			abstractTriggerInfo = new CalendarIntervalTriggerInfo((CalendarIntervalTrigger) trigger);

		}
		return abstractTriggerInfo;
	}

}
