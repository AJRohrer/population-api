package com.population.api.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.population.api.configuration.ApplicationConfiguration;

public class PopulationEstimate {

    public String populationDate = ""; //mm/dd/yyyy
    public long populationEstimate = 0; //If the population of countries or the world is ever estimated in the future of this app we want to be able to store a value over 2 billion (int).
    public String regionDescription = "";
    public String estimationYear = "";
    public String estimationMonth = "";
    public String estimationDay = "";


    public PopulationEstimate(String date, long estimatedPopulation, String descriptionOfRegion){
        if (isDateStringValid(date))
            populationDate = date;
        else
            throw new IllegalArgumentException("Date is not in a valid format.");

        populationEstimate = estimatedPopulation;
        regionDescription = descriptionOfRegion;

        String[] monthDayYear = date.split("/",3);
        estimationMonth = formatDayOrMonth(monthDayYear[0]);
        estimationDay = formatDayOrMonth(monthDayYear[1]);
        estimationYear = monthDayYear[2];
    }

    private String formatDayOrMonth(String dayOrMonth){
        return Integer.parseInt(dayOrMonth) >= 10 ? dayOrMonth : "0" + dayOrMonth;
    }

    // Validates that a date matches the format mm/dd/yyyy
    // Months can only be 1-12
    // Days can only be 1-31
    // Years can be anything between 1000-9999
    private boolean isDateStringValid(String dateToValidate){
        return dateToValidate.matches(ApplicationConfiguration.DateRegex());
    }
}
