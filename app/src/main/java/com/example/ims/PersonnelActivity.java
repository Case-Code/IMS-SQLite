package com.example.ims;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PersonnelActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ImageButton mActionMenuImageButton;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //Employee data management
    private AutoCompleteTextView edmEmployeeSearchAutoCompleteTextView;
    private ListView edmPatientListView;

    //Attendance and Departure
    private AutoCompleteTextView aadDateSearchAutoCompleteTextView;
    private ListView aadDateListView;

    //Employee
    private EditText employeeFirstNameEditText;
    private EditText employeeLastNameEditText;
    private EditText employeeEmailEditText;
    private EditText employeePhoneEditText;
    private TextView employeeHireDateTextView;
    private EditText employeeSalaryEditText;
    private Spinner employeeDepartmentSpinner;
    private EditText employeeJobTitleEditText;
    private EditText employeeMinSalaryEditText;
    private EditText employeeMaxSalaryEditText;
    private Spinner employeeRegionNameSpinner;
    private Spinner employeeCountryNameSpinner;
    private Spinner employeeCitySpinner;
    private EditText employeeStreetAddressEditText;
    private EditText employeePostalCodeEditText;
    private Button employeeAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel);

        // initial
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
        mDrawerLayout = findViewById(R.id.activity_Personnel);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
         //Employee data management
         edmEmployeeSearchAutoCompleteTextView=findViewById(R.id.text_edm_employee_search);
         edmPatientListView=findViewById(R.id.list_edm_patient);

        //Attendance and Departure
         aadDateSearchAutoCompleteTextView=findViewById(R.id.text_aad_date_search);
         aadDateListView=findViewById(R.id.list_aad_date);

        //Employee
         employeeFirstNameEditText=findViewById(R.id.edit_employee_firstname);
         employeeLastNameEditText=findViewById(R.id.edit_employee_lastname);
         employeeEmailEditText=findViewById(R.id.edit_employee_email);
         employeePhoneEditText=findViewById(R.id.edit_employee_phone);
         employeeHireDateTextView=findViewById(R.id.text_employee_hire_date);
         employeeSalaryEditText=findViewById(R.id.edit_employee_salary);
         employeeDepartmentSpinner=findViewById(R.id.spinner_employee_department);
         employeeJobTitleEditText=findViewById(R.id.edit_employee_job_title);
         employeeMinSalaryEditText=findViewById(R.id.edit_employee_min_salary);
         employeeMaxSalaryEditText=findViewById(R.id.edit_employee_max_salary);
         employeeRegionNameSpinner=findViewById(R.id.spinner_employee_region_name);
         employeeCountryNameSpinner=findViewById(R.id.spinner_employee_country_name);
         employeeCitySpinner=findViewById(R.id.spinner_employee_city);
         employeeStreetAddressEditText=findViewById(R.id.edit_employee_street_address);
         employeePostalCodeEditText=findViewById(R.id.edit_employee_postal_code);
         employeeAddButton=findViewById(R.id.button_employee_add);
    }
}