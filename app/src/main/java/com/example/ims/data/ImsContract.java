package com.example.ims.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * API Contract for the IMS app.
 */
public class ImsContract {

    public static final String CONTENT_AUTHORITY = "com.example.ims";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Path database
    public static final String PATH_PATIENT = "patient";
    public static final String PATH_PATIENT_DATA_TO_ANALYSIS = "patient_data_to_analysis";
    public static final String PATH_PATIENT_DATA_TO_CLINICS = "patient_data_to_clinics";
    public static final String PATH_PATIENT_RECORDS = "patient_records";
    public static final String PATH_PATIENT_PROGRESS = "patient_progress";
    public static final String PATH_INVOICES = "invoices";
    public static final String PATH_HEALTH_RECORD = "health_record";
    public static final String PATH_CURRENT_AND_PAST_MEDICATIONS = "current_and_past_medications";
    public static final String PATH_MAJOR_ILLNESSES = "major_illnesses";
    public static final String PATH_SURGICAL_PROCEDURES = "surgical_procedures";
    public static final String PATH_PATIENT_VACCINES = "patient_vaccines";
    public static final String PATH_DOCTOR_DIAGNOSIS = "doctor_diagnosis";
    public static final String PATH_PATIENT_DATA_TO_PHARMACY = "patient_data_to_pharmacy";
    public static final String PATH_PATIENT_DATA_TO_RADIOLOGY = "patient_data_to_radiology";
    public static final String PATH_MEDICINE_REGISTRY = "medicine_registry";
    public static final String PATH_SALES_RECORD = "sales_record";
    public static final String PATH_EMPLOYEES = "employees";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ImsContract() {
    }

    // Reception
    // Patient
    public static final class PatientEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT;

        // Patient table
        public static final String TABLE_NAME = PATH_PATIENT;
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

    // Patient data to analysis
    public static final class PatientDataToAnalysisEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_DATA_TO_ANALYSIS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_ANALYSIS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_ANALYSIS;

        // Patient data to analysis table
        public static final String TABLE_NAME = PATH_PATIENT_DATA_TO_ANALYSIS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TRANSFER_DATE = "transfer_date";
        public static final String COLUMN_ANALYSIS_NAME = "analysis_name";
        public static final String COLUMN_PATIENT_ID = "patient_id";

        // Types of analysis
        public static final int ANALYSIS_UNKNOWN = 0;
        public static final int ANALYSIS_COMPLETE_BLOOD_COUNT = 1;
        public static final int ANALYSIS_URINE_EXAMINATION = 2;
        public static final int ANALYSIS_STOOL_EXAMINATION = 3;

