package com.example.ims.adapter;

import android.content.ContentUris;
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

public class MedicineRegistryCursorAdapter extends CursorAdapter {

    public MedicineRegistryCursorAdapter(Context context, Cursor c) {
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

        int medicineNameColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_PRICE);


        String medicineName = cursor.getString(medicineNameColumnIndex);
        int quantity = cursor.getInt(quantityColumnIndex);
        int price = cursor.getInt(priceColumnIndex);

        itemPharmacyMedicineNameTextView.setText(medicineName);
        itemPharmacyQuantityTextView.setText("quantity: ".concat(String.valueOf(quantity)));
        itemPharmacyPrice.setText("$".concat(String.valueOf(price)));

        // color
        itemPharmacyMedicineNameTextView.setTextColor(context.getColor(R.color.colorPrimary));
        itemPharmacyQuantityTextView.setTextColor(context.getColor(R.color.background));
        itemPharmacyPrice.setTextColor(context.getColor(R.color.background));
    }
}
