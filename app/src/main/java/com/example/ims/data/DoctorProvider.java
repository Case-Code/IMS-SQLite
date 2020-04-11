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

public class DoctorProvider extends ContentProvider {
    public static final String LOG_TAG = DoctorProvider.class.getSimpleName();



    private static final int DOCTOR_DIAGNOSIS = 122;
    private static final int DOCTOR_DIAGNOSIS_ID = 123;
    private ImsDbHelper mImsDbHelper;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // Doctor diagnosis
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_DOCTOR_DIAGNOSIS, DOCTOR_DIAGNOSIS);
        URI_MATCHER.addURI(ImsContract.CONTENT_AUTHORITY, ImsContract.PATH_DOCTOR_DIAGNOSIS + "/#", DOCTOR_DIAGNOSIS_ID);
    }


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

            switch (match){
                case DOCTOR_DIAGNOSIS:
                    cursor = database.query(ImsContract.DoctorDiagnosisEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                case DOCTOR_DIAGNOSIS_ID:
                    selection = ImsContract.DoctorDiagnosisEntry._ID + "=?";
                    selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                    cursor = database.query(ImsContract.DoctorDiagnosisEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                    break;
                default:
                    throw new IllegalArgumentException("Cannot query unknown URI "+DoctorProvider.class + uri);

            }


        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case DOCTOR_DIAGNOSIS:
                return ImsContract.DoctorDiagnosisEntry.CONTENT_LIST_TYPE;
            case DOCTOR_DIAGNOSIS_ID:
                return ImsContract.DoctorDiagnosisEntry.CONTENT_ITEM_TYPE;

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
            case DOCTOR_DIAGNOSIS:
                return insertDoctorDiagnosis(uri, values);
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();
        int rowsDeleted;

        final int match = URI_MATCHER.match(uri);
        switch (match) {

            case DOCTOR_DIAGNOSIS:
                rowsDeleted = database.delete(ImsContract.DoctorDiagnosisEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DOCTOR_DIAGNOSIS_ID:
                selection = ImsContract.DoctorDiagnosisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(ImsContract.DoctorDiagnosisEntry.TABLE_NAME, selection, selectionArgs);
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
            case DOCTOR_DIAGNOSIS:
                return updateDoctorDiagnosis(uri, values, selection, selectionArgs);
            case DOCTOR_DIAGNOSIS_ID:
                selection = ImsContract.DoctorDiagnosisEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateDoctorDiagnosis(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }

    }
    private Uri insertDoctorDiagnosis(Uri uri, ContentValues values) {

        // Diagnosis
        String diagnosis = values.getAsString(ImsContract.DoctorDiagnosisEntry.COLUMN_DIAGNOSIS);
        if (diagnosis == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a diagnosis");
        }

        // Additional notes
        String additionalNotes = values.getAsString(ImsContract.DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES);
        if (additionalNotes == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a additional notes");
        }

        // performing physician signature
        String performingPhysicianSignature = values.getAsString(ImsContract.DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE);
        if (performingPhysicianSignature == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a performing physician signature");
        }

        // Patient id
        Integer patientId = values.getAsInteger(ImsContract.DoctorDiagnosisEntry.COLUMN_PATIENT_ID);
        if (patientId == null) {
            throw new IllegalArgumentException("Doctor diagnosis requires a patient id");
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        long id = database.insert(ImsContract.DoctorDiagnosisEntry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    // Update doctor diagnosis
    private int updateDoctorDiagnosis(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        // Diagnosis
        if (values.containsKey(ImsContract.DoctorDiagnosisEntry.COLUMN_DIAGNOSIS)) {
            String diagnosis = values.getAsString(ImsContract.DoctorDiagnosisEntry.COLUMN_DIAGNOSIS);
            if (diagnosis == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a diagnosis");
            }
        }

        // Additional notes
        if (values.containsKey(ImsContract.DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES)) {
            String additionalNotes = values.getAsString(ImsContract.DoctorDiagnosisEntry.COLUMN_ADDITIONAL_NOTES);
            if (additionalNotes == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a additional notes");
            }
        }

        // performing physician signature
        if (values.containsKey(ImsContract.DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE)) {
            String performingPhysicianSignature = values.getAsString(ImsContract.DoctorDiagnosisEntry.COLUMN_PERFORMING_PHYSICIAN_SIGNATURE);
            if (performingPhysicianSignature == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a performing physician signature");
            }
        }

        // Patient id
        if (values.containsKey(ImsContract.DoctorDiagnosisEntry.COLUMN_PATIENT_ID)) {
            Integer patientId = values.getAsInteger(ImsContract.DoctorDiagnosisEntry.COLUMN_PATIENT_ID);
            if (patientId == null) {
                throw new IllegalArgumentException("Doctor diagnosis requires a patient id");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImsDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(ImsContract.DoctorDiagnosisEntry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
