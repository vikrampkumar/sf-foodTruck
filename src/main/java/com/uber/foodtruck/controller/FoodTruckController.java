package com.uber.foodtruck.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.uber.foodtruck.Constants.Endpoints;
import com.uber.foodtruck.domain.FoodTruck;
import com.uber.foodtruck.service.FoodTruckCacheService;
import com.uber.foodtruck.utils.FileUtils;

@EnableAutoConfiguration
@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class FoodTruckController {

    private static final String JSON_FILE_PATH = "/Users/vkumar/Desktop/uber/sf-readablefoodtruck.json";

    @Autowired
    private FoodTruckCacheService foodTruckCacheService;

    final Logger LOG = LoggerFactory.getLogger(FoodTruckController.class);

    @RequestMapping(value = Endpoints.APV1.App.LIST_OF_FOOD_TRUCKS, method = RequestMethod.GET)
    @ResponseBody
    WebResponse<Collection<FoodTruck>> getListOfFoodTrucks(
            @RequestParam(Endpoints.Qparam.LATITUDE) final double latitude,
            @RequestParam(Endpoints.Qparam.LONGITUDE) final double longitude) {
        final WebResponse<Collection<FoodTruck>> response = new WebResponse<>(
                this.foodTruckCacheService.getListOfFoodTrucksNearLocation(latitude, longitude));
        return response;
    }

    public void main(final String[] args) throws Exception {
        SpringApplication.run(FoodTruckController.class, args);
        this.foodTruckCacheService.initializeCache(FileUtils.getJsonFromFile(JSON_FILE_PATH));
    }
}
