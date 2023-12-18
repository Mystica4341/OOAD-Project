package com.mobile.ooad_project.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.DatSanControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.Model.DatSan;
import com.mobile.ooad_project.Model.San;
import com.mobile.ooad_project.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatSanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatSanFrag extends Fragment {

    Spinner spinnerGio, spinnerThoiGian;

    EditText edtNgay;

    CheckBox cbSan5, cbSan7, cbTuNhien, cbNhanTao;
    TextView tvThanhTien;

    RadioButton rbCo, rbKhong;

    Button btnThoat, btnXacNhan;

    CoSoSanControl csc;

    SanControl sc;

    int tongtien;
    LinearLayout lnSan5, lnSan7, lnTuNhien, lnNhanTao;

    public ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();

    ArrayList<String> dsSan = new ArrayList<>();

    ArrayList<String> dsGio = new ArrayList<>();

    ArrayList<String> dsThoiGian = new ArrayList<>();

    DatSanControl dsc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DatSanFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Frag.
     */
    // TODO: Rename and change types and number of parameters
    public static DatSanFrag newInstance(String param1, String param2) {
        DatSanFrag fragment = new DatSanFrag();
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
        View view = inflater.inflate(R.layout.fragment_datsan_, container, false);
        addControl(view);
        disableEdt(edtNgay);
        addEvent();
        return view;
    }

    public void addControl(View view){
//        spinnerCoSoSan = view.findViewById(R.id.spinnerCoSoSan);
        spinnerGio = view.findViewById(R.id.spinnerGio);
        spinnerThoiGian = view.findViewById(R.id.spinnerThoiGianDa);
        edtNgay = view.findViewById(R.id.edtChonNgay);
        cbSan5 = view.findViewById(R.id.checkBoxS5);
        cbSan7 = view.findViewById(R.id.checkBoxS7);
        cbNhanTao = view.findViewById(R.id.cbNhanTao);
        cbTuNhien = view.findViewById(R.id.cbTuNhien);
        rbCo = view.findViewById(R.id.checkBoxCo);
        rbKhong = view.findViewById(R.id.checkBoxKhong);
        btnThoat = view.findViewById(R.id.btnThoat);
        btnXacNhan = view.findViewById(R.id.btnXacNhan);
        lnSan5 = view.findViewById(R.id.lnSan5);
        lnSan7 = view.findViewById(R.id.lnSan7);
        lnNhanTao = view.findViewById(R.id.lnNhanTao);
        lnTuNhien = view.findViewById(R.id.lnTuNhien);
        tvThanhTien = view.findViewById(R.id.tvThanhTien);
    }

    public void addEvent(){
        LoadDB();
//        initDataSan();
        initDataGio();
        initDataThoiGian();
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dsSan);
//        spinnerCoSoSan.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsGio);
        spinnerGio.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsThoiGian);
        spinnerThoiGian.setAdapter(adapter3);

        edtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDiaLogDay();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idSan = 0;
                ArrayList<San> lstSan = new ArrayList<>();
                ArrayList<DatSan> lstDatSan = new ArrayList<>();
                try {
                    lstSan = sc.loadData();
                    lstDatSan = dsc.loadData();
                }catch (IndexOutOfBoundsException e){

                }
                int loaiSan = 0;
                String ngayDa = edtNgay.getText().toString();
                int loaiCo = 0;
                int tinhtrangthanhtoan = 0;
                if (edtNgay.getText() != null) {
                    if (cbSan5.isChecked() || cbSan7.isChecked()) {
                        if (cbTuNhien.isChecked() || cbNhanTao.isChecked()){
                            if (cbSan5.isChecked()) loaiSan = 5;
                            if (cbSan7.isChecked()) loaiSan = 7;
                            if (cbNhanTao.isChecked()) loaiCo = 0;
                            if (cbTuNhien.isChecked()) loaiCo = 1;
                            if (rbCo.isChecked()) tinhtrangthanhtoan = 1;
                            for (San s : lstSan) {
                                if (s.getLoaiSan() == loaiSan && s.getLoaiCo() == loaiCo) {
                                    idSan = s.getIdSan();
                                    for (DatSan ds : lstDatSan) {
                                        if (ds.getIdSan() == idSan) {
                                            idSan = 0;
                                            break;
                                        }
                                        break;
                                    }
                                    if (idSan != 0)
                                        break;
                                    else continue;
                                }
                            }

                            if (idSan != 0) {
                                dsc.insertData(ngayDa, spinnerGio.getSelectedItem().toString(), spinnerThoiGian.getSelectedItem().toString(), tinhtrangthanhtoan, DangNhapActivity.idKH, idSan, tongtien);
                                Toast.makeText(getContext(), "Hẹn thành công", Toast.LENGTH_LONG).show();
                            } else Toast.makeText(getContext(), "Hẹn thất bại do không có sân hoặc không còn sân trống", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getContext(), "Chưa chọn loại Cỏ", Toast.LENGTH_LONG).show();
                        }
                    } else
                        Toast.makeText(getContext(), "Chưa chọn loại sân", Toast.LENGTH_LONG).show();
                } else Toast.makeText(getContext(), "Chưa chọn ngày đá", Toast.LENGTH_LONG).show();
            }
        });
        lnSan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbSan5.setChecked(true);
                cbSan7.setChecked(false);
            }
        });
        lnSan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbSan5.setChecked(false);
                cbSan7.setChecked(true);
            }
        });
        lnTuNhien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbTuNhien.setChecked(true);
                cbNhanTao.setChecked(false);
            }
        });
        lnNhanTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbTuNhien.setChecked(false);
                cbNhanTao.setChecked(true);
            }
        });
        spinnerThoiGian.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int loaiSan = 0;
                String thanhtien = "";
                int gioDa = 0;
                if (cbSan5.isChecked()) loaiSan = 5;
                if (cbSan7.isChecked()) loaiSan = 7;
                if (spinnerThoiGian.getSelectedItem().toString().equals("1 tiếng")) gioDa = 1;
                else if (spinnerThoiGian.getSelectedItem().toString().equals("2 tiếng")) gioDa = 2;
                else if (spinnerThoiGian.getSelectedItem().toString().equals("3 tiếng")) gioDa = 3;
                for(CoSoSan css: lsCoSoSan)
                    if (loaiSan == 5){
                        tongtien = css.getGiaSan5()*gioDa;
                        thanhtien = "Tổng tiền: " + css.getGiaSan5()*gioDa;}
                    else if (loaiSan == 7){
                        tongtien = css.getGiaSan7()*gioDa;
                        thanhtien = "Tổng tiền: " + css.getGiaSan7()*gioDa;}
                tvThanhTien.setText(thanhtien);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void LoadDB(){
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        sc = new SanControl(getContext(), SanControl.DATABASE_NAME, null, 1);
        dsc = new DatSanControl(getContext(), DatSanControl.DATABASE_NAME, null, 1);
    }

    public void disableEdt(EditText edt){
        edt.setFocusable(false);
        edt.setCursorVisible(false);
        edt.setKeyListener(null);
    }

    private void initDataGio(){
        for (int i = 5; i <= 24; i++){
            dsGio.add(i +":00");
            dsGio.add(i +":30");
        }
    }

    private void initDataThoiGian(){
        for (int i = 0; i <=3; i++){
            if (i == 0){
                dsThoiGian.add("");
            }else
                dsThoiGian.add(i +" tiếng");
        }
    }

    private void OpenDiaLogDay(){
        DatePickerDialog dialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtNgay.setText(dayOfMonth + "/" + month+1 + "/" + year);
            }
        }, 2023, 0, 1);
        dialog.show();
    }
}