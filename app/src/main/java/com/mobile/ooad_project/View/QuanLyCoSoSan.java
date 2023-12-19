package com.mobile.ooad_project.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mobile.ooad_project.Adapter.QuanLyCoSoSanAdapter;
import com.mobile.ooad_project.Control.CoSoSanControl;
import com.mobile.ooad_project.Model.CoSoSan;
import com.mobile.ooad_project.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyCoSoSan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyCoSoSan extends Fragment {

    EditText edtCoSoSan, edtTP, edtQuan;

    ListView lvCoSoSan;

    Button btnThem, btnXoa;

    CoSoSanControl csc;

    ArrayList<CoSoSan> lsCoSoSan = new ArrayList<>();

    QuanLyCoSoSanAdapter coSoSanAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyCoSoSan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLyCoSoSan.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyCoSoSan newInstance(String param1, String param2) {
        QuanLyCoSoSan fragment = new QuanLyCoSoSan();
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
        View view = inflater.inflate(R.layout.fragment_quan_ly_co_so_san, container, false);
        addControl(view);
        addEvent();
        return view;
    }

    public void addControl(View view){
        btnThem = view.findViewById(R.id.btnthemcss);
        btnXoa = view.findViewById(R.id.btnxoacss);
        lvCoSoSan = view.findViewById(R.id.ListVCSS);
        edtCoSoSan = view.findViewById(R.id.editTextTenCSS);
        edtQuan = view.findViewById(R.id.editTextQuan);
        edtTP = view.findViewById(R.id.editTextTP);
    }

    public void addEvent(){
        loadDB();
        try {
            lsCoSoSan = csc.loadData();
            coSoSanAdapter = new QuanLyCoSoSanAdapter(requireContext(), R.layout.custom_listview_quanlycososan, lsCoSoSan);
            lvCoSoSan.setAdapter(coSoSanAdapter);
        }catch (IndexOutOfBoundsException e){

        }

        lvCoSoSan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });
    }

    private void loadDB(){
        csc = new CoSoSanControl(getContext(), CoSoSanControl.DATABASE_NAME, null, 1);
    }
}