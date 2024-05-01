package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
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
    public void AddOrUpdateBuilding(@RequestBody CustomerDTO customerDTO){
        customerService.UpgradeOrAddBuilding(customerDTO);
    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id){
        ResponseDTO result = customerService.listStaffs(id);
        return result;
    }

    @PostMapping("/assigment")
    public void upgradeAssignmentBuilding(@RequestBody AssignmentDTO assignmentDTO){
        customerService.GiveCustomerForStaff(assignmentDTO);
    }

    @DeleteMapping("/{ids}")
    public void DeleteBuildings(@PathVariable Long[] ids){
        customerService.DeleteCustomer(ids);
    }

    @PostMapping("/transaction")
    public void AddOrUpgradeTransaction(@RequestBody TransactionDTO transactionDTO) {
        System.out.println("ok");
    }
}
