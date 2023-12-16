package com.mobile.ooad_project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiTietCoSoSanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiTietCoSoSanFrag extends Fragment {

    public ArrayList<CoSoSan> lsChiTietCoSoSan = new ArrayList<>();

    TextView tvTenCoSo, tvDiaChiCoSo, tvSdtCoSo, tvMotaCoSo, tvGia;

    Button btnDatSan;

    ImageView imgHinhAnhCoSo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChiTietCoSoSanFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChiTietCoSoSan.
     */
    // TODO: Rename and change types and number of parameters
    public static ChiTietCoSoSanFrag newInstance(String param1, String param2) {
        ChiTietCoSoSanFrag fragment = new ChiTietCoSoSanFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chi_tiet_co_so_san, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    private void addControl(View view){
        tvTenCoSo = view.findViewById(R.id.tvTenCoSoSan);
        tvDiaChiCoSo = view.findViewById(R.id.tvDiaChiCoSoSan);
        tvGia = view.findViewById(R.id.tvGiaCoSoSan);
        tvMotaCoSo = view.findViewById(R.id.tvMoTaCoSoSan);
        tvSdtCoSo = view.findViewById(R.id.tvSDTCoSoSan);
        imgHinhAnhCoSo = view.findViewById(R.id.imgChiTietCoSoSan);
        btnDatSan = view.findViewById(R.id.btnDatSan_ChiTietCoSoSan);
    }

    private void addEvent(){
        for (CoSoSan cs: lsChiTietCoSoSan){
            tvTenCoSo.setText(cs.getTen());
            tvSdtCoSo.setText(cs.getSdt());
            tvGia.setText(cs.getGiaSan5() + " - " + cs.getGiaSan7() + "/giờ");
            tvMotaCoSo.setText(cs.getMoTa());
            tvDiaChiCoSo.setText(cs.getDiachi());
            Picasso.get().load(cs.getHinhAnh()).resize(300, 120).into(imgHinhAnhCoSo);
        }

        btnDatSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatSanFrag datSanFrag = new DatSanFrag();
                for (CoSoSan cs : lsChiTietCoSoSan){
                    datSanFrag.lsCoSoSan = lsChiTietCoSoSan;
                    datSanFrag.tenCoSoSan = cs.getTen();
                }
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameFrag, datSanFrag).commit();
            }
        });
    }
}