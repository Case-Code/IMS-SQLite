package com.example.ims;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.app.LoaderManager;
import android.content.Loader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ims.adapter.AnalysisCursorAdapter;
import com.example.ims.data.ImsContract;
import com.google.android.material.navigation.NavigationView;

public class AnalysisLabActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,
        LoaderManager.LoaderCallbacks<Cursor>{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    //List of clients referred to the laboratory for analysis
    private EditText searchNameEditText;
    private ListView patientNameListView;

    //Patient records
    private  TextView prBillToNameTextView;
    private  TextView prDateOfBirthTextView;
    private  TextView prPatientIdTextView;
    private  TextView prMedicalRecordTextView;
    private  TextView prNextAppointmentDateTextView;
    private  TextView prNextTreatmentPlanReviewDateTextView;
    private  TextView prPhysicianSignatureTextView;
    private  TextView prDateSignedTextView;

    private ListView ppProgressNotesListView;

    AnalysisCursorAdapter mAnalysisCursorAdapter;
    Uri mUri;


    private String patientSearch;
    private String firstName;
    private String lastName;

    private static final int ANALYSIS_LOADER = 161;
    private static final int ANALYSIS_SEARCH_LOADER = 160;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysislab);

        init();

        mAnalysisCursorAdapter = new AnalysisCursorAdapter(this,null);
        patientNameListView.setAdapter(mAnalysisCursorAdapter);
        patientNameListView.setTextFilterEnabled(true);
        searchNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    patientSearch = null;
                    getLoaderManager().restartLoader(ANALYSIS_LOADER, null, AnalysisLabActivity.this);
                } else {
                    patientSearch = charSequence.toString();

                    firstName = getFirstName(patientSearch);
                    Log.e("na", getFirstName(patientSearch));
                    lastName = getLastName(patientSearch);

                    getLoaderManager().restartLoader(ANALYSIS_LOADER, null, AnalysisLabActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getLoaderManager().initLoader(ANALYSIS_LOADER, null, this);




        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


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
        mDrawerLayout = findViewById(R.id.activity_analysis_lab);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);


        //List of clients referred to the laboratory for analysis
        searchNameEditText =findViewById(R.id.edit_analysislab_searchname);
       patientNameListView =findViewById(R.id.list_analysislab_patientname);

        //Patient records
         prBillToNameTextView=findViewById(R.id.text_pr_bill_to_name);;
         prDateOfBirthTextView=findViewById(R.id.text_pr_date_of_birth);;
         prPatientIdTextView=findViewById(R.id.text_pr_patient_id);;
         prMedicalRecordTextView=findViewById(R.id.text_pr_medical_record);;
         prNextAppointmentDateTextView=findViewById(R.id.text_pr_next_appointment_date);;
         prNextTreatmentPlanReviewDateTextView=findViewById(R.id.text_pr_next_treatment_plan_review_date);;
         prPhysicianSignatureTextView=findViewById(R.id.text_pr_physician_signature);;
         prDateSignedTextView=findViewById(R.id.text_pr_date_signed);

         //Patient progress
        ppProgressNotesListView=findViewById(R.id.list_pp_progress_notes);;







    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle)
    {
            if(id==ANALYSIS_LOADER) {
                if (patientSearch == null) {

                    String[] projection = {
                            ImsContract.PatientDataToAnalysisEntry._ID,
                            ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID,
                            ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME,
                            ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE
                    };
                    return new CursorLoader(this,
                            ImsContract.PatientDataToAnalysisEntry.CONTENT_URI,
                            projection,
                            null,
                            null,
                            null);
                } else {
                    return getDoctorSearch(firstName, lastName, this);

                }
            }else {

            }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
        int id = loader.getId();
        if (id == ANALYSIS_LOADER) {
            if (mUri == null) {
                mAnalysisCursorAdapter.swapCursor(cursor);
            }else {


            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {
        int id = loader.getId();
        if (id == ANALYSIS_LOADER) {
            if (mUri == null) {
                mAnalysisCursorAdapter.swapCursor(null);
            }
        }
    }

    private int getPatientId(String firstName, String lastName, Context context) {
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
        if (firstName != null && lastName == null) {
            selection = ImsContract.PatientEntry.COLUMN_FIRST_NAME + " LIKE '" + firstName + "%'";
        } else if (firstName == null && lastName != null) {
            selection = ImsContract.PatientEntry.COLUMN_LAST_NAME + " LIKE '" + lastName + "%'";
        } else if (firstName != null && lastName != null) {
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
        while (cursor.moveToNext()) {

            // Id column index
            int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry._ID);

            // Id
            int id = cursor.getInt(patientIdColumnIndex);

            if (id >= 0) {
                patientId = id;
            } else {
                patientSearch = null;
            }
        }
        return patientId;
    }

    // Get first name
    public static String getFirstName(String username) {

        String un = username;

        int indexOf = username.indexOf(" ");

        if (indexOf == -1) {
            un = username;
        } else {
            un = username.substring(0, indexOf);
        }
        return un;
    }

    // Get last name
    public static String getLastName(String username) {

        String un = username;

        int indexOf = username.indexOf(" ");
        if (indexOf == -1) {
            un = null;
        } else {
            un = username.substring(indexOf + 1, username.length());
        }
        return un;
    }
    private CursorLoader getDoctorSearch(String firstName, String lastName, Context context) {
        // Run query
        Uri uri = ImsContract.PatientDataToAnalysisEntry.CONTENT_URI;

        String[] projection = {
                ImsContract.PatientDataToAnalysisEntry._ID,
                ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME,
                ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE,
                ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID};

        String selection = ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID + " = " + getPatientId(firstName, lastName, context);

        return new CursorLoader(context,
                uri,
                projection,
                selection,
                null,
                null);
    }
}
