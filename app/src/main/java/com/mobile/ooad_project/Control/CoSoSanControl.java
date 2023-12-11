package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.TaiKhoan;

import java.util.ArrayList;

public class CoSoSanControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
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

    public CoSoSanControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "+IDCOSOSAN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " + TENCOSOSAN + " TEXT NOT NULL, "+DIACHICOSOSAN+" TEXT NOT NULL, " + SDTCOSOSAN +" TEXT, "+SOLUONGSAN+" INTEGER NOT NULL, "+MOTACOSOSAN+" TEXT, "+HINHANHCOSOSAN+" TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }

    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        String sql = "INSERT OR IGNORE INTO " + TABLE_NAME + " VALUES ( 1, 'San 367', '1165 Hoang Hoa Tham, Phuong 13, Quan Tan Binh', '0834596596', 10, 'San Dep Nhat Viet Nam',  'data:image/jpeg;base64,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')";
        db.execSQL(sql);
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
    public ArrayList<CoSoSan> loadData() {
        ArrayList<CoSoSan> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            CoSoSan css = new CoSoSan();
            css.setIdCoSoSan(cursor.getInt(0));
            css.setTen(cursor.getString(1));
            css.setDiachi(cursor.getString(2));
            css.setSdt(cursor.getString(3));
            css.setSoLuongSan(cursor.getInt(4));
            css.setMoTa(cursor.getString(5));
            css.setHinhAnh(cursor.getString(6));
            result.add(css);
        } while (cursor.moveToNext());
        return result;
    }
}
