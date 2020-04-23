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

public class MajorIllnessesCursorAdapter extends CursorAdapter {
    public MajorIllnessesCursorAdapter(Context context, Cursor c) {
        super(context, c,11);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.item_major_illnesses, viewGroup, false);



    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView illnessesTextView=view.findViewById(R.id.text_item_mi_illnesses);
        TextView physicianTextView=view.findViewById(R.id.text_item_mi_physician);
        TextView startDateTextView=view.findViewById(R.id.text_item_mi_start_date);
        TextView endDateTextView=view.findViewById(R.id.text_item_mi_end_date);

        int   majorIdColumnIndex = cursor.getColumnIndex(ImsContract.MajorIllnessesEntry._ID);
        int illnessesColumnIndex= cursor.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS);
        int physicianColumnIndex= cursor.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN);
        int startDateColumnIndex= cursor.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_START_DATE);
        int endDateColumnIndex= cursor.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_END_DATE);

        String illnesses=cursor.getString(illnessesColumnIndex);
        String physician=cursor.getString(physicianColumnIndex);
        String startDate=cursor.getString(startDateColumnIndex);
        String endDate=cursor.getString(endDateColumnIndex);

        illnessesTextView.setText(illnesses);
        physicianTextView.setText(physician);
        startDateTextView.setText(startDate);
        endDateTextView.setText(endDate);
    }
}
