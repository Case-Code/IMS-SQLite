package com.example.ims.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ims.R;

import java.util.List;

public class FragmentSalesRecord extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //component date sales record
    private TextView pharmacyFsrDateYearTextView;
    private TextView pharmacyFsrDateMonthDayTextView;
    private EditText pharmacyFsrSearchEditText;
    private ImageButton pharmacyFsrDateImageButton;
    private ListView pharmacyFsrSalesRecordListView;

    private View view ;
    public void init(){
        //component date sales record
         pharmacyFsrDateYearTextView =view.findViewById(R.id.text_pharmacy_fsr_date_year);
        pharmacyFsrDateMonthDayTextView=view.findViewById(R.id.text_pharmacy_fsr_date_month_day);;
         pharmacyFsrSearchEditText=view.findViewById(R.id.edit_pharmacy_fsr_search);;
         pharmacyFsrDateImageButton=view.findViewById(R.id.image_pharmacy_fsr_date);;
         pharmacyFsrSalesRecordListView=view.findViewById(R.id.list_pharmacy_fsr_sales_record);;

    }
    public FragmentSalesRecord() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment health_record.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHealthRecord newInstance(String param1, String param2) {
        FragmentHealthRecord fragment = new FragmentHealthRecord();
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
        return inflater.inflate(R.layout.fragment_sales_record, container, false);
    }
}