        static boolean isValidTypesOfAnalysis(int typesOfAnalysis) {
            return typesOfAnalysis == ANALYSIS_UNKNOWN ||
                    typesOfAnalysis == ANALYSIS_COMPLETE_BLOOD_COUNT ||
                    typesOfAnalysis == ANALYSIS_URINE_EXAMINATION ||
                    typesOfAnalysis == ANALYSIS_STOOL_EXAMINATION;
        }
    }

    // Patient data to clinics
    public static final class PatientDataToClinicsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_DATA_TO_CLINICS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_CLINICS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_CLINICS;

        // Patient data to clinics table
        public static final String TABLE_NAME = PATH_PATIENT_DATA_TO_CLINICS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TRANSFER_DATE = "transfer_date";
        public static final String COLUMN_CLINIC_NAME = "clinic_name";
        public static final String COLUMN_PATIENT_ID = "patient_id";

        // The names of the clinics
        public static final int CLINICS_UNKNOWN = 0;
        public static final int CLINICS_ENDEMIC_DISEASES = 1;
        public static final int CLINICS_MEDICAL_AND_MICROBIOLOGICAL_ANALYZES = 2;
        public static final int CLINICS_PSYCHOLOGICAL_DISEASES = 3;
        public static final int CLINICS_PHONETIC_AND_PHONEME = 4;
        public static final int CLINICS_EAR_NOSE_AND_THROAT = 5;
        public static final int CLINICS_COLON_AND_ANUS = 6;
        public static final int CLINICS_BLOOD_VESSELS = 7;
        public static final int CLINICS_ENDOCRINE_GLANDS = 8;
        public static final int CLINICS_RHEUMATISM_AND_IMMUNITY = 9;
        public static final int CLINICS_KIDNEY = 10;
        public static final int CLINICS_THE_PAIN = 11;
        public static final int CLINICS_CHESTS_DISEASES = 12;
        public static final int CLINICS_HEART_DRAWING = 13;
        public static final int CLINICS_CARDIOTHORACIC_SURGERY = 14;
        public static final int CLINICS_FERTILITY_UNIT = 15;
        public static final int CLINICS_GENERAL_INTERIOR = 16;
        public static final int CLINICS_RHEUMATISM_AND_REHABILITATION = 17;
        public static final int CLINICS_PLASTIC_SURGERY = 18;
        public static final int CLINICS_GENERAL_SURGERY = 19;
        public static final int CLINICS_ONCOLOGY_AND_NUCLEAR_MEDICINE = 20;
        public static final int CLINICS_LEATHER_AND_GENITAL = 21;

        static boolean isValidTheNamesOfTheClinics(int theNamesOfTheClinics) {
            return theNamesOfTheClinics == CLINICS_UNKNOWN ||
                    theNamesOfTheClinics == CLINICS_ENDEMIC_DISEASES ||
                    theNamesOfTheClinics == CLINICS_MEDICAL_AND_MICROBIOLOGICAL_ANALYZES ||
                    theNamesOfTheClinics == CLINICS_PSYCHOLOGICAL_DISEASES ||
                    theNamesOfTheClinics == CLINICS_PHONETIC_AND_PHONEME ||
                    theNamesOfTheClinics == CLINICS_EAR_NOSE_AND_THROAT ||
                    theNamesOfTheClinics == CLINICS_COLON_AND_ANUS ||
                    theNamesOfTheClinics == CLINICS_BLOOD_VESSELS ||
                    theNamesOfTheClinics == CLINICS_ENDOCRINE_GLANDS ||
                    theNamesOfTheClinics == CLINICS_RHEUMATISM_AND_IMMUNITY ||
                    theNamesOfTheClinics == CLINICS_KIDNEY ||
                    theNamesOfTheClinics == CLINICS_THE_PAIN ||
                    theNamesOfTheClinics == CLINICS_CHESTS_DISEASES ||
                    theNamesOfTheClinics == CLINICS_HEART_DRAWING ||
                    theNamesOfTheClinics == CLINICS_CARDIOTHORACIC_SURGERY ||
                    theNamesOfTheClinics == CLINICS_FERTILITY_UNIT ||
                    theNamesOfTheClinics == CLINICS_GENERAL_INTERIOR ||
                    theNamesOfTheClinics == CLINICS_RHEUMATISM_AND_REHABILITATION ||
                    theNamesOfTheClinics == CLINICS_PLASTIC_SURGERY ||
                    theNamesOfTheClinics == CLINICS_GENERAL_SURGERY ||
                    theNamesOfTheClinics == CLINICS_ONCOLOGY_AND_NUCLEAR_MEDICINE ||
                    theNamesOfTheClinics == CLINICS_LEATHER_AND_GENITAL;
        }
    }

    // Patient records
    public static final class PatientRecordsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_RECORDS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_RECORDS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_RECORDS;

        // Patient records table
        public static final String TABLE_NAME = PATH_PATIENT_RECORDS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_BILL_TO_NAME = "bill_to_name";
        public static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_MEDICAL_RECORD_ID = "medical_record_id";
        public static final String COLUMN_NEXT_APPOINTMENT_DATE = "next_appointment_date";
        public static final String COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE = "next_treatment_plan_review_date";
        public static final String COLUMN_PHYSICIAN_SIGNATURE = "physician_signature";
        public static final String COLUMN_DATE_SIGNED = "date_signed";
        public static final String COLUMN_X_RAY_IMAGE = "x_ray_image";
        public static final String COLUMN_PATIENT_ID = "patient_id";

    }

    // Patient progress
    public static final class PatientProgressEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_PROGRESS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_PROGRESS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_PROGRESS;

        // Patient progress table
        public static final String TABLE_NAME = PATH_PATIENT_PROGRESS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROGRESS_NOTES = "progress_notes";
        //edit name column is unq
        public static final String COLUMN_DATE = "date_progress";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Invoices
    public static final class InvoicesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVOICES);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVOICES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INVOICES;

        // Invoices table
        public static final String TABLE_NAME = PATH_INVOICES;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE_OF_SVC = "date_of_svc";
        public static final String COLUMN_INVOICE_DATE = "invoice_date";
        public static final String COLUMN_DATE_DUE = "date_due";
        public static final String COLUMN_BILL_TO_NAME = "bill_to_name";
        public static final String COLUMN_BILL_TO_ADDRESS = "bill_to_address";
        public static final String COLUMN_BILL_TO_PHONE = "bill_to_phone";
        public static final String COLUMN_BILL_TO_FAX = "bill_to_fax";
        public static final String COLUMN_BILL_TO_EMAIL = "bill_to_email";
        public static final String COLUMN_SVC_ID = "svc_id";
        public static final String COLUMN_MEDICAL_SERVICES = "medical_services";
        public static final String COLUMN_MEDICATION = "medication";
        public static final String COLUMN_COST = "cost";
        public static final String COLUMN_SUBTOTAL = "subtotal";
        public static final String COLUMN_TAX_RATE = "tax_rate";
        public static final String COLUMN_TOTAL_TAX = "total_tax";
        public static final String COLUMN_OTHER = "other";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_QUESTIONS_NAME = "questions_name";
        public static final String COLUMN_QUESTIONS_EMAIL = "questions_email";
        public static final String COLUMN_QUESTIONS_PHONE = "questions_phone";
        public static final String COLUMN_QUESTIONS_WEB = "questions_web";
        public static final String COLUMN_PROCEDURE = "procedure";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Health record
    public static final class HealthRecordEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_HEALTH_RECORD);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTH_RECORD;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HEALTH_RECORD;

        // Health record table
        public static final String TABLE_NAME = PATH_HEALTH_RECORD;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DATE_OF_THE_LAST_UPDATE = "date_of_the_last_update";
        public static final String COLUMN_CURRENT_PHYSICIAN_NAME = "current_physician_name";
        public static final String COLUMN_DOCTORS_PHONE = "doctors_phone";
        public static final String COLUMN_CURRENT_PHARMACY_NAME = "current_pharmacy_name";
        public static final String COLUMN_PHARMACY_PHONE = "pharmacy_phone";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Current and past medications
    public static final class CurrentAndPastMedicationsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CURRENT_AND_PAST_MEDICATIONS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CURRENT_AND_PAST_MEDICATIONS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CURRENT_AND_PAST_MEDICATIONS;

        // Current and past medications table
        public static final String TABLE_NAME = PATH_CURRENT_AND_PAST_MEDICATIONS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MEDICAMENT_NAME = "medicament_name";
        public static final String COLUMN_PHYSICIAN = "physician";
        public static final String COLUMN_DOSAGE = "dosage";
        public static final String COLUMN_FREQ = "freq";
        public static final String COLUMN_PURPOSE = "purpose";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Major illnesses
    public static final class MajorIllnessesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MAJOR_ILLNESSES);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MAJOR_ILLNESSES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MAJOR_ILLNESSES;

        // Major illnesses table
        public static final String TABLE_NAME = PATH_MAJOR_ILLNESSES;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ILLNESS = "illness";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_PHYSICIAN = "physician";
        public static final String COLUMN_TREATMENT_NOTES = "treatment_notes";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Surgical procedures
    public static final class SurgicalProceduresEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SURGICAL_PROCEDURES);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SURGICAL_PROCEDURES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SURGICAL_PROCEDURES;

        // Surgical procedures table
        public static final String TABLE_NAME = PATH_SURGICAL_PROCEDURES;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PROCEDURE = "procedure";
        public static final String COLUMN_PHYSICIAN = "physician";
        public static final String COLUMN_HOSPITAL = "hospital";
        public static final String COLUMN_DATE_SURGICAL_PROCEDURES = "date_surgical_procedures";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Patient vaccines
    public static final class PatientVaccinesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_VACCINES);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_VACCINES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_VACCINES;

        // Patient vaccines table
        public static final String TABLE_NAME = PATH_PATIENT_VACCINES;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME_OF_VACCINATION = "name_of_vaccination";
        public static final String COLUMN_HISTORY_OF_VACCINATION = "history_of_vaccination";
        public static final String COLUMN_PATIENT_ID = "patient_id";

        // Names of vaccination
        public static final int VACCINATION_UNKNOWN = 0;
        public static final int VACCINATION_D_T = 1;
        public static final int VACCINATION_D_T_A_P = 2;
        public static final int VACCINATION_T_D = 3;
        public static final int VACCINATION_TETANUS_T_DAP = 4;
        public static final int VACCINATION_TETANUS = 5;
        public static final int VACCINATION_INFLUENZA_VACCINE = 6;
        public static final int VACCINATION_ZOSTAVAX = 7;
        public static final int VACCINATION_MENINGITIS = 8;
        public static final int VACCINATION_YELLOW_FEVER = 9;
        public static final int VACCINATION_POLIO = 10;

        public static boolean isValidNamesOfVaccination(int typesOfPatientVaccinesTetanus) {
            return typesOfPatientVaccinesTetanus == VACCINATION_UNKNOWN ||
                    typesOfPatientVaccinesTetanus == VACCINATION_D_T ||
                    typesOfPatientVaccinesTetanus == VACCINATION_D_T_A_P ||
                    typesOfPatientVaccinesTetanus == VACCINATION_T_D ||
                    typesOfPatientVaccinesTetanus == VACCINATION_TETANUS_T_DAP ||
                    typesOfPatientVaccinesTetanus == VACCINATION_TETANUS ||
                    typesOfPatientVaccinesTetanus == VACCINATION_INFLUENZA_VACCINE ||
                    typesOfPatientVaccinesTetanus == VACCINATION_ZOSTAVAX ||
                    typesOfPatientVaccinesTetanus == VACCINATION_MENINGITIS ||
                    typesOfPatientVaccinesTetanus == VACCINATION_YELLOW_FEVER ||
                    typesOfPatientVaccinesTetanus == VACCINATION_POLIO;
        }
    }

    // The doctor
    // Doctor diagnosis
    public static final class DoctorDiagnosisEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_DOCTOR_DIAGNOSIS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DOCTOR_DIAGNOSIS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DOCTOR_DIAGNOSIS;

        // Doctor diagnosis table
        public static final String TABLE_NAME = PATH_DOCTOR_DIAGNOSIS;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DIAGNOSIS = "diagnosis";
        public static final String COLUMN_ADDITIONAL_NOTES = "additional_notes";
        public static final String COLUMN_PERFORMING_PHYSICIAN_SIGNATURE = "performing_physician_signature";
        public static final String COLUMN_DATE_OF_SERVICE = "Date_of_Service";
        public static final String COLUMN_PATIENT_DATA_TO_CLINICS_ID = "patient_data_to_clinics_id";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Patient data to pharmacy
    public static final class PatientDataToPharmacyEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_DATA_TO_PHARMACY);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_PHARMACY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_PHARMACY;

        // Patient data to pharmacy table
        public static final String TABLE_NAME = PATH_PATIENT_DATA_TO_PHARMACY;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TRANSFER_DATE = "transfer_date";
        public static final String COLUMN_DOCTOR_DIAGNOSIS_ID = "doctor_diagnosis_id";
        public static final String COLUMN_PATIENT_ID = "patient_id";
    }

    // Patient data to radiology
    public static final class PatientDataToRadiologyEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PATIENT_DATA_TO_RADIOLOGY);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_RADIOLOGY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PATIENT_DATA_TO_RADIOLOGY;

        // Patient data to radiology table
        public static final String TABLE_NAME = PATH_PATIENT_DATA_TO_RADIOLOGY;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TRANSFER_DATE = "transfer_date";
        public static final String COLUMN_TYPES_OF_RADIATION = "types_of_radiation";
        public static final String COLUMN_PATIENT_ID = "patient_id";

        // Types of radiology
        public static final int RADIOLOGY_UNKNOWN = 0;
        public static final int RADIOLOGY_X_RAYS = 1;
        public static final int RADIOLOGY_CT_SCAN = 2;
        public static final int RADIOLOGY_MAGNETIC_RESONANCE_IMAGING = 3;
        public static final int RADIOLOGY_ULTRASOUND = 4;
        public static final int RADIOLOGY_SECTIONAL_TOMOGRAPHY_OF_THE_POSITRON_EMISSION = 5;

        public static boolean isValidTypesOfRadiology(int typesOfPatientVaccinesTetanus) {
            return typesOfPatientVaccinesTetanus == RADIOLOGY_UNKNOWN ||
                    typesOfPatientVaccinesTetanus == RADIOLOGY_X_RAYS ||
                    typesOfPatientVaccinesTetanus == RADIOLOGY_CT_SCAN ||
                    typesOfPatientVaccinesTetanus == RADIOLOGY_MAGNETIC_RESONANCE_IMAGING ||
                    typesOfPatientVaccinesTetanus == RADIOLOGY_ULTRASOUND ||
                    typesOfPatientVaccinesTetanus == RADIOLOGY_SECTIONAL_TOMOGRAPHY_OF_THE_POSITRON_EMISSION;
        }
    }

    // The pharmacy
    // Medicine registry
    public static final class MedicineRegistryEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_MEDICINE_REGISTRY);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEDICINE_REGISTRY;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MEDICINE_REGISTRY;

        // Medicine registry table
        public static final String TABLE_NAME = PATH_MEDICINE_REGISTRY;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_QR = "qr";
        public static final String COLUMN_MEDICINE_NAME = "medicine_name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
    }

    // Sales record
    public static final class SalesRecordEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_SALES_RECORD);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SALES_RECORD;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SALES_RECORD;

        // Sales record table
        public static final String TABLE_NAME = PATH_SALES_RECORD;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MEDICINE_REGISTRY_ID = "medicine_registry_id";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SALE_DATE = "sale_date";
        public static final String COLUMN_PRICE = "price";
    }

    // Personnel
    // Employees
    public static final class EmployeesEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EMPLOYEES);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EMPLOYEES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EMPLOYEES;

        // Sales record table
        public static final String TABLE_NAME = PATH_EMPLOYEES;
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_HIRE_DATE = "hire_date";
        public static final String COLUMN_SALARY = "salary";
        public static final String COLUMN_JOB_TITLE = "job_title";
        public static final String COLUMN_MIN_SALARY = "min_salary";
        public static final String COLUMN_MAX_SALARY = "max_salary";
        public static final String COLUMN_DEPARTMENT_NAME = "department_name";
        public static final String COLUMN_REGION_NAME = "region_name";
        public static final String COLUMN_COUNTRY_NAME = "country_name";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_STREET_ADDRESS = "street_address";
        public static final String COLUMN_POSTAL_CODE = "postal_code";
    }
}
