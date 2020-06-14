package com.population.api.apiutilities;

import org.springframework.web.client.RestTemplate;

public final class ApiFacade {
    private ApiFacade(){}

    public static Object sendGETRequest(String uriToCall){
        RestTemplate rt = new RestTemplate();
        return rt.getForObject(uriToCall, Object.class);
    }
}
