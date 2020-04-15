package com.example.ims.adapter;

import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.R;
import com.example.ims.ReceptionActivity;
import com.example.ims.data.ImsContract;
import com.example.ims.data.ImsContract.PatientEntry;
import com.example.ims.fragment.FragmentHealthRecord;
import com.example.ims.fragment.FragmentInvoices;
import com.example.ims.fragment.FragmentPatientRecords;

import java.util.Date;

public class PatientCursorAdapter extends CursorAdapter {

    private static final String TAG = PatientCursorAdapter.class.getSimpleName();
    private int heightColumnIndex;
    private Uri mCurrentPatientUri;
    private ContentValues values;
    public int id;


    public PatientCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_patients, parent, false);
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {
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
        final int idColumnIndex = cursor.getColumnIndex(PatientEntry._ID);
        int firstNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_LAST_NAME);
        int phoneNumberColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_PHONE_NUMBER);
        int birthDateColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_BIRTH_DATE);
        int locationColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_LOCATION);
        int weightColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_WEIGHT);
        int heightColumnIndex = cursor.getColumnIndex(PatientEntry.COLUMN_HEIGHT);

        id = cursor.getInt(idColumnIndex);
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
        final ReceptionActivity activity = new ReceptionActivity();

        analysisLabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri productUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                new ReceptionActivity().showTransferredToTheAnalysisLabDialog(context);

//                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setView(activity.mDialogTransferredToTheAnalysisLabView);
//                builder.setTitle("Transferred to the analysis lab");
//                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        final Date date = new Date();
//                        if (mCurrentPatientUri == null && activity.mTypesOfAnalysis == ImsContract.PatientDataToAnalysisEntry.ANALYSIS_UNKNOWN) {
//                            return;
//                        }
//                        values = new ContentValues();
//                        values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME, activity.mTypesOfAnalysis);
//
//                        if (TextUtils.isEmpty(date.toString())) {
//                            Toast.makeText(context, "First name is required", Toast.LENGTH_SHORT).show();
//                        } else {
//                            values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE, date.toString());
//                        }
//                        if (id <= 0) {
//                            Toast.makeText(context, "retrun please ", Toast.LENGTH_SHORT).show();
//                        } else {
//                            values.put(ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID, id);
//                        }
//
//                        if (mCurrentPatientUri == null) {
//                            Uri newUri = context.getContentResolver().insert(ImsContract.PatientDataToAnalysisEntry.CONTENT_URI,
//                                    values);
//                            if (newUri == null) {
//                                Toast.makeText(context, "falid", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(context, ":susccful", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(context, ":susccful", Toast.LENGTH_SHORT).show();
//
//                            }
//                        } else {
//                            return;
//                        }
//
//
//                    }
//                });
//                builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (dialog != null) {
//                            dialog.dismiss();
//
//                        }
//
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.setCanceledOnTouchOutside(false);
//                alertDialog.show();

            }
        });

        clinicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri productUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                new ReceptionActivity().showTransferredToClinicsDialog(context);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(activity.mDialogTransferredToClinicsView);
                builder.setTitle("Transferred to clinics");
                builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final Date date = new Date();

                        if (mCurrentPatientUri == null
                                && activity.mTheNamesOfTheClinics == ImsContract.PatientDataToClinicsEntry.CLINICS_UNKNOWN) {
                            return;
                        }
                        values = new ContentValues();

                        values.put(ImsContract.PatientDataToClinicsEntry.COLUMN_CLINIC_NAME, activity.mTheNamesOfTheClinics);

                        if (TextUtils.isEmpty(date.toString())) {
                            Toast.makeText(context, "First name is required", Toast.LENGTH_SHORT).show();
                        } else {
                            values.put(ImsContract.PatientDataToClinicsEntry.COLUMN_TRANSFER_DATE, date.toString());
                        }
                        if (id <= 0) {
                            Toast.makeText(context, "retrun please ", Toast.LENGTH_SHORT).show();
                        } else {
                            values.put(ImsContract.PatientDataToClinicsEntry.COLUMN_PATIENT_ID, id);
                        }

                        if (mCurrentPatientUri == null) {
                            Uri newUri = context.getContentResolver().insert(ImsContract.PatientDataToClinicsEntry.CONTENT_URI,
                                    values);
                            if (newUri == null) {
                                Toast.makeText(context, "falid", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, ":susccful", Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, ":susccful", Toast.LENGTH_SHORT).show();
                                System.out.println(values);

                                Log.i(TAG, values.toString());

                            }
                        } else {
                            return;
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

        healthRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri productUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                ReceptionActivity.mFragmentManager.beginTransaction().replace(R.id.frame_layout_patient_records, new FragmentHealthRecord(), null).commit();
            }
        });

        patientRecordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri productUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);

                ReceptionActivity.mFragmentManager.beginTransaction().replace(R.id.frame_layout_patient_records, new FragmentPatientRecords(), null).commit();
            }
        });

        invoicesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri productUri = ContentUris.withAppendedId(PatientEntry.CONTENT_URI, id);


                ReceptionActivity.mFragmentManager.beginTransaction().replace(R.id.frame_layout_patient_records
                        , new FragmentInvoices(id), null).commit();


            }

        });
    }

    /**
     * This method reduced product stock by 1
     *
     * @param context                - Activity context
     * @param productUri             - Uri used to update the stock of a specific product in the ListView
     * @param currentQuantityInStock - current stock of that specific product
     */
    private void adjustProductQuantity(Context context, Uri productUri, int currentQuantityInStock) {

        // Subtract 1 from current value if quantity of product >= 1
        int newQuantityValue = (currentQuantityInStock >= 1) ? currentQuantityInStock - 1 : 0;

        if (currentQuantityInStock == 0) {
            Toast.makeText(context.getApplicationContext(), "Product is out of stock!", Toast.LENGTH_SHORT).show();
        }

        // Update table by using new value of quantity
        ContentValues contentValues = new ContentValues();
        contentValues.put(PatientEntry.COLUMN_FIRST_NAME, newQuantityValue);
        int numRowsUpdated = context.getContentResolver().update(productUri, contentValues, null, null);
        if (numRowsUpdated > 0) {
            // Show error message in Logs with info about pass update.
            Log.i(TAG, "Item has been sold");
        } else {
            Toast.makeText(context.getApplicationContext(), "No available product in stock", Toast.LENGTH_SHORT).show();
            // Show error message in Logs with info about fail update.
            Log.e(TAG, "Issue with upload value of quantity");
        }
    }

}