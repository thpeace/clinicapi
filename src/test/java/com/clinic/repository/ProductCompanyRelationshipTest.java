package com.clinic.repository;

import com.clinic.model.Company;
import com.clinic.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class ProductCompanyRelationshipTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testProductCompanyRelationship() {
        // Create Company
        Company company = Company.builder()
                .nameth("บริษัท ยาไทย จำกัด")
                .nameen("Thai Drug Co., Ltd.")
                .createdDate(LocalDateTime.now())
                .build();
        company = companyRepository.save(company);
        Assertions.assertNotNull(company.getId());

        // Create Product
        Product product = Product.builder()
                .gname("Paracetamol")
                .tname("Sara")
                .company(company)
                .build();
        product = productRepository.save(product);

        // Verify
        Product fetchedProduct = productRepository.findById(product.getId()).orElseThrow();
        Assertions.assertNotNull(fetchedProduct.getCompany());
        Assertions.assertEquals(company.getId(), fetchedProduct.getCompany().getId());
        Assertions.assertEquals("Thai Drug Co., Ltd.", fetchedProduct.getCompany().getNameen());
    }
}
