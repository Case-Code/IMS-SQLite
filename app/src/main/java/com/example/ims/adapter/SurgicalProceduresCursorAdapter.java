package com.example.ims.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.loader.content.CursorLoader;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

public class SurgicalProceduresCursorAdapter extends CursorAdapter {

    public SurgicalProceduresCursorAdapter(Context context, Cursor c) {
        super(context, c, 12);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_surgical_procedures, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView procedureTextView = view.findViewById(R.id.text_item_sp_procedure);
        TextView physicianTextView = view.findViewById(R.id.text_item_sp_physician);
        TextView hospitalTextView = view.findViewById(R.id.text_item_sp_hospital);
        TextView dateTextView = view.findViewById(R.id.text_item_sp_date);

        int surgicalIdColumnIndex = cursor.getColumnIndex(ImsContract.SurgicalProceduresEntry._ID);
        int procedureColumnIndex = cursor.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_PROCEDURE);
        int physicianColumnIndex = cursor.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_PHYSICIAN);
        int hospitalColumnIndex = cursor.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_HOSPITAL);
        int dateColumnIndex = cursor.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES);

        String procedure = cursor.getString(procedureColumnIndex);
        String physician = cursor.getString(physicianColumnIndex);
        String hospital = cursor.getString(hospitalColumnIndex);
        String date = cursor.getString(dateColumnIndex);

        procedureTextView.setText(procedure);
        physicianTextView.setText(physician);
        hospitalTextView.setText(hospital);
        dateTextView.setText(date);
    }
}
