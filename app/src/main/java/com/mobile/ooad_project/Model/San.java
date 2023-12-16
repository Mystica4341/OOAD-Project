package com.mobile.ooad_project.Model;

public class San {
    int idSan, idCoSoSan, tinhTrangSan, loaiCo, loaiSan;

    public int getIdSan() {
        return idSan;
    }

    public void setIdSan(int idSan) {
        this.idSan = idSan;
    }

    public int getIdCoSoSan() {
        return idCoSoSan;
    }

    public void setIdCoSoSan(int idCoSoSan) {
        this.idCoSoSan = idCoSoSan;
    }


    public int getTinhTrangSan() {
        return tinhTrangSan;
    }

    public void setTinhTrangSan(int tinhTrangSan) {
        this.tinhTrangSan = tinhTrangSan;
    }

    public int getLoaiCo() {
        return loaiCo;
    }

    public void setLoaiCo(int loaiCo) {
        this.loaiCo = loaiCo;
    }

    public int getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(int loaiSan) {
        this.loaiSan = loaiSan;
    }

    public San(int idSan, int idCoSoSan, int tinhTrangSan, int loaiCo, int loaiSan) {
        this.idSan = idSan;
        this.idCoSoSan = idCoSoSan;
        this.tinhTrangSan = tinhTrangSan;
        this.loaiCo = loaiCo;
        this.loaiSan = loaiSan;
    }

    public San(){

    }
}
