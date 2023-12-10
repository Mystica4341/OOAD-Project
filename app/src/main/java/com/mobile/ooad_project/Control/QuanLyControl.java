package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.QuanLy;

public class QuanLyControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    private static final String TABLE_NAME = "QuanLy";
    private static String IDQUANLY = "idquanly";
    private static final String TENQUANLY = "tenquanly";
    private static final String SDTQUANLY = "sdtquanly";
    private static final String EMAILQUANLY = "emailquanly";
    private static final String CCCD = "cccd";
    private static final String IDTAIKHOAN = "idtaikhoan";

    public QuanLyControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE "+TABLE_NAME+"("+IDQUANLY+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + TENQUANLY +" TEXT NOT NULL," + SDTQUANLY +" TEXT NOT NULL," + EMAILQUANLY +" TEXT NOT NULL," + CCCD + " TEXT NOT NULL," +IDTAIKHOAN+ "INTEGER NOT NULL, FOREIGN KEY("+IDTAIKHOAN+") REFERENCES "+TaiKhoanControl.TABLE_NAME+"("+TaiKhoanControl.IDTAIKHOAN+"))";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idquanly, String tenKH, String sdtKH, String emailKH, String CCCDKH, String diaChiKH, int idTaiKhoan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDQUANLY, idquanly);
        value.put(TENQUANLY, tenKH);
        value.put(SDTQUANLY, sdtKH);
        value.put(EMAILQUANLY, emailKH);
        value.put(CCCD, CCCDKH);
        value.put(IDTAIKHOAN, idTaiKhoan);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(QuanLy old_QL, QuanLy new_QL){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDQUANLY, new_QL.getIdQuanLy());
        value.put(TENQUANLY, new_QL.getHoTen());
        value.put(SDTQUANLY, new_QL.getSdt());
        value.put(EMAILQUANLY, new_QL.getEmail());
        value.put(CCCD, new_QL.getCCCD());
        value.put(IDTAIKHOAN, new_QL.getIdTaiKhoan());
        db.update(TABLE_NAME, value, IDQUANLY = "?",new String[]{String.valueOf(old_QL.getIdQuanLy())});
        db.close();
    }
    public void deleteData(int idQL){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,  IDQUANLY + " =?",
                new String[]{String.valueOf(idQL)});
        db.close();
    }
}
