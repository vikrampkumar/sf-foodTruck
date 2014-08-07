package com.sf.foodtruck;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import com.sf.foodtruck.FoodTruckCache;
import com.sf.foodtruck.utils.FileUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FoodTruckCacheTest {

    private static final String JSON_FILE_PATH = "/Users/vkumar/Desktop/uber/sf-readablefoodtruck.json";

    @Test
    public void setFoodTruckCacheTest() throws FileNotFoundException {
        FoodTruckCache.setCacheFromJson(FileUtils.getJsonFromFile(JSON_FILE_PATH));
        
        assertThat(FoodTruckCache.foodTruckCacheMap.size(), equalTo(282));
    }
}
