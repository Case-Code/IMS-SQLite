package com.example.ims.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.ims.data.ImsContract.*;

/**
 * Database helper for IMS app. Manages database creation and version management.
 */
public class ImsDbHelper extends SQLiteOpenHelper {

    //  Name of the database file
    private static final String DATABASE_NAME = "ims.db";

    // Database version. If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    // Create a String that contains the SQL statement to create the 'patient' table
    private static final String SQL_CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientEntry.TABLE_NAME + " ("
            + PatientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, "
            + PatientEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, "
            + PatientEntry.COLUMN_PHONE_NUMBER + " INTEGER NOT NULL, "
            + PatientEntry.COLUMN_BIRTH_DATE + " TEXT NOT NULL, "
            + PatientEntry.COLUMN_LOCATION + " TEXT NOT NULL, "
            + PatientEntry.COLUMN_WEIGHT + " INTEGER NOT NULL, "
            + PatientEntry.COLUMN_HEIGHT + " INTEGER NOT NULL, "
            + PatientEntry.COLUMN_GENDER + " INTEGER NOT NULL DEFAULT 0);";

    // Create a String that contains the SQL statement to create the 'patient data To analysis' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_ANALYSIS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToAnalysisEntry.TABLE_NAME + " ("
            + PatientDataToAnalysisEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE + " TEXT NOT NULL, "
            + PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME + " INTEGER NOT NULL DEFAULT 0, "

            + PatientDataToAnalysisEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient data to clinics' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_CLINICS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToClinicsEntry.TABLE_NAME + " ("
            + PatientDataToClinicsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE + " TEXT NOT NULL, "
            + PatientDataToClinicsEntry.COLUMN_CLINIC_NAME + " INTEGER NOT NULL DEFAULT 0, "
            + PatientDataToClinicsEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient records' table
    private static final String SQL_CREATE_PATIENT_RECORDS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientRecordsEntry.TABLE_NAME + " ("
            + PatientRecordsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientRecordsEntry.COLUMN_BILL_TO_NAME + " TEXT , "
            + PatientRecordsEntry.COLUMN_DATE_OF_BIRTH + " TEXT , "
            + PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID + " INTEGER , "
            + PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE + " TEXT , "
            + PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE + " TEXT , "
            + PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE + " TEXT , "
            + PatientRecordsEntry.COLUMN_DATE_SIGNED + " TEXT , "
            + PatientRecordsEntry.COLUMN_X_RAY_IMAGE + " TEXT , "
            + PatientRecordsEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient progress' table
    private static final String SQL_CREATE_PATIENT_PROGRESS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientProgressEntry.TABLE_NAME + " ("
            + PatientProgressEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientProgressEntry.COLUMN_PROGRESS_NOTES + " TEXT NOT NULL, "
            + PatientProgressEntry.COLUMN_DATE + " TEXT NOT NULL,"
            + PatientProgressEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'invoices' table
    private static final String SQL_CREATE_INVOICES_TABLE = "CREATE TABLE IF NOT EXISTS " + InvoicesEntry.TABLE_NAME + " ("
            + InvoicesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + InvoicesEntry.COLUMN_DATE_OF_SVC + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_INVOICE_DATE + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_DATE_DUE + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_BILL_TO_NAME + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_BILL_TO_ADDRESS + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_BILL_TO_PHONE + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_BILL_TO_FAX + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_BILL_TO_EMAIL + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_SVC_ID + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_MEDICAL_SERVICES + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_MEDICATION + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_COST + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_SUBTOTAL + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_TAX_RATE + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_TOTAL_TAX + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_OTHER + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_TOTAL + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_QUESTIONS_NAME + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_QUESTIONS_EMAIL + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_QUESTIONS_PHONE + " INTEGER NOT NULL, "
            + InvoicesEntry.COLUMN_QUESTIONS_WEB + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_PROCEDURE + " TEXT NOT NULL, "
            + InvoicesEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'health record' table
    private static final String SQL_CREATE_HEALTH_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS " + HealthRecordEntry.TABLE_NAME + " ("
            + HealthRecordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE + " TEXT NOT NULL, "
            + HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME + " TEXT NOT NULL, "
            + HealthRecordEntry.COLUMN_DOCTORS_PHONE + " INTEGER NOT NULL, "
            + HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME + " TEXT NOT NULL, "
            + HealthRecordEntry.COLUMN_PHARMACY_PHONE + " INTEGER NOT NULL, "
            + HealthRecordEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'current and past medications' table
    private static final String SQL_CREATE_CURRENT_AND_PAST_MEDICATIONS_TABLE = "CREATE TABLE IF NOT EXISTS " +
            CurrentAndPastMedicationsEntry.TABLE_NAME + " ("
            + CurrentAndPastMedicationsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME + " TEXT NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN + " TEXT NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_DOSAGE + " TEXT NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_FREQ + " INTEGER NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_PURPOSE + " TEXT NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_START_DATE + " TEXT NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_END_DATE + " TEXT NOT NULL, "
            + CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'major illnesses' table
    private static final String SQL_CREATE_MAJOR_ILLNESSES_TABLE = "CREATE TABLE IF NOT EXISTS " + MajorIllnessesEntry.TABLE_NAME + " ("
            + MajorIllnessesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MajorIllnessesEntry.COLUMN_ILLNESS + " TEXT NOT NULL, "
            + MajorIllnessesEntry.COLUMN_START_DATE + " TEXT NOT NULL, "
            + MajorIllnessesEntry.COLUMN_END_DATE + " TEXT NOT NULL, "
            + MajorIllnessesEntry.COLUMN_PHYSICIAN + " TEXT NOT NULL, "
            + MajorIllnessesEntry.COLUMN_TREATMENT_NOTES + " TEXT NOT NULL, "
            + MajorIllnessesEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'surgical procedures' table
    private static final String SQL_CREATE_SURGICAL_PROCEDURES_TABLE = "CREATE TABLE IF NOT EXISTS " + SurgicalProceduresEntry.TABLE_NAME + " ("
            + SurgicalProceduresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SurgicalProceduresEntry.COLUMN_PROCEDURE + " TEXT NOT NULL, "
            + SurgicalProceduresEntry.COLUMN_PHYSICIAN + " TEXT NOT NULL, "
            + SurgicalProceduresEntry.COLUMN_HOSPITAL + " TEXT NOT NULL, "
            + SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES + " TEXT NOT NULL, "
            + SurgicalProceduresEntry.COLUMN_NOTES + " TEXT NOT NULL, "
            + SurgicalProceduresEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'basic patient vaccines' table
    private static final String SQL_CREATE_PATIENT_VACCINES_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientVaccinesEntry.TABLE_NAME + " ("
            + PatientVaccinesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION + " INTEGER NOT NULL DEFAULT 0, "
            + PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION + " TEXT NOT NULL, "
            + PatientVaccinesEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'doctor diagnosis' table
    private static final String SQL_CREATE_DOCTOR_DIAGNOSIS_TABLE = "CREATE TABLE IF NOT EXISTS " + DoctorDiagnosisEntry.TABLE_NAME + " ("
            + DoctorDiagnosisEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DoctorDiagnosisEntry.COLUMN_DIAGNOSIS + " TEXT NOT NULL, "
            + DoctorDiagnosisEntry.COLUMN_Date_of_Service + " TEXT NOT NULL, "
            + DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES + " TEXT NOT NULL, "
            + DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE + " TEXT NOT NULL, "
            + DoctorDiagnosisEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient data to pharmacy' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_PHARMACY_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToPharmacyEntry.TABLE_NAME + " ("
            + PatientDataToPharmacyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToPharmacyEntry.COLUMN_TRANSFER_DATE + " TEXT NOT NULL, "
            + PatientDataToPharmacyEntry.COLUMN_DOCTOR_DIAGNOSIS_ID + " INTEGER NOT NULL, "
            + PatientDataToPharmacyEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient data to radiology' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_RADIOLOGY_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToRadiologyEntry.TABLE_NAME + " ("
            + PatientDataToRadiologyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE + " TEXT NOT NULL, "
            + PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION + " INTEGER NOT NULL DEFAULT 0, "
            + PatientDataToRadiologyEntry.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'medicine registry' table
    private static final String SQL_CREATE_MEDICINE_REGISTRY_TABLE = "CREATE TABLE IF NOT EXISTS " + MedicineRegistryEntry.TABLE_NAME + " ("
            + MedicineRegistryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MedicineRegistryEntry.COLUMN_QR + " INTEGER NOT NULL, "
            + MedicineRegistryEntry.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "
            + MedicineRegistryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
            + MedicineRegistryEntry.COLUMN_PRICE + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'sales record' table
    private static final String SQL_CREATE_SALES_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS " + SalesRecordEntry.TABLE_NAME + " ("
            + SalesRecordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SalesRecordEntry.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "
            + SalesRecordEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
            + SalesRecordEntry.COLUMN_SALE_DATE + " TEXT NOT NULL, "
            + SalesRecordEntry.COLUMN_PRICE + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'employees' table
    private static final String SQL_CREATE_EMPLOYEES_TABLE = "CREATE TABLE IF NOT EXISTS " + EmployeesEntry.TABLE_NAME + " ("
            + EmployeesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + EmployeesEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, "
            + EmployeesEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, "
            + EmployeesEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
            + EmployeesEntry.COLUMN_PHONE_NUMBER + " INTEGER NOT NULL, "
            + EmployeesEntry.COLUMN_HIRE_DATE + " TEXT NOT NULL, "
            + EmployeesEntry.COLUMN_SALARY + " INTEGER NOT NULL, "
            + EmployeesEntry.COLUMN_JOB_TITLE + " TEXT NOT NULL, "
            + EmployeesEntry.COLUMN_MIN_SALARY + " INTEGER, "
            + EmployeesEntry.COLUMN_MAX_SALARY + " INTEGER, "
            + EmployeesEntry.COLUMN_DEPARTMENT_NAME + " INTEGER NOT NULL DEFAULT 0, "
            + EmployeesEntry.COLUMN_REGION_NAME + " INTEGER NOT NULL DEFAULT 0, "
            + EmployeesEntry.COLUMN_COUNTRY_NAME + " INTEGER NOT NULL DEFAULT 0, "
            + EmployeesEntry.COLUMN_CITY + " INTEGER NOT NULL DEFAULT 0, "
            + EmployeesEntry.COLUMN_STREET_ADDRESS + " TEXT, "
            + EmployeesEntry.COLUMN_POSTAL_CODE + " INTEGER NOT NULL); ";

    private static final String SQL_DROP_PATIENT_TABLE = "DROP TABLE IF EXISTS " + PatientEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_ANALYSIS_TABLE = "DROP TABLE IF EXISTS " + PatientDataToAnalysisEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_CLINICS_TABLE = "DROP TABLE IF EXISTS " + PatientDataToClinicsEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_RECORDS_TABLE = "DROP TABLE IF EXISTS " + PatientRecordsEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_PROGRESS_TABLE = "DROP TABLE IF EXISTS " + PatientProgressEntry.TABLE_NAME;
    private static final String SQL_DROP_INVOICES_TABLE = "DROP TABLE IF EXISTS " + InvoicesEntry.TABLE_NAME;
    private static final String SQL_DROP_HEALTH_RECORD_TABLE = "DROP TABLE IF EXISTS " + HealthRecordEntry.TABLE_NAME;
    private static final String SQL_DROP_CURRENT_AND_PAST_MEDICATIONS_TABLE = "DROP TABLE IF EXISTS " + CurrentAndPastMedicationsEntry.TABLE_NAME;
    private static final String SQL_DROP_MAJOR_ILLNESSES_TABLE = "DROP TABLE IF EXISTS " + MajorIllnessesEntry.TABLE_NAME;
    private static final String SQL_DROP_SURGICAL_PROCEDURES_TABLE = "DROP TABLE IF EXISTS " + SurgicalProceduresEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_VACCINES_TABLE = "DROP TABLE IF EXISTS " + PatientVaccinesEntry.TABLE_NAME;
    private static final String SQL_DROP_DOCTOR_DIAGNOSIS_TABLE = "DROP TABLE IF EXISTS " + DoctorDiagnosisEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_PHARMACY_TABLE = "DROP TABLE IF EXISTS " + PatientDataToPharmacyEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_RADIOLOGY_TABLE = "DROP TABLE IF EXISTS " + PatientDataToRadiologyEntry.TABLE_NAME;
    private static final String SQL_DROP_MEDICINE_REGISTRY_TABLE = "DROP TABLE IF EXISTS " + MedicineRegistryEntry.TABLE_NAME;
    private static final String SQL_DROP_SALES_RECORD_TABLE = "DROP TABLE IF EXISTS " + SalesRecordEntry.TABLE_NAME;
    private static final String SQL_DROP_EMPLOYEES_TABLE = "DROP TABLE IF EXISTS " + EmployeesEntry.TABLE_NAME;

    /**
     * Constructs a new instance of {@link ImsDbHelper}.
     *
     * @param context of the app
     */
    public ImsDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This is called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PATIENT_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_DATA_TO_ANALYSIS_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_DATA_TO_CLINICS_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_RECORDS_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_PROGRESS_TABLE);
        db.execSQL(SQL_CREATE_INVOICES_TABLE);
        db.execSQL(SQL_CREATE_HEALTH_RECORD_TABLE);
        db.execSQL(SQL_CREATE_CURRENT_AND_PAST_MEDICATIONS_TABLE);
        db.execSQL(SQL_CREATE_MAJOR_ILLNESSES_TABLE);
        db.execSQL(SQL_CREATE_SURGICAL_PROCEDURES_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_VACCINES_TABLE);
        db.execSQL(SQL_CREATE_DOCTOR_DIAGNOSIS_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_DATA_TO_PHARMACY_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_DATA_TO_RADIOLOGY_TABLE);
        db.execSQL(SQL_CREATE_MEDICINE_REGISTRY_TABLE);
        db.execSQL(SQL_CREATE_SALES_RECORD_TABLE);
        db.execSQL(SQL_CREATE_EMPLOYEES_TABLE);
    }

