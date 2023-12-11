package com.mobile.ooad_project.Model;

public class GiaoHuu {
    int idTranGiaoHuu, idKhachA, idKhachB, idSan;
    String  ngayDaGiaoHuu;

    public int getIdTranGiaoHuu() {
        return idTranGiaoHuu;
    }

    public GiaoHuu(int idTranGiaoHuu, int idKhachA, int idKhachB, int idSan, String ngayDaGiaoHuu) {
        this.idTranGiaoHuu = idTranGiaoHuu;
        this.idKhachA = idKhachA;
        this.idKhachB = idKhachB;
        this.idSan = idSan;
        this.ngayDaGiaoHuu = ngayDaGiaoHuu;
    }

    public GiaoHuu() {
    }

    public void setIdTranGiaoHuu(int idTranGiaoHuu) {
        this.idTranGiaoHuu = idTranGiaoHuu;
    }

    public int getIdKhachA() {
        return idKhachA;
    }

    public void setIdKhachA(int idKhachA) {
        this.idKhachA = idKhachA;
    }

    public int getIdKhachB() {
        return idKhachB;
    }

    public void setIdKhachB(int idKhachB) {
        this.idKhachB = idKhachB;
    }

    public String getNgayDaGiaoHuu() {
        return ngayDaGiaoHuu;
    }

    public void setNgayDaGiaoHuu(String ngayDaGiaoHuu) {
        this.ngayDaGiaoHuu = ngayDaGiaoHuu;
    }

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }
}
