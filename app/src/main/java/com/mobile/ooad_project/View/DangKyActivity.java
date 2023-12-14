package com.mobile.ooad_project.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.ooad_project.Control.KhachHangControl;
import com.mobile.ooad_project.Control.TaiKhoanControl;
import com.mobile.ooad_project.Model.TaiKhoan;
import com.mobile.ooad_project.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DangKyActivity extends AppCompatActivity {
    TaiKhoanControl taiKhoanControl;
    KhachHangControl khachHangControl;
    SQLiteDatabase db;
    EditText edtTaiKhoan, edtMatKhau, edtTenKH, edtDiaChiKH, edtSDTKH, edtCCCDKH, edtNhapLaiMK, edtEmailKH;
    Button btnDangKy, btnDaCoTK;
    ArrayList<TaiKhoan> lsTaiKhoan = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControl();
        try {
            addEvent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addControl(){
        edtTaiKhoan = (EditText)findViewById(R.id.edtTenTaiKhoan_DangKy);
        edtMatKhau = (EditText)findViewById(R.id.edtMatKhau_DangKy);
        edtNhapLaiMK = (EditText)findViewById(R.id.edtNhapLaiMatKhau_DangKy) ;
        edtTenKH = (EditText)findViewById(R.id.edtHoVaTen_DangKy);
        edtSDTKH = (EditText)findViewById(R.id.edtSDT_DangKy);
        edtDiaChiKH = (EditText)findViewById(R.id.edtDiaChi_DangKy);
        edtCCCDKH = (EditText)findViewById(R.id.edtCCCD_DangKy);
        edtEmailKH = (EditText)findViewById(R.id.edtEmail_DangKy);
        btnDaCoTK = (Button) findViewById(R.id.btnDaCoTK_DangKy);
        btnDangKy = (Button) findViewById(R.id.btnDangKy_DangKy);
    }
    public void addEvent() throws IOException {
        taiKhoanActive();
        khachHangActive();
        adminActive();
        btnDaCoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(edtMatKhau.getText()).equals(String.valueOf(edtNhapLaiMK.getText()))) {
                    try {
                        if (String.valueOf(edtMatKhau.getText()).length() >= 8 && String.valueOf(edtMatKhau.getText()).length() <= 16) {
                            taiKhoanControl.insertData(String.valueOf(edtTaiKhoan.getText()), encodeMatKhau(String.valueOf(edtMatKhau.getText())));
                            lsTaiKhoan = taiKhoanControl.loadData();
                            khachHangControl.insertData(String.valueOf(edtTenKH.getText()), String.valueOf(edtSDTKH.getText()), String.valueOf(edtEmailKH.getText()), String.valueOf(edtCCCDKH.getText()), String.valueOf(edtDiaChiKH.getText()), lsTaiKhoan.size());
                            Toast.makeText(DangKyActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(DangKyActivity.this, DangNhapActivity.class);
                                    intent.putExtra("taikhoan",String.valueOf(edtTaiKhoan.getText()));
                                    intent.putExtra("matkhau",String.valueOf(edtMatKhau.getText()));
                                    startActivity(intent);
                                }
                            },2000);
                        } else if (String.valueOf(edtMatKhau.getText()).length() <= 8)
                            Toast.makeText(DangKyActivity.this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
                        else if (String.valueOf(edtMatKhau.getText()).length() >= 16)
                            Toast.makeText(DangKyActivity.this, "Mật khẩu quá dài", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else
                    Toast.makeText(DangKyActivity.this, "Hai mat khau khong giong nhau", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void taiKhoanActive(){
        taiKhoanControl = new TaiKhoanControl(getApplicationContext(), TaiKhoanControl.DATABASE_NAME, null, 1);
        taiKhoanControl.onCreate(db);

    }

    public void adminActive() throws IOException {
        taiKhoanControl.insertAdminData(1, "admin", encodeMatKhau("admin12"));
    }
    public void khachHangActive(){
        khachHangControl = new KhachHangControl(getApplicationContext(), TaiKhoanControl.DATABASE_NAME, null, 1);
        khachHangControl.onCreate(db);
    }
    public String encodeMatKhau(String matkhau) throws IOException {
        InputStream in = getResources().getAssets().open("codePassword");
        int size = in.available();
        byte[] buffer = new byte[size];
        in.read(buffer);
        in.close();
        String text = new String(buffer);
        String encodePassword = "";
        String[] codePassword = text.split("\r\n");
        for(int i = 0; i < matkhau.length(); i++){
            for (int j = 0; j < codePassword.length; j++){
                String charCode = codePassword[j];
                if(matkhau.charAt(i) == charCode.charAt(0)){
                    if (matkhau.charAt(i) != 'a' && matkhau.charAt(i) != 'b' && matkhau.charAt(i) != 'c')
                        encodePassword += charCode.charAt(4) + "" + charCode.charAt(5);
                    else
                        encodePassword += charCode.charAt(4);
                    break;
                }
            }
        }
        return encodePassword;
    }
}