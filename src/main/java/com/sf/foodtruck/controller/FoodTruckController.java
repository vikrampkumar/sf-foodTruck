package com.sf.foodtruck.controller;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sf.foodtruck.Constants.Endpoints;
import com.sf.foodtruck.domain.FoodTruck;
import com.sf.foodtruck.service.FoodTruckCacheService;
import com.sf.foodtruck.utils.TestUtils;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class FoodTruckController {
    
    private static final Logger LOG = LoggerFactory.getLogger(FoodTruckController.class);
    
    private static final String JSON_FILE_NAME = "sf-readablefoodtruck.json";
    
    public final FoodTruckCacheService foodTruckCacheService;
    
    @Autowired
    public FoodTruckController(FoodTruckCacheService inFoodTruckCacheService) {
        this.foodTruckCacheService = inFoodTruckCacheService;        
    }

    /**
     * Rest api to initialize the food truck cache.
     * 
     * @return Success.
     * @throws IOException if food truck json file not found. 
     */
    @RequestMapping(value = Endpoints.APV1.App.INITIALIZE_CACHE)    
    @ResponseBody
    public WebResponse<String> initializeCache() throws IOException {
        this.foodTruckCacheService.initializeCache(TestUtils.getResourceAsString(JSON_FILE_NAME));
        return new WebResponse<String>("SUCCESS");
    }

    /**
     * Rest api to get a list of food trucks at the specified latitude and longitude.
     * 
     * @param latitude latitude.
     * @param longitude longitude.
     * @return Collection of food trucks.
     */
    @RequestMapping(value = Endpoints.APV1.App.LIST_OF_FOOD_TRUCKS)
    @ResponseBody
    WebResponse<Collection<FoodTruck>> getListOfFoodTrucks(
            @RequestParam(Endpoints.Qparam.LATITUDE) final double latitude,
            @RequestParam(Endpoints.Qparam.LONGITUDE) final double longitude) {
        final WebResponse<Collection<FoodTruck>> response = new WebResponse<>(
                this.foodTruckCacheService.getListOfFoodTrucksNearLocation(latitude, longitude));
        return response;
    }
}
