package com.sav.csi.entity;


import java.util.Date;

public class ProductPrice {
    public ProductPrice() {}

    public ProductPrice(ProductPrice other) {
        productCode = other.getProductCode();
        number = other.getNumber();
        depart = other.getDepart();
        begin = other.getBegin();
        end = other.getEnd();
        value = other.getValue();
    }

    public ProductPrice(String productCode, int number, int depart, Date begin, Date end, long value) {
        this.productCode = productCode;
        this.number = number;
        this.depart = depart;
        this.begin = begin;
        this.end = end;
        this.value = value;
    }

    private long id;

    private String productCode;

    private int number;

    private int depart;

    private Date begin;

    private Date end;

    private long value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
