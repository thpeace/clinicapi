package com.clinic.controller;

import com.clinic.dto.ProductResponse;
import com.clinic.model.Product;
import com.clinic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/import")
    public String importProducts(@RequestBody ProductResponse productResponse) {
        List<Product> products = productResponse.getData();

        // Ensure new inserts have proper IDs (if needed)
        productRepository.saveAll(products);
        return "Imported " + products.size() + " products successfully!";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}