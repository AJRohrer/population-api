package com.population.api.apiutilities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DataParserTest {

    private static final String dateRegex = "((([1])([0-2]))|(([0]??)([1-9])))[/]((([0]??)([1-9]))|(([1-2][0-9])|(3)([0-1])))[/](([1-9])[0-9]{3})";

    @Test
    void testExtractStringWithRegex() {
        assertEquals("4/10/2010",DataParser.extractStringWithRegex("4/10/2010",dateRegex),"Valid date");
        assertEquals("",DataParser.extractStringWithRegex("", dateRegex), "Empty string should return empty");
        assertEquals("",DataParser.extractStringWithRegex(null, dateRegex), "Null value should return empty string");
        assertEquals("4/10/2010",DataParser.extractStringWithRegex("4/10/2010 test some more text",dateRegex), "Method should only return the date string");
        assertEquals("4/10/2010",DataParser.extractStringWithRegex("Other text first 4/10/2010 test some more text",dateRegex), "Method should only return the date string");
        assertEquals("",DataParser.extractStringWithRegex("/10/2010",dateRegex),"Invalid date");
        assertEquals("",DataParser.extractStringWithRegex("4/1220/2010",dateRegex),"Invalid date");
        assertEquals("",DataParser.extractStringWithRegex("4/10/20a22",dateRegex),"Invalid date");
        assertEquals("",DataParser.extractStringWithRegex("4//2010",dateRegex),"Invalid date");
    }
}