package com.example.ims.adapter;

import android.content.ContentUris;
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

public class  DoctorCursorAdapter extends CursorAdapter {
    public DoctorCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_patient_name, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView firstNameTextView = view.findViewById(R.id.text_first_name);
        TextView lastNameTextView = view.findViewById(R.id.text_list_name);

        final int idColumnIndex = cursor.getColumnIndex(ImsContract.DoctorDiagnosisEntry.COLUMN_PATIENT_ID);
        int firstNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME);

        final int id = cursor.getInt(idColumnIndex);
        String firstName = cursor.getString(firstNameColumnIndex);
        String lastName = cursor.getString(lastNameColumnIndex);


        firstNameTextView.setText(firstName);
        lastNameTextView.setText(lastName);


        Uri productUri = ContentUris.withAppendedId(ImsContract.PatientEntry.CONTENT_URI, id);


    }
}
