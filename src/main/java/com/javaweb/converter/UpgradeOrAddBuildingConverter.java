package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UpgradeOrAddBuildingConverter {
    private static ModelMapper modelMapper;
    private static BuildingRepository buildingRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        UpgradeOrAddBuildingConverter.modelMapper = modelMapper;
    }

    @Autowired
    public void setBuildingRepository(BuildingRepository buildingRepository) {
        UpgradeOrAddBuildingConverter.buildingRepository = buildingRepository;
    }

    public static BuildingEntity toUpgradeOrAddBuildingConverter(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity;
        buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        if(buildingDTO.getId() != null) buildingEntity.setItems(buildingRepository.findById(buildingDTO.getId()).get().getItems());

        String result = buildingDTO.getTypeCode().stream().collect(Collectors.joining(","));
        buildingEntity.setTypeCode(result);
        return buildingEntity;
    }
}
