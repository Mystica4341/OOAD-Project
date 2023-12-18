package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.HoanTien;

import java.util.ArrayList;

public class HoanTienControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    private static final String TABLE_NAME = "HoanTien";
    private static String IDHOANTIEN = "id";
    private static final String SOTIENHOAN = "sotienhoan";
    private static final String TINHTRANG = "tinhtrang";  //0 la Dang Xu Ly; 1 la Thanh Cong, 2 la Da Huy
    private static final String IDKHACHHANG = "idkhachhang";
    private static final String IDSAN = "idSan";

    public HoanTienControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+IDHOANTIEN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + SOTIENHOAN +" INTEGER NOT NULL," + TINHTRANG +" INTEGER NOT NULL," + IDKHACHHANG +" INTEGER NOT NULL REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+")," + IDSAN + " INTEGER NOT NULL REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"))";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int soTienHoan, int tinhTrang, int idKhachHang, int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(SOTIENHOAN, soTienHoan);
        value.put(TINHTRANG, tinhTrang);
        value.put(IDKHACHHANG, idKhachHang);
        value.put(IDSAN, idSan);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(HoanTien old_HT, HoanTien new_HT){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDHOANTIEN, new_HT.getIdHoanTien());
        value.put(SOTIENHOAN, new_HT.getSoTien());
        value.put(TINHTRANG, new_HT.getTinhTrang());
        value.put(IDKHACHHANG, new_HT.getIdKhach());
        value.put(IDSAN, new_HT.getIdSan());
        db.delete(TABLE_NAME, IDHOANTIEN + " =?", new String[]{String.valueOf(old_HT.getIdHoanTien())});
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void deleteData(int idHoanTien){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,  IDHOANTIEN + " =?",
                new String[]{String.valueOf(idHoanTien)});
        db.close();
    }

    public ArrayList<HoanTien> loadData() {
        java.util.ArrayList<com.mobile.ooad_project.Model.HoanTien> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            com.mobile.ooad_project.Model.HoanTien tk = new com.mobile.ooad_project.Model.HoanTien();
            tk.setIdHoanTien(cursor.getInt(0));
            tk.setSoTien(cursor.getInt(1));
            tk.setTinhTrang(cursor.getInt(2));
            tk.setIdKhach(cursor.getInt(3));
            tk.setIdSan(cursor.getInt(4));
            result.add(tk);
        } while (cursor.moveToNext());
        return result;
    }
}
