package com.example.ims.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.ims.R;
import com.example.ims.adapter.SalesRecordCursorAdapter;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;

import java.util.Calendar;
import java.util.logging.Logger;

public class FragmentSalesRecord extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FragmentSalesRecord.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //component date sales record
    private TextView dateYearTextView;
    private TextView dateMonthDayTextView;
    private EditText searchEditText;
    private ImageButton dateImageButton;
    private ListView salesRecordListView;

    private SalesRecordCursorAdapter salesRecordCursorAdapter;

    private String datePacker = null;
    private String mSearchText;

    private static final int SALES_RECORD_LOADER = 1;

    private View view;
    private FragmentSalesRecord mContext;

    public void init() {
        //component date sales record
        dateYearTextView = view.findViewById(R.id.text_salesrecord_dateyear);
        dateMonthDayTextView = view.findViewById(R.id.text_salesrecord__datemonth);
        searchEditText = view.findViewById(R.id.edit_salesrecord_search);
        dateImageButton = view.findViewById(R.id.image_salesrecord_date);
        salesRecordListView = view.findViewById(R.id.list_salesrecord);
    }

    public FragmentSalesRecord() {
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
        view = inflater.inflate(R.layout.fragment_salesrecord, container, false);

        // Initialization
        init();

        salesRecordCursorAdapter = new SalesRecordCursorAdapter(getContext(), null);
        salesRecordListView.setAdapter(salesRecordCursorAdapter);

        dateImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        datePacker = Utils.formatDate(calendar.getTime());

                        getLoaderManager().restartLoader(SALES_RECORD_LOADER, null, mContext);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
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
                    getLoaderManager().restartLoader(SALES_RECORD_LOADER, null, mContext);
                } else {
                    mSearchText = charSequence.toString();
                    getLoaderManager().restartLoader(SALES_RECORD_LOADER, null, mContext);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = this;

        //get data in loader activity
        getLoaderManager().initLoader(SALES_RECORD_LOADER, null, mContext);
    }

    // Get get doctor search clinic : getContacts
    private CursorLoader getMedicine(String saleDate, Context context) {

        // Run query
        Uri uri = ImsContract.SalesRecordEntry.CONTENT_URI;

        String[] projection = {
                ImsContract.SalesRecordEntry._ID,
                ImsContract.SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID,
                ImsContract.SalesRecordEntry.COLUMN_QUANTITY,
                ImsContract.SalesRecordEntry.COLUMN_PRICE,
                ImsContract.SalesRecordEntry.COLUMN_SALE_DATE};

        String selection = ImsContract.SalesRecordEntry.COLUMN_SALE_DATE + " LIKE '" + saleDate + "%'";

        return new CursorLoader(context,
                uri,
                projection,
                selection,
                null,
                null);
    }

    // Get medicine search
    private CursorLoader getMedicineSearch(String searchText, Context context) {

        // Run query
        Uri uri = ImsContract.SalesRecordEntry.CONTENT_URI;

        String[] projection = {
                ImsContract.SalesRecordEntry._ID,
                ImsContract.SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID,
                ImsContract.SalesRecordEntry.COLUMN_QUANTITY,
                ImsContract.SalesRecordEntry.COLUMN_PRICE,
                ImsContract.SalesRecordEntry.COLUMN_SALE_DATE};

        String selection = ImsContract.SalesRecordEntry.COLUMN_MEDICINE_REGISTRY_ID + " LIKE '" + getMedicineRegistryId(searchText, getContext()) + "%'";

        return new CursorLoader(
                context,
                uri,
                projection,
                selection,
                null,
                null);
    }

    // Get medicine registry id
    private int getMedicineRegistryId(String searchText, Context context) {
        int medicineRegistryId = 0;

        // Uri
        Uri uri = ImsContract.MedicineRegistryEntry.CONTENT_URI;

        // Column name
        String[] projection = {ImsContract.MedicineRegistryEntry._ID};

        // Selection
        String selection = ImsContract.MedicineRegistryEntry.COLUMN_MEDICINE_NAME + " LIKE '" + searchText + "%' OR "
                + ImsContract.MedicineRegistryEntry.COLUMN_QR + " LIKE '" + searchText + "%'";

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

            // Medicine registry
            int medicineRegistryColumnIndex = cursor.getColumnIndex(ImsContract.MedicineRegistryEntry._ID);

            // Firs name and last name
            medicineRegistryId = cursor.getInt(medicineRegistryColumnIndex);
        }
        return medicineRegistryId;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        if (mSearchText == null) {
            return getMedicine(datePacker, getContext());
        } else {
            return getMedicineSearch(mSearchText, getContext());
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        if (datePacker == null) {
            salesRecordCursorAdapter.swapCursor(null);
        } else {
            if (data == null && data.getCount() < 0) {
                Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();
                return;
            } else if (data.getCount() <= 0) {
                Toast.makeText(getContext(), "There is no data", Toast.LENGTH_SHORT).show();
            }

            salesRecordCursorAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
    }
}
