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

public class InvoicesCursorAdapter extends CursorAdapter {

    private static final String TAG = InvoicesCursorAdapter.class.getSimpleName();

    public InvoicesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 12);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_invoices, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
      /*  TextView svcIdTextView = view.findViewById(R.id.text_svc_id);
        TextView medicalServicesTextView = view.findViewById(R.id.text_medical_services);
        TextView medictionTextView = view.findViewById(R.id.text_mediction);
        TextView costTextView = view.findViewById(R.id.text_cost);

        final int svcIdColumnIndex = cursor.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SVC_ID);
        int medicalServicesColumnIndex = cursor.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES);
        int medictionColumnIndex = cursor.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICATION);
        int costColumnIndex = cursor.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_COST);

        final int svcId = cursor.getInt(svcIdColumnIndex);
        String firstName = cursor.getString(medicalServicesColumnIndex);
        String lastName = cursor.getString(medictionColumnIndex);
        int costNumber = cursor.getInt(costColumnIndex);

        svcIdTextView.setText(svcId);
        medicalServicesTextView.setText(firstName);
        medictionTextView.setText(lastName);
        costTextView.setText(costNumber);*/
    }
}
