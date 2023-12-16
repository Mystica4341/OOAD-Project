package com.mobile.ooad_project.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.DatSan;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

public class NhatKyDatSanAdapter extends ArrayAdapter {
    Context context;
    int layout;
    ArrayList<DatSan> lstDatSan;
    TextView tvTen, tvNgayDat;
    CoSoSanControl csc;
    SanControl sc;
    public NhatKyDatSanAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DatSan> data) {
        super(context, resource, data);
        this.context = context;
        this.layout = resource;
        this.lstDatSan = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        DatSan ds = lstDatSan.get(position);
        if(convertView == null) convertView = LayoutInflater.from(context).inflate(layout,null);
        tvTen = convertView.findViewById(R.id.tvTenCoSoSan_NK);
        sc = new SanControl(context, SanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(context, CoSoSanControl.DATABASE_NAME, null, 1);

        ArrayList<CoSoSan> lsCoSoSan = csc.loadData();
        ArrayList<San> lsSan = sc.loadData();
        for (San s: lsSan)
            for (CoSoSan css: lsCoSoSan)
                if (ds.getIdSan() == s.getIdSan())
                    if (s.getIdCoSoSan() == css.getIdCoSoSan())
                        tvTen.setText(css.getTen());
        tvNgayDat = convertView.findViewById(R.id.tvNgayDat);
        tvNgayDat.setText(ds.getNgayDat());
        return super.getView(position, convertView, parent);
    }
}
