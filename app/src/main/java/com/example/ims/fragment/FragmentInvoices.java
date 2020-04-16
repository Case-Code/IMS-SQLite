package com.example.ims.fragment;

import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Entity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ims.ReceptionActivity;
import com.example.ims.adapter.InvoicesCursorAdapter;
import com.example.ims.R;
import com.example.ims.adapter.PatientCursorAdapter;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;

import java.util.ArrayList;


public class FragmentInvoices extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button billSaveButton;
    Button svcAddButton;
    Button totalAddButton;
    Button questionSendButton;

   public  TextView patientIdTextView;
    TextView dateOfSvcTextView;
    TextView invoiceDateTextView;
    TextView dateDueTextView;

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

    ContentValues values;

    private Uri mCurrentPatientInvoicesUri;


    private InvoicesCursorAdapter mInvoicesSvcCursorAdapter;

    // TODO: Rename and change types of parameters
    public int id;
    private String mParam2;


    View view;

    public void init() {

        billSaveButton = view.findViewById(R.id.button_billtosave);
        svcAddButton = view.findViewById(R.id.button_svcadd);
        totalAddButton = view.findViewById(R.id.button_totelsave);
        questionSendButton = view.findViewById(R.id.button_questionssend);

        patientIdTextView=view.findViewById(R.id.text_patient_id);
        dateOfSvcTextView=view.findViewById(R.id.text_data_of_svc);
         invoiceDateTextView=view.findViewById(R.id.text_invoice_date);
         dateDueTextView=view.findViewById(R.id.text_date_due);


        billNameEditText = view.findViewById(R.id.edit_billtoname);
        billPhoneEditText = view.findViewById(R.id.edit_billtophone);
        billAddressEditText = view.findViewById(R.id.edit_bill_to_address);
        billFixEditText = view.findViewById(R.id.edit_bill_to_fax);
        billEmailEditText = view.findViewById(R.id.edit_bill_to_email);

        svcIdEditText = view.findViewById(R.id.edit_svcid);
        medicalServicesEditText = view.findViewById(R.id.edit_medicalservis);
        medicationEditText = view.findViewById(R.id.edit_medication);
        costEditText = view.findViewById(R.id.edit_cost);

        subtotalEditText = view.findViewById(R.id.edit_sub_total);
        taxRateEditText = view.findViewById(R.id.edit_tex_rate);
        ;
        totalTaxEditText = view.findViewById(R.id.edit_total_tax);
        otherEditText = view.findViewById(R.id.edit_other);
        totalEditText = view.findViewById(R.id.edit_total);

        questionsNameEditText = view.findViewById(R.id.edit_questions_name);
        questionEmailEditText = view.findViewById(R.id.edit_questions_email);
        questionsPhoneEditText = view.findViewById(R.id.edit_questions_phone);
        questionsWebEditText = view.findViewById(R.id.edit_questions_web);
        procedureEditText = view.findViewById(R.id.edit_procedure);


    }

    public FragmentInvoices(int patientId) {
        this.id=patientId;
     /*   String id = String.valueOf(patientId);
        patientIdTextView.setText(id);*/

        // Required empty public constructor
    }
    public FragmentInvoices( ) {}



        // TODO: Rename and change types and number of parameters
   /* public static FragmentInvoices newInstance(String param1, String param2) {
        FragmentInvoices fragment = new FragmentInvoices();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aVoid(id);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_invoices, container, false);




        init();




        String patientIdstring =String.valueOf(id);
        patientIdTextView.setText(patientIdstring);
        aVoid(id);






                dateOfSvcTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        dateOfSvcTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        invoiceDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        invoiceDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        dateDueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        dateDueTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });


        billSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





            }
        });


        svcAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        totalAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        questionSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values=new ContentValues();
                totalSave();
                svcSave();
                questionsSend();


                billToSave();
                saveDate();

                Log.e("dsa","data::::"+values);

                Uri newUri =
                        getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
                if (newUri == null) {
                    Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;


    }
    public void saveDate(){

        final String patientIdString= patientIdTextView.getText().toString().trim();
        final String dateOfSvcString =dateOfSvcTextView.getText().toString().trim();
        final String invoiceDateString =invoiceDateTextView.getText().toString().trim();
        final  String dataOfDueString =dateDueTextView.getText().toString().trim();

        if (mCurrentPatientInvoicesUri == null &&
            TextUtils.isEmpty(patientIdString) &&
            TextUtils.isEmpty(dateOfSvcString) &&
            TextUtils.isEmpty(invoiceDateString) &&
            TextUtils.isEmpty(dataOfDueString))
        {
        return ;
    }




        if (TextUtils.isEmpty(patientIdString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_PATIENT_ID, patientIdString);
        }
        if (TextUtils.isEmpty(dateOfSvcString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC, dateOfSvcString);
        }
        if (TextUtils.isEmpty(invoiceDateString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE, invoiceDateString);
        }
        if (TextUtils.isEmpty(dataOfDueString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_DATE_DUE, dataOfDueString);
        }




    }

    public void billToSave() {


        String billNameString = billNameEditText.getText().toString().trim();
        String billAddressString = billAddressEditText.getText().toString().trim();
        String billEmailString = billEmailEditText.getText().toString().trim();
        String billPhoneString = billPhoneEditText.getText().toString().trim();
        String billFaxString = billFixEditText.getText().toString().trim();


        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(billNameString) &&
                TextUtils.isEmpty(billAddressString) &&
                TextUtils.isEmpty(billEmailString) &&
                TextUtils.isEmpty(billPhoneString) &&
                TextUtils.isEmpty(billFaxString)) {
            return ;
        }
        //Integer phoneNumber =Integer.parseInt(billPhoneString);
        //  Integer fixNumber = Integer.parseInt(billFaxString);


        if (TextUtils.isEmpty(billNameString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME, billNameString);
        }
        if (TextUtils.isEmpty(billAddressString)) {
            Toast.makeText(getContext(), "COLUMN_BILL_TO_ADDRESS is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS, billAddressString);
        }
        if (TextUtils.isEmpty(billEmailString)) {
            Toast.makeText(getContext(), "COLUMN_BILL_TO_EMAIL is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL, billEmailString);
        }
        if (TextUtils.isEmpty(billPhoneString)) {
            Toast.makeText(getContext(), "COLUMN_BILL_TO_PHONE is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE, billPhoneString);
        }
        if (TextUtils.isEmpty(billFaxString)) {
            Toast.makeText(getContext(), "COLUMN_BILL_TO_FAX is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX, billFaxString);
        }




    }

    public  void svcSave() {



        String svcIdString = svcIdEditText.getText().toString().trim();
        String medicalServiceString = medicalServicesEditText.getText().toString().trim();
        String medicationString = medicationEditText.getText().toString().trim();
        String costString = costEditText.getText().toString().trim();


        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(svcIdString) &&
                TextUtils.isEmpty(medicalServiceString) &&
                TextUtils.isEmpty(medicationString) &&
                TextUtils.isEmpty(costString)) {
            return ;
        }


        if (TextUtils.isEmpty(svcIdString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_SVC_ID, svcIdString);
        }
        if (TextUtils.isEmpty(medicalServiceString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES, medicalServiceString);
        }
        if (TextUtils.isEmpty(medicationString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_MEDICATION, medicationString);
        }
        if (TextUtils.isEmpty(costString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_COST, costString);
        }



    }


    public void totalSave() {


        String subTotalString = subtotalEditText.getText().toString().trim();
        String taxRateString = taxRateEditText.getText().toString().trim();
        String totalTaxString = totalTaxEditText.getText().toString().trim();
        String otherString = otherEditText.getText().toString().trim();
        String totalString = totalEditText.getText().toString().trim();

        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(subTotalString) &&
                TextUtils.isEmpty(taxRateString) &&
                TextUtils.isEmpty(totalTaxString) &&
                TextUtils.isEmpty(otherString) &&
                TextUtils.isEmpty(totalString)) {
            return ;
        }

        if (TextUtils.isEmpty(subTotalString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_SUBTOTAL, subTotalString);
        }
        if (TextUtils.isEmpty(taxRateString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_TAX_RATE, taxRateString);
        }
        if (TextUtils.isEmpty(totalTaxString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_TOTAL_TAX, totalTaxString);
        }
        if (TextUtils.isEmpty(otherString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_OTHER, otherString);
        }
        if (TextUtils.isEmpty(totalString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_TOTAL, totalString);
        }

    }

    public void questionsSend() {

        String questionsNameString = questionsNameEditText.getText().toString().trim();
        String questionsPhoneString = questionsPhoneEditText.getText().toString().trim();
        String questionsEmailString = questionEmailEditText.getText().toString().trim();
        String questionsWebString = questionsWebEditText.getText().toString().trim();
       String procedureString= procedureEditText.getText().toString().trim();

        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(questionsNameString) &&
                TextUtils.isEmpty(questionsPhoneString) &&
                TextUtils.isEmpty(questionsEmailString) &&
                TextUtils.isEmpty(questionsWebString)) {
            return ;
        }

        if (TextUtils.isEmpty(questionsNameString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_NAME, questionsNameString);
        }
        if (TextUtils.isEmpty(questionsPhoneString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_PHONE, questionsPhoneString);
        }
        if (TextUtils.isEmpty(questionsEmailString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_EMAIL, questionsEmailString);
        }
        if (TextUtils.isEmpty(questionsWebString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_WEB, questionsWebString);
        }
        if (TextUtils.isEmpty(procedureString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.InvoicesEntry.COLUMN_PROCEDURE, procedureString);
        }

    }

    public void aVoid(int a){

        Uri uri = ImsContract.InvoicesEntry.CONTENT_URI;

        Cursor cursor =
                getActivity().getContentResolver().
                query(uri, new String[]{ImsContract.InvoicesEntry.COLUMN_PATIENT_ID},
                        String.valueOf(a), null, null);

        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

        int   x = cursor.getInt(cursor.getColumnIndex(ImsContract.InvoicesEntry._ID));

            if (x > 0) {
                Log.e(TAG , "aasdaddsdasd::"+a);


            }else if(x==0){
                Log.e(TAG , "mynoterr::"+a);


            }

            cursor.moveToNext();

        }
        cursor.close();



    }
    private static final String TAG = FragmentInvoices.class.getSimpleName();

}
