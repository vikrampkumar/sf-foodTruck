/*
 * Copyright 2013 Mobile Iron, Inc.
 * All rights reserved.
 */

package com.uber.foodtruck.jobs.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Abstract class for all non-concurrent Quartz Jobs.
 */
@DisallowConcurrentExecution
abstract class AbstractNonConcurrentQuartzJob extends QuartzJobBean {

    protected AbstractNonConcurrentQuartzJob() {

    }
}
