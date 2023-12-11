package com.mobile.ooad_project.Model;

public class Giai {
    int idGiai, idSan;
    String tenGiai, NgayThiDau;

    public int getIdGiai() {
        return idGiai;
    }

    public void setIdGiai(int idGiai) {
        this.idGiai = idGiai;
    }

    public String getTenGiai() {
        return tenGiai;
    }

    public void setTenGiai(String tenGiai) {
        this.tenGiai = tenGiai;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }

    public String getNgayThiDau() {
        return NgayThiDau;
    }

    public void setNgayThiDau(String ngayThiDau) {
        NgayThiDau = ngayThiDau;
    }

    public Giai(int idGiai, String tenGiai, int idSan, String ngayThiDau) {
        this.idGiai = idGiai;
        this.tenGiai = tenGiai;
        this.idSan = idSan;
        NgayThiDau = ngayThiDau;
    }

    public Giai(){

    }
}
