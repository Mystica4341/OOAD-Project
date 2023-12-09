package com.mobile.ooad_project.Model;

public class KhachHang {
    String idKhach, hoTen, sdt, email, diaChi, cCCD, idTaiKhoan;

    public String getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(String idKhach) {
        this.idKhach = idKhach;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCCCD() {
        return cCCD;
    }

    public void setCCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    public String getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public KhachHang(String idKhach, String hoTen, String sdt, String email, String diaChi, String cCCD, String idTaiKhoan) {
        this.idKhach = idKhach;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.cCCD = cCCD;
        this.idTaiKhoan = idTaiKhoan;
    }

    public KhachHang(){

    }
}
