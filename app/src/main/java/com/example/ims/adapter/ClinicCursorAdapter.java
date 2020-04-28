package com.example.ims.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.*;

public class ClinicCursorAdapter extends CursorAdapter {
    public ClinicCursorAdapter(Context context, Cursor c) {
        super(context, c, 15);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_clinic, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView firstLastNameTextView = view.findViewById(R.id.text_clinic_firstlastname);
        TextView clinicNameTextView = view.findViewById(R.id.text_clinic_clinicname);
        TextView transferDataTextView = view.findViewById(R.id.text_clinic_transferdata);

        int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID);
        int clinicNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME);
        int transferDataColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE);

        int patientId = cursor.getInt(patientIdColumnIndex);
        int clinicName = cursor.getInt(clinicNameColumnIndex);
        String transferDate = cursor.getString(transferDataColumnIndex);


        firstLastNameTextView.setText(getPatientName(patientId, context));
        clinicNameTextView.setText(getClinicName(clinicName));
        transferDataTextView.setText(transferDate);
    }

    // Get patient name
    private String getPatientName(int patientId, Context context) {
        String patientName = null;

        // Column name
        String[] projection = {
                ImsContract.PatientEntry.COLUMN_FIRST_NAME,
                ImsContract.PatientEntry.COLUMN_LAST_NAME};

        // SQL query
        @SuppressLint("Recycle") Cursor cursor = context.getContentResolver().query(
                ImsContract.PatientEntry.CONTENT_URI,
                projection,
                ImsContract.PatientEntry._ID + " =" + patientId,
                null,
                null);

        assert cursor != null;
        while (cursor.moveToNext()) {

            // Firs name and last name column index
            int patientFirsNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);
            int patientLastNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME);

            // Firs name and last name
            String patientFirsName = cursor.getString(patientFirsNameColumnIndex);
            String patientLastName = cursor.getString(patientLastNameColumnIndex);

            if (patientFirsName != null & patientLastName != null) {
                patientName = patientFirsName.concat(" " + patientLastName);
            }
        }
        return patientName;
    }

    // Get clinic name
    private String getClinicName(int clinicName) {
        if (CLINICS_ENDEMIC_DISEASES == clinicName) {
            return "Endemic diseases:";
        } else if (clinicName == CLINICS_MEDICAL_AND_MICROBIOLOGICAL_ANALYZES) {
            return "Medical and microbiological analyzes";
        } else if (clinicName == CLINICS_PSYCHOLOGICAL_DISEASES) {
            return "Psychological diseases";
        } else if (clinicName == CLINICS_PHONETIC_AND_PHONEME) {
            return "Phonetic and phoneme";
        } else if (clinicName == CLINICS_EAR_NOSE_AND_THROAT) {
            return "Ear, nose and throat";
        } else if (clinicName == CLINICS_COLON_AND_ANUS) {
            return "Colon and anus";
        } else if (clinicName == CLINICS_BLOOD_VESSELS) {
            return "Blood vessels";
        } else if (clinicName == CLINICS_ENDOCRINE_GLANDS) {
            return "Endocrine glands";
        } else if (clinicName == CLINICS_RHEUMATISM_AND_IMMUNITY) {
            return "Rheumatism and immunity";
        } else if (clinicName == CLINICS_KIDNEY) {
            return "Kidney";
        } else if (clinicName == CLINICS_THE_PAIN) {
            return "The pain";
        } else if (clinicName == CLINICS_CHESTS_DISEASES) {
            return "Chest's diseases";
        } else if (clinicName == CLINICS_HEART_DRAWING) {
            return "Heart drawing";
        } else if (clinicName == CLINICS_CARDIOTHORACIC_SURGERY) {
            return "Cardiothoracic surgery";
        } else if (clinicName == CLINICS_FERTILITY_UNIT) {
            return "Fertility unit";
        } else if (clinicName == CLINICS_GENERAL_INTERIOR) {
            return "General interior";
        } else if (clinicName == CLINICS_RHEUMATISM_AND_REHABILITATION) {
            return "Rheumatism and rehabilitation";
        } else if (clinicName == CLINICS_PLASTIC_SURGERY) {
            return "Plastic surgery";
        } else if (clinicName == CLINICS_GENERAL_SURGERY) {
            return "General surgery";
        } else if (clinicName == CLINICS_ONCOLOGY_AND_NUCLEAR_MEDICINE) {
            return "Oncology and nuclear medicine";
        } else if (clinicName == CLINICS_LEATHER_AND_GENITAL) {
            return "Leather and genital";
        }
        return "CLINICS_UNKNOWN";
    }
}



