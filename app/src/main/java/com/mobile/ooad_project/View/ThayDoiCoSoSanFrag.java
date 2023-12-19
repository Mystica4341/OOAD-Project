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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThayDoiCoSoSanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThayDoiCoSoSanFrag extends Fragment {

    public static ArrayList<CoSoSan> lsCoSoSan_QuanLy = new ArrayList<>();

    EditText edtTenCoSo, edtDiaChiCoSo, edtSdt, edtSoLuongSan, edtMoTa;

    Button btnThayDoi;

    CoSoSanControl csc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThayDoiCoSoSanFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThayDoiCoSoSanFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ThayDoiCoSoSanFrag newInstance(String param1, String param2) {
        ThayDoiCoSoSanFrag fragment = new ThayDoiCoSoSanFrag();
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
        View view = inflater.inflate(R.layout.fragment_thay_doi_co_so_san, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    private void addControl(View view){
        edtTenCoSo = view.findViewById(R.id.editTextTenCSSsua);
        edtDiaChiCoSo = view.findViewById(R.id.editTextDiaChiSua);
        edtSdt = view.findViewById(R.id.editTextSDTsua);
        edtSoLuongSan = view.findViewById(R.id.editTextSoLuongSan);
        edtMoTa = view.findViewById(R.id.editTextMoTa);
        btnThayDoi = view.findViewById(R.id.btnSua);
    }

    private void addEvent(){
        csc = new CoSoSanControl(requireContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        for (CoSoSan cs: lsCoSoSan_QuanLy){
            edtTenCoSo.setText(cs.getTen());
            edtMoTa.setText(cs.getMoTa());
            edtSoLuongSan.setText(String.valueOf(cs.getSoLuongSan()));
            edtSdt.setText(cs.getSdt());
            edtDiaChiCoSo.setText(cs.getDiachi());
        }
        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    for (CoSoSan cs: lsCoSoSan_QuanLy){
                        CoSoSan csNew = new CoSoSan();
                        csNew.setIdCoSoSan(cs.getIdCoSoSan());
                        csNew.setTen(edtTenCoSo.getText().toString());
                        csNew.setDiachi(edtDiaChiCoSo.getText().toString());
                        csNew.setSdt(edtSdt.getText().toString());
                        csNew.setMoTa(edtMoTa.getText().toString());
                        csNew.setSoLuongSan(Integer.parseInt(edtSoLuongSan.getText().toString()));
                        csNew.setGiaSan5(cs.getGiaSan5());
                        csNew.setGiaSan7(cs.getGiaSan7());
                        csNew.setHinhAnh(cs.getHinhAnh());
                        csc.updateData(cs, csNew);
                        Toast.makeText(requireContext(), "Sửa thành công", Toast.LENGTH_LONG).show();
                        ChiTietCoSoSanFrag chiTietCoSoSanFrag = new ChiTietCoSoSanFrag();
                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction fs = fm.beginTransaction();
                        fs.replace(R.id.frameFragAdmin, chiTietCoSoSanFrag).commit();
                    }
                }catch (Exception e){
                    Toast.makeText(requireContext(), "Sửa thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}