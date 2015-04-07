package com.hong.spring.modules.scheduler;

import java.util.List;

import org.quartz.SchedulerMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quartz")
public class SchedulerRestController {

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(value = "/schedulers", method = RequestMethod.GET)
	public List<SchedulerMetaData> schedulers() {
		return schedulerService.getAllSchedulers();
	}

	@RequestMapping(value = "/schedulers/{schedulerName}", method = RequestMethod.GET)
	public SchedulerMetaData scheduler(@PathVariable String schedulerName) {
		return schedulerService.getScheduler(schedulerName);
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/shutdown", method = RequestMethod.PUT)
	public void scheduler_shutdown(@PathVariable String schedulerName) {
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/standby", method = RequestMethod.PUT)
	public void scheduler_standby(@PathVariable String schedulerName) {
		
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/start", method = RequestMethod.PUT)
	public void scheduler_start(@PathVariable String schedulerName) {
		
		
	}

	@RequestMapping(value = "/schedulers/{schedulerName}/jobs", method = RequestMethod.GET)
	public void jobs(@PathVariable String schedulerName) {
		
		
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
	public void triggers(@PathVariable String schedulerName) {
		
		
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
