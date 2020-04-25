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

        clinicNameTextView.setText(clinicName);
        transferDataTextView.setText(transferDate);




        Uri uri = ImsContract.PatientEntry.CONTENT_URI;
         cursor =context. getContentResolver().query(uri, new String[]{ImsContract.PatientEntry.COLUMN_FIRST_NAME},
                 ImsContract.PatientEntry._ID + " ="+patientId , null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            String patientname = cursor.getString(cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME));

            if (patientname != null) {
                firstLastNameTextView.setText(patientname);
            }
        }

    }
}
