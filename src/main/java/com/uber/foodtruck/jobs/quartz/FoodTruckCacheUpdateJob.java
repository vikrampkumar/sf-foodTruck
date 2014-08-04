package com.uber.foodtruck.jobs.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.uber.foodtruck.service.FoodTruckCacheService;

public class FoodTruckCacheUpdateJob extends AbstractNonConcurrentQuartzJob {

	@Autowired
	private FoodTruckCacheService foodTruckCacheService;

	public FoodTruckCacheUpdateJob() {
		super();
	}

	@Override
	protected void executeInternal(final JobExecutionContext arg0)
			throws JobExecutionException {
		this.foodTruckCacheService.updateCacheFromSFDataUrl();
	}
}
