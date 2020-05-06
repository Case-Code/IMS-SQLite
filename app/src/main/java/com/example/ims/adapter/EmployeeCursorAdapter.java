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

public class EmployeeCursorAdapter extends CursorAdapter
{
    public EmployeeCursorAdapter(Context context, Cursor c)
    {
        super(context, c, 12);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return LayoutInflater.from(context).inflate(R.layout.item_employee_name, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView firstLastNameTextView = view.findViewById(R.id.text_employeename_fname);
        TextView lastLastNameTextView = view.findViewById(R.id.text_employeename_lname);

        int firstNameColumnIndex = cursor.getColumnIndex(ImsContract.EmployeesEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(ImsContract.EmployeesEntry.COLUMN_LAST_NAME);

        String firstName = cursor.getString(firstNameColumnIndex);
        String lastName = cursor.getString(lastNameColumnIndex);

        firstLastNameTextView.setText(firstName);
        lastLastNameTextView.setText(lastName);


    }
}
