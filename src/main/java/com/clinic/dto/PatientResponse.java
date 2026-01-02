package com.clinic.dto;

import com.clinic.model.Patient;
import lombok.Data;
import java.util.List;

@Data
public class PatientResponse {
    private String status;
    private List<Patient> data;
    private int total;
    private String sql;
}