package com.mobile.ooad_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.KhachHangControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Control.TaiKhoanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.KhachHang;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.Model.TaiKhoan;
import com.mobile.ooad_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiaoHuuAdapter extends ArrayAdapter {

    Context context;

    int layoutItem;

    ArrayList<GiaoHuu> lsGiaoHuu = new ArrayList<>();

    CoSoSanControl csc;

    SanControl sc;

    TaiKhoanControl tkc;

    KhachHangControl khc;

    String tenSan, tenKhach;
    public GiaoHuuAdapter(@NonNull Context context, int resource, ArrayList<GiaoHuu> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutItem = resource;
        this.lsGiaoHuu = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        GiaoHuu giaoHuu = lsGiaoHuu.get(position);


        //LoadData
        sc = new SanControl(context, SanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(context, CoSoSanControl.DATABASE_NAME, null, 1);
        tkc = new TaiKhoanControl(context, TaiKhoanControl.DATABASE_NAME, null, 1);
        khc = new KhachHangControl(context, KhachHangControl.DATABASE_NAME, null, 1);
        //attach data
        ArrayList<CoSoSan> lsCoSoSan = csc.loadData();
        ArrayList<San> lsSan = sc.loadData();
        ArrayList<KhachHang> lsKhachHang = khc.loadData();
        ArrayList<TaiKhoan> lsTaiKhoan = tkc.loadData();

        if (convertView == null) convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        ImageView imgSan = (ImageView) convertView.findViewById(R.id.imgSan);
//        for (GiaoHuu gh: lsGiaoHuu){
//
//        }

        //Check Ten Khach Hang
        TextView tvKhachA = (TextView) convertView.findViewById(R.id.tvTenDoiBong);
        for (KhachHang k: lsKhachHang){
            for (TaiKhoan tk: lsTaiKhoan){
                if (k.getIdTaiKhoan() == tk.getIdTaiKhoan()){
                    tenKhach = k.getHoTen();
                }
            }
        }
        tvKhachA.setText(tenKhach); //Note lấy tên của khách A gán vào

        TextView tvNgayThiDau = (TextView) convertView.findViewById(R.id.tvNgayThiDau);
        tvNgayThiDau.setText(giaoHuu.getNgayDaGiaoHuu());

        //Check Ten San + push hình anh
        TextView tvSan = (TextView) convertView.findViewById(R.id.tvSan);
        for(San s: lsSan){
                for (CoSoSan cs: lsCoSoSan){
                    if(cs.getIdCoSoSan() == s.getIdCoSoSan() && s.getIdSan() == giaoHuu.getIdSan()){
                        tenSan = cs.getTen();
                        Picasso.get().load(cs.getHinhAnh()).resize(120, 120).into(imgSan);
                }
            }
        }

        tvSan.setText(tenSan); //Note lấy tên của Sân gắn vào

        return convertView;
    }
}
