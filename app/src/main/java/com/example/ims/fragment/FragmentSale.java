package com.example.ims.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ims.R;

public class FragmentSale extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
        // add sale
    private EditText pharmacyFSaleQrEditText;
    private ImageButton pharmacyDSalePlusImageButton;
    private EditText pharmacyFSaleQuantityEditText;
    private ImageButton pharmacyFSaleMinusImageButton;
    private Button pharmacyFSaleSaleButton;
    private  TextView pharmacyFSaleTotalTextView;
    private EditText pharmacyFSaleSearchEditText;

    //show sale
    private ListView pharmacyFSaleSaleListView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
private View view ;
    public void init(){
        // add sale
         pharmacyFSaleQrEditText=view.findViewById(R.id.edit_pharmacy_fsale_qr);
         pharmacyDSalePlusImageButton=view.findViewById(R.id.image_pharmacy_fsale_plus);
         pharmacyFSaleQuantityEditText=view.findViewById(R.id.edit_pharmacy_fsale_quantity);
         pharmacyFSaleMinusImageButton=view.findViewById(R.id.image_pharmacy_fsale_minus);
         pharmacyFSaleSaleButton=view.findViewById(R.id.button_pharmacy_fsale_sale);
         pharmacyFSaleTotalTextView=view.findViewById(R.id.text_pharmacy_fsale_total);
         pharmacyFSaleSearchEditText=view.findViewById(R.id.edit_pharmacy_fsale_search);

        //show sale
         pharmacyFSaleSaleListView=view.findViewById(R.id.list_pharmacy_fsale_sale);

    }
    public FragmentSale() {
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
        return inflater.inflate(R.layout.fragment_sale, container, false);
    }
}