package com.javaweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "note")
    private String note;

    @Column(name = "staffid")
    private Long staffid;

    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity customer = new CustomerEntity();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
