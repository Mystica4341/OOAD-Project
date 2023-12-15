package com.mobile.ooad_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.R;
import com.mobile.ooad_project.View.ChiTietCoSoSanFrag;
import com.mobile.ooad_project.View.HenLichGiaoHuuFrag;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachCoSoSanAdapter extends ArrayAdapter {

    Context context;

    ArrayList<CoSoSan> lsCoSoSan;

    int layoutItem;

    public DanhSachCoSoSanAdapter(@NonNull Context context, int resource, ArrayList<CoSoSan> data) {
        super(context, resource, data);
        this.context = context;
        this.lsCoSoSan = data;
        this.layoutItem = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CoSoSan cs = lsCoSoSan.get(position);
        if (convertView == null) convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        ImageView imgCoSoSan = convertView.findViewById(R.id.imgCoSoSan);
        Picasso.get().load(cs.getHinhAnh()).resize(120, 120).into(imgCoSoSan);

        TextView tvTenCoSoSan = convertView.findViewById(R.id.tvTenCS);
        tvTenCoSoSan.setText(cs.getTen());

        TextView tvDiaChi = convertView.findViewById(R.id.tvDiachiTimKiem);
        tvDiaChi.setText(cs.getDiachi());

        TextView tvSDT = convertView.findViewById(R.id.tvSDT);
        tvSDT.setText(cs.getSdt());

        TextView tvGiaTien = convertView.findViewById(R.id.tvGiaSan);
        tvGiaTien.setText(cs.getGiaSan5() + " - " + cs.getGiaSan7() + "/giờ");
        Button btnChiTiet = convertView.findViewById(R.id.btnXemchitiet);
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChiTietCoSoSanFrag chiTietCoSoSanFrag = new ChiTietCoSoSanFrag();
                chiTietCoSoSanFrag.lsChiTietCoSoSan.add(cs);
                FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameFrag, chiTietCoSoSanFrag).commit();
            }
        });
        return convertView;
    }
}
