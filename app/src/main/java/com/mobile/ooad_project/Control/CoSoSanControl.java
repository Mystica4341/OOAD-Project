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
    public static final String TENCOSOSAN = "tencososan";
    private static final String DIACHICOSOSAN = "diachicososan";
    private static final String SDTCOSOSAN = "sdtCoSoSan";
    private static final String SOLUONGSAN = "soluongsan";
    private static final String MOTACOSOSAN = "motacososan";
    private static final String HINHANHCOSOSAN = "hinhanhcososan";
    private static final String GIASAN5 = "giasan5";
    private static final String GIASAN7 = "giasan7";

    public CoSoSanControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "+IDCOSOSAN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + TENCOSOSAN + " TEXT NOT NULL, "+DIACHICOSOSAN+" TEXT NOT NULL, " + SDTCOSOSAN +" TEXT, "+SOLUONGSAN+" INTEGER NOT NULL, "+MOTACOSOSAN+" TEXT, "+HINHANHCOSOSAN+" TEXT NOT NULL," + GIASAN5 + "INTEGER," + GIASAN7 + "INTERGER)";
        db.execSQL(sql);
        db.close();
    }

    public void initData(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME
                + " VALUES ( 1, 'San 367', '1165 Hoang Hoa Tham, Phuong 13, Quan Tan Binh', '0834596596', 10, 'San Dep Nhat Viet Nam', " +
                "'https://cdn.alongwalk.info/vn/wp-content/uploads/2022/06/09193838/image-danh-sach-san-bong-da-o-quan-tan-binh-165475311721151.jpg', 100000, 350000)";
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME
                + " VALUES ( 2, 'San K34', '65 Bach Dang, Phuong 2, Quan Tan Binh', '0908787798', 4, 'San k34 co day du tien nghi de to chuc giai cho San 5 va 7', " +
                "'https://global-uploads.webflow.com/60af8c708c6f35480d067652/622cc136def1b0d0de4e6e54_screenshot_1647099862.png', 90000, 320000)";
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME
                + " VALUES ( 3, 'San Ong Bau', '253 Vuon Lai, An Phu Dong, Quan 12', '0908999798', 6, 'Keo san 5 san 7', " +
                "'https://lh3.googleusercontent.com/p/AF1QipPxEewl5yqYBGXS7sPn_y_Ub_Gk6l4FQYEgZ1zX=s680-w680-h510', 120000, 300000)";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
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
            css.setGiaSan5(cursor.getInt(7));
            css.setGiaSan7(cursor.getInt(8));
            result.add(css);
        } while (cursor.moveToNext());
        return result;
    }
}
