package com.clinic.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "did")
    private String did;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "gname")
    private String gname;

    @Column(name = "tname")
    private String tname;

    @Column(name = "unit")
    private String unit;

    @Column(name = "bunit")
    private String bunit;

    @Column(name = "uqty")
    private Integer uqty;

    @Column(name = "dgid")
    private String dgid;

    @Column(name = "dgroup")
    private String dgroup;

    @Column(name = "tid")
    private String tid;

    @Column(name = "typname")
    private String typname;

    @Column(name = "bprice")
    private BigDecimal bprice;

    @Column(name = "tprice")
    private BigDecimal tprice;

    @Column(name = "sprice")
    private BigDecimal sprice;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "sqty")
    private Integer sqty;

    @Column(name = "duse")
    private String duse;

    @Column(name = "duse_l1")
    private String duseL1;

    @Column(name = "duse_l2")
    private String duseL2;

    @Column(name = "wuse")
    private String wuse;

    @Column(name = "huse")
    private String huse;

    @Column(name = "status")
    private String status;

    @Column(name = "dsize")
    private String dsize;

    @Column(name = "vat")
    private String vat;

    @Column(name = "dc")
    private String dc;

    @Column(name = "ec")
    private String ec;

    @Column(name = "img")
    private String img;

    @Column(name = "typ")
    private String typ;

    // Getters and Setters
    // ... generate using your IDE or manually write them

}