package com.clinic.dto;

import lombok.Data;
import java.util.List;

@Data
public class TreatmentImportRequest {
    private String status;
    private List<TreatmentImportDto> data;
    private Integer total;
}
