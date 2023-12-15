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
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.Giai;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DaGiaiAdapter extends ArrayAdapter {

    Context context;

    int layoutItem;

    ArrayList<Giai> lsGiai = new ArrayList<>();

    CoSoSanControl csc;

    SanControl sc;

    String tenSan;

    public DaGiaiAdapter(@NonNull Context context, int resource, ArrayList<Giai> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutItem = resource;
        this.lsGiai = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Giai g = lsGiai.get(position);
        if (convertView == null) convertView = LayoutInflater.from(context).inflate(layoutItem, null);

        sc = new SanControl(context, SanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(context, CoSoSanControl.DATABASE_NAME, null, 1);

        ArrayList<CoSoSan> lsCoSoSan = csc.loadData();
        ArrayList<San> lsSan = sc.loadData();

        ImageView imgGiai = (ImageView) convertView.findViewById(R.id.imgGiai);
        Picasso.get().load("https://as1.ftcdn.net/v2/jpg/01/11/31/98/1000_F_111319813_nbnq4zcx4JWLVACHvGP8srVcbR9aJiJx.jpg").resize(120, 120).into(imgGiai);

        TextView tvTenGiai = (TextView) convertView.findViewById(R.id.tvTenGiai);
        tvTenGiai.setText(g.getTenGiai());

        TextView tvNgayThiDau = (TextView) convertView.findViewById(R.id.tvNgayThiDauGiai);
        tvNgayThiDau.setText(g.getNgayThiDau());

        TextView tvSan = (TextView) convertView.findViewById(R.id.tvTenSan);
        for(San s: lsSan){
            for (CoSoSan cs: lsCoSoSan){
                if(cs.getIdCoSoSan() == s.getIdCoSoSan() && s.getIdSan() == g.getIdSan()){
                    tenSan = cs.getTen();

                }
            }
        }
        tvSan.setText(tenSan);


        return convertView;
    }
}
