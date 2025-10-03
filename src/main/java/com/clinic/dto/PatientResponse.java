package com.clinic.dto;

import com.clinic.model.Patient;
import java.util.List;

public class PatientResponse {
    private String status;
    private List<Patient> data;
    private int total;
    private String sql;

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Patient> getData() {
        return data;
    }

    public void setData(List<Patient> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}