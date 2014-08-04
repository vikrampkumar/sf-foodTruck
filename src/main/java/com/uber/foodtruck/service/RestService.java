package com.uber.foodtruck.service;

import org.springframework.web.client.RestTemplate;

public interface RestService {

	RestTemplate getUnsecureRestTemplate();
}
