package com.mobile.ooad_project.Model;

public class CoSoSan {
    String idCoSoSan, diachi, ten, sdt, soLuongSan, moTa, hinhAnh;

    public String getIdCoSoSan() {
        return idCoSoSan;
    }

    public void setIdCoSoSan(String idCoSoSan) {
        this.idCoSoSan = idCoSoSan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSoLuongSan() {
        return soLuongSan;
    }

    public void setSoLuongSan(String soLuongSan) {
        this.soLuongSan = soLuongSan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public CoSoSan(String idCoSoSan, String diachi, String ten, String sdt, String soLuongSan, String moTa, String hinhAnh) {
        this.idCoSoSan = idCoSoSan;
        this.diachi = diachi;
        this.ten = ten;
        this.sdt = sdt;
        this.soLuongSan = soLuongSan;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public CoSoSan(){

    }
}
