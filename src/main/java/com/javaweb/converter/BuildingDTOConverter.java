package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingDTO(BuildingEntity item) {
        BuildingSearchResponse buildingSearchResponse = modelMapper.map(item, BuildingSearchResponse.class);

        String[] s = item.getDistrict().split("_");
        buildingSearchResponse.setAddress(item.getStreet() + " , " + item.getWard() + " , " + "Quận " + s[1]);
        List<RentAreaEntity> rentalAreaEntity = item.getItems();
        String result = rentalAreaEntity.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingSearchResponse.setRentArea(result);
        return buildingSearchResponse;
    }
}
