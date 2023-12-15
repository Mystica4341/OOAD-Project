package com.mobile.ooad_project.Model;

public class CoSoSan {
    int idCoSoSan, soLuongSan, giaSan5, giaSan7;

    String diachi, ten, moTa, sdt, hinhAnh;

    public int getGiaSan5() {
        return giaSan5;
    }

    public void setGiaSan5(int giaSan5) {
        this.giaSan5 = giaSan5;
    }

    public int getGiaSan7() {
        return giaSan7;
    }

    public void setGiaSan7(int giaSan7) {
        this.giaSan7 = giaSan7;
    }

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

    public CoSoSan(int idCoSoSan, String diachi, String ten, String sdt, int soLuongSan, String moTa, String hinhAnh, int giaSan5, int giaSan7) {
        this.idCoSoSan = idCoSoSan;
        this.diachi = diachi;
        this.ten = ten;
        this.sdt = sdt;
        this.soLuongSan = soLuongSan;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.giaSan5 = giaSan5;
        this.giaSan7 = giaSan7;
    }

    public CoSoSan(){

    }
}
