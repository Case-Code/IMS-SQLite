package com.example.ims.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.ims.R;
import com.example.ims.ReceptionActivity;
import com.example.ims.data.ImsContract;
import com.example.ims.data.ImsContract.PatientEntry;
import com.example.ims.fragment.FragmentHealthRecord;
import com.example.ims.fragment.FragmentInvoices;
import com.example.ims.fragment.FragmentPatientRecords;
import com.example.logutil.Utils;

import java.util.Date;

public class PatientCursorAdapter extends CursorAdapter {

    private static final String TAG = PatientCursorAdapter.class.getSimpleName();

    public PatientCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_patients, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
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

        // Find the columns of patient attributes that we're interested in
        int patientIdColumnIndex = cursor.getColumnIndex(PatientEntry._ID);
        int firstNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_LAST_NAME);
        int phoneNumberColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PHONE_NUMBER);
        int birthDateColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_BIRTH_DATE);
        int locationColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_LOCATION);
        int weightColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_WEIGHT);
        int heightColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_HEIGHT);

        final int id = cursor.getInt(patientIdColumnIndex);
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

        // Analysis lab
        analysisLabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new ReceptionActivity().showTransferredToTheAnalysisLabDialog(context);
                final Uri patientId = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(ReceptionActivity.mDialogTransferredToTheAnalysisLab);
                builder.setTitle("Transferred to the analysis lab");

                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Date date = new Date();

                        String dateString = Utils.formatDate(date);
                        int mTypesOfAnalysis = ReceptionActivity.mTypesOfAnalysis;
                        int idPatient = getIdPatient(context, patientId);

                        ContentValues values = new ContentValues();
                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE, dateString);
                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME, mTypesOfAnalysis);
                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID, String.valueOf(idPatient));


                        // Insert and update patient
                        Uri newUri = context.getContentResolver().insert(ImsContract.PatientDataToAnalysisEntry.CONTENT_URI, values);
                        if (newUri == null) {
                            Toast.makeText(view.getContext(), "Error with transfer patient", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Transferred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        // Clinic
        clinicButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(final View v) {
                final Uri patientId = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                int idPatient = getIdPatient(context, patientId);
                new ReceptionActivity().showTransferredToClinicsDialog(context);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(ReceptionActivity.mDialogTransferredToClinicsView);
                builder.setTitle("Transferred to clinics");

                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Date date = new Date();

                        String dateString = Utils.formatDate(date);
                        int theNamesOfTheClinics = ReceptionActivity.mTheNamesOfTheClinics;
                        int idPatient = getIdPatient(context, patientId);
                        Log.e(TAG, "patientid::::" + idPatient);

                        ContentValues values = new ContentValues();
                        values.put(ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE, dateString);
                        values.put(ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME, theNamesOfTheClinics);
                        values.put(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID, String.valueOf(idPatient));

                        // Insert and update patient
                        Uri newUri = context.getContentResolver().insert(ImsContract.PatientDataToClinicsEntry.CONTENT_URI, values);
                        if (newUri == null) {
                            Toast.makeText(v.getContext(), "Error with transfer patient", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "Transferred", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });

        // Health record
        healthRecordButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final Uri patientId = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                int idPatient = getIdPatient(context, patientId);
                ReceptionActivity.mFragmentManager.beginTransaction().replace(R.id.frame_layout_patient_records, new FragmentHealthRecord(idPatient), null).commit();
            }
        });

        // Patient records
        patientRecordsButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final Uri patientId = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                int idPatient = getIdPatient(context, patientId);
                ReceptionActivity.mFragmentManager.beginTransaction().replace(R.id.frame_layout_patient_records, new FragmentPatientRecords(idPatient), null).commit();
            }
        });

        // Invoices
        invoicesButton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final Uri patientId = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                int idPatient = getIdPatient(context, patientId);
                ReceptionActivity.mFragmentManager.beginTransaction().replace(R.id.frame_layout_patient_records, new FragmentInvoices(idPatient), null).commit();
            }
        });
    }

    // Get id patient
    @RequiresApi(api = Build.VERSION_CODES.O)
    private int getIdPatient(Context context, Uri currentPatientUri) {
        int patientId = 0;
        String[] projection = {PatientEntry._ID};
        Cursor cursor = context.getContentResolver().query(currentPatientUri, projection, null, null);

        assert cursor != null;
        while (cursor.moveToNext()) {
            int patientIdColumnIndex = cursor.getColumnIndex(PatientEntry._ID);
            patientId = cursor.getInt(patientIdColumnIndex);
        }
        return patientId;
    }
}