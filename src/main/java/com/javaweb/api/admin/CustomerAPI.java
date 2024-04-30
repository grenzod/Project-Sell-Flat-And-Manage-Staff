package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping ("/api/customer")
@Transactional
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public void AddOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
    }

    @PostMapping("/assigment")
    public void upgradeAssignmentCustomer(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
    }
}
