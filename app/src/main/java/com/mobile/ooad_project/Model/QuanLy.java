package com.mobile.ooad_project.Model;

public class QuanLy {
    String idQuanLy, hoTen, sdt, cCCD, email, idTaiKhoan;

    public String getIdQuanLy() {
        return idQuanLy;
    }

    public void setIdQuanLy(String idQuanLy) {
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

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public QuanLy(String idQuanLy, String hoTen, String sdt, String cCCD, String email, String idTaiKhoan) {
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
