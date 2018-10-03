package com.example.dell.qunlsch.model;

public class HoaDon {
    String ma,ngay;
    public HoaDon(String ma, String ngay) {
        this.ma = ma;
        this.ngay = ngay;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
