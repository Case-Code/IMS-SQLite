package com.example.ims.fragment;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.ims.R;
import com.example.ims.adapter.MedicineRegistryCursorAdapter;
import com.example.ims.data.ImsContract;

public class FragmentMedicineRegistry extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = FragmentMedicineRegistry.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // component view to fragment medicine registry
    private EditText qrEditText;
    private EditText medicineNameEditText;
    private EditText quantityEditText;
    private EditText priceEditText;
    private EditText searchEditText;

    private ImageButton medicinePlusImageButton;
    private ImageButton medicineMinusImageButton;

    private Button insertButton;
    private Button updateButton;

    private ListView medicineSalesListView;

    MedicineRegistryCursorAdapter mMedicineRegistryCursorAdapter;

    private Uri mCurrentMedicineUri = null;

    public static final int MEDICINE_REGISTRY_LOADER = 1;
    public static final int MEDICINE_UPDATE_LOADER = 2;

    private String mSearchText;

    private View view;
    private FragmentMedicineRegistry mContext;

    public void init() {

        // init component view to fragment medicine registry
        qrEditText = view.findViewById(R.id.edit_medicine_qr);
        medicineNameEditText = view.findViewById(R.id.edit_medicine_name);
        medicinePlusImageButton = view.findViewById(R.id.image_medicine_plus);
        quantityEditText = view.findViewById(R.id.edit_medicine_quantity);
        medicineMinusImageButton = view.findViewById(R.id.image_medicine_minus);
        priceEditText = view.findViewById(R.id.edit_medicine_price);
        insertButton = view.findViewById(R.id.button_medicine_insert);
        updateButton = view.findViewById(R.id.button_medicine_update);
        searchEditText = view.findViewById(R.id.edit_medicine_search);
        medicineSalesListView = view.findViewById(R.id.list_medicine_sales);
    }


    public FragmentMedicineRegistry() {
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
    public static FragmentMedicineRegistry newInstance(String param1, String param2) {
        FragmentMedicineRegistry fragment = new FragmentMedicineRegistry();
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
        view = inflater.inflate(R.layout.fragment_medicineregistry, container, false);

        // Initialization
        init();

        mMedicineRegistryCursorAdapter = new MedicineRegistryCursorAdapter(getContext(), null);
        medicineSalesListView.setAdapter(mMedicineRegistryCursorAdapter);

        // Medicine plus
        medicinePlusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityIncrement();
            }
        });

        // Medicine minus
        medicineMinusImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityDecrement();
            }
        });

        // Insert medicine
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add medicine
                addMedicine();
            }
        });

        // Update medicine
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Search medicine
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mSearchText = null;
                    getLoaderManager().restartLoader(MEDICINE_REGISTRY_LOADER, null, mContext);
                } else {
                    mSearchText = charSequence.toString();
                    getLoaderManager().restartLoader(MEDICINE_REGISTRY_LOADER, null, mContext);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        medicineSalesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mCurrentMedicineUri = ContentUris.withAppendedId(ImsContract.MedicineRegistryEntry.CONTENT_URI, id);
                getLoaderManager().restartLoader(MEDICINE_REGISTRY_LOADER, null, mContext);
            }
        });
        return view;
    }

    // Add medicine
    private void addMedicine() {

        String qr = qrEditText.getText().toString().trim();
        String medicineName = medicineNameEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();
        String price = priceEditText.getText().toString().trim();

        // Check if this is supposed to be a new patient
        // and check if all the fields in the editor are blank
        if (mCurrentMedicineUri == null
                && TextUtils.isEmpty(qr)
                && TextUtils.isEmpty(medicineName)
                && TextUtils.isEmpty(quantity)
                && TextUtils.isEmpty(price)) {
            return;
        }

        // Create a ContentValues object where column names are the keys,
        // and patient attributes from the editor are the values.
        ContentValues values = new ContentValues();

        // REQUIRED VALUES
        // Validation section
        // QR
        if (TextUtils.isEmpty(qr)) {
            Toast.makeText(getContext(), "QR is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.MedicineRegistryEntry.COLUMN_QR, qr);
        }

        // Medicine name
        if (TextUtils.isEmpty(medicineName)) {
            Toast.makeText(getContext(), "Medicine name is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME, medicineName);
        }

        // Quantity
        if (TextUtils.isEmpty(quantity)) {
            Toast.makeText(getContext(), "Quantity is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY, quantity);
        }

        // Price
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(getContext(), "Price is required", Toast.LENGTH_SHORT).show();
        } else {
            values.put(ImsContract.MedicineRegistryEntry.COLUMN_PRICE, price);
        }

        // Insert and update medicine
        if (mCurrentMedicineUri == null) {
            Uri newUri = getContext().getContentResolver().insert(ImsContract.MedicineRegistryEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(getContext(), getString(R.string.editor_insert_medicineregistry_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), getString(R.string.editor_insert_medicineregistry_successful), Toast.LENGTH_SHORT).show();

                // data reset
                qrEditText.setText(null);
                medicineNameEditText.setText(null);
                quantityEditText.setText(null);
                priceEditText.setText(null);
            }

        } else {
            int rowsAffected = getContext().getContentResolver().update(mCurrentMedicineUri, values, null, null);
            if (rowsAffected == 0) {
                Toast.makeText(getContext(), getString(R.string.editor_update_medicineregistry_failed), Toast.LENGTH_SHORT).show();
            } else {
                getLoaderManager().destroyLoader(MEDICINE_UPDATE_LOADER);
                mCurrentMedicineUri = null;
                Toast.makeText(getContext(), getString(R.string.editor_update_medicineregistry_successful), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Quantity increment
    private void quantityIncrement() {
        if (TextUtils.isEmpty(quantityEditText.getText().toString().trim())) {
            quantityEditText.setText("1");
        } else {
            String quantityString = quantityEditText.getText().toString();
            int quantity = Integer.parseInt(quantityString);
            quantityEditText.setText(String.valueOf(++quantity));
        }
    }

    // Quantity decrement
    private void quantityDecrement() {
        if (TextUtils.isEmpty(quantityEditText.getText().toString().trim())) {
            quantityEditText.setText("0");
        } else {
            String quantityString = quantityEditText.getText().toString();
            int quantity = Integer.parseInt(quantityString);
            if (quantity == 0) {
                quantity = 0;
                quantityEditText.setText(String.valueOf(quantity));
            } else {
                quantityEditText.setText(String.valueOf(--quantity));
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this;

        //get data in loader activity
        getLoaderManager().initLoader(MEDICINE_REGISTRY_LOADER, null, mContext);
    }

    // Get medicine search
    private CursorLoader getMedicineSearch(String searchText, Context context) {
        // Run query
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        String[] projection = {
                ImsContract.MedicineRegistryEntry._ID,
                ImsContract.MedicineRegistryEntry.COLUMN_QR,
                ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME,
                ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY,
                ImsContract.MedicineRegistryEntry.COLUMN_PRICE};

        String selection = ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME + " LIKE '" + searchText + "%' OR "
                + ImsContract.MedicineRegistryEntry.COLUMN_QR + " LIKE '" + searchText + "%'";

        return new CursorLoader(
                context,
                uri,
                projection,
                selection,
                null,
                null);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                ImsContract.MedicineRegistryEntry._ID,
                ImsContract.MedicineRegistryEntry.COLUMN_QR,
                ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME,
                ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY,
                ImsContract.MedicineRegistryEntry.COLUMN_PRICE};

        if (mCurrentMedicineUri == null && mSearchText == null) {
            return new CursorLoader(
                    this.getActivity(),
                    ImsContract.MedicineRegistryEntry.CONTENT_URI,
                    projection,
                    null,
                    null,
                    null);
        } else if (mCurrentMedicineUri == null) {
            return getMedicineSearch(mSearchText, this.getContext());
        } else {
            return new CursorLoader(
                    this.getActivity(),
                    mCurrentMedicineUri,
                    projection,
                    null,
                    null,
                    null);
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (mCurrentMedicineUri == null) {
            mMedicineRegistryCursorAdapter.swapCursor(data);
        } else {
            if (data == null && data.getCount() < 0) {
                return;
            }

            if (data.moveToFirst()) {
                // get row medicine name
                int qrColumnIndex = data.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_QR);
                int medicineNameColumnIndex = data.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME);
                int quantityColumnIndex = data.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_QUANTITY);
                int priceColumnIndex = data.getColumnIndex(ImsContract.MedicineRegistryEntry.COLUMN_PRICE);

                String qr = data.getString(qrColumnIndex);
                String medicineName = data.getString(medicineNameColumnIndex);
                String quantity = data.getString(quantityColumnIndex);
                String price = data.getString(priceColumnIndex);

                qrEditText.setText(qr);
                medicineNameEditText.setText(medicineName);
                quantityEditText.setText(quantity);
                priceEditText.setText(price);
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        if (mCurrentMedicineUri == null) {
            mMedicineRegistryCursorAdapter.swapCursor(null);
        } else {
            qrEditText.setText(null);
            medicineNameEditText.setText(null);
            quantityEditText.setText(null);
            priceEditText.setText(null);
        }
    }
}