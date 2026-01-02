package com.clinic.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class TreatmentImportDto {
    private String tid;
    private String bcode;
    private String tname;
    private String price;
    private String price1;
    private String price2;
    private String price3;
    private String price4;
    private String typ;
    private String score_d;
    private String score_dtyp;
    private String score;
    private String score_typ;
    private String costs;
    private String qty;
    private String unit;
    private String vat;
    private String dtyp;
    private String dc;
    private String du;
    private String ec;
    private String eu;
    private String tgroup;
    private String status;
    private String ttyp;
    private String alate;
    private String lv;
    private String n1;
    private String n2;
    private List<PriceListItem> plist;

    @Data
    public static class PriceListItem {
        private String tid;
        private String qty;
        private String price;
    }
}
