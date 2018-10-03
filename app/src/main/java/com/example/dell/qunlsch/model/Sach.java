package com.example.dell.qunlsch.model;

public class Sach {
    String name,soluong,masach;

    public Sach(String name, String soluong, String masach) {
        this.name = name;
        this.soluong = soluong;
        this.masach = masach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }
}
