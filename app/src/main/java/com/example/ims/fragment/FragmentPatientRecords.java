package com.example.ims.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.ims.R;
import com.example.ims.adapter.PatientProgressCursorAdapter;
import com.example.ims.data.ImsContract;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPatientRecords#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPatientRecords extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    Button patientRecordSaveButton;
    Button patientProgressAddButton;

    EditText patientIdEditText;
    EditText medicalRecordEditText;
    EditText physicianSignatureEditText;
    EditText progressNotesEditText;
    TextView appointmentDateTextView;
    TextView treatmentPlanDateTextView;
    TextView dateSignedTextView;
    TextView patientProgressDateTextView;

    ListView patientProgressListView;
        View view ;

    public Uri mCurrentPatientUri;
    private PatientProgressCursorAdapter mPatientProgressCursorAdapter;


    public void init (){
         patientRecordSaveButton=view.findViewById(R.id.button_patient_records_save);
         patientProgressAddButton=view.findViewById(R.id.button_patient_progress_add);

         patientIdEditText=view.findViewById(R.id.edit_patient_id);
         medicalRecordEditText=view.findViewById(R.id.edit_medical_record_id);
         physicianSignatureEditText=view.findViewById(R.id.edit_physician_signature);
         progressNotesEditText=view.findViewById(R.id.edit_progress_notes);
         appointmentDateTextView=view.findViewById(R.id.text_appointment_date);
         treatmentPlanDateTextView=view.findViewById(R.id.text_treatment_plan_date);
         dateSignedTextView=view.findViewById(R.id.text_date_signed);
         patientProgressDateTextView=view.findViewById(R.id.text_patient_progress_date);
        patientProgressListView=view.findViewById(R.id.list_patient_progress);

    }
    public void savePatientRecord(){

       String patientIdString=  patientIdEditText.getText().toString().trim();
        String medicalRecordString= medicalRecordEditText.getText().toString().trim();
        String physicianSignatureString=  physicianSignatureEditText.getText().toString().trim();
        String dateSignedString= dateSignedTextView.getText().toString().trim();

        String appointmentDateString=  appointmentDateTextView.getText().toString().trim();
        String treatmentPlanDateString=  treatmentPlanDateTextView.getText().toString().trim();
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(patientIdString)) {
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_PATIENT_ID , patientIdString);
        }
        if (TextUtils.isEmpty(medicalRecordString)) {
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID ,medicalRecordString );
        }
        if (TextUtils.isEmpty(physicianSignatureString)) {
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE ,physicianSignatureString );
        }
        if (TextUtils.isEmpty(dateSignedString)) {
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_DATE_SIGNED , dateSignedString);
        }
        if (TextUtils.isEmpty(appointmentDateString)) {
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE , appointmentDateString);
        }
        if (TextUtils.isEmpty(treatmentPlanDateString)) {
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE ,treatmentPlanDateString );
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.PatientRecordsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }
    }

    public void patientProgressAdd(){
        String progressNotesString=progressNotesEditText.getText().toString().trim();

      //  String patientProgressDateString= patientProgressDateTextView.getText().toString().trim();
        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(progressNotesString)) {

            return; } else { values.put(ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES , progressNotesString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {

            return; } else { values.put(ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID , id);
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.PatientProgressEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }


    }






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int id;

    public FragmentPatientRecords(int id) {
        // Required empty public constructor
        this.id=id;
    }
    public FragmentPatientRecords() {}


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         *     * @param param1 Parameter 1.

       * @param param2 Parameter 2.
    000 @return A new instance of fragment Fragment_pationt_view.
         */
    // TODO: Rename and change types and number of parameters
    public static FragmentPatientRecords newInstance(String param1, String param2) {
        FragmentPatientRecords fragment = new FragmentPatientRecords();
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
        mPatientProgressCursorAdapter= new PatientProgressCursorAdapter(getActivity() ,null);

        view= inflater.inflate(R.layout.fragment_patient_records, container, false);
        init();
            patientProgressListView.setAdapter(mPatientProgressCursorAdapter);

            patientProgressAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    patientProgressAdd();
                }
            });

return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String[] projection =
                { ImsContract.PatientProgressEntry._ID,
                        ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES, ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID,};

        this.id=id;
      /*  if(id==0){

        }else if(id!=0){

        }

        if(mCurrentPatientUri==null)
        {
            return new CursorLoader(this.getActivity(),
                    ImsContract.PatientProgressEntry.CONTENT_URI,
                    projection,
                    null,null,null
                    );
        }else {
            return new CursorLoader(
                this.getActivity(),
                    mCurrentPatientUri,
                    projection,
                    id+""
                    ,null,null
            );
        }*/




       if(id>0){
        return new CursorLoader(
                this.getActivity(),
                mCurrentPatientUri,
                projection,
                ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID+" like %"+id+"%"

                ,null,null
        );
      }else
       {
           return new CursorLoader(
                   this.getActivity(),
                   mCurrentPatientUri,
                   null,
                   null
                   ,null,null
           );

       }

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

        if(mCurrentPatientUri==null){
            mPatientProgressCursorAdapter.swapCursor(data);
       /* }else{
            if(data==null||data.getCount()<1){
                return;
            }
            if(data.moveToFirst()){
                int patientIdColumnIndex = data.getColumnIndex(ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID);
                int textProgressColumnIndex = data.getColumnIndex(ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES);

                String textProgress = data.getString(patientIdColumnIndex);
                String patientId = data.getString(textProgressColumnIndex);

            }*/

        }


    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if (mCurrentPatientUri == null) {
            mPatientProgressCursorAdapter.swapCursor(null);
        }
      /*  else{
            textProgressTextView.setText("");
            textDateTextView.setText("");

        }*/

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, null,  this);

        super.onActivityCreated(savedInstanceState);
    }



  /*  @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LiveData<ImsContract.PatientRecordsEntry> pa = new MutableLiveData<ImsContract.PatientRecordsEntry>();
        pa.observe(getViewLifecycleOwner(),);
    }*/
}
