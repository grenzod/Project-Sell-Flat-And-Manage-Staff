package com.javaweb.builder;

import java.util.ArrayList;
import java.util.List;

public class BuildingSearchBuilder {
    private String name;
    private String district;
    private String ward;
    private String street;
    private Long numberofbasement;
    private String managername;
    private String managerphone;
    private Long floorarea;
    private Long rentPriceFirst;
    private Long rentPriceEnd;
    private Long valueRFirst;
    private Long valueREnd;
    private Long idStaff;
    private List<String>typeCode;

    public BuildingSearchBuilder(Builder builder) {
        this.name = builder.nameBuilding;
        this.district = builder.District;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberofbasement = builder.numberOfBasement;
        this.managername = builder.nameManager;
        this.managerphone = builder.phoneManager;
        this.floorarea = builder.floorArea;
        this.rentPriceFirst = builder.rentPriceFirst;
        this.rentPriceEnd = builder.rentPriceEnd;
        this.valueRFirst = builder.valueRFirst;
        this.valueREnd = builder.valueREnd;
        this.idStaff = builder.idStaff;
        this.typeCode = builder.typeCode;
    }

    public String getName() {
        return name;
    }
    public String getDistrict() {
        return district;
    }
    public String getWard() {
        return ward;
    }
    public String getStreet() {
        return street;
    }
    public Long getNumberofbasement() {
        return numberofbasement;
    }
    public String getNameManager() {
        return managername;
    }
    public String getPhoneManager() {
        return managerphone;
    }
    public Long getFloorarea() {
        return floorarea;
    }
    public Long getRentPriceFirst() {
        return rentPriceFirst;
    }
    public Long getRentPriceEnd() {
        return rentPriceEnd;
    }
    public Long getValueRFirst() {
        return valueRFirst;
    }
    public Long getValueREnd() {
        return valueREnd;
    }
    public Long getIdStaff() {
        return idStaff;
    }
    public List<String> getTypeCode() {
        return typeCode;
    }

    public static class Builder{
        private String nameBuilding;
        private String District;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String nameManager;
        private String phoneManager;
        private Long floorArea;
        private Long rentPriceFirst;
        private Long rentPriceEnd;
        private Long valueRFirst;
        private Long valueREnd;
        private Long idStaff;
        private List<String>typeCode = new ArrayList<>();

        public Builder setName(String name) {
            this.nameBuilding = name;
            return this;
        }
        public Builder setIdDistrict(String idDistrict) {
            this.District = idDistrict;
            return this;
        }
        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }
        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }
        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }
        public Builder setNameManager(String nameManager) {
            this.nameManager = nameManager;
            return this;
        }
        public Builder setPhoneManager(String phoneManager) {
            this.phoneManager = phoneManager;
            return this;
        }
        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }
        public Builder setRentPriceFirst(Long rentPriceFirst) {
            this.rentPriceFirst = rentPriceFirst;
            return this;
        }
        public Builder setRentPriceEnd(Long rentPriceEnd) {
            this.rentPriceEnd = rentPriceEnd;
            return this;
        }
        public Builder setValueRFirst(Long valueRFirst) {
            this.valueRFirst = valueRFirst;
            return this;
        }
        public Builder setValueREnd(Long valueREnd) {
            this.valueREnd = valueREnd;
            return this;
        }
        public Builder setIdStaff(Long idStaff) {
            this.idStaff = idStaff;
            return this;
        }
        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
