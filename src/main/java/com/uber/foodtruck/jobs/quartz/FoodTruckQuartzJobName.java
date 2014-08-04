/*
 * Copyright 2014 Mobile Iron, Inc.
 * All rights reserved.
 */

package com.uber.foodtruck.jobs.quartz;


/**
 * Quartz Job Names.
 */
public enum FoodTruckQuartzJobName implements QuartzJobName {
    FoodTruckCacheUpdateJob;

    /**
     * Can this job be run concurrently? Used in job verification and logging.
     * 
     * @return True if the job can be run concurrently.
     */
    @Override
    public boolean isJobConcurrent() {
        return false;
    }
}
