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
    private static final String SQL_CREATE_PATIENT_DATA_TO_ANALYSIS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToAnalysis.TABLE_NAME + " ("
            + PatientDataToAnalysis._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToAnalysis.COLUMN_TRANSFER_DATE + " TEXT NOT NULL, "
            + PatientDataToAnalysis.COLUMN_ANALYSIS_NAME + " TEXT NOT NULL, "
            + PatientDataToAnalysis.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient data to clinics' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_CLINICS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToClinics.TABLE_NAME + " ("
            + PatientDataToClinics._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToClinics.COLUMN_TRANSFER_DATE + " TEXT NOT NULL, "
            + PatientDataToClinics.COLUMN_CLINIC_NAME + " TEXT NOT NULL, "
            + PatientDataToClinics.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient records' table
    private static final String SQL_CREATE_PATIENT_RECORDS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientRecords.TABLE_NAME + " ("
            + PatientRecords._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientRecords.COLUMN_BILL_TO_NAME + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_DATE_OF_BIRTH + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_MEDICAL_RECORD_ID + " INTEGER NOT NULL, "
            + PatientRecords.COLUMN_NEXT_APPOINTMENT_DATE + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_PHYSICIAN_SIGNATURE + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_DATE_SIGNED + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_X_RAY_IMAGE + " TEXT NOT NULL, "
            + PatientRecords.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient progress' table
    private static final String SQL_CREATE_PATIENT_PROGRESS_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientProgress.TABLE_NAME + " ("
            + PatientProgress._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientProgress.COLUMN_PROGRESS_NOTES + " TEXT NOT NULL, "
            + PatientProgress.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'invoices' table
    private static final String SQL_CREATE_INVOICES_TABLE = "CREATE TABLE IF NOT EXISTS " + Invoices.TABLE_NAME + " ("
            + Invoices._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Invoices.COLUMN_DATE_OF_SVC + " TEXT NOT NULL, "
            + Invoices.COLUMN_INVOICE_DATE + " TEXT NOT NULL, "
            + Invoices.COLUMN_DATE_DUE + " TEXT NOT NULL, "
            + Invoices.COLUMN_BILL_TO_NAME + " TEXT NOT NULL, "
            + Invoices.COLUMN_BILL_TO_ADDRESS + " TEXT NOT NULL, "
            + Invoices.COLUMN_BILL_TO_PHONE + " INTEGER NOT NULL, "
            + Invoices.COLUMN_BILL_TO_FAX + " INTEGER NOT NULL, "
            + Invoices.COLUMN_BILL_TO_EMAIL + " TEXT NOT NULL, "
            + Invoices.COLUMN_SVC_ID + " INTEGER NOT NULL, "
            + Invoices.COLUMN_MEDICAL_SERVICES + " TEXT NOT NULL, "
            + Invoices.COLUMN_MEDICATION + " TEXT NOT NULL, "
            + Invoices.COLUMN_COST + " INTEGER NOT NULL, "
            + Invoices.COLUMN_SUBTOTAL + " INTEGER NOT NULL, "
            + Invoices.COLUMN_TAX_RATE + " INTEGER NOT NULL, "
            + Invoices.COLUMN_TOTAL_TAX + " INTEGER NOT NULL, "
            + Invoices.COLUMN_OTHER + " INTEGER NOT NULL, "
            + Invoices.COLUMN_TOTAL + " INTEGER NOT NULL, "
            + Invoices.COLUMN_QUESTIONS_NAME + " TEXT NOT NULL, "
            + Invoices.COLUMN_QUESTIONS_EMAIL + " TEXT NOT NULL, "
            + Invoices.COLUMN_QUESTIONS_PHONE + " INTEGER NOT NULL, "
            + Invoices.COLUMN_QUESTIONS_WEB + " TEXT NOT NULL, "
            + Invoices.COLUMN_PROCEDURE + " TEXT NOT NULL, "
            + Invoices.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'health record' table
    private static final String SQL_CREATE_HEALTH_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS " + HealthRecord.TABLE_NAME + " ("
            + HealthRecord._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HealthRecord.COLUMN_DATE_OF_THE_LAST_UPDATE + " TEXT NOT NULL, "
            + HealthRecord.COLUMN_CURRENT_PHYSICIAN_NAME + " TEXT NOT NULL, "
            + HealthRecord.COLUMN_DOCTORS_PHONE + " INTEGER NOT NULL, "
            + HealthRecord.COLUMN_CURRENT_PHARMACY_NAME + " TEXT NOT NULL, "
            + HealthRecord.COLUMN_PHARMACY_PHONE + " INTEGER NOT NULL, "
            + HealthRecord.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'current and past medications' table
    private static final String SQL_CREATE_CURRENT_AND_PAST_MEDICATIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + CurrentAndPastMedications.TABLE_NAME + " ("
            + CurrentAndPastMedications._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CurrentAndPastMedications.COLUMN_PHYSICIAN + " TEXT NOT NULL, "
            + CurrentAndPastMedications.COLUMN_DOSAGE + " TEXT NOT NULL, "
            + CurrentAndPastMedications.COLUMN_FREQ + " INTEGER NOT NULL, "
            + CurrentAndPastMedications.COLUMN_PURPOSE + " TEXT NOT NULL, "
            + CurrentAndPastMedications.COLUMN_START_DATE + " TEXT NOT NULL, "
            + CurrentAndPastMedications.COLUMN_END_DATE + " TEXT NOT NULL, "
            + CurrentAndPastMedications.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'major illnesses' table
    private static final String SQL_CREATE_MAJOR_ILLNESSES_TABLE = "CREATE TABLE IF NOT EXISTS " + MajorIllnesses.TABLE_NAME + " ("
            + MajorIllnesses._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MajorIllnesses.COLUMN_ILLNESS + " TEXT NOT NULL, "
            + MajorIllnesses.COLUMN_START_DATE + " TEXT NOT NULL, "
            + MajorIllnesses.COLUMN_END_DATE + " TEXT NOT NULL, "
            + MajorIllnesses.COLUMN_PHYSICIAN + " TEXT NOT NULL, "
            + MajorIllnesses.COLUMN_TREATMENT_NOTES + " TEXT NOT NULL, "
            + MajorIllnesses.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'surgical procedures' table
    private static final String SQL_CREATE_SURGICAL_PROCEDURES_TABLE = "CREATE TABLE IF NOT EXISTS " + SurgicalProcedures.TABLE_NAME + " ("
            + SurgicalProcedures._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SurgicalProcedures.COLUMN_PROCEDURE + " TEXT NOT NULL, "
            + SurgicalProcedures.COLUMN_PHYSICIAN + " TEXT NOT NULL, "
            + SurgicalProcedures.COLUMN_HOSPITAL + " TEXT NOT NULL, "
            + SurgicalProcedures.COLUMN_DATE_SURGICAL_PROCEDURES + " TEXT NOT NULL, "
            + SurgicalProcedures.COLUMN_NOTES + " TEXT NOT NULL, "
            + SurgicalProcedures.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient vaccines' table
    private static final String SQL_CREATE_PATIENT_VACCINES_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientVaccines.TABLE_NAME + " ("
            + PatientVaccines._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientVaccines.COLUMN_TETANUS + " TEXT NOT NULL, "
            + PatientVaccines.COLUMN_NAME_OF_VACCINATION + " TEXT NOT NULL, "
            + PatientVaccines.COLUMN_HISTORY_OF_VACCINATION + " TEXT NOT NULL, "
            + PatientVaccines.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'doctor diagnosis' table
    private static final String SQL_CREATE_DOCTOR_DIAGNOSIS_TABLE = "CREATE TABLE IF NOT EXISTS " + DoctorDiagnosis.TABLE_NAME + " ("
            + DoctorDiagnosis._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DoctorDiagnosis.COLUMN_DIAGNOSIS + " TEXT NOT NULL, "
            + DoctorDiagnosis.COLUMN_ADDITIONAL_NOTES + " TEXT NOT NULL, "
            + DoctorDiagnosis.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE + " TEXT NOT NULL, "
            + DoctorDiagnosis.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient data to pharmacy' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_PHARMACY_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToPharmacy.TABLE_NAME + " ("
            + PatientDataToPharmacy._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToPharmacy.COLUMN_TRANSFER_DATA + " TEXT NOT NULL, "
            + PatientDataToPharmacy.COLUMN_DOCTOR_DIAGNOSIS_ID + " INTEGER NOT NULL, "
            + PatientDataToPharmacy.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'patient data to radiology' table
    private static final String SQL_CREATE_PATIENT_DATA_TO_RADIOLOGY_TABLE = "CREATE TABLE IF NOT EXISTS " + PatientDataToRadiology.TABLE_NAME + " ("
            + PatientDataToRadiology._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PatientDataToRadiology.COLUMN_TRANSFER_DATA + " TEXT NOT NULL, "
            + PatientDataToRadiology.COLUMN_TYPES_OF_RADIATION + " TEXT NOT NULL, "
            + PatientDataToRadiology.COLUMN_PATIENT_ID + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'medicine registry' table
    private static final String SQL_CREATE_MEDICINE_REGISTRY_TABLE = "CREATE TABLE IF NOT EXISTS " + MedicineRegistry.TABLE_NAME + " ("
            + MedicineRegistry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MedicineRegistry.COLUMN_QR + " INTEGER NOT NULL, "
            + MedicineRegistry.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "
            + MedicineRegistry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
            + MedicineRegistry.COLUMN_PRICE + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'sales record' table
    private static final String SQL_CREATE_SALES_RECORD_TABLE = "CREATE TABLE IF NOT EXISTS " + SalesRecord.TABLE_NAME + " ("
            + SalesRecord._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SalesRecord.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "
            + SalesRecord.COLUMN_QUANTITY + " INTEGER NOT NULL, "
            + SalesRecord.COLUMN_SALE_DATE + " TEXT NOT NULL, "
            + SalesRecord.COLUMN_PRICE + " INTEGER NOT NULL); ";

    // Create a String that contains the SQL statement to create the 'employees' table
    private static final String SQL_CREATE_EMPLOYEES_TABLE = "CREATE TABLE IF NOT EXISTS " + Employees.TABLE_NAME + " ("
            + Employees._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Employees.COLUMN_FIRST_NAME + " TEXT NOT NULL, "
            + Employees.COLUMN_LAST_NAME + " TEXT NOT NULL, "
            + Employees.COLUMN_EMAIL + " TEXT NOT NULL, "
            + Employees.COLUMN_PHONE_NUMBER + " INTEGER NOT NULL, "
            + Employees.COLUMN_HIRE_DATE + " TEXT NOT NULL, "
            + Employees.COLUMN_SALARY + " INTEGER NOT NULL, "
            + Employees.COLUMN_JOB_TITLE + " TEXT NOT NULL, "
            + Employees.COLUMN_MIN_SALARY + " INTEGER, "
            + Employees.COLUMN_MAX_SALARY + " INTEGER, "
            + Employees.COLUMN_DEPARTMENT_NAME + " TEXT NOT NULL, "
            + Employees.COLUMN_REGION_NAME + " TEXT NOT NULL, "
            + Employees.COLUMN_COUNTRY_NAME + " TEXT NOT NULL, "
            + Employees.COLUMN_CITY + " TEXT NOT NULL, "
            + Employees.COLUMN_STREET_ADDRESS + " TEXT, "
            + Employees.COLUMN_POSTAL_CODE + " INTEGER NOT NULL); ";

    private static final String SQL_DROP_PATIENT_TABLE = "DROP TABLE IF EXISTS " + PatientEntry.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_ANALYSIS_TABLE = "DROP TABLE IF EXISTS " + PatientDataToAnalysis.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_CLINICS_TABLE = "DROP TABLE IF EXISTS " + PatientDataToClinics.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_RECORDS_TABLE = "DROP TABLE IF EXISTS " + PatientRecords.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_PROGRESS_TABLE = "DROP TABLE IF EXISTS " + PatientProgress.TABLE_NAME;
    private static final String SQL_DROP_INVOICES_TABLE = "DROP TABLE IF EXISTS " + Invoices.TABLE_NAME;
    private static final String SQL_DROP_HEALTH_RECORD_TABLE = "DROP TABLE IF EXISTS " + HealthRecord.TABLE_NAME;
    private static final String SQL_DROP_CURRENT_AND_PAST_MEDICATIONS_TABLE = "DROP TABLE IF EXISTS " + CurrentAndPastMedications.TABLE_NAME;
    private static final String SQL_DROP_MAJOR_ILLNESSES_TABLE = "DROP TABLE IF EXISTS " + MajorIllnesses.TABLE_NAME;
    private static final String SQL_DROP_SURGICAL_PROCEDURES_TABLE = "DROP TABLE IF EXISTS " + SurgicalProcedures.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_VACCINES_TABLE = "DROP TABLE IF EXISTS " + PatientVaccines.TABLE_NAME;
    private static final String SQL_DROP_DOCTOR_DIAGNOSIS_TABLE = "DROP TABLE IF EXISTS " + DoctorDiagnosis.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_PHARMACY_TABLE = "DROP TABLE IF EXISTS " + PatientDataToPharmacy.TABLE_NAME;
    private static final String SQL_DROP_PATIENT_DATA_TO_RADIOLOGY_TABLE = "DROP TABLE IF EXISTS " + PatientDataToRadiology.TABLE_NAME;
    private static final String SQL_DROP_MEDICINE_REGISTRY_TABLE = "DROP TABLE IF EXISTS " + MedicineRegistry.TABLE_NAME;
    private static final String SQL_DROP_SALES_RECORD_TABLE = "DROP TABLE IF EXISTS " + SalesRecord.TABLE_NAME;
    private static final String SQL_DROP_EMPLOYEES_TABLE = "DROP TABLE IF EXISTS " + Employees.TABLE_NAME;

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
