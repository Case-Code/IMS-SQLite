package com.example.ims.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
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
    private EditText qrEditText;
    private EditText nameEditText;
    private ImageButton plusImageButton;
    private EditText quantityEditText;
    private ImageButton minusImageButton;
    private EditText priceEditText;
    private Button insertButton;
    private  Button updateButton;
    private EditText searchEditText;

private View view ;

public void init (){
    // init component view to fragment medicine registry
     qrEditText =view.findViewById(R.id.edit_medicine_qr);
     nameEditText =view.findViewById(R.id.edit_medicine_name);
     plusImageButton =view.findViewById(R.id.image_medicine_plus);
     quantityEditText =view.findViewById(R.id.edit_medicine_quantity);
     minusImageButton =view.findViewById(R.id.image_medicine_minus);
     priceEditText =view.findViewById(R.id.edit_medicine_price);
     insertButton =view.findViewById(R.id.button_medicine_insert);
     updateButton =view.findViewById(R.id.button_medicine_update);
     searchEditText =view.findViewById(R.id.edit_medicine_search);


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
        return inflater.inflate(R.layout.fragment_medicineregistry, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }
}