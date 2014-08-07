package com.sf.foodtruck.Constants;

public final class Endpoints {

	public Endpoints() {
		
	}
	
	public static final class Qparam {
		
		public Qparam() {
			
		}
		
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
	}
	
	public static final class APV1 {
		
		public APV1() {
			
		}
		
		public static final class App {
		
			public App() {
				
			}
			
			public static final String ROOT = "/";
			public static final String LIST_OF_FOOD_TRUCKS = "listOfFoodTrucks";
			public static final String INITIALIZE_CACHE = "initializeCache";
		}
	}
}
