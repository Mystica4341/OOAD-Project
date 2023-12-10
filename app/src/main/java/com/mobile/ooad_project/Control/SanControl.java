package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.San;

public class SanControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    public static final String TABLE_NAME = "San";
    public static String IDSAN = "id";
    private static final String TINHTRANGSAN = "tinhtrangsan";
    private static final String LOAICO = "loaico";
    private static final String LOAISAN = "loaisan";
    private static final String IDCOSOSAN = "idcososan";

    public SanControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + IDSAN +" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," + TINHTRANGSAN +" INTEGER NOT NULL CHECK ("+TINHTRANGSAN+" >=0 AND "+TINHTRANGSAN+" <=1)," + LOAISAN +" INTEGER NOT NULL CHECK ("+LOAISAN+" = 5 OR "+LOAISAN+" = 7), "+LOAICO +" INTEGER NOT NULL CHECK ("+LOAICO+" >=0 AND "+LOAICO+" <=1)," + IDCOSOSAN +" INTEGER NOT NULL, FOREIGN KEY("+IDCOSOSAN+") REFERENCES "+CoSoSanControl.TABLE_NAME+"("+CoSoSanControl.IDCOSOSAN+"));";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idSan, int tinhTrangSan, int loaiSan, int loaiCo, int idCoSoSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDSAN,idSan);
        value.put(TINHTRANGSAN, tinhTrangSan);
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
        value.put(TINHTRANGSAN, new_San.getTinhTrangSan());
        value.put(LOAISAN,new_San.getLoaiSan());
        value.put(LOAICO, new_San.getLoaiCo());
        value.put(IDCOSOSAN,new_San.getIdCoSoSan());
        db.update(TABLE_NAME, value, IDSAN = "?",new String[]{String.valueOf(old_San.getIdSan())});
        db.close();
    }
    public void deleteData(int idSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDSAN + " =?",
                new String[]{String.valueOf(idSan)});
        db.close();
    }
}
