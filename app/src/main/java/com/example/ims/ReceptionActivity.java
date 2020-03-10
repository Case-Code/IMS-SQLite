package com.example.ims;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
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

import java.util.Calendar;

import com.example.ims.data.ImsContract.PatientEntry;
import com.example.ims.data.ImsContract.PatientDataToLaboratoriesEntry;


public class ReceptionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, LoaderManager.LoaderCallbacks<Cursor> {

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
    private ImageButton mActionDateImageButton;
    private Spinner mPatientGenderSpinner;
    public static FragmentManager mfragmentManager;

    private int mGender = PatientEntry.GENDER_UNKNOWN;
    private String mTypesOfAnalysis = null;

    private Uri mCurrentPatientUri;

    private PatientCursorAdapter mPatientCursorAdapter;

    private static final int PATIENT_REGISTRATION_LOADER = 1;
    private static final int PATIENT_UPDATE_LOADER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        mfragmentManager=getSupportFragmentManager();

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
                showPatientRegistrationDialog();
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
                showPatientRegistrationDialog();
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
                showDeleteConfirmationDialog();
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

    // initial
    private void init() {
        mDrawerLayout = findViewById(R.id.activity_reception);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
        mEmptyReceptionImageView = findViewById(R.id.image_empty_reception);
        mPatientListView = findViewById(R.id.list_patient);
    }

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

    private void setupSpinnerTypesOfAnalysis() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.array_analysis_options,
                android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mPatientGenderSpinner.setAdapter(genderSpinnerAdapter);
        mPatientGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.analysis_complete_blood_count))) {
                        mTypesOfAnalysis = PatientDataToLaboratoriesEntry.ANALYSIS_COMPLETE_BLOOD_COUNT;
                    } else if (selection.equals(getString(R.string.analysis_urine_examination))) {
                        mTypesOfAnalysis = PatientDataToLaboratoriesEntry.ANALYSIS_URINE_EXAMINATION;
                    } else if (selection.equals(getString(R.string.analysis_stool_examination))) {
                        mTypesOfAnalysis = PatientDataToLaboratoriesEntry.ANALYSIS_STOOL_EXAMINATION;
                    } else {
                        // TODO mTypesOfAnalysis = PatientEntry.GENDER_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = PatientEntry.GENDER_UNKNOWN;
            }
        });
    }

    // Show patient registration dialog
    private void showPatientRegistrationDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_patient_registration, null);

        mFirstNameEditText = mView.findViewById(R.id.edit_first_name);
        mLastNameEditText = mView.findViewById(R.id.edit_last_name);
        mPhoneNumberEditText = mView.findViewById(R.id.edit_phone_number);
        mBirthDateEditText = mView.findViewById(R.id.edit_birth_date);
        mLocationEditText = mView.findViewById(R.id.edit_location);
        mWeightEditText = mView.findViewById(R.id.edit_weight);
        mHeightEditText = mView.findViewById(R.id.edit_height);
        mActionDateImageButton = mView.findViewById(R.id.image_button_action_date);
        mPatientGenderSpinner = mView.findViewById(R.id.spinner_gender);

        setupSpinnerGender();

        mActionDateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        mBirthDateEditText.setText(date);
                    }
                };
                showDatePicker(dateSetListener);
            }
        });

        if (mCurrentPatientUri == null) {
            setupSpinnerGender(); // TODO change the code

            builder.setView(mView);
            builder.setTitle("Patient registration");
            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    patientRegistration();
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
        } else {
            setupSpinnerGender(); // TODO change the code

            builder.setView(mView);
            builder.setTitle("Patient update");
            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    patientRegistration();
                }
            });
            builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (dialog != null) {
                        dialog.dismiss();
                        getLoaderManager().destroyLoader(PATIENT_UPDATE_LOADER);
                    }
                }
            });
        }


        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
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
                mCurrentPatientUri = (Uri) null;
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
                mCurrentPatientUri = (Uri) null;
                Toast.makeText(this, getString(R.string.editor_delete_patient_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * View and set the date-picker
     * 1. DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
     * 2.  month += 1;
     * 3. String date = month + "/" + dayOfMonth + "/" + year;
     *
     * @param mDateSetListener
     */
    private void showDatePicker(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    // Show transferred to the analysis lab dialog
    private void showTransferredToTheAnalysisLabDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_transferred_to_the_analysis_lab, null);

        final Spinner typeOfAnalysisSpinner = mView.findViewById(R.id.spinner_types_of_analysis);

        builder.setView(mView);
        builder.setTitle("Transferred to the analysis lab");
        builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                patientRegistration();
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
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    // Transferred to the analysis lab
    private void transferredToTheAnalysisLab() {

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
}

