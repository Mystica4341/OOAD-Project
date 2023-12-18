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
import com.mobile.ooad_project.Control.DatSanControl;
import com.mobile.ooad_project.Control.GiaoHuuControl;
import com.mobile.ooad_project.Control.HoanTienControl;
import com.mobile.ooad_project.Control.KhachHangControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.DatSan;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.HoanTien;
import com.mobile.ooad_project.Model.KhachHang;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

public class HoanTienAdapter extends ArrayAdapter {

    Context context;

    int layoutItem;

    ArrayList<HoanTien> lsHoanTien = new ArrayList<>();

    DatSanControl dcs;

    CoSoSanControl csc;

    HoanTienControl htc;

    KhachHangControl khc;

    SanControl sc;

    public HoanTienAdapter(@NonNull Context context, int resource, ArrayList<HoanTien> data) {
        super(context, resource, data);
        this.context = context;
        this.layoutItem = resource;
        this.lsHoanTien = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HoanTien hoanTien = lsHoanTien.get(position);

        if (convertView == null) convertView = LayoutInflater.from(context).inflate(layoutItem, null);
        dcs = new DatSanControl(getContext(), DatSanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        htc = new HoanTienControl(getContext(), HoanTienControl.DATABASE_NAME, null, 1);
        khc = new KhachHangControl(getContext(), KhachHangControl.DATABASE_NAME, null, 1);
        sc = new SanControl(getContext(), SanControl.DATABASE_NAME, null, 1);

        ArrayList<DatSan> lsDatSan = new ArrayList<>();
        ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();
        ArrayList<San> lsSC = new ArrayList<>();
        ArrayList<KhachHang> lsKhachHang = new ArrayList<>();

        try{
            lsDatSan = dcs.loadData();
            lsCoSoSan = csc.loadData();
            lsSC = sc.loadData();
            lsKhachHang = khc.loadData();

        }catch (Exception e){

        }
        TextView tvSoTien = convertView.findViewById(R.id.tvTien_HoanTien);
        if (hoanTien.getSoTien() != 0) {
            tvSoTien.setText(String.valueOf(hoanTien.getSoTien()));
            TextView tvTenKhach = convertView.findViewById(R.id.tvTenKhach_HoanTien);
            for (KhachHang kh : lsKhachHang) {
                if (kh.getIdKhach() == hoanTien.getIdKhach()) {
                    tvTenKhach.setText(kh.getHoTen());
                }
            }


            TextView tvSan = convertView.findViewById(R.id.tvSan_HoanTien);
            for (CoSoSan cs: lsCoSoSan) {
                for (San s : lsSC) {
                    if (s.getIdSan() == hoanTien.getIdSan() && s.getIdCoSoSan() == cs.getIdCoSoSan()) {
                        tvSan.setText(cs.getTen() + " - Sân " + s.getLoaiSan());
                    }
                }
            }

            TextView tvTrangThai = convertView.findViewById(R.id.tvTrangThai);
            if (hoanTien.getTinhTrang() == 0) tvTrangThai.setText("Chờ xử lý");
            else if (hoanTien.getTinhTrang() == 1) tvTrangThai.setText("Thành công");
            else if (hoanTien.getTinhTrang() == 2) tvTrangThai.setText("Đã hủy");
        }
        else {

        }
        return convertView;
    }
}
