package com.mobile.ooad_project.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.mobile.ooad_project.Adapter.DaGiaiAdapter;
import com.mobile.ooad_project.Adapter.GiaoHuuAdapter;
import com.mobile.ooad_project.Adapter.NhatKyDaGiaiAdapter;
import com.mobile.ooad_project.Adapter.NhatKyDatSanAdapter;
import com.mobile.ooad_project.Adapter.NhatKyGiaoHuuAdapter;
import com.mobile.ooad_project.Control.DatSanControl;
import com.mobile.ooad_project.Control.GiaiControl;
import com.mobile.ooad_project.Control.GiaoHuuControl;
import com.mobile.ooad_project.Control.HoanTienControl;
import com.mobile.ooad_project.Model.DatSan;
import com.mobile.ooad_project.Model.Giai;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.KhachHang;
import com.mobile.ooad_project.Model.TaiKhoan;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanNhatKyFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanNhatKyFrag extends Fragment {
    public static ArrayList<KhachHang> accountInfo;

    ImageView imgSetting;

    TextView tvHoTen, tvEmail;

    ListView lvNhatKy;

    TabLayout tabLayout;

    TabItem tabDatSan, tabDaGiai, tabGiaoHuu;
    DatSanControl dsc;
    GiaoHuuControl ghc;
    GiaiControl gc;

    HoanTienControl htc;
    ArrayList<DatSan> lstDatSan;
    ArrayList<GiaoHuu> lstGiaoHuu;
    ArrayList<Giai> lstGiai;
    NhatKyDatSanAdapter adapter1;
    NhatKyGiaoHuuAdapter adapter2;
    NhatKyDaGiaiAdapter adapter3;

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
        addEvent();
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
    private void addEvent(){
        for(KhachHang a : accountInfo) {
            tvHoTen.setText(a.getHoTen());
            tvEmail.setText(a.getEmail());
        }
        loadData();
        adapter1 = new NhatKyDatSanAdapter(requireContext(),R.layout.custom_listview_nhatkydatsan,lstDatSan);
        lvNhatKy.setAdapter(adapter1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                if (i == 0) {
                    adapter1 = new NhatKyDatSanAdapter(requireContext(), R.layout.custom_listview_nhatkydatsan, lstDatSan);
                    lvNhatKy.setAdapter(adapter1);
                } else if (i == 1) {
                    adapter3 = new NhatKyDaGiaiAdapter(requireContext(), R.layout.custom_listview_nhatkydatsan, lstGiai);
                    lvNhatKy.setAdapter(adapter3);
                } else if (i == 2) {
                    adapter2 = new NhatKyGiaoHuuAdapter(requireContext(),R.layout.custom_listview_nhatkydatsan,lstGiaoHuu);
                    lvNhatKy.setAdapter(adapter2);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThayDoiTaiKhoanFrag thayDoiTaiKhoanFrag = new ThayDoiTaiKhoanFrag();
                ThayDoiTaiKhoanFrag.lsKhachHangSetting = accountInfo;
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction fs = fm.beginTransaction();
                fs.replace(R.id.frameFrag, thayDoiTaiKhoanFrag).commit();
            }
        });
        lvNhatKy.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Hoàn Tiền Cọc");
                builder.setMessage("Bạn có đồng ý hoàn tiền ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatSan ds = lstDatSan.get(position);
                        htc.insertData(ds.getTongTien(), 0, DangNhapActivity.idKH, ds.getIdSan());
                        dialog.dismiss();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext());
                        builder1.setTitle("Hoàn Tiền");
                        builder1.setMessage("Bạn đã chọn hoàn tiền, vui lòng chờ xử lý");
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return false;
            }
        });
    }
    private void loadData(){
        dsc = new DatSanControl(requireContext(),DatSanControl.DATABASE_NAME,null,1);
        ghc = new GiaoHuuControl(requireContext(),GiaoHuuControl.DATABASE_NAME,null,1);
        gc = new GiaiControl(requireContext(),GiaiControl.DATABASE_NAME,null,1);
        htc = new HoanTienControl(requireContext(),HoanTienControl.DATABASE_NAME,null,1);
        try {
            for(KhachHang kh: accountInfo) {
                lstGiaoHuu = ghc.loadDataKhachHang(kh.getIdKhach());
                lstGiai = gc.loadDataKhacHang(kh.getIdKhach());
            }
        }catch (IndexOutOfBoundsException e){

        }
        try {
            for (KhachHang kh: accountInfo) {
                lstDatSan = dsc.loadDataKhachHang(kh.getIdKhach());
                for (GiaoHuu gh : lstGiaoHuu) {
                    if (kh.getIdKhach() == gh.getIdKhachB()) {
                        lstDatSan.addAll(dsc.loadDataKhachHang(gh.getIdKhachA()));
                        break;
                    } else lstDatSan = dsc.loadDataKhachHang(kh.getIdKhach());
                }
            }
        }catch (IndexOutOfBoundsException i){

        }
    }
}