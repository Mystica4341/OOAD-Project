package com.mobile.ooad_project.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mobile.ooad_project.Adapter.GiaoHuuAdapter;
import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Control.GiaoHuuControl;
import com.mobile.ooad_project.Control.SanControl;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaGiaoHuuFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaGiaoHuuFrag extends Fragment {

    Button btnHenLich;

    ArrayList<GiaoHuu> lsGiaoHuu;

    GiaoHuuAdapter adapter;

    ListView lvGiaoHuu;

    GiaoHuuControl ghc;

    SanControl sc;

    CoSoSanControl csc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DaGiaoHuuFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaGiaoHuuFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static DaGiaoHuuFrag newInstance(String param1, String param2) {
        DaGiaoHuuFrag fragment = new DaGiaoHuuFrag();
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
        View view = inflater.inflate(R.layout.fragment_da_giao_huu, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    public void addControl(View view){
        btnHenLich = view.findViewById(R.id.btnTaoLich);
        lvGiaoHuu = view.findViewById(R.id.lvDaGiaoHuu);
    }

    public void addEvent(){
        btnHenLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HenLichGiaoHuuFrag HenLichFrag = new HenLichGiaoHuuFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameFrag, HenLichFrag).commit();
            }
        });
        LoadDB();
        try{
            lsGiaoHuu = ghc.loadData();
            adapter = new GiaoHuuAdapter(requireContext(), R.layout.custom_listview_henlichdagiaohuu, lsGiaoHuu);
            lvGiaoHuu.setAdapter(adapter);
        } catch (Exception e){

        }

        lvGiaoHuu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiaoHuu gh = lsGiaoHuu.get(position);
                if(gh.getIdKhachB() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Hẹn giao hữu");
                    builder.setMessage("Bạn đồng ý hẹn lịch này ?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GiaoHuu ghnew = new GiaoHuu();
                            ghnew.setIdSan(gh.getIdSan());
                            ghnew.setNgayDaGiaoHuu(gh.getNgayDaGiaoHuu());
                            ghnew.setIdKhachA(gh.getIdKhachA());
                            ghnew.setIdTranGiaoHuu(gh.getIdTranGiaoHuu());
                            ghnew.setIdKhachB(DangNhapActivity.idKH);
                            ghc.updateData(gh, ghnew);
                            dialog.dismiss();
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext());
                            builder1.setTitle("Đặt thành công");
                            builder1.setMessage("Bạn đã đặt lịch thành công");
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
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Thất bại");
                    builder.setMessage("Lịch đã đầy!");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }

    private void LoadDB(){
        sc = new SanControl(getContext(), SanControl.DATABASE_NAME, null, 1);
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
        ghc = new GiaoHuuControl(getContext(), GiaoHuuControl.DATABASE_NAME, null, 1);

    }
}