package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.Giai;

public class GiaiControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    private static final String TABLE_NAME = "Giai";
    private static String IDGIAI = "id";
    private static final String TENGIAI = "tengiai";
    private static final String IDSAN = "idSan";
    private static final String NGAYTHIDAU = "ngaythidau";

    public GiaiControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + IDGIAI +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + TENGIAI +" TEXT NOT NULL," + NGAYTHIDAU +" TEXT NOT NULL," + IDSAN +" INTEGER NOT NULL REFERENCES "+SanControl.TABLE_NAME+"("+SanControl.IDSAN+"));";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(String tenGiai, int idSan, String ngayThiDau){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(TENGIAI,tenGiai);
        value.put(IDSAN,idSan);
        value.put(NGAYTHIDAU,ngayThiDau);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(Giai old_Giai, Giai new_Giai){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDGIAI,new_Giai.getIdGiai());
        value.put(TENGIAI,new_Giai.getTenGiai());
        value.put(IDSAN, new_Giai.getIdSan());
        value.put(NGAYTHIDAU,new_Giai.getNgayThiDau());
        db.update(TABLE_NAME, value, IDGIAI = "?",new String[]{String.valueOf(old_Giai.getIdGiai())});
        db.close();
    }
    public void deleteData(int idGiai){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDGIAI + " =?",
                new String[]{String.valueOf(idGiai)});
        db.close();
    }
}
