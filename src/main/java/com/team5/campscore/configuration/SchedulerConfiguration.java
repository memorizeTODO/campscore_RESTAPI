package com.team5.campscore.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfiguration implements SchedulingConfigurer {
	    
	    private final int POOL_SIZE = 10;

		@Override
	    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
	        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
	        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
	        threadPoolTaskScheduler.setThreadNamePrefix("campscore-scheduled-task-pool-");
	        threadPoolTaskScheduler.initialize();
	        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
	    }
}
