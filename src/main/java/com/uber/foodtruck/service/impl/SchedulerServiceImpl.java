package com.uber.foodtruck.service.impl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.foodtruck.jobs.quartz.FoodTruckCacheUpdateJob;
import com.uber.foodtruck.jobs.quartz.FoodTruckQuartzJobName;
import com.uber.foodtruck.jobs.quartz.QuartzJobName;

@Service
public class SchedulerServiceImpl<T extends Enum<T> & QuartzJobName> {

    private static final String TRIGGER_IDENTIFIER = "trigger-%s";

    private static final String DEFAULT_JOB_GROUP = "DEFAULT";

    private static final long INTERVAL_MILLS = 86400;

    @Autowired
    private Scheduler scheduler;

    public SchedulerServiceImpl() {

    }

    @PostConstruct
    private void doPostConstruct() throws SchedulerException {
        registerQuartzJobs();
    }

    private void registerQuartzJobs() throws IllegalStateException,
            SchedulerException {
        createOrUpdateQuartzJob(FoodTruckCacheUpdateJob.class, FoodTruckQuartzJobName.FoodTruckCacheUpdateJob.toString());
    }

    private void scheduleSimpleJob(final Class<? extends Job> jobClazz,
            final String jobName, final String groupName,
            final long intervalInMillis, final JobDataMap jobDataMap,
            final Date triggerStartTime) throws SchedulerException {

        try {
            System.out.println("Attempting to schedule quartz job: class [{}] with name [{}] " + jobClazz.getSimpleName());

            final JobBuilder jobBuilder = JobBuilder.newJob(jobClazz).withIdentity(jobName, groupName);
            if (jobDataMap != null) {
                jobBuilder.usingJobData(jobDataMap);
            }

            /*
             * withMisfireHandlingInstructionNextWithRemainingCount: instructs
             * the Scheduler that upon a misfire situation for a SimpleTrigger
             * do nothing, misfired executions are discarded. The scheduler
             * waits for next scheduled interval and goes back to schedule.
             */
            final JobDetail jobDetail = jobBuilder.build();
            final Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(String.format(TRIGGER_IDENTIFIER, jobName), groupName)
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .repeatForever()
                            .withIntervalInMilliseconds(intervalInMillis)
                            .withMisfireHandlingInstructionNextWithRemainingCount())
                    .startAt(triggerStartTime).build();

            this.scheduler.scheduleJob(jobDetail, trigger);
            System.out.println("Successfully added job name [{}]" + jobName
                    + " in group [{}]" + groupName);
        } catch (final SchedulerException exn) {
            throw new IllegalStateException(exn);
        }
    }

    private boolean jobExists(final String jobName, final String groupName) {
        try {
            final JobKey jobKey = new JobKey(jobName, groupName);
            return this.scheduler.checkExists(jobKey);
        } catch (final SchedulerException exn) {
            throw new IllegalStateException(exn);
        }
    }

    private void createOrUpdateQuartzJob(final Class<? extends Job> clazz, final String jobName) throws SchedulerException {
        if (!this.jobExists(jobName, DEFAULT_JOB_GROUP)) {
            this.scheduleSimpleJob(clazz, jobName, DEFAULT_JOB_GROUP, INTERVAL_MILLS, null, new Date());
        } else {
            // TODO need to add logic to update job.
        }
    }
}
