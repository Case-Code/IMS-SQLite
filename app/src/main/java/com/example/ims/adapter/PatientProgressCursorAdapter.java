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

public class PatientProgressCursorAdapter extends CursorAdapter {
    private static final String TAG = PatientProgressCursorAdapter.class.getSimpleName();

    public PatientProgressCursorAdapter(Context context, Cursor c) {
        super(context, c, 1101);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_patient_progress, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textProgressTextView = view.findViewById(R.id.text_progress_notes);
        TextView textDateTextView = view.findViewById(R.id.text_date);

        int id = cursor.getColumnIndex(ImsContract.PatientProgressEntry._ID);
        int textProgressColumnIndex = cursor.getColumnIndex(ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES);
        int dateColumnIndex = cursor.getColumnIndex(ImsContract.PatientProgressEntry.COLUMN_DATE);

        String textProgress = cursor.getString(textProgressColumnIndex);
        String date = cursor.getString(dateColumnIndex);

        textProgressTextView.setText(textProgress);
        textDateTextView.setText(date);
    }
}
