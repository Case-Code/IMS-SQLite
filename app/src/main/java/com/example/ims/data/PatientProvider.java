package com.example.ims.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PatientProvider extends ContentProvider {
    private ImsDbHelper mImsDbHelper;


    public static final String LOG_TAG =PatientProvider.class.getSimpleName();

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

    // Invoices0
    private static final int INVOICES = 110;
    private static final int INVOICES_ID = 111;

    // Health record0
    private static final int HEALTH_RECORD = 112;
    private static final int HEALTH_RECORD_ID = 113;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    static {

    }


    @Override
    public boolean onCreate()
    {
        mImsDbHelper = new ImsDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