    // This is called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
        db.execSQL(SQL_DROP_PATIENT_TABLE);
        db.execSQL(SQL_DROP_PATIENT_DATA_TO_ANALYSIS_TABLE);
        db.execSQL(SQL_DROP_PATIENT_DATA_TO_CLINICS_TABLE);
        db.execSQL(SQL_DROP_PATIENT_RECORDS_TABLE);
        db.execSQL(SQL_DROP_PATIENT_PROGRESS_TABLE);
        db.execSQL(SQL_DROP_INVOICES_TABLE);
        db.execSQL(SQL_DROP_HEALTH_RECORD_TABLE);
        db.execSQL(SQL_DROP_CURRENT_AND_PAST_MEDICATIONS_TABLE);
        db.execSQL(SQL_DROP_MAJOR_ILLNESSES_TABLE);
        db.execSQL(SQL_DROP_SURGICAL_PROCEDURES_TABLE);
        db.execSQL(SQL_DROP_PATIENT_VACCINES_TABLE);
        db.execSQL(SQL_DROP_DOCTOR_DIAGNOSIS_TABLE);
        db.execSQL(SQL_DROP_PATIENT_DATA_TO_PHARMACY_TABLE);
        db.execSQL(SQL_DROP_PATIENT_DATA_TO_RADIOLOGY_TABLE);
        db.execSQL(SQL_DROP_MEDICINE_REGISTRY_TABLE);
        db.execSQL(SQL_DROP_SALES_RECORD_TABLE);
        db.execSQL(SQL_DROP_EMPLOYEES_TABLE);
        onCreate(db);
    }
}
