package com.mobile.ooad_project.Model;

public class QuanLy {
    int idQuanLy, idTaiKhoan;
    String hoTen, sdt, cCCD, email;

    public int getIdQuanLy() {
        return idQuanLy;
    }

    public void setIdQuanLy(int idQuanLy) {
        this.idQuanLy = idQuanLy;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCCCD() {
        return cCCD;
    }

    public void setCCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public QuanLy(int idQuanLy, String hoTen, String sdt, String cCCD, String email, int idTaiKhoan) {
        this.idQuanLy = idQuanLy;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.cCCD = cCCD;
        this.email = email;
        this.idTaiKhoan = idTaiKhoan;
    }

    public QuanLy(){

    }
}
