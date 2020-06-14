package com.population.api.apiutilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DataParser {
    private DataParser(){}

    public static String extractStringWithRegex(String stringWithValue, String regexToMatch){
        Pattern p = Pattern.compile(regexToMatch);
        Matcher matcher = p.matcher(stringWithValue);
        matcher.find();
        return matcher.group();
    }
}
