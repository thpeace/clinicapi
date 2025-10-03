package com.clinic.model;

import javax.persistence.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hn;
    private String mode;
    private String dat;
    private String level;
    private String pname;
    private String fname;
    private String lname;
    private String nname;
    private String sex;
    private String personalid;
    private String birthday;
    private String occupation;
    private String nationality;
    private String address1;
    private String address2;
    private String tel1;
    private String tel2;
    private String tel3;
    private String email;
    private String stayin;
    private String image;
    private String vn;
    private String cn;
    private String empid;
    private String dinose;
    private String news;
    private String suse;
    private String sr;
    private String pr;
    private String meko;
    private String mem;
    private String tm;
    private String am;
    private String province;
    private String zip;
    private String skin;
    private String opt;
    private String cdt;
    private String cdtext;
    private String tnet;
    private String salary;
    private String edu;
    private String cdin;
    private String cdruge;
    private String rdin;
    private String mcode;
    private String mdate;
    private String mscore;
    private String ecode;
    private String edate;
    private String exdate;
    private String status;
    private String facebook;
    private String line;
    private String wg;
    private String hg;
    private String typ;
    private String drugeanti;
    private String clinicname;

    // Getters & Setters
}