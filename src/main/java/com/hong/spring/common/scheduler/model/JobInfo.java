package com.hong.spring.common.scheduler.model;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;

public class JobInfo {

	private String name;
	private String group;
	private String description;
	private Class<? extends Job> jobClass;
	private JobDataMap jobDataMap;
	private boolean durability;
	private boolean shouldRecover;
	private List<? extends AbstractTriggerInfo> triggerInfos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class<? extends Job> getJobClass() {
		return jobClass;
	}

	public void setJobClass(Class<? extends Job> jobClass) {
		this.jobClass = jobClass;
	}

	public JobDataMap getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public boolean isDurability() {
		return durability;
	}

	public void setDurability(boolean durability) {
		this.durability = durability;
	}

	public boolean isShouldRecover() {
		return shouldRecover;
	}

	public void setShouldRecover(boolean shouldRecover) {
		this.shouldRecover = shouldRecover;
	}

	public List<? extends AbstractTriggerInfo> getTriggerInfos() {
		return triggerInfos;
	}

	public void setTriggerInfos(List<? extends AbstractTriggerInfo> triggerInfos) {
		this.triggerInfos = triggerInfos;
	}

}
