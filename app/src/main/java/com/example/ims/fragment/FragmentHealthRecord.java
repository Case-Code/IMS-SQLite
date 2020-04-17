package com.example.ims.fragment;

import android.app.DatePickerDialog;
import android.content.ContentValues;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ims.R;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHealthRecord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHealthRecord extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public EditText patientPhysicianNameEditText;
    public EditText patientPharmacyNameEditText;
    public EditText patientPharmacyPhoneEditText;
    public EditText medicationMedicamentNameEditText;
    public EditText medicationPhysicianEditText;
    public EditText medicationPurposeEditText;
    public EditText medicationFREQEditText;
    public EditText medicationDosageEditText;
    public EditText majorIllnessEditText;
    public EditText majorPhysicianEditText;
    public EditText majorTreatmentNotesEditText;
    public EditText proceduresEditText;
    public EditText proceduresPhysicianEditText;
    public EditText proceduresHospitalEditText;
    public EditText proceduresNotesEditText;
    Button patientSaveButton;
    Button medicationSaveButton;
    Button majorAddButton;
    Button proceduresAddButton;
    Button vaccinesAddButton;
    EditText patientDoctorPhoneEditText;
    TextView patientDateLastUpdateTextView;
    TextView patientNameTextView;
    TextView medicationStartDateTextView;
    TextView medicationEndDateTextView;
    TextView majorStartDateTextView;
    TextView majorEndDateTextView;
    ListView majorListView;
    TextView proceduresDateSurgicalTextView;
    ListView proceduresListView;

    TextView vaccinesTETANUSTextView;
    TextView vaccinesINFLUENZAVACCINETextView;
    TextView vaccinesZOSTAVAXTextView;
    TextView vaccinesMENINGITISTextView;
    TextView vaccinesYELLOWFEVERTextView;
    TextView vaccinesPOLIOTextView;
    TextView vaccinesHistoryTextView;
    Spinner vaccinesSpinner;
    ListView vaccinesListView;
    // ContentValues values;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int id;


    public FragmentHealthRecord(int patientId) {
        // Required empty public constructor
        this.id = patientId;

    }

    public FragmentHealthRecord() {

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
        FragmentHealthRecord fragment = new FragmentHealthRecord(0);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void savePatient() {
        String patientPhysicianNameString = patientPhysicianNameEditText.getText().toString().trim();
        String patientPharmacyNameString = patientPharmacyNameEditText.getText().toString().trim();
        String patientPharmacyPhoneString = patientPharmacyPhoneEditText.getText().toString().trim();
        String patientDoctorPhoneString = patientDoctorPhoneEditText.getText().toString().trim();
        String patientDateLastUpdateString = patientDateLastUpdateTextView.getText().toString().trim();

        if (TextUtils.isEmpty(patientPhysicianNameString)
                && TextUtils.isEmpty(patientPharmacyNameString)
                && TextUtils.isEmpty(patientPharmacyPhoneString) &&
                TextUtils.isEmpty(patientDoctorPhoneString) &&
                TextUtils.isEmpty(patientDateLastUpdateString)

        ) {
            patientPharmacyNameEditText.setError("please write to name");
            patientPhysicianNameEditText.setError("please write to name");
            patientPharmacyPhoneEditText.setError("please write to name");

            return;
        }
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(patientPhysicianNameString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME, patientPhysicianNameString);
        }
        if (TextUtils.isEmpty(patientPharmacyNameString)) {
            patientPharmacyNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME, patientPharmacyNameString);
        }
        if (TextUtils.isEmpty(patientPharmacyPhoneString)) {
            patientPharmacyPhoneEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PHARMACY_PHONE, patientPharmacyPhoneString);
        }
        if (TextUtils.isEmpty(patientDoctorPhoneString)) {
            patientDateLastUpdateTextView.setError("please write to pharmacy");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DOCTORS_PHONE, patientDoctorPhoneString);
        }
        if (TextUtils.isEmpty(patientDateLastUpdateString)) {
            patientDateLastUpdateTextView.setError("please write to phone");
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE, patientDateLastUpdateString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            return;

        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PATIENT_ID, id);
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.InvoicesEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }

    }

    public void saveMedications() {

        String medicationMedicamentNameString = medicationMedicamentNameEditText.getText().toString().trim();
        String medicationPhysicianString = medicationPhysicianEditText.getText().toString().trim();
        String medicationPurposeString = medicationPurposeEditText.getText().toString().trim();
        String medicationFREQString = medicationFREQEditText.getText().toString().trim();
        String medicationDosageString = medicationDosageEditText.getText().toString().trim();
        String medicationStartDateString = medicationStartDateTextView.getText().toString().trim();
        String medicationEndDateString = medicationEndDateTextView.getText().toString().trim();

        if (TextUtils.isEmpty(medicationMedicamentNameString)
                && TextUtils.isEmpty(medicationPhysicianString)
                && TextUtils.isEmpty(medicationPurposeString) &&
                TextUtils.isEmpty(medicationFREQString) &&
                TextUtils.isEmpty(medicationEndDateString) &&
                TextUtils.isEmpty(medicationStartDateString) &&
                TextUtils.isEmpty(medicationDosageString)

        ) {
            medicationMedicamentNameEditText.setError("please write to name");
            medicationPhysicianEditText.setError("please write to name");
            medicationPurposeEditText.setError("please write to name");

            return;
        }
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(medicationStartDateString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_START_DATE, medicationStartDateString);
        }
        if (TextUtils.isEmpty(medicationEndDateString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_END_DATE, medicationEndDateString);
        }
        if (TextUtils.isEmpty(medicationMedicamentNameString)) {
            return;

        } else {
            //  values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN, medicationMedicamentNameString);
        }
        if (TextUtils.isEmpty(medicationPhysicianString)) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN, medicationPhysicianString);
        }
        if (TextUtils.isEmpty(medicationPurposeString)) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PURPOSE, medicationPurposeString);
        }
        if (TextUtils.isEmpty(medicationFREQString)) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_FREQ, medicationFREQString);
        }
        if (TextUtils.isEmpty(medicationDosageString)) {
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_DOSAGE, medicationDosageString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID, id);
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }

    }

    public void addMajor() {


        String majorIllnessString = majorIllnessEditText.getText().toString().trim();
        String majorPhysicianString = majorPhysicianEditText.getText().toString().trim();
        String majorTreatmentNotesString = majorTreatmentNotesEditText.getText().toString().trim();
        String majorStartDateString = majorStartDateTextView.getText().toString().trim();
        String majorEndDateString = majorEndDateTextView.getText().toString().trim();


        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(majorIllnessString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS, majorIllnessString);
        }
        if (TextUtils.isEmpty(majorPhysicianString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN, majorPhysicianString);
        }
        if (TextUtils.isEmpty(majorTreatmentNotesString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_TREATMENT_NOTES, majorTreatmentNotesString);
        }
        if (TextUtils.isEmpty(majorStartDateString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_START_DATE, majorStartDateString);
        }
        if (TextUtils.isEmpty(majorEndDateString)) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_END_DATE, majorEndDateString);
        }
        if (TextUtils.isEmpty(String.valueOf(id))) {
            patientPhysicianNameEditText.setError("please write to name");
            return;

        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PATIENT_ID, id);
        }

        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_patient_successful), Toast.LENGTH_SHORT).show();
        }

    }

    public void addProcedures() {


    }


    public void init() {

//        patientSaveButton = view.findViewById(R.id.button_health_record_patient_save);
//        medicationSaveButton = view.findViewById(R.id.button_health_record_medications_save);
//        majorAddButton = view.findViewById(R.id.button_health_record_major);
//        proceduresAddButton = view.findViewById(R.id.button_health_record_procedures_add);
//        vaccinesAddButton = view.findViewById(R.id.button_health_record_vaccines_add);
//
//
//        patientPhysicianNameEditText =
//                view.findViewById(R.id.edit_health_record_patient_physiscian_name);
//
//        patientPharmacyNameEditText =
//                view.findViewById(R.id.edit_health_record_patient_pharmacy_name);
//        patientPharmacyPhoneEditText =
//                view.findViewById(R.id.edit_health_record_patient_Pharmacy_phone);
//        patientDateLastUpdateTextView = view.findViewById(R.id.text_health_record_patient_date);
//        patientDoctorPhoneEditText =
//                view.findViewById(R.id.edit_health_record_patient_doctor_phone);
//        patientNameTextView = view.findViewById(R.id.text_health_record_patient_name);
//
//
//        medicationMedicamentNameEditText =
//                view.findViewById(R.id.edit_health_record__medications_medicament_name);
//        medicationPhysicianEditText =
//                view.findViewById(R.id.edit_health_record_medications_physiscian);
//        medicationPurposeEditText = view.findViewById(R.id.edit_health_record_medications_purpose);
//        medicationFREQEditText = view.findViewById(R.id.edit_health_record_medications_freq);
//        medicationDosageEditText = view.findViewById(R.id.edit_health_record_medications_dosage);
//        medicationStartDateTextView =
//                view.findViewById(R.id.text_health_record_medications_start_date);
//        medicationEndDateTextView = view.findViewById(R.id.text_health_record_medications_end_date);
//
//        majorIllnessEditText = view.findViewById(R.id.edit_health_record_Major_illness);
//        majorPhysicianEditText = view.findViewById(R.id.edit_health_record_Major_physician);
//        majorTreatmentNotesEditText = view.findViewById(R.id.edit_health_record_major_notes);
//        majorStartDateTextView = view.findViewById(R.id.text_health_record_Major_start_date);
//        majorEndDateTextView = view.findViewById(R.id.text_health_record_Major_end_date);
//        majorListView = view.findViewById(R.id.list_health_record_major);
//
//
//        proceduresEditText = view.findViewById(R.id.edit_health_record_procedure_e);
//        proceduresPhysicianEditText =
//                view.findViewById(R.id.edit_health_record_procedure_physician);
//        proceduresHospitalEditText = view.findViewById(R.id.edit_health_record_procedure_hospital);
//        proceduresNotesEditText = view.findViewById(R.id.edit_health_record_procedure_note);
//        proceduresDateSurgicalTextView = view.findViewById(R.id.text_health_record_procedure_date);
//        proceduresListView = view.findViewById(R.id.list_helath_record_procedure);
//
//        vaccinesTETANUSTextView = view.findViewById(R.id.text_health_record_vaccines_tetanus);
//        vaccinesINFLUENZAVACCINETextView =
//                view.findViewById(R.id.text_health_record_vaccines_vaccine);
//        vaccinesZOSTAVAXTextView = view.findViewById(R.id.text_health_record_vaccines_zostavax);
//        vaccinesMENINGITISTextView = view.findViewById(R.id.text_health_record_vaccines_meningitis);
//        vaccinesYELLOWFEVERTextView =
//                view.findViewById(R.id.text_health_record_vaccines_yellow_fever);
//        vaccinesPOLIOTextView = view.findViewById(R.id.text_health_record_vaccines_polio);
//        vaccinesHistoryTextView = view.findViewById(R.id.text_health_record_vaccines_history);
//        vaccinesSpinner = view.findViewById(R.id.spinner_health_record_vaccines);
//
//        vaccinesListView = view.findViewById(R.id.list_health_record_vaccines);
//

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
        view = inflater.inflate(R.layout.fragment_health_record, container, false);

//        init();
//        patientDateLastUpdateTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        month += 1;
//                        String date = month + "/" + dayOfMonth + "/" + year;
//                        patientDateLastUpdateTextView.setText(date);
//                    }
//                };
//                Utils.showDatePicker(getContext(), dateSetListener);
//            }
//        });

//        patientSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                savePatient();
//            }
//        });

//        majorAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


        return view;
    }


}
