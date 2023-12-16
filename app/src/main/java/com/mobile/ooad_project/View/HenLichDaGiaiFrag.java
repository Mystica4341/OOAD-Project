package com.mobile.ooad_project.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.GiaiControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HenLichDaGiaiFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HenLichDaGiaiFrag extends Fragment {

    Button btnHuyDaGiai, btnDatDaGiai;

    Spinner spinnerDiaChi, spinnerLoaiSan;

    EditText edtNgayThiDau, edtTenGiai;

    ImageView imgNgayThiDau, imgSanDaGiai;

    ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();

    ArrayList<San> lsSan = new ArrayList<>();

    CoSoSanControl csc;
    GiaiControl gc;

    SanControl sc;

    ArrayList<String> dsTenCoSo = new ArrayList<>();

    ArrayList<String> dsLoaiSan = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HenLichDaGiaiFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaGiaiFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static HenLichDaGiaiFrag newInstance(String param1, String param2) {
        HenLichDaGiaiFrag fragment = new HenLichDaGiaiFrag();
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
        View view = inflater.inflate(R.layout.fragment_hen_lich_da_giai, container, false);
        addControl(view);
        disableEdt(edtNgayThiDau);
        addEvent();
        return view;
    }

    private void addControl(View view){
        btnHuyDaGiai = view.findViewById(R.id.btnHuyDat_DaGiai);
        btnDatDaGiai = view.findViewById(R.id.btnDatGiai_DaGiai);
        spinnerDiaChi = view.findViewById(R.id.spinnerDiaChiSan_DaGiai);
        spinnerLoaiSan = view.findViewById(R.id.spinnerTenSan_DaGiai);
        edtNgayThiDau = view.findViewById(R.id.edtNgayBatDau_DaGiai);
        imgNgayThiDau = view.findViewById(R.id.imgNgayThiDau);
        imgSanDaGiai = view.findViewById(R.id.imgSan_DaGiai);
        edtTenGiai = view.findViewById(R.id.edtTenGiai_DaGiai);
    }
    private void addEvent(){
        sc = new SanControl(getContext(), SanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        gc = new GiaiControl(getContext(),GiaiControl.DATABASE_NAME,null,1);
        initDataSan();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsTenCoSo);
        spinnerDiaChi.setAdapter(adapter1);

        btnHuyDaGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaGiaiFrag DaGiaiFrag = new DaGiaiFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameFrag, DaGiaiFrag).commit();
            }
        });
        edtNgayThiDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiaLog();
            }
        });
        imgNgayThiDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiaLog();
            }
        });

        spinnerDiaChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                for (CoSoSan cs : lsCoSoSan){
                    if (cs.getTen().equals(spinnerDiaChi.getSelectedItem().toString())){
                        dsLoaiSan.clear();
                        Picasso.get().load(cs.getHinhAnh()).resize(200, 200).into(imgSanDaGiai);
                        loadLoaiSan();
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsLoaiSan);
                        spinnerLoaiSan.setAdapter(adapter2);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnDatDaGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ArrayList<San> lstSanTrong = new ArrayList<>();
                    if (spinnerLoaiSan.getSelectedItem().toString().equals("Sân 5")){
                        try {
                            lstSanTrong = sc.loadSanTrong(5, 0, spinnerDiaChi.getSelectedItem().toString());
                        }catch (IndexOutOfBoundsException e){
                            try {
                                lstSanTrong = sc.loadSanTrong(5, 1, spinnerDiaChi.getSelectedItem().toString());
                            }catch (IndexOutOfBoundsException i){

                            }
                        }
                    }else {
                        try {
                            lstSanTrong = sc.loadSanTrong(7, 0, spinnerDiaChi.getSelectedItem().toString());
                        } catch (IndexOutOfBoundsException e) {
                            try {
                                lstSanTrong = sc.loadSanTrong(7, 1, spinnerDiaChi.getSelectedItem().toString());
                            } catch (IndexOutOfBoundsException i) {

                            }
                        }
                    }
                    int id = 0;
                    for (San s : lstSanTrong) {
                        id = s.getIdSan();
                        break;
                    }
                    if (id != 0) {
                        gc.insertData(edtTenGiai.getText().toString(), id, edtNgayThiDau.getText().toString());
                        Toast.makeText(getContext(), "Hẹn thành công", Toast.LENGTH_LONG).show();
                    }else Toast.makeText(getContext(), "Hẹn thất bại", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initDataSan(){
        lsCoSoSan = csc.loadData();
        lsSan = sc.loadData();
        for (CoSoSan css: lsCoSoSan){
            dsTenCoSo.add(css.getTen());
        }
    }

    public void loadLoaiSan(){
//        lsCoSoSan = csc.loadData();
//        lsSan = sc.loadData();
//        for (CoSoSan css: lsCoSoSan){
//            for (San s: lsSan){
//                if (s.getIdCoSoSan() == css.getIdCoSoSan() && s.getTinhTrangSan() == 1 && css.getTen().equals(spinnerDiaChi.getSelectedItem().toString())){
//                    dsLoaiSan.add("San " + s.getLoaiSan());
//                }
//            }
//        }
        dsLoaiSan.add("Sân 5");
        dsLoaiSan.add("Sân 7");
    }

    private void OpenDiaLog(){
        DatePickerDialog dialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtNgayThiDau.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, 2023, 1, 1);

        dialog.show();
    }

    public void disableEdt(EditText edt){
        edt.setFocusable(false);
        edt.setCursorVisible(false);
        edt.setKeyListener(null);
    }
}