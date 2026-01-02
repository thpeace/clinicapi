package com.clinic.dto.response;

import com.clinic.model.Product;
import lombok.Data;
import java.util.List;

@Data
public class ProductResponse {
    private String status;
    private List<Product> data;
    private Integer total;
}
