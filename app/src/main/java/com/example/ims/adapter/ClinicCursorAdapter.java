package com.example.ims.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
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
import com.example.ims.TheDoctorActivity;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;

import java.util.Date;

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
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView firstLastNameTextView = view.findViewById(R.id.text_transferredtopharmacy_firstlastname);
        TextView clinicNameTextView = view.findViewById(R.id.text_clinic_clinicname);
        TextView transferDataTextView = view.findViewById(R.id.text_clinic_transferdata);

        Button clinicAnalysisLabButton = view.findViewById(R.id.button_clinic_analysislab);
        Button clinicRadiologyLaboratoryButton = view.findViewById(R.id.button_clinic_radiologylaboratory);
        Button clinicPharmacyButton = view.findViewById(R.id.button_clinic_pharmacy);


        int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID);
        int clinicNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME);
        int transferDataColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE);

        final int patientId = cursor.getInt(patientIdColumnIndex);
        int clinicName = cursor.getInt(clinicNameColumnIndex);
        String transferDate = cursor.getString(transferDataColumnIndex);

        firstLastNameTextView.setText(getPatientName(patientId, context));
        clinicNameTextView.setText(getClinicName(clinicName));
        transferDataTextView.setText(transferDate);

        // Transformation to analysis lab
        clinicAnalysisLabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new TheDoctorActivity().showTransferredToTheAnalysisLabDialog(context);
                final Uri patientIdUri = ContentUris.withAppendedId(ImsContract.PatientEntry.CONTENT_URI, patientId);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(TheDoctorActivity.mDialogTransferredToTheAnalysisLab);
                builder.setTitle("Transferred to the analysis lab");

                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Date date = new Date();

                        String dateString = Utils.formatDate(date);
                        int mTypesOfAnalysis = TheDoctorActivity.mTypesOfAnalysis;
                        int idPatient = getIdPatient(context, patientIdUri);

                        ContentValues values = new ContentValues();
                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE, dateString);
                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME, mTypesOfAnalysis);
                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID, String.valueOf(idPatient));


                        // Insert patient data to analysis
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

        // Transformation to radiology laboratory
        clinicRadiologyLaboratoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new TheDoctorActivity().showTransferredToRadiologyDialog(context);
                final Uri patientIdUri = ContentUris.withAppendedId(ImsContract.PatientEntry.CONTENT_URI, patientId);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(TheDoctorActivity.mDialogTransferredToRadiology);
                builder.setTitle("Transferred to the radiology");

                // Set patient name
                TheDoctorActivity.transferredToRadiologyFirstLastName.setText(getPatientName(patientId, context));

                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Date date = new Date();

                        String dateString = Utils.formatDate(date);
                        int mTypesOfRadiology = TheDoctorActivity.mTypesOfRadiology;
                        int idPatient = getIdPatient(context, patientIdUri);

                        ContentValues values = new ContentValues();
                        values.put(ImsContract.PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE, dateString);
                        values.put(ImsContract.PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION, mTypesOfRadiology);
                        values.put(ImsContract.PatientDataToRadiologyEntry.COLUMN_PATIENT_ID, String.valueOf(idPatient));


                        // Insert patient data to radiology
                        Uri newUri = context.getContentResolver().insert(ImsContract.PatientDataToRadiologyEntry.CONTENT_URI, values);
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

        // Transformation to the pharmacy
        clinicPharmacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new TheDoctorActivity().showTransferredToThePharmacyDialog(context);
                final Uri patientIdUri = ContentUris.withAppendedId(ImsContract.PatientEntry.CONTENT_URI, patientId);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(TheDoctorActivity.mDialogTransferredToThePharmacy);
                builder.setTitle("Transferred to the pharmacy");

                // Set patient name
                TheDoctorActivity.transferredToPharmacyFirstLastName.setText(getPatientName(patientId, context));

                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Date date = new Date();

                        String dateString = Utils.formatDate(date);
                        int mPatientDataToClinicsId = TheDoctorActivity.mPatientDataToClinicsId;
                        int idPatient = getIdPatient(context, patientIdUri);

                        ContentValues values = new ContentValues();
                        values.put(ImsContract.PatientDataToPharmacyEntry.COLUMN_TRANSFER_DATE, dateString);
                        values.put(ImsContract.PatientDataToPharmacyEntry.COLUMN_DOCTOR_DIAGNOSIS_ID, mPatientDataToClinicsId);
                        values.put(ImsContract.PatientDataToPharmacyEntry.COLUMN_PATIENT_ID, String.valueOf(idPatient));

                        // Insert patient data to pharmacy
                        Uri newUri = context.getContentResolver().insert(ImsContract.PatientDataToPharmacyEntry.CONTENT_URI, values);
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
    }

    // Get patient name
    private String getPatientName(int patientId, Context context) {
        String patientName = null;

        // Uri
        Uri uri = ImsContract.PatientEntry.CONTENT_URI;

        // Column name
        String[] projection =
                {
                        ImsContract.PatientEntry.COLUMN_FIRST_NAME,
                        ImsContract.PatientEntry.COLUMN_LAST_NAME
                };

        // Selection
        String selection = ImsContract.PatientEntry._ID + " =" + patientId;

        // SQL query
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                selection,
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

    // Get id patient
    @RequiresApi(api = Build.VERSION_CODES.O)
    private int getIdPatient(Context context, Uri currentPatientUri) {
        int patientId = 0;
        String[] projection = {ImsContract.PatientEntry._ID};
        Cursor cursor = context.getContentResolver().query(currentPatientUri, projection, null, null);

        assert cursor != null;
        while (cursor.moveToNext()) {
            int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry._ID);
            patientId = cursor.getInt(patientIdColumnIndex);
        }
        return patientId;
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


