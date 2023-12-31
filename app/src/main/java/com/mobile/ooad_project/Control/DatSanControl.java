package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.DatSan;
import com.mobile.ooad_project.Model.HoanTien;

import java.util.ArrayList;

public class DatSanControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    public static final String TABLE_NAME = "DatSan";
    private static String IDDATSAN = "id";
    public static final String IDKHACHHANG = "idkhachhang";
    public static final String IDSAN = "idsan";
    private static final String TINHTRANG = "tinhtrang";
    private static final String NGAYDAT = "ngaydat";
    private static final String GIODAT = "giodat";
    private static final String THOIGIANDAT = "thoigiandat";

    private static final String TONGTIEN = "tongtien";

    public DatSanControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+IDDATSAN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + NGAYDAT  +" TEXT NOT NULL," +GIODAT+" TEXT, " +THOIGIANDAT+ " TEXT, " + TINHTRANG +" INTEGER NOT NULL,"  + IDKHACHHANG +" INTEGER NOT NULL REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+")," + IDSAN + " INTEGER NOT NULL REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"), " + TONGTIEN + " INTEGER)";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String ngayDat, String gioDat, String thoiGianDat, int tinhTrang, int idKhachHang, int idSan, int tongtien){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(NGAYDAT, ngayDat);
        value.put(GIODAT, gioDat);
        value.put(THOIGIANDAT, thoiGianDat);
        value.put(TINHTRANG, tinhTrang);
        value.put(IDKHACHHANG, idKhachHang);
        value.put(IDSAN, idSan);
        value.put(TONGTIEN, tongtien);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(DatSan old_DS, DatSan new_DS){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDDATSAN, new_DS.getIdDatSan());
        value.put(NGAYDAT, new_DS.getNgayDat());
        value.put(GIODAT, new_DS.getGioDat());
        value.put(THOIGIANDAT, new_DS.gettGDat());
        value.put(TINHTRANG, new_DS.getTinhTrang());
        value.put(IDKHACHHANG, new_DS.getIdKhachHang());
        value.put(IDSAN, new_DS.getIdSan());
        db.update(TABLE_NAME, value, IDDATSAN = "?",new String[]{String.valueOf(old_DS.getIdDatSan())});
        db.close();
    }
    public void deleteData(int idDatSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,  IDDATSAN + " =?",
                new String[]{String.valueOf(idDatSan)});
        db.close();
    }
    public ArrayList<DatSan> loadData(){
        ArrayList<DatSan> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do{
            DatSan datSan = new DatSan();
            datSan.setIdDatSan(cursor.getInt(0));
            datSan.setNgayDat(cursor.getString(1));
            datSan.setGioDat(cursor.getString(2));
            datSan.settGDat(cursor.getInt(3));
            datSan.setTinhTrang(cursor.getInt(4));
            datSan.setIdKhachHang(cursor.getInt(5));
            datSan.setIdSan(cursor.getInt(6));
            datSan.setTongTien(cursor.getInt(7));
            result.add(datSan);
        }while(cursor.moveToNext());
        return result;
    }
    public ArrayList<DatSan> loadDataKhachHang(int id){
        ArrayList<DatSan> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME  + " WHERE " + IDKHACHHANG + " = ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        do{
            DatSan datSan = new DatSan();
            datSan.setIdDatSan(cursor.getInt(0));
            datSan.setNgayDat(cursor.getString(1));
            datSan.setGioDat(cursor.getString(2));
            datSan.settGDat(cursor.getInt(3));
            datSan.setTinhTrang(cursor.getInt(4));
            datSan.setIdKhachHang(cursor.getInt(5));
            datSan.setIdSan(cursor.getInt(6));
            datSan.setTongTien(cursor.getInt(7));
            result.add(datSan);
        }while(cursor.moveToNext());
        return result;
    }
}
