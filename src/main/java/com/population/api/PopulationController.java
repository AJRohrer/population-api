package com.population.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.population.api.apiutilities.ApiFacade;
import com.population.api.apiutilities.DataParser;
import com.population.api.configuration.ApplicationConfiguration;
import com.population.api.model.PopulationEstimate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/")
public class PopulationController {

    @CrossOrigin(origins = "*")
    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "I am healthy!";
    }

    @CrossOrigin(origins="*")
    @GetMapping("/populationestimate")
    public ArrayList<PopulationEstimate> getPopulationEstimates(){
        return parsePopulationApiResponse(ApiFacade.sendGETRequest(ApplicationConfiguration.CensusUri()));
    }

    private String getJsonString(Object o){
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(o);
        } catch (Exception e){
            return "Invalid data, could not create Json.";
        }
    }

    private ArrayList<PopulationEstimate> parsePopulationApiResponse(Object response){
        ArrayList<PopulationEstimate> populationsByRegion = new ArrayList<>();
        ArrayList<ArrayList<String>> populationsResponse = ((ArrayList<ArrayList<String>>)response);
        populationsResponse.remove(0); // first item is unneeded metadata.
        populationsResponse.forEach(regionPopulation -> populationsByRegion.add(new PopulationEstimate(DataParser.extractStringWithRegex(regionPopulation.get(1), ApplicationConfiguration.DateRegex()),
                                                                                                        Integer.parseInt(regionPopulation.get(3)),
                                                                                                        regionPopulation.get(4))));
        return populationsByRegion;
    }

}
