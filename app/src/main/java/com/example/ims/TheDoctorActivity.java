package com.example.ims;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.Loader;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.CursorLoader;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ims.adapter.ClinicCursorAdapter;
import com.example.ims.data.ImsContract;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TheDoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static View mDialogTransferredToClinicsView;
    private Spinner mTheNameOfTheClinicSpinner;

    //List of clients referred to the clinic
    private EditText searchClinicAutoCompleteTextView;
    private ListView patientListView;
    ClinicCursorAdapter mClinicCursorAdapter;
    public Uri mClinicUri;


    //Doctor diagnosis
    private AutoCompleteTextView patienttransformationAutoCompleteTextView;
    private ListView transformationListView;
    private TextView firstlastnameTextView;
    private TextView dateofbirthTextView;
    private TextView dateofserviceTextView;
    private EditText diagnosisEditText;
    private EditText additionalNotesEditText;
    private EditText performingPhysicianSignatureEditText;
    private Button saveButton;
    private Button printButton;

    private static final int CLINIC_LOADER = 141;

    private String mTheNamesOfTheClinics = "null";

    public void save() {
        String doctorDdDateOfServiceString = dateofserviceTextView.getText().toString().trim();
        String doctorDdDiagnosisString = diagnosisEditText.getText().toString().trim();
        String doctorDdAdditionalNotesString = additionalNotesEditText.getText().toString().trim();
        String doctorDdPerformingPhysicianSignatureString = performingPhysicianSignatureEditText.getText().toString().trim();
        ContentValues values = new ContentValues();

      /*  if (TextUtils.isEmpty(doctorDdDateOfServiceString)) {
        } else {
            values.put(ImsContract.DoctorDiagnosisEntry.,doctorDdDateOfServiceString );
        }
        if (TextUtils.isEmpty()) {
        } else {
            values.put(ImsContract.InvoicesEntry., );
        }
        if (TextUtils.isEmpty()) {
        } else {
            values.put(ImsContract.InvoicesEntry., );
        }
        if (TextUtils.isEmpty()) {
        } else {
            values.put(ImsContract.InvoicesEntry., );
        }
        if (TextUtils.isEmpty()) {
        } else {
            values.put(ImsContract.InvoicesEntry., );
        }*/
    }
   /* public String getNamePatient( String patientId ){
        Cursor cursor;
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        cursor =getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME,ImsContract.PatientEntry.COLUMN_LAST_NAME},
                ImsContract.PatientEntry.COLUMN_FIRST_NAME + " like '%"+patientId+"%'" , null, null);
        cursor.moveToFirst();
        while (cursor.) {

            String patientFName = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));
            String patientLName = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME));

                return  patientFName+" "+patientLName ;
            }
        }

    }*/

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thedoctor);

        init();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mClinicCursorAdapter = new ClinicCursorAdapter(this, null);
        patientListView.setAdapter(mClinicCursorAdapter);
        patientListView.setTextFilterEnabled(true);


//        mActionMenuImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDrawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

