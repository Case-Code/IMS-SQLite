package com.example.ims;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private AutoCompleteTextView departmentACTV;
    private EditText jobTitleEditText;
    private EditText minSalaryEditText;
    private EditText maxSalaryEditText;
    private AutoCompleteTextView regionNameACTV;
    private AutoCompleteTextView countryNameACTV;
    private AutoCompleteTextView cityACTV;
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

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                saveData();
            }
        });
        getLoaderManager().initLoader(EMPLOYEE_LOADER, null, this);

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
        departmentACTV = findViewById(R.id.spinner_employee_department);
        jobTitleEditText = findViewById(R.id.edit_employee_jobtitle);
        minSalaryEditText = findViewById(R.id.edit_employee_minsalary);
        maxSalaryEditText = findViewById(R.id.edit_employee_maxsalary);
        regionNameACTV = findViewById(R.id.spinner_employee_regionname);
        countryNameACTV = findViewById(R.id.spinner_employee_countryname);
        cityACTV = findViewById(R.id.spinner_employee_city);
        streetAddressEditText = findViewById(R.id.edit_employee_streetaddress);
        postalCodeEditText = findViewById(R.id.edit_employee_postalcode);
        addButton = findViewById(R.id.button_employee_add);
    }

    public void saveData()
    {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String hireDate = hireDateTextView.getText().toString().trim();
        String salary = salaryEditText.getText().toString().trim();
        String department = departmentACTV.getText().toString().trim();
        String jobTitle = jobTitleEditText.getText().toString().trim();
        String minSalary = minSalaryEditText.getText().toString().trim();
        String maxSalary = maxSalaryEditText.getText().toString().trim();
        String regionName = regionNameACTV.getText().toString().trim();
        String countryName = countryNameACTV.getText().toString().trim();
        String city = cityACTV.getText().toString().trim();
        String streetAddress = streetAddressEditText.getText().toString().trim();
        String postalCode = postalCodeEditText.getText().toString().trim();

        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(firstName))
        {
            firstNameEditText.setError("please write on firstName ");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_FIRST_NAME, firstName);
        }
        if (TextUtils.isEmpty(lastName))
        {
            lastNameEditText.setError("please write on lastName ");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_LAST_NAME, lastName);
        }
        if (TextUtils.isEmpty(email))
        {
            emailEditText.setError("please write on email");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_EMAIL, email);
        }
        if (TextUtils.isEmpty(phone))
        {
            phoneEditText.setError("please write on phone");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_PHONE_NUMBER, phone);
        }
        if (TextUtils.isEmpty(hireDate))
        {
            hireDateTextView.setError("please write on hireDate");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_HIRE_DATE, hireDate);
        }
        if (TextUtils.isEmpty(salary))
        {
            salaryEditText.setError("please write on salary");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_SALARY, salary);
        }
        if (TextUtils.isEmpty(department))
        {
            departmentACTV.setError("please write on department ");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_DEPARTMENT_NAME, department);
        }
        if (TextUtils.isEmpty(jobTitle))
        {
            jobTitleEditText.setError("please write on jobTitle");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_JOB_TITLE, jobTitle);
        }
        if (TextUtils.isEmpty(minSalary))
        {
            minSalaryEditText.setError("please write on minSalary");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_MIN_SALARY, minSalary);
        }
        if (TextUtils.isEmpty(maxSalary))
        {
            maxSalaryEditText.setError("please write on maxSalary");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_MAX_SALARY, maxSalary);
        }
        if (TextUtils.isEmpty(regionName))
        {
            regionNameACTV.setError("please write on regionName");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_REGION_NAME, regionName);
        }
        if (TextUtils.isEmpty(countryName))
        {
            countryNameACTV.setError("please write on countryName");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_COUNTRY_NAME, countryName);
        }
        if (TextUtils.isEmpty(city))
        {
            cityACTV.setError("please write on city");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_CITY, city);
        }
        if (TextUtils.isEmpty(streetAddress))
        {
            streetAddressEditText.setError("please write on streetAddress");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_STREET_ADDRESS, streetAddress);
        }
        if (TextUtils.isEmpty(postalCode))
        {
            postalCodeEditText.setError("please write on postalCode");
            return;
        }
        else
        {
            values.put(ImsContract.EmployeesEntry.COLUMN_POSTAL_CODE, postalCode);
        }
        Uri newUri = getContentResolver().insert(ImsContract.EmployeesEntry.CONTENT_URI, values);
        if (newUri == null)
        {
            Toast.makeText(this, getString(R.string.insert_doctor_failed), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, getString(R.string.insert_doctor_successful), Toast.LENGTH_SHORT).show();
            setText();

        }

    }

    public void setText()
    {
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        phoneEditText.setText("");
        hireDateTextView.setText("");
        salaryEditText.setText("");
        departmentACTV.setText("");
        jobTitleEditText.setText("");
        minSalaryEditText.setText("");
        maxSalaryEditText.setText("");
        regionNameACTV.setText("");
        countryNameACTV.setText("");
        cityACTV.setText("");
        streetAddressEditText.setText("");
        postalCodeEditText.setText("");
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