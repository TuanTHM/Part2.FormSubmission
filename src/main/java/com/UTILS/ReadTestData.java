package com.UTILS;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadTestData {
    private static final String TESTDATA = "./TestData/TestData.properties";

    public static String getTestData(String nameOfData) {
        try {
            Properties properties = new Properties();
            FileInputStream fileIn = new FileInputStream(TESTDATA);
            properties.load(fileIn);
            return properties.getProperty(nameOfData);

        } catch (Exception e) {
            System.out.println("ERROR - " + nameOfData + " - NOT FOUND");
            return null;
        }
    }

}
