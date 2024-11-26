package com.UTILS;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadUIElement {
    private static final String FORMSUBMISSION_PATH = "./UI_Elements/UI_Form.properties";

    public static String getUIElement(String nameOfElement) {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(FORMSUBMISSION_PATH);
            properties.load(file);
            return properties.getProperty(nameOfElement);

        } catch (Exception e) {
            System.out.println("ERROR - Cannot read data of " + nameOfElement);
            return null;
        }
    }
}
