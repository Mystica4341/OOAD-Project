package com.mobile.ooad_project.Control;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobile.ooad_project.Model.TaiKhoan;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaiKhoanControl extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projectooad";
    private static final int DATABASE_VERSION = 1;
    @SuppressLint("SdCardPath")
    public static final String PATH = "/data/data/com.mobile.ooad_project/database/projectooad.db";
    public static final String TABLE_NAME = "TaiKhoan";
    public static String IDTAIKHOAN = "id";
    private static final String TAIKHOAN = "taikhoan";
    private static final String MATKHAU = "matkhau";

    public TaiKhoanControl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + IDTAIKHOAN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," + TAIKHOAN + " TEXT NOT NULL," + MATKHAU + " TEXT NOT NULL)";
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String taiKhoan, String matKhau) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(TAIKHOAN, taiKhoan);
        value.put(MATKHAU, matKhau);
        db.insert(TABLE_NAME, null, value);
        db.close();
    }

    public void updateData(TaiKhoan old_TK, TaiKhoan new_TK) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues value = new ContentValues();
        value.put(IDTAIKHOAN, new_TK.getIdTaiKhoan());
        value.put(TAIKHOAN, new_TK.getTaiKhoan());
        value.put(MATKHAU, new_TK.getMatKhau());
        db.update(TABLE_NAME, value, IDTAIKHOAN = "?", new String[]{String.valueOf(old_TK.getIdTaiKhoan())});
        db.close();
    }

    public void deleteData(int idTK) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.OPEN_READWRITE);
        db.delete(TABLE_NAME, IDTAIKHOAN + " =?",
                new String[]{String.valueOf(idTK)});
        db.close();
    }

    public boolean checkTaiKhoan(String taikhoan, String matkhau, Context context) throws IOException {
        ArrayList<TaiKhoan> lsTaiKhoan = loadData();
        for (TaiKhoan a : lsTaiKhoan) {
            if (taikhoan.equals(a.getTaiKhoan()) && matkhau.equals(decodeMatKhau(a.getMatKhau(), context)))
                return true;
            else
                return false;
        }
        return false;
    }

    public ArrayList<TaiKhoan> loadData() {
        ArrayList<TaiKhoan> result = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        do {
            TaiKhoan tk = new TaiKhoan();
            tk.setIdTaiKhoan(String.valueOf(cursor.getInt(0)));
            tk.setTaiKhoan(cursor.getString(1));
            tk.setMatKhau(cursor.getString(2));
            result.add(tk);
        } while (cursor.moveToNext());
        return result;
    }


    public String decodeMatKhau(String matkhau, Context context) throws IOException {
        InputStream in = context.getResources().getAssets().open("codePassword");
        int size = in.available();
        byte[] buffer = new byte[size];
        in.read(buffer);
        in.close();
        String text = new String(buffer);
        String decodePassword = "";
        String[] codePassword = text.split("\r\n");
        for (int i = 0; i < matkhau.length(); i++) {
            String password = String.valueOf(matkhau.charAt(i));
            for (int j = 0; j < codePassword.length; j++) {
                String charCode = codePassword[j];
                if (Integer.parseInt(password) < 5) {
                    password += String.valueOf(matkhau.charAt(i + 1));
                    j = 2;
                    i += 1;
                    continue;
                } else {
                    if (password.length() < 2) {
                        if (password.equals(String.valueOf(charCode.charAt(4)))) {
                            decodePassword += charCode.charAt(0);
                            break;
                        }
                    } else {
                        if (password.equals(charCode.charAt(4) + "" + charCode.charAt(5))) {
                            decodePassword += charCode.charAt(0);
                            break;
                        }
                    }
                }
            }
        }
        return decodePassword;
    }
}
