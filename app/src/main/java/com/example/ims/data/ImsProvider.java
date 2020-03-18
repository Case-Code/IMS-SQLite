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
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT, PATIENT);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_PATIENT + "/#", PATIENT_ID);
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
                cursor = database.query(PatientDataToAnalysis.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_ANALYSIS_ID:
                selection = PatientDataToAnalysis._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToAnalysis.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_CLINICS:
                cursor = database.query(PatientDataToClinics.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_CLINICS_ID:
                selection = PatientDataToClinics._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToClinics.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_RECORDS:
                cursor = database.query(PatientRecords.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_RECORDS_ID:
                selection = PatientRecords._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientRecords.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_PROGRESS:
                cursor = database.query(PatientProgress.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_PROGRESS_ID:
                selection = PatientProgress._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientProgress.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case INVOICES:
                cursor = database.query(Invoices.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case INVOICES_ID:
                selection = Invoices._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(Invoices.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case HEALTH_RECORD:
                cursor = database.query(HealthRecord.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case HEALTH_RECORD_ID:
                selection = HealthRecord._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(HealthRecord.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case CURRENT_AND_PAST_MEDICATIONS:
                cursor = database.query(CurrentAndPastMedications.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                selection = CurrentAndPastMedications._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(CurrentAndPastMedications.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case MAJOR_ILLNESSES:
                cursor = database.query(MajorIllnesses.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MAJOR_ILLNESSES_ID:
                selection = MajorIllnesses._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(MajorIllnesses.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case SURGICAL_PROCEDURES:
                cursor = database.query(SurgicalProcedures.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SURGICAL_PROCEDURES_ID:
                selection = SurgicalProcedures._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(SurgicalProcedures.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_VACCINES:
                cursor = database.query(PatientVaccines.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_VACCINES_ID:
                selection = PatientVaccines._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientVaccines.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case DOCTOR_DIAGNOSIS:
                cursor = database.query(DoctorDiagnosis.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DOCTOR_DIAGNOSIS_ID:
                selection = DoctorDiagnosis._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(DoctorDiagnosis.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_PHARMACY:
                cursor = database.query(PatientDataToPharmacy.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_PHARMACY_ID:
                selection = PatientDataToPharmacy._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToPharmacy.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case PATIENT_DATA_TO_RADIOLOGY:
                cursor = database.query(PatientDataToRadiology.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                selection = PatientDataToRadiology._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PatientDataToRadiology.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case MEDICINE_REGISTRY:
                cursor = database.query(MedicineRegistry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case MEDICINE_REGISTRY_ID:
                selection = MedicineRegistry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(MedicineRegistry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case SALES_RECORD:
                cursor = database.query(SalesRecord.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SALES_RECORD_ID:
                selection = SalesRecord._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(SalesRecord.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case EMPLOYEES:
                cursor = database.query(Employees.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case EMPLOYEES_ID:
                selection = Employees._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(Employees.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
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
                return PatientDataToAnalysis.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_ANALYSIS_ID:
                return PatientDataToAnalysis.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_CLINICS:
                return PatientDataToClinics.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_CLINICS_ID:
                return PatientDataToClinics.CONTENT_ITEM_TYPE;

            case PATIENT_RECORDS:
                return PatientRecords.CONTENT_LIST_TYPE;
            case PATIENT_RECORDS_ID:
                return PatientRecords.CONTENT_ITEM_TYPE;

            case PATIENT_PROGRESS:
                return PatientProgress.CONTENT_LIST_TYPE;
            case PATIENT_PROGRESS_ID:
                return PatientProgress.CONTENT_ITEM_TYPE;

            case INVOICES:
                return Invoices.CONTENT_LIST_TYPE;
            case INVOICES_ID:
                return Invoices.CONTENT_ITEM_TYPE;

            case HEALTH_RECORD:
                return HealthRecord.CONTENT_LIST_TYPE;
            case HEALTH_RECORD_ID:
                return HealthRecord.CONTENT_ITEM_TYPE;

            case CURRENT_AND_PAST_MEDICATIONS:
                return CurrentAndPastMedications.CONTENT_LIST_TYPE;
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                return CurrentAndPastMedications.CONTENT_ITEM_TYPE;

            case MAJOR_ILLNESSES:
                return MajorIllnesses.CONTENT_LIST_TYPE;
            case MAJOR_ILLNESSES_ID:
                return MajorIllnesses.CONTENT_ITEM_TYPE;

            case SURGICAL_PROCEDURES:
                return SurgicalProcedures.CONTENT_LIST_TYPE;
            case SURGICAL_PROCEDURES_ID:
                return SurgicalProcedures.CONTENT_ITEM_TYPE;

            case PATIENT_VACCINES:
                return PatientVaccines.CONTENT_LIST_TYPE;
            case PATIENT_VACCINES_ID:
                return PatientVaccines.CONTENT_ITEM_TYPE;

            case DOCTOR_DIAGNOSIS:
                return DoctorDiagnosis.CONTENT_LIST_TYPE;
            case DOCTOR_DIAGNOSIS_ID:
                return DoctorDiagnosis.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_PHARMACY:
                return PatientDataToPharmacy.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_PHARMACY_ID:
                return PatientDataToPharmacy.CONTENT_ITEM_TYPE;

            case PATIENT_DATA_TO_RADIOLOGY:
                return PatientDataToRadiology.CONTENT_LIST_TYPE;
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                return PatientDataToRadiology.CONTENT_ITEM_TYPE;

            case MEDICINE_REGISTRY:
                return MedicineRegistry.CONTENT_LIST_TYPE;
            case MEDICINE_REGISTRY_ID:
                return MedicineRegistry.CONTENT_ITEM_TYPE;

            case SALES_RECORD:
                return SalesRecord.CONTENT_LIST_TYPE;
            case SALES_RECORD_ID:
                return SalesRecord.CONTENT_ITEM_TYPE;

            case EMPLOYEES:
                return Employees.CONTENT_LIST_TYPE;
            case EMPLOYEES_ID:
                return Employees.CONTENT_ITEM_TYPE;

            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATIENT:
                return insertPatient(uri, values);
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

    // TODO insert the data
    // Insert patient data to analysis
    private Uri insertPatientDataToAnalysis(Uri uri, ContentValues values) {

        // Transfer date
        String transferDate = values.getAsString(PatientDataToAnalysis.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to analysis  requires a transfer date");
        }

        // Analysis name
        Integer analysisName = values.getAsInteger(PatientDataToAnalysis.COLUMN_ANALYSIS_NAME);
        if (analysisName == null) {
            throw new IllegalArgumentException("Patient data to analysis  requires a analysis name");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToAnalysis.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to analysis  requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToAnalysis.TABLE_NAME, null, values);
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
        String transferDate = values.getAsString(PatientDataToClinics.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to clinics  requires a transfer date");
        }

        // Clinic name
        Integer clinicName = values.getAsInteger(PatientDataToClinics.COLUMN_CLINIC_NAME);
        if (clinicName == null) {
            throw new IllegalArgumentException("Patient data to clinics  requires a clinic name");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToClinics.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to clinics  requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToClinics.TABLE_NAME, null, values);
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
        String billToName = values.getAsString(PatientRecords.COLUMN_BILL_TO_NAME);
        if (billToName == null) {
            throw new IllegalArgumentException("Patient records requires a bill to name");
        }

        // Date of birth
        String dateOfBirth = values.getAsString(PatientRecords.COLUMN_DATE_OF_BIRTH);
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Patient records requires a date of birth");
        }

        // Medical record id
        Integer medicalRecordId = values.getAsInteger(PatientRecords.COLUMN_MEDICAL_RECORD_ID);
        if (medicalRecordId == null) {
            throw new IllegalArgumentException("Patient records requires a medical record id");
        }

        // Next appointment date
        String nextAppointmentDate = values.getAsString(PatientRecords.COLUMN_NEXT_APPOINTMENT_DATE);
        if (nextAppointmentDate == null) {
            throw new IllegalArgumentException("Patient records requires a next appointment date");
        }

        // Next treatment plan review date
        String nextTreatmentPlanReviewDate = values.getAsString(PatientRecords.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE);
        if (nextTreatmentPlanReviewDate == null) {
            throw new IllegalArgumentException("Patient records requires a next treatment plan review date");
        }

        // Physician signature
        String physicianSignature = values.getAsString(PatientRecords.COLUMN_PHYSICIAN_SIGNATURE);
        if (physicianSignature == null) {
            throw new IllegalArgumentException("Patient records requires a physician signature");
        }

        // Date signed
        String dateSigned = values.getAsString(PatientRecords.COLUMN_DATE_SIGNED);
        if (dateSigned == null) {
            throw new IllegalArgumentException("Patient records requires a date signed");
        }

        // X-ray image
        String xRayImage = values.getAsString(PatientRecords.COLUMN_X_RAY_IMAGE);
        if (xRayImage == null) {
            throw new IllegalArgumentException("Patient records requires a x-ray image");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientRecords.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient records requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientRecords.TABLE_NAME, null, values);
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
        String progressNotes = values.getAsString(PatientProgress.COLUMN_PROGRESS_NOTES);
        if (progressNotes == null) {
            throw new IllegalArgumentException("Patient progress requires a progress notes");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientProgress.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient progress requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientProgress.TABLE_NAME, null, values);
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
        String dateOfSvc = values.getAsString(Invoices.COLUMN_DATE_OF_SVC);
        if (dateOfSvc == null) {
            throw new IllegalArgumentException("Invoices requires a date of svc");
        }

        // Invoice date
        String invoiceDate = values.getAsString(Invoices.COLUMN_INVOICE_DATE);
        if (invoiceDate == null) {
            throw new IllegalArgumentException("Invoices requires a invoice date");
        }

        // Date due
        String dateDue = values.getAsString(Invoices.COLUMN_DATE_DUE);
        if (dateDue == null) {
            throw new IllegalArgumentException("Invoices requires a date due");
        }

        // Bill to name
        String billToName = values.getAsString(Invoices.COLUMN_BILL_TO_NAME);
        if (billToName == null) {
            throw new IllegalArgumentException("Invoices requires a bill to name");
        }

        // Bill to address
        String billToAddress = values.getAsString(Invoices.COLUMN_BILL_TO_ADDRESS);
        if (billToAddress == null) {
            throw new IllegalArgumentException("Invoices requires a bill to address");
        }

        // Bill to phone
        Integer billToPhone = values.getAsInteger(Invoices.COLUMN_BILL_TO_PHONE);
        if (billToPhone == null) {
            throw new IllegalArgumentException("Invoices requires a bill to phone");
        }

        // Bill to fax
        Integer billToFax = values.getAsInteger(Invoices.COLUMN_BILL_TO_FAX);
        if (billToFax == null) {
            throw new IllegalArgumentException("Invoices requires a bill to fax");
        }

        // Bill to email
        String billToEmail = values.getAsString(Invoices.COLUMN_BILL_TO_EMAIL);
        if (billToEmail == null) {
            throw new IllegalArgumentException("Invoices requires a bill to email");
        }

        // Svc id
        Integer svcId = values.getAsInteger(Invoices.COLUMN_SVC_ID);
        if (svcId == null) {
            throw new IllegalArgumentException("Invoices requires a svc id");
        }

        // Medical services
        String medicalServices = values.getAsString(Invoices.COLUMN_MEDICAL_SERVICES);
        if (medicalServices == null) {
            throw new IllegalArgumentException("Invoices requires a medical services");
        }

        // Medication
        String medication = values.getAsString(Invoices.COLUMN_MEDICATION);
        if (medication == null) {
            throw new IllegalArgumentException("Invoices requires a medication");
        }

        // Cost
        Integer cost = values.getAsInteger(Invoices.COLUMN_COST);
        if (cost == null) {
            throw new IllegalArgumentException("Invoices requires a cost");
        }

        // Subtotal
        Integer subtotal = values.getAsInteger(Invoices.COLUMN_SUBTOTAL);
        if (subtotal == null) {
            throw new IllegalArgumentException("Invoices requires a subtotal");
        }

        // Tax rate
        Integer taxRate = values.getAsInteger(Invoices.COLUMN_TAX_RATE);
        if (taxRate == null) {
            throw new IllegalArgumentException("Invoices requires a tax rate");
        }

        // Total tax
        Integer totalTax = values.getAsInteger(Invoices.COLUMN_TOTAL_TAX);
        if (totalTax == null) {
            throw new IllegalArgumentException("Invoices requires a total tax");
        }

        // Other
        Integer other = values.getAsInteger(Invoices.COLUMN_OTHER);
        if (other == null) {
            throw new IllegalArgumentException("Invoices requires a other");
        }

        // Total
        Integer total = values.getAsInteger(Invoices.COLUMN_TOTAL);
        if (total == null) {
            throw new IllegalArgumentException("Invoices requires a total");
        }

        // Questions name
        String questionsName = values.getAsString(Invoices.COLUMN_QUESTIONS_NAME);
        if (questionsName == null) {
            throw new IllegalArgumentException("Invoices requires a questions name");
        }

        // Questions email
        String questionsEmail = values.getAsString(Invoices.COLUMN_QUESTIONS_EMAIL);
        if (questionsEmail == null) {
            throw new IllegalArgumentException("Invoices requires a questions email");
        }

        // Questions phone
        Integer questionsPhone = values.getAsInteger(Invoices.COLUMN_QUESTIONS_PHONE);
        if (questionsPhone == null) {
            throw new IllegalArgumentException("Invoices requires a questions phone");
        }

        // Questions web
        String questionsWeb = values.getAsString(Invoices.COLUMN_QUESTIONS_WEB);
        if (questionsWeb == null) {
            throw new IllegalArgumentException("Invoices requires a questions web");
        }

        // Procedure
        String procedure = values.getAsString(Invoices.COLUMN_PROCEDURE);
        if (procedure == null) {
            throw new IllegalArgumentException("Invoices requires a procedure");
        }

        // Patient id
        Integer patientId = values.getAsInteger(Invoices.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Invoices requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(Invoices.TABLE_NAME, null, values);
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
        String dateOfTheLastUpdate = values.getAsString(HealthRecord.COLUMN_DATE_OF_THE_LAST_UPDATE);
        if (dateOfTheLastUpdate == null) {
            throw new IllegalArgumentException("Health record requires a date of the last update");
        }

        // Current physician name
        String currentPhysicianName = values.getAsString(HealthRecord.COLUMN_CURRENT_PHYSICIAN_NAME);
        if (currentPhysicianName == null) {
            throw new IllegalArgumentException("Health record requires a current physician name");
        }

        // Doctor's phone
        Integer doctorsPhone = values.getAsInteger(HealthRecord.COLUMN_DOCTORS_PHONE);
        if (doctorsPhone == null) {
            throw new IllegalArgumentException("Health record requires a doctor's phone");
        }

        // current pharmacy name
        String currentPharmacyName = values.getAsString(HealthRecord.COLUMN_CURRENT_PHARMACY_NAME);
        if (currentPharmacyName == null) {
            throw new IllegalArgumentException("Health record requires a current pharmacy name");
        }

        // Pharmacy phone
        Integer pharmacyPhone = values.getAsInteger(HealthRecord.COLUMN_PHARMACY_PHONE);
        if (pharmacyPhone == null) {
            throw new IllegalArgumentException("Health record requires a pharmacy phone");
        }

        // Patient id
        Integer patientId = values.getAsInteger(HealthRecord.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Health record requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(HealthRecord.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert current and past medications
    private Uri insertCurrentAndPastMedications(Uri uri, ContentValues values) {

        // Physician
        String physician = values.getAsString(CurrentAndPastMedications.COLUMN_PHYSICIAN);
        if (physician == null) {
            throw new IllegalArgumentException("Current and past medications requires a physician");
        }

        // dosage
        String dosage = values.getAsString(CurrentAndPastMedications.COLUMN_DOSAGE);
        if (dosage == null) {
            throw new IllegalArgumentException("Current and past medications requires a dosage");
        }

        // Freq
        Integer freq = values.getAsInteger(CurrentAndPastMedications.COLUMN_FREQ);
        if (freq == null) {
            throw new IllegalArgumentException("Current and past medications requires a freq");
        }

        // Purpose
        String purpose = values.getAsString(CurrentAndPastMedications.COLUMN_PURPOSE);
        if (purpose == null) {
            throw new IllegalArgumentException("Current and past medications requires a purpose");
        }

        // Start date
        String startDate = values.getAsString(CurrentAndPastMedications.COLUMN_START_DATE);
        if (startDate == null) {
            throw new IllegalArgumentException("Current and past medications requires a start date");
        }

        // End date
        String endDate = values.getAsString(CurrentAndPastMedications.COLUMN_END_DATE);
        if (endDate == null) {
            throw new IllegalArgumentException("Current and past medications requires a end date");
        }

        // Patient id
        Integer patientId = values.getAsInteger(CurrentAndPastMedications.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Current and past medications requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(CurrentAndPastMedications.TABLE_NAME, null, values);
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
        String illness = values.getAsString(MajorIllnesses.COLUMN_ILLNESS);
        if (illness == null) {
            throw new IllegalArgumentException("Major illnesses requires a illness");
        }

        // Start date
        String startDate = values.getAsString(MajorIllnesses.COLUMN_START_DATE);
        if (startDate == null) {
            throw new IllegalArgumentException("Major illnesses requires a start date");
        }

        // End date
        String endDate = values.getAsString(MajorIllnesses.COLUMN_END_DATE);
        if (endDate == null) {
            throw new IllegalArgumentException("Major illnesses requires a end date");
        }

        // Physician
        String physician = values.getAsString(MajorIllnesses.COLUMN_PHYSICIAN);
        if (physician == null) {
            throw new IllegalArgumentException("Major illnesses requires a physician");
        }

        // Treatment notes
        String treatmentNotes = values.getAsString(MajorIllnesses.COLUMN_TREATMENT_NOTES);
        if (treatmentNotes == null) {
            throw new IllegalArgumentException("Major illnesses requires a treatment notes");
        }

        // Patient id
        Integer patientId = values.getAsInteger(MajorIllnesses.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Major illnesses requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(MajorIllnesses.TABLE_NAME, null, values);
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
        String procedure = values.getAsString(SurgicalProcedures.COLUMN_PROCEDURE);
        if (procedure == null) {
            throw new IllegalArgumentException("Surgical procedures requires a procedure");
        }

        // Physician
        String physician = values.getAsString(SurgicalProcedures.COLUMN_PHYSICIAN);
        if (physician == null) {
            throw new IllegalArgumentException("Surgical procedures requires a physician");
        }

        // Hospital
        String hospital = values.getAsString(SurgicalProcedures.COLUMN_HOSPITAL);
        if (hospital == null) {
            throw new IllegalArgumentException("Surgical procedures requires a hospital");
        }

        // Date surgical procedures
        String dateSurgicalProcedures = values.getAsString(SurgicalProcedures.COLUMN_DATE_SURGICAL_PROCEDURES);
        if (dateSurgicalProcedures == null) {
            throw new IllegalArgumentException("Surgical procedures requires a date surgical procedures");
        }

        // Notes
        String notes = values.getAsString(SurgicalProcedures.COLUMN_NOTES);
        if (notes == null) {
            throw new IllegalArgumentException("Surgical procedures requires a notes");
        }

        // Patient id
        Integer patientId = values.getAsInteger(SurgicalProcedures.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Surgical procedures requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(SurgicalProcedures.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert patient vaccines
    private Uri insertPatientVaccines(Uri uri, ContentValues values) {

        // tetanus
        Integer tetanus = values.getAsInteger(PatientVaccines.COLUMN_TETANUS);
        if (tetanus == null) {
            throw new IllegalArgumentException("Patient vaccines requires a tetanus");
        }

        // Name of vaccination
        Integer nameOfVaccination = values.getAsInteger(PatientVaccines.COLUMN_NAME_OF_VACCINATION);
        if (nameOfVaccination == null) {
            throw new IllegalArgumentException("Patient vaccines requires a name of vaccination");
        }

        // History of vaccination
        String historyOfVaccination = values.getAsString(PatientVaccines.COLUMN_HISTORY_OF_VACCINATION);
        if (historyOfVaccination == null) {
            throw new IllegalArgumentException("Patient vaccines requires a history of vaccination");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientVaccines.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient vaccines requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientVaccines.TABLE_NAME, null, values);
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
        String diagnosis = values.getAsString(DoctorDiagnosis.COLUMN_DIAGNOSIS);
        if (diagnosis == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a diagnosis");
        }

        // Additional notes
        String additionalNotes = values.getAsString(DoctorDiagnosis.COLUMN_ADDITIONAL_NOTES);
        if (additionalNotes == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a additional notes");
        }

        // performing physician signature
        String performingPhysicianSignature = values.getAsString(DoctorDiagnosis.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE);
        if (performingPhysicianSignature == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a performing physician signature");
        }

        // Patient id
        Integer patientId = values.getAsInteger(DoctorDiagnosis.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(DoctorDiagnosis.TABLE_NAME, null, values);
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
        String transferDate = values.getAsString(PatientDataToPharmacy.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to pharmacy requires a transfer date");
        }

        // Doctor diagnosis id
        Integer doctorDiagnosisId = values.getAsInteger(PatientDataToPharmacy.COLUMN_DOCTOR_DIAGNOSIS_ID);
        if (doctorDiagnosisId == null) {
            throw new IllegalArgumentException("Patient data to pharmacy requires a doctor diagnosis id");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToPharmacy.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to pharmacy requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToPharmacy.TABLE_NAME, null, values);
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
        String transferDate = values.getAsString(PatientDataToRadiology.COLUMN_TRANSFER_DATE);
        if (transferDate == null) {
            throw new IllegalArgumentException("Patient data to radiology requires a transfer date");
        }

        // Types of radiation
        Integer typesOfRadiation = values.getAsInteger(PatientDataToRadiology.COLUMN_TYPES_OF_RADIATION);
        if (typesOfRadiation == null) {
            throw new IllegalArgumentException("Patient data to radiology requires a types of radiation");
        }

        // Patient id
        Integer patientId = values.getAsInteger(PatientDataToRadiology.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Patient data to radiology requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(PatientDataToRadiology.TABLE_NAME, null, values);
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
        Integer qr = values.getAsInteger(MedicineRegistry.COLUMN_QR);
        if (qr == null) {
            throw new IllegalArgumentException("Medicine registry requires a qr");
        }

        // Medicine name
        String medicineName = values.getAsString(MedicineRegistry.COLUMN_MEDICINE_NAME);
        if (medicineName == null) {
            throw new IllegalArgumentException("Medicine registry requires a medicine name");
        }

        // Quantity
        Integer quantity = values.getAsInteger(MedicineRegistry.COLUMN_QUANTITY);
        if (quantity == null) {
            throw new IllegalArgumentException("Medicine registry requires a quantity");
        }

        // Price
        Integer price = values.getAsInteger(MedicineRegistry.COLUMN_PRICE);
        if (price == null) {
            throw new IllegalArgumentException("Medicine registry requires a price");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(MedicineRegistry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Insert sales record
    private Uri insertSalesRecord(Uri uri, ContentValues values) {

        // Medicine name
        String medicineName = values.getAsString(SalesRecord.COLUMN_MEDICINE_NAME);
        if (medicineName == null) {
            throw new IllegalArgumentException("Sales record requires a medicine name");
        }

        // Quantity
        Integer quantity = values.getAsInteger(SalesRecord.COLUMN_QUANTITY);
        if (quantity == null) {
            throw new IllegalArgumentException("Sales record requires a quantity");
        }

        // Sale date
        String saleDate = values.getAsString(SalesRecord.COLUMN_MEDICINE_NAME);
        if (saleDate == null) {
            throw new IllegalArgumentException("v requires a sale date");
        }

        // Price
        Integer price = values.getAsInteger(SalesRecord.COLUMN_PRICE);
        if (price == null) {
            throw new IllegalArgumentException("Sales record requires a price");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(SalesRecord.TABLE_NAME, null, values);
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
        String firstName = values.getAsString(Employees.COLUMN_FIRST_NAME);
        if (firstName == null) {
            throw new IllegalArgumentException("Employees requires a first name");
        }

        // Last name
        String lastName = values.getAsString(Employees.COLUMN_LAST_NAME);
        if (lastName == null) {
            throw new IllegalArgumentException("Employees requires a last name");
        }

        // Email
        String email = values.getAsString(Employees.COLUMN_EMAIL);
        if (email == null) {
            throw new IllegalArgumentException("Employees requires a email");
        }

        // Phone number
        Integer phoneNumber = values.getAsInteger(Employees.COLUMN_PHONE_NUMBER);
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Employees requires a phone number");
        }

        // Hire date
        String hireDate = values.getAsString(Employees.COLUMN_HIRE_DATE);
        if (hireDate == null) {
            throw new IllegalArgumentException("Employees requires a hire date");
        }

        // Salary
        Integer salary = values.getAsInteger(Employees.COLUMN_SALARY);
        if (salary == null) {
            throw new IllegalArgumentException("Employees requires a salary");
        }

        // Job title
        String jobTitle = values.getAsString(Employees.COLUMN_JOB_TITLE);
        if (jobTitle == null) {
            throw new IllegalArgumentException("Employees requires a job title");
        }

        // Min salary
        Integer minSalary = values.getAsInteger(Employees.COLUMN_MIN_SALARY);
        if (minSalary < 0) {
            throw new IllegalArgumentException("Employees requires a min salary");
        }

        // Max salary
        Integer maxSalary = values.getAsInteger(Employees.COLUMN_MAX_SALARY);
        if (maxSalary < 0) {
            throw new IllegalArgumentException("Employees requires a max salary");
        }

        // Department name
        Integer departmentName = values.getAsInteger(Employees.COLUMN_DEPARTMENT_NAME);
        if (departmentName == null) {
            throw new IllegalArgumentException("Employees requires a department name");
        }

        // Region name
        Integer regionName = values.getAsInteger(Employees.COLUMN_REGION_NAME);
        if (regionName == null) {
            throw new IllegalArgumentException("Employees requires a region name");
        }

        // Country name
        Integer countryName = values.getAsInteger(Employees.COLUMN_COUNTRY_NAME);
        if (countryName == null) {
            throw new IllegalArgumentException("Employees requires a country name");
        }

        // City
        Integer city = values.getAsInteger(Employees.COLUMN_CITY);
        if (city == null) {
            throw new IllegalArgumentException("Employees requires a city");
        }

        // Street address
        values.getAsString(Employees.COLUMN_STREET_ADDRESS);

        // Postal code
        Integer postalCode = values.getAsInteger(Employees.COLUMN_POSTAL_CODE);
        if (postalCode == null) {
            throw new IllegalArgumentException("Employees requires a postal code");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(Employees.TABLE_NAME, null, values);
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
                rowsDeleted = database.delete(PatientDataToAnalysis.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_ANALYSIS_ID:
                selection = PatientDataToAnalysis._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToAnalysis.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_CLINICS:
                rowsDeleted = database.delete(PatientDataToClinics.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_CLINICS_ID:
                selection = PatientDataToClinics._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToClinics.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_RECORDS:
                rowsDeleted = database.delete(PatientRecords.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_RECORDS_ID:
                selection = PatientRecords._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientRecords.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_PROGRESS:
                rowsDeleted = database.delete(PatientProgress.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_PROGRESS_ID:
                selection = PatientProgress._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientProgress.TABLE_NAME, selection, selectionArgs);
                break;

            case INVOICES:
                rowsDeleted = database.delete(Invoices.TABLE_NAME, selection, selectionArgs);
                break;
            case INVOICES_ID:
                selection = Invoices._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(Invoices.TABLE_NAME, selection, selectionArgs);
                break;

            case HEALTH_RECORD:
                rowsDeleted = database.delete(HealthRecord.TABLE_NAME, selection, selectionArgs);
                break;
            case HEALTH_RECORD_ID:
                selection = HealthRecord._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(HealthRecord.TABLE_NAME, selection, selectionArgs);
                break;

            case CURRENT_AND_PAST_MEDICATIONS:
                rowsDeleted = database.delete(CurrentAndPastMedications.TABLE_NAME, selection, selectionArgs);
                break;
            case CURRENT_AND_PAST_MEDICATIONS_ID:
                selection = CurrentAndPastMedications._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(CurrentAndPastMedications.TABLE_NAME, selection, selectionArgs);
                break;

            case MAJOR_ILLNESSES:
                rowsDeleted = database.delete(MajorIllnesses.TABLE_NAME, selection, selectionArgs);
                break;
            case MAJOR_ILLNESSES_ID:
                selection = MajorIllnesses._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(MajorIllnesses.TABLE_NAME, selection, selectionArgs);
                break;

            case SURGICAL_PROCEDURES:
                rowsDeleted = database.delete(SurgicalProcedures.TABLE_NAME, selection, selectionArgs);
                break;
            case SURGICAL_PROCEDURES_ID:
                selection = SurgicalProcedures._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SurgicalProcedures.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_VACCINES:
                rowsDeleted = database.delete(PatientVaccines.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_VACCINES_ID:
                selection = PatientVaccines._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientVaccines.TABLE_NAME, selection, selectionArgs);
                break;

            case DOCTOR_DIAGNOSIS:
                rowsDeleted = database.delete(DoctorDiagnosis.TABLE_NAME, selection, selectionArgs);
                break;
            case DOCTOR_DIAGNOSIS_ID:
                selection = DoctorDiagnosis._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(DoctorDiagnosis.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_PHARMACY:
                rowsDeleted = database.delete(PatientDataToPharmacy.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_PHARMACY_ID:
                selection = PatientDataToPharmacy._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToPharmacy.TABLE_NAME, selection, selectionArgs);
                break;

            case PATIENT_DATA_TO_RADIOLOGY:
                rowsDeleted = database.delete(PatientDataToRadiology.TABLE_NAME, selection, selectionArgs);
                break;
            case PATIENT_DATA_TO_RADIOLOGY_ID:
                selection = PatientDataToRadiology._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(PatientDataToRadiology.TABLE_NAME, selection, selectionArgs);
                break;

            case MEDICINE_REGISTRY:
                rowsDeleted = database.delete(MedicineRegistry.TABLE_NAME, selection, selectionArgs);
                break;
            case MEDICINE_REGISTRY_ID:
                selection = MedicineRegistry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(MedicineRegistry.TABLE_NAME, selection, selectionArgs);
                break;

            case SALES_RECORD:
                rowsDeleted = database.delete(SalesRecord.TABLE_NAME, selection, selectionArgs);
                break;
            case SALES_RECORD_ID:
                selection = SalesRecord._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(SalesRecord.TABLE_NAME, selection, selectionArgs);
                break;

            case EMPLOYEES:
                rowsDeleted = database.delete(Employees.TABLE_NAME, selection, selectionArgs);
                break;
            case EMPLOYEES_ID:
                selection = Employees._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(Employees.TABLE_NAME, selection, selectionArgs);
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
        if (values.containsKey(PatientDataToAnalysis.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToAnalysis.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to analysis  requires a transfer date");
            }
        }

        // Analysis name
        if (values.containsKey(PatientDataToAnalysis.COLUMN_ANALYSIS_NAME)) {
            Integer analysisName = values.getAsInteger(PatientDataToAnalysis.COLUMN_ANALYSIS_NAME);
            if (analysisName == null) {
                throw new IllegalArgumentException("Patient data to analysis  requires a analysis name");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToAnalysis.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToAnalysis.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to analysis  requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToAnalysis.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient data to clinics
    private int updatePatientDataToClinics(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToClinics.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToClinics.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to clinics  requires a transfer date");
            }
        }

        // Clinic name
        if (values.containsKey(PatientDataToClinics.COLUMN_CLINIC_NAME)) {
            Integer clinicName = values.getAsInteger(PatientDataToClinics.COLUMN_CLINIC_NAME);
            if (clinicName == null) {
                throw new IllegalArgumentException("Patient data to clinics  requires a clinic name");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToClinics.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToClinics.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to clinics  requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToClinics.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient records
    private int updatePatientRecords(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Bill to name
        if (values.containsKey(PatientRecords.COLUMN_BILL_TO_NAME)) {
            String billToName = values.getAsString(PatientRecords.COLUMN_BILL_TO_NAME);
            if (billToName == null) {
                throw new IllegalArgumentException("Patient records requires a bill to name");
            }
        }

        // Date of birth
        if (values.containsKey(PatientRecords.COLUMN_DATE_OF_BIRTH)) {
            String dateOfBirth = values.getAsString(PatientRecords.COLUMN_DATE_OF_BIRTH);
            if (dateOfBirth == null) {
                throw new IllegalArgumentException("Patient records requires a date of birth");
            }
        }

        // Medical record id
        if (values.containsKey(PatientRecords.COLUMN_MEDICAL_RECORD_ID)) {
            Integer medicalRecordId = values.getAsInteger(PatientRecords.COLUMN_MEDICAL_RECORD_ID);
            if (medicalRecordId == null) {
                throw new IllegalArgumentException("Patient records requires a medical record id");
            }
        }

        // Next appointment date
        if (values.containsKey(PatientRecords.COLUMN_NEXT_APPOINTMENT_DATE)) {
            String nextAppointmentDate = values.getAsString(PatientRecords.COLUMN_NEXT_APPOINTMENT_DATE);
            if (nextAppointmentDate == null) {
                throw new IllegalArgumentException("Patient records requires a next appointment date");
            }
        }

        // Next treatment plan review date
        if (values.containsKey(PatientRecords.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE)) {
            String nextTreatmentPlanReviewDate = values.getAsString(PatientRecords.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE);
            if (nextTreatmentPlanReviewDate == null) {
                throw new IllegalArgumentException("Patient records requires a next treatment plan review date");
            }
        }

        // Physician signature
        if (values.containsKey(PatientRecords.COLUMN_PHYSICIAN_SIGNATURE)) {
            String physicianSignature = values.getAsString(PatientRecords.COLUMN_PHYSICIAN_SIGNATURE);
            if (physicianSignature == null) {
                throw new IllegalArgumentException("Patient records requires a physician signature");
            }
        }

        // Date signed
        if (values.containsKey(PatientRecords.COLUMN_DATE_SIGNED)) {
            String dateSigned = values.getAsString(PatientRecords.COLUMN_DATE_SIGNED);
            if (dateSigned == null) {
                throw new IllegalArgumentException("Patient records requires a date signed");
            }
        }

        // X-ray image
        if (values.containsKey(PatientRecords.COLUMN_X_RAY_IMAGE)) {
            String xRayImage = values.getAsString(PatientRecords.COLUMN_X_RAY_IMAGE);
            if (xRayImage == null) {
                throw new IllegalArgumentException("Patient records requires a x-ray image");
            }
        }

        // Patient id
        if (values.containsKey(PatientRecords.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientRecords.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient records requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientRecords.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient progress
    private int updatePatientProgress(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Progress notes
        if (values.containsKey(PatientProgress.COLUMN_PROGRESS_NOTES)) {
            String progressNotes = values.getAsString(PatientProgress.COLUMN_PROGRESS_NOTES);
            if (progressNotes == null) {
                throw new IllegalArgumentException("Patient progress requires a progress notes");
            }
        }

        // Patient id
        if (values.containsKey(PatientProgress.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientProgress.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient progress requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientProgress.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update invoices
    private int updateInvoices(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Date of svc
        if (values.containsKey(Invoices.COLUMN_DATE_OF_SVC)) {
            String dateOfSvc = values.getAsString(Invoices.COLUMN_DATE_OF_SVC);
            if (dateOfSvc == null) {
                throw new IllegalArgumentException("Invoices requires a date of svc");
            }
        }

        // Invoice date
        if (values.containsKey(Invoices.COLUMN_INVOICE_DATE)) {
            String invoiceDate = values.getAsString(Invoices.COLUMN_INVOICE_DATE);
            if (invoiceDate == null) {
                throw new IllegalArgumentException("Invoices requires a invoice date");
            }
        }

        // Date due
        if (values.containsKey(Invoices.COLUMN_DATE_DUE)) {
            String dateDue = values.getAsString(Invoices.COLUMN_DATE_DUE);
            if (dateDue == null) {
                throw new IllegalArgumentException("Invoices requires a date due");
            }
        }

        // Bill to name
        if (values.containsKey(Invoices.COLUMN_BILL_TO_NAME)) {
            String billToName = values.getAsString(Invoices.COLUMN_BILL_TO_NAME);
            if (billToName == null) {
                throw new IllegalArgumentException("Invoices requires a bill to name");
            }
        }

        // Bill to address
        if (values.containsKey(Invoices.COLUMN_BILL_TO_ADDRESS)) {
            String billToAddress = values.getAsString(Invoices.COLUMN_BILL_TO_ADDRESS);
            if (billToAddress == null) {
                throw new IllegalArgumentException("Invoices requires a bill to address");
            }
        }

        // Bill to phone
        if (values.containsKey(Invoices.COLUMN_BILL_TO_PHONE)) {
            Integer billToPhone = values.getAsInteger(Invoices.COLUMN_BILL_TO_PHONE);
            if (billToPhone == null) {
                throw new IllegalArgumentException("Invoices requires a bill to phone");
            }
        }

        // Bill to fax
        if (values.containsKey(Invoices.COLUMN_BILL_TO_FAX)) {
            Integer billToFax = values.getAsInteger(Invoices.COLUMN_BILL_TO_FAX);
            if (billToFax == null) {
                throw new IllegalArgumentException("Invoices requires a bill to fax");
            }
        }

        // Bill to email
        if (values.containsKey(Invoices.COLUMN_BILL_TO_EMAIL)) {
            String billToEmail = values.getAsString(Invoices.COLUMN_BILL_TO_EMAIL);
            if (billToEmail == null) {
                throw new IllegalArgumentException("Invoices requires a bill to email");
            }
        }

        // Svc id
        if (values.containsKey(Invoices.COLUMN_SVC_ID)) {
            Integer svcId = values.getAsInteger(Invoices.COLUMN_SVC_ID);
            if (svcId == null) {
                throw new IllegalArgumentException("Invoices requires a svc id");
            }
        }

        // Medical services
        if (values.containsKey(Invoices.COLUMN_MEDICAL_SERVICES)) {
            String medicalServices = values.getAsString(Invoices.COLUMN_MEDICAL_SERVICES);
            if (medicalServices == null) {
                throw new IllegalArgumentException("Invoices requires a medical services");
            }
        }

        // Medication
        if (values.containsKey(Invoices.COLUMN_MEDICATION)) {
            String medication = values.getAsString(Invoices.COLUMN_MEDICATION);
            if (medication == null) {
                throw new IllegalArgumentException("Invoices requires a medication");
            }
        }

        // Cost
        if (values.containsKey(Invoices.COLUMN_COST)) {
            Integer cost = values.getAsInteger(Invoices.COLUMN_COST);
            if (cost == null) {
                throw new IllegalArgumentException("Invoices requires a cost");
            }
        }

        // Subtotal
        if (values.containsKey(Invoices.COLUMN_SUBTOTAL)) {
            Integer subtotal = values.getAsInteger(Invoices.COLUMN_SUBTOTAL);
            if (subtotal == null) {
                throw new IllegalArgumentException("Invoices requires a subtotal");
            }
        }

        // Tax rate
        if (values.containsKey(Invoices.COLUMN_TAX_RATE)) {
            Integer taxRate = values.getAsInteger(Invoices.COLUMN_TAX_RATE);
            if (taxRate == null) {
                throw new IllegalArgumentException("Invoices requires a tax rate");
            }
        }

        // Total tax
        if (values.containsKey(Invoices.COLUMN_TOTAL_TAX)) {
            Integer totalTax = values.getAsInteger(Invoices.COLUMN_TOTAL_TAX);
            if (totalTax == null) {
                throw new IllegalArgumentException("Invoices requires a total tax");
            }
        }

        // Other
        if (values.containsKey(Invoices.COLUMN_OTHER)) {
            Integer other = values.getAsInteger(Invoices.COLUMN_OTHER);
            if (other == null) {
                throw new IllegalArgumentException("Invoices requires a other");
            }
        }

        // Total
        if (values.containsKey(Invoices.COLUMN_TOTAL)) {
            Integer total = values.getAsInteger(Invoices.COLUMN_TOTAL);
            if (total == null) {
                throw new IllegalArgumentException("Invoices requires a total");
            }
        }

        // Questions name
        if (values.containsKey(Invoices.COLUMN_QUESTIONS_NAME)) {
            String questionsName = values.getAsString(Invoices.COLUMN_QUESTIONS_NAME);
            if (questionsName == null) {
                throw new IllegalArgumentException("Invoices requires a questions name");
            }
        }

        // Questions email
        if (values.containsKey(Invoices.COLUMN_QUESTIONS_EMAIL)) {
            String questionsEmail = values.getAsString(Invoices.COLUMN_QUESTIONS_EMAIL);
            if (questionsEmail == null) {
                throw new IllegalArgumentException("Invoices requires a questions email");
            }
        }

        // Questions phone
        if (values.containsKey(Invoices.COLUMN_QUESTIONS_PHONE)) {
            Integer questionsPhone = values.getAsInteger(Invoices.COLUMN_QUESTIONS_PHONE);
            if (questionsPhone == null) {
                throw new IllegalArgumentException("Invoices requires a questions phone");
            }
        }
        // Questions web
        if (values.containsKey(Invoices.COLUMN_QUESTIONS_WEB)) {
            String questionsWeb = values.getAsString(Invoices.COLUMN_QUESTIONS_WEB);
            if (questionsWeb == null) {
                throw new IllegalArgumentException("Invoices requires a questions web");
            }
        }

        // Procedure
        if (values.containsKey(Invoices.COLUMN_PROCEDURE)) {
            String procedure = values.getAsString(Invoices.COLUMN_PROCEDURE);
            if (procedure == null) {
                throw new IllegalArgumentException("Invoices requires a procedure");
            }
        }

        // Patient id
        if (values.containsKey(Invoices.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(Invoices.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Invoices requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(Invoices.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update health record
    private int updateHealthRecord(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Date of the last update
        if (values.containsKey(HealthRecord.COLUMN_DATE_OF_THE_LAST_UPDATE)) {
            String dateOfTheLastUpdate = values.getAsString(HealthRecord.COLUMN_DATE_OF_THE_LAST_UPDATE);
            if (dateOfTheLastUpdate == null) {
                throw new IllegalArgumentException("Health record requires a date of the last update");
            }
        }

        // Current physician name
        if (values.containsKey(HealthRecord.COLUMN_CURRENT_PHYSICIAN_NAME)) {
            String currentPhysicianName = values.getAsString(HealthRecord.COLUMN_CURRENT_PHYSICIAN_NAME);
            if (currentPhysicianName == null) {
                throw new IllegalArgumentException("Health record requires a current physician name");
            }
        }

        // Doctor's phone
        if (values.containsKey(HealthRecord.COLUMN_DOCTORS_PHONE)) {
            Integer doctorsPhone = values.getAsInteger(HealthRecord.COLUMN_DOCTORS_PHONE);
            if (doctorsPhone == null) {
                throw new IllegalArgumentException("Health record requires a doctor's phone");
            }
        }

        // current pharmacy name
        if (values.containsKey(HealthRecord.COLUMN_CURRENT_PHARMACY_NAME)) {
            String currentPharmacyName = values.getAsString(HealthRecord.COLUMN_CURRENT_PHARMACY_NAME);
            if (currentPharmacyName == null) {
                throw new IllegalArgumentException("Health record requires a current pharmacy name");
            }
        }

        // Pharmacy phone
        if (values.containsKey(HealthRecord.COLUMN_PHARMACY_PHONE)) {
            Integer pharmacyPhone = values.getAsInteger(HealthRecord.COLUMN_PHARMACY_PHONE);
            if (pharmacyPhone == null) {
                throw new IllegalArgumentException("Health record requires a pharmacy phone");
            }
        }

        // Patient id
        if (values.containsKey(HealthRecord.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(HealthRecord.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Health record requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(HealthRecord.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update current and past medications
    private int updateCurrentAndPastMedications(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Physician
        if (values.containsKey(CurrentAndPastMedications.COLUMN_PHYSICIAN)) {
            String physician = values.getAsString(CurrentAndPastMedications.COLUMN_PHYSICIAN);
            if (physician == null) {
                throw new IllegalArgumentException("Current and past medications requires a physician");
            }
        }

        // dosage
        if (values.containsKey(CurrentAndPastMedications.COLUMN_DOSAGE)) {
            String dosage = values.getAsString(CurrentAndPastMedications.COLUMN_DOSAGE);
            if (dosage == null) {
                throw new IllegalArgumentException("Current and past medications requires a dosage");
            }
        }

        // Freq
        if (values.containsKey(CurrentAndPastMedications.COLUMN_FREQ)) {
            Integer freq = values.getAsInteger(CurrentAndPastMedications.COLUMN_FREQ);
            if (freq == null) {
                throw new IllegalArgumentException("Current and past medications requires a freq");
            }
        }

        // Purpose
        if (values.containsKey(CurrentAndPastMedications.COLUMN_PURPOSE)) {
            String purpose = values.getAsString(CurrentAndPastMedications.COLUMN_PURPOSE);
            if (purpose == null) {
                throw new IllegalArgumentException("Current and past medications requires a purpose");
            }
        }

        // Start date
        if (values.containsKey(CurrentAndPastMedications.COLUMN_START_DATE)) {
            String startDate = values.getAsString(CurrentAndPastMedications.COLUMN_START_DATE);
            if (startDate == null) {
                throw new IllegalArgumentException("Current and past medications requires a start date");
            }
        }

        // End date
        if (values.containsKey(CurrentAndPastMedications.COLUMN_END_DATE)) {
            String endDate = values.getAsString(CurrentAndPastMedications.COLUMN_END_DATE);
            if (endDate == null) {
                throw new IllegalArgumentException("Current and past medications requires a end date");
            }
        }

        // Patient id
        if (values.containsKey(CurrentAndPastMedications.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(CurrentAndPastMedications.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Current and past medications requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(CurrentAndPastMedications.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update major illnesses
    private int updateMajorIllnesses(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Illness
        if (values.containsKey(MajorIllnesses.COLUMN_ILLNESS)) {
            String illness = values.getAsString(MajorIllnesses.COLUMN_ILLNESS);
            if (illness == null) {
                throw new IllegalArgumentException("Major illnesses requires a illness");
            }
        }

        // Start date
        if (values.containsKey(MajorIllnesses.COLUMN_START_DATE)) {
            String startDate = values.getAsString(MajorIllnesses.COLUMN_START_DATE);
            if (startDate == null) {
                throw new IllegalArgumentException("Major illnesses requires a start date");
            }
        }

        // End date
        if (values.containsKey(MajorIllnesses.COLUMN_END_DATE)) {
            String endDate = values.getAsString(MajorIllnesses.COLUMN_END_DATE);
            if (endDate == null) {
                throw new IllegalArgumentException("Major illnesses requires a end date");
            }
        }

        // Physician
        if (values.containsKey(MajorIllnesses.COLUMN_PHYSICIAN)) {
            String physician = values.getAsString(MajorIllnesses.COLUMN_PHYSICIAN);
            if (physician == null) {
                throw new IllegalArgumentException("Major illnesses requires a physician");
            }
        }

        // Treatment notes
        if (values.containsKey(MajorIllnesses.COLUMN_TREATMENT_NOTES)) {
            String treatmentNotes = values.getAsString(MajorIllnesses.COLUMN_TREATMENT_NOTES);
            if (treatmentNotes == null) {
                throw new IllegalArgumentException("Major illnesses requires a treatment notes");
            }
        }

        // Patient id
        if (values.containsKey(MajorIllnesses.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(MajorIllnesses.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Major illnesses requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(MajorIllnesses.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update surgical procedures
    private int updateSurgicalProcedures(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Procedure
        if (values.containsKey(SurgicalProcedures.COLUMN_PROCEDURE)) {
            String procedure = values.getAsString(SurgicalProcedures.COLUMN_PROCEDURE);
            if (procedure == null) {
                throw new IllegalArgumentException("Surgical procedures requires a procedure");
            }
        }

        // Physician
        if (values.containsKey(SurgicalProcedures.COLUMN_PHYSICIAN)) {
            String physician = values.getAsString(SurgicalProcedures.COLUMN_PHYSICIAN);
            if (physician == null) {
                throw new IllegalArgumentException("Surgical procedures requires a physician");
            }
        }

        // Hospital
        if (values.containsKey(SurgicalProcedures.COLUMN_HOSPITAL)) {
            String hospital = values.getAsString(SurgicalProcedures.COLUMN_HOSPITAL);
            if (hospital == null) {
                throw new IllegalArgumentException("Surgical procedures requires a hospital");
            }
        }

        // Date surgical procedures
        if (values.containsKey(SurgicalProcedures.COLUMN_DATE_SURGICAL_PROCEDURES)) {
            String dateSurgicalProcedures = values.getAsString(SurgicalProcedures.COLUMN_DATE_SURGICAL_PROCEDURES);
            if (dateSurgicalProcedures == null) {
                throw new IllegalArgumentException("Surgical procedures requires a date surgical procedures");
            }
        }

        // Notes
        if (values.containsKey(SurgicalProcedures.COLUMN_NOTES)) {
            String notes = values.getAsString(SurgicalProcedures.COLUMN_NOTES);
            if (notes == null) {
                throw new IllegalArgumentException("Surgical procedures requires a notes");
            }
        }

        // Patient id
        if (values.containsKey(SurgicalProcedures.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(SurgicalProcedures.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Surgical procedures requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(SurgicalProcedures.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient vaccines
    private int updatePatientVaccines(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // tetanus
        if (values.containsKey(PatientVaccines.COLUMN_TETANUS)) {
            Integer tetanus = values.getAsInteger(PatientVaccines.COLUMN_TETANUS);
            if (tetanus == null) {
                throw new IllegalArgumentException("Patient vaccines requires a tetanus");
            }
        }

        // Name of vaccination
        if (values.containsKey(PatientVaccines.COLUMN_NAME_OF_VACCINATION)) {
            Integer nameOfVaccination = values.getAsInteger(PatientVaccines.COLUMN_NAME_OF_VACCINATION);
            if (nameOfVaccination == null) {
                throw new IllegalArgumentException("Patient vaccines requires a name of vaccination");
            }
        }

        // History of vaccination
        if (values.containsKey(PatientVaccines.COLUMN_HISTORY_OF_VACCINATION)) {
            String historyOfVaccination = values.getAsString(PatientVaccines.COLUMN_HISTORY_OF_VACCINATION);
            if (historyOfVaccination == null) {
                throw new IllegalArgumentException("Patient vaccines requires a history of vaccination");
            }
        }

        // Patient id
        if (values.containsKey(PatientVaccines.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientVaccines.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient vaccines requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientVaccines.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update doctor diagnosis
    private int updateDoctorDiagnosis(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Diagnosis
        if (values.containsKey(DoctorDiagnosis.COLUMN_DIAGNOSIS)) {
            String diagnosis = values.getAsString(DoctorDiagnosis.COLUMN_DIAGNOSIS);
            if (diagnosis == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a diagnosis");
            }
        }

        // Additional notes
        if (values.containsKey(DoctorDiagnosis.COLUMN_ADDITIONAL_NOTES)) {
            String additionalNotes = values.getAsString(DoctorDiagnosis.COLUMN_ADDITIONAL_NOTES);
            if (additionalNotes == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a additional notes");
            }
        }

        // performing physician signature
        if (values.containsKey(DoctorDiagnosis.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE)) {
            String performingPhysicianSignature = values.getAsString(DoctorDiagnosis.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE);
            if (performingPhysicianSignature == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a performing physician signature");
            }
        }

        // Patient id
        if (values.containsKey(DoctorDiagnosis.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(DoctorDiagnosis.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(DoctorDiagnosis.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient data to pharmacy
    private int updatePatientDataToPharmacy(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToPharmacy.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToPharmacy.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to pharmacy requires a transfer date");
            }
        }

        // Doctor diagnosis id
        if (values.containsKey(PatientDataToPharmacy.COLUMN_DOCTOR_DIAGNOSIS_ID)) {
            Integer doctorDiagnosisId = values.getAsInteger(PatientDataToPharmacy.COLUMN_DOCTOR_DIAGNOSIS_ID);
            if (doctorDiagnosisId == null) {
                throw new IllegalArgumentException("Patient data to pharmacy requires a doctor diagnosis id");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToPharmacy.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToPharmacy.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to pharmacy requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToPharmacy.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update patient data to radiology
    private int updatePatientDataToRadiology(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Transfer date
        if (values.containsKey(PatientDataToRadiology.COLUMN_TRANSFER_DATE)) {
            String transferDate = values.getAsString(PatientDataToRadiology.COLUMN_TRANSFER_DATE);
            if (transferDate == null) {
                throw new IllegalArgumentException("Patient data to radiology requires a transfer date");
            }
        }

        // Types of radiation
        if (values.containsKey(PatientDataToRadiology.COLUMN_TYPES_OF_RADIATION)) {
            Integer typesOfRadiation = values.getAsInteger(PatientDataToRadiology.COLUMN_TYPES_OF_RADIATION);
            if (typesOfRadiation == null) {
                throw new IllegalArgumentException("Patient data to radiology requires a types of radiation");
            }
        }

        // Patient id
        if (values.containsKey(PatientDataToRadiology.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(PatientDataToRadiology.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Patient data to radiology requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(PatientDataToRadiology.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update medicine registry
    private int updateMedicineRegistry(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Qr
        if (values.containsKey(MedicineRegistry.COLUMN_QR)) {
            Integer qr = values.getAsInteger(MedicineRegistry.COLUMN_QR);
            if (qr == null) {
                throw new IllegalArgumentException("Medicine registry requires a qr");
            }
        }

        // Medicine name
        if (values.containsKey(MedicineRegistry.COLUMN_MEDICINE_NAME)) {
            String medicineName = values.getAsString(MedicineRegistry.COLUMN_MEDICINE_NAME);
            if (medicineName == null) {
                throw new IllegalArgumentException("Medicine registry requires a medicine name");
            }
        }

        // Quantity
        if (values.containsKey(MedicineRegistry.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(MedicineRegistry.COLUMN_QUANTITY);
            if (quantity == null) {
                throw new IllegalArgumentException("Medicine registry requires a quantity");
            }
        }

        // Price
        if (values.containsKey(MedicineRegistry.COLUMN_PRICE)) {
            Integer price = values.getAsInteger(MedicineRegistry.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Medicine registry requires a price");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(MedicineRegistry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update sales record
    private int updateSalesRecord(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Medicine name
        if (values.containsKey(SalesRecord.COLUMN_MEDICINE_NAME)) {
            String medicineName = values.getAsString(SalesRecord.COLUMN_MEDICINE_NAME);
            if (medicineName == null) {
                throw new IllegalArgumentException("Sales record requires a medicine name");
            }
        }

        // Quantity
        if (values.containsKey(SalesRecord.COLUMN_QUANTITY)) {
            Integer quantity = values.getAsInteger(SalesRecord.COLUMN_QUANTITY);
            if (quantity == null) {
                throw new IllegalArgumentException("Sales record requires a quantity");
            }
        }

        // Sale date
        if (values.containsKey(SalesRecord.COLUMN_MEDICINE_NAME)) {
            String saleDate = values.getAsString(SalesRecord.COLUMN_MEDICINE_NAME);
            if (saleDate == null) {
                throw new IllegalArgumentException("v requires a sale date");
            }
        }

        // Price
        if (values.containsKey(SalesRecord.COLUMN_PRICE)) {
            Integer price = values.getAsInteger(SalesRecord.COLUMN_PRICE);
            if (price == null) {
                throw new IllegalArgumentException("Sales record requires a price");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(SalesRecord.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    // Update employees
    private int updateEmployees(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // First name
        if (values.containsKey(Employees.COLUMN_FIRST_NAME)) {
            String firstName = values.getAsString(Employees.COLUMN_FIRST_NAME);
            if (firstName == null) {
                throw new IllegalArgumentException("Employees requires a first name");
            }
        }

        // Last name
        if (values.containsKey(Employees.COLUMN_LAST_NAME)) {
            String lastName = values.getAsString(Employees.COLUMN_LAST_NAME);
            if (lastName == null) {
                throw new IllegalArgumentException("Employees requires a last name");
            }
        }

        // Email
        if (values.containsKey(Employees.COLUMN_EMAIL)) {
            String email = values.getAsString(Employees.COLUMN_EMAIL);
            if (email == null) {
                throw new IllegalArgumentException("Employees requires a email");
            }
        }

        // Phone number
        if (values.containsKey(Employees.COLUMN_PHONE_NUMBER)) {
            Integer phoneNumber = values.getAsInteger(Employees.COLUMN_PHONE_NUMBER);
            if (phoneNumber == null) {
                throw new IllegalArgumentException("Employees requires a phone number");
            }
        }

        // Hire date
        if (values.containsKey(Employees.COLUMN_HIRE_DATE)) {
            String hireDate = values.getAsString(Employees.COLUMN_HIRE_DATE);
            if (hireDate == null) {
                throw new IllegalArgumentException("Employees requires a hire date");
            }
        }

        // Salary
        if (values.containsKey(Employees.COLUMN_SALARY)) {
            Integer salary = values.getAsInteger(Employees.COLUMN_SALARY);
            if (salary == null) {
                throw new IllegalArgumentException("Employees requires a salary");
            }
        }

        // Job title
        if (values.containsKey(Employees.COLUMN_JOB_TITLE)) {
            String jobTitle = values.getAsString(Employees.COLUMN_JOB_TITLE);
            if (jobTitle == null) {
                throw new IllegalArgumentException("Employees requires a job title");
            }
        }

        // Min salary
        if (values.containsKey(Employees.COLUMN_MIN_SALARY)) {
            Integer minSalary = values.getAsInteger(Employees.COLUMN_MIN_SALARY);
            if (minSalary < 0) {
                throw new IllegalArgumentException("Employees requires a min salary");
            }
        }

        // Max salary
        if (values.containsKey(Employees.COLUMN_MAX_SALARY)) {
            Integer maxSalary = values.getAsInteger(Employees.COLUMN_MAX_SALARY);
            if (maxSalary < 0) {
                throw new IllegalArgumentException("Employees requires a max salary");
            }
        }

        // Department name
        if (values.containsKey(Employees.COLUMN_DEPARTMENT_NAME)) {
            Integer departmentName = values.getAsInteger(Employees.COLUMN_DEPARTMENT_NAME);
            if (departmentName == null) {
                throw new IllegalArgumentException("Employees requires a department name");
            }
        }

        // Region name
        if (values.containsKey(Employees.COLUMN_REGION_NAME)) {
            Integer regionName = values.getAsInteger(Employees.COLUMN_REGION_NAME);
            if (regionName == null) {
                throw new IllegalArgumentException("Employees requires a region name");
            }
        }

        // Country name
        if (values.containsKey(Employees.COLUMN_COUNTRY_NAME)) {
            Integer countryName = values.getAsInteger(Employees.COLUMN_COUNTRY_NAME);
            if (countryName == null) {
                throw new IllegalArgumentException("Employees requires a country name");
            }
        }

        // City
        if (values.containsKey(Employees.COLUMN_COUNTRY_NAME)) {
            Integer city = values.getAsInteger(Employees.COLUMN_CITY);
            if (city == null) {
                throw new IllegalArgumentException("Employees requires a city");
            }
        }

        // Street address
        values.getAsString(Employees.COLUMN_STREET_ADDRESS);

        // Postal code
        if (values.containsKey(Employees.COLUMN_POSTAL_CODE)) {
            Integer postalCode = values.getAsInteger(Employees.COLUMN_POSTAL_CODE);
            if (postalCode == null) {
                throw new IllegalArgumentException("Employees requires a postal code");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(Employees.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
