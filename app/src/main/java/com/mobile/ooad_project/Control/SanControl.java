package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.San;

import java.io.IOException;
import java.util.ArrayList;

public class SanControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    public static final String TABLE_NAME = "San";
    public static String IDSAN = "id";
    private static final String LOAICO = "loaico";
    private static final String LOAISAN = "loaisan";
    private static final String IDCOSOSAN = "idcososan";

    public SanControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + IDSAN +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + LOAISAN +" INTEGER NOT NULL, "+LOAICO +" INTEGER NOT NULL," + IDCOSOSAN +" INTEGER NOT NULL REFERENCES "+CoSoSanControl.TABLE_NAME+"("+CoSoSanControl.IDCOSOSAN+"));";
        db.execSQL(sql);
        db.close();
    }

    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        //(ID, Tinh Trang, Loai San, Loai Co, ID Co So)
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (1, 5, 1, 1)"; //loai co 1 la tu nhien, 0 la nhan tao
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (2, 5, 1, 1)";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (3, 7, 1, 1)";
        db.execSQL(sql3);
        String sql4 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (4, 5, 0, 2)"; //loai co 1 la tu nhien, 0 la nhan tao //tinh trang san 1 la available, 0 la unavailable
        db.execSQL(sql4);
        String sql5 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (5, 7, 0, 2)";
        db.execSQL(sql5);
        String sql6 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (6, 7, 1, 2)";
        db.execSQL(sql6);
        String sql7 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (7, 5, 1, 3)"; //loai co 1 la tu nhien, 0 la nhan tao //tinh trang san 1 la available, 0 la unavailable
        db.execSQL(sql7);
        String sql8 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (8, 5, 0, 3)";
        db.execSQL(sql8);
        String sql9 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (9, 7, 0, 3)";
        db.execSQL(sql9);
        String sql10 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (10, 5, 1, 3)"; //loai co 1 la tu nhien, 0 la nhan tao //tinh trang san 1 la available, 0 la unavailable
        db.execSQL(sql10);
        String sql11 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (11, 7, 1, 3)";
        db.execSQL(sql11);
        String sql12 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (12, 7, 1, 3)";
        db.execSQL(sql12);
        String sql13 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (13, 5, 1, 2)"; //loai co 1 la tu nhien, 0 la nhan tao //tinh trang san 1 la available, 0 la unavailable
        db.execSQL(sql13);
        String sql14 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (14, 7, 0, 2)";
        db.execSQL(sql14);
        String sql15 = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES (15, 7, 1, 2)";
        db.execSQL(sql15);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int loaiSan, int loaiCo, int idCoSoSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(LOAISAN,loaiSan);
        value.put(LOAICO, loaiCo);
        value.put(IDCOSOSAN,idCoSoSan);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(San old_San, San new_San){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDSAN, new_San.getIdSan());
        value.put(LOAISAN,new_San.getLoaiSan());
        value.put(LOAICO, new_San.getLoaiCo());
        value.put(IDCOSOSAN,new_San.getIdCoSoSan());
        db.delete(TABLE_NAME, IDSAN + " =?", new String[]{String.valueOf(old_San.getIdSan())});
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void deleteData(int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDSAN + " =?",
                new String[]{String.valueOf(idSan)});
        db.close();
    }

    public ArrayList<San> loadData() {
        java.util.ArrayList<com.mobile.ooad_project.Model.San> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            com.mobile.ooad_project.Model.San s = new com.mobile.ooad_project.Model.San();
            s.setIdSan(cursor.getInt(0));
            s.setLoaiSan(cursor.getInt(1));
            s.setLoaiCo(cursor.getInt(2));
            s.setIdCoSoSan(cursor.getInt(3));
            result.add(s);
        } while (cursor.moveToNext());
        return result;
    }

    public int checkSan(int idCoSoSan, int loaiSan) throws IOException {
        ArrayList<San> lsSan = loadData();
        for (San a : lsSan) {
            if (idCoSoSan == a.getIdCoSoSan() && loaiSan == a.getLoaiSan())
                return a.getIdSan();
            else
                continue;
        }
        return 0;
    }

}
