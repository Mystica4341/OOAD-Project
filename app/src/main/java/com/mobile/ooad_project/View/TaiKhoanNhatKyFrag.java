package com.mobile.ooad_project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mobile.ooad_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanNhatKyFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanNhatKyFrag extends Fragment {

    ImageView imgSetting;

    TextView tvHoTen, tvEmail;

    ListView lvNhatKy;

    TabLayout tabLayout;

    TabItem tabDatSan, tabDaGiai, tabGiaoHuu;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaiKhoanNhatKyFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NhatKyFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static TaiKhoanNhatKyFrag newInstance(String param1, String param2) {
        TaiKhoanNhatKyFrag fragment = new TaiKhoanNhatKyFrag();
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
        View view = inflater.inflate(R.layout.fragment_tai_khoan_nhat_ky, container, false);
        addControl(view);
        return view;
    }

    private void addControl(View view){
        imgSetting = view.findViewById(R.id.imgSetting);
        tvHoTen = view.findViewById(R.id.tvTenKhach);
        tvEmail = view.findViewById(R.id.tvEmail);
        tabLayout = view.findViewById(R.id.TabLayout);
        tabDatSan = view.findViewById(R.id.TabDatSan);
        tabDaGiai = view.findViewById(R.id.TabDaGiai);
        tabGiaoHuu = view.findViewById(R.id.TabGiaoHuu);
        lvNhatKy = view.findViewById(R.id.lvNhatKy);
    }
}