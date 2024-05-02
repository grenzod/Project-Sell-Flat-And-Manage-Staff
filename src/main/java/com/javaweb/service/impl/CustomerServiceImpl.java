package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.converter.UpgradeOrAddCustomerConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerSearchBuilderConverter customerSearchBuilderConverter;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<CustomerSearchResponse> getAllCustomers(Pageable pageable, CustomerSearchRequest customerSearchRequest) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(customerSearchRequest);
        List<CustomerEntity> customerEntities = customerRepository.findAllCustomers(pageable, customerSearchBuilder);

        List<CustomerSearchResponse> resultList = new ArrayList<>();
        for(CustomerEntity item : customerEntities) {
            CustomerSearchResponse customer = modelMapper.map(item, CustomerSearchResponse.class);
            resultList.add(customer);
        }
        return resultList;
    }

    @Override
    public int countTotalItems(CustomerSearchRequest searchRequest) {
        CustomerSearchBuilder customerSearchBuilder = customerSearchBuilderConverter.toCustomerSearchBuilder(searchRequest);
        return customerRepository.countTotalItem(customerSearchBuilder);
    }

    @Override
    public ResponseDTO listStaffs(Long id) {
        CustomerEntity customer = customerRepository.findById(id).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1, "STAFF");
        List<UserEntity> staffAssignment = customer.getUsers();

        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        for(UserEntity x : staffs){
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(x.getFullName());
            staffResponseDTO.setStaffId(x.getId());
            if(staffAssignment.contains(x)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        ResponseDTO kq = new ResponseDTO();
        kq.setData(staffResponseDTOS);
        kq.setMessage("Success");
        return kq;
    }

    @Override
    public void GiveCustomerForStaff(AssignmentDTO assignmentDTO) {
        CustomerEntity customerEntity = customerRepository.findById(assignmentDTO.getId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentDTO.getStaffs());
        customerEntity.setUsers(staffs);
    }

    @Override
    public void DeleteCustomer(Long[] ids) {
        for(Long id : ids) {
            CustomerEntity customerEntity = customerRepository.findById(id).get();
            customerEntity.setActive(0L);
        }
    }

    @Override
    public void UpgradeOrAddCustomer(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = UpgradeOrAddCustomerConverter.toUpgradeOrAddCustomerConverter(customerDTO);
        customerRepository.save(customerEntity);
    }

    @Override
    public void UpgradeOrAddTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transaction = new TransactionEntity();
        if(transactionDTO.getId() != null){
            transaction = transactionRepository.findById(transactionDTO.getId()).get();
        }
        else {
            transaction.setCode(transactionDTO.getCode());
            transaction.setCustomer(customerRepository.findById(transactionDTO.getCustomerId()).get());
        }
        transaction.setNote(transactionDTO.getTransactionDetail());
        transaction.setStaffid(SecurityUtils.getPrincipal().getId());
        transactionRepository.save(transaction);
    }
}
