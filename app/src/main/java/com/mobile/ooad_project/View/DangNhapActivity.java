package com.mobile.ooad_project.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.ooad_project.Control.TaiKhoanControl;
import com.mobile.ooad_project.Model.TaiKhoan;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

public class DangNhapActivity extends AppCompatActivity {

    SQLiteDatabase db;

    EditText edtUser, edtPassword;

    Button btnLogin;

    TaiKhoanControl control = new TaiKhoanControl(this);

    ArrayList<TaiKhoan> lsTaiKhoan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        control.onCreate(db);
        control.initData();
//        lsTaiKhoan = control.loadData();
        addControl();
        addEvent();
    }

    public void addControl(){
        edtPassword = (EditText) findViewById(R.id.editTextMK);
        edtUser = (EditText) findViewById(R.id.editTextUser);
        btnLogin = (Button) findViewById(R.id.btnDangNhap);
    }

    public void addEvent(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(control.checkTaiKhoan(String.valueOf(edtUser.getText()),String.valueOf(edtPassword.getText()),lsTaiKhoan)){
//                    Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
//                else
//                    Toast.makeText(DangNhapActivity.this,"Wrong Password or Username",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}