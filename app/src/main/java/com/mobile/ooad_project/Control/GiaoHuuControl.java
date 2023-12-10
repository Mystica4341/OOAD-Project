package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.GiaoHuu;


public class GiaoHuuControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.example.dbsqlite/database/projectooad.db";
    private static final String TABLE_NAME = "GiaoHuu";
    private static String IDTRANGIAOHUU = "id";
    private static final String NGAYDAGIAOHUU = "ngaydagiaohuu";
    private static final String IDKHACHA = "IDKHACHA";
    private static final String IDKHACHB = "IDKHACHB";
    private static final String IDSAN = "idSan";

    public GiaoHuuControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE "+TABLE_NAME+"("+IDTRANGIAOHUU+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + NGAYDAGIAOHUU +" TEXT NOT NULL," + IDKHACHA +" INTEGER NOT NULL," + IDKHACHB +" INTEGER NOT NULL," + IDSAN + " INTEGER NOT NULL, FOREIGN KEY("+IDSAN+") REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"), FOREIGN KEY("+IDKHACHA+") REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+"), FOREIGN KEY("+IDKHACHB+") REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+"))";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idTranGiaoHuu, String ngayDaGiaoHuu, int idKhachA, int idKhachB, int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDTRANGIAOHUU, idTranGiaoHuu);
        value.put(NGAYDAGIAOHUU, ngayDaGiaoHuu);
        value.put(IDKHACHA, idKhachA);
        value.put(IDKHACHB, idKhachB);
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
}
