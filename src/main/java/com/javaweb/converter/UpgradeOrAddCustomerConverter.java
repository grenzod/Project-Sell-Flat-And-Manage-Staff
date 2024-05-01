package com.javaweb.converter;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        if(customerEntity.getId() != null){
            customerEntity.setUsers(customerRepository.findById(customerDTO.getId()).get().getUsers());
            customerEntity.setTransactions(customerRepository.findById(customerDTO.getId()).get().getTransactions());
        }

        return customerEntity;
    }
}
