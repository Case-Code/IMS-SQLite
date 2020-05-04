package com.example.ims;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ims.adapter.PatientProgressCursorAdapter;
import com.example.ims.adapter.RadiologyCursorAdapter;
import com.example.ims.data.ImsContract;
import com.google.android.material.navigation.NavigationView;

public class RadiologyLaboratoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
   LoaderManager.LoaderCallbacks<Cursor>
{
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    //List of clients referred to the radiology laboratory
    private EditText nameSearchEditView;
    private ListView patientNameListView;
    // id Loader
    private static final int RADIOLOGY_LOADER = 171;
    private static final int RADIOLOGY_SEARCH_LOADER = 160;
    private static final int PATIENT_RECORD_LOADER = 172;

    //add name uri equals null
    Uri mUri, mPatientUri;

    //  use add data by cursor adapter
    PatientProgressCursorAdapter mPatientProgressCursorAdapter;
    RadiologyCursorAdapter mRadiologyCursorAdapter;

    //field data
    private String patientSearch;
    private String firstName;
    private String lastName;
    private int patientId;


    //Patient records
    private TextView billToNameTextView;
    private TextView dateOfBirthTextView;
    private TextView patientIdTextView;
    private TextView medicalRecordIdTextView;
    private TextView nextAppointmentDateTextView;
    private TextView nextTreatmentPlanReviewDateTextView;
    private TextView physicianSignatureTextView;
    private TextView dateSignedTextView;

    //Patient progress
    private ListView progressNotesListView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiologylaboratory);

        init();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionMenuImageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mDrawerLayout.openDrawer(GravityCompat.START);

            }
        });

        mRadiologyCursorAdapter = new RadiologyCursorAdapter(this, null);
        patientNameListView.setAdapter(mRadiologyCursorAdapter);
        patientNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id)
            {

                mUri = ContentUris.withAppendedId(ImsContract.PatientDataToAnalysisEntry.CONTENT_URI, id);
                patientId = getIdPatient(mUri, RadiologyLaboratoryActivity.this);

                if (view.getId() != 0)
                {
                    getLoaderManager().destroyLoader(PATIENT_RECORD_LOADER);
                    getLoaderManager().destroyLoader(121);
                }
                //TODO::get patient data and progress
                mPatientUri = ContentUris.withAppendedId(ImsContract.PatientRecordsEntry.CONTENT_URI, patientId);
                getLoaderManager().initLoader(PATIENT_RECORD_LOADER, null, RadiologyLaboratoryActivity.this);

                getLoaderManager().initLoader(121, null, RadiologyLaboratoryActivity.this);


            }
        });

        mPatientProgressCursorAdapter = new PatientProgressCursorAdapter(this, null);
        progressNotesListView.setAdapter(mPatientProgressCursorAdapter);

        patientNameListView.setTextFilterEnabled(true);
        nameSearchEditView.addTextChangedListener(new TextWatcher()
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
                    getLoaderManager().restartLoader(RADIOLOGY_LOADER, null, RadiologyLaboratoryActivity.this);
                }
                else
                {
                    patientSearch = charSequence.toString();

                    firstName = getFirstName(patientSearch);

                    lastName = getLastName(patientSearch);

                    getLoaderManager().restartLoader(RADIOLOGY_LOADER, null, RadiologyLaboratoryActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });
        getLoaderManager().initLoader(RADIOLOGY_LOADER, null, this);


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

    // initial
    private void init()
    {
        mDrawerLayout = findViewById(R.id.activity_radiology_laboratory);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);


        //List of clients referred to the radiology laboratory
        nameSearchEditView = findViewById(R.id.edit_radiology_namesearch);
        patientNameListView = findViewById(R.id.list_radiology_patient);

        //Patient records
        billToNameTextView = findViewById(R.id.text_radiology_billtoname);
        dateOfBirthTextView = findViewById(R.id.text_radiology_dateofbirth);
        patientIdTextView = findViewById(R.id.text_radiology_patient_id);
        medicalRecordIdTextView = findViewById(R.id.text_radiology_medicalrecordid);
        nextAppointmentDateTextView = findViewById(R.id.text_radiology_nextappointmentdate);
        nextTreatmentPlanReviewDateTextView = findViewById(R.id.text_radiology_nexttreatmentplanreviewdate);
        physicianSignatureTextView = findViewById(R.id.text_radiology_physiciansignature);
        dateSignedTextView = findViewById(R.id.text_radiology_datesigned);

        //Patient progress
        progressNotesListView = findViewById(R.id.list_radiology_progressnotes);
    }

    public int getIdPatient(Uri uri, Context context)
    {
        int id = 0;

        String[] projection = {ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID,};
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
            int
               patientIdColumnIndex =
               cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID);


            // patient id
            id = cursor.getInt(patientIdColumnIndex);

            if (id == 0) return id;
        }
        return id;

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle)
    {
        if (id == RADIOLOGY_LOADER)
        {
            if (patientSearch == null)
            {

                String[] projection = {
                   ImsContract.PatientDataToRadiologyEntry._ID,
                   ImsContract.PatientDataToRadiologyEntry.COLUMN_PATIENT_ID,
                   ImsContract.PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION,
                   ImsContract.PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE
                };
                return new CursorLoader(
                   this,
                   ImsContract.PatientDataToRadiologyEntry.CONTENT_URI,
                   projection,
                   null,
                   null,
                   null);
            }
            else
            {
                return getRadiologySearch(firstName, lastName);

            }
        }
        else if (id == PATIENT_RECORD_LOADER)
        {

            String[] projection =
               {
                  ImsContract.PatientRecordsEntry.COLUMN_BILL_TO_NAME,
                  ImsContract.PatientRecordsEntry.COLUMN_DATE_OF_BIRTH,
                  ImsContract.PatientRecordsEntry.COLUMN_PATIENT_ID,
                  ImsContract.PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID,
                  ImsContract.PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE,
                  ImsContract.PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE,
                  ImsContract.PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE,
                  ImsContract.PatientRecordsEntry.COLUMN_DATE_SIGNED,
                  ImsContract.PatientRecordsEntry.COLUMN_X_RAY_IMAGE
               };
            Log.e("uri", "a" + mPatientUri);

            return new CursorLoader(this,
               mPatientUri,
               projection,
               null, null, null);


        }
        else
        {
            if (patientId <= 0) return null;

            String[] projection = {ImsContract.PatientProgressEntry._ID,
               ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES,
               ImsContract.PatientProgressEntry.COLUMN_DATE,};

            return new CursorLoader(
               this,
               ImsContract.PatientProgressEntry.CONTENT_URI,
               projection,
               ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID + " =" + patientId
               , null, null
            );


        }

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
        int id = loader.getId();

        if (id == RADIOLOGY_LOADER)
        {
            if (mUri == null)
            {
                mRadiologyCursorAdapter.swapCursor(cursor);
            }
        }
        else if (id == PATIENT_RECORD_LOADER)
        {
            //TODO:: name text view in activity
            String name = getString(R.string.bill_to_name);
            String date = getString(R.string.date_of_birth);
            String pid = getString(R.string.patient_id);
            String rid = getString(R.string.medical_record_id);
            String nodate = getString(R.string.next_appointment_date);
            String treatment = getString(R.string.next_treatment_plan_review_date);
            String physician = getString(R.string.physician_signature);
            String signed = getString(R.string.date_signed);

            if (cursor.moveToNext())
            {

                int billToNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_BILL_TO_NAME);
                int dateOfBirthColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_DATE_OF_BIRTH);
                int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_PATIENT_ID);
                int medicalRecordIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID);
                int nextAppointmentColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE);
                int nextTreatmentPlanColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE);
                int physicianSignatureColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE);
                int dateSignedColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry.COLUMN_DATE_SIGNED);
                //  int xRayImageColumnIndex = cursor.getColumnIndex(ImsContract.PatientRecordsEntry
                //  .COLUMN_X_RAY_IMAGE);

                String billToName = cursor.getString(billToNameColumnIndex);
                String dateOfBirth = cursor.getString(dateOfBirthColumnIndex);
                String patientId = cursor.getString(patientIdColumnIndex);
                String medicalRecordId = cursor.getString(medicalRecordIdColumnIndex);
                String nextAppointment = cursor.getString(nextAppointmentColumnIndex);
                String nextTreatmentPlan = cursor.getString(nextTreatmentPlanColumnIndex);
                String physicianSignature = cursor.getString(physicianSignatureColumnIndex);
                String dateSigned = cursor.getString(dateSignedColumnIndex);
                //  String xRayImage = cursor.getString(xRayImageColumnIndex);


                billToNameTextView.setText(name + ":" + billToName);
                dateOfBirthTextView.setText(date + ":" + dateOfBirth);
                patientIdTextView.setText(pid + ":" + patientId);
                medicalRecordIdTextView.setText(rid + ":" + medicalRecordId);
                nextAppointmentDateTextView.setText(nodate + ":" + nextAppointment);
                nextTreatmentPlanReviewDateTextView.setText(treatment + ":" + nextTreatmentPlan);
                physicianSignatureTextView.setText(physician + ":" + physicianSignature);
                dateSignedTextView.setText(signed + ":" + dateSigned);

            }
        }
        else
        {
            mPatientProgressCursorAdapter.swapCursor(cursor);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        int id = loader.getId();
        if (id == RADIOLOGY_LOADER)
        {
            if (mUri == null)
            {
                mRadiologyCursorAdapter.swapCursor(null);
            }
        }
        else if (id == PATIENT_RECORD_LOADER)
        {

            String name = getString(R.string.bill_to_name);
            String date = getString(R.string.date_of_birth);
            String pid = getString(R.string.patient_id);
            String rid = getString(R.string.medical_record_id);
            String nodate = getString(R.string.next_appointment_date);
            String treatment = getString(R.string.next_treatment_plan_review_date);
            String physician = getString(R.string.physician_signature);
            String signed = getString(R.string.date_signed);


            billToNameTextView.setText(name);
            dateOfBirthTextView.setText(date);
            patientIdTextView.setText(pid);
            medicalRecordIdTextView.setText(rid);
            nextAppointmentDateTextView.setText(nodate);
            nextTreatmentPlanReviewDateTextView.setText(treatment);
            physicianSignatureTextView.setText(physician);
            dateSignedTextView.setText(signed);

        }
        else
        {
            mPatientProgressCursorAdapter.swapCursor(null);

        }
    }

    //TODO search
    private CursorLoader getRadiologySearch(String firstName, String lastName)
    {
        // Run query
        Uri uri = ImsContract.PatientDataToRadiologyEntry.CONTENT_URI;

        String[] projection = {
           ImsContract.PatientDataToAnalysisEntry._ID,
           ImsContract.PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION,
           ImsContract.PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE,
           ImsContract.PatientDataToRadiologyEntry.COLUMN_PATIENT_ID};

        String
           selection =
           ImsContract.PatientDataToRadiologyEntry.COLUMN_PATIENT_ID + " = " + getPatientId(firstName, lastName);

        return new CursorLoader(
           this,
           uri,
           projection,
           selection,
           null,
           null);
    }

    private int getPatientId(String firstName, String lastName)
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
            selection =
               ImsContract.PatientEntry.COLUMN_FIRST_NAME + " LIKE '" + firstName + "%' AND " + ImsContract.PatientEntry.COLUMN_LAST_NAME + " LIKE '" + lastName + "%'";
        }

        // SQL query
        @SuppressLint("Recycle")
        Cursor cursor = getContentResolver().query(
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

}
