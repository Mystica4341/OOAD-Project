package com.mobile.ooad_project.Model;

public class HoanTien {
    String idHoanTien, idKhach, idSan, soTien, tinhTrang;

    public String getIdHoanTien() {
        return idHoanTien;
    }

    public void setIdHoanTien(String idHoanTien) {
        this.idHoanTien = idHoanTien;
    }

    public String getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(String idKhach) {
        this.idKhach = idKhach;
    }

    public String getIdSan() {
        return idSan;
    }

    public void setIdSan(String idSan) {
        this.idSan = idSan;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public HoanTien(String idHoanTien, String idKhach, String idSan, String soTien, String tinhTrang) {
        this.idHoanTien = idHoanTien;
        this.idKhach = idKhach;
        this.idSan = idSan;
        this.soTien = soTien;
        this.tinhTrang = tinhTrang;
    }

    public HoanTien(){

    }
}
