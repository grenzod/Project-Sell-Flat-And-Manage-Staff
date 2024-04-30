package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
@Transactional
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;

    //Thêm mới hoặc sửa tòa nhà
    @PostMapping
    public void AddOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        buildingService.UpgradeOrAddBuilding(buildingDTO);
    }

    //Xóa tòa nhà theo id
    @DeleteMapping("/{ids}")
    public void DeleteBuildings(@PathVariable Long[] ids){
        buildingService.DeleteBulding(ids);
    }

    //Thao tác hiển thị nhân viên lên modal
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = buildingService.listStaffs(id);
        return result;
    }

    //THao tác giao nhân viên
    @PostMapping("/assigment")
    public void upgradeAssignmentBuilding(@RequestBody AssignmentDTO assignmentDTO){
        buildingService.GiveBuildingForStaff(assignmentDTO);
    }
}
