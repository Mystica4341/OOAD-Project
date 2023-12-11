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
        String sql = "INSERT OR IGNORE INTO " + TABLE_NAME
                + " VALUES ( 1, 'San 367', '1165 Hoang Hoa Tham, Phuong 13, Quan Tan Binh', '0834596596', 10, 'San Dep Nhat Viet Nam', 'https://cdn.discordapp.com/attachments/1084393111096131599/1166405314036375652/image.png?ex=6581bd99&is=656f4899&hm=0ca6b817fa57b658f83dc38b15f8cbe0af20edcadeaf3b681003a69588d9e101&')";

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
