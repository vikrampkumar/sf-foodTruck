/*
 * Copyright 2014 Mobile Iron, Inc.
 * All rights reserved.
 */

package com.uber.foodtruck.context;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Spring bean job factory that allows for autowiring fields in a Quartz job.
 *
 * Credit: http://blog.btmatthews.com/?p=40
 */
public class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory {

    private final ApplicationContext applicationContext;

    public AutowiringSpringBeanJobFactory(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        this.applicationContext.getAutowireCapableBeanFactory().autowireBean(job);
        return job;
    }
}
