package com.framework.core;

import java.io.*;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {


    public static String getPropertyFromFile(String key) {


        String browser = System.getProperty("configuration.path");
        Properties allProperties = new Properties();
      //  String[] paths = pathWithPropertiesFiles.split("[;]");
        switch (browser){
            case "chrome":
                try {
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();
                    allProperties.load(loader.getResourceAsStream("chrome/chrome.properties"));

                } catch (FileNotFoundException e) {
                    Logger.getLogger(e.toString());
                } catch (IOException e) {
                    Logger.getLogger(e.toString());
                }
                break;
            case "edge":
                try {
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();
                    allProperties.load(loader.getResourceAsStream("edge/edge.properties"));

                } catch (FileNotFoundException e) {
                    Logger.getLogger(e.toString());
                } catch (IOException e) {
                    Logger.getLogger(e.toString());
                }
                break;
            case "firefox":
                try {
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();
                    allProperties.load(loader.getResourceAsStream("firefox/firefox.properties"));

                } catch (FileNotFoundException e) {
                    Logger.getLogger(e.toString());
                } catch (IOException e) {
                    Logger.getLogger(e.toString());
                }
                break;
        }
        return allProperties.getProperty(key);

    }



}
