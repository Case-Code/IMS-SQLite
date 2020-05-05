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

import static com.example.ims.data.ImsContract.PatientDataToRadiologyEntry.*;

public class RadiologyCursorAdapter extends CursorAdapter
{
    public RadiologyCursorAdapter(Context context, Cursor c)
    {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return LayoutInflater.from(context).inflate(R.layout.item_radiology_laboratory, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView firstLastNameTextView = view.findViewById(R.id.text_itemradiology_name);
        TextView typeOfRadiationTextView = view.findViewById(R.id.text_itemradiology_type);
        TextView dateTextView = view.findViewById(R.id.text_itemradiology_date);


        int patientIdColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToRadiologyEntry.COLUMN_PATIENT_ID);
        int typeOfRadiationColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToRadiologyEntry.COLUMN_TYPES_OF_RADIATION);
        int transferDateColumnIndex = cursor.getColumnIndex(ImsContract.PatientDataToRadiologyEntry.COLUMN_TRANSFER_DATE);

        int patientId = cursor.getInt(patientIdColumnIndex);
        int typeOfRadiation = cursor.getInt(typeOfRadiationColumnIndex);
        String transferDate = cursor.getString(transferDateColumnIndex);


        firstLastNameTextView.setText(getPatientName(patientId, context));
        typeOfRadiationTextView.setText(getTypeOfRadiation(typeOfRadiation));
        dateTextView.setText(transferDate);
    }

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

    private String getTypeOfRadiation(int type)
    {
        if (RADIOLOGY_X_RAYS == type)
        {
            return "X-rays";
        }
        else if (RADIOLOGY_CT_SCAN == type)
        {
            return "CT scan";
        }

        else if (RADIOLOGY_MAGNETIC_RESONANCE_IMAGING == type)
        {
            return "Magnetic resonance imaging";
        }
        else if (RADIOLOGY_ULTRASOUND == type)
        {
            return "Ultrasound";
        }
        else if (RADIOLOGY_SECTIONAL_TOMOGRAPHY_OF_THE_POSITRON_EMISSION == type)
        {
            return "Sectional tomography of the positron emission";
        }

        return "UNKNOWN";


    }
}
