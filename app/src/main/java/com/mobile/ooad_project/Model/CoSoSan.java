package com.mobile.ooad_project.Model;

public class CoSoSan {
    int idCoSoSan, soLuongSan;

    String diachi, ten, moTa, sdt, hinhAnh;

    public int getIdCoSoSan() {
        return idCoSoSan;
    }

    public void setIdCoSoSan(int idCoSoSan) {
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

    public int getSoLuongSan() {
        return soLuongSan;
    }

    public void setSoLuongSan(int soLuongSan) {
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

    public CoSoSan(int idCoSoSan, String diachi, String ten, String sdt, int soLuongSan, String moTa, String hinhAnh) {
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
