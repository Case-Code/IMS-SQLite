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
        return LayoutInflater.from(context).inflate(R.layout.fragment_invoices, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor data) {


    TextView    dateOfSvcTextView=view.findViewById(R.id.text_invoices_data_of_svc);
        TextView     invoiceDateTextView=view.findViewById(R.id.text_invoice_date);
        TextView  dateDueTextView=view.findViewById(R.id.text_invoices_date_due);


   EditText     billNameEditText = view.findViewById(R.id.edit_invoices_billtoname);
        EditText     billPhoneEditText = view.findViewById(R.id.edit_invoices_billtophone);
        EditText  billAddressEditText = view.findViewById(R.id.edit_invoices_bill_to_address);
        EditText  billFixEditText = view.findViewById(R.id.edit_invoices_bill_to_fax);
        EditText   billEmailEditText = view.findViewById(R.id.edit_invoices_bill_to_email);

        EditText svcIdEditText = view.findViewById(R.id.edit_invoices_svcid);
        EditText  medicalServicesEditText = view.findViewById(R.id.edit_invoices_medicalservis);
        EditText   medicationEditText = view.findViewById(R.id.edit_invoices_medication);
        EditText  costEditText = view.findViewById(R.id.edit_invoices_cost);

        EditText  subtotalEditText = view.findViewById(R.id.edit_invoices_sub_total);
        EditText taxRateEditText = view.findViewById(R.id.edit_invoices_tex_rate);
        EditText   totalTaxEditText = view.findViewById(R.id.edit_invoices_total_tax);
        EditText   otherEditText = view.findViewById(R.id.edit_invoices_other);
        EditText   totalEditText = view.findViewById(R.id.edit_invoices_total);

        EditText    questionsNameEditText = view.findViewById(R.id.edit_invoices_questions_name);
        EditText    questionEmailEditText = view.findViewById(R.id.edit_invoices_questions_email);
        EditText    questionsPhoneEditText = view.findViewById(R.id.edit_invoices_questions_phone);
        EditText   questionsWebEditText = view.findViewById(R.id.edit_invoices_questions_web);
        EditText     procedureEditText = view.findViewById(R.id.edit_invoices_procedure);



        int dateOfSvcColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_OF_SVC);
        int invoiceColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_INVOICE_DATE);
        int dateDueColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_DATE_DUE);
        int billToNameColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_NAME);
        int billToAddressColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_ADDRESS);
        int billToPhoneColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_PHONE);
        int billToFaxColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_FAX);
        int billToEmailColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_BILL_TO_EMAIL);
        int svcIdColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_SVC_ID);
        int medicalServicesColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICAL_SERVICES);
        int medicationColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_MEDICATION);
        int costColumnIndex = data.getColumnIndex(ImsContract.InvoicesEntry.COLUMN_COST);
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



    }
}
