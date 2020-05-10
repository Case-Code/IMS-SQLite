package com.example.ims;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.ims.adapter.PatientCursorAdapter;
import com.example.ims.data.ImsContract;
import com.example.ims.data.ImsContract.PatientEntry;
import com.example.logutil.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class ReceptionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = ReceptionActivity.class.getSimpleName();
    private static final int PATIENT_REGISTRATION_LOADER = 1;
    private static final int PATIENT_UPDATE_LOADER = 2;
    public static int mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN;
    public static int mTheNamesOfTheClinics = ImsContract.PatientDataToClinicsEntry.CLINICS_UNKNOWN;
    public static FragmentManager mFragmentManager;

    public static View mDialogTransferredToClinicsView;
    public static View mDialogTransferredToTheAnalysisLab;

    public Uri mCurrentPatientUri;

    private PatientCursorAdapter mPatientCursorAdapter;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ImageButton mActionMenuImageButton;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FloatingActionButton mFloatingActionButton;
    private ImageView mEmptyReceptionImageView;

    private ListView mPatientListView;

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mPhoneNumberEditText;
    private EditText mBirthDateEditText;
    private EditText mLocationEditText;
    private EditText mWeightEditText;
    private EditText mHeightEditText;

    private Spinner mPatientGenderSpinner;
    private Spinner mTypeOfAnalysisSpinner;
    private Spinner mTheNameOfTheClinicSpinner;

    private int mGender = PatientEntry.GENDER_UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        mFragmentManager = getSupportFragmentManager();

        init();

        mPatientListView.setEmptyView(mEmptyReceptionImageView);

        mPatientCursorAdapter = new PatientCursorAdapter(this, null);
        mPatientListView.setAdapter(mPatientCursorAdapter);


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

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentPatientUri = null;
                showPatientRegistrationDialog("add", "Patient registration");
            }
        });

        mPatientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // From the content URI that represents the specific product that was clicked on,
                // by appending "id" (passed as input to this method) onto the PatientEntry #CONTENT_URI
                // For example, the URI would be "content://com.example.android.ims/patient/2"
                // if the product with ID 2 was clicked on
                mCurrentPatientUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);
                getLoaderManager().initLoader(PATIENT_UPDATE_LOADER, null, ReceptionActivity.this);
                showPatientRegistrationDialog("update", "Patient update");
            }
        });

        mPatientListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // From the content URI that represents the specific product that was clicked on,
                // by appending "id" (passed as input to this method) onto the PatientEntry #CONTENT_URI
                // For example, the URI would be "content://com.example.android.ims/patient/2"
                // if the product with ID 2 was clicked on
                mCurrentPatientUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);
                showPatientDeleteConfirmationDialog();
                return false;
            }
        });

        getLoaderManager().initLoader(PATIENT_REGISTRATION_LOADER, null, this);
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
        mDrawerLayout = findViewById(R.id.activity_reception);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
        mEmptyReceptionImageView = findViewById(R.id.image_empty_reception);
        mPatientListView = findViewById(R.id.list_reception_patient);
        mDialogTransferredToClinicsView = getLayoutInflater().inflate(R.layout.dialog_transferred_to_clinics, null);
        mDialogTransferredToTheAnalysisLab = getLayoutInflater().inflate(R.layout.dialog_transferred_to_the_analysis_lab, null);
    }

    // Setup spinner gender
    private void setupSpinnerGender() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.array_gender_options,
                android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mPatientGenderSpinner.setAdapter(genderSpinnerAdapter);
        mPatientGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = PatientEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = PatientEntry.GENDER_FEMALE;
                    } else {
                        mGender = PatientEntry.GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = PatientEntry.GENDER_UNKNOWN;
            }
        });
    }

    // Setup spinner types of analysis
    public void setupSpinnerTypesOfAnalysis(Context context) {
        ArrayAdapter typeOfAnalysisSpinnerAdapter = ArrayAdapter.createFromResource(
                context,
                R.array.array_analysis_options,
                android.R.layout.simple_spinner_item);

        typeOfAnalysisSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mTypeOfAnalysisSpinner.setAdapter(typeOfAnalysisSpinnerAdapter);
        mTypeOfAnalysisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Complete blood count")) { // TODO chane the text
                        mTypesOfAnalysis =
                                ImsContract.PatientDataToAnalysisEntry.ANALYSIS_COMPLETE_BLOOD_COUNT;
                    } else if (selection.equals("Urine examination")) {
                        mTypesOfAnalysis =
                                ImsContract.PatientDataToAnalysisEntry.ANALYSIS_URINE_EXAMINATION;
                    } else if (selection.equals("Stool examination")) {
                        mTypesOfAnalysis =
                                ImsContract.PatientDataToAnalysisEntry.ANALYSIS_STOOL_EXAMINATION;
                    } else {
                        mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTypesOfAnalysis = ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN;
            }
        });
    }

    // Setup spinner the names of the clinics
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
                    if (selection.equals("Endemic diseases")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_ENDEMIC_DISEASES;
                    } else if (selection.equals("Medical and microbiological analyzes")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_MEDICAL_AND_MICROBIOLOGICAL_ANALYZES;
                    } else if (selection.equals("Psychological diseases")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_PSYCHOLOGICAL_DISEASES;
                    } else if (selection.equals("Phonetic and phoneme")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_PHONETIC_AND_PHONEME;
                    } else if (selection.equals("Ear, nose and throat")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_EAR_NOSE_AND_THROAT;
                    } else if (selection.equals("Colon and anus")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_COLON_AND_ANUS;
                    } else if (selection.equals("Blood vessels")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_BLOOD_VESSELS;
                    } else if (selection.equals("Endocrine glands")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_ENDOCRINE_GLANDS;
                    } else if (selection.equals("Rheumatism and immunity")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_RHEUMATISM_AND_IMMUNITY;
                    } else if (selection.equals("Kidney")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_KIDNEY;
                    } else if (selection.equals("The pain")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_THE_PAIN;
                    } else if (selection.equals("Chest's diseases")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_CHESTS_DISEASES;
                    } else if (selection.equals("Heart drawing")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_HEART_DRAWING;
                    } else if (selection.equals("Cardiothoracic surgery")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_CARDIOTHORACIC_SURGERY;
                    } else if (selection.equals("Fertility unit")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_FERTILITY_UNIT;
                    } else if (selection.equals("General interior")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_GENERAL_INTERIOR;
                    } else if (selection.equals("Rheumatism and rehabilitation")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_RHEUMATISM_AND_REHABILITATION;
                    } else if (selection.equals("Plastic surgery")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_PLASTIC_SURGERY;
                    } else if (selection.equals("General surgery")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_GENERAL_SURGERY;
                    } else if (selection.equals("Oncology and nuclear medicine")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_ONCOLOGY_AND_NUCLEAR_MEDICINE;
                    } else if (selection.equals("Leather and genital")) {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_LEATHER_AND_GENITAL;
                    } else {
                        mTheNamesOfTheClinics =
                                ImsContract.PatientDataToClinicsEntry.CLINICS_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTheNamesOfTheClinics = ImsContract.PatientDataToClinicsEntry.CLINICS_UNKNOWN;
            }
        });
    }

    // Show patient registration dialog
    private void showPatientRegistrationDialog(String nameButton, String title) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_patient_registration, null);

        mFirstNameEditText = mView.findViewById(R.id.edit_first_name);
        mLastNameEditText = mView.findViewById(R.id.edit_last_name);
        mPhoneNumberEditText = mView.findViewById(R.id.edit_phone_number);
        mBirthDateEditText = mView.findViewById(R.id.edit_birth_date);
        mLocationEditText = mView.findViewById(R.id.edit_location);
        mWeightEditText = mView.findViewById(R.id.edit_weight);
        mHeightEditText = mView.findViewById(R.id.edit_height);
        ImageButton actionDateImageButton = mView.findViewById(R.id.image_button_action_date);
        mPatientGenderSpinner = mView.findViewById(R.id.spinner_gender);
        builder.setPositiveButton(nameButton, null);
        builder.setNegativeButton("decline", null);
        builder.setTitle(title);
        builder.setView(mView);

        setupSpinnerGender();

        actionDateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener
                        dateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month += 1;
                                String date = month + "/" + dayOfMonth + "/" + year;
                                mBirthDateEditText.setText(date);
                            }
                        };
                Utils.showDatePicker(ReceptionActivity.this, dateSetListener);
            }
        });


        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);


        if (mCurrentPatientUri == null) {

            setupSpinnerGender(); // TODO change the code


            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String fName = mFirstNameEditText.getText().toString();
                            String lName = mLastNameEditText.getText().toString();


                            if (uniFName(fName, ReceptionActivity.this) != null &
                                    uniLName(lName, ReceptionActivity.this) != null) {
                                mFirstNameEditText.setError("name useed is database");
                                mLastNameEditText.setError("name use in database");


                                Log.e("nameclick", "not");
                                return;
                            } else {

                                patientRegistration();
                                alertDialog.dismiss();
                            }
                        }
                    });
                    Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    negativeButton.setText("Decline");
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }
            });

        }
        // TODO update patient row
        else {
            setupSpinnerGender();

            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button
                            positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            patientRegistration();
                            alertDialog.dismiss();

                        }
                    });
                    Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    negativeButton.setText("Decline");
                    negativeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getLoaderManager().destroyLoader(PATIENT_UPDATE_LOADER);
                            alertDialog.dismiss();
                        }
                    });

                }
            });
        }
        alertDialog.show();

    }

    // Show patient delete confirmation dialog
    private void showPatientDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReceptionActivity.this);
        builder.setMessage("Delete this patient?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                patientDelete();
            }
        });
        builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Show transferred to the analysis lab dialog
    public void showTransferredToTheAnalysisLabDialog(Context context) {
        if (mDialogTransferredToTheAnalysisLab.getParent() != null) {
            ((ViewGroup) mDialogTransferredToTheAnalysisLab.getParent()).removeView(mDialogTransferredToTheAnalysisLab);
        }

        mTypeOfAnalysisSpinner =
                mDialogTransferredToTheAnalysisLab.findViewById(R.id.spinner_types_of_analysis);
        setupSpinnerTypesOfAnalysis(context);
    }

    // Show transferred to clinics dialog
    public void showTransferredToClinicsDialog(Context context) {
        if (mDialogTransferredToClinicsView.getParent() != null) {
            ((ViewGroup) mDialogTransferredToClinicsView.getParent()).removeView(mDialogTransferredToClinicsView);
        }

        mTheNameOfTheClinicSpinner =
                mDialogTransferredToClinicsView.findViewById(R.id.spinner_name_the_clinics);
        setupSpinnerTheNamesOfTheClinics(context);
    }

    // Patient registration
    private void patientRegistration() {
        String firstNameString = mFirstNameEditText.getText().toString().trim();
        String lastNameString = mLastNameEditText.getText().toString().trim();
        String phoneNumberString = mPhoneNumberEditText.getText().toString().trim();
        String birthDateString = mBirthDateEditText.getText().toString().trim();
        String locationString = mLocationEditText.getText().toString().trim();
        String weightString = mWeightEditText.getText().toString().trim();
        String heightString = mHeightEditText.getText().toString().trim();

        // Check if this is supposed to be a new patient
        // and check if all the fields in the editor are blank
        if (mCurrentPatientUri == null &&
                TextUtils.isEmpty(firstNameString) &&
                TextUtils.isEmpty(lastNameString) &&
                TextUtils.isEmpty(phoneNumberString) &&
                TextUtils.isEmpty(birthDateString) &&
                TextUtils.isEmpty(locationString) &&
                TextUtils.isEmpty(weightString) &&
                TextUtils.isEmpty(heightString) &&
                mGender == PatientEntry.GENDER_UNKNOWN) {
            return;
        }

        // Create a ContentValues object where column names are the keys,
        // and patient attributes from the editor are the values.
        ContentValues values = new ContentValues();

        // REQUIRED VALUES
        // Validation section
        if (TextUtils.isEmpty(firstNameString)) {
            Toast.makeText(this, "First name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(PatientEntry.COLUMN_FIRST_NAME, firstNameString);

        }

        if (TextUtils.isEmpty(lastNameString)) {
            Toast.makeText(this, "Last name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(PatientEntry.COLUMN_LAST_NAME, lastNameString);
        }

        if (TextUtils.isEmpty(phoneNumberString)) {
            Toast.makeText(this, "Phone number is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(PatientEntry.COLUMN_PHONE_NUMBER, phoneNumberString);
        }

        if (TextUtils.isEmpty(birthDateString)) {
            Toast.makeText(this, "Birth date is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(PatientEntry.COLUMN_BIRTH_DATE, birthDateString);
        }

        if (TextUtils.isEmpty(locationString)) {
            Toast.makeText(this, "Location is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(PatientEntry.COLUMN_LOCATION, locationString);
        }

        int weight = 0;
        if (TextUtils.isEmpty(weightString)) {
            Toast.makeText(this, "Weight is required", Toast.LENGTH_SHORT).show();
        } else {
            weight = Integer.parseInt(weightString);
            values.put(PatientEntry.COLUMN_WEIGHT, weight);
        }

        if (TextUtils.isEmpty(heightString)) {
            Toast.makeText(this, "Height is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(PatientEntry.COLUMN_HEIGHT, heightString);
        }

        // optional values
        values.put(PatientEntry.COLUMN_GENDER, mGender);

       /* if(getPatientName(firstNameString,lastNameString ,ReceptionActivity.this)==false){
            mFirstNameEditText.setError("the name is error ");
            Log.e("name","yse:::::::::");



        }*/


        // Insert and update patient
        if (mCurrentPatientUri == null) {
            Uri newUri = getContentResolver().insert(PatientEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();

            }

        } else {
            int rowsAffected = getContentResolver().update(mCurrentPatientUri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(this, getString(R.string.editor_update_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                getLoaderManager().destroyLoader(PATIENT_UPDATE_LOADER);
                mCurrentPatientUri = null;
                Toast.makeText(this, getString(R.string.editor_update_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Patient delete
    private void patientDelete() {
        if (mCurrentPatientUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentPatientUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, getString(R.string.editor_delete_patient_failed), Toast.LENGTH_SHORT).show();
            } else {
                mCurrentPatientUri = null;
                Toast.makeText(this, getString(R.string.editor_delete_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                PatientEntry._ID,
                PatientEntry.COLUMN_FIRST_NAME,
                PatientEntry.COLUMN_LAST_NAME,
                PatientEntry.COLUMN_PHONE_NUMBER,
                PatientEntry.COLUMN_BIRTH_DATE,
                PatientEntry.COLUMN_LOCATION,
                PatientEntry.COLUMN_WEIGHT,
                PatientEntry.COLUMN_HEIGHT};

        if (mCurrentPatientUri == null) {
            return new CursorLoader(this,
                    PatientEntry.CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        } else {
            return new CursorLoader(this,
                    mCurrentPatientUri,
                    projection,
                    null,
                    null,
                    null);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (mCurrentPatientUri == null) {
            mPatientCursorAdapter.swapCursor(data);
        } else {
            if (data == null || data.getCount() < 1) {
                return;
            }

            if (data.moveToFirst()) {
                int firstNameColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_FIRST_NAME);
                int lastNameColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_LAST_NAME);
                int phoneNumberColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_PHONE_NUMBER);
                int birthDateColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_BIRTH_DATE);
                int locationColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_LOCATION);
                int weightColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_WEIGHT);
                int heightColumnIndex = data.getColumnIndex(PatientEntry.COLUMN_HEIGHT);

                String firstName = data.getString(firstNameColumnIndex);
                String lastName = data.getString(lastNameColumnIndex);
                String phoneNumber = data.getString(phoneNumberColumnIndex);
                String birthDate = data.getString(birthDateColumnIndex);
                String location = data.getString(locationColumnIndex);
                String weight = data.getString(weightColumnIndex);
                String height = data.getString(heightColumnIndex);

                mFirstNameEditText.setText(firstName);
                mLastNameEditText.setText(lastName);
                mPhoneNumberEditText.setText(phoneNumber);
                mBirthDateEditText.setText(birthDate);
                mLocationEditText.setText(location);
                mWeightEditText.setText(weight);
                mHeightEditText.setText(height);

                switch (mGender) {
                    case PatientEntry.GENDER_MALE:
                        mPatientGenderSpinner.setSelection(PatientEntry.GENDER_MALE);
                        break;
                    case PatientEntry.GENDER_FEMALE:
                        mPatientGenderSpinner.setSelection(PatientEntry.GENDER_FEMALE);
                        break;
                    default:
                        mPatientGenderSpinner.setSelection(PatientEntry.GENDER_UNKNOWN);
                        break;
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mCurrentPatientUri == null) {
            mPatientCursorAdapter.swapCursor(null);
        } else {
            mFirstNameEditText.setText("");
            mLastNameEditText.setText("");
            mPhoneNumberEditText.setText("");
            mBirthDateEditText.setText("");
            mLocationEditText.setText("");
            mWeightEditText.setText("");
            mHeightEditText.setText("");
            mPatientGenderSpinner.setSelection(0);
        }
    }


    public String uniFName(String name, Context context) {
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;

        // Column name
        String[] projection = {ImsContract.PatientEntry.COLUMN_FIRST_NAME};

        String selection = ImsContract.PatientEntry.COLUMN_FIRST_NAME + " LIKE '" + name + "%'";

        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                selection,
                null,
                null);

        while (cursor.moveToNext()) {

            // Firs name and last name column index
            int
                    patientFirsNameColumnIndex =
                    cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);

            String patientFirsName = cursor.getString(patientFirsNameColumnIndex);
            if (patientFirsName == null) {
                return null;
            } else {
                Log.e("name", "not uniqe");
                return name;
            }
        }

        return null;
    }

    public String uniLName(String name, Context context) {
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;

        // Column name
        String[] projection = {ImsContract.PatientEntry.COLUMN_LAST_NAME};

        String selection = ImsContract.PatientEntry.COLUMN_LAST_NAME + " LIKE '" + name + "%'";

        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                selection,
                null,
                null);

        while (cursor.moveToNext()) {

            // Firs name and last name column index
            int
                    patientFirsNameColumnIndex =
                    cursor.getColumnIndex(PatientEntry.COLUMN_LAST_NAME);

            String patientLastName = cursor.getString(patientFirsNameColumnIndex);
            if (patientLastName == null) {
                return null;
            } else {
                Log.e("name", "not uniqe");
                return name;
            }
        }

        return null;
    }
}


