package com.mobile.ooad_project.Model;

public class San {
    String idSan, idCoSoSan, soLuongSan, tinhTrangSan, loaiCo;
    int loaiSan;

    public String getIdSan() {
        return idSan;
    }

    public void setIdSan(String idSan) {
        this.idSan = idSan;
    }

    public String getIdCoSoSan() {
        return idCoSoSan;
    }

    public void setIdCoSoSan(String idCoSoSan) {
        this.idCoSoSan = idCoSoSan;
    }

    public String getSoLuongSan() {
        return soLuongSan;
    }

    public void setSoLuongSan(String soLuongSan) {
        this.soLuongSan = soLuongSan;
    }

    public String getTinhTrangSan() {
        return tinhTrangSan;
    }

    public void setTinhTrangSan(String tinhTrangSan) {
        this.tinhTrangSan = tinhTrangSan;
    }

    public String getLoaiCo() {
        return loaiCo;
    }

    public void setLoaiCo(String loaiCo) {
        this.loaiCo = loaiCo;
    }

    public int getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(int loaiSan) {
        this.loaiSan = loaiSan;
    }

    public San(String idSan, String idCoSoSan, String soLuongSan, String tinhTrangSan, String loaiCo, int loaiSan) {
        this.idSan = idSan;
        this.idCoSoSan = idCoSoSan;
        this.soLuongSan = soLuongSan;
        this.tinhTrangSan = tinhTrangSan;
        this.loaiCo = loaiCo;
        this.loaiSan = loaiSan;
    }

    public San(){

    }
}
