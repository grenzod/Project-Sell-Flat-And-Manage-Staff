package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.security.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UpgradeOrAddCustomerConverter {
    private static ModelMapper modelMapper;
    private static CustomerRepository customerRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        UpgradeOrAddCustomerConverter.modelMapper = modelMapper;
    }

    @Autowired
    public void setBuildingRepository(CustomerRepository customerRepository) {
        UpgradeOrAddCustomerConverter.customerRepository= customerRepository;
    }

    public static CustomerEntity toUpgradeOrAddCustomerConverter(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        customerEntity.setActive(1L);
        if(customerEntity.getId() != null){
            customerEntity.setUsers(customerRepository.findById(customerDTO.getId()).get().getUsers());
            customerEntity.setTransactions(customerRepository.findById(customerDTO.getId()).get().getTransactions());
            customerEntity.setCreatedBy(customerRepository.findById(customerDTO.getId()).get().getCreatedBy());
            customerEntity.setCreatedDate(customerRepository.findById(customerDTO.getId()).get().getCreatedDate());
        }

        return customerEntity;
    }
}
