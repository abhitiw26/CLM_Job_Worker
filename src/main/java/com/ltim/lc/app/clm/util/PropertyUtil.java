package com.ltim.lc.app.clm.util;


public class PropertyUtil {

    public static String getProperty(String key) {
        if (null != System.getenv(key)) {
            return System.getenv(key).strip();
        }

        key = key.replaceAll("_", ".").toLowerCase();
        return System.getProperty(key).strip();
    }
}