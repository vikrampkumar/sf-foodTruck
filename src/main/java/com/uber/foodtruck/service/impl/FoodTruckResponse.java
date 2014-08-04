package com.uber.foodtruck.service.impl;

import java.util.Collection;

import com.uber.foodtruck.domain.FoodTruck;

public class FoodTruckResponse {

	private Collection<FoodTruck> foodTruckCollection;

	public FoodTruckResponse() {

	}

	public Collection<FoodTruck> getFoodTruckCollection() {
		return foodTruckCollection;
	}

	public void setFoodTruckCollection(Collection<FoodTruck> foodTruckCollection) {
		this.foodTruckCollection = foodTruckCollection;
	}

}
