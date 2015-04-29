package com.hong.spring.modules.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spring.common.scheduler.model.JobInfo;
import com.hong.spring.common.scheduler.model.ScheduleInfo;
import com.hong.spring.common.scheduler.model.trigger.AbstractTriggerInfo;

@RestController
@RequestMapping("/api/quartz")
public class SchedulerRestController {

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(value = "/schedulers", method = RequestMethod.GET)
	public List<ScheduleInfo> schedulers() {
		return schedulerService.getScheduleInfos();
	}

	@RequestMapping(value = "/schedulers/{schedulerName}", method = RequestMethod.GET)
	public ScheduleInfo scheduler(@PathVariable String schedulerName) {
		return schedulerService.getScheduleInfo(schedulerName);
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/shutdown", method = RequestMethod.PUT)
	public void scheduler_shutdown(
			@PathVariable String schedulerName,
			@RequestParam(value = "waitForJobsToComplete", required = false) boolean waitForJobsToComplete) {
		
		schedulerService.shutdown(schedulerName, waitForJobsToComplete);
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/standby", method = RequestMethod.PUT)
	public void scheduler_standby(@PathVariable String schedulerName) {
		schedulerService.standby(schedulerName);
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/start", method = RequestMethod.PUT)
	public void scheduler_start(@PathVariable String schedulerName) {
		schedulerService.start(schedulerName);
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs", method = RequestMethod.GET)
	public List<JobInfo> jobs(@PathVariable String schedulerName) {
		return schedulerService.getJobInfos(schedulerName);
	}
	
	@RequestMapping(value = "/schedulers/{schedulerName}/executing_jobs", method = RequestMethod.GET)
	public List<JobInfo> executing_jobs(@PathVariable String schedulerName) {
		return schedulerService.getCurrentlyExecutingJobInfos(schedulerName);
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs/{jobGroup}", method = RequestMethod.GET)
	public void jobs_group(
			@PathVariable String schedulerName,
			@PathVariable String jobGroup) {
		
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs/{jobGroup}/{jobName}", method = RequestMethod.GET)
	public void jobs_jobgroup_jobname(
			@PathVariable String schedulerName,
			@PathVariable String jobGroup,
			@PathVariable String jobName) {
		
		
	}
	
	@RequestMapping(value = "/schedulers/{schedulerName}/jobs/{jobGroup}/{jobName}/run", method = RequestMethod.POST)
	public void jobs_jobgroup_jobname_run(
			@PathVariable String schedulerName,
			@PathVariable String jobGroup,
			@PathVariable String jobName) {
		
		schedulerService.executeJobNow(schedulerName, jobName, jobGroup);
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs/{jobGroup}/{jobName}/triggers", method = RequestMethod.GET)
	public void jobs_jobgroup_jobname_triggers(
			@PathVariable String schedulerName,
			@PathVariable String jobGroup,
			@PathVariable String jobName) {
		
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs/{jobGroup}/{jobName}/triggers/{triggerGroup}", method = RequestMethod.GET)
	public void jobs_jobgroup_jobname_triggers_triggergroup(
			@PathVariable String schedulerName,
			@PathVariable String jobGroup,
			@PathVariable String jobName,
			@PathVariable String triggerGroup) {
		
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs/{jobGroup}/{jobName}/triggers/{triggerGroup}/{triggerName}", method = RequestMethod.GET)
	public void jobs_jobgroup_jobname_triggers_triggergroup_triggername(
			@PathVariable String schedulerName,
			@PathVariable String jobGroup,
			@PathVariable String jobName,
			@PathVariable String triggerGroup,
			@PathVariable String triggerName) {
		
		
	}
	
	@RequestMapping(value = "/schedulers/{schedulerName}/triggers", method = RequestMethod.GET)
	public List<? extends AbstractTriggerInfo> triggers(@PathVariable String schedulerName) {
		return schedulerService.getTriggerInfos(schedulerName);
	}
	
	@RequestMapping(value = "/schedulers/{schedulerName}/triggers/{triggerGroup}", method = RequestMethod.GET)
	public void triggers_triggergroup(
			@PathVariable String schedulerName,
			@PathVariable String triggerGroup) {
		
		
	}
	
	@RequestMapping(value = "/schedulers/{schedulerName}/triggers/{triggerGroup}/{triggerName}", method = RequestMethod.GET)
	public void triggers_triggergroup_triggername(
			@PathVariable String schedulerName,
			@PathVariable String triggerGroup,
			@PathVariable String triggerName) {
		
		
	}
	
	@RequestMapping(value = "/schedulers/{schedulerName}/triggers/{triggerGroup}/{triggerName}/job", method = RequestMethod.GET)
	public void triggers_triggergroup_triggername_job(
			@PathVariable String schedulerName,
			@PathVariable String triggerGroup,
			@PathVariable String triggerName) {
		
		
	}

}
