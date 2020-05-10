package com.example.ims.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.ims.R;
import com.example.ims.Sale;

import java.util.ArrayList;

public class SaleAdapter extends ArrayAdapter<Sale> {

    private Context mContext;

    public SaleAdapter(@NonNull Context context, ArrayList<Sale> sales) {
        super(context, 0, sales);
        this.mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pharmacy, parent, false);
        }

        Sale currentSale = getItem(position);

        TextView itemPharmacyMedicineNameTextView = convertView.findViewById(R.id.text_itempharmacy_medicinename);
        TextView itemPharmacyQuantityTextView = convertView.findViewById(R.id.text_itempharmacy_quantity);
        TextView itemPharmacyPrice = convertView.findViewById(R.id.text_itempharmacy_price);

        assert currentSale != null;
        itemPharmacyMedicineNameTextView.setText(currentSale.getMedicineName());
        itemPharmacyQuantityTextView.setText("quantity: ".concat(String.valueOf(currentSale.getQuantity())));
        itemPharmacyPrice.setText("$".concat(String.valueOf(currentSale.getPrice())));

        // color
        itemPharmacyMedicineNameTextView.setTextColor(mContext.getColor(R.color.sale_first_text));
        itemPharmacyQuantityTextView.setTextColor(mContext.getColor(R.color.sale_second_text));
        itemPharmacyPrice.setTextColor(mContext.getColor(R.color.sale_second_text));

        return convertView;
    }
}
