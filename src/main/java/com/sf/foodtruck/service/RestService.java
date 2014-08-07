package com.sf.foodtruck.service;

import org.springframework.web.client.RestTemplate;

public interface RestService {

    /**
     * Get unsecured rest template.
     * 
     * @return rest template.
     */
	RestTemplate getUnsecureRestTemplate();
}
