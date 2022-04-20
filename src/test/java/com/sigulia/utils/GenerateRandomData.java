package com.sigulia.utils;
import org.apache.commons.lang3.RandomStringUtils;


public class GenerateRandomData {
    // Create random string
    public static String generatedString() {
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;
    }

    // Create random integer
    public static String generatedInt() {
        int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedInt = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedInt;
    }
}
