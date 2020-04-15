package com.example.ims.fragment;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ims.adapter.InvoicesCursorAdapter;
import com.example.ims.R;
import com.example.ims.data.ImsContract;


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

    Button billSaveButton;
    Button svcAddButton;
    Button totalAddButton;
    Button questionSendButton;

   public EditText billNameEditText;
    EditText billPhoneEditText;
    EditText billAddressEditText;
    EditText billFixEditText;
    EditText billEmailEditText;
    EditText svcIdEditText;
    EditText medicalServicesEditText;
    EditText medicationEditText;
    EditText costEditText;
    EditText subtotalEditText;
    EditText taxRateEditText;
    EditText totalTaxEditText;
    EditText otherEditText;
    EditText totalEditText;
    EditText questionsNameEditText;
    EditText questionEmailEditText;
    EditText questionsPhoneEditText;
    EditText questionsWebEditText;
    EditText procedureEditText;

    private Uri mCurrentPatientInvoicesUri;



    private InvoicesCursorAdapter mInvoicesSvcCursorAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

View view ;
public void init (){

    billSaveButton = view. findViewById(R.id.button_billtosave);
    svcAddButton=view.findViewById(R.id.button_svcadd);
    totalAddButton=view.findViewById(R.id.button_totelsave);
    questionSendButton=view.findViewById(R.id.button_questionssend);

     billNameEditText=view.findViewById(R.id.edit_billtoname);
     billPhoneEditText=view.findViewById(R.id.edit_billtophone);
     billAddressEditText=view.findViewById(R.id.edit_bill_to_address);
     billFixEditText=view.findViewById(R.id.edit_bill_to_fax);
     billEmailEditText=view.findViewById(R.id.edit_bill_to_email);

  /*  svcIdEditText =view.findViewById(R.id.edit_svcid);
     medicalServicesEditText=viewGroup.findViewById(R.id.edit_medicalservis);
     medicationEditText=viewGroup.findViewById(R.id.edit_medication);
     costEditText=viewGroup.findViewById(R.id.edit_cost);

    subtotalEditText=viewGroup.findViewById(R.id.edit_sub_total);
     taxRateEditText=viewGroup.findViewById(R.id.edit_tex_rate);;
     totalTaxEditText=viewGroup.findViewById(R.id.edit_total_tax);
     otherEditText=viewGroup.findViewById(R.id.edit_other);
     totalEditText=viewGroup.findViewById(R.id.edit_total);

    questionsNameEditText=viewGroup.findViewById(R.id.edit_questions_name);
     questionEmailEditText=viewGroup.findViewById(R.id.edit_questions_email);
     questionsPhoneEditText=viewGroup.findViewById(R.id.edit_questions_phone);
     questionsWebEditText=viewGroup.findViewById(R.id.edit_questions_web);
     procedureEditText=viewGroup.findViewById(R.id.edit_procedure);
*/



}
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_invoices, container, false);

        init();

        billSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billToSave();

            }
        });




return  view;


    }
    public void billToSave(){
        String billNameString= billNameEditText.getText().toString().trim();
        String billAddressString= billAddressEditText.getText().toString().trim();
        String billEmailString= billEmailEditText.getText().toString().trim();
        String billPhoneString= billPhoneEditText.getText().toString().trim();
        String billFaxString= billFixEditText.getText().toString().trim();


        if(mCurrentPatientInvoicesUri==null&&
                TextUtils.isEmpty(billNameString)&&
                TextUtils.isEmpty(billAddressString)&&
                TextUtils.isEmpty(billEmailString)&&
                TextUtils.isEmpty(billPhoneString)&&
                TextUtils.isEmpty(billFaxString)){
            return; }
        //Integer phoneNumber =Integer.parseInt(billPhoneString);
      //  Integer fixNumber = Integer.parseInt(billFaxString);
        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(billNameString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME, billNameString);
        }
        if (TextUtils.isEmpty(billAddressString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS, billAddressString);
        }
        if (TextUtils.isEmpty(billEmailString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL, billEmailString);
        }
        if (TextUtils.isEmpty(billPhoneString)) {
            Toast.makeText(getContext(), "phone Number is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE,billPhoneString );
        }
        if (TextUtils.isEmpty(billFaxString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX, billFaxString);
        }
        if(mCurrentPatientInvoicesUri==null){
            Uri newUri =getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }

    }

   /* public void svcSave(){

        String svcIdString= svcIdEditText.getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();

        if(mCurrentPatientInvoicesUri==null&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()){
            return; }
        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX, );
        }
        if(mCurrentPatientInvoicesUri==null){
            Uri newUri =getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }

    }


    public void totalSave(){


        String svcIdString= svcIdEditText.getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();

        if(mCurrentPatientInvoicesUri==null&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()){
            return; }
        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX, );
        }
        if(mCurrentPatientInvoicesUri==null){
            Uri newUri =getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void questionsSend(){

        String svcIdString= svcIdEditText.getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();
        String String= .getText().toString().trim();

        if(mCurrentPatientInvoicesUri==null&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()&&
                TextUtils.isEmpty()){
            return; }
        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE, );
        }
        if (TextUtils.isEmpty()) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX, );
        }
        if(mCurrentPatientInvoicesUri==null){
            Uri newUri =getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
