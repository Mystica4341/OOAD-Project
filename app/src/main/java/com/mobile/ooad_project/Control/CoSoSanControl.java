package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.CoSoSan;

public class CoSoSanControl extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    public static final String TABLE_NAME = "CoSoSan";
    public static String IDCOSOSAN = "id";
    private static final String TENCOSOSAN = "tencososan";
    private static final String DIACHICOSOSAN = "diachicososan";
    private static final String SDTCOSOSAN = "sdtCoSoSan";
    private static final String SOLUONGSAN = "soluongsan";
    private static final String MOTACOSOSAN = "motacososan";
    private static final String HINHANHCOSOSAN = "hinhanhcososan";

    public CoSoSanControl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "+IDCOSOSAN+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + TENCOSOSAN + " TEXT NOT NULL, "+DIACHICOSOSAN+" TEXT NOT NULL, " + SDTCOSOSAN +" TEXT, "+SOLUONGSAN+" INTEGER NOT NULL, "+MOTACOSOSAN+" TEXT, "+HINHANHCOSOSAN+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertData(int idCoSoSan, String tenCoSoSan, String diaChiCoSoSan, String sdtCoSoSan, int soLuongSan, String moTaSan, String hinhAnhSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDCOSOSAN,idCoSoSan);
        value.put(TENCOSOSAN,tenCoSoSan);
        value.put(DIACHICOSOSAN,diaChiCoSoSan);
        value.put(SDTCOSOSAN,sdtCoSoSan);
        value.put(SOLUONGSAN,soLuongSan);
        value.put(MOTACOSOSAN,moTaSan);
        value.put(HINHANHCOSOSAN,hinhAnhSan);
        db.insert(TABLE_NAME,null,value);
        db.close();
    }
    public void updateData(CoSoSan old_CSS, CoSoSan new_CSS){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDCOSOSAN,new_CSS.getIdCoSoSan());
        value.put(TENCOSOSAN, new_CSS.getTen());
        value.put(DIACHICOSOSAN, new_CSS.getDiachi());
        value.put(SDTCOSOSAN, new_CSS.getSdt());
        value.put(SOLUONGSAN, new_CSS.getSoLuongSan());
        value.put(MOTACOSOSAN, new_CSS.getMoTa());
        value.put(HINHANHCOSOSAN, new_CSS.getHinhAnh());
        db.update(TABLE_NAME, value, IDCOSOSAN = "?",new String[]{String.valueOf(old_CSS.getIdCoSoSan())});
        db.close();
    }
    public void deleteData(int idCoSoSan){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDCOSOSAN + " =?",
                new String[]{String.valueOf(idCoSoSan)});
        db.close();
    }
}
