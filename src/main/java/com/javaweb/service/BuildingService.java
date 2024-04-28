package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    ResponseDTO listStaffs(Long id);
    void UpgradeOrAddBuilding(BuildingDTO buildingDTO);
    void GiveBuildingForStaff(AssignmentBuildingDTO assignmentBuildingDTO);
    void DeleteBulding(Long[] ids);
    List<BuildingSearchResponse> getAllBuilding(Pageable pageable, BuildingSearchRequest buildingSearchRequest);
    int countTotalItems(BuildingSearchRequest buildingSearchRequest);
}
