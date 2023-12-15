package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.DatSan;
import com.mobile.ooad_project.Model.HoanTien;

public class DatSanControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    private static final String TABLE_NAME = "DatSan";
    private static String IDDATSAN = "id";
    private static final String IDKHACHHANG = "idkhachhang";
    private static final String IDSAN = "idsan";
    private static final String TINHTRANG = "tinhtrang";
    private static final String NGAYDAT = "ngaydat";
    private static final String GIODAT = "giodat";
    private static final String THOIGIANDAT = "thoigiandat";

    public DatSanControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+IDDATSAN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + NGAYDAT  +" TEXT NOT NULL," +GIODAT+" TEXT NOT NULL, " +THOIGIANDAT+ " TEXT NOT NULL, " + TINHTRANG +" INTEGER NOT NULL,"  + IDKHACHHANG +" INTEGER NOT NULL REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+")," + IDSAN + " INTEGER NOT NULL REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"))";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String ngayDat, String gioDat, String thoiGianDat, int tinhTrang, int idKhachHang, int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(NGAYDAT, ngayDat);
        value.put(GIODAT, gioDat);
        value.put(THOIGIANDAT, thoiGianDat);
        value.put(TINHTRANG, tinhTrang);
        value.put(IDKHACHHANG, idKhachHang);
        value.put(IDSAN, idSan);
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
}
