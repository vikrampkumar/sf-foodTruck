package com.uber.foodtruck.service;

import java.util.Collection;

import com.uber.foodtruck.domain.FoodTruck;

public interface FoodTruckCacheService {

	/**
	 * Get all the food trucks near the specified latitude and longitude.
	 * 
	 * @param latitude geo co-ordinates latitude.
	 * @param longitude geo co-ordinates longitude.
	 * @return Collection of food trucks.
	 */
	Collection<FoodTruck> getListOfFoodTrucksNearLocation(final double latitude, final double longitude);
	
	/**
	 * Initialize food truck cache from the json file stored at /Users/vkumar/Desktop/uber/sf-readablefoodtruck.json.
	 * 
	 * @param foodTruckJson json with the array of food trucks in sf.
	 */
	void initializeCache(final String foodTruckJson);
	
	void updateCacheFromSFDataUrl();
}
