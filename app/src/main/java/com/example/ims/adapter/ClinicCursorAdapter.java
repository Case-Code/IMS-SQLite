package com.example.ims.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_BLOOD_VESSELS;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_CARDIOTHORACIC_SURGERY;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_CHESTS_DISEASES;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_COLON_AND_ANUS;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_EAR_NOSE_AND_THROAT;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_ENDEMIC_DISEASES;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_ENDOCRINE_GLANDS;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_FERTILITY_UNIT;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_GENERAL_INTERIOR;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_GENERAL_SURGERY;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_HEART_DRAWING;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_KIDNEY;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_LEATHER_AND_GENITAL;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_MEDICAL_AND_MICROBIOLOGICAL_ANALYZES;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_ONCOLOGY_AND_NUCLEAR_MEDICINE;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_PHONETIC_AND_PHONEME;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_PLASTIC_SURGERY;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_PSYCHOLOGICAL_DISEASES;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_RHEUMATISM_AND_IMMUNITY;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_RHEUMATISM_AND_REHABILITATION;
import static com.example.ims.data.ImsContract.PatientDataToClinicsEntry.CLINICS_THE_PAIN;

public class ClinicCursorAdapter extends CursorAdapter {
    public ClinicCursorAdapter(Context context, Cursor c) {
        super(context, c,15);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.item_clinic, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView firstLastNameTextView=view.findViewById(R.id.text_clinic_firstlastname);
        TextView clinicNameTextView=view.findViewById(R.id.text_clinic_clinicname);
        TextView transferDataTextView=view.findViewById(R.id.text_clinic_transferdata);

        int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID);
        int clinicNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME);
        int transferDataColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE);

        String patientId = cursor.getString(patientIdColumnIndex);
        String clinicName = cursor.getString(clinicNameColumnIndex);
        String transferDate  = cursor.getString(transferDataColumnIndex);


     int name =Integer.parseInt(clinicName) ;
     int idPatient = Integer.parseInt(patientId);


        firstLastNameTextView.setText(getNamePatient(idPatient ,context));

        clinicNameTextView.setText(getNameClinic(name));
        transferDataTextView.setText(transferDate);






    }

    public String getNameClinic(int name ){
        if(CLINICS_ENDEMIC_DISEASES == name){
            return  "Endemic diseases:";

        }else if(name==CLINICS_MEDICAL_AND_MICROBIOLOGICAL_ANALYZES){
            return "Medical and microbiological analyzes";

        }else if(name==CLINICS_PSYCHOLOGICAL_DISEASES){
            return"Psychological diseases";

        }else if(name==CLINICS_PHONETIC_AND_PHONEME){
        return"Phonetic and phoneme";
    }
        else if(name==CLINICS_EAR_NOSE_AND_THROAT){
            return"Ear, nose and throat";
        }
        else if(name==CLINICS_COLON_AND_ANUS){
            return"Colon and anus";
        }
        else if(name==CLINICS_BLOOD_VESSELS){
            return"Blood vessels";
        }
        else if(name==CLINICS_ENDOCRINE_GLANDS){
            return"Endocrine glands";
        }
        else if(name==CLINICS_RHEUMATISM_AND_IMMUNITY){
            return"Rheumatism and immunity";
        }
        else if(name==CLINICS_KIDNEY){
            return"Kidney";
        } else if(name==CLINICS_THE_PAIN){
            return"The pain";
        }
        else if(name==CLINICS_CHESTS_DISEASES){
            return"Chest's diseases";
        }
        else if(name==CLINICS_HEART_DRAWING){
            return"Heart drawing";
        }
        else if(name==CLINICS_CARDIOTHORACIC_SURGERY){
            return"Cardiothoracic surgery";
        }
        else if(name==CLINICS_FERTILITY_UNIT){
            return"Fertility unit";
        }
        else if(name==CLINICS_GENERAL_INTERIOR){
            return"General interior";
        }
        else if(name==CLINICS_RHEUMATISM_AND_REHABILITATION){
            return"Rheumatism and rehabilitation";
        }
        else if(name==CLINICS_PLASTIC_SURGERY){
            return"Plastic surgery";
        }
        else if(name==CLINICS_GENERAL_SURGERY){
            return"General surgery";
        }
        else if(name==CLINICS_ONCOLOGY_AND_NUCLEAR_MEDICINE){
            return"Oncology and nuclear medicine";
        }
        else if(name==CLINICS_LEATHER_AND_GENITAL){
            return"Leather and genital";
        }

            return"CLINICS_UNKNOWN";



    }

    public String getNamePatient( int patientId ,Context context){
        Cursor cursor;
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
        cursor =context.getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME,ImsContract.PatientEntry.COLUMN_LAST_NAME},
                ImsContract.PatientEntry._ID + " ="+patientId , null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            String patientFName = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));
            String patientLName = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME));

            if (patientFName != null& patientLName!=null) {
                        return  patientFName+" "+patientLName ;
                }
        }
     return null;
    }
}
