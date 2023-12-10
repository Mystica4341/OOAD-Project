package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.TaiKhoan;

public class TaiKhoanControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.example.dbsqlite/database/projectooad.db";
    public static final String TABLE_NAME = "TaiKhoan";
    public static String IDTAIKHOAN = "id";
    private static final String TAIKHOAN = "tengiai";
    private static final String MATKHAU = "idSan";

    public TaiKhoanControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + IDTAIKHOAN +" INTERGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," + TAIKHOAN +" TEXT NOT NULL," + MATKHAU +" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idTK, String taiKhoan, String matKhau){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDTAIKHOAN,idTK);
        value.put(TAIKHOAN,taiKhoan);
        value.put(MATKHAU,matKhau);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(TaiKhoan old_TK, TaiKhoan new_TK){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDTAIKHOAN,new_TK.getIdTaiKhoan());
        value.put(TAIKHOAN, new_TK.getTaiKhoan());
        value.put(MATKHAU, new_TK.getMatKhau());
        db.update(TABLE_NAME, value, IDTAIKHOAN = "?",new String[]{String.valueOf(old_TK.getIdTaiKhoan())});
        db.close();
    }
    public void deleteData(int idTK){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDTAIKHOAN + " =?",
                new String[]{String.valueOf(idTK)});
        db.close();
    }
}
