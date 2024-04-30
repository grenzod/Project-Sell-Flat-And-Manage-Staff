package com.javaweb.service.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.converter.CustomerSearchBuilderConverter;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
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
    ModelMapper modelMapper;

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

}
