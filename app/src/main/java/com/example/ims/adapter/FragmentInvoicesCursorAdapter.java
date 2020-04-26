package com.example.ims.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.loader.content.CursorLoader;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

public class FragmentInvoicesCursorAdapter extends CursorAdapter {
    private static final String TAG = FragmentInvoicesCursorAdapter.class.getSimpleName();

    public FragmentInvoicesCursorAdapter(Context context, Cursor c) {
        super(context, c,121);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_invoices, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor data) {
        //TODO: I am use IDS for look this IDS ;)
         TextView svcIdTextView=view.findViewById(R.id.text_iteminvoices_svcid);
         TextView medicalServicesTextView=view.findViewById(R.id.text_iteminvoices_medicalservices);
         TextView medicationTextView=view.findViewById(R.id.text_iteminvoices_mediction);
         TextView costTextView=view.findViewById(R.id.text_iteminvoices_cost);


         int svcIdColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SVC_ID);
         int medicalServicesColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES);
         int medicationColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICATION);
         int costColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_COST);

         String svcId = data.getString(svcIdColumnIndex);
         String medicalServices = data.getString(medicalServicesColumnIndex);
         String medication = data.getString(medicationColumnIndex);
         String cost = data.getString(costColumnIndex);

         svcIdTextView.setText(svcId);
         medicalServicesTextView.setText(medicalServices);
         medicationTextView.setText(medication);
         costTextView.setText(cost);

/*

        int dateOfSvcColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC);
        int invoiceColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE);
        int dateDueColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_DUE);
        int billToNameColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME);
        int billToAddressColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS);
        int billToPhoneColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE);
        int billToFaxColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX);
        int billToEmailColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL);

        int supTotalColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SUBTOTAL);
        int taxRateColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TAX_RATE);
        int totalTaxColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TOTAL_TAX);
        int otherColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_OTHER);
        int totalColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_TOTAL);
        int questionsNameColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_NAME);
        int questionsEmailColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_EMAIL);
        int questionsPhoneColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_PHONE);
        int questionsWebColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_QUESTIONS_WEB);
        int procedureColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_PROCEDURE);
        int patientIdColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_PATIENT_ID);


        String dateOfSvc = data.getString(dateOfSvcColumnIndex);
        String invoice = data.getString(invoiceColumnIndex);
        String dateDue = data.getString(dateDueColumnIndex);
        String billToName = data.getString(billToNameColumnIndex);
        String billToAddress = data.getString(billToAddressColumnIndex);
        String billToPhone = data.getString(billToPhoneColumnIndex);
        String billToFax = data.getString(billToFaxColumnIndex);
        String billToEmail = data.getString(billToEmailColumnIndex);
        String svcId = data.getString(svcIdColumnIndex);
        String medicalServices = data.getString(medicalServicesColumnIndex);
        String medication = data.getString(medicationColumnIndex);
        String cost = data.getString(costColumnIndex);
        String supTotal = data.getString(supTotalColumnIndex);
        String taxRate = data.getString(taxRateColumnIndex);
        String totalTax = data.getString(totalTaxColumnIndex);
        String other = data.getString(otherColumnIndex);
        String total = data.getString(totalColumnIndex);
        String questionsName = data.getString(questionsNameColumnIndex);
        String questionsEmail = data.getString(questionsEmailColumnIndex);
        String questionsPhone = data.getString(questionsPhoneColumnIndex);
        String questionsWeb = data.getString(questionsWebColumnIndex);
        String procedure = data.getString(procedureColumnIndex);
        String patientId = data.getString(patientIdColumnIndex);


        dateOfSvcTextView.setText(dateOfSvc);
        invoiceDateTextView.setText(invoice);
        dateDueTextView.setText(dateDue);

        billFixEditText.setText(billToFax);
        billNameEditText.setText(billToName);
        billAddressEditText.setText(billToAddress);
        billEmailEditText.setText(billToEmail);
        billPhoneEditText.setText(billToPhone);

        svcIdEditText.setText(svcId);
        medicalServicesEditText.setText(medicalServices);
        medicationEditText.setText(medication);
        costEditText.setText(cost);
        subtotalEditText.setText(supTotal);
        taxRateEditText.setText(taxRate);
        totalTaxEditText.setText(totalTax);
        otherEditText.setText(other);
        totalEditText.setText(total);

        questionsNameEditText.setText(questionsName);
        questionsPhoneEditText.setText(questionsPhone);
        questionEmailEditText.setText(questionsEmail);
        questionsWebEditText.setText(questionsWeb);
        procedureEditText.setText(procedure);
*/



    }
}
