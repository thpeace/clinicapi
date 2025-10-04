package com.clinic.dto;

import com.clinic.model.Product;
import java.util.List;

public class ProductResponse {
    private String status;
    private List<Product> data;
    private Integer total;

    // Getters & Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}