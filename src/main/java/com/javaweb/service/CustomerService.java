package com.javaweb.service;

import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> getAllCustomers(Pageable pageable, CustomerSearchRequest searchRequest);
    int countTotalItems(CustomerSearchRequest searchRequest);
    ResponseDTO listStaffs(Long id);
    void GiveCustomerForStaff(AssignmentDTO assignmentDTO);
    void DeleteCustomer(Long[] ids);
    void UpgradeOrAddBuilding(CustomerDTO customerDTO);
    void UpgradeOrAddTransaction(TransactionDTO transactionDTO);
}
