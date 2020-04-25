package com.example.ims.fragment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.ims.R;
import com.example.ims.adapter.FragmentInvoicesCursorAdapter;
import com.example.ims.adapter.InvoicesCursorAdapter;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;


public class FragmentInvoices extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int INVOICES_LOADER = 141;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int PATIENT_REGISTRATION_LOADER = 1;
    private static final String TAG = FragmentInvoices.class.getSimpleName();
    ImageView mEmptyInvoicesImageView;
    ContentValues mValues;
    Uri mCurrentPatientInvoicesUri;
    // TODO: Rename and change types of parameters
    int mIdPatient;
    View view;
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
    Uri mInvoicesUri;
    private Button saveInvoicesButton;
    private Button svcInvoicesButton;
    private TextView patientIdTextView;
    private TextView dateOfSvcTextView;
    private TextView invoiceDateTextView;
    private TextView dateDueTextView;
    private EditText billNameEditText;
    private EditText billPhoneEditText;
    private EditText billAddressEditText;
    private EditText billFixEditText;
    private EditText billEmailEditText;
    private EditText svcIdEditText;
    private EditText medicalServicesEditText;
    private EditText medicationEditText;
    private EditText costEditText;
    private EditText subtotalEditText;
    private EditText taxRateEditText;
    private EditText totalTaxEditText;
    private EditText otherEditText;
    private EditText totalEditText;
    private EditText questionsNameEditText;
    private EditText questionEmailEditText;
    private EditText questionsPhoneEditText;
    private EditText questionsWebEditText;
    private EditText procedureEditText;
    private ListView invoicesListView;
    private FragmentInvoicesCursorAdapter mFragmentInvoicesCursorAdapter;
    private String mParam2;


    public FragmentInvoices(int patientId) {
        this.mIdPatient = patientId;


        // Required empty public constructor
    }

    public FragmentInvoices() {
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentInvoices newInstance(String param1, String param2) {
        FragmentInvoices fragment = new FragmentInvoices();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void init() {

        //  mEmptyInvoicesImageView=view.findViewById(R.id.image_empty_invoice);
        //    svcAddButton = view.findViewById(R.id.button_svcadd);

        patientIdTextView = view.findViewById(R.id.text_invoices_patientId);
        dateOfSvcTextView = view.findViewById(R.id.text_invoices_dateOfSvc);
        invoiceDateTextView = view.findViewById(R.id.text_invoices_invoiceDate);
        dateDueTextView = view.findViewById(R.id.text_invoices_dateDue);

        billNameEditText = view.findViewById(R.id.edit_invoices_billtoname);
        billPhoneEditText = view.findViewById(R.id.edit_invoices_billtophone);
        billFixEditText = view.findViewById(R.id.edit_invoices_billtoix);
        billAddressEditText = view.findViewById(R.id.edit_invoices_billtoadress);
        billEmailEditText = view.findViewById(R.id.edit_invoices_billtoemail);

        svcIdEditText = view.findViewById(R.id.edit_invoices_svcid);
        medicalServicesEditText = view.findViewById(R.id.edit_invoices_medicalservices);
        medicationEditText = view.findViewById(R.id.edit_invoices_mediction);
        costEditText = view.findViewById(R.id.edit_invoices_cost);
        svcInvoicesButton = view.findViewById(R.id.button_invoices_save);

        invoicesListView = view.findViewById(R.id.list_invoices_svc);

        subtotalEditText = view.findViewById(R.id.edit_invoices_suptotal);
        taxRateEditText = view.findViewById(R.id.edit_invoices_taxrate);
        totalTaxEditText = view.findViewById(R.id.edit_invoices_totaltax);
        otherEditText = view.findViewById(R.id.edit_invoices_other);
        totalEditText = view.findViewById(R.id.edit_invoices_total);

        questionsNameEditText = view.findViewById(R.id.edit_invoices_questionsname);
        questionsPhoneEditText = view.findViewById(R.id.edit_invoices_questionsphone);
        questionEmailEditText = view.findViewById(R.id.edit_invoices_questionsemail);
        questionsWebEditText = view.findViewById(R.id.edit_invoices_questionsweb);

        procedureEditText = view.findViewById(R.id.edit_invoices_procedure);

        saveInvoicesButton = view.findViewById(R.id.button_invoices_save);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //
        // getLoaderManager().initLoader(0,null ,this);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_invoices, container, false);

        mFragmentInvoicesCursorAdapter = new FragmentInvoicesCursorAdapter(getActivity(), null);

        init();
        String id = "PATIENT ID";
        patientIdTextView.setText(id.concat("::" + String.valueOf(mIdPatient)));

        // minvoicesListView.setEmptyView(mEmptyInvoicesImageView);

        invoicesListView.setAdapter(mFragmentInvoicesCursorAdapter);


        dateOfSvcTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener
                        dateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog.OnDateSetListener
                        dateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
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
                DatePickerDialog.OnDateSetListener
                        dateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
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

        //TODO: write to code in save data to invoices
        saveInvoicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mValues = new ContentValues();
                totalSave();
                svcSave();
                questionsSend();


                billToSave();
                saveInvoicesPatient();
                mValues.put(ImsContract.InvoicesEntry.COLUMN_PATIENT_ID, mIdPatient);


                Log.e("dsa", "data::::" + mValues);

                Uri newUri =
                        getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, mValues);
                if (newUri == null) {
                    Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
                }

            }
        });
        // supportLoaderManager.initLoader(23, null ,this);


        return view;


    }

    public void saveInvoicesPatient() {

        //  final String patientIdString = patientIdTextView.getText().toString().trim();
        final String dateOfSvcString = dateOfSvcTextView.getText().toString().trim();
        final String invoiceDateString = invoiceDateTextView.getText().toString().trim();
        final String dataOfDueString = dateDueTextView.getText().toString().trim();

        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(dateOfSvcString) &&
                TextUtils.isEmpty(invoiceDateString) &&
                TextUtils.isEmpty(dataOfDueString)) {
            return;
        }


