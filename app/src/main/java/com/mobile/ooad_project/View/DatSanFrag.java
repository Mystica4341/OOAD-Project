package com.mobile.ooad_project.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DatSanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatSanFrag extends Fragment {

    Spinner spinnerCoSoSan, spinnerGio, spinnerThoiGian;

    EditText edtNgay;

    CheckBox cbSan5, cbSan7, cbTuNhien, cbNhanTao;

    RadioButton rbCo, rbKhong;

    Button btnThoat, btnXacNhan;

    CoSoSanControl csc;

    SanControl sc;
    LinearLayout lnSan5, lnSan7, lnTuNhien, lnNhanTao;

    ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();

    ArrayList<String> dsSan = new ArrayList<>();

    ArrayList<String> dsGio = new ArrayList<>();

    ArrayList<String> dsThoiGian = new ArrayList<>();

    int idCoSoSan = 0;

    int idSan = 0;

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
        spinnerCoSoSan = view.findViewById(R.id.spinnerCoSoSan);
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
    }

    public void addEvent(){
        LoadDB();
        initDataSan();
        initDataGio();
        initDataThoiGian();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dsSan);
        spinnerCoSoSan.setAdapter(adapter1);
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
                int loaiSan = 0;
                String ngayDa = edtNgay.getText().toString();
                int loaiCo = 1;
                int tinhtrangthanhtoan = 0;
                String Thoigian = spinnerGio.getSelectedItem().toString();
                if (edtNgay.getText() == null) {
                    if (cbSan5.isChecked() || cbSan7.isChecked()) {
                        if (cbTuNhien.isChecked() || cbNhanTao.isChecked()){
                            ArrayList<CoSoSan> lsCoSoSan = csc.loadData();
                            for (CoSoSan cs : lsCoSoSan) {
                                if (cs.getTen().equals(spinnerCoSoSan.getSelectedItem().toString())) {
                                    idCoSoSan = cs.getIdCoSoSan();
                                } else continue;
                            }
                            if (cbSan5.isChecked()) loaiSan = 5;
                            if (cbSan7.isChecked()) loaiSan = 7;
                            if (cbNhanTao.isChecked()) loaiCo = 0;
                            if (cbTuNhien.isChecked()) loaiCo = 1;
                            if (rbCo.isChecked()) tinhtrangthanhtoan = 1;
                            try {
                                idSan = sc.checkSan(idCoSoSan, loaiSan);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (idSan != 0) {
                                //Mising insert
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
    }

    public void LoadDB(){
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        sc = new SanControl(getContext(), SanControl.DATABASE_NAME, null, 1);
    }

    private void initDataSan(){
        try {
            lsCoSoSan = csc.loadData();
            for (CoSoSan s : lsCoSoSan) {
                dsSan.add(s.getTen());
            }
        }catch (IndexOutOfBoundsException e){

        }
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
        for (int i = 1; i <=3; i++){
            dsThoiGian.add(i +" tiếng");
        }
    }

    private void OpenDiaLogDay(){
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edtNgay.setText(dayOfMonth + "/" + month + "/" + year);
            }
        }, 2023, 1, 1);
        dialog.show();
    }
}