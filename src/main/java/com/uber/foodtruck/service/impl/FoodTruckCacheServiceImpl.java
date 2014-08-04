package com.uber.foodtruck.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.foodtruck.FoodTruckCache;
import com.uber.foodtruck.domain.FoodTruck;
import com.uber.foodtruck.service.FoodTruckCacheService;
import com.uber.foodtruck.service.RestService;

@Service
public class FoodTruckCacheServiceImpl implements FoodTruckCacheService {

    private static String SF_FOOD_TRUCK_URL = "http://data.sfgov.org/resource/rqzj-sfat.json";

    @Autowired
    private RestService restService;

    public FoodTruckCacheServiceImpl() {

    }

    @Override
    public Collection<FoodTruck> getListOfFoodTrucksNearLocation(
            final double latitude, final double longitude) {
        return FoodTruckCache.getFoodTruckNearLocation(latitude, longitude);
    }

    @Override
    public void initializeCache(final String foodTruckJson) {
        FoodTruckCache.setCacheFromJson(foodTruckJson);
    }

    @Override
    public void updateCacheFromSFDataUrl() {
        final FoodTruck[] resp = this.restService.getUnsecureRestTemplate().getForObject(SF_FOOD_TRUCK_URL, FoodTruck[].class);
        final Collection<FoodTruck> foodTruckCollection = new HashSet<>(Arrays.asList(resp));
        FoodTruckCache.setCache(foodTruckCollection);
    }
}
