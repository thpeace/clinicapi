package com.clinic.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "treatments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Treatment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String tid;

    @Column(length = 50)
    private String bcode;

    @Column(length = 200)
    private String tname;

    @Column(precision = 15, scale = 2)
    private BigDecimal price;

    @Column(precision = 15, scale = 2)
    private BigDecimal price1;

    @Column(precision = 15, scale = 2)
    private BigDecimal price2;

    @Column(precision = 15, scale = 2)
    private BigDecimal price3;

    @Column(precision = 15, scale = 2)
    private BigDecimal price4;

    @Column(length = 10)
    private String typ;

    @Column(name = "score_d", length = 20)
    private String scoreD;

    @Column(name = "score_dtyp", length = 20)
    private String scoreDtyp;

    @Column(length = 20)
    private String score;

    @Column(name = "score_typ", length = 20)
    private String scoreTyp;

    @Column(precision = 15, scale = 2)
    private BigDecimal costs;

    private Integer qty;

    @Column(length = 50)
    private String unit;

    @Column(length = 10)
    private String vat;

    @Column(length = 10)
    private String dtyp;

    @Column(length = 10)
    private String dc;

    @Column(length = 10)
    private String du;

    @Column(length = 10)
    private String ec;

    @Column(length = 10)
    private String eu;

    @Column(length = 20)
    private String tgroup;

    @Builder.Default
    @Column(length = 10)
    private String status = "Y";

    @Column(length = 20)
    private String ttyp;

    @Column(length = 50)
    private String alate;

    @Column(length = 10)
    private String lv;

    @Column(length = 100)
    private String n1;

    @Column(length = 100)
    private String n2;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
}
