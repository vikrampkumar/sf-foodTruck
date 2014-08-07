package com.sf.foodtruck.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sf.foodtruck.service.FoodTruckCacheService;

@Service
public class FoodTruckCacheUpdateJob {

    private static final int FIXED_DELAY_IN_MILLS = 1000;
    
    @Autowired
    private FoodTruckCacheService foodTruckCacheService;

    public FoodTruckCacheUpdateJob() {
       
    }

    @Scheduled(fixedDelay = FIXED_DELAY_IN_MILLS)
    protected void executeJob() {
        this.foodTruckCacheService.updateCacheFromSFDataUrl();
    }
}
