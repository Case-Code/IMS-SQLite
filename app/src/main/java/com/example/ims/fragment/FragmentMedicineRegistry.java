package com.example.ims.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.ims.R;

public class FragmentMedicineRegistry extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // component view to fragment medicine registry
    private EditText pharmacyFmrQrEditText;
    private EditText pharmacyFmrMedicineNameEditText;
    private ImageButton pharmacyFmrPlusImageButton;
    private EditText pharmacyFmrQuantityEditText;
    private ImageButton pharmacyFmrMinusImageButton;
    private EditText pharmacyFmrPriceEditText;
    private Button pharmacyFmrInsertButton;
    private  Button pharmacyFmrUpdateButton;
    private EditText pharmacyFmrSearchEditText;

private View view ;

public void init (){
    // init component view to fragment medicine registry
     pharmacyFmrQrEditText=view.findViewById(R.id.edit_pharmacy_fmr_qr);
     pharmacyFmrMedicineNameEditText=view.findViewById(R.id.edit_pharmacy_fmr_medicine_name);
     pharmacyFmrPlusImageButton=view.findViewById(R.id.image_pharmacy_fmr_plus);
     pharmacyFmrQuantityEditText=view.findViewById(R.id.edit_pharmacy_fmr_quantity);
     pharmacyFmrMinusImageButton=view.findViewById(R.id.image_pharmacy_fmr_minus);
     pharmacyFmrPriceEditText=view.findViewById(R.id.edit_pharmacy_fmr_price);
     pharmacyFmrInsertButton=view.findViewById(R.id.button_pharmacy_fmr_insert);
     pharmacyFmrUpdateButton=view.findViewById(R.id.button_pharmacy_fmr_update);
     pharmacyFmrSearchEditText=view.findViewById(R.id.edit_pharmacy_fmr_search);


}


    public FragmentMedicineRegistry() {
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
    public static FragmentMedicineRegistry newInstance(String param1, String param2) {
        FragmentMedicineRegistry fragment = new FragmentMedicineRegistry();
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
        return inflater.inflate(R.layout.fragment_medicine_registry, container, false);
    }
}