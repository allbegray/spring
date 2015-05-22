package com.hong.spring.common.scheduler;

import org.quartz.Calendar;
import org.quartz.CalendarIntervalTrigger;
import org.quartz.CronTrigger;
import org.quartz.DailyTimeIntervalTrigger;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.calendar.CronCalendar;
import org.quartz.impl.calendar.DailyCalendar;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.impl.calendar.MonthlyCalendar;
import org.quartz.impl.calendar.WeeklyCalendar;

import com.hong.spring.common.scheduler.model.calendar.AbstractCalendarInfo;
import com.hong.spring.common.scheduler.model.calendar.AnnualCalendarInfo;
import com.hong.spring.common.scheduler.model.calendar.CronCalendarInfo;
import com.hong.spring.common.scheduler.model.calendar.DailyCalendarInfo;
import com.hong.spring.common.scheduler.model.calendar.HolidayCalendarInfo;
import com.hong.spring.common.scheduler.model.calendar.MonthlyCalendarInfo;
import com.hong.spring.common.scheduler.model.calendar.WeeklyCalendarInfo;
import com.hong.spring.common.scheduler.model.trigger.AbstractTriggerInfo;
import com.hong.spring.common.scheduler.model.trigger.CalendarIntervalTriggerInfo;
import com.hong.spring.common.scheduler.model.trigger.CronTriggerInfo;
import com.hong.spring.common.scheduler.model.trigger.DailyTimeIntervalTriggerInfo;
import com.hong.spring.common.scheduler.model.trigger.SimpleTriggerInfo;

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

	public static AbstractCalendarInfo toCalendarInfo(Calendar calendar) {
		AbstractCalendarInfo abstractCalendarInfo = null;

		if (calendar instanceof AnnualCalendar) {
			abstractCalendarInfo = new AnnualCalendarInfo((AnnualCalendar) calendar);

		} else if (calendar instanceof CronCalendar) {
			abstractCalendarInfo = new CronCalendarInfo((CronCalendar) calendar);

		} else if (calendar instanceof DailyCalendar) {
			abstractCalendarInfo = new DailyCalendarInfo((DailyCalendar) calendar);

		} else if (calendar instanceof HolidayCalendar) {
			abstractCalendarInfo = new HolidayCalendarInfo((HolidayCalendar) calendar);

		} else if (calendar instanceof MonthlyCalendar) {
			abstractCalendarInfo = new MonthlyCalendarInfo((MonthlyCalendar) calendar);

		} else if (calendar instanceof WeeklyCalendar) {
			abstractCalendarInfo = new WeeklyCalendarInfo((WeeklyCalendar) calendar);

		}
		return abstractCalendarInfo;
	}

}
