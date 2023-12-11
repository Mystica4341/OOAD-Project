package com.mobile.ooad_project.Model;

public class HoanTien {
    int idHoanTien, idKhach, idSan, soTien, tinhTrang;

    public int getIdHoanTien() {
        return idHoanTien;
    }

    public void setIdHoanTien(int idHoanTien) {
        this.idHoanTien = idHoanTien;
    }

    public int getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public HoanTien(int idHoanTien, int idKhach, int idSan, int soTien, int tinhTrang) {
        this.idHoanTien = idHoanTien;
        this.idKhach = idKhach;
        this.idSan = idSan;
        this.soTien = soTien;
        this.tinhTrang = tinhTrang;
    }

    public HoanTien(){

    }
}
