package com.example.ims;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.app.LoaderManager;
import android.content.Loader;


import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.CursorLoader;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ims.adapter.ClinicCursorAdapter;
import com.example.ims.data.ImsContract;
import com.google.android.material.navigation.NavigationView;

public class TheDoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LoaderManager.LoaderCallbacks<Cursor> {

    private final static String TAG = TheDoctorActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static View mDialogTransferredToClinicsView;
    private Spinner mTheNameOfTheClinicSpinner;

    //List of clients referred to the clinic
    private EditText doctorSearchEditText;
    private ListView patientListView;
    private ClinicCursorAdapter mClinicCursorAdapter;
    public Uri mClinicUri;

    //Doctor diagnosis
    private TextView firstlastnameTextView;
    private TextView dateofbirthTextView;
    private TextView dateofserviceTextView;
    private EditText diagnosisEditText;
    private EditText additionalNotesEditText;
    private EditText performingPhysicianSignatureEditText;
    private Button saveButton;
    private Button printButton;

    private static final int CLINIC_LOADER = 1;
    private static final int CLINIC_SEARCH_LOADER = 2;

    private String mTheNamesOfTheClinics = null;

    private String doctorSearch;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thedoctor);

        init();

        // Navigation view
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mClinicCursorAdapter = new ClinicCursorAdapter(this, null);
        patientListView.setAdapter(mClinicCursorAdapter);
        patientListView.setTextFilterEnabled(true);

        doctorSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    doctorSearch = null;
                    getLoaderManager().restartLoader(CLINIC_LOADER, null, TheDoctorActivity.this);
                } else {
                    doctorSearch = charSequence.toString();
                    getLoaderManager().restartLoader(CLINIC_LOADER, null, TheDoctorActivity.this);
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

    // Initialization
    private void init() {
        mDrawerLayout = findViewById(R.id.activity_the_doctor);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mDialogTransferredToClinicsView = getLayoutInflater().inflate(R.layout.dialog_doctor_transferred_to_clinics, null);

        //List of clients referred to the clinic
        doctorSearchEditText = findViewById(R.id.edit_doctor_search);
        patientListView = findViewById(R.id.list_doctor_patient);

        //Doctor diagnosis
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

    // Get patient id
    private int getPatientId(String patientName, Context context) {
        int patientId = 0;

        // Uri
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        String[] selectionArgs = {patientName};

        // Column name
        String[] projection = {
                ImsContract.PatientEntry._ID,
                ImsContract.PatientEntry.COLUMN_FIRST_NAME,
                ImsContract.PatientEntry.COLUMN_LAST_NAME};

        // Selection
        // first_name like '%' AND last_name like '%'
        // TODO add last name in search
        String selection = ImsContract.PatientEntry.COLUMN_FIRST_NAME + "= ?";
            //   +" AND  " +ImsContract.PatientEntry.COLUMN_LAST_NAME + "= ?";
       // selectionArgs[]=paientName;
        selectionArgs[0]=patientName;
        // SQL query
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                selection,
                selectionArgs,
                null);

        assert cursor != null;
        while (cursor.moveToNext()) {

            // Id column index
            int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry._ID);

            // Id
            int id = cursor.getInt(patientIdColumnIndex);

            if (id >= 0) {
                patientId = id;
            } else {
                doctorSearch = null;
            }
        }
        return patientId;
    }

    // Get get doctor search clinic : getContacts
    private CursorLoader getDoctorSearch(String patientName, Context context) {
        // Run query
        Uri uri = ImsContract.PatientDataToClinicsEntry.CONTENT_URI;

        String[] projection = {
                ImsContract.PatientDataToClinicsEntry._ID,
                ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME,
                ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE,
                ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID};

        String selection = ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID + " = " + getPatientId(patientName, context);

        return new CursorLoader(context,
                uri,
                projection,
                selection,
                null,
                null);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        if (doctorSearch == null) {
            String[] projection = {
                    ImsContract.PatientDataToClinicsEntry._ID,
                    ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME,
                    ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE,
                    ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID};

            return new CursorLoader(this,
                    ImsContract.PatientDataToClinicsEntry.CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);

        } else {
            return getDoctorSearch(this.doctorSearch, this);
        }
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
}