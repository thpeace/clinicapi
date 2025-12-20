package com.clinic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hn")
    private String hn;

    @Column(name = "mode")
    private String mode;

    @Column(name = "dat")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dat;

    @Column(name = "level")
    private String level;

    @Column(name = "pname")
    private String pname;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "nname")
    private String nname;

    @Column(name = "sex")
    private String sex;

    @Column(name = "personalid")
    private String personalid;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "tel1")
    private String tel1;

    @Column(name = "tel2")
    private String tel2;

    @Column(name = "tel3")
    private String tel3;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private BigDecimal salary;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "stayin")
    private String stayin;

    @Column(name = "image")
    private String image;

    @Column(name = "vn")
    private String vn;

    @Column(name = "cn")
    private String cn;

    @Column(name = "empid")
    private String empid;

    @Column(name = "dinose")
    private String dinose;

    @Column(name = "news")
    private String news;

    @Column(name = "suse")
    private String suse;

    @Column(name = "sr")
    private String sr;

    @Column(name = "pr")
    private String pr;

    @Column(name = "meko")
    private String meko;

    @Column(name = "mem")
    private String mem;

    @Column(name = "tm")
    private String tm;

    @Column(name = "am")
    private String am;

    @Column(name = "province")
    private String province;

    @Column(name = "zip")
    private String zip;

    @Column(name = "skin")
    private String skin;

    @Column(name = "opt")
    private String opt;

    @Column(name = "cdt")
    private String cdt;

    @Column(name = "cdtext")
    private String cdtext;

    @Column(name = "tnet")
    private String tnet;

    @Column(name = "edu")
    private String edu;

    @Column(name = "cdin")
    private String cdin;

    @Column(name = "cdruge")
    private String cdruge;

    @Column(name = "rdin")
    private String rdin;

    @Column(name = "mcode")
    private String mcode;

    @Column(name = "mdate")
    private String mdate;

    @Column(name = "mscore")
    private String mscore;

    @Column(name = "ecode")
    private String ecode;

    @Column(name = "edate")
    private String edate;

    @Column(name = "exdate")
    private String exdate;

    @Column(name = "status")
    private String status;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "line")
    private String line;

    @Column(name = "wg")
    private String wg;

    @Column(name = "hg")
    private String hg;

    @Column(name = "typ")
    private String typ;

    @Column(name = "drugeanti")
    private String drugeanti;

    @Column(name = "clinicname")
    private String clinicname;

    // =====================
    // Getters & Setters
    // =====================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHn() {
        return hn;
    }

    public void setHn(String hn) {
        this.hn = hn;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public LocalDateTime getDat() {
        return dat;
    }

    public void setDat(LocalDateTime dat) {
        this.dat = dat;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersonalid() {
        return personalid;
    }

    public void setPersonalid(String personalid) {
        this.personalid = personalid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getStayin() {
        return stayin;
    }

    public void setStayin(String stayin) {
        this.stayin = stayin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getDinose() {
        return dinose;
    }

    public void setDinose(String dinose) {
        this.dinose = dinose;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getSuse() {
        return suse;
    }

    public void setSuse(String suse) {
        this.suse = suse;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getMeko() {
        return meko;
    }

    public void setMeko(String meko) {
        this.meko = meko;
    }

    public String getMem() {
        return mem;
    }

    public void setMem(String mem) {
        this.mem = mem;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getCdt() {
        return cdt;
    }

    public void setCdt(String cdt) {
        this.cdt = cdt;
    }

    public String getCdtext() {
        return cdtext;
    }

    public void setCdtext(String cdtext) {
        this.cdtext = cdtext;
    }

    public String getTnet() {
        return tnet;
    }

    public void setTnet(String tnet) {
        this.tnet = tnet;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public String getCdin() {
        return cdin;
    }

    public void setCdin(String cdin) {
        this.cdin = cdin;
    }

    public String getCdruge() {
        return cdruge;
    }

    public void setCdruge(String cdruge) {
        this.cdruge = cdruge;
    }

    public String getRdin() {
        return rdin;
    }

    public void setRdin(String rdin) {
        this.rdin = rdin;
    }

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMscore() {
        return mscore;
    }

    public void setMscore(String mscore) {
        this.mscore = mscore;
    }

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getExdate() {
        return exdate;
    }

    public void setExdate(String exdate) {
        this.exdate = exdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWg() {
        return wg;
    }

    public void setWg(String wg) {
        this.wg = wg;
    }

    public String getHg() {
        return hg;
    }

    public void setHg(String hg) {
        this.hg = hg;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getDrugeanti() {
        return drugeanti;
    }

    public void setDrugeanti(String drugeanti) {
        this.drugeanti = drugeanti;
    }
}