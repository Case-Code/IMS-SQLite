package com.example.ims.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ims.data.ImsContract.*;

public class ImsProvider extends ContentProvider {

    public static final String LOG_TAG = ImsProvider.class.getSimpleName();

    // Patient
    private static final int PATIENT = 100;
    private static final int PATIENT_ID = 101;

    // Patient data to analysis
    private static final int PATIENT_DATA_TO_ANALYSIS = 102;
    private static final int PATIENT_DATA_TO_ANALYSIS_ID = 103;

    // Patient data to clinics
    private static final int PATIENT_DATA_TO_CLINICS = 104;
    private static final int PATIENT_DATA_TO_CLINICS_ID = 105;

    // Patient records
    private static final int PATIENT_RECORDS = 106;
    private static final int PATIENT_RECORDS_ID = 107;

    // Patient progress
    private static final int PATIENT_PROGRESS = 108;
    private static final int PATIENT_PROGRESS_ID = 109;

    // Invoices
    private static final int INVOICES = 110;
    private static final int INVOICES_ID = 111;

    // Health record
    private static final int HEALTH_RECORD = 112;
    private static final int HEALTH_RECORD_ID = 113;

    // Current and past medications
    private static final int CURRENT_AND_PAST_MEDICATIONS = 114;
    private static final int CURRENT_AND_PAST_MEDICATIONS_ID = 115;

    // Major illnesses
    private static final int MAJOR_ILLNESSES = 116;
    private static final int MAJOR_ILLNESSES_ID = 117;

    // Surgical procedures
    private static final int SURGICAL_PROCEDURES = 118;
    private static final int SURGICAL_PROCEDURES_ID = 119;

    // Patient vaccines
    private static final int PATIENT_VACCINES = 120;
    private static final int PATIENT_VACCINES_ID = 121;

    // Doctor diagnosis
    private static final int DOCTOR_DIAGNOSIS = 122;
    private static final int DOCTOR_DIAGNOSIS_ID = 123;

    // Patient data to pharmacy
    private static final int PATIENT_DATA_TO_PHARMACY = 124;
    private static final int PATIENT_DATA_TO_PHARMACY_ID = 125;

    // Patient data to radiology
    private static final int PATIENT_DATA_TO_RADIOLOGY = 126;
    private static final int PATIENT_DATA_TO_RADIOLOGY_ID = 127;

    // Medicine registry
    private static final int MEDICINE_REGISTRY = 128;
    private static final int MEDICINE_REGISTRY_ID = 129;

    // Sales record
    private static final int SALES_RECORD = 130;
    private static final int SALES_RECORD_ID = 131;

