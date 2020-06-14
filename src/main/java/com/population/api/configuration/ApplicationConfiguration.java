package com.population.api.configuration;

public final class ApplicationConfiguration {
    private ApplicationConfiguration(){}

    public static String DateRegex(){
        return "((([1])([0-2]))|(([0]??)([1-9])))[/]((([0]??)([1-9]))|(([1-2][0-9])|(3)([0-1])))[/](([1-9])[0-9]{3})";
    }

    public static String CensusUri(){
        return "https://api.census.gov/data/2019/pep/population?get=DATE_CODE,DATE_DESC,DENSITY,POP,NAME,STATE&for=region:*&key=aa32a07a49de32f2d38267f826a084a1c3030221";
    }
}
