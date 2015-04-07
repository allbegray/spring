package com.hong.spring.common.scheduler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.Calendar;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.spi.JobFactory;

public class SchedulerTemplate {

	private Scheduler scheduler;

	public SchedulerTemplate() {
		try {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException("Failed to create scheduler.", e);
		}
	}

	public SchedulerTemplate(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	// scheduler wrapper method

	public String getSchedulerName() {
		try {
			return scheduler.getSchedulerName();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public String getSchedulerInstanceId() {
		try {
			return scheduler.getSchedulerInstanceId();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public SchedulerContext getContext() {
		try {
			return scheduler.getContext();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void start() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void startDelayed(int seconds) {
		try {
			scheduler.startDelayed(seconds);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean isStarted() {
		try {
			return scheduler.isStarted();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void standby() {
		try {
			scheduler.standby();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean isInStandbyMode() {
		try {
			return scheduler.isInStandbyMode();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void shutdown() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void shutdown(boolean waitForJobsToComplete) {
		try {
			scheduler.shutdown(waitForJobsToComplete);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean isShutdown() {
		try {
			return scheduler.isShutdown();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public SchedulerMetaData getMetaData() {
		try {
			return scheduler.getMetaData();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public List<JobExecutionContext> getCurrentlyExecutingJobs() {
		try {
			return scheduler.getCurrentlyExecutingJobs();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void setJobFactory(JobFactory factory) {
		try {
			scheduler.setJobFactory(factory);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public ListenerManager getListenerManager() {
		try {
			return scheduler.getListenerManager();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Date scheduleJob(JobDetail jobDetail, Trigger trigger) {
		try {
			return scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Date scheduleJob(Trigger trigger) {
		try {
			return scheduler.scheduleJob(trigger);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void scheduleJobs(Map<JobDetail, Set<? extends Trigger>> triggersAndJobs, boolean replace) {
		try {
			scheduler.scheduleJobs(triggersAndJobs, replace);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void scheduleJob(JobDetail jobDetail, Set<? extends Trigger> triggersForJob, boolean replace) {
		try {
			scheduler.scheduleJob(jobDetail, triggersForJob, replace);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean unscheduleJob(TriggerKey triggerKey) {
		try {
			return scheduler.unscheduleJob(triggerKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean unscheduleJobs(List<TriggerKey> triggerKeys) {
		try {
			return scheduler.unscheduleJobs(triggerKeys);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Date rescheduleJob(TriggerKey triggerKey, Trigger newTrigger) {
		try {
			return scheduler.rescheduleJob(triggerKey, newTrigger);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void addJob(JobDetail jobDetail, boolean replace) {
		try {
			scheduler.addJob(jobDetail, replace);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void addJob(JobDetail jobDetail, boolean replace, boolean storeNonDurableWhileAwaitingScheduling) {
		try {
			scheduler.addJob(jobDetail, replace, storeNonDurableWhileAwaitingScheduling);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean deleteJob(JobKey jobKey) {
		try {
			return scheduler.deleteJob(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean deleteJobs(List<JobKey> jobKeys) {
		try {
			return scheduler.deleteJobs(jobKeys);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void triggerJob(JobKey jobKey) {
		try {
			scheduler.triggerJob(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void triggerJob(JobKey jobKey, JobDataMap data) {
		try {
			scheduler.triggerJob(jobKey, data);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void pauseJob(JobKey jobKey) {
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void pauseJobs(GroupMatcher<JobKey> matcher) {
		try {
			scheduler.pauseJobs(matcher);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void pauseTrigger(TriggerKey triggerKey) {
		try {
			scheduler.pauseTrigger(triggerKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void pauseTriggers(GroupMatcher<TriggerKey> matcher) {
		try {
			scheduler.pauseTriggers(matcher);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void resumeJob(JobKey jobKey) {
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void resumeJobs(GroupMatcher<JobKey> matcher) {
		try {
			scheduler.resumeJobs(matcher);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void resumeTrigger(TriggerKey triggerKey) {
		try {
			scheduler.resumeTrigger(triggerKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void resumeTriggers(GroupMatcher<TriggerKey> matcher) {
		try {
			scheduler.resumeTriggers(matcher);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void pauseAll() {
		try {
			scheduler.pauseAll();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void resumeAll() {
		try {
			scheduler.resumeAll();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public List<String> getJobGroupNames() {
		try {
			return scheduler.getJobGroupNames();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Set<JobKey> getJobKeys(GroupMatcher<JobKey> matcher) {
		try {
			return scheduler.getJobKeys(matcher);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public List<? extends Trigger> getTriggersOfJob(JobKey jobKey) {
		try {
			return scheduler.getTriggersOfJob(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public List<String> getTriggerGroupNames() {
		try {
			return scheduler.getTriggerGroupNames();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Set<TriggerKey> getTriggerKeys(GroupMatcher<TriggerKey> matcher) {
		try {
			return scheduler.getTriggerKeys(matcher);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Set<String> getPausedTriggerGroups() {
		try {
			return scheduler.getPausedTriggerGroups();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public JobDetail getJobDetail(JobKey jobKey) {
		try {
			return scheduler.getJobDetail(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Trigger getTrigger(TriggerKey triggerKey) {
		try {
			return scheduler.getTrigger(triggerKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public TriggerState getTriggerState(TriggerKey triggerKey) {
		try {
			return scheduler.getTriggerState(triggerKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void addCalendar(String calName, Calendar calendar, boolean replace, boolean updateTriggers) {
		try {
			scheduler.addCalendar(calName, calendar, replace, updateTriggers);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean deleteCalendar(String calName) {
		try {
			return scheduler.deleteCalendar(calName);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public Calendar getCalendar(String calName) {
		try {
			return scheduler.getCalendar(calName);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public List<String> getCalendarNames() {
		try {
			return scheduler.getCalendarNames();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean interrupt(JobKey jobKey) throws UnableToInterruptJobException {
		try {
			return scheduler.interrupt(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean interrupt(String fireInstanceId) throws UnableToInterruptJobException {
		try {
			return scheduler.interrupt(fireInstanceId);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean checkExists(JobKey jobKey) {
		try {
			return scheduler.checkExists(jobKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public boolean checkExists(TriggerKey triggerKey) {
		try {
			return scheduler.checkExists(triggerKey);
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

	public void clear() {
		try {
			scheduler.clear();
		} catch (SchedulerException e) {
			throw new SchedulerRuntimeException(e);
		}
	}

}
