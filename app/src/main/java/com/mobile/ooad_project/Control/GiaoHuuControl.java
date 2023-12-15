package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.Model.TaiKhoan;

import java.io.IOException;
import java.util.ArrayList;


public class GiaoHuuControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    private static final String TABLE_NAME = "GiaoHuu";
    private static String IDTRANGIAOHUU = "id";
    private static final String NGAYDAGIAOHUU = "ngaydagiaohuu";
    private static final String IDKHACHA = "IDKHACHA";
    private static final String IDKHACHB = "IDKHACHB";
    private static final String IDSAN = "idSan";

    public GiaoHuuControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+IDTRANGIAOHUU+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + NGAYDAGIAOHUU +" TEXT NOT NULL," + IDKHACHA +" INTEGER NOT NULL REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+")," + IDKHACHB +" INTEGER REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+")," + IDSAN + " INTEGER NOT NULL REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"))";
        db.execSQL(sql);
        db.close();
    }

    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES ( 1,'19/08/2023', 2, null, 1)";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES ( 2,'24/12/2023', 2, null, 7)";
        db.execSQL(sql2);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String ngayDaGiaoHuu, int idKhachA, int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(NGAYDAGIAOHUU, ngayDaGiaoHuu);
        value.put(IDKHACHA, idKhachA);
        value.put(IDSAN, idSan);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(GiaoHuu old_GH, GiaoHuu new_GH){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDTRANGIAOHUU, new_GH.getIdTranGiaoHuu());
        value.put(NGAYDAGIAOHUU, new_GH.getNgayDaGiaoHuu());
        value.put(IDKHACHA, new_GH.getIdKhachA());
        value.put(IDKHACHB, new_GH.getIdKhachB());
        value.put(IDSAN, new_GH.getIdSan());
        db.update(TABLE_NAME, value, IDTRANGIAOHUU = "?",new String[]{String.valueOf(old_GH.getIdTranGiaoHuu())});
        db.close();
    }
    public void deleteData(int idTranGiaoHuu){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,  IDTRANGIAOHUU + " =?",
                new String[]{String.valueOf(idTranGiaoHuu)});
        db.close();
    }

    public ArrayList<GiaoHuu> loadData() {
        java.util.ArrayList<com.mobile.ooad_project.Model.GiaoHuu> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            com.mobile.ooad_project.Model.GiaoHuu tk = new com.mobile.ooad_project.Model.GiaoHuu();
            tk.setIdSan(cursor.getInt(0));
            tk.setNgayDaGiaoHuu(cursor.getString(1));
            tk.setIdKhachA(cursor.getInt(2));
            tk.setIdKhachB(cursor.getInt(3));
            tk.setIdSan(cursor.getInt(4));
            result.add(tk);
        } while (cursor.moveToNext());
        return result;
    }
}
