package com.uber.foodtruck;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import com.uber.foodtruck.utils.FileUtils;

public class FoodTruckCacheTest {

	@Test
	public void setFoodTruckCacheTest() throws FileNotFoundException {		
		FoodTruckCache.setCacheFromJson(FileUtils.getJsonFromFile());
		
		
	}
}
