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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
}