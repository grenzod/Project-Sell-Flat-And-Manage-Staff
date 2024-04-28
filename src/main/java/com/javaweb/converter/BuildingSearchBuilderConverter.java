package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest){
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(buildingSearchRequest.getName())
                .setIdDistrict(buildingSearchRequest.getDistrict())
                .setFloorArea(buildingSearchRequest.getFloorArea())
                .setStreet(buildingSearchRequest.getStreet())
                .setWard(buildingSearchRequest.getWard())
                .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                .setNameManager(buildingSearchRequest.getManagerName())
                .setPhoneManager(buildingSearchRequest.getManagerPhone())
                .setValueRFirst(buildingSearchRequest.getAreaFrom())
                .setValueREnd(buildingSearchRequest.getAreaTo())
                .setRentPriceFirst(buildingSearchRequest.getRentPriceFrom())
                .setRentPriceEnd(buildingSearchRequest.getRentPriceFrom())
                .setIdStaff(buildingSearchRequest.getStaffId())
                .setTypeCode(buildingSearchRequest.getTypeCode())
                .build();
        return buildingSearchBuilder;
    }
}