/*
        if (TextUtils.isEmpty(patientIdString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_PATIENT_ID, patientIdString);
        }
*/
        if (TextUtils.isEmpty(dateOfSvcString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC, dateOfSvcString);
        }
        if (TextUtils.isEmpty(invoiceDateString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE, invoiceDateString);
        }
        if (TextUtils.isEmpty(dataOfDueString)) {
            Toast.makeText(getContext(), "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_DATE_DUE, dataOfDueString);
        }


    }

    public void billToSave() {


        String billNameString = billNameEditText.getText().toString().trim();
        String billPhoneString = billPhoneEditText.getText().toString().trim();
        String billFaxString = billFixEditText.getText().toString().trim();
        String billAddressString = billAddressEditText.getText().toString().trim();
        String billEmailString = billEmailEditText.getText().toString().trim();


        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(billNameString) &&
                TextUtils.isEmpty(billAddressString) &&
                TextUtils.isEmpty(billEmailString) &&
                TextUtils.isEmpty(billPhoneString) &&
                TextUtils.isEmpty(billFaxString)) {
            return;
        }
        //Integer phoneNumber =Integer.parseInt(billPhoneString);
        //  Integer fixNumber = Integer.parseInt(billFaxString);


        if (TextUtils.isEmpty(billNameString)) {
            billNameEditText.setError("write on keyboard bill name ");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME, billNameString);
        }
        if (TextUtils.isEmpty(billPhoneString)) {
            billPhoneEditText.setError("write on keyboard  a bill to phone ");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE, billPhoneString);
        }
        if (TextUtils.isEmpty(billFaxString)) {
            billFixEditText.setError("write on keyboard  bill to fix");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX, billFaxString);
        }
        if (TextUtils.isEmpty(billAddressString)) {
            billAddressEditText.setError("write on keyboard bill to address ");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS, billAddressString);
        }
        if (TextUtils.isEmpty(billEmailString)) {
            billEmailEditText.setError("write on keyboard bill to email ");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL, billEmailString);
        }


    }

    public void svcSave() {


        String svcIdString = svcIdEditText.getText().toString().trim();
        String medicalServiceString = medicalServicesEditText.getText().toString().trim();
        String medicationString = medicationEditText.getText().toString().trim();
        String costString = costEditText.getText().toString().trim();


        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(svcIdString) &&
                TextUtils.isEmpty(medicalServiceString) &&
                TextUtils.isEmpty(medicationString) &&
                TextUtils.isEmpty(costString)) {
            return;
        }


        if (TextUtils.isEmpty(svcIdString)) {
            svcIdEditText.setError("write on keyboard svc id ");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_SVC_ID, svcIdString);
        }
        if (TextUtils.isEmpty(medicalServiceString)) {
            medicalServicesEditText.setError("write on keyboard medical service");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES, medicalServiceString);
        }
        if (TextUtils.isEmpty(medicationString)) {
            medicationEditText.setError("write to keyboard medication");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_MEDICATION, medicationString);
        }
        if (TextUtils.isEmpty(costString)) {
            costEditText.setError("write to keyboard cost ");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_COST, costString);
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
            return;
        }

        if (TextUtils.isEmpty(subTotalString)) {
            subtotalEditText.setError("write to keyboard  sub total");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_SUBTOTAL, subTotalString);
        }
        if (TextUtils.isEmpty(taxRateString)) {
            taxRateEditText.setError("write to keyboard tax rate");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_TAX_RATE, taxRateString);
        }
        if (TextUtils.isEmpty(totalTaxString)) {
            totalTaxEditText.setError("write to keyboard total tax");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_TOTAL_TAX, totalTaxString);
        }
        if (TextUtils.isEmpty(otherString)) {
            otherEditText.setError("write to keyboard other");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_OTHER, otherString);
        }
        if (TextUtils.isEmpty(totalString)) {
            totalEditText.setError("write to keyboard total");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_TOTAL, totalString);
        }

    }

    public void questionsSend() {

        String questionsNameString = questionsNameEditText.getText().toString().trim();
        String questionsPhoneString = questionsPhoneEditText.getText().toString().trim();
        String questionsEmailString = questionEmailEditText.getText().toString().trim();
        String questionsWebString = questionsWebEditText.getText().toString().trim();
        String procedureString = procedureEditText.getText().toString().trim();

        if (mCurrentPatientInvoicesUri == null &&
                TextUtils.isEmpty(questionsNameString) &&
                TextUtils.isEmpty(questionsPhoneString) &&
                TextUtils.isEmpty(questionsEmailString) &&
                TextUtils.isEmpty(questionsWebString)) {
            return;
        }

        if (TextUtils.isEmpty(questionsNameString)) {
            questionsNameEditText.setError("write to keyboard questions name");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_NAME, questionsNameString);
        }
        if (TextUtils.isEmpty(questionsPhoneString)) {
            questionsPhoneEditText.setError("write to keyboard questions phone");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_PHONE, questionsPhoneString);
        }
        if (TextUtils.isEmpty(questionsEmailString)) {
            questionEmailEditText.setError("write to keyboard questions email");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_EMAIL, questionsEmailString);

        }
        if (TextUtils.isEmpty(questionsWebString)) {
            questionsWebEditText.setError("write to keyboard questions web");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_WEB, questionsWebString);
        }
        if (TextUtils.isEmpty(procedureString)) {
            procedureEditText.setError("write to keyboard procedure");
        } else {
            mValues.put(ImsContract.InvoicesEntry.COLUMN_PROCEDURE, procedureString);
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        if (id == INVOICES_LOADER) {
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


            if (mIdPatient > 0) {
                return new CursorLoader(
                        this.getActivity(),
                        ImsContract.InvoicesEntry.CONTENT_URI,
                        projection,
                        ImsContract.InvoicesEntry.COLUMN_PATIENT_ID + " =" + mIdPatient

                        , null, null
                );
            } else {
                return new CursorLoader(
                        this.getActivity(),
                        mCurrentPatientInvoicesUri,
                        projection,
                        null
                        , null, null
                );

            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        int id = loader.getId();
        if (id == INVOICES_LOADER) {
            if (mCurrentPatientInvoicesUri == null) {

                mFragmentInvoicesCursorAdapter.swapCursor(data);

            } else {

                if (data == null || data.getCount() < 1) {
                    return;
                }

                if (data.moveToFirst()) {
                    int
                            dateOfSvcColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC);
                    int
                            invoiceColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE);
                    int
                            dateDueColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_DUE);
                    int
                            billToNameColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME);
                    int
                            billToAddressColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS);
                    int
                            billToPhoneColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE);
                    int
                            billToFaxColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX);
                    int
                            billToEmailColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL);
               /* int svcIdColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SVC_ID);
                int medicalServicesColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES);
                int medicationColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICATION);
                int costColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_COST);*/
                    int
                            supTotalColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SUBTOTAL);
                    int
                            taxRateColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TAX_RATE);
                    int
                            totalTaxColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TOTAL_TAX);
                    int
                            otherColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_OTHER);
                    int
                            totalColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TOTAL);
                    int
                            questionsNameColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_NAME);
                    int
                            questionsEmailColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_EMAIL);
                    int
                            questionsPhoneColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_PHONE);
                    int
                            questionsWebColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_WEB);
                    int
                            procedureColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_PROCEDURE);
                    int
                            patientIdColumnIndex =
                            data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_PATIENT_ID);


                    String dateOfSvc = data.getString(dateOfSvcColumnIndex);
                    String invoice = data.getString(invoiceColumnIndex);
                    String dateDue = data.getString(dateDueColumnIndex);
                    String billToName = data.getString(billToNameColumnIndex);
                    String billToAddress = data.getString(billToAddressColumnIndex);
                    String billToPhone = data.getString(billToPhoneColumnIndex);
                    String billToFax = data.getString(billToFaxColumnIndex);
                    String billToEmail = data.getString(billToEmailColumnIndex);
               /* String svcId = data.getString(svcIdColumnIndex);
                String medicalServices = data.getString(medicalServicesColumnIndex);
                String medication = data.getString(medicationColumnIndex);
                String cost = data.getString(costColumnIndex);*/
                    String supTotal = data.getString(supTotalColumnIndex);
                    String taxRate = data.getString(taxRateColumnIndex);
                    String totalTax = data.getString(totalTaxColumnIndex);
                    String other = data.getString(otherColumnIndex);
                    String total = data.getString(totalColumnIndex);
                    String questionsName = data.getString(questionsNameColumnIndex);
                    String questionsEmail = data.getString(questionsEmailColumnIndex);
                    String questionsPhone = data.getString(questionsPhoneColumnIndex);
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

               /* svcIdEditText.setText(svcId);
                medicalServicesEditText.setText(medicalServices);
                medicationEditText.setText(medication);
                costEditText.setText(cost);*/
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
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        int id = loader.getId();
        if (id == INVOICES_LOADER) {

            if (mCurrentPatientInvoicesUri == null) {
                mFragmentInvoicesCursorAdapter.swapCursor(null);


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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(INVOICES_LOADER, null, this);

        super.onActivityCreated(savedInstanceState);
    }


}
