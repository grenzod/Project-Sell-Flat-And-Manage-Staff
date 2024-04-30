package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> findAllCustomers(Pageable pageable, CustomerSearchBuilder customerSearchBuilder);
    int countTotalItem(CustomerSearchBuilder customerSearchBuilder);
}
