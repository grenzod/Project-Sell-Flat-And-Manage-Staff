package com.javaweb.enums;

import java.util.Map;
import java.util.TreeMap;

public enum  TypeCode {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String name;
    TypeCode(String name){
        this.name = name;
    }

    public static Map<String, String> type(){
        Map<String, String> TypeCodes = new TreeMap<>();
        for (TypeCode it : TypeCode.values()){
            TypeCodes.put(it.toString(), it.name);
        }
        return TypeCodes;
    }
}
