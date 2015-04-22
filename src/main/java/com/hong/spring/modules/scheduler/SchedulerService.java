package com.hong.spring.modules.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.SchedulerRepository;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import com.hong.spring.common.scheduler.SchedulerTemplate;
import com.hong.spring.common.scheduler.SchedulerUtils;
import com.hong.spring.common.scheduler.job.HelloJob;
import com.hong.spring.common.scheduler.model.AbstractTriggerInfo;
import com.hong.spring.common.scheduler.model.JobInfo;
import com.hong.spring.common.scheduler.model.ScheduleInfo;

@Component
public class SchedulerService {
	
	@PostConstruct
	public void setup() {
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever()).build();
		SchedulerTemplate schedulerTemplate = new SchedulerTemplate();
		schedulerTemplate.scheduleJob(job, trigger);
	}
	
	public SchedulerTemplate getSchedulerTemplate(String schedulerName) {
		return new SchedulerTemplate(SchedulerRepository.getInstance().lookup(schedulerName));
	}

	public List<ScheduleInfo> getScheduleInfos() {
		Collection<Scheduler> schedulers = SchedulerRepository.getInstance().lookupAll();
		return schedulers.stream().parallel()
				.map(scheduler -> new ScheduleInfo(new SchedulerTemplate(scheduler).getMetaData()))
				.collect(Collectors.toList());
	}

	public ScheduleInfo getScheduleInfo(String schedulerName) {
		SchedulerTemplate schedulerTemplate = getSchedulerTemplate(schedulerName);
		return new ScheduleInfo(schedulerTemplate.getMetaData());
	}
	
	public List<? extends AbstractTriggerInfo> getTriggerInfos(String schedulerName) {
		SchedulerTemplate schedulerTemplate = this.getSchedulerTemplate(schedulerName);
		
		return schedulerTemplate.getTriggerGroupNames().stream()
				.map(group -> schedulerTemplate.getTriggerKeys(GroupMatcher.groupEquals(group)))
				.map(triggerKeys -> triggerKeys.stream()
						.map(triggerKey -> SchedulerUtils.toTriggerInfo(schedulerTemplate.getTrigger(triggerKey)))
						.collect(Collectors.toList())
				)
				.flatMap(triggerInfos -> triggerInfos.stream())
				.collect(Collectors.toList());
	}
	
	public List<JobInfo> getJobInfos(String schedulerName) {
		SchedulerTemplate schedulerTemplate = this.getSchedulerTemplate(schedulerName);
		
		return schedulerTemplate.getJobGroupNames().stream()
			.map(group -> schedulerTemplate.getJobKeys(GroupMatcher.groupEquals(group)))
			.map(jobKeys -> jobKeys.stream()
					.map(jobKey -> new JobInfo(schedulerTemplate.getJobDetail(jobKey), schedulerTemplate.getTriggersOfJob(jobKey)))
					.collect(Collectors.toList())
			)
			.flatMap(jobInfos -> jobInfos.stream())
			.collect(Collectors.toList());
	}

	public JobInfo getJobInfo(String schedulerName, String name, String group) {
		return getJobInfo(schedulerName, JobKey.jobKey(name, group));
	}

	public JobInfo getJobInfo(String schedulerName, JobKey jobKey) {
		SchedulerTemplate schedulerTemplate = this.getSchedulerTemplate(schedulerName);
		JobDetail jobDetail = schedulerTemplate.getJobDetail(jobKey);
		List<? extends Trigger> triggers = schedulerTemplate.getTriggersOfJob(jobKey);
		return new JobInfo(jobDetail, triggers);
	}

}
