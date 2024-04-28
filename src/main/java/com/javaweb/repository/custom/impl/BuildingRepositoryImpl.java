package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    private StringBuilder innerJoinMethod(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder();

        Long idStaff = buildingSearchBuilder.getIdStaff();
        if(idStaff != null) {
            sql.append(" INNER JOIN assignmentbuilding c ON a.id = c.buildingid");
            sql.append(" INNER JOIN user d ON d.id = c.staffid ");
        }

        return sql;
    }

    private StringBuilder solveQueryNormalMethod(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1 = 1");

        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("idStaff") && !fieldName.equals("code") && !fieldName.startsWith("value")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
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

    private StringBuilder solveQuerySpecialMethod(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder();

        Long idStaff = buildingSearchBuilder.getIdStaff();
        if(idStaff != null) {
            sql.append(" AND d.id = " + idStaff);
        }

        Long valueFirst = buildingSearchBuilder.getValueRFirst();
        Long valueEnd = buildingSearchBuilder.getValueREnd();
        if(valueFirst != null || valueEnd != null) {
            sql.append(" AND EXISTS (SELECT * FROM rentarea b WHERE a.id = b.buildingid");
            if(valueEnd != null) {
                sql.append(" AND b.value <= " + valueEnd);
            }
            if(valueFirst != null){
                sql.append(" AND b.value >= " + valueFirst);
            }
            sql.append(")");
        }

        Long rentPriceFirst = buildingSearchBuilder.getRentPriceFirst();
        Long rentPriceEnd = buildingSearchBuilder.getRentPriceEnd();
        if(rentPriceFirst!= null) {
            sql.append(" AND a.rentPrice >= " + rentPriceFirst);
        }
        if(rentPriceEnd != null){
            sql.append(" AND a.rentPrice <= " + rentPriceEnd);
        }

        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if(typeCode != null && typeCode.size() != 0) {
            sql.append(" AND a.type IN (");
            String s = typeCode.stream().map(it -> "'" + it + "'").collect(Collectors.joining(","));
            sql.append(s +")");
        }

        return sql;
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(buildingSearchBuilder));

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public List<BuildingEntity> getAllBuilding(Pageable pageable, BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(buildingSearchBuilder))
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());

        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = buildQueryFilter(buildingSearchBuilder);
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    private StringBuilder buildQueryFilter(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT a.* FROM building a");
        sql.append(innerJoinMethod(buildingSearchBuilder));
        sql.append(solveQueryNormalMethod(buildingSearchBuilder));
        sql.append(solveQuerySpecialMethod(buildingSearchBuilder));

        return sql;
    }
}
