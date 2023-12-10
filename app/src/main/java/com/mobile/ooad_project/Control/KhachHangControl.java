package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.KhachHang;

public class KhachHangControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    public static final String TABLE_NAME = "KhachHang";
    public static String IDKHACHHANG = "idkhachhang";
    private static final String TENKHACHHANG = "tenkhachhang";
    private static final String SDTKHACHHANG = "sdtkhachhang";
    private static final String EMAILKHACHHANG = "emailkhachhang";
    private static final String CCCD = "cccd";
    private static final String DIACHIKHACHHANG = "diachikhachhang";
    private static final String IDTAIKHOAN = "idtaikhoan";

    public KhachHangControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE "+TABLE_NAME+"("+IDKHACHHANG+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + TENKHACHHANG +" TEXT NOT NULL," + SDTKHACHHANG +" TEXT NOT NULL," + EMAILKHACHHANG +" TEXT NOT NULL," + CCCD + " TEXT NOT NULL," +DIACHIKHACHHANG+ "TEXT NOT NULL, " +IDTAIKHOAN+ "INTEGER NOT NULL, FOREIGN KEY("+IDTAIKHOAN+") REFERENCES "+TaiKhoanControl.TABLE_NAME+"("+TaiKhoanControl.IDTAIKHOAN+"))";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idKhachHang, String tenKH, String sdtKH, String emailKH, String CCCDKH, String diaChiKH, int idTaiKhoan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDKHACHHANG, idKhachHang);
        value.put(TENKHACHHANG, tenKH);
        value.put(SDTKHACHHANG, sdtKH);
        value.put(EMAILKHACHHANG, emailKH);
        value.put(CCCD, CCCDKH);
        value.put(DIACHIKHACHHANG,diaChiKH);
        value.put(IDTAIKHOAN, idTaiKhoan);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(KhachHang old_KH, KhachHang new_KH){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDKHACHHANG, new_KH.getIdKhach());
        value.put(TENKHACHHANG, new_KH.getHoTen());
        value.put(SDTKHACHHANG, new_KH.getSdt());
        value.put(EMAILKHACHHANG, new_KH.getEmail());
        value.put(CCCD, new_KH.getCCCD());
        value.put(DIACHIKHACHHANG, new_KH.getDiaChi());
        value.put(IDTAIKHOAN, new_KH.getIdTaiKhoan());
        db.update(TABLE_NAME, value, IDKHACHHANG = "?",new String[]{String.valueOf(old_KH.getIdKhach())});
        db.close();
    }
    public void deleteData(int idKH){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,  IDKHACHHANG + " =?",
                new String[]{String.valueOf(idKH)});
        db.close();
    }
}
