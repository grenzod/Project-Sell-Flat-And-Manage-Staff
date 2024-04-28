package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingUpgradeConverter {
    private static BuildingRepository buildingRepository;

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository) {
        BuildingUpgradeConverter.buildingRepository = buildingRepository;
    }

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        BuildingUpgradeConverter.modelMapper = modelMapper;
    }

    public static BuildingDTO toBuildingUpgrade(Long id){
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);

        List<RentAreaEntity> rentAreaEntities = buildingEntity.getItems();
        String result = rentAreaEntities.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(result);

        String[] s = buildingEntity.getTypeCode().split(",");
        List<String> typeCode = new ArrayList<>();
        for(String x : s){
            typeCode.add(x);
        }
        buildingDTO.setTypeCode(typeCode);
        return buildingDTO;
    }
}
