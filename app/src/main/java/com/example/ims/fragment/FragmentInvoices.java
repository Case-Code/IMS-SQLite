package com.example.ims.fragment;

import android.app.DatePickerDialog;


import android.content.ContentValues;

import android.app.LoaderManager;
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
import android.content.Loader;



import androidx.fragment.app.Fragment;

import com.example.ims.adapter.InvoicesCursorAdapter;
import com.example.ims.R;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;


public class FragmentInvoices extends Fragment implements  LoaderManager.LoaderCallbacks<Cursor> {
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
    private static final int PATIENT_REGISTRATION_LOADER = 1;



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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_invoices, container, false);




        init();




        String patientIdstring =String.valueOf(id);
        patientIdTextView.setText(patientIdstring);







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

        getLoaderManager().initLoader()

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


        String billFaxString = billFixEditText.getText().toString().trim();
        String billNameString = billNameEditText.getText().toString().trim();
        String billAddressString = billAddressEditText.getText().toString().trim();
        String billEmailString = billEmailEditText.getText().toString().trim();
        String billPhoneString = billPhoneEditText.getText().toString().trim();


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

  /*  public void aVoid(int a){
        Uri uri = ImsContract.InvoicesEntry.CONTENT_URI;
        Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{ImsContract.InvoicesEntry.COLUMN_PATIENT_ID}, String.valueOf(a), null, null);

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

    }*/
    public Uri mCurrentPatientUri;
    private static final String TAG = FragmentInvoices.class.getSimpleName();

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection =
                {
                ImsContract.InvoicesEntry._ID,
                ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC,
                ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE,
                ImsContract.InvoicesEntry.COLUMN_DATE_DUE,
                ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME,
                ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS,
                ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE,
                ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX,
                ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL,
                ImsContract.InvoicesEntry.COLUMN_SVC_ID,
                ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES,
                ImsContract.InvoicesEntry.COLUMN_MEDICATION,
                ImsContract.InvoicesEntry.COLUMN_COST,
                ImsContract.InvoicesEntry.COLUMN_SUBTOTAL,
                ImsContract.InvoicesEntry.COLUMN_TAX_RATE,
                ImsContract.InvoicesEntry.COLUMN_TOTAL_TAX,
                ImsContract.InvoicesEntry.COLUMN_OTHER,
                ImsContract.InvoicesEntry.COLUMN_TOTAL,
                ImsContract.InvoicesEntry.COLUMN_QUESTIONS_NAME,
                ImsContract.InvoicesEntry.COLUMN_QUESTIONS_EMAIL,
                ImsContract.InvoicesEntry.COLUMN_QUESTIONS_PHONE,
                ImsContract.InvoicesEntry.COLUMN_QUESTIONS_WEB,
                ImsContract.InvoicesEntry.COLUMN_PROCEDURE,
                ImsContract.InvoicesEntry.COLUMN_PATIENT_ID
               };


        if (mCurrentPatientUri == null) {
            return new CursorLoader(this.getActivity(),
                    ImsContract.PatientEntry.CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        } else {
            return new CursorLoader(this.getActivity(),
                    mCurrentPatientUri,
                    projection,
                    null,
                    null,
                    null);
        }    }




    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (mCurrentPatientUri == null) {
            //  mPatientCursorAdapter.swapCursor(data);
        } else {

            if (data == null || data.getCount() < 1) {
                return;
            }

            if (data.moveToFirst()) {
                int dateOfSvcColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC);
                int invoiceColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE);
                int dateDueColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_DUE);
                int billToNameColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME);
                int billToAddressColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS);
                int billToPhoneColumnIndex = data.getColumnIndex( ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE);
                int billToFaxColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX);
                int billToEmailColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL);
                int svcIdColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SVC_ID);
                int medicalServicesColumnIndex = data.getColumnIndex( ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES);
                int medicationColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICATION);
                int costColumnIndex = data.getColumnIndex( ImsContract.InvoicesEntry.COLUMN_COST);
                int supTotalColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SUBTOTAL);
                int taxRateColumnIndex = data.getColumnIndex( ImsContract.InvoicesEntry.COLUMN_TAX_RATE);
                int totalTaxColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TOTAL_TAX);
                int otherColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_OTHER);
                int totalColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TOTAL);
                int questionsNameColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_NAME);
                int questionsEmailColumnIndex = data.getColumnIndex( ImsContract.InvoicesEntry.COLUMN_QUESTIONS_EMAIL);
                int questionsPhoneColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_PHONE);
                int questionsWebColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_WEB);
                int procedureColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_PROCEDURE);
                int patientIdColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_PATIENT_ID);



                String dateOfSvc = data.getString(dateOfSvcColumnIndex);
                String invoice = data.getString(invoiceColumnIndex);
                String dateDue = data.getString(dateDueColumnIndex);
                String billToName = data.getString(billToNameColumnIndex);
                String billToAddress= data.getString(billToAddressColumnIndex);
                String billToPhone = data.getString(billToPhoneColumnIndex);
                String billToFax= data.getString(billToFaxColumnIndex);
                String billToEmail= data.getString(billToEmailColumnIndex);
                String svcId= data.getString(svcIdColumnIndex);
                String medicalServices= data.getString(medicalServicesColumnIndex);
                String medication= data.getString(medicationColumnIndex);
                String  cost= data.getString(costColumnIndex);
                String  supTotal= data.getString(supTotalColumnIndex);
                String  taxRate= data.getString(taxRateColumnIndex);
                String totalTax = data.getString(totalTaxColumnIndex);
                String other = data.getString(otherColumnIndex);
                String total = data.getString(totalColumnIndex);
                String questionsName = data.getString(questionsNameColumnIndex);
                String questionsEmail = data.getString(questionsEmailColumnIndex);
                String  questionsPhone= data.getString(questionsPhoneColumnIndex);
                String questionsWeb = data.getString(questionsWebColumnIndex);
                String procedure = data.getString(procedureColumnIndex);
                String patientId = data.getString(patientIdColumnIndex);



                patientIdTextView.setText(patientId);
                dateOfSvcTextView.setText(dateOfSvc);
                invoiceDateTextView.setText(invoice);
                dateDueTextView.setText(dateDue);

                billFixEditText.setText(billToFax);
                billNameEditText.setText(billToName);
                billAddressEditText.setText(billToAddress);
                billEmailEditText.setText(billToEmail);
                billPhoneEditText.setText(billToPhone);

                svcIdEditText.setText(svcId);
                medicalServicesEditText.setText(medicalServices);
                medicationEditText.setText(medication);
                costEditText.setText(cost);
                subtotalEditText.setText(supTotal);
                taxRateEditText.setText(taxRate);
                totalTaxEditText.setText(totalTax);
                otherEditText.setText(other);
                totalEditText.setText(total);

                questionsNameEditText.setText(questionsName);
                questionsPhoneEditText.setText(questionsPhone);
                questionEmailEditText.setText(questionsEmail);
                questionsWebEditText.setText(questionsWeb);
                procedureEditText.setText(procedure);






            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        if (mCurrentPatientUri == null) {

        } else {
            patientIdTextView.setText("");
            dateOfSvcTextView.setText("");
            invoiceDateTextView.setText("");
            dateDueTextView.setText("");
            billFixEditText.setText("");
            billNameEditText.setText("");
            billAddressEditText.setText("");
            billEmailEditText.setText("");
            billPhoneEditText.setText("");

            svcIdEditText.setText("");
            medicalServicesEditText.setText("");
            medicationEditText.setText("");
            costEditText.setText("");
            subtotalEditText.setText("");
            taxRateEditText.setText("");
            totalTaxEditText.setText("");
            otherEditText.setText("");
            totalEditText.setText("");

            questionsNameEditText.setText("");
            questionsPhoneEditText.setText("");
            questionEmailEditText.setText("");
            questionsWebEditText.setText("");
            procedureEditText.setText("");






        }

    }




}
