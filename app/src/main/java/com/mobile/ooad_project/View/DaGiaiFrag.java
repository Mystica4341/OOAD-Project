package com.mobile.ooad_project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.mobile.ooad_project.Adapter.DaGiaiAdapter;
import com.mobile.ooad_project.Control.GiaiControl;
import com.mobile.ooad_project.Model.Giai;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaGiaiFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaGiaiFrag extends Fragment {

    Button btnHenLich;

    ArrayList<Giai> lsGiai = new ArrayList<>();

    ListView lvDaGiai;

    DaGiaiAdapter daGiaiAdapter;

    GiaiControl gc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DaGiaiFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DaGiai.
     */
    // TODO: Rename and change types and number of parameters
    public static DaGiaiFrag newInstance(String param1, String param2) {
        DaGiaiFrag fragment = new DaGiaiFrag();
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
        View view = inflater.inflate(R.layout.fragment_da_giai, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    private void addControl(View view){
        btnHenLich = view.findViewById(R.id.btnTaoLichDaGiai);
        lvDaGiai = view.findViewById(R.id.lvDaGiai);
    }

    private void addEvent(){
        loadDB();
        btnHenLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HenLichDaGiaiFrag HenLichFrag = new HenLichDaGiaiFrag();
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameFrag, HenLichFrag).commit();
            }
        });

        daGiaiAdapter = new DaGiaiAdapter(requireContext(), R.layout.custom_listview_henlichdagiai, lsGiai);
        lvDaGiai.setAdapter(daGiaiAdapter);
    }

    public void loadDB(){
        gc = new GiaiControl(getContext(),GiaiControl.DATABASE_NAME,null,1);
        try {
            lsGiai = gc.loadData();
        }catch (IndexOutOfBoundsException i){

        }

    }
}