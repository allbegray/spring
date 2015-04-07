package com.hong.spring.modules.scheduler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.quartz.Scheduler;
import org.quartz.SchedulerMetaData;
import org.quartz.impl.SchedulerRepository;
import org.springframework.stereotype.Component;

import com.hong.spring.common.scheduler.SchedulerTemplate;

@Component
public class SchedulerService {

	public List<SchedulerMetaData> getAllSchedulers() {
		Collection<Scheduler> schedulers = SchedulerRepository.getInstance().lookupAll();
		return schedulers.stream().parallel()
				.map(scheduler -> new SchedulerTemplate(scheduler).getMetaData())
				.collect(Collectors.toList());
	}

	public SchedulerMetaData getScheduler(String schedulerName) {
		Scheduler scheduler = SchedulerRepository.getInstance().lookup(schedulerName);
		return new SchedulerTemplate(scheduler).getMetaData();
	}

}
