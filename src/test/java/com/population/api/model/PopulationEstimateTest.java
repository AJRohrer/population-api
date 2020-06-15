package com.population.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PopulationEstimateTest {

    @Test
    void testPopulationEstimateWithInvalidDate(){
        try{
            PopulationEstimate pe = new PopulationEstimate("4/1a0/2020",10201000,"Northwest");
        } catch (IllegalArgumentException e) {
            assertEquals("Date is not in a valid format.", e.getMessage(), "IllegalArgumentException should be thrown when date is invalid.");
        }
    }

    @Test
    void testPopulationEstimateWithValidData(){
        PopulationEstimate pe = new PopulationEstimate("1/1/2019", 123124133,"SouthEast");
        assertEquals(123124133,pe.populationEstimate, "Population was " + pe.populationEstimate + " expected 123124133");
        assertEquals("1/1/2019", pe.populationDate, "Population date was " + pe.populationDate + "expected 1/1/2019");
    }

    @Test
    void compareTo() {
        PopulationEstimate pe1 = new PopulationEstimate("1/2/1992",1235123512,"Northwest");
        PopulationEstimate pe2 = new PopulationEstimate("2/1/1998", 981919813,"SouthEast");
        assertEquals(-5, pe1.compareTo(pe2), "Result was " + pe1.compareTo(pe2) + " expected -5, N is 5 letters before S");
        assertEquals(5, pe2.compareTo(pe1), "Result was " + pe2.compareTo(pe1) + " expected 5, s is 5 letters after N");
    }
}