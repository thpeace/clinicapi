package com.clinic.dto;

import com.clinic.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class ProductResponse {
    private String status;
    private List<Product> data;
    private Integer total;
}