package com.population.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(path = "/")
public class PopulationController {

    @CrossOrigin(origins = "*")
    @GetMapping("/hello")
    public String helloWorld(@RequestParam String teststring){
        return "This is the test string: " + teststring;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/populationestimate")
    public String getPopulationEstimates(){
        final String requestUrl = "https://api.census.gov/data/2019/pep/population?get=DATE_CODE,DATE_DESC,DENSITY,POP,NAME,STATE&for=region:*&key=aa32a07a49de32f2d38267f826a084a1c3030221";
        RestTemplate rt = new RestTemplate();
        Object result = rt.getForObject(requestUrl, Object.class);
        return null;
    }
}
