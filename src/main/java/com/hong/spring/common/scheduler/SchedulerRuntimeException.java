package com.hong.spring.common.scheduler;

import org.quartz.SchedulerException;

public class SchedulerRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SchedulerRuntimeException(String string, SchedulerException e) {
		super(string, e);
	}

	public SchedulerRuntimeException(SchedulerException e) {
		super(e);
	}

}
