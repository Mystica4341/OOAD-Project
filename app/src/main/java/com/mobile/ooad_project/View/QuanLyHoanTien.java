package com.mobile.ooad_project.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobile.ooad_project.Adapter.HoanTienAdapter;
import com.mobile.ooad_project.Control.HoanTienControl;
import com.mobile.ooad_project.Model.GiaoHuu;
import com.mobile.ooad_project.Model.HoanTien;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyHoanTien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyHoanTien extends Fragment {

    HoanTienControl htc;

    ArrayList<HoanTien> lsHoanTien = new ArrayList();

    ListView lvHoanTien;

    HoanTienAdapter hoanTienAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyHoanTien() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLyHoanTien.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyHoanTien newInstance(String param1, String param2) {
        QuanLyHoanTien fragment = new QuanLyHoanTien();
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
        View view = inflater.inflate(R.layout.fragment_quan_ly_hoan_tien, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    private void addControl(View view){
        lvHoanTien = view.findViewById(R.id.lvHoanTien);
    }
    private void addEvent(){
        htc = new HoanTienControl(requireContext(), HoanTienControl.DATABASE_NAME, null, 1);
        try {
            lsHoanTien = htc.loadData();
            hoanTienAdapter = new HoanTienAdapter(requireContext(), R.layout.custom_listview_quanlyhoantien, lsHoanTien);
            lvHoanTien.setAdapter(hoanTienAdapter);
        } catch (Exception e){

        }
        lvHoanTien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                HoanTien ht = lsHoanTien.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Hoàn Tiền");
                builder.setMessage("Xác nhận hoàn tiền ?");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HoanTien htNew = new HoanTien();
                        htNew.setIdHoanTien(ht.getIdHoanTien());
                        htNew.setSoTien(ht.getSoTien());
                        htNew.setTinhTrang(1);
                        htNew.setIdKhach(ht.getIdKhach());
                        htNew.setIdSan(ht.getIdSan());
                        htc.updateData(ht, htNew);
                        dialog.dismiss();
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(requireContext());
                        builder1.setTitle("Xác Nhận");
                        builder1.setMessage("Xác nhận hoàn tiền");
                        AlertDialog alertDialog = builder1.create();
                        alertDialog.show();
                        try {
                            lsHoanTien = htc.loadData();
                            hoanTienAdapter = new HoanTienAdapter(requireContext(), R.layout.custom_listview_quanlyhoantien, lsHoanTien);
                            lvHoanTien.setAdapter(hoanTienAdapter);
                        } catch (Exception e){

                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HoanTien htNew = new HoanTien();
                        htNew.setIdHoanTien(ht.getIdHoanTien());
                        htNew.setSoTien(ht.getSoTien());
                        htNew.setTinhTrang(2);
                        htNew.setIdKhach(ht.getIdKhach());
                        htNew.setIdSan(ht.getIdSan());
                        htc.updateData(ht, htNew);
                        dialog.dismiss();
                        try {
                            lsHoanTien = htc.loadData();
                            hoanTienAdapter = new HoanTienAdapter(requireContext(), R.layout.custom_listview_quanlyhoantien, lsHoanTien);
                            lvHoanTien.setAdapter(hoanTienAdapter);
                        } catch (Exception e){

                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }
}