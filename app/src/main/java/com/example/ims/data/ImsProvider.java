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

import com.example.ims.data.ImsContract.PatientEntry;

public class ImsProvider extends ContentProvider {

    public static final String LOG_TAG = ImsProvider.class.getSimpleName();
    private static final int PATIENT = 100;
    private static final int PATIENT_ID = 101;
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

}
