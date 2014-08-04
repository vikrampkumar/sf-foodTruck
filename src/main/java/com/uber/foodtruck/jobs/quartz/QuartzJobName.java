/*
 * Copyright 2014 Mobile Iron, Inc.
 * All rights reserved.
 */

package com.uber.foodtruck.jobs.quartz;

/**
 * Specifies required information for referencing a Quartz job.
 */
public interface QuartzJobName {

    /**
     * Can this job be run concurrently? Used in job verification and logging.
     *
     * @return True if the job can be run concurrently.
     */
    boolean isJobConcurrent();
}
