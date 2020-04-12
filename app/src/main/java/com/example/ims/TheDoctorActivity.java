package com.example.ims;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ims.data.ImsContract;
import com.example.ims.data.ImsProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TheDoctorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ImageButton mActionMenuImageButton;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static View mDialogTransferredToClinicsView;
    private Spinner mTheNameOfTheClinicSpinner;
    private FloatingActionButton  mButtonClinic;
    private FloatingActionButton mButtonAnaylislap;
    private FloatingActionButton mButtonPharmsy;
    private FloatingActionButton mButtonRadioly;
    AutoCompleteTextView edittext ;







    private String mTheNamesOfTheClinics = "null";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_doctor);

        init();

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
        mButtonClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTransferredToClinicsDialog();
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
        mDrawerLayout = findViewById(R.id.activity_the_doctor);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mDialogTransferredToClinicsView = getLayoutInflater().inflate(R.layout.dialog_doctor_transferred_to_clinics, null);
        mButtonClinic =findViewById(R.id.button_doc_clinic);
        mButtonAnaylislap =findViewById(R.id.button_doc_anaylislap);
        mButtonPharmsy =findViewById(R.id.button_doc_pharmsy);
        mButtonRadioly =findViewById(R.id.button_doc_radiology);


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
                        mTheNamesOfTheClinics  = "Complete blood count";
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


    public ArrayList<ImsContract.PatientEntry>  searchPatient() {
        ArrayList<ImsContract.PatientEntry>arr = new ArrayList<>();

        if (TextUtils.isEmpty(arr.toString())) {
            return null;
        }


        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        String[] selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
        Cursor cursor = getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME}, null, null, "ASC");
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
        }
        if (null == cursor) {

        } else if (cursor.getCount() < 1) {

        } else {
            String resultname = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));
            ImsContract.PatientEntry p =new ImsContract.PatientEntry();
                if(p!=null){
            arr.add(p);}
                cursor.moveToNext();


        }
        cursor.close();

        return arr;

    }
    public void showTransferredToClinicsDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        edittext = mDialogTransferredToClinicsView.findViewById(R.id.tv_search_patient_clinic);
        ArrayList arr = new ArrayList();
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        Cursor cursor =getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME},null,null, ImsContract.PatientEntry.COLUMN_FIRST_NAME );
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false) {

            String patientname = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));

            if(patientname!=null){
                arr.add(patientname);
            }
            patientname=null;
            cursor.moveToNext();

        }


       /* {
//first code
        String[] mProjection =
                {
                        ImsContract.PatientEntry.COLUMN_FIRST_NAME
                        // Contract class constant for the locale column name
                };

// Defines a string to contain the selection clause
        String selectionClause = null;
        Uri mCurrentUri;


// Initializes an array to contain selection arguments
        String[] selectionArgs = {""};
        // Gets a word from the UI
        String search = edittext.getText().toString();

        if (TextUtils.isEmpty(search)) {
            selectionClause = null;
            selectionArgs[0] = "";

        } else {
            selectionClause = ImsContract.PatientEntry.COLUMN_FIRST_NAME + " = ?";
            selectionArgs[0] = search;

        }

        Cursor cursor = getContentResolver().query(ImsContract.PatientEntry.CONTENT_URI
                , mProjection, selectionClause, selectionArgs, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            if (null == cursor) {

            } else if (cursor.getCount() < 1) {


            } else {
                int firstNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);
                String patientname = cursor.getString(firstNameColumnIndex);
                arr.add(patientname);

            }
            cursor.moveToNext();

        }
        cursor.close();

    }*/

//end code


        ArrayAdapter <String>adabterEdittext=new ArrayAdapter<String>(this ,android.R.layout.simple_list_item_1,arr);
        edittext.setAdapter(adabterEdittext);

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

}