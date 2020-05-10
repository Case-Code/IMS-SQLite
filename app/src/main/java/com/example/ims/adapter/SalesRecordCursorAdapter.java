package com.example.ims.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

public class SalesRecordCursorAdapter extends CursorAdapter {

    public SalesRecordCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_pharmacy, viewGroup, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView itemPharmacyMedicineNameTextView = view.findViewById(R.id.text_itempharmacy_medicinename);
        TextView itemPharmacyQuantityTextView = view.findViewById(R.id.text_itempharmacy_quantity);
        TextView itemPharmacyPrice = view.findViewById(R.id.text_itempharmacy_price);

        int salesRecordIdColumnIndex = cursor.getColumnIndex(ImsContract.SalesRecordEntry._ID);
        int medicineRegistryIdColumnIndex = cursor.getColumnIndex(ImsContract.SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID);
        int quantityColumnIndex = cursor.getColumnIndex(ImsContract.SalesRecordEntry.COLUMN_QUANTITY);
        int saleDateColumnIndex = cursor.getColumnIndex(ImsContract.SalesRecordEntry.COLUMN_SALE_DATE);
        int priceColumnIndex = cursor.getColumnIndex(ImsContract.SalesRecordEntry.COLUMN_PRICE);

        int salesRecordId = cursor.getInt(salesRecordIdColumnIndex);
        int medicineRegistryId = cursor.getInt(medicineRegistryIdColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);
        String saleDate = cursor.getString(saleDateColumnIndex);
        int price = cursor.getInt(priceColumnIndex);

        itemPharmacyMedicineNameTextView.setText(getMedicineName(medicineRegistryId, context));
        itemPharmacyQuantityTextView.setText("quantity: ".concat(String.valueOf(quantity)));
        itemPharmacyPrice.setText("$".concat(String.valueOf(price)));

        // color
        itemPharmacyMedicineNameTextView.setTextColor(context.getColor(R.color.salesrecord_first_text));
        itemPharmacyQuantityTextView.setTextColor(context.getColor(R.color.salesrecord_second_text));
        itemPharmacyPrice.setTextColor(context.getColor(R.color.salesrecord_second_text));
    }

    // Get medicine Name
    private String getMedicineName(int medicineRegistryId, Context context) {
        String medicineName = null;

        // Uri
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        // Column name
        String[] projection = {ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME};

        // Selection
        String selection = ImsContract.MedicineRegistryEntry._ID + " =" + medicineRegistryId;

        // SQL query
        @SuppressLint("Recycle")
        Cursor cursor = context.getContentResolver().query(
                uri,
                projection,
                selection,
                null,
                null);

        assert cursor != null;
        while (cursor.moveToNext()) {

            // Medicine name
            int medicineNameColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME);

            // Firs name and last name
            medicineName = cursor.getString(medicineNameColumnIndex);

            if (medicineName != null) {
                medicineName = medicineName;
            }
        }
        return medicineName;
    }
}
