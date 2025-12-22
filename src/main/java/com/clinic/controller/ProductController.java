package com.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clinic.dto.ProductResponse;
import com.clinic.model.Product;
import com.clinic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/import")
    public String importProducts(@RequestBody ProductResponse productResponse) {
        List<Product> products = productResponse.getData();

        logger.info("Received {} products to import", products.size());

        for (Product p : products) {
            logger.debug("Importing product: {}", p.getDid());
            p.setDid(null); // Ensure new inserts have null IDs
        }

        // Ensure new inserts have proper IDs (if needed)
        productRepository.saveAll(products);
        logger.info("Successfully imported {} products", products.size());
        return "Imported " + products.size() + " products successfully!";
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}