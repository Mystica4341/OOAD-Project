package com.mobile.ooad_project.Model;

public class DatSan {
    int idDatSan, idKhachHang, idSan, tGDat, tinhTrang;
    String ngayDat, gioDat;

    public int getIdDatSan() {
        return idDatSan;
    }

    public void setIdDatSan(int idDatSan) {
        this.idDatSan = idDatSan;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }

    public int gettGDat() {
        return tGDat;
    }

    public void settGDat(int tGDat) {
        this.tGDat = tGDat;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGioDat() {
        return gioDat;
    }

    public void setGioDat(String gioDat) {
        this.gioDat = gioDat;
    }

    public DatSan(int idDatSan, int idKhachHang, int idSan, int tGDat, int tinhTrang, String ngayDat, String gioDat) {
        this.idDatSan = idDatSan;
        this.idKhachHang = idKhachHang;
        this.idSan = idSan;
        this.tGDat = tGDat;
        this.tinhTrang = tinhTrang;
        this.ngayDat = ngayDat;
        this.gioDat = gioDat;
    }

    public DatSan() {
    }
}
