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

import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CoSoSanAdapter extends ArrayAdapter {

    ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();

    int layoutItem;

    Context context;
    public CoSoSanAdapter(@NonNull Context context, int resource, ArrayList<CoSoSan> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutItem = resource;
        this.lsCoSoSan = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CoSoSan css = lsCoSoSan.get(position);
        if (convertView == null) convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        TextView tvTenCoSo = convertView.findViewById(R.id.txtTenSan_Quanlycososan);
        tvTenCoSo.setText(css.getTen());

        TextView tvDiaChi = convertView.findViewById(R.id.txtDiacChi_Quanlycososan);
        tvDiaChi.setText(css.getDiachi());

        ImageView imgCoSo = convertView.findViewById(R.id.imgCSS);
        Picasso.get().load(css.getHinhAnh()).resize(120, 120).into(imgCoSo);

        return convertView;
    }
}
