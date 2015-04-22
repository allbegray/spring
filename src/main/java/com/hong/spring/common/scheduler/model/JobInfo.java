package com.hong.spring.common.scheduler.model;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import com.hong.spring.common.scheduler.SchedulerUtils;

public class JobInfo {

	private String name;
	private String group;
	private String description;
	private Class<? extends Job> jobClass;
	private JobDataMap jobDataMap;
	private boolean durability;
	private boolean shouldRecover;
	private List<AbstractTriggerInfo> triggerInfos;

	public JobInfo() {
	}

	public JobInfo(JobDetail jobDetail) {
		this(jobDetail, null);
	}

	public JobInfo(JobDetail jobDetail, List<? extends Trigger> triggers) {
		JobKey jobKey = jobDetail.getKey();
		this.name = jobKey.getName();
		this.group = jobKey.getGroup();
		this.jobClass = jobDetail.getJobClass();
		this.jobDataMap = jobDetail.getJobDataMap();
		this.durability = jobDetail.isDurable();
		this.shouldRecover = jobDetail.requestsRecovery();

		if (triggers != null) {
			triggerInfos = triggers.stream()
					.map(trigger -> SchedulerUtils.toTriggerInfo(trigger))
					.collect(Collectors.toList());
		}
	}

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

	public List<AbstractTriggerInfo> getTriggerInfos() {
		return triggerInfos;
	}

	public void setTriggerInfos(List<AbstractTriggerInfo> triggerInfos) {
		this.triggerInfos = triggerInfos;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
