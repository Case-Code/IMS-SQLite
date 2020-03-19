package com.example.ims;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentInvoices#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInvoices extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private InvoicesSvcCursorAdapter mInvoicesSvcCursorAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentInvoices() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Invoices.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInvoices newInstance(String param1, String param2) {
        FragmentInvoices fragment = new FragmentInvoices();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         View rootview= inflater.inflate(R.layout.fragment_invoices, container, false);

       /*  ListView  mSvcListView =rootview.findViewById(R.id.list_svc);
         mInvoicesSvcCursorAdapter =new InvoicesSvcCursorAdapter(getActivity() ,null);

        mSvcListView.setAdapter(mInvoicesSvcCursorAdapter);*/


        return rootview;
    }
}
