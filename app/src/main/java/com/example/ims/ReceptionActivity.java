package com.example.ims;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.Calendar;

public class ReceptionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ImageButton mActionMenuImageButton;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FloatingActionButton mFloatingActionButton;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);

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

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPatientRegistrationDialog();
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
        mDrawerLayout = findViewById(R.id.activity_reception);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mFloatingActionButton = findViewById(R.id.floating_action_button);
    }

    // Show patient registration dialog
    private void showPatientRegistrationDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_patient_registration, null);

        final EditText firstNameEditText = mView.findViewById(R.id.edit_first_name);
        final EditText lastNameEditText = mView.findViewById(R.id.edit_last_name);
        final EditText phoneEditText = mView.findViewById(R.id.edit_phone);
        final EditText birthDateEditText = mView.findViewById(R.id.edit_birth_date);
        final EditText locationEditText = mView.findViewById(R.id.edit_location);
        final EditText weightEditText = mView.findViewById(R.id.edit_weight);
        final EditText heightEditText = mView.findViewById(R.id.edit_height);
        final ImageButton actionDateImageButton = mView.findViewById(R.id.image_button_action_date);

        actionDateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        birthDateEditText.setText(date);
                    }
                };
                showDatePicker(dateSetListener);
            }
        });

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

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    // Patient registration
    private void patientRegistration() {

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

        final Spinner typeOfAnalysisSpinner = mView.findViewById(R.id.spinner_type_of_analysis);

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

}


