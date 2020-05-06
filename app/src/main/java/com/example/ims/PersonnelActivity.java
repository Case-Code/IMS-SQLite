package com.example.ims;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

import com.example.ims.adapter.EmployeeCursorAdapter;
import com.example.ims.data.ImsContract;
import com.google.android.material.navigation.NavigationView;

public class PersonnelActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
   , LoaderManager.LoaderCallbacks<Cursor>
{

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ImageButton mActionMenuImageButton;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    //Employee data management
    private EditText employeeSearchEditText;
    private ListView employeeListView;

    //Attendance and Departure
    private EditText dateSearchEditText;
    private ListView dateListView;

    //Employee
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private TextView hireDateTextView;
    private EditText salaryEditText;
    private Spinner departmentSpinner;
    private EditText jobTitleEditText;
    private EditText minSalaryEditText;
    private EditText maxSalaryEditText;
    private Spinner regionNameSpinner;
    private Spinner countryNameSpinner;
    private Spinner citySpinner;
    private EditText streetAddressEditText;
    private EditText postalCodeEditText;
    private Button addButton;

    //num loader
    private static final int EMPLOYEE_LOADER = 1;
    //  private static final int EMPLOYEE_LOADER=2;

    //adapter
    EmployeeCursorAdapter mEmployeeCursorAdapter;

    //select data  with  uri
    Uri mEmployeeUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel);

        // initial
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

        //init adapter
        mEmployeeCursorAdapter = new EmployeeCursorAdapter(this, null);


        //set data in list view
        employeeListView.setAdapter(mEmployeeCursorAdapter);

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
        mDrawerLayout = findViewById(R.id.activity_Personnel);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        //Employee data management
        employeeSearchEditText = findViewById(R.id.edit_personnel_employeesearch);
        employeeListView = findViewById(R.id.list_personnel_employee);

        //Attendance and Departure
        dateSearchEditText = findViewById(R.id.edit_personnel_datesearch);
        dateListView = findViewById(R.id.list_personnel_date);

        //Employee
        firstNameEditText = findViewById(R.id.edit_employee_firstname);
        lastNameEditText = findViewById(R.id.edit_employee_lastname);
        emailEditText = findViewById(R.id.edit_employee_emai);
        phoneEditText = findViewById(R.id.edit_employee_phone);
        hireDateTextView = findViewById(R.id.text_employee_hiredate);
        salaryEditText = findViewById(R.id.edit_employee_salary);
        departmentSpinner = findViewById(R.id.spinner_employee_department);
        jobTitleEditText = findViewById(R.id.edit_employee_jobtitle);
        minSalaryEditText = findViewById(R.id.edit_employee_minsalary);
        maxSalaryEditText = findViewById(R.id.edit_employee_maxsalary);
        regionNameSpinner = findViewById(R.id.spinner_employee_regionname);
        countryNameSpinner = findViewById(R.id.spinner_employee_countryname);
        citySpinner = findViewById(R.id.spinner_employee_city);
        streetAddressEditText = findViewById(R.id.edit_employee_streetaddress);
        postalCodeEditText = findViewById(R.id.edit_employee_postalcode);
        addButton = findViewById(R.id.button_employee_add);
    }

    public void saveData()
    {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle)
    {
        if (EMPLOYEE_LOADER == id)
        {
            if (mEmployeeUri == null)
            {
                String[] projection = {
                   ImsContract.EmployeesEntry._ID,
                   ImsContract.EmployeesEntry.COLUMN_FIRST_NAME,
                   ImsContract.EmployeesEntry.COLUMN_LAST_NAME};

                return new CursorLoader(
                   this,
                   ImsContract.EmployeesEntry.CONTENT_URI,
                   projection,
                   null,
                   null,
                   null);
            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor)
    {
        int id = loader.getId();
        if (id == EMPLOYEE_LOADER)
        {
            if (mEmployeeUri == null)
            {
                mEmployeeCursorAdapter.swapCursor(cursor);
            }

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader)
    {

        int id = loader.getId();
        if (id == EMPLOYEE_LOADER)
        {
            if (mEmployeeUri == null)
            {
                mEmployeeCursorAdapter.swapCursor(null);
            }

        }
    }
}