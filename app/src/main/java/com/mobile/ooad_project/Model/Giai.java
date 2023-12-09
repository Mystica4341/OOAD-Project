package com.mobile.ooad_project.Model;

public class Giai {
    String idGiai, tenGiai, idSan, NgayThiDau;

    public String getIdGiai() {
        return idGiai;
    }

    public void setIdGiai(String idGiai) {
        this.idGiai = idGiai;
    }

    public String getTenGiai() {
        return tenGiai;
    }

    public void setTenGiai(String tenGiai) {
        this.tenGiai = tenGiai;
    }

    public String getIdSan() {
        return idSan;
    }

    public void setIdSan(String idSan) {
        this.idSan = idSan;
    }

    public String getNgayThiDau() {
        return NgayThiDau;
    }

    public void setNgayThiDau(String ngayThiDau) {
        NgayThiDau = ngayThiDau;
    }

    public Giai(String idGiai, String tenGiai, String idSan, String ngayThiDau) {
        this.idGiai = idGiai;
        this.tenGiai = tenGiai;
        this.idSan = idSan;
        NgayThiDau = ngayThiDau;
    }

    public Giai(){

    }
}
