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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    // ===== Primary Key =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Identification =====
    @Column(nullable = true, length = 50)
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

    // Getters and Setters
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getBunit() {
        return bunit;
    }

    public void setBunit(String bunit) {
        this.bunit = bunit;
    }

    public Integer getUqty() {
        return uqty;
    }

    public void setUqty(Integer uqty) {
        this.uqty = uqty;
    }

    public String getDgid() {
        return dgid;
    }

    public void setDgid(String dgid) {
        this.dgid = dgid;
    }

    public String getDgroup() {
        return dgroup;
    }

    public void setDgroup(String dgroup) {
        this.dgroup = dgroup;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTypname() {
        return typname;
    }

    public void setTypname(String typname) {
        this.typname = typname;
    }

    public BigDecimal getBprice() {
        return bprice;
    }

    public void setBprice(BigDecimal bprice) {
        this.bprice = bprice;
    }

    public BigDecimal getTprice() {
        return tprice;
    }

    public void setTprice(BigDecimal tprice) {
        this.tprice = tprice;
    }

    public BigDecimal getSprice() {
        return sprice;
    }

    public void setSprice(BigDecimal sprice) {
        this.sprice = sprice;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getSqty() {
        return sqty;
    }

    public void setSqty(Integer sqty) {
        this.sqty = sqty;
    }

    public String getDuse() {
        return duse;
    }

    public void setDuse(String duse) {
        this.duse = duse;
    }

    public String getDuseL1() {
        return duseL1;
    }

    public void setDuseL1(String duseL1) {
        this.duseL1 = duseL1;
    }

    public String getDuseL2() {
        return duseL2;
    }

    public void setDuseL2(String duseL2) {
        this.duseL2 = duseL2;
    }

    public String getWuse() {
        return wuse;
    }

    public void setWuse(String wuse) {
        this.wuse = wuse;
    }

    public String getHuse() {
        return huse;
    }

    public void setHuse(String huse) {
        this.huse = huse;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getDsize() {
        return dsize;
    }

    public void setDsize(String dsize) {
        this.dsize = dsize;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}