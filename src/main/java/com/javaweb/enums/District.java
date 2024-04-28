package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum District {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_4("Quận 4");

    private final String districtName;
    District(String districtName){
        this.districtName = districtName;
    }

    public static Map<String, String> type(){
        Map<String, String> districts = new TreeMap<>();
        for (District it : District.values()){
            districts.put(it.toString(), it.districtName);
        }
        return districts;
    }
}
