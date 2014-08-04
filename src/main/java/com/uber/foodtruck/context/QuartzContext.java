/*
 * Copyright 2014 Mobile Iron, Inc.
 * All rights reserved.
 */

package com.uber.foodtruck.context;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Quartz job configuration.
 */
@Configuration
public class QuartzContext {

    private static final String SCHEDULER_NAME = "FOOD_TRUCK_SCHEDULER";

    @Autowired
    private ApplicationContext applicationContext;

    public QuartzContext() {

    }

    /**
     * The quartz scheduler factory bean.
     * 
     * @return SchedulerFactoryBean.
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        final SpringBeanJobFactory beanJobFactory = new AutowiringSpringBeanJobFactory(this.applicationContext);

        final SchedulerFactoryBean bean = new SchedulerFactoryBean();
        {
            final ClassPathResource resource = new ClassPathResource("quartz.properties");
            bean.setConfigLocation(resource);
        }
        bean.setJobFactory(beanJobFactory);
        bean.setSchedulerName(SCHEDULER_NAME);
        bean.setStartupDelay(0);
        bean.setWaitForJobsToCompleteOnShutdown(true);

        return bean;
    }

    /**
     * The quartz scheduler.
     * 
     * @return Scheduler.
     */
    @Bean
    public Scheduler scheduler() {
        return schedulerFactoryBean().getObject();
    }
}
