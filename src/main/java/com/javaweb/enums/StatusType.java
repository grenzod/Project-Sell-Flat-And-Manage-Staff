package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum StatusType {
    CXL("Chưa xử lý"),
    DXL("Đang xử lý"),
    HT("Hoàn thành");

    private String name;
    private StatusType(String name) {this.name = name;}

    public static Map<String, String> statusType(){
        Map<String, String> typeCodes =  new LinkedHashMap<>();
        for(StatusType it : StatusType.values()){
            typeCodes.put(it.toString(), it.name);
        }
        return typeCodes;
    }
}
