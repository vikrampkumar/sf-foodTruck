package com.uber.foodtruck.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.uber.foodtruck.domain.FoodTruck;
import com.uber.foodtruck.service.FoodTruckCacheService;
import com.uber.foodtruck.utils.FileUtils;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, 
	classes = { FoodTruckCacheServiceImpl.class, RestServiceImpl.class })
public class FoodTruckServiceImplTest extends AbstractTestNGSpringContextTests {

    // TODO remove the below hard code. Move it to resources.
    private static final String JSON_FILE_PATH = "/Users/vkumar/Desktop/uber/sf-readablefoodtruck.json";

    @Autowired
    private FoodTruckCacheService foodTruckCacheService;

    @Test
    public void getListOfFoodTrucksTest() throws FileNotFoundException {
	this.foodTruckCacheService.initializeCache(FileUtils
		.getJsonFromFile(JSON_FILE_PATH));
	final Collection<FoodTruck> foodTrucks = this.foodTruckCacheService
		.getListOfFoodTrucksNearLocation(37.7901490874965,
			-122.398658184594);
	assertThat(foodTrucks, notNullValue());
	assertThat(foodTrucks.size(), equalTo(196));
    }

    @Test
    public void getFoodTruckListFromUrl() {
	this.foodTruckCacheService.updateCacheFromSFDataUrl();
    }

}
