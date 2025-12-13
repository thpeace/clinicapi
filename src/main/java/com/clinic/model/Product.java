package com.clinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Product entity representing clinic products (medications, supplies, etc.)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", indexes = {
        @Index(name = "idx_product_barcode", columnList = "barcode"),
        @Index(name = "idx_product_did", columnList = "did")
})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    // ===== Primary Key =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Identification =====
    @Column(nullable = false, length = 50)
    private String did;

    @Column(unique = true, length = 100)
    private String barcode;

    private String gname; // Generic name
    private String tname; // Trade name

    // ===== Unit & Grouping =====
    private String unit;
    private String bunit;
    private Integer uqty;
    private String dgid;
    private String dgroup;
    private String tid;
    private String typname;

    // ===== Pricing (precision for financial safety) =====
    @Column(precision = 15, scale = 2)
    private BigDecimal bprice; // Buying price

    @Column(precision = 15, scale = 2)
    private BigDecimal tprice; // Trade price

    @Column(precision = 15, scale = 2)
    private BigDecimal sprice; // Selling price

    @Column(precision = 18, scale = 2)
    private BigDecimal total; // sprice * sqty

    // ===== Stock =====
    private Integer sqty;

    // ===== Usage Instructions =====
    private String duse;
    private String duseL1;
    private String duseL2;
    private String wuse;
    private String huse;

    // ===== Status (Enum for better consistency) =====
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProductStatus status;

    // ===== Extra attributes =====
    private String dsize;
    private String vat;
    private String dc;
    private String ec;
    private String img;
    private String typ;

    // ===== Enum Definition =====
    public enum ProductStatus {
        ACTIVE, INACTIVE, EXPIRED
    }
}