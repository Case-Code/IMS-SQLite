package com.example.ims.fragment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ims.R;
import com.example.ims.Sale;
import com.example.ims.adapter.SaleAdapter;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;

import java.util.ArrayList;
import java.util.Date;

public class FragmentSale extends Fragment {

    private static final String TAG = FragmentSale.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // add sale
    private EditText saleQrEditText;
    private ImageButton salePlusImageButton;
    private EditText saleQuantityEditText;
    private ImageButton saleMinusImageButton;
    private Button saleSaleButton, saleAddButton;
    private TextView saleTotalTextView;
    private EditText saleSearchEditText;

    //show sale
    private ListView saleListView;

    public static double total = 0.0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;

    public void init() {
        // add sale
        saleQrEditText = view.findViewById(R.id.edit_sale_qr);
        saleQuantityEditText = view.findViewById(R.id.edit_sale_quantity);
        saleSearchEditText = view.findViewById(R.id.edit_sale_search);

        salePlusImageButton = view.findViewById(R.id.imagebutton_sale_plus);
        saleMinusImageButton = view.findViewById(R.id.imagebutton_sale_minus);

        saleSaleButton = view.findViewById(R.id.button_sale_sale);
        saleAddButton = view.findViewById(R.id.button_sale_add);

        saleTotalTextView = view.findViewById(R.id.text_sale_total);

        saleListView = view.findViewById(R.id.list_sale_sale);
    }

    public FragmentSale() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment health_record.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentHealthRecord newInstance(String param1, String param2) {
        FragmentHealthRecord fragment = new FragmentHealthRecord();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sale, container, false);

        // Initialization
        init();

        salePlusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityIncrement();
            }
        });
        saleMinusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityDecrement();
            }
        });

        final ArrayList<Sale> saleArrayList = new ArrayList<Sale>();
        final SaleAdapter saleAdapter = new SaleAdapter(getActivity(), saleArrayList);

        // Add sale
        saleAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String saleQr = saleQrEditText.getText().toString();
                String saleQuantity = saleQuantityEditText.getText().toString();

                int qrInput = Integer.parseInt(saleQr);
                int quantityInput = Integer.parseInt(saleQuantity);

                int medicineRegistryId = getMedicineRegistryId(qrInput, getContext());
                String medicineName = getMedicineName(qrInput, getContext());
                int quantity = getQuantity(qrInput, getContext());
                double price = getPrice(qrInput, getContext());
                String saleDate = Utils.formatDate(new Date());

                // New price
                double newPrice = quantityInput * price;

                // Total price
                total += newPrice;
                saleTotalTextView.setText("Total: ".concat(String.valueOf(total)));

                // Adjust medicine quantity
                adjustMedicineQuantity(getContext(), qrInput, quantity, quantityInput);

                // Sale medicine
                saleMedicine(medicineRegistryId, quantityInput, saleDate, newPrice);

                saleArrayList.add(new Sale(medicineName, quantityInput, newPrice));
                saleListView.setAdapter(saleAdapter);

                // Reset data
                saleQrEditText.setText(null);
                saleQuantityEditText.setText(null);
            }
        });

        // Add sale
        saleSaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total = 0.0;
                saleTotalTextView.setText("Total: ".concat(String.valueOf(total)));
                saleArrayList.clear();
                saleListView.setAdapter(saleAdapter);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    // Quantity increment
    private void quantityIncrement() {
        if (TextUtils.isEmpty(saleQuantityEditText.getText().toString().trim())) {
            saleQuantityEditText.setText("1");
        } else {
            String quantityString = saleQuantityEditText.getText().toString();
            int quantity = Integer.parseInt(quantityString);
            saleQuantityEditText.setText(String.valueOf(++quantity));
        }
    }

    // Quantity decrement
    private void quantityDecrement() {
        if (TextUtils.isEmpty(saleQuantityEditText.getText().toString().trim())) {
            saleQuantityEditText.setText("0");
        } else {
            String quantityString = saleQuantityEditText.getText().toString();
            int quantity = Integer.parseInt(quantityString);
            if (quantity == 0) {
                quantity = 0;
                saleQuantityEditText.setText(String.valueOf(quantity));
            } else {
                saleQuantityEditText.setText(String.valueOf(--quantity));
            }
        }
    }

    /**
     * This method reduced product stock by 1
     *
     * @param context                   - Activity context
     * @param qrInput                   - qr used to update the stock of a specific quantity in the ListView
     * @param currentQuantityInPharmacy - current stock of that specific quantity
     */
    private void adjustMedicineQuantity(Context context, int qrInput, int currentQuantityInPharmacy, double quantityInput) {

        // Subtract 1 from current value if quantity of product >= 1
        double newQuantityValue = (currentQuantityInPharmacy >= 1) ? currentQuantityInPharmacy - quantityInput : 0;

        if (currentQuantityInPharmacy == 0) {
            Toast.makeText(context.getApplicationContext(), "The quantity is out of stock!", Toast.LENGTH_SHORT).show();
        }

        // Update table by using new value of quantity
        ContentValues contentValues = new ContentValues();

        contentValues.put(ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY, newQuantityValue);

        // Selection
        String selection = ImsContract.MedicineRegistryEntry.COLUMN_QR + " =" + qrInput;

        int numRowsUpdated = context.getContentResolver().update(
                ImsContract.MedicineRegistryEntry.CONTENT_URI,
                contentValues,
                selection,
                null);

        if (numRowsUpdated > 0) {
            // Show error message in Logs with info about pass update.
            Log.i(TAG, "Item has been sold");
        } else {
            Toast.makeText(context.getApplicationContext(), "No available quantity in stock", Toast.LENGTH_SHORT).show();
            // Show error message in Logs with info about fail update.
            Log.e(TAG, "Issue with upload value of quantity");
        }
    }

    // Sale medicine
    private void saleMedicine(int medicineRegistryId, int quantity, String saleData, double price) {

        // Check if this is supposed to be a new patient
        // and check if all the fields in the editor are blank
        if (medicineRegistryId == 0
                && quantity == 0
                && saleData == null
                && price == 0) {
            return;
        }

        // Create a ContentValues object where column names are the keys,
        // and patient attributes from the editor are the values.
        ContentValues values = new ContentValues();

        // REQUIRED VALUES
        // Validation section
        // Medicine registry id
        if (medicineRegistryId == 0) {
            Toast.makeText(getContext(), "Medicine registry id is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID, medicineRegistryId);
        }

        // Quantity
        if (quantity == 0) {
            Toast.makeText(getContext(), "Quantity is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.SalesRecordEntry.COLUMN_QUANTITY, quantity);
        }

        // Sale data
        if (saleData == null) {
            Toast.makeText(getContext(), "Sale data is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.SalesRecordEntry.COLUMN_SALE_DATE, saleData);
        }

        // Price
        if (price == 0) {
            Toast.makeText(getContext(), "Price is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.SalesRecordEntry.COLUMN_PRICE, price);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.SalesRecordEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }
    }

    // Get medicine registry id
    private int getMedicineRegistryId(int qr, Context context) {
        int medicineRegistryId = 0;

        // Uri
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        // Column name
        String[] projection = {ImsContract.MedicineRegistryEntry._ID};

        // Selection
        String selection = ImsContract.MedicineRegistryEntry.COLUMN_QR + " =" + qr;

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

            // Medicine registry id
            int medicineRegistryIdColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry._ID);

            medicineRegistryId = cursor.getInt(medicineRegistryIdColumnIndex);
        }
        return medicineRegistryId;
    }

    // Get medicine name
    private String getMedicineName(int qr, Context context) {
        String medicineName = null;

        // Uri
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        // Column name
        String[] projection = {ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME};

        // Selection
        String selection = ImsContract.MedicineRegistryEntry.COLUMN_QR + " =" + qr;

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

            medicineName = cursor.getString(medicineNameColumnIndex);
        }
        return medicineName;
    }

    // Get quantity
    private int getQuantity(int qr, Context context) {
        int quantity = 0;

        // Uri
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        // Column quantity
        String[] projection = {ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY};

        // Selection
        String selection = ImsContract.MedicineRegistryEntry.COLUMN_QR + " =" + qr;

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

            // Quantity
            int quantityColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY);

            quantity = cursor.getInt(quantityColumnIndex);
        }
        return quantity;
    }

    // Get quantity
    private double getPrice(int qr, Context context) {
        double price = 0;

        // Uri
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        // Column price
        String[] projection = {ImsContract.MedicineRegistryEntry.COLUMN_PRICE};

        // Selection
        String selection = ImsContract.MedicineRegistryEntry.COLUMN_QR + " =" + qr;

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

            // Price
            int priceColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_PRICE);

            price = cursor.getInt(priceColumnIndex);
        }
        return price;
    }


}
