package com.example.ims;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentUris;
import android.content.ContentValues;
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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.CursorLoader;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ims.adapter.ClinicCursorAdapter;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;
import com.google.android.material.navigation.NavigationView;

public class TheDoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
   LoaderManager.LoaderCallbacks<Cursor>
{

    private final static String TAG = TheDoctorActivity.class.getSimpleName();

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    //List of clients referred to the clinic
    private EditText doctorSearchEditText;
    private ListView patientListView;
    private ClinicCursorAdapter mClinicCursorAdapter;
    public Uri mClinicUri;
    Uri mUri;

    //Doctor diagnosis
    private TextView firstlastnameTextView;
    private TextView dateofbirthTextView;
    private TextView dateofserviceTextView;
    private EditText diagnosisEditText;
    private EditText additionalNotesEditText;
    private EditText performingPhysicianSignatureEditText;
    private Button addButton;
    private Button printButton;

    private static final int CLINIC_LOADER = 1;

    private String mTheNamesOfTheClinics = null;

    private String patientSearch;
    private String firstName;
    private String lastName;
    private int patientId;

    private Spinner mTypeOfAnalysisSpinner;
    private Spinner mTypeOfRadiationSpinner;

    public static int mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN;
    public static int mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_UNKNOWN;

    // Dialog analysis lab, radiology laboratory and Pharmacy
    public static View mDialogTransferredToTheAnalysisLab;
    public static View mDialogTransferredToRadiology;
    public static View mDialogTransferredToThePharmacy;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thedoctor);

        // Initialization
        init();

        dateofserviceTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
                    {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        dateofserviceTextView.setText(date);
                    }
                };
                Utils.showDatePicker(TheDoctorActivity.this, dateSetListener);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addDoctor();
            }
        });

        // Navigation view
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mClinicCursorAdapter = new ClinicCursorAdapter(this, null);
        patientListView.setAdapter(mClinicCursorAdapter);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id)
            {

                mUri = ContentUris.withAppendedId(ImsContract.PatientDataToClinicsEntry.CONTENT_URI, id);
                patientId = getIdPatient(mUri, TheDoctorActivity.this);
                getPatient(patientId, TheDoctorActivity.this);

            }
        });

        patientListView.setTextFilterEnabled(true);

        doctorSearchEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if (charSequence.length() == 0)
                {
                    patientSearch = null;
                    getLoaderManager().restartLoader(CLINIC_LOADER, null, TheDoctorActivity.this);
                }
                else
                {
                    patientSearch = charSequence.toString();

                    firstName = getFirstName(patientSearch);
                    lastName = getLastName(patientSearch);

                    getLoaderManager().restartLoader(CLINIC_LOADER, null, TheDoctorActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
            }
        });

        getLoaderManager().initLoader(CLINIC_LOADER, null, this);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        Intent intent;
        int id = menuItem.getItemId();
        switch (id)
        {
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
    private void init()
    {
        mDrawerLayout = findViewById(R.id.activity_the_doctor);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);

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
        addButton = findViewById(R.id.button_doctor_save);
        printButton = findViewById(R.id.button_doctor_print);

        // Dialog analysis lab, radiology laboratory and Pharmacy
        mDialogTransferredToTheAnalysisLab = getLayoutInflater().inflate(R.layout.dialog_transferred_to_the_analysis_lab, null);
        mDialogTransferredToRadiology = getLayoutInflater().inflate(R.layout.dialog_transferred_to_radiology, null);
        mDialogTransferredToThePharmacy = getLayoutInflater().inflate(R.layout.dialog_transferred_to_pharmacy, null);
    }

    // Setup spinner types of analysis
    public void setupSpinnerTypesOfAnalysis(Context context)
    {
        ArrayAdapter typeOfAnalysisSpinnerAdapter = ArrayAdapter.createFromResource(
           context,
           R.array.array_analysis_options,
           android.R.layout.simple_spinner_item);

        typeOfAnalysisSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mTypeOfAnalysisSpinner.setAdapter(typeOfAnalysisSpinnerAdapter);
        mTypeOfAnalysisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection))
                {
                    if (selection.equals("Complete blood count"))
                    { // TODO chane the text
                        mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_COMPLETE_BLOOD_COUNT;
                    }
                    else if (selection.equals("Urine examination"))
                    {
                        mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_URINE_EXAMINATION;
                    }
                    else if (selection.equals("Stool examination"))
                    {
                        mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_STOOL_EXAMINATION;
                    }
                    else
                    {
                        mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN;
            }
        });
    }

    // Setup spinner types of radiation
    public void setupSpinnerTypesOfRadiation(Context context)
    {
        ArrayAdapter typeOfRadiationSpinnerAdapter = ArrayAdapter.createFromResource(
           context,
           R.array.array_radiology_options,
           android.R.layout.simple_spinner_item);

        typeOfRadiationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mTypeOfRadiationSpinner.setAdapter(typeOfRadiationSpinnerAdapter);
        mTypeOfRadiationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection))
                {
                    if (selection.equals("X-rays"))
                    { // TODO chane the text
                        mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_X_RAYS;
                    }
                    else if (selection.equals("CT scan"))
                    {
                        mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_CT_SCAN;
                    }
                    else if (selection.equals("Magnetic resonance imaging"))
                    {
                        mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_MAGNETIC_RESONANCE_IMAGING;
                    }
                    else if (selection.equals("Ultrasound"))
                    {
                        mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_ULTRASOUND;
                    }
                    else if (selection.equals("Sectional tomography of the positron emission"))
                    {
                        mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_SECTIONAL_TOMOGRAPHY_OF_THE_POSITRON_EMISSION;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                mTypesOfRadiology = ImsContract.PatientDataToRadiologyEntry.RADIOLOGY_UNKNOWN;
            }
        });
    }

    // Show transferred to the analysis lab dialog
    public void showTransferredToTheAnalysisLabDialog(Context context)
    {
        if (mDialogTransferredToTheAnalysisLab.getParent() != null)
        {
            ((ViewGroup) mDialogTransferredToTheAnalysisLab.getParent()).removeView(mDialogTransferredToTheAnalysisLab);
        }

        mTypeOfAnalysisSpinner = mDialogTransferredToTheAnalysisLab.findViewById(R.id.spinner_types_of_analysis);
        setupSpinnerTypesOfAnalysis(context);
    }

    // Show transferred to radiology dialog
    public void showTransferredToRadiologyDialog(Context context)
    {
        if (mDialogTransferredToRadiology.getParent() != null)
        {
            ((ViewGroup) mDialogTransferredToRadiology.getParent()).removeView(mDialogTransferredToRadiology);
        }

        mTypeOfRadiationSpinner = mDialogTransferredToRadiology.findViewById(R.id.spinner_transferredtoradiology_typesofradiation);
        setupSpinnerTypesOfRadiation(context);
    }

    // Show transferred to the pharmacy dialog
    public void showTransferredToThePharmacyDialog(Context context)
    {
        if (mDialogTransferredToThePharmacy.getParent() != null)
        {
            ((ViewGroup) mDialogTransferredToThePharmacy.getParent()).removeView(mDialogTransferredToThePharmacy);
        }
    }

    // Add doctor
    public void addDoctor()
    {

        String dateOfService = dateofserviceTextView.getText().toString().trim();
        String diagnosis = diagnosisEditText.getText().toString().trim();
        String additionalNotes = additionalNotesEditText.getText().toString().trim();
        String performingPhysicianSignature = performingPhysicianSignatureEditText.getText().toString().trim();

        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(dateOfService))
        {
            dateofserviceTextView.setError("please return a touch item to  date Of Service");
            return;
        }
        else
        {
            values.put(ImsContract.DoctorDiagnosisEntry.COLUMN_DATE_OF_SERVICE, dateOfService);
        }
        if (TextUtils.isEmpty(diagnosis))
        {
            diagnosisEditText.setError("please return a touch item to  diagnosis");
            return;
        }
        else
        {
            values.put(ImsContract.DoctorDiagnosisEntry.COLUMN_DIAGNOSIS, diagnosis);
        }
        if (TextUtils.isEmpty(additionalNotes))
        {
            additionalNotesEditText.setError("please return a touch item to  additional notes");
            return;
        }
        else
        {
            values.put(ImsContract.DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES, additionalNotes);
        }
        if (TextUtils.isEmpty(performingPhysicianSignature))
        {
            performingPhysicianSignatureEditText.setError("please return a touch item to  performing Physician Signature");
            return;
        }
        else
        {
            values.put(ImsContract.DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE, performingPhysicianSignature);
        }
        if (patientId <= 0)
        {
            additionalNotesEditText.setError("get id equals 0 return click item please ");
            return;
        }
        else
        {
            values.put(ImsContract.DoctorDiagnosisEntry.COLUMN_PATIENT_ID, patientId);
        }

        Uri newUri =
           getContentResolver().insert(ImsContract.DoctorDiagnosisEntry.CONTENT_URI, values);
        if (newUri == null)
        {
            Toast.makeText(this, getString(R.string.insert_doctor_failed), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, getString(R.string.insert_doctor_successful), Toast.LENGTH_SHORT).show();
        }

    }

    // Get patient id
    private int getPatientId(String firstName, String lastName, Context context)
    {
        int patientId = 0;

        // Uri
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;

        // Column name
        String[] projection = {
           ImsContract.PatientEntry._ID,
           ImsContract.PatientEntry.COLUMN_FIRST_NAME,
           ImsContract.PatientEntry.COLUMN_LAST_NAME};

        // Selection
        // first_name like '%' AND last_name like '%'
        // TODO add last name in search
        String selection = null;
        if (firstName != null && lastName == null)
        {
            selection = ImsContract.PatientEntry.COLUMN_FIRST_NAME + " LIKE '" + firstName + "%'";
        }
        else if (firstName == null && lastName != null)
        {
            selection = ImsContract.PatientEntry.COLUMN_LAST_NAME + " LIKE '" + lastName + "%'";
        }
        else if (firstName != null && lastName != null)
        {
            selection = ImsContract.PatientEntry.COLUMN_FIRST_NAME + " LIKE '" + firstName + "%' AND " + ImsContract.PatientEntry.COLUMN_LAST_NAME + " LIKE '" + lastName + "%'";
        }

        // SQL query
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(
           uri,
           projection,
           selection,
           null,
           null);

        assert cursor != null;
        while (cursor.moveToNext())
        {

            // Id column index
            int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry._ID);

            // Id
            int id = cursor.getInt(patientIdColumnIndex);

            if (id >= 0)
            {
                patientId = id;
            }
            else
            {
                patientSearch = null;
            }
        }
        return patientId;
    }

    // Get get doctor search clinic : getContacts
    private CursorLoader getDoctorSearch(String firstName, String lastName, Context context)
    {
        // Run query
        Uri uri = ImsContract.PatientDataToClinicsEntry.CONTENT_URI;

        String[] projection = {
           ImsContract.PatientDataToClinicsEntry._ID,
           ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME,
           ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE,
           ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID};

        String selection = ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID + " = " + getPatientId(firstName, lastName, context);

        return new CursorLoader(
           context,
           uri,
           projection,
           selection,
           null,
           null);
    }

    // Get first name
    public static String getFirstName(String username)
    {

        String un = username;

        int indexOf = username.indexOf(" ");

        if (indexOf == -1)
        {
            un = username;
        }
        else
        {
            un = username.substring(0, indexOf);
        }
        return un;
    }

    // Get last name
    public static String getLastName(String username)
    {

        String un = username;

        int indexOf = username.indexOf(" ");
        if (indexOf == -1)
        {
            un = null;
        }
        else
        {
            un = username.substring(indexOf + 1, username.length());
        }
        return un;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args)
    {
        if (patientSearch == null)
        {
            String[] projection = {
               ImsContract.PatientDataToClinicsEntry._ID,
               ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME,
               ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE,
               ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID};

            return new CursorLoader(
               this,
               ImsContract.PatientDataToClinicsEntry.CONTENT_URI,
               projection,
               null,
               null,
               null);
        }
        else
        {
            return getDoctorSearch(firstName, lastName, this);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Cursor data)
    {
        int id = loader.getId();
        if (id == CLINIC_LOADER)
        {
            if (mClinicUri == null)
            {
                mClinicCursorAdapter.swapCursor(data);
            }
            else
            {
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader)
    {
        int id = loader.getId();
        if (id == CLINIC_LOADER)
        {
            if (mClinicUri == null)
            {
                mClinicCursorAdapter.swapCursor(null);
            }
        }
    }

    private void getPatient(int patientId, Context context)
    {

        Uri uri = ImsContract.PatientEntry.CONTENT_URI;

        // Column name
        String[] projection = {
           ImsContract.PatientEntry.COLUMN_FIRST_NAME,
           ImsContract.PatientEntry.COLUMN_LAST_NAME,
           ImsContract.PatientEntry.COLUMN_BIRTH_DATE};

        // Selection
        String selection = ImsContract.PatientEntry._ID + " =" + patientId;

        // SQL query
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(
           uri,
           projection,
           selection,
           null,
           null);

        assert cursor != null;
        while (cursor.moveToNext())
        {

            // Firs name and last name column index
            int patientFirsNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);
            int patientLastNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME);
            int dateOfBirthColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_BIRTH_DATE);

            // Firs name and last name
            String patientFirsName = cursor.getString(patientFirsNameColumnIndex);
            String patientLastName = cursor.getString(patientLastNameColumnIndex);
            String dateOfBirth = cursor.getString(dateOfBirthColumnIndex);

            if (patientFirsName != null & patientLastName != null)
            {//update code
                firstlastnameTextView.setText(patientFirsName.concat(" " + patientLastName));
                dateofbirthTextView.setText(dateOfBirth);

                // patientName = patientFirsName.concat(" " + patientLastName);
            }
        }
    }

    public int getIdPatient(Uri uri, Context context)
    {
        int id = 0;

        String[] projection = {ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID,};
        // select  COLUMN_PATIENT_ID;
        Cursor cursor = context.getContentResolver().query(
           uri,
           projection,
           null,
           null,
           null);

        assert cursor != null;
        while (cursor.moveToNext())
        {

            // patient id column index
            int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID);


            // patient id
            int idPatient = cursor.getInt(patientIdColumnIndex);

            if (id == 0) return idPatient;
        }

        return id;
    }

}