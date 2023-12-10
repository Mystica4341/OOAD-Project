package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.HoanTien;

public class HoanTienControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    private static final String TABLE_NAME = "HoanTien";
    private static String IDHOANTIEN = "id";
    private static final String SOTIENHOAN = "sotienhoan";
    private static final String TINHTRANG = "tinhtrang";
    private static final String IDKHACHHANG = "idkhachhang";
    private static final String IDSAN = "idSan";

    public HoanTienControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE "+TABLE_NAME+"("+IDHOANTIEN+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," + SOTIENHOAN +" INTEGER NOT NULL," + TINHTRANG +" INTEGER NOT NULL DEFAULT 0 CHECK ("+TINHTRANG+" >=0 AND "+TINHTRANG+" <=1)," + IDKHACHHANG +" INTEGER NOT NULL," + IDSAN + " INTEGER NOT NULL, FOREIGN KEY("+IDSAN+") REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"), FOREIGN KEY("+IDKHACHHANG+") REFERENCES "+KhachHangControl.TABLE_NAME+"("+KhachHangControl.IDKHACHHANG+"))";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idHoanTien, int soTienHoan, int tinhTrang, int idKhachHang, int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDHOANTIEN, idHoanTien);
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
        db.update(TABLE_NAME, value, IDHOANTIEN = "?",new String[]{String.valueOf(old_HT.getIdHoanTien())});
        db.close();
    }
    public void deleteData(int idHoanTien){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME,  IDHOANTIEN + " =?",
                new String[]{String.valueOf(idHoanTien)});
        db.close();
    }
}
