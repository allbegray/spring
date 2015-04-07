package com.hong.spring.modules.scheduler;

import java.util.Collection;

import org.quartz.Scheduler;
import org.quartz.impl.SchedulerRepository;
import org.springframework.stereotype.Component;

@Component
public class SchedulerService {

	public Collection<Scheduler> getAllSchedulers() {
		return SchedulerRepository.getInstance().lookupAll();
	}

	public void getScheduler(String schedulerName) {
		// TODO Auto-generated method stub
		
	}

}