//        mButtonClinic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showTransferredToClinicsDialog();
//            }
//        });
        //TODO look in code this error in run code
        mClinicCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence charSequence) {
                Uri uri = ImsContract.PatientDataToClinicsEntry.CONTENT_URI;


                Cursor c = getContentResolver().query(uri, new String[]{ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID, ImsContract.PatientDataToClinicsEntry._ID},
                        ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID + " LIKE '%" + charSequence + "%'", null, null);


                return c;
            }

        });
        searchClinicAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (mClinicCursorAdapter != null) {
                    mClinicCursorAdapter.getFilter().filter(charSequence);
                    mClinicCursorAdapter.notifyDataSetChanged();

                    Log.e("search::", "" + charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }

        });
        getLoaderManager().initLoader(CLINIC_LOADER, null, this);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.menu_item_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_reception:
                intent = new Intent(this, ReceptionActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_the_doctor:
                intent = new Intent(this, TheDoctorActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_analysis_lab:
                intent = new Intent(this, AnalysisLabActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_radiology_laboratory:
                intent = new Intent(this, RadiologyLaboratoryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_the_pharmacy:
                intent = new Intent(this, ThePharmacyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_financial_accounts:
                intent = new Intent(this, FinancialAccountsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_personnel:
                intent = new Intent(this, PersonnelActivity.class);
                startActivity(intent);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // initial
    private void init() {
        mDrawerLayout = findViewById(R.id.activity_the_doctor);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mDialogTransferredToClinicsView = getLayoutInflater().inflate(R.layout.dialog_doctor_transferred_to_clinics, null);

        searchClinicAutoCompleteTextView = findViewById(R.id.actv_doctor_searchclinic);
        patientListView = findViewById(R.id.list_doctor_patient);

        //Doctor diagnosis
        patienttransformationAutoCompleteTextView = findViewById(R.id.actv_doctor_patienttransformation);
        transformationListView = findViewById(R.id.list_doctor_transformation);
        firstlastnameTextView = findViewById(R.id.text_doctor_firstlastname);
        dateofbirthTextView = findViewById(R.id.text_doctor_dateofbirth);
        dateofserviceTextView = findViewById(R.id.text_doctor_dateofservice);
        diagnosisEditText = findViewById(R.id.edit_doctor_diagnosis);
        additionalNotesEditText = findViewById(R.id.edit_doctor_additionalnotes);
        performingPhysicianSignatureEditText = findViewById(R.id.edit_doctor_performingphysiciansignature);
        saveButton = findViewById(R.id.button_doctor_save);
        printButton = findViewById(R.id.button_doctor_print);
    }

    private void setupSpinnerTheNamesOfTheClinics(Context context) {
        ArrayAdapter theNameOfTheClinicSpinnerAdapter = ArrayAdapter.createFromResource(
                context,
                R.array.array_clinics_options,
                android.R.layout.simple_spinner_item);

        theNameOfTheClinicSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mTheNameOfTheClinicSpinner.setAdapter(theNameOfTheClinicSpinnerAdapter);
        mTheNameOfTheClinicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Complete blood count")) { // TODO chane the text
                        mTheNamesOfTheClinics = "Complete blood count";
                    } else if (selection.equals("Urine examination")) {
                        mTheNamesOfTheClinics = "Urine examination";
                    } else if (selection.equals("Stool examination")) {
                        mTheNamesOfTheClinics = "Stool examination";
                    } else {
                        mTheNamesOfTheClinics = "Unknown";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTheNamesOfTheClinics = "Unknown";
            }
        });
    }


    public ArrayList<ImsContract.PatientEntry> searchPatient() {
        ArrayList<ImsContract.PatientEntry> arr = new ArrayList<>();

        if (TextUtils.isEmpty(arr.toString())) {
            return null;
        }

        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME}, null, null, "ASC");
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
        }
        if (null == cursor) {

        } else if (cursor.getCount() < 1) {

        } else {
            String resultname = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));
            ImsContract.PatientEntry p = new ImsContract.PatientEntry();
            if (p != null) {
                arr.add(p);
            }
            cursor.moveToNext();
        }
        cursor.close();

        return arr;

    }

    public void showTransferredToClinicsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        searchClinicAutoCompleteTextView = mDialogTransferredToClinicsView.findViewById(R.id.tv_search_patient_clinic);
        ArrayList arr = new ArrayList();
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME},
                null, null, ImsContract.PatientEntry.COLUMN_FIRST_NAME);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            String patientname = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));

            if (patientname != null) {
                arr.add(patientname);
            }

            cursor.moveToNext();

        }

        ArrayAdapter<String> adabterEdittext = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        //   searchClinicAutoCompleteTextView.setAdapter(adabterEdittext);

        mTheNameOfTheClinicSpinner = mDialogTransferredToClinicsView.findViewById(R.id.spinner_doc_types_of_clinics);
        setupSpinnerTheNamesOfTheClinics(this);

        builder.setView(mDialogTransferredToClinicsView);
        builder.setTitle("Transferred to clinics");
        builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {

                    dialog.dismiss();

                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        if (id == CLINIC_LOADER) {

            String[] projection = {
                    ImsContract.PatientDataToClinicsEntry._ID
                    , ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME,
                    ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE,
                    ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID
            };

            return new CursorLoader(this,
                    ImsContract.PatientDataToClinicsEntry.CONTENT_URI,
                    projection,
                    null, null, null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Cursor data) {
        int id = loader.getId();
        if (id == CLINIC_LOADER) {
            if (mClinicUri == null) {
                mClinicCursorAdapter.swapCursor(data);
            } else {

            }


        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {
        int id = loader.getId();
        if (id == CLINIC_LOADER) {
            if (mClinicUri == null) {
                mClinicCursorAdapter.swapCursor(null);
            }


        }


    }

/*
    public void searchNamePatient(CharSequence query) {
        if (patientListView == null) return;

        mClinicCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            @Override
            public Cursor runQuery(CharSequence charSequence) {

                Cursor cursor;
                Uri uri = ImsContract.PatientEntry.CONTENT_URI;
                cursor =
                        getContentResolver().query(uri, new String[]{ImsContract.PatientEntry._ID,ImsContract.PatientEntry.COLUMN_FIRST_NAME, ImsContract.PatientEntry.COLUMN_LAST_NAME},
                                ImsContract.PatientEntry.COLUMN_FIRST_NAME + " LIKE '%" + charSequence+"%'", null, null);
                cursor.moveToFirst();
                while (cursor.isAfterLast() == false) {


                    charSequence =
                            cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));

                }

                    return cursor;

            }
        });

    }
*/

    public Cursor getFilterCursor(String namePatient) {
        Cursor cursor;

        int id = mClinicCursorAdapter.getIddPatient(namePatient, this);
        Log.e("mClinicCursorAdapter::", namePatient);
        Uri uri = ImsContract.PatientDataToClinicsEntry.CONTENT_URI;
        cursor =
                getContentResolver().query(uri, new String[]{ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID},
                        ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID + "=" + id, null, null);
        cursor.moveToFirst();


        while (cursor.isAfterLast() == false) {

            id = cursor.getInt(cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID));
            // namePatient =
            //       cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry._ID));

            if (id != 0) {
                Log.e("search::", "idpatient" + id);


                return cursor;
            }
        }


        return null;
    }

    public Cursor search() {
        Cursor c = null;

        return c;
    }

}