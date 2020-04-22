package com.example.ims.fragment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.example.ims.adapter.PatientProgressCursorAdapter;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPatientRecords#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPatientRecords extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    Button ReceptionFprSaveButton;
    Button ReceptionFprAddButton;

    EditText ReceptionFprPatientIdEditText;
    EditText ReceptionFprMedicalRecordIdEditText;
    EditText ReceptionFprPhysicanSignatureEditText;
    EditText ReceptionFprProgressNotesEditText;
    TextView ReceptionFprNextAppointmentDateTextView;
    TextView ReceptionFprNextTreatmentPlanReviewDateTextView;
    TextView ReceptionFprDateSignedTextView;
    TextView ReceptionFprDateTextView;

    ListView ReceptionFprPatientProgressListView;
        View view ;

    public Uri mCurrentPatientUri;
    private PatientProgressCursorAdapter mPatientProgressCursorAdapter;


    public void init (){

        ReceptionFprPatientIdEditText =view.findViewById(R.id.edit_reception_fpr_patient_id);
        ReceptionFprMedicalRecordIdEditText =view.findViewById(R.id.edit_reception_fpr_medical_record_id);
        ReceptionFprPhysicanSignatureEditText =view.findViewById(R.id.edit_reception_fpr_physican_signature);
        ReceptionFprSaveButton =view.findViewById(R.id.button_reception_fpr_save);
        ReceptionFprNextAppointmentDateTextView =view.findViewById(R.id.text_reception_fpr_next_appointment_date);
        ReceptionFprNextTreatmentPlanReviewDateTextView =view.findViewById(R.id.text_reception_fpr_next_treatment_plan_review_date);
        ReceptionFprDateSignedTextView =view.findViewById(R.id.text_reception_fpr_date_signed);


        ReceptionFprDateTextView =view.findViewById(R.id.text_reception_fpr_date);
        ReceptionFprProgressNotesEditText =view.findViewById(R.id.edit_reception_fpr_progress_notes);
        ReceptionFprAddButton =view.findViewById(R.id.button_reception_fpr_add);
        ReceptionFprPatientProgressListView =view.findViewById(R.id.list_reception_fpr_patient_progress);

    }
    public void savePatientRecord(){

       String patientIdString=  ReceptionFprPatientIdEditText.getText().toString().trim();
        String medicalRecordIdString= ReceptionFprMedicalRecordIdEditText.getText().toString().trim();
        String physicianSignatureString=  ReceptionFprPhysicanSignatureEditText.getText().toString().trim();
        String dateSignedString= ReceptionFprDateSignedTextView.getText().toString().trim();

        String appointmentDateString=  ReceptionFprNextAppointmentDateTextView.getText().toString().trim();
        String treatmentPlanDateString=  ReceptionFprNextTreatmentPlanReviewDateTextView.getText().toString().trim();

        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(patientIdString)) {ReceptionFprPatientIdEditText.setError("please return a touch item to  patient Id");
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_PATIENT_ID , patientIdString);
        }
        if (TextUtils.isEmpty(medicalRecordIdString)) {ReceptionFprMedicalRecordIdEditText.setError("please return write to medical Record Id ");
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_MEDICAL_RECORD_ID ,medicalRecordIdString );
        }
        if (TextUtils.isEmpty(physicianSignatureString)) {ReceptionFprPhysicanSignatureEditText.setError("please return write to physician Signature ");
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_PHYSICIAN_SIGNATURE ,physicianSignatureString );
        }
        if (TextUtils.isEmpty(dateSignedString)) {ReceptionFprNextAppointmentDateTextView.setError("please return write to date Signed ");
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_DATE_SIGNED , dateSignedString);
        }
        if (TextUtils.isEmpty(appointmentDateString)) {ReceptionFprNextAppointmentDateTextView.setError("please return write to appointment Date ");
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_NEXT_APPOINTMENT_DATE , appointmentDateString);
        }
        if (TextUtils.isEmpty(treatmentPlanDateString)) {ReceptionFprNextTreatmentPlanReviewDateTextView.setError("please return write to  treatment Plan Date");
            return; } else { values.put(ImsContract.PatientRecordsEntry.COLUMN_NEXT_TREATMENT_PLAN_REVIEW_DATE ,treatmentPlanDateString );
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.PatientRecordsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_health_record_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_health_record_successful), Toast.LENGTH_SHORT).show();
        }
    }

    public void patientProgressAdd(){
        String progressNotesString= ReceptionFprProgressNotesEditText.getText().toString().trim();
        String dateString = ReceptionFprDateTextView.getText().toString().trim();

      //  String patientProgressDateString= patientProgressDateTextView.getText().toString().trim();
        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(progressNotesString)) {ReceptionFprProgressNotesEditText.setError("please return write to progress Notes ");
            return; } else { values.put(ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES , progressNotesString);
        }
        if (TextUtils.isEmpty(dateString)) {ReceptionFprDateTextView.setError("please return write to date");
            return; } else { values.put(ImsContract.PatientProgressEntry.COLUMN_DATE , dateString);
        }

        if (TextUtils.isEmpty(String.valueOf(idpatient))) {
            return; } else { values.put(ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID , idpatient); }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.PatientProgressEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.insert_progress_notes_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.insert_progress_notes_successful), Toast.LENGTH_SHORT).show();
        }


    }






    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int idpatient;

    public FragmentPatientRecords(int id) {
        // Required empty public constructor
        this.idpatient=id;
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
            ReceptionFprPatientProgressListView.setAdapter(mPatientProgressCursorAdapter);


        ReceptionFprNextAppointmentDateTextView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        ReceptionFprNextAppointmentDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);


            }
        });
        ReceptionFprNextTreatmentPlanReviewDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        ReceptionFprNextTreatmentPlanReviewDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);

            }
        });
        ReceptionFprDateSignedTextView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        ReceptionFprDateSignedTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        ReceptionFprSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePatientRecord();


            }
        });


        ReceptionFprDateTextView .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        ReceptionFprDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);

            }
        });
        ReceptionFprAddButton.setOnClickListener(new View.OnClickListener() {
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
CursorLoader c=null;
        String[] projection =
                { ImsContract.PatientProgressEntry._ID,
                        ImsContract.PatientProgressEntry.COLUMN_PROGRESS_NOTES,
                        ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID,};






       if(idpatient>0){
        c= new CursorLoader(
                this.getActivity(),
                ImsContract.PatientProgressEntry.CONTENT_URI,
                projection,
                ImsContract.PatientProgressEntry.COLUMN_PATIENT_ID+" ="+idpatient
                ,null,null
        );
        return c;
      }
       else {
           c= new CursorLoader(
                   this.getActivity(),
                   mCurrentPatientUri,
                   projection,
                        null
                   ,null,null
           );

           return c;
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
        getLoaderManager().initLoader(21, null,  this);


        super.onActivityCreated(savedInstanceState);
    }



  /*  @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LiveData<ImsContract.PatientRecordsEntry> pa = new MutableLiveData<ImsContract.PatientRecordsEntry>();
        pa.observe(getViewLifecycleOwner(),);
    }*/
}
