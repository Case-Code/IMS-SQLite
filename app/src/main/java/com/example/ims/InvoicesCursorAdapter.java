package com.example.ims;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ims.data.ImsContract;

public class InvoicesCursorAdapter extends CursorAdapter {

    private static final String TAG = InvoicesCursorAdapter.class.getSimpleName();

    public InvoicesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 12);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.list_item_invoices, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView svcIdTextView = view.findViewById(R.id.text_svc_id);
        TextView medicalServicesTextView = view.findViewById(R.id.text_medical_services);
        TextView medictionTextView = view.findViewById(R.id.text_mediction);
        TextView costTextView = view.findViewById(R.id.text_cost);

        final int idColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry._ID);
        int firstNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_FIRST_NAME);
        int lastNameColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_LAST_NAME);
        int phoneNumberColumnIndex = cursor.getColumnIndex(ImsContract.PatientEntry.COLUMN_PHONE_NUMBER);

        final int id = cursor.getInt(idColumnIndex);
        String firstName = cursor.getString(firstNameColumnIndex);
        String lastName = cursor.getString(lastNameColumnIndex);
        String phoneNumber = cursor.getString(phoneNumberColumnIndex);

        svcIdTextView.setText(id);
        medicalServicesTextView.setText(firstName);
        medictionTextView.setText(lastName);
        costTextView.setText(phoneNumber);


    }
}
