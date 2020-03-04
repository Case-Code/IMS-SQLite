package com.example.ims.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.ims.R;

/**
 * API Contract for the IMS app.
 */
public class ImsContract {

    public static final String CONTENT_AUTHORITY = "com.example.ims";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PATIENT = "patient";
    public static final String PATH_PATIENT_DATA_TO_LABORATORIES = "patient_data_to_laboratories";


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ImsContract() {
    }

    // Patient
    public static final class PatientEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT;

        // Patient table
        public static final String TABLE_NAME = "patient";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_BIRTH_DATE = "birth_date";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_HEIGHT = "height";
        public final static String COLUMN_GENDER = "gender";

        // Gender
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        static boolean isValidGender(int gender) {
            return gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE;
        }

    }

    // Patient data toLaboratories
    public static final class PatientDataToLaboratoriesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_DATA_TO_LABORATORIES);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_LABORATORIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_LABORATORIES;

        // Patient table
        public static final String TABLE_NAME = "patient_data_to_laboratories";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TRANSFER_DATE = "transfer_date";
        public static final String COLUMN_TYPE_OF_ANALYSIS = "type_of_analysis";
        public static final String COLUMN_PATIENT_ID = "patient_id";

        // Types of analysis
        public static final String ANALYSIS_COMPLETE_BLOOD_COUNT = String.valueOf(R.string.analysis_complete_blood_count);
        public static final String ANALYSIS_URINE_EXAMINATION = String.valueOf(R.string.analysis_urine_examination);
        public static final String ANALYSIS_STOOL_EXAMINATION = String.valueOf(R.string.analysis_stool_examination);

        static boolean isValidTypesOfAnalysis(String typesOfAnalysis) {
            return typesOfAnalysis == ANALYSIS_COMPLETE_BLOOD_COUNT || typesOfAnalysis == ANALYSIS_URINE_EXAMINATION || typesOfAnalysis == ANALYSIS_STOOL_EXAMINATION;
        }

    }

}
