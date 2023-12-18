package com.mobile.ooad_project.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.DatSanControl;
import com.mobile.ooad_project.Control.GiaiControl;
import com.mobile.ooad_project.Control.GiaoHuuControl;
import com.mobile.ooad_project.Control.HoanTienControl;
import com.mobile.ooad_project.Control.KhachHangControl;
import com.mobile.ooad_project.Control.QuanLyControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Control.TaiKhoanControl;
import com.mobile.ooad_project.Model.KhachHang;
import com.mobile.ooad_project.R;


import java.io.IOException;
import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {
    public static int idKH;

    SQLiteDatabase db;

    EditText edtUser, edtPassword;

    Button btnLogin, btnLoginWF, btnLoginWG;
    TextView tvDangKy, tvQuenMK;

    TaiKhoanControl taiKhoanControl;
    CoSoSanControl csc;
    SanControl sc;
    GiaoHuuControl ghc;
    GiaiControl gc;
    QuanLyControl qlc;
    HoanTienControl htc;
    DatSanControl dsc;
    KhachHangControl khachHangControl;
    ArrayList<KhachHang> accountInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControl();
        addEvent();
        Intent intent = getIntent();
        edtUser.setText(intent.getStringExtra("taikhoan"));
        edtPassword.setText(intent.getStringExtra("matkhau"));
    }

    public void addControl(){
        edtPassword = (EditText) findViewById(R.id.editTextMK);
        edtUser = (EditText) findViewById(R.id.editTextUser);
        btnLogin = (Button) findViewById(R.id.btnDangNhap);
        btnLoginWF = (Button) findViewById(R.id.btnFacebook);
        btnLoginWG = (Button) findViewById(R.id.btnGoogle);
        tvDangKy = (TextView)findViewById(R.id.tvDangKyDN);
        tvQuenMK = (TextView)findViewById(R.id.tvQuenMK);
    }

    public void addEvent(){
        dbActive();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try {
                        if(taiKhoanControl.checkTaiKhoan(String.valueOf(edtUser.getText()),String.valueOf(edtPassword.getText()),getApplicationContext())){
                            Toast.makeText(DangNhapActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            accountInfo = taiKhoanControl.loadTaiKhoan(String.valueOf(edtUser.getText()),String.valueOf(edtPassword.getText()),getApplicationContext());
                            for (KhachHang kh: accountInfo)
                                idKH = kh.getIdKhach();
                            TaiKhoanNhatKyFrag.accountInfo = accountInfo;
                            String admin = String.valueOf(edtUser.getText());
                            String adminPassword = String.valueOf(edtPassword.getText());
                            if (admin.equals("admin") && adminPassword.equals("admin12")){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(DangNhapActivity.this, AdminAvtivity.class);
                                        startActivity(intent);
                                    }
                                },1000);
                            }else {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                },1000);
                            }
                        } else
                            Toast.makeText(DangNhapActivity.this,"Wrong Password or Username",Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, QuenMKActivity.class);
                startActivity(intent);
            }
        });
    }
    public void dbActive(){
        taiKhoanControl = new TaiKhoanControl(getApplicationContext(), TaiKhoanControl.DATABASE_NAME, null, 1);
        taiKhoanControl.onCreate(db);
        csc = new CoSoSanControl(getApplicationContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        csc.onCreate(db);
        sc = new SanControl(getApplicationContext(), SanControl.DATABASE_NAME, null, 1);
        sc.onCreate(db);
        ghc = new GiaoHuuControl(getApplicationContext(), GiaoHuuControl.DATABASE_NAME, null, 1);
        ghc.onCreate(db);
        khachHangControl = new KhachHangControl(getApplicationContext(), TaiKhoanControl.DATABASE_NAME, null, 1);
        khachHangControl.onCreate(db);
        gc = new GiaiControl(getApplicationContext(), GiaiControl.DATABASE_NAME, null, 1);
        gc.onCreate(db);
        qlc = new QuanLyControl(getApplicationContext(), QuanLyControl.DATABASE_NAME, null, 1);
        qlc.onCreate(db);
        htc = new HoanTienControl(getApplicationContext(), HoanTienControl.DATABASE_NAME, null, 1);
        htc.onCreate(db);
        dsc = new DatSanControl(getApplicationContext(), DatSanControl.DATABASE_NAME,null,1);
        dsc.onCreate(db);
        csc.initData();
        sc.initData();
    }
}