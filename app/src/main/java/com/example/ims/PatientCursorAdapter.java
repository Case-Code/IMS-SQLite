package com.example.ims;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ims.data.ImsContract.PatientEntry;

public class PatientCursorAdapter extends CursorAdapter {

    public PatientCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item_patients, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView firstNameTextView = view.findViewById(R.id.text_first_name_and_list_name);
        TextView birthDateTextView = view.findViewById(R.id.text_birth_date);
        TextView weightTextView = view.findViewById(R.id.text_weight);
        TextView locationTextView = view.findViewById(R.id.text_location);
        TextView phoneNumberTextView = view.findViewById(R.id.text_phone_number);
        TextView heightTextView = view.findViewById(R.id.text_height);

        Button analysisLabButton = view.findViewById(R.id.button_analysis_lab);
        Button clinicButton = view.findViewById(R.id.button_clinic);
        Button healthRecordButton = view.findViewById(R.id.button_health_record);
        Button patientRecordsButton = view.findViewById(R.id.button_patient_records);
        Button invoicesButton = view.findViewById(R.id.button_invoices);


        int firstNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_LAST_NAME);
        int phoneNumberColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PHONE_NUMBER);
        int birthDateColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_BIRTH_DATE);
        int locationColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_LOCATION);
        int weightColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_WEIGHT);
        int heightColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_HEIGHT);

        String firstName = cursor.getString(firstNameColumnIndex);
        String lastName = cursor.getString(lastNameColumnIndex);
        String phoneNumber = cursor.getString(phoneNumberColumnIndex);
        String birthDate = cursor.getString(birthDateColumnIndex);
        String location = cursor.getString(locationColumnIndex);
        String weight = cursor.getString(weightColumnIndex);
        String height = cursor.getString(heightColumnIndex);

        firstNameTextView.setText(firstName.concat(" " + lastName));
        phoneNumberTextView.setText(phoneNumber);
        birthDateTextView.setText(birthDate);
        locationTextView.setText(location);
        weightTextView.setText(weight.concat(" kg"));
        heightTextView.setText(height.concat(" cm"));

        analysisLabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add analysis lab code
            }
        });

        clinicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add clinic code
            }
        });

        healthRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add health record code
            }
        });

        patientRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add patient records code
            }
        });

        invoicesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add invoicesButton code
            }
        });
    }
}
