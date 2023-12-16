package com.mobile.ooad_project.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.GiaoHuuControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HenLichGiaoHuuFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HenLichGiaoHuuFrag extends Fragment {

    EditText  edtDiaChi, edtNgayDa;

    Spinner spinnerSan, spinnerGioDa;

    Button btnHen;

    RadioButton RBSan5, RBSan7;

    ArrayList<String> dsSan = new ArrayList<>();

    ArrayList<String> dsGio = new ArrayList<>();

    ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();

    SanControl sc;

    CoSoSanControl csc;

    GiaoHuuControl ghc;






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HenLichGiaoHuuFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HenLichGiaoHuuFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static HenLichGiaoHuuFrag newInstance(String param1, String param2) {
        HenLichGiaoHuuFrag fragment = new HenLichGiaoHuuFrag();
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
        View view = inflater.inflate(R.layout.fragment_hen_lich_giao_huu, container, false);
        addControl(view);
        disableEdt(edtDiaChi);
        disableEdt(edtNgayDa);
        addEvent();
        return view;
    }

    public void addControl(View view){
        edtDiaChi = view.findViewById(R.id.edtDiaChi);
        edtNgayDa = view.findViewById(R.id.edtNgayHen);
        spinnerGioDa = view.findViewById(R.id.spinnerGioBatDau);
        spinnerSan = view.findViewById(R.id.spinnerSan);
        btnHen = view.findViewById(R.id.btnXacNhanHen);
        RBSan5 = view.findViewById(R.id.rbSan5);
        RBSan7 = view.findViewById(R.id.rbSan7);
    }

    public void disableEdt(EditText edt){
        edt.setFocusable(false);
        edt.setCursorVisible(false);
        edt.setKeyListener(null);
    }

    public void addEvent(){
        LoadDB();
        initDataSan();
        initDataGio();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsSan);
        spinnerSan.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsGio);
        spinnerGioDa.setAdapter(adapter2);

        edtNgayDa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDiaLog();
            }
        });

        btnHen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<San> lstSanTrong = new ArrayList<>();
                int loaiSan = 0;
                String ngayDa = String.valueOf(edtNgayDa.getText());
                if (ngayDa != null) {
                    if (RBSan5.isChecked() || RBSan7.isChecked()) {
                        //lay IdCoSoSan tu spinner Ten CoSoSan, LoaiSan, TinhTrang. Cho chay het table San lay ID San
                        if (RBSan5.isChecked()) loaiSan = 5;
                        if (RBSan7.isChecked()) loaiSan = 7;
                        try {
                            lstSanTrong = sc.loadSanTrong(loaiSan, 0, spinnerSan.getSelectedItem().toString());
                        } catch (IndexOutOfBoundsException e) {
                            try {
                                lstSanTrong = sc.loadSanTrong(loaiSan, 1, spinnerSan.getSelectedItem().toString());
                            } catch (IndexOutOfBoundsException i) {

                            }
                        }
                        int idSan = 0;
                        for (San s : lstSanTrong) {
                            San s1 = new San();
                            s1.setIdSan(s.getIdSan());
                            s1.setLoaiSan(s.getLoaiSan());
                            s1.setIdCoSoSan(s.getIdCoSoSan());
                            s1.setLoaiCo(s.getLoaiCo());
                            s1.setTinhTrangSan(0);
                            sc.updateData(s,s1);
                            idSan = s.getIdSan();
                            break;
                        }
                        if (idSan != 0) {
                            ghc.insertData(ngayDa, DangNhapActivity.idKH, idSan);
                            Toast.makeText(getContext(), "Hẹn thành công", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getContext(), "Hẹn thất bại do không có sân hoặc không còn sân trống", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getContext(), "Chưa chọn loại sân", Toast.LENGTH_LONG).show();
                }else Toast.makeText(getContext(), "Chưa chọn ngày đá", Toast.LENGTH_LONG).show();
            }
        });
        spinnerSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadDiachi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void loadDiachi(){
        lsCoSoSan = csc.loadData();
        for (CoSoSan s: lsCoSoSan){
            if(s.getTen().equals(spinnerSan.getSelectedItem().toString()))
                edtDiaChi.setText(s.getDiachi());
        }
    }

    private void initDataSan(){
        lsCoSoSan = csc.loadData();
        for (CoSoSan s: lsCoSoSan){
            dsSan.add(s.getTen());
        }
    }

    private void initDataGio(){
        for (int i = 5; i <= 24; i++){
            dsGio.add(i +":00");
            dsGio.add(i +":30");
        }
    }

    private void OpenDiaLog(){
        DatePickerDialog dialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtNgayDa.setText(dayOfMonth + "/" + month+1 + "/" + year);
            }
        }, 2023, 0, 1);

        dialog.show();
    }

    private void LoadDB(){
        sc = new SanControl(getContext(), SanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        ghc = new GiaoHuuControl(getContext(), GiaoHuuControl.DATABASE_NAME, null, 1);
    }
}