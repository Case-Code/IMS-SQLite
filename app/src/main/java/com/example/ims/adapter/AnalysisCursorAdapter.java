package com.example.ims.adapter;

import android.annotation.SuppressLint;
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

import static com.example.ims.data.ImsContract.PatientDataToAnalysisEntry.*;


public class AnalysisCursorAdapter extends CursorAdapter
{
    public AnalysisCursorAdapter(Context context, Cursor c)
    {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)

    {
        return LayoutInflater.from(context).inflate(R.layout.item_analysis_lab, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {

        TextView firstLastNameTextView = view.findViewById(R.id.text_analysislap_flastname);
        TextView typeOfAnalysisTextView = view.findViewById(R.id.text_analysislap_typesofanalysis);
        TextView dateTextView = view.findViewById(R.id.text_analysislap_transferdate);


        int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_ID);
        int analysisNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME);
        int transferDateColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE);

        int patientId = cursor.getInt(patientIdColumnIndex);
        int analysisName = cursor.getInt(analysisNameColumnIndex);
        String transferDate = cursor.getString(transferDateColumnIndex);


        firstLastNameTextView.setText(getPatientName(patientId, context));
        typeOfAnalysisTextView.setText(getAnalysisName(analysisName));
        dateTextView.setText(transferDate);


    }

    // Get patient name
    private String getPatientName(int patientId, Context context)
    {
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
        while (cursor.moveToNext())
        {

            // Firs name and last name column index
            int patientFirsNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);
            int patientLastNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME);

            // Firs name and last name
            String patientFirsName = cursor.getString(patientFirsNameColumnIndex);
            String patientLastName = cursor.getString(patientLastNameColumnIndex);

            if (patientFirsName != null & patientLastName != null)
            {
                patientName = patientFirsName.concat(" " + patientLastName);
            }
        }
        return patientName;
    }

    private String getAnalysisName(int analysisName)
    {
        if (ANALYSIS_COMPLETE_BLOOD_COUNT == analysisName)
        {
            return "Complete blood count";
        }
        else if (ANALYSIS_URINE_EXAMINATION == analysisName)
        {
            return "Urine examination";
        }
        else if (ANALYSIS_STOOL_EXAMINATION == analysisName)
        {
            return "Stool examination";
        }
        return "ANALYSIS UNKNOWN";


    }

}
