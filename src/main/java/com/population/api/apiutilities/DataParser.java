package com.population.api.apiutilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DataParser {
    private DataParser(){}

    // Return a date formatted as mm/dd/yyyy out of a string if it contains a date in that format.
    public static String extractStringWithRegex(String stringWithValue, String regexToMatch){
        if (stringWithValue == null || stringWithValue == "") return "";
        Pattern p = Pattern.compile(regexToMatch);
        Matcher matcher = p.matcher(stringWithValue);
        if (!matcher.find()) return "";
        return matcher.group();
    }
}
