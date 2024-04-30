package com.javaweb.repository.custom.impl;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private StringBuilder innerJoinMethod(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder();

        Long idStaff = customerSearchBuilder.getStaffId();
        if(idStaff != null) {
            sql.append(" INNER JOIN assignmentcustomer c ON a.id = c.customerid");
            sql.append(" INNER JOIN user d ON d.id = c.staffid ");
        }

        return sql;
    }

    private StringBuilder solveQueryNormalMethod(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1 = 1");

        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("idStaff")) {
                    Object value = item.get(customerSearchBuilder);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            sql.append(" AND a." + fieldName + " = " + value);
                        }
                        else if(item.getType().getName().equals("java.lang.String")) {
                            sql.append(" AND a." + fieldName + " LIKE '%" + value + "%'");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sql;
    }

    private StringBuilder buildQueryFilter(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT a.* FROM customer a");
        sql.append(innerJoinMethod(customerSearchBuilder));
        sql.append(solveQueryNormalMethod(customerSearchBuilder));

        return sql;
    }

    @Override
    public List<CustomerEntity> findAllCustomers(Pageable pageable, CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(customerSearchBuilder))
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(CustomerSearchBuilder customerSearchBuilder) {
        StringBuilder sql = buildQueryFilter(customerSearchBuilder);
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }
}
