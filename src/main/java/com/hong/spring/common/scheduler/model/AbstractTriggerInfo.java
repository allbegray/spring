package com.hong.spring.common.scheduler.model;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.quartz.spi.OperableTrigger;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hong.spring.common.serializer.DateyyyyMMddHHmmssSerializer;

public abstract class AbstractTriggerInfo {

	private String name;
	private String group;
	private String jobName;
	private String jobGroup;
	private String description;
	private JobDataMap jobDataMap;
	private String calendarName;
	private String fireInstanceId;
	private int misfireInstruction;
	private int priority;

	private Date startTime;
	private Date endTime;
	private Date nextFireTime;
	private Date previousFireTime;

	public AbstractTriggerInfo() {
	}

	public AbstractTriggerInfo(OperableTrigger trigger) {
		TriggerKey triggerKey = trigger.getKey();
		this.name = triggerKey.getName();
		this.group = triggerKey.getGroup();

		JobKey jobKey = trigger.getJobKey();
		this.jobName = jobKey.getName();
		this.jobGroup = jobKey.getGroup();

		this.description = trigger.getDescription();
		this.jobDataMap = trigger.getJobDataMap();
		this.calendarName = trigger.getCalendarName();

		this.fireInstanceId = trigger.getFireInstanceId();
		this.misfireInstruction = trigger.getMisfireInstruction();
		this.priority = trigger.getPriority();

		this.startTime = trigger.getStartTime();
		this.endTime = trigger.getEndTime();
		this.nextFireTime = trigger.getNextFireTime();
		this.previousFireTime = trigger.getPreviousFireTime();
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public JobDataMap getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public String getFireInstanceId() {
		return fireInstanceId;
	}

	public void setFireInstanceId(String fireInstanceId) {
		this.fireInstanceId = fireInstanceId;
	}

	public int getMisfireInstruction() {
		return misfireInstruction;
	}

	public void setMisfireInstruction(int misfireInstruction) {
		this.misfireInstruction = misfireInstruction;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	@JsonSerialize(using = DateyyyyMMddHHmmssSerializer.class)
	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

}