    // Employees
    private static final int EMPLOYEES = 132;
    private static final int EMPLOYEES_ID = 133;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // Patient
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT, PATIENT);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT + "/#", PATIENT_ID);

        // Patient data to analysis
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_ANALYSIS, PATIENT_DATA_TO_ANALYSIS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_ANALYSIS + "/#", PATIENT_DATA_TO_ANALYSIS_ID);

        // Patient data to clinics
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_CLINICS, PATIENT_DATA_TO_CLINICS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_CLINICS + "/#", PATIENT_DATA_TO_CLINICS_ID);

        // Patient records
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_RECORDS, PATIENT_RECORDS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_RECORDS + "/#", PATIENT_RECORDS_ID);

        // Patient progress
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_PROGRESS, PATIENT_PROGRESS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_PROGRESS + "/#", PATIENT_PROGRESS_ID);

        // Invoices
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_INVOICES, INVOICES);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_INVOICES + "/#", INVOICES_ID);

        // Health record
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_HEALTH_RECORD, HEALTH_RECORD);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_HEALTH_RECORD + "/#", HEALTH_RECORD_ID);

        // Current and past medications
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_CURRENT_AND_PAST_MEDICATIONS, CURRENT_AND_PAST_MEDICATIONS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_CURRENT_AND_PAST_MEDICATIONS + "/#", CURRENT_AND_PAST_MEDICATIONS_ID);

        // Major illnesses
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_MAJOR_ILLNESSES, MAJOR_ILLNESSES);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_MAJOR_ILLNESSES + "/#", MAJOR_ILLNESSES_ID);

        // Surgical procedures
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_SURGICAL_PROCEDURES, SURGICAL_PROCEDURES);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_SURGICAL_PROCEDURES + "/#", SURGICAL_PROCEDURES_ID);

        // Patient vaccines
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_VACCINES, PATIENT_VACCINES);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_VACCINES + "/#", PATIENT_VACCINES_ID);

        // Doctor diagnosis
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_DOCTOR_DIAGNOSIS, DOCTOR_DIAGNOSIS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_DOCTOR_DIAGNOSIS + "/#", DOCTOR_DIAGNOSIS_ID);

        // Patient data to pharmacy
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_PHARMACY, PATIENT_DATA_TO_PHARMACY);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_PHARMACY + "/#", PATIENT_DATA_TO_PHARMACY_ID);

        // Patient data to radiology
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_RADIOLOGY, PATIENT_DATA_TO_RADIOLOGY);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT_DATA_TO_RADIOLOGY + "/#", PATIENT_DATA_TO_RADIOLOGY_ID);

        // Medicine registry
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_MEDICINE_REGISTRY, MEDICINE_REGISTRY);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_MEDICINE_REGISTRY + "/#", MEDICINE_REGISTRY_ID);

        // Sales record
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_SALES_RECORD, SALES_RECORD);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_SALES_RECORD + "/#", SALES_RECORD_ID);

        // Employees
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_EMPLOYEES, EMPLOYEES);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_EMPLOYEES + "/#", EMPLOYEES_ID);
    }

    private ImsDbHelper mImsDbHelper;

    @Override
    public boolean onCreate() {
        mImsDbHelper = new ImsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = mImsDbHelper.getReadableDatabase();
        Cursor cursor;

        int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATIENT:
                cursor = database.query(PatientEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_ID:
                selection = PatientEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_ANALYSIS:
                cursor = database.query(PatientDataToAnalysisEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_ANALYSIS_ID:
                selection = PatientDataToAnalysisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToAnalysisEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_CLINICS:
                cursor = database.query(PatientDataToClinicsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_CLINICS_ID:
                selection = PatientDataToClinicsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToClinicsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_RECORDS:
                cursor = database.query(PatientRecordsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_RECORDS_ID:
                selection = PatientRecordsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientRecordsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_PROGRESS:
                cursor = database.query(PatientProgressEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_PROGRESS_ID:
                selection = PatientProgressEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientProgressEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case INVOICES:
                cursor = database.query(InvoicesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INVOICES_ID:
                selection = InvoicesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(InvoicesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case HEALTH_RECORD:
                cursor = database.query(HealthRecordEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case HEALTH_RECORD_ID:
                selection = HealthRecordEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(HealthRecordEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CURRENT_AND_PAST_MEDICATIONS:
                cursor = database.query(CurrentAndPastMedicationsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                selection = CurrentAndPastMedicationsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(CurrentAndPastMedicationsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case MAJOR_ILLNESSES:
                cursor = database.query(MajorIllnessesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MAJOR_ILLNESSES_ID:
                selection = MajorIllnessesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(MajorIllnessesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case SURGICAL_PROCEDURES:
                cursor = database.query(SurgicalProceduresEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SURGICAL_PROCEDURES_ID:
                selection = SurgicalProceduresEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(SurgicalProceduresEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_VACCINES:
                cursor = database.query(PatientVaccinesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_VACCINES_ID:
                selection = PatientVaccinesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientVaccinesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case DOCTOR_DIAGNOSIS:
                cursor = database.query(DoctorDiagnosisEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DOCTOR_DIAGNOSIS_ID:
                selection = DoctorDiagnosisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(DoctorDiagnosisEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_PHARMACY:
                cursor = database.query(PatientDataToPharmacyEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_PHARMACY_ID:
                selection = PatientDataToPharmacyEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToPharmacyEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_RADIOLOGY:
                cursor = database.query(PatientDataToRadiologyEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                selection = PatientDataToRadiologyEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToRadiologyEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case MEDICINE_REGISTRY:
                cursor = database.query(MedicineRegistryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MEDICINE_REGISTRY_ID:
                selection = MedicineRegistryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(MedicineRegistryEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case SALES_RECORD:
                cursor = database.query(SalesRecordEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SALES_RECORD_ID:
                selection = SalesRecordEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(SalesRecordEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case EMPLOYEES:
                cursor = database.query(EmployeesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case EMPLOYEES_ID:
                selection = EmployeesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(EmployeesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATIENT:
                return PatientEntry.CONTENT_LIST_TYPE;
            case PATIENT_ID:
                return PatientEntry.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_ANALYSIS:
                return PatientDataToAnalysisEntry.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_ANALYSIS_ID:
                return PatientDataToAnalysisEntry.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_CLINICS:
                return PatientDataToClinicsEntry.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_CLINICS_ID:
                return PatientDataToClinicsEntry.CONTENT_ITEM_TYPE;

            case PATIENT_RECORDS:
                return PatientRecordsEntry.CONTENT_LIST_TYPE;
            case PATIENT_RECORDS_ID:
                return PatientRecordsEntry.CONTENT_ITEM_TYPE;

            case PATIENT_PROGRESS:
                return PatientProgressEntry.CONTENT_LIST_TYPE;
            case PATIENT_PROGRESS_ID:
                return PatientProgressEntry.CONTENT_ITEM_TYPE;

            case INVOICES:
                return InvoicesEntry.CONTENT_LIST_TYPE;
            case INVOICES_ID:
                return InvoicesEntry.CONTENT_ITEM_TYPE;

            case HEALTH_RECORD:
                return HealthRecordEntry.CONTENT_LIST_TYPE;
            case HEALTH_RECORD_ID:
                return HealthRecordEntry.CONTENT_ITEM_TYPE;

            case CURRENT_AND_PAST_MEDICATIONS:
                return CurrentAndPastMedicationsEntry.CONTENT_LIST_TYPE;
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                return CurrentAndPastMedicationsEntry.CONTENT_ITEM_TYPE;

            case MAJOR_ILLNESSES:
                return MajorIllnessesEntry.CONTENT_LIST_TYPE;
            case MAJOR_ILLNESSES_ID:
                return MajorIllnessesEntry.CONTENT_ITEM_TYPE;

            case SURGICAL_PROCEDURES:
                return SurgicalProceduresEntry.CONTENT_LIST_TYPE;
            case SURGICAL_PROCEDURES_ID:
                return SurgicalProceduresEntry.CONTENT_ITEM_TYPE;

            case PATIENT_VACCINES:
                return PatientVaccinesEntry.CONTENT_LIST_TYPE;
            case PATIENT_VACCINES_ID:
                return PatientVaccinesEntry.CONTENT_ITEM_TYPE;

            case DOCTOR_DIAGNOSIS:
                return DoctorDiagnosisEntry.CONTENT_LIST_TYPE;
            case DOCTOR_DIAGNOSIS_ID:
                return DoctorDiagnosisEntry.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_PHARMACY:
                return PatientDataToPharmacyEntry.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_PHARMACY_ID:
                return PatientDataToPharmacyEntry.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_RADIOLOGY:
                return PatientDataToRadiologyEntry.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                return PatientDataToRadiologyEntry.CONTENT_ITEM_TYPE;

            case MEDICINE_REGISTRY:
                return MedicineRegistryEntry.CONTENT_LIST_TYPE;
            case MEDICINE_REGISTRY_ID:
                return MedicineRegistryEntry.CONTENT_ITEM_TYPE;

            case SALES_RECORD:
                return SalesRecordEntry.CONTENT_LIST_TYPE;
            case SALES_RECORD_ID:
                return SalesRecordEntry.CONTENT_ITEM_TYPE;

            case EMPLOYEES:
                return EmployeesEntry.CONTENT_LIST_TYPE;
            case EMPLOYEES_ID:
                return EmployeesEntry.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        assert values != null;
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATIENT:
                return insertPatient(uri, values);
            case PATIENT_DATA_TO_ANALYSIS:
                return insertPatientDataToAnalysis(uri, values);
            case PATIENT_DATA_TO_CLINICS:
                return insertPatientDataToClinics(uri, values);
            case PATIENT_RECORDS:
                return insertPatientRecords(uri, values);
            case PATIENT_PROGRESS:
                return insertPatientProgress(uri, values);
            case INVOICES:
                return insertInvoices(uri, values);
            case HEALTH_RECORD:
                return insertHealthRecord(uri, values);
            case CURRENT_AND_PAST_MEDICATIONS:
                return insertCurrentAndPastMedications(uri, values);
            case MAJOR_ILLNESSES:
                return insertMajorIllnesses(uri, values);
            case SURGICAL_PROCEDURES:
                return insertSurgicalProcedures(uri, values);
            case PATIENT_VACCINES:
                return insertOtherPatientVaccines(uri, values);
            case DOCTOR_DIAGNOSIS:
                return insertDoctorDiagnosis(uri, values);
            case PATIENT_DATA_TO_PHARMACY:
                return insertPatientDataToPharmacy(uri, values);
            case PATIENT_DATA_TO_RADIOLOGY:
                return insertPatientDataToRadiology(uri, values);
            case MEDICINE_REGISTRY:
                return insertMedicineRegistry(uri, values);
            case SALES_RECORD:
                return insertSalesRecord(uri, values);
            case EMPLOYEES:
                return insertEmployees(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    // Insert patient
    private Uri insertPatient(Uri uri, ContentValues values) {
        String firstName = values.getAsString(PatientEntry.COLUMN_FIRST_NAME);
        if (firstName == null) {
            throw new IllegalArgumentException("Patient requires a first name");
        }

        String lastName = values.getAsString(PatientEntry.COLUMN_LAST_NAME);
        if (lastName == null) {
            throw new IllegalArgumentException("Patient requires a last name");
        }

        Integer phoneNumber = values.getAsInteger(PatientEntry.COLUMN_PHONE_NUMBER);
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Patient requires valid phone number");
        }

        String birthDate = values.getAsString(PatientEntry.COLUMN_BIRTH_DATE);
        if (birthDate == null) {
            throw new IllegalArgumentException("Patient requires a birth date");
        }

        String location = values.getAsString(PatientEntry.COLUMN_LOCATION);
        if (location == null) {
            throw new IllegalArgumentException("Patient requires a location");
        }

        Integer weight = values.getAsInteger(PatientEntry.COLUMN_WEIGHT);
        if (weight != null && weight < 0) {
            throw new IllegalArgumentException("Patient requires valid weight");
        }

        Integer height = values.getAsInteger(PatientEntry.COLUMN_HEIGHT);
        if (height != null && height < 0) {
            throw new IllegalArgumentException("Patient requires valid height");
        }

        Integer gender = values.getAsInteger(PatientEntry.COLUMN_GENDER);
        if (gender == null || !PatientEntry.isValidGender(gender)) {
            throw new IllegalArgumentException("Patient requires valid gender");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient data to analysis
    private Uri insertPatientDataToAnalysis(Uri uri, ContentValues values) {

        // Transfer date
        String transferDate = values.getAsString(PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to analysis  requires a transfer date");
        }

        // Analysis name
        String analysisName = values.getAsString(PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME);
        if (analysisName == null) {
            throw new IllegalArgumentException("Patient data to analysis  requires a analysis name");
        }


        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToAnalysisEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to analysis  requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToAnalysisEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient data to clinics
    private Uri insertPatientDataToClinics(Uri uri, ContentValues values) {

        // Transfer date
        String transferDate = values.getAsString(PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to clinics  requires a transfer date");
        }

        // Clinic name
        Integer clinicName = values.getAsInteger(PatientDataToClinicsEntry.COLUMN_CLINIC_NAME);
        if (clinicName == null) {
            throw new IllegalArgumentException("Patient data to clinics  requires a clinic name");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToClinicsEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to clinics  requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToClinicsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient records
    private Uri insertPatientRecords(Uri uri, ContentValues values) {

        // Bill to name
       /* String billToName = values.getAsString(PatientRecordsEntry.COLUMN_BILL_TO_NAME);
        if (billToName == null) {
            throw new IllegalArgumentException("Patient records requires a bill to name");

        }*/

        // Date of birth
      /*  String dateOfBirth = values.getAsString(PatientRecordsEntry.COLUMN_DATE_OF_BIRTH);
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Patient records requires a date of birth");
        }*/
/*
        // Medical record id
        Integer medicalRecordId = values.getAsInteger(PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID);
        if (medicalRecordId == null) {
            throw new IllegalArgumentException("Patient records requires a medical record id");
        }

        // Next appointment date
        String nextAppointmentDate = values.getAsString(PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE);
        if (nextAppointmentDate == null) {
            throw new IllegalArgumentException("Patient records requires a next appointment date");
        }*/

      /*  // Next treatment plan review date
        String nextTreatmentPlanReviewDate = values.getAsString(PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE);
        if (nextTreatmentPlanReviewDate == null) {
            throw new IllegalArgumentException("Patient records requires a next treatment plan review date");
        }

        // Physician signature
        String physicianSignature = values.getAsString(PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE);
        if (physicianSignature == null) {
            throw new IllegalArgumentException("Patient records requires a physician signature");
        }*/

        // Date signed
      /*  String dateSigned = values.getAsString(PatientRecordsEntry.COLUMN_DATE_SIGNED);
        if (dateSigned == null) {
            throw new IllegalArgumentException("Patient records requires a date signed");
        }
*/
        // X-ray image
       /* String xRayImage = values.getAsString(PatientRecordsEntry.COLUMN_X_RAY_IMAGE);
        if (xRayImage == null) {
            throw new IllegalArgumentException("Patient records requires a x-ray image");
        }*/

        // Patient id
        Integer patientId = values.getAsInteger(PatientRecordsEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient records requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientRecordsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient progress
    private Uri insertPatientProgress(Uri uri, ContentValues values) {

        // Progress notes
        String progressNotes = values.getAsString(PatientProgressEntry.COLUMN_PROGRESS_NOTES);
        if (progressNotes == null) {
            throw new IllegalArgumentException("Patient progress requires a progress notes");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientProgressEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient progress requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientProgressEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert invoices
    private Uri insertInvoices(Uri uri, ContentValues values) {

        // Date of svc
        String dateOfSvc = values.getAsString(InvoicesEntry.COLUMN_DATE_OF_SVC);
        if (dateOfSvc == null) {
            throw new IllegalArgumentException("Invoices requires a date of svc");
        }

        // Invoice date
        String invoiceDate = values.getAsString(InvoicesEntry.COLUMN_INVOICE_DATE);
        if (invoiceDate == null) {
            throw new IllegalArgumentException("Invoices requires a invoice date");
        }

        // Date due
        String dateDue = values.getAsString(InvoicesEntry.COLUMN_DATE_DUE);
        if (dateDue == null) {
            throw new IllegalArgumentException("Invoices requires a date due");
        }

        // Bill to name
        String billToName = values.getAsString(InvoicesEntry.COLUMN_BILL_TO_NAME);
        if (billToName == null) {
            throw new IllegalArgumentException("Invoices requires a bill to name");
        }

        // Bill to address
        String billToAddress = values.getAsString(InvoicesEntry.COLUMN_BILL_TO_ADDRESS);
        if (billToAddress == null) {
            throw new IllegalArgumentException("Invoices requires a bill to address");
        }

        // Bill to phone
        Integer billToPhone = values.getAsInteger(InvoicesEntry.COLUMN_BILL_TO_PHONE);
        if (billToPhone == null) {
            throw new IllegalArgumentException("Invoices requires a bill to phone");
        }

        // Bill to fax
        Integer billToFax = values.getAsInteger(InvoicesEntry.COLUMN_BILL_TO_FAX);
        if (billToFax == null) {
            throw new IllegalArgumentException("Invoices requires a bill to fax");
        }

        // Bill to email
        String billToEmail = values.getAsString(InvoicesEntry.COLUMN_BILL_TO_EMAIL);
        if (billToEmail == null) {
            throw new IllegalArgumentException("Invoices requires a bill to email");
        }

        // Svc id
        Integer svcId = values.getAsInteger(InvoicesEntry.COLUMN_SVC_ID);
        if (svcId == null) {
            throw new IllegalArgumentException("Invoices requires a svc id");
        }

        // Medical services
        String medicalServices = values.getAsString(InvoicesEntry.COLUMN_MEDICAL_SERVICES);
        if (medicalServices == null) {
            throw new IllegalArgumentException("Invoices requires a medical services");
        }

        // Medication
        String medication = values.getAsString(InvoicesEntry.COLUMN_MEDICATION);
        if (medication == null) {
            throw new IllegalArgumentException("Invoices requires a medication");
        }

        // Cost
        Integer cost = values.getAsInteger(InvoicesEntry.COLUMN_COST);
        if (cost == null) {
            throw new IllegalArgumentException("Invoices requires a cost");
        }

        // Subtotal
        Integer subtotal = values.getAsInteger(InvoicesEntry.COLUMN_SUBTOTAL);
        if (subtotal == null) {
            throw new IllegalArgumentException("Invoices requires a subtotal");
        }

        // Tax rate
        Integer taxRate = values.getAsInteger(InvoicesEntry.COLUMN_TAX_RATE);
        if (taxRate == null) {
            throw new IllegalArgumentException("Invoices requires a tax rate");
        }

        // Total tax
        Integer totalTax = values.getAsInteger(InvoicesEntry.COLUMN_TOTAL_TAX);
        if (totalTax == null) {
            throw new IllegalArgumentException("Invoices requires a total tax");
        }

        // Other
        Integer other = values.getAsInteger(InvoicesEntry.COLUMN_OTHER);
        if (other == null) {
            throw new IllegalArgumentException("Invoices requires a other");
        }

        // Total
        Integer total = values.getAsInteger(InvoicesEntry.COLUMN_TOTAL);
        if (total == null) {
            throw new IllegalArgumentException("Invoices requires a total");
        }

        // Questions name
        String questionsName = values.getAsString(InvoicesEntry.COLUMN_QUESTIONS_NAME);
        if (questionsName == null) {
            throw new IllegalArgumentException("Invoices requires a questions name");
        }

        // Questions email
        String questionsEmail = values.getAsString(InvoicesEntry.COLUMN_QUESTIONS_EMAIL);
        if (questionsEmail == null) {
            throw new IllegalArgumentException("Invoices requires a questions email");
        }

        // Questions phone
        Integer questionsPhone = values.getAsInteger(InvoicesEntry.COLUMN_QUESTIONS_PHONE);
        if (questionsPhone == null) {
            throw new IllegalArgumentException("Invoices requires a questions phone");
        }

        // Questions web
        String questionsWeb = values.getAsString(InvoicesEntry.COLUMN_QUESTIONS_WEB);
        if (questionsWeb == null) {
            throw new IllegalArgumentException("Invoices requires a questions web");
        }

        // Procedure
        String procedure = values.getAsString(InvoicesEntry.COLUMN_PROCEDURE);
        if (procedure == null) {
            throw new IllegalArgumentException("Invoices requires a procedure");
        }

        // Patient id
        Integer patientId = values.getAsInteger(InvoicesEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Invoices requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(InvoicesEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert health record
    private Uri insertHealthRecord(Uri uri, ContentValues values) {

        // Date of the last update
        String dateOfTheLastUpdate = values.getAsString(HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE);
        if (dateOfTheLastUpdate == null) {
            throw new IllegalArgumentException("Health record requires a date of the last update");
        }

        // Current physician name
        String currentPhysicianName = values.getAsString(HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME);
        if (currentPhysicianName == null) {
            throw new IllegalArgumentException("Health record requires a current physician name");
        }

        // Doctor's phone
        Integer doctorsPhone = values.getAsInteger(HealthRecordEntry.COLUMN_DOCTORS_PHONE);
        if (doctorsPhone == null) {
            throw new IllegalArgumentException("Health record requires a doctor's phone");
        }

        // current pharmacy name
        String currentPharmacyName = values.getAsString(HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME);
        if (currentPharmacyName == null) {
            throw new IllegalArgumentException("Health record requires a current pharmacy name");
        }

        // Pharmacy phone
        Integer pharmacyPhone = values.getAsInteger(HealthRecordEntry.COLUMN_PHARMACY_PHONE);
        if (pharmacyPhone == null) {
            throw new IllegalArgumentException("Health record requires a pharmacy phone");
        }

        // Patient id
        Integer patientId = values.getAsInteger(HealthRecordEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Health record requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(HealthRecordEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert current and past medications
    private Uri insertCurrentAndPastMedications(Uri uri, ContentValues values) {

        // Medicament name
        String medicamentName = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME);
        if (medicamentName == null) {
            throw new IllegalArgumentException("Current and past medications requires a medicament name");
        }

        // Physician
        String physician = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN);
        if (physician == null) {
            throw new IllegalArgumentException("Current and past medications requires a physician");
        }

        // dosage
        String dosage = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_DOSAGE);
        if (dosage == null) {
            throw new IllegalArgumentException("Current and past medications requires a dosage");
        }

        // Freq
        Integer freq = values.getAsInteger(CurrentAndPastMedicationsEntry.COLUMN_FREQ);
        if (freq == null) {
            throw new IllegalArgumentException("Current and past medications requires a freq");
        }

        // Purpose
        String purpose = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_PURPOSE);
        if (purpose == null) {
            throw new IllegalArgumentException("Current and past medications requires a purpose");
        }

        // Start date
        String startDate = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_START_DATE);
        if (startDate == null) {
            throw new IllegalArgumentException("Current and past medications requires a start date");
        }

        // End date
        String endDate = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_END_DATE);
        if (endDate == null) {
            throw new IllegalArgumentException("Current and past medications requires a end date");
        }

        // Patient id
        Integer patientId = values.getAsInteger(CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Current and past medications requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(CurrentAndPastMedicationsEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert major illnesses
    private Uri insertMajorIllnesses(Uri uri, ContentValues values) {

        // Illness
        String illness = values.getAsString(MajorIllnessesEntry.COLUMN_ILLNESS);
        if (illness == null) {
            throw new IllegalArgumentException("Major illnesses requires a illness");
        }

        // Start date
        String startDate = values.getAsString(MajorIllnessesEntry.COLUMN_START_DATE);
        if (startDate == null) {
            throw new IllegalArgumentException("Major illnesses requires a start date");
        }

        // End date
        String endDate = values.getAsString(MajorIllnessesEntry.COLUMN_END_DATE);
        if (endDate == null) {
            throw new IllegalArgumentException("Major illnesses requires a end date");
        }

        // Physician
        String physician = values.getAsString(MajorIllnessesEntry.COLUMN_PHYSICIAN);
        if (physician == null) {
            throw new IllegalArgumentException("Major illnesses requires a physician");
        }

        // Treatment notes
        String treatmentNotes = values.getAsString(MajorIllnessesEntry.COLUMN_TREATMENT_NOTES);
        if (treatmentNotes == null) {
            throw new IllegalArgumentException("Major illnesses requires a treatment notes");
        }

        // Patient id
        Integer patientId = values.getAsInteger(MajorIllnessesEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Major illnesses requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(MajorIllnessesEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert surgical procedures
    private Uri insertSurgicalProcedures(Uri uri, ContentValues values) {

        // Procedure
        String procedure = values.getAsString(SurgicalProceduresEntry.COLUMN_PROCEDURE);
        if (procedure == null) {
            throw new IllegalArgumentException("Surgical procedures requires a procedure");
        }

        // Physician
        String physician = values.getAsString(SurgicalProceduresEntry.COLUMN_PHYSICIAN);
        if (physician == null) {
            throw new IllegalArgumentException("Surgical procedures requires a physician");
        }

        // Hospital
        String hospital = values.getAsString(SurgicalProceduresEntry.COLUMN_HOSPITAL);
        if (hospital == null) {
            throw new IllegalArgumentException("Surgical procedures requires a hospital");
        }

        // Date surgical procedures
        String dateSurgicalProcedures = values.getAsString(SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES);
        if (dateSurgicalProcedures == null) {
            throw new IllegalArgumentException("Surgical procedures requires a date surgical procedures");
        }

        // Notes
        String notes = values.getAsString(SurgicalProceduresEntry.COLUMN_NOTES);
        if (notes == null) {
            throw new IllegalArgumentException("Surgical procedures requires a notes");
        }

        // Patient id
        Integer patientId = values.getAsInteger(SurgicalProceduresEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Surgical procedures requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(SurgicalProceduresEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient vaccines
    private Uri insertOtherPatientVaccines(Uri uri, ContentValues values) {

        // Name of vaccination
        Integer nameOfVaccination = values.getAsInteger(PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION);
        if (nameOfVaccination == null) {
            throw new IllegalArgumentException("Patient vaccines requires a name of vaccination");
        }

        // History of vaccination
        String historyOfVaccination = values.getAsString(PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION);
        if (historyOfVaccination == null) {
            throw new IllegalArgumentException("Patient vaccines requires a history of vaccination");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientVaccinesEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient vaccines requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientVaccinesEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert doctor diagnosis
    private Uri insertDoctorDiagnosis(Uri uri, ContentValues values) {

        // Diagnosis
        String diagnosis = values.getAsString(DoctorDiagnosisEntry.COLUMN_DIAGNOSIS);
        if (diagnosis == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a diagnosis");
        }

        // Additional notes
        String additionalNotes = values.getAsString(DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES);
        if (additionalNotes == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a additional notes");
        }

        // performing physician signature
        String performingPhysicianSignature = values.getAsString(DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE);
        if (performingPhysicianSignature == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a performing physician signature");
        }

        // Date of service
        String DateOfService = values.getAsString(DoctorDiagnosisEntry.COLUMN_DATE_OF_SERVICE);
        if (DateOfService == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a Date Of Service");
        }

        // Patient data to clinics id
        Integer patientDataToClinicsId = values.getAsInteger(DoctorDiagnosisEntry.COLUMN_PATIENT_DATA_TO_CLINICS_ID);
        if (patientDataToClinicsId == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a patient data to clinics id");
        }

        // Patient id
        Integer patientId = values.getAsInteger(DoctorDiagnosisEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(DoctorDiagnosisEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient data to pharmacy
    private Uri insertPatientDataToPharmacy(Uri uri, ContentValues values) {

        // Transfer date
        String transferDate = values.getAsString(PatientDataToPharmacyEntry.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to pharmacy requires a transfer date");
        }

        // Doctor diagnosis id
        Integer doctorDiagnosisId = values.getAsInteger(PatientDataToPharmacyEntry.COLUMN_DOCTOR_DIAGNOSIS_ID);
        if (doctorDiagnosisId == null) {
            throw new IllegalArgumentException("Patient data to pharmacy requires a doctor diagnosis id");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToPharmacyEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to pharmacy requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToPharmacyEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient data to radiology
    private Uri insertPatientDataToRadiology(Uri uri, ContentValues values) {

        // Transfer date
        String transferDate = values.getAsString(PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to radiology requires a transfer date");
        }

        // Types of radiation
        Integer typesOfRadiation = values.getAsInteger(PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION);
        if (typesOfRadiation == null) {
            throw new IllegalArgumentException("Patient data to radiology requires a types of radiation");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToRadiologyEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to radiology requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToRadiologyEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert medicine registry
    private Uri insertMedicineRegistry(Uri uri, ContentValues values) {

        // Qr
        Integer qr = values.getAsInteger(MedicineRegistryEntry.COLUMN_QR);
        if (qr == null) {
            throw new IllegalArgumentException("Medicine registry requires a qr");
        }

        // Medicine name
        String medicineName = values.getAsString(MedicineRegistryEntry.COLUMN_MEDICINE_NAME);
        if (medicineName == null) {
            throw new IllegalArgumentException("Medicine registry requires a medicine name");
        }

        // Quantity
        Integer quantity = values.getAsInteger(MedicineRegistryEntry.COLUMN_QUANTITY);
        if (quantity == null) {
            throw new IllegalArgumentException("Medicine registry requires a quantity");
        }

        // Price
        Integer price = values.getAsInteger(MedicineRegistryEntry.COLUMN_PRICE);
        if (price == null) {
            throw new IllegalArgumentException("Medicine registry requires a price");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(MedicineRegistryEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert sales record
    private Uri insertSalesRecord(Uri uri, ContentValues values) {

        // Medicine registry id
        String medicineName = values.getAsString(SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID);
        if (medicineName == null) {
            throw new IllegalArgumentException("Sales record requires a medicine name");
        }

        // Quantity
        Integer quantity = values.getAsInteger(SalesRecordEntry.COLUMN_QUANTITY);
        if (quantity == null) {
            throw new IllegalArgumentException("Sales record requires a quantity");
        }

        // Sale date
        String saleDate = values.getAsString(SalesRecordEntry.COLUMN_SALE_DATE);
        if (saleDate == null) {
            throw new IllegalArgumentException("v requires a sale date");
        }

        // Price
        Integer price = values.getAsInteger(SalesRecordEntry.COLUMN_PRICE);
        if (price == null) {
            throw new IllegalArgumentException("Sales record requires a price");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(SalesRecordEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert employees
    private Uri insertEmployees(Uri uri, ContentValues values) {

        // First name
        String firstName = values.getAsString(EmployeesEntry.COLUMN_FIRST_NAME);
        if (firstName == null) {
            throw new IllegalArgumentException("Employees requires a first name");
        }

        // Last name
        String lastName = values.getAsString(EmployeesEntry.COLUMN_LAST_NAME);
        if (lastName == null) {
            throw new IllegalArgumentException("Employees requires a last name");
        }

        // Email
        String email = values.getAsString(EmployeesEntry.COLUMN_EMAIL);
        if (email == null) {
            throw new IllegalArgumentException("Employees requires a email");
        }

        // Phone number
        Integer phoneNumber = values.getAsInteger(EmployeesEntry.COLUMN_PHONE_NUMBER);
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Employees requires a phone number");
        }

        // Hire date
        String hireDate = values.getAsString(EmployeesEntry.COLUMN_HIRE_DATE);
        if (hireDate == null) {
            throw new IllegalArgumentException("Employees requires a hire date");
        }

        // Salary
        Integer salary = values.getAsInteger(EmployeesEntry.COLUMN_SALARY);
        if (salary == null) {
            throw new IllegalArgumentException("Employees requires a salary");
        }

        // Job title
        String jobTitle = values.getAsString(EmployeesEntry.COLUMN_JOB_TITLE);
        if (jobTitle == null) {
            throw new IllegalArgumentException("Employees requires a job title");
        }

        // Min salary
        Integer minSalary = values.getAsInteger(EmployeesEntry.COLUMN_MIN_SALARY);
        if (minSalary < 0) {
            throw new IllegalArgumentException("Employees requires a min salary");
        }

        // Max salary
        Integer maxSalary = values.getAsInteger(EmployeesEntry.COLUMN_MAX_SALARY);
        if (maxSalary < 0) {
            throw new IllegalArgumentException("Employees requires a max salary");
        }

        // Department name
        String departmentName = values.getAsString(EmployeesEntry.COLUMN_DEPARTMENT_NAME);
        if (departmentName == null) {
            throw new IllegalArgumentException("Employees requires a department name");
        }

        // Region name
        String regionName = values.getAsString(EmployeesEntry.COLUMN_REGION_NAME);
        if (regionName == null) {
            throw new IllegalArgumentException("Employees requires a region name");
        }

        // Country name
        String countryName = values.getAsString(EmployeesEntry.COLUMN_COUNTRY_NAME);
        if (countryName == null) {
            throw new IllegalArgumentException("Employees requires a country name");
        }

        // City
        String city = values.getAsString(EmployeesEntry.COLUMN_CITY);
        if (city == null) {
            throw new IllegalArgumentException("Employees requires a city");
        }

        // Street address
        values.getAsString(EmployeesEntry.COLUMN_STREET_ADDRESS);

        // Postal code
        Integer postalCode = values.getAsInteger(EmployeesEntry.COLUMN_POSTAL_CODE);
        if (postalCode == null) {
            throw new IllegalArgumentException("Employees requires a postal code");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(EmployeesEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATIENT:
                rowsDeleted = database.delete(PatientEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_ID:
                selection = PatientEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_ANALYSIS:
                rowsDeleted = database.delete(PatientDataToAnalysisEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_ANALYSIS_ID:
                selection = PatientDataToAnalysisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToAnalysisEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_CLINICS:
                rowsDeleted = database.delete(PatientDataToClinicsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_CLINICS_ID:
                selection = PatientDataToClinicsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToClinicsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_RECORDS:
                rowsDeleted = database.delete(PatientRecordsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_RECORDS_ID:
                selection = PatientRecordsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientRecordsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_PROGRESS:
                rowsDeleted = database.delete(PatientProgressEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_PROGRESS_ID:
                selection = PatientProgressEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientProgressEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case INVOICES:
                rowsDeleted = database.delete(InvoicesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case INVOICES_ID:
                selection = InvoicesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(InvoicesEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case HEALTH_RECORD:
                rowsDeleted = database.delete(HealthRecordEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case HEALTH_RECORD_ID:
                selection = HealthRecordEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(HealthRecordEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case CURRENT_AND_PAST_MEDICATIONS:
                rowsDeleted = database.delete(CurrentAndPastMedicationsEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                selection = CurrentAndPastMedicationsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(CurrentAndPastMedicationsEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case MAJOR_ILLNESSES:
                rowsDeleted = database.delete(MajorIllnessesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MAJOR_ILLNESSES_ID:
                selection = MajorIllnessesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(MajorIllnessesEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case SURGICAL_PROCEDURES:
                rowsDeleted = database.delete(SurgicalProceduresEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case SURGICAL_PROCEDURES_ID:
                selection = SurgicalProceduresEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SurgicalProceduresEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_VACCINES:
                rowsDeleted = database.delete(PatientVaccinesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_VACCINES_ID:
                selection = PatientVaccinesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientVaccinesEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case DOCTOR_DIAGNOSIS:
                rowsDeleted = database.delete(DoctorDiagnosisEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DOCTOR_DIAGNOSIS_ID:
                selection = DoctorDiagnosisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(DoctorDiagnosisEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_PHARMACY:
                rowsDeleted = database.delete(PatientDataToPharmacyEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_PHARMACY_ID:
                selection = PatientDataToPharmacyEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToPharmacyEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_RADIOLOGY:
                rowsDeleted = database.delete(PatientDataToRadiologyEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                selection = PatientDataToRadiologyEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToRadiologyEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case MEDICINE_REGISTRY:
                rowsDeleted = database.delete(MedicineRegistryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MEDICINE_REGISTRY_ID:
                selection = MedicineRegistryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(MedicineRegistryEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case SALES_RECORD:
                rowsDeleted = database.delete(SalesRecordEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case SALES_RECORD_ID:
                selection = SalesRecordEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SalesRecordEntry.TABLE_NAME, selection, selectionArgs);
                break;

            case EMPLOYEES:
                rowsDeleted = database.delete(EmployeesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case EMPLOYEES_ID:
                selection = EmployeesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(EmployeesEntry.TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATIENT:
                return updatePatient(uri, values, selection, selectionArgs);
            case PATIENT_ID:
                selection = PatientEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatient(uri, values, selection, selectionArgs);

            case PATIENT_DATA_TO_ANALYSIS:
                return updatePatientDataToAnalysis(uri, values, selection, selectionArgs);
            case PATIENT_DATA_TO_ANALYSIS_ID:
                selection = PatientDataToAnalysisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatientDataToAnalysis(uri, values, selection, selectionArgs);

            case PATIENT_DATA_TO_CLINICS:
                return updatePatientDataToClinics(uri, values, selection, selectionArgs);
            case PATIENT_DATA_TO_CLINICS_ID:
                selection = PatientDataToClinicsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatientDataToClinics(uri, values, selection, selectionArgs);

            case PATIENT_RECORDS:
                return updatePatientRecords(uri, values, selection, selectionArgs);
            case PATIENT_RECORDS_ID:
                selection = PatientRecordsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatientRecords(uri, values, selection, selectionArgs);

            case PATIENT_PROGRESS:
                return updatePatientProgress(uri, values, selection, selectionArgs);
            case PATIENT_PROGRESS_ID:
                selection = PatientProgressEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatientProgress(uri, values, selection, selectionArgs);

            case INVOICES:
                return updateInvoices(uri, values, selection, selectionArgs);
            case INVOICES_ID:
                selection = InvoicesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateInvoices(uri, values, selection, selectionArgs);

            case HEALTH_RECORD:
                return updateHealthRecord(uri, values, selection, selectionArgs);
            case HEALTH_RECORD_ID:
                selection = HealthRecordEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateHealthRecord(uri, values, selection, selectionArgs);

            case CURRENT_AND_PAST_MEDICATIONS:
                return updateCurrentAndPastMedications(uri, values, selection, selectionArgs);
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                selection = CurrentAndPastMedicationsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateCurrentAndPastMedications(uri, values, selection, selectionArgs);

            case MAJOR_ILLNESSES:
                return updateMajorIllnesses(uri, values, selection, selectionArgs);
            case MAJOR_ILLNESSES_ID:
                selection = MajorIllnessesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateMajorIllnesses(uri, values, selection, selectionArgs);

            case SURGICAL_PROCEDURES:
                return updateSurgicalProcedures(uri, values, selection, selectionArgs);
            case SURGICAL_PROCEDURES_ID:
                selection = SurgicalProceduresEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateSurgicalProcedures(uri, values, selection, selectionArgs);

            case PATIENT_VACCINES:
                return updateOtherPatientVaccines(uri, values, selection, selectionArgs);
            case PATIENT_VACCINES_ID:
                selection = PatientVaccinesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateOtherPatientVaccines(uri, values, selection, selectionArgs);

            case DOCTOR_DIAGNOSIS:
                return updateDoctorDiagnosis(uri, values, selection, selectionArgs);
            case DOCTOR_DIAGNOSIS_ID:
                selection = DoctorDiagnosisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateDoctorDiagnosis(uri, values, selection, selectionArgs);

            case PATIENT_DATA_TO_PHARMACY:
                return updatePatientDataToPharmacy(uri, values, selection, selectionArgs);
            case PATIENT_DATA_TO_PHARMACY_ID:
                selection = PatientDataToPharmacyEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatientDataToPharmacy(uri, values, selection, selectionArgs);

            case PATIENT_DATA_TO_RADIOLOGY:
                return updatePatientDataToRadiology(uri, values, selection, selectionArgs);
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                selection = PatientDataToRadiologyEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePatientDataToRadiology(uri, values, selection, selectionArgs);

            case MEDICINE_REGISTRY:
                return updateMedicineRegistry(uri, values, selection, selectionArgs);
            case MEDICINE_REGISTRY_ID:
                selection = MedicineRegistryEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateMedicineRegistry(uri, values, selection, selectionArgs);

            case SALES_RECORD:
                return updateSalesRecord(uri, values, selection, selectionArgs);
            case SALES_RECORD_ID:
                selection = SalesRecordEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateSalesRecord(uri, values, selection, selectionArgs);

            case EMPLOYEES:
                return updateEmployees(uri, values, selection, selectionArgs);
            case EMPLOYEES_ID:
                selection = EmployeesEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateEmployees(uri, values, selection, selectionArgs);

            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    // Update patient
    private int updatePatient(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(PatientEntry.COLUMN_FIRST_NAME)) {
            String firstName = values.getAsString(PatientEntry.COLUMN_FIRST_NAME);
            if (firstName == null) {
                throw new IllegalArgumentException("Patient requires a first name");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_LAST_NAME)) {
            String lastName = values.getAsString(PatientEntry.COLUMN_LAST_NAME);
            if (lastName == null) {
                throw new IllegalArgumentException("Patient requires a last name");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_PHONE_NUMBER)) {
            Integer phoneNumber = values.getAsInteger(PatientEntry.COLUMN_PHONE_NUMBER);
            if (phoneNumber == null) {
                throw new IllegalArgumentException("Patient requires valid phone number");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_BIRTH_DATE)) {
            String birthDate = values.getAsString(PatientEntry.COLUMN_BIRTH_DATE);
            if (birthDate == null) {
                throw new IllegalArgumentException("Patient requires a birth date");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_LOCATION)) {
            String location = values.getAsString(PatientEntry.COLUMN_LOCATION);
            if (location == null) {
                throw new IllegalArgumentException("Patient requires a location");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_WEIGHT)) {
            Integer weight = values.getAsInteger(PatientEntry.COLUMN_WEIGHT);
            if (weight != null && weight < 0) {
                throw new IllegalArgumentException("Patient requires valid weight");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_HEIGHT)) {
            Integer height = values.getAsInteger(PatientEntry.COLUMN_HEIGHT);
            if (height != null && height < 0) {
                throw new IllegalArgumentException("Patient requires valid height");
            }
        }

        if (values.containsKey(PatientEntry.COLUMN_GENDER)) {
            Integer gender = values.getAsInteger(PatientEntry.COLUMN_GENDER);
            if (gender == null || !PatientEntry.isValidGender(gender)) {
                throw new IllegalArgumentException("Patient requires valid gender");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // TODO update the data
    // Update patient data to analysis
    private int updatePatientDataToAnalysis(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to analysis  requires a transfer date");
            }
        }

        // Analysis name
        if (values.containsKey(PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME)) {
            Integer analysisName = values.getAsInteger(PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME);
            if (analysisName == null) {
                throw new IllegalArgumentException("Patient data to analysis  requires a analysis name");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToAnalysisEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToAnalysisEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to analysis  requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToAnalysisEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient data to clinics
    private int updatePatientDataToClinics(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to clinics  requires a transfer date");
            }
        }

        // Clinic name
        if (values.containsKey(PatientDataToClinicsEntry.COLUMN_CLINIC_NAME)) {
            Integer clinicName = values.getAsInteger(PatientDataToClinicsEntry.COLUMN_CLINIC_NAME);
            if (clinicName == null) {
                throw new IllegalArgumentException("Patient data to clinics  requires a clinic name");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToClinicsEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToClinicsEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to clinics  requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToClinicsEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient records
    private int updatePatientRecords(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Bill to name
        if (values.containsKey(PatientRecordsEntry.COLUMN_BILL_TO_NAME)) {
            String billToName = values.getAsString(PatientRecordsEntry.COLUMN_BILL_TO_NAME);
            if (billToName == null) {
                throw new IllegalArgumentException("Patient records requires a bill to name");
            }
        }

        // Date of birth
        if (values.containsKey(PatientRecordsEntry.COLUMN_DATE_OF_BIRTH)) {
            String dateOfBirth = values.getAsString(PatientRecordsEntry.COLUMN_DATE_OF_BIRTH);
            if (dateOfBirth == null) {
                throw new IllegalArgumentException("Patient records requires a date of birth");
            }
        }

        // Medical record id
        if (values.containsKey(PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID)) {
            Integer medicalRecordId = values.getAsInteger(PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID);
            if (medicalRecordId == null) {
                throw new IllegalArgumentException("Patient records requires a medical record id");
            }
        }

        // Next appointment date
        if (values.containsKey(PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE)) {
            String nextAppointmentDate = values.getAsString(PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE);
            if (nextAppointmentDate == null) {
                throw new IllegalArgumentException("Patient records requires a next appointment date");
            }
        }

        // Next treatment plan review date
        if (values.containsKey(PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE)) {
            String nextTreatmentPlanReviewDate = values.getAsString(PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE);
            if (nextTreatmentPlanReviewDate == null) {
                throw new IllegalArgumentException("Patient records requires a next treatment plan review date");
            }
        }

        // Physician signature
        if (values.containsKey(PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE)) {
            String physicianSignature = values.getAsString(PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE);
            if (physicianSignature == null) {
                throw new IllegalArgumentException("Patient records requires a physician signature");
            }
        }

        // Date signed
        if (values.containsKey(PatientRecordsEntry.COLUMN_DATE_SIGNED)) {
            String dateSigned = values.getAsString(PatientRecordsEntry.COLUMN_DATE_SIGNED);
            if (dateSigned == null) {
                throw new IllegalArgumentException("Patient records requires a date signed");
            }
        }

        // X-ray image
        if (values.containsKey(PatientRecordsEntry.COLUMN_X_RAY_IMAGE)) {
            String xRayImage = values.getAsString(PatientRecordsEntry.COLUMN_X_RAY_IMAGE);
            if (xRayImage == null) {
                throw new IllegalArgumentException("Patient records requires a x-ray image");
            }
        }

        // Patient id
        if (values.containsKey(PatientRecordsEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientRecordsEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient records requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientRecordsEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient progress
    private int updatePatientProgress(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Progress notes
        if (values.containsKey(PatientProgressEntry.COLUMN_PROGRESS_NOTES)) {
            String progressNotes = values.getAsString(PatientProgressEntry.COLUMN_PROGRESS_NOTES);
            if (progressNotes == null) {
                throw new IllegalArgumentException("Patient progress requires a progress notes");
            }
        }
        //add column date
        if (values.containsKey(PatientProgressEntry.COLUMN_DATE)) {
            String date = values.getAsString(PatientProgressEntry.COLUMN_DATE);
            if (date == null) {
                throw new IllegalArgumentException("Patient progress requires a date");
            }
        }
        // Patient id
        if (values.containsKey(PatientProgressEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientProgressEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient progress requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientProgressEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update invoices
    private int updateInvoices(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Date of svc
        if (values.containsKey(InvoicesEntry.COLUMN_DATE_OF_SVC)) {
            String dateOfSvc = values.getAsString(InvoicesEntry.COLUMN_DATE_OF_SVC);
            if (dateOfSvc == null) {
                throw new IllegalArgumentException("Invoices requires a date of svc");
            }
        }

        // Invoice date
        if (values.containsKey(InvoicesEntry.COLUMN_INVOICE_DATE)) {
            String invoiceDate = values.getAsString(InvoicesEntry.COLUMN_INVOICE_DATE);
            if (invoiceDate == null) {
                throw new IllegalArgumentException("Invoices requires a invoice date");
            }
        }

        // Date due
        if (values.containsKey(InvoicesEntry.COLUMN_DATE_DUE)) {
            String dateDue = values.getAsString(InvoicesEntry.COLUMN_DATE_DUE);
            if (dateDue == null) {
                throw new IllegalArgumentException("Invoices requires a date due");
            }
        }

        // Bill to name
        if (values.containsKey(InvoicesEntry.COLUMN_BILL_TO_NAME)) {
            String billToName = values.getAsString(InvoicesEntry.COLUMN_BILL_TO_NAME);
            if (billToName == null) {
                throw new IllegalArgumentException("Invoices requires a bill to name");
            }
        }

        // Bill to address
        if (values.containsKey(InvoicesEntry.COLUMN_BILL_TO_ADDRESS)) {
            String billToAddress = values.getAsString(InvoicesEntry.COLUMN_BILL_TO_ADDRESS);
            if (billToAddress == null) {
                throw new IllegalArgumentException("Invoices requires a bill to address");
            }
        }

        // Bill to phone
        if (values.containsKey(InvoicesEntry.COLUMN_BILL_TO_PHONE)) {
            Integer billToPhone = values.getAsInteger(InvoicesEntry.COLUMN_BILL_TO_PHONE);
            if (billToPhone == null) {
                throw new IllegalArgumentException("Invoices requires a bill to phone");
            }
        }

        // Bill to fax
        if (values.containsKey(InvoicesEntry.COLUMN_BILL_TO_FAX)) {
            Integer billToFax = values.getAsInteger(InvoicesEntry.COLUMN_BILL_TO_FAX);
            if (billToFax == null) {
                throw new IllegalArgumentException("Invoices requires a bill to fax");
            }
        }

        // Bill to email
        if (values.containsKey(InvoicesEntry.COLUMN_BILL_TO_EMAIL)) {
            String billToEmail = values.getAsString(InvoicesEntry.COLUMN_BILL_TO_EMAIL);
            if (billToEmail == null) {
                throw new IllegalArgumentException("Invoices requires a bill to email");
            }
        }

        // Svc id
        if (values.containsKey(InvoicesEntry.COLUMN_SVC_ID)) {
            Integer svcId = values.getAsInteger(InvoicesEntry.COLUMN_SVC_ID);
            if (svcId == null) {
                throw new IllegalArgumentException("Invoices requires a svc id");
            }
        }

        // Medical services
        if (values.containsKey(InvoicesEntry.COLUMN_MEDICAL_SERVICES)) {
            String medicalServices = values.getAsString(InvoicesEntry.COLUMN_MEDICAL_SERVICES);
            if (medicalServices == null) {
                throw new IllegalArgumentException("Invoices requires a medical services");
            }
        }

        // Medication
        if (values.containsKey(InvoicesEntry.COLUMN_MEDICATION)) {
            String medication = values.getAsString(InvoicesEntry.COLUMN_MEDICATION);
            if (medication == null) {
                throw new IllegalArgumentException("Invoices requires a medication");
            }
        }

        // Cost
        if (values.containsKey(InvoicesEntry.COLUMN_COST)) {
            Integer cost = values.getAsInteger(InvoicesEntry.COLUMN_COST);
            if (cost == null) {
                throw new IllegalArgumentException("Invoices requires a cost");
            }
        }

        // Subtotal
        if (values.containsKey(InvoicesEntry.COLUMN_SUBTOTAL)) {
            Integer subtotal = values.getAsInteger(InvoicesEntry.COLUMN_SUBTOTAL);
            if (subtotal == null) {
                throw new IllegalArgumentException("Invoices requires a subtotal");
            }
        }

        // Tax rate
        if (values.containsKey(InvoicesEntry.COLUMN_TAX_RATE)) {
            Integer taxRate = values.getAsInteger(InvoicesEntry.COLUMN_TAX_RATE);
            if (taxRate == null) {
                throw new IllegalArgumentException("Invoices requires a tax rate");
            }
        }

        // Total tax
        if (values.containsKey(InvoicesEntry.COLUMN_TOTAL_TAX)) {
            Integer totalTax = values.getAsInteger(InvoicesEntry.COLUMN_TOTAL_TAX);
            if (totalTax == null) {
                throw new IllegalArgumentException("Invoices requires a total tax");
            }
        }

        // Other
        if (values.containsKey(InvoicesEntry.COLUMN_OTHER)) {
            Integer other = values.getAsInteger(InvoicesEntry.COLUMN_OTHER);
            if (other == null) {
                throw new IllegalArgumentException("Invoices requires a other");
            }
        }

        // Total
        if (values.containsKey(InvoicesEntry.COLUMN_TOTAL)) {
            Integer total = values.getAsInteger(InvoicesEntry.COLUMN_TOTAL);
            if (total == null) {
                throw new IllegalArgumentException("Invoices requires a total");
            }
        }

        // Questions name
        if (values.containsKey(InvoicesEntry.COLUMN_QUESTIONS_NAME)) {
            String questionsName = values.getAsString(InvoicesEntry.COLUMN_QUESTIONS_NAME);
            if (questionsName == null) {
                throw new IllegalArgumentException("Invoices requires a questions name");
            }
        }

        // Questions email
        if (values.containsKey(InvoicesEntry.COLUMN_QUESTIONS_EMAIL)) {
            String questionsEmail = values.getAsString(InvoicesEntry.COLUMN_QUESTIONS_EMAIL);
            if (questionsEmail == null) {
                throw new IllegalArgumentException("Invoices requires a questions email");
            }
        }

        // Questions phone
        if (values.containsKey(InvoicesEntry.COLUMN_QUESTIONS_PHONE)) {
            Integer questionsPhone = values.getAsInteger(InvoicesEntry.COLUMN_QUESTIONS_PHONE);
            if (questionsPhone == null) {
                throw new IllegalArgumentException("Invoices requires a questions phone");
            }
        }
        // Questions web
        if (values.containsKey(InvoicesEntry.COLUMN_QUESTIONS_WEB)) {
            String questionsWeb = values.getAsString(InvoicesEntry.COLUMN_QUESTIONS_WEB);
            if (questionsWeb == null) {
                throw new IllegalArgumentException("Invoices requires a questions web");
            }
        }

        // Procedure
        if (values.containsKey(InvoicesEntry.COLUMN_PROCEDURE)) {
            String procedure = values.getAsString(InvoicesEntry.COLUMN_PROCEDURE);
            if (procedure == null) {
                throw new IllegalArgumentException("Invoices requires a procedure");
            }
        }

        // Patient id
        if (values.containsKey(InvoicesEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(InvoicesEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Invoices requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(InvoicesEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update health record
    private int updateHealthRecord(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Date of the last update
        if (values.containsKey(HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE)) {
            String dateOfTheLastUpdate = values.getAsString(HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE);
            if (dateOfTheLastUpdate == null) {
                throw new IllegalArgumentException("Health record requires a date of the last update");
            }
        }

        // Current physician name
        if (values.containsKey(HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME)) {
            String currentPhysicianName = values.getAsString(HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME);
            if (currentPhysicianName == null) {
                throw new IllegalArgumentException("Health record requires a current physician name");
            }
        }

        // Doctor's phone
        if (values.containsKey(HealthRecordEntry.COLUMN_DOCTORS_PHONE)) {
            Integer doctorsPhone = values.getAsInteger(HealthRecordEntry.COLUMN_DOCTORS_PHONE);
            if (doctorsPhone == null) {
                throw new IllegalArgumentException("Health record requires a doctor's phone");
            }
        }

        // current pharmacy name
        if (values.containsKey(HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME)) {
            String currentPharmacyName = values.getAsString(HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME);
            if (currentPharmacyName == null) {
                throw new IllegalArgumentException("Health record requires a current pharmacy name");
            }
        }

        // Pharmacy phone
        if (values.containsKey(HealthRecordEntry.COLUMN_PHARMACY_PHONE)) {
            Integer pharmacyPhone = values.getAsInteger(HealthRecordEntry.COLUMN_PHARMACY_PHONE);
            if (pharmacyPhone == null) {
                throw new IllegalArgumentException("Health record requires a pharmacy phone");
            }
        }

        // Patient id
        if (values.containsKey(HealthRecordEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(HealthRecordEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Health record requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(HealthRecordEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update current and past medications
    private int updateCurrentAndPastMedications(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Medicament name
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME)) {
            String medicamentName = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME);
            if (medicamentName == null) {
                throw new IllegalArgumentException("Current and past medications requires a medicament name");
            }
        }

        // Physician
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN)) {
            String physician = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN);
            if (physician == null) {
                throw new IllegalArgumentException("Current and past medications requires a physician");
            }
        }

        // dosage
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_DOSAGE)) {
            String dosage = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_DOSAGE);
            if (dosage == null) {
                throw new IllegalArgumentException("Current and past medications requires a dosage");
            }
        }

        // Freq
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_FREQ)) {
            Integer freq = values.getAsInteger(CurrentAndPastMedicationsEntry.COLUMN_FREQ);
            if (freq == null) {
                throw new IllegalArgumentException("Current and past medications requires a freq");
            }
        }

        // Purpose
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_PURPOSE)) {
            String purpose = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_PURPOSE);
            if (purpose == null) {
                throw new IllegalArgumentException("Current and past medications requires a purpose");
            }
        }

        // Start date
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_START_DATE)) {
            String startDate = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_START_DATE);
            if (startDate == null) {
                throw new IllegalArgumentException("Current and past medications requires a start date");
            }
        }

        // End date
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_END_DATE)) {
            String endDate = values.getAsString(CurrentAndPastMedicationsEntry.COLUMN_END_DATE);
            if (endDate == null) {
                throw new IllegalArgumentException("Current and past medications requires a end date");
            }
        }

        // Patient id
        if (values.containsKey(CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Current and past medications requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(CurrentAndPastMedicationsEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update major illnesses
    private int updateMajorIllnesses(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Illness
        if (values.containsKey(MajorIllnessesEntry.COLUMN_ILLNESS)) {
            String illness = values.getAsString(MajorIllnessesEntry.COLUMN_ILLNESS);
            if (illness == null) {
                throw new IllegalArgumentException("Major illnesses requires a illness");
            }
        }

        // Start date
        if (values.containsKey(MajorIllnessesEntry.COLUMN_START_DATE)) {
            String startDate = values.getAsString(MajorIllnessesEntry.COLUMN_START_DATE);
            if (startDate == null) {
                throw new IllegalArgumentException("Major illnesses requires a start date");
            }
        }

        // End date
        if (values.containsKey(MajorIllnessesEntry.COLUMN_END_DATE)) {
            String endDate = values.getAsString(MajorIllnessesEntry.COLUMN_END_DATE);
            if (endDate == null) {
                throw new IllegalArgumentException("Major illnesses requires a end date");
            }
        }

        // Physician
        if (values.containsKey(MajorIllnessesEntry.COLUMN_PHYSICIAN)) {
            String physician = values.getAsString(MajorIllnessesEntry.COLUMN_PHYSICIAN);
            if (physician == null) {
                throw new IllegalArgumentException("Major illnesses requires a physician");
            }
        }

        // Treatment notes
        if (values.containsKey(MajorIllnessesEntry.COLUMN_TREATMENT_NOTES)) {
            String treatmentNotes = values.getAsString(MajorIllnessesEntry.COLUMN_TREATMENT_NOTES);
            if (treatmentNotes == null) {
                throw new IllegalArgumentException("Major illnesses requires a treatment notes");
            }
        }

        // Patient id
        if (values.containsKey(MajorIllnessesEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(MajorIllnessesEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Major illnesses requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(MajorIllnessesEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update surgical procedures
    private int updateSurgicalProcedures(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Procedure
        if (values.containsKey(SurgicalProceduresEntry.COLUMN_PROCEDURE)) {
            String procedure = values.getAsString(SurgicalProceduresEntry.COLUMN_PROCEDURE);
            if (procedure == null) {
                throw new IllegalArgumentException("Surgical procedures requires a procedure");
            }
        }

        // Physician
        if (values.containsKey(SurgicalProceduresEntry.COLUMN_PHYSICIAN)) {
            String physician = values.getAsString(SurgicalProceduresEntry.COLUMN_PHYSICIAN);
            if (physician == null) {
                throw new IllegalArgumentException("Surgical procedures requires a physician");
            }
        }

        // Hospital
        if (values.containsKey(SurgicalProceduresEntry.COLUMN_HOSPITAL)) {
            String hospital = values.getAsString(SurgicalProceduresEntry.COLUMN_HOSPITAL);
            if (hospital == null) {
                throw new IllegalArgumentException("Surgical procedures requires a hospital");
            }
        }

        // Date surgical procedures
        if (values.containsKey(SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES)) {
            String dateSurgicalProcedures = values.getAsString(SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES);
            if (dateSurgicalProcedures == null) {
                throw new IllegalArgumentException("Surgical procedures requires a date surgical procedures");
            }
        }

        // Notes
        if (values.containsKey(SurgicalProceduresEntry.COLUMN_NOTES)) {
            String notes = values.getAsString(SurgicalProceduresEntry.COLUMN_NOTES);
            if (notes == null) {
                throw new IllegalArgumentException("Surgical procedures requires a notes");
            }
        }

        // Patient id
        if (values.containsKey(SurgicalProceduresEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(SurgicalProceduresEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Surgical procedures requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(SurgicalProceduresEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient vaccines
    private int updateOtherPatientVaccines(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Name of vaccination
        if (values.containsKey(PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION)) {
            Integer nameOfVaccination = values.getAsInteger(PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION);
            if (nameOfVaccination == null) {
                throw new IllegalArgumentException("Patient vaccines requires a name of vaccination");
            }
        }

        // History of vaccination
        if (values.containsKey(PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION)) {
            String historyOfVaccination = values.getAsString(PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION);
            if (historyOfVaccination == null) {
                throw new IllegalArgumentException("Patient vaccines requires a history of vaccination");
            }
        }

        // Patient id
        if (values.containsKey(PatientVaccinesEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientVaccinesEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient vaccines requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientVaccinesEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update doctor diagnosis
    private int updateDoctorDiagnosis(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Diagnosis
        if (values.containsKey(DoctorDiagnosisEntry.COLUMN_DIAGNOSIS)) {
            String diagnosis = values.getAsString(DoctorDiagnosisEntry.COLUMN_DIAGNOSIS);
            if (diagnosis == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a diagnosis");
            }
        }

        // Additional notes
        if (values.containsKey(DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES)) {
            String additionalNotes = values.getAsString(DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES);
            if (additionalNotes == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a additional notes");
            }
        }

        // performing physician signature
        if (values.containsKey(DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE)) {
            String performingPhysicianSignature = values.getAsString(DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE);
            if (performingPhysicianSignature == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a performing physician signature");
            }
        }

        // Patient data to clinics id
        if (values.containsKey(DoctorDiagnosisEntry.COLUMN_PATIENT_DATA_TO_CLINICS_ID)) {
            Integer patientDataToClinicsId = values.getAsInteger(DoctorDiagnosisEntry.COLUMN_PATIENT_DATA_TO_CLINICS_ID);
            if (patientDataToClinicsId == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a patient data to clinics id");
            }
        }

        // Patient id
        if (values.containsKey(DoctorDiagnosisEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(DoctorDiagnosisEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(DoctorDiagnosisEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient data to pharmacy
    private int updatePatientDataToPharmacy(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToPharmacyEntry.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToPharmacyEntry.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to pharmacy requires a transfer date");
            }
        }

        // Doctor diagnosis id
        if (values.containsKey(PatientDataToPharmacyEntry.COLUMN_DOCTOR_DIAGNOSIS_ID)) {
            Integer doctorDiagnosisId = values.getAsInteger(PatientDataToPharmacyEntry.COLUMN_DOCTOR_DIAGNOSIS_ID);
            if (doctorDiagnosisId == null) {
                throw new IllegalArgumentException("Patient data to pharmacy requires a doctor diagnosis id");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToPharmacyEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToPharmacyEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to pharmacy requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToPharmacyEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient data to radiology
    private int updatePatientDataToRadiology(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to radiology requires a transfer date");
            }
        }

        // Types of radiation
        if (values.containsKey(PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION)) {
            Integer typesOfRadiation = values.getAsInteger(PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION);
            if (typesOfRadiation == null) {
                throw new IllegalArgumentException("Patient data to radiology requires a types of radiation");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToRadiologyEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToRadiologyEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to radiology requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToRadiologyEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update medicine registry
    private int updateMedicineRegistry(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Qr
        if (values.containsKey(MedicineRegistryEntry.COLUMN_QR)) {
            Integer qr = values.getAsInteger(MedicineRegistryEntry.COLUMN_QR);
            if (qr == null) {
                throw new IllegalArgumentException("Medicine registry requires a qr");
            }
        }

        // Medicine name
        if (values.containsKey(MedicineRegistryEntry.COLUMN_MEDICINE_NAME)) {
            String medicineName = values.getAsString(MedicineRegistryEntry.COLUMN_MEDICINE_NAME);
            if (medicineName == null) {
                throw new IllegalArgumentException("Medicine registry requires a medicine name");
            }
        }

        // Quantity
        if (values.containsKey(MedicineRegistryEntry.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(MedicineRegistryEntry.COLUMN_QUANTITY);
            if (quantity == null) {
                throw new IllegalArgumentException("Medicine registry requires a quantity");
            }
        }

        // Price
        if (values.containsKey(MedicineRegistryEntry.COLUMN_PRICE)) {
            Integer price = values.getAsInteger(MedicineRegistryEntry.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Medicine registry requires a price");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(MedicineRegistryEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update sales record
    private int updateSalesRecord(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Medicine registry id
        if (values.containsKey(SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID)) {
            String medicineName = values.getAsString(SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID);
            if (medicineName == null) {
                throw new IllegalArgumentException("Sales record requires a medicine name");
            }
        }

        // Quantity
        if (values.containsKey(SalesRecordEntry.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(SalesRecordEntry.COLUMN_QUANTITY);
            if (quantity == null) {
                throw new IllegalArgumentException("Sales record requires a quantity");
            }
        }

        // Sale date
        if (values.containsKey(SalesRecordEntry.COLUMN_SALE_DATE)) {
            String saleDate = values.getAsString(SalesRecordEntry.COLUMN_SALE_DATE);
            if (saleDate == null) {
                throw new IllegalArgumentException("v requires a sale date");
            }
        }

        // Price
        if (values.containsKey(SalesRecordEntry.COLUMN_PRICE)) {
            Integer price = values.getAsInteger(SalesRecordEntry.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Sales record requires a price");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(SalesRecordEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update employees
    private int updateEmployees(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // First name
        if (values.containsKey(EmployeesEntry.COLUMN_FIRST_NAME)) {
            String firstName = values.getAsString(EmployeesEntry.COLUMN_FIRST_NAME);
            if (firstName == null) {
                throw new IllegalArgumentException("Employees requires a first name");
            }
        }

        // Last name
        if (values.containsKey(EmployeesEntry.COLUMN_LAST_NAME)) {
            String lastName = values.getAsString(EmployeesEntry.COLUMN_LAST_NAME);
            if (lastName == null) {
                throw new IllegalArgumentException("Employees requires a last name");
            }
        }

        // Email
        if (values.containsKey(EmployeesEntry.COLUMN_EMAIL)) {
            String email = values.getAsString(EmployeesEntry.COLUMN_EMAIL);
            if (email == null) {
                throw new IllegalArgumentException("Employees requires a email");
            }
        }

        // Phone number
        if (values.containsKey(EmployeesEntry.COLUMN_PHONE_NUMBER)) {
            Integer phoneNumber = values.getAsInteger(EmployeesEntry.COLUMN_PHONE_NUMBER);
            if (phoneNumber == null) {
                throw new IllegalArgumentException("Employees requires a phone number");
            }
        }

        // Hire date
        if (values.containsKey(EmployeesEntry.COLUMN_HIRE_DATE)) {
            String hireDate = values.getAsString(EmployeesEntry.COLUMN_HIRE_DATE);
            if (hireDate == null) {
                throw new IllegalArgumentException("Employees requires a hire date");
            }
        }

        // Salary
        if (values.containsKey(EmployeesEntry.COLUMN_SALARY)) {
            Integer salary = values.getAsInteger(EmployeesEntry.COLUMN_SALARY);
            if (salary == null) {
                throw new IllegalArgumentException("Employees requires a salary");
            }
        }

        // Job title
        if (values.containsKey(EmployeesEntry.COLUMN_JOB_TITLE)) {
            String jobTitle = values.getAsString(EmployeesEntry.COLUMN_JOB_TITLE);
            if (jobTitle == null) {
                throw new IllegalArgumentException("Employees requires a job title");
            }
        }

        // Min salary
        if (values.containsKey(EmployeesEntry.COLUMN_MIN_SALARY)) {
            Integer minSalary = values.getAsInteger(EmployeesEntry.COLUMN_MIN_SALARY);
            if (minSalary < 0) {
                throw new IllegalArgumentException("Employees requires a min salary");
            }
        }

        // Max salary
        if (values.containsKey(EmployeesEntry.COLUMN_MAX_SALARY)) {
            Integer maxSalary = values.getAsInteger(EmployeesEntry.COLUMN_MAX_SALARY);
            if (maxSalary < 0) {
                throw new IllegalArgumentException("Employees requires a max salary");
            }
        }

        // Department name
        if (values.containsKey(EmployeesEntry.COLUMN_DEPARTMENT_NAME)) {
            Integer departmentName = values.getAsInteger(EmployeesEntry.COLUMN_DEPARTMENT_NAME);
            if (departmentName == null) {
                throw new IllegalArgumentException("Employees requires a department name");
            }
        }

        // Region name
        if (values.containsKey(EmployeesEntry.COLUMN_REGION_NAME)) {
            Integer regionName = values.getAsInteger(EmployeesEntry.COLUMN_REGION_NAME);
            if (regionName == null) {
                throw new IllegalArgumentException("Employees requires a region name");
            }
        }

        // Country name
        if (values.containsKey(EmployeesEntry.COLUMN_COUNTRY_NAME)) {
            Integer countryName = values.getAsInteger(EmployeesEntry.COLUMN_COUNTRY_NAME);
            if (countryName == null) {
                throw new IllegalArgumentException("Employees requires a country name");
            }
        }

        // City
        if (values.containsKey(EmployeesEntry.COLUMN_COUNTRY_NAME)) {
            Integer city = values.getAsInteger(EmployeesEntry.COLUMN_CITY);
            if (city == null) {
                throw new IllegalArgumentException("Employees requires a city");
            }
        }

        // Street address
        values.getAsString(EmployeesEntry.COLUMN_STREET_ADDRESS);

        // Postal code
        if (values.containsKey(EmployeesEntry.COLUMN_POSTAL_CODE)) {
            Integer postalCode = values.getAsInteger(EmployeesEntry.COLUMN_POSTAL_CODE);
            if (postalCode == null) {
                throw new IllegalArgumentException("Employees requires a postal code");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(EmployeesEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
