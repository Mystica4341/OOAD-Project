package com.mobile.ooad_project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.ooad_project.Control.KhachHangControl;
import com.mobile.ooad_project.Model.KhachHang;
import com.mobile.ooad_project.Model.TaiKhoan;
import com.mobile.ooad_project.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThayDoiTaiKhoanFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThayDoiTaiKhoanFrag extends Fragment {

    public static ArrayList<KhachHang> lsKhachHangSetting;

    EditText edtName, edtSdt, edtEmail, edtDiaChi, edtCCCD;

    Button btnSuaSetting;

    KhachHangControl khc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThayDoiTaiKhoanFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaiKhoanFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ThayDoiTaiKhoanFrag newInstance(String param1, String param2) {
        ThayDoiTaiKhoanFrag fragment = new ThayDoiTaiKhoanFrag();
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
        View view = inflater.inflate(R.layout.fragment_thay_doi_tai_khoan, container, false);
        addControl(view);
        disableEdt(edtName);
        addEvent();
        return view;
    }

    private void addControl(View view){
        edtName = view.findViewById(R.id.edtNameSetting);
        edtSdt = view.findViewById(R.id.edtSDTSetting);
        edtCCCD = view.findViewById(R.id.edtCCCDSetting);
        edtEmail = view.findViewById(R.id.edtEmailSetting);
        edtDiaChi = view.findViewById(R.id.edtDiachiSetting);
        btnSuaSetting = view.findViewById(R.id.btnSuaSetting);
    }

    public void addEvent(){
        for (KhachHang kh: lsKhachHangSetting)edtName.setText(kh.getHoTen());
        btnSuaSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khc = new KhachHangControl(requireContext(), KhachHangControl.DATABASE_NAME, null,1);
                try {
                    for (KhachHang kh : lsKhachHangSetting) {
                        KhachHang khNew = new KhachHang();
                        khNew.setIdTaiKhoan(kh.getIdTaiKhoan());
                        khNew.setIdKhach(kh.getIdKhach());
                        khNew.setHoTen(kh.getHoTen());
                        khNew.setCCCD(edtCCCD.getText().toString());
                        khNew.setSdt(edtSdt.getText().toString());
                        khNew.setEmail(edtEmail.getText().toString());
                        khNew.setDiaChi(edtDiaChi.getText().toString());
                        khc.updateData(kh, khNew);
                    }
                    Toast.makeText(requireContext(), "Sửa thành công", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(requireContext(), "Sửa thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void disableEdt(EditText edt){
        edt.setFocusable(false);
        edt.setCursorVisible(false);
        edt.setKeyListener(null);
    }
}