package com.example.ims.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

public class AnalysisCursorAdapter extends CursorAdapter
{
    public AnalysisCursorAdapter(Context context, Cursor c)
    {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return LayoutInflater.from(context).inflate(R.layout.item_analysis_lab,viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {

        TextView firstLastNameTextView = view.findViewById(R.id.text_analysislap_flastname);
        TextView typeOfAnalysisTextView = view.findViewById(R.id.text_analysislap_typesofanalysis);
        TextView dateTextView = view.findViewById(R.id.text_analysislap_transferdate);



        int  patientNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_PATIENT_NAME);
        int  analysisNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_ANALYSIS_NAME);
        int  transferDateColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToAnalysisEntry.COLUMN_TRANSFER_DATE);

        String patientName = cursor.getString(patientNameColumnIndex);
        int analysisName = cursor.getInt(analysisNameColumnIndex);
        String transferDate= cursor.getString(transferDateColumnIndex);


        firstLastNameTextView.setText(patientName);
        typeOfAnalysisTextView.setText(String.valueOf(analysisName));
        dateTextView.setText(transferDate);



    }
}
