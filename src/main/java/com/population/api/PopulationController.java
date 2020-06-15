package com.population.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.population.api.apiutilities.ApiFacade;
import com.population.api.apiutilities.DataParser;
import com.population.api.configuration.ApplicationConfiguration;
import com.population.api.model.PopulationEstimate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;

@RestController
@RequestMapping(path = "/")
public class PopulationController {

    // Used to test to make sure that tomcat/java are functioning correctly.
    @CrossOrigin(origins = "*")
    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "I am healthy!";
    }

    // Return the data to the client from the Census API
    @CrossOrigin(origins="*")
    @GetMapping("/populationestimate")
    public ArrayList<PopulationEstimate> getPopulationEstimates(){
        return parsePopulationApiResponse(ApiFacade.sendGETRequest(ApplicationConfiguration.CensusUri()));
    }

    // Organize the response from the Census api into what is required by the client.
    private ArrayList<PopulationEstimate> parsePopulationApiResponse(Object response){
        ArrayList<PopulationEstimate> populationsByRegion = new ArrayList<>();
        ArrayList<ArrayList<String>> populationsResponse = ((ArrayList<ArrayList<String>>)response);
        populationsResponse.remove(0); // first item is unneeded metadata.
        populationsResponse.forEach(regionPopulation -> populationsByRegion.add(new PopulationEstimate(DataParser.extractStringWithRegex(regionPopulation.get(1), ApplicationConfiguration.DateRegex()),
                                                                                                        Integer.parseInt(regionPopulation.get(3)),
                                                                                                        regionPopulation.get(4))));
        Collections.sort(populationsByRegion);
        return populationsByRegion;
    }

}
