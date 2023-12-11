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

import com.mobile.ooad_project.Control.TaiKhoanControl;
import com.mobile.ooad_project.Model.TaiKhoan;
import com.mobile.ooad_project.R;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText edtUser, edtPassword;

    Button btnLogin, btnLoginWF, btnLoginWG;
    TextView tvDangKy, tvQuenMK;

    TaiKhoanControl taiKhoanControl;


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
        taiKhoanActive();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(taiKhoanControl.checkTaiKhoan(String.valueOf(edtUser.getText()),String.valueOf(edtPassword.getText()),getApplicationContext())){
                        Toast.makeText(DangNhapActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        },2000);
                    }
                    else
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
    public void taiKhoanActive(){
        taiKhoanControl = new TaiKhoanControl(getApplicationContext(), TaiKhoanControl.DATABASE_NAME, null, 1);
        taiKhoanControl.onCreate(db);
    }
}