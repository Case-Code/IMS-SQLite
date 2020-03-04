package com.example.ims.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.ims.data.ImsContract.PatientEntry;

/**
 * Database helper for IMS app. Manages database creation and version management.
 */
public class ImsDbHelper extends SQLiteOpenHelper {

    //  Name of the database file
    private static final String DATABASE_NAME = "shelter.db";

    // Database version. If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;

    // Create a String that contains the SQL statement to create the patient table
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

    private static final String SQL_DROP_PETS_TABLE = "DROP TABLE IF EXISTS " + PatientEntry.TABLE_NAME;

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
    }

    // This is called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
        db.execSQL(SQL_DROP_PETS_TABLE);
        onCreate(db);
    }
}
