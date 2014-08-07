package com.sf.foodtruck.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.sf.foodtruck.domain.FoodTruck;
import com.sf.foodtruck.service.FoodTruckCacheService;
import com.sf.foodtruck.service.impl.FoodTruckCacheServiceImpl;
import com.sf.foodtruck.service.impl.RestServiceImpl;
import com.sf.foodtruck.utils.TestUtils;

import static org.hamcrest.Matchers.*;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = { FoodTruckCacheServiceImpl.class, RestServiceImpl.class })
public class FoodTruckServiceImplTest extends AbstractTestNGSpringContextTests {

    // TODO remove the below hard code. Move it to resources.
    private static final String JSON_FILE_NAME = "sf-readablefoodtruck.json";

    @Autowired
    private FoodTruckCacheService foodTruckCacheService;

    @Test
    public void getListOfFoodTrucksTest() throws IOException {
        this.foodTruckCacheService.initializeCache(TestUtils.getResourceAsString(JSON_FILE_NAME));
        
        final Collection<FoodTruck> foodTrucks = this.foodTruckCacheService.getListOfFoodTrucksNearLocation(37.7901490874965,
                        -122.398658184594);
        assertThat(foodTrucks, notNullValue());
        assertThat(foodTrucks.size(), equalTo(8));
    }

    @Test
    public void getFoodTruckListFromUrl() {
        this.foodTruckCacheService.updateCacheFromSFDataUrl();
    }

}
