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
