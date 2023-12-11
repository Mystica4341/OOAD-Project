package com.mobile.ooad_project.Model;

public class TaiKhoan {
    int idTaiKhoan;
    String taiKhoan, matKhau;

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public TaiKhoan(int idTaiKhoan, String taiKhoan, String matKhau) {
        this.idTaiKhoan = idTaiKhoan;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }

    public TaiKhoan(){

    }
}
