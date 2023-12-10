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

import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.KhachHang;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

public class GiaoHuuAdapter extends ArrayAdapter {

    Context context;

    int layoutItem;

    ArrayList<GiaoHuu> lsGiaoHuu = new ArrayList<>();
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
        KhachHang khachHang = new KhachHang();
        if (convertView == null) convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        ImageView imgSan = (ImageView) convertView.findViewById(R.id.imgSan);
        //Missing hàm push ảnh lên

        TextView tvKhachA = (TextView) convertView.findViewById(R.id.tvTenDoiBong);
        tvKhachA.setText(giaoHuu.getIdKhachA()); //Note lấy tên của khách A gán vào

        TextView tvNgayThiDau = (TextView) convertView.findViewById(R.id.tvNgayThiDau);
        tvNgayThiDau.setText(giaoHuu.getNgayDaGiaoHuu());

        TextView tvSan = (TextView) convertView.findViewById(R.id.tvSan);
        tvSan.setText(giaoHuu.getIdSan()); //Note lấy tên của Sân gắn vào

        return convertView;
    }
}
