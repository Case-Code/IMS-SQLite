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

    private EditText hrCurrentPhysicianNameEditText;
    private EditText hrCurrentPharmacyNameEditText;
    private EditText hrPharmacyPhoneEditText;
    private EditText capmMedicamentNameEditText;
    private EditText capmMedicationsPhysicianEditText;
    private EditText capmPurposeEditText;
    private EditText capmFreqEditText;
    private EditText capmDosageEditText;
    private EditText miIllnessEditText;
    private EditText miPhysicianEditText;
    private EditText miTreatmentNotesEditText;
    private EditText spProcedureEditText;
    private EditText spPhysicianEditText;
    private EditText spHospitalEditText;
    private EditText spNotesEditText;
    private EditText hrDoctorsPhoneEditText;

    private Button hrSaveButton;
    private Button capmMedicationSaveButton;
    private Button miAddButton;
    private Button spAddButton;
    private Button pvAddButton;

    private TextView hrDateOfTheLastUpdateTextView;
    private TextView hrPatientNameTextView;
    private TextView capmMedicationStartDateTextView;
    private TextView capmMedicationEndDateTextView;
    private TextView miStartDateTextView;
    private TextView miEndDateTextView;
    private TextView spDateTextView;
    private TextView pvTetanusTextView;
    private TextView pvInfluenzaVaccineTextView;
    private TextView pvZostavaxTextView;
    private TextView pvMeningitisTextView;
    private TextView pvYellowFeverTextView;
    private TextView pvPolioTextView;
    private TextView pvHistoryOfVaccinationTextView;


    private ListView majorIllnessesListView;
    private ListView surgicalProceduresListView;
    private ListView patientVaccinesListView;

    private Spinner pvTetanusSpinner;

    // ContentValues values;
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int mPatientId;

    public FragmentHealthRecord(int patientId) {
        // Required empty public constructor
        this.mPatientId = patientId;
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

    private void saveHealthRecord() {
        String currentPhysicianNameString = hrCurrentPhysicianNameEditText.getText().toString().trim();
        String currentPharmacyNameString = hrCurrentPharmacyNameEditText.getText().toString().trim();
        String pharmacyPhoneString = hrPharmacyPhoneEditText.getText().toString().trim();
        String doctorsPhoneString = hrDoctorsPhoneEditText.getText().toString().trim();
        String dateOfTheLastUpdateString = hrDateOfTheLastUpdateTextView.getText().toString().trim();

        if (TextUtils.isEmpty(currentPhysicianNameString)
                && TextUtils.isEmpty(currentPharmacyNameString)
                && TextUtils.isEmpty(pharmacyPhoneString)
                && TextUtils.isEmpty(doctorsPhoneString)
                && TextUtils.isEmpty(dateOfTheLastUpdateString)) {
            hrCurrentPharmacyNameEditText.setError("Please write to current pharmacy name");
            hrCurrentPhysicianNameEditText.setError("Please write to current physician name");
            hrPharmacyPhoneEditText.setError("Please write to pharmacy phone");
            return;
        }

        // Current physician name
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(currentPhysicianNameString)) {
            hrCurrentPhysicianNameEditText.setError("please write to current physician name");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME, currentPhysicianNameString);
        }

        // Current pharmacy name
        if (TextUtils.isEmpty(currentPharmacyNameString)) {
            hrCurrentPharmacyNameEditText.setError("please write to current pharmacy name");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME, currentPharmacyNameString);
        }

        // Pharmacy phone
        if (TextUtils.isEmpty(pharmacyPhoneString)) {
            hrPharmacyPhoneEditText.setError("please write to pharmacy phone");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PHARMACY_PHONE, pharmacyPhoneString);
        }

        // Doctors phone
        if (TextUtils.isEmpty(doctorsPhoneString)) {
            hrDateOfTheLastUpdateTextView.setError("please write to doctors phone");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DOCTORS_PHONE, doctorsPhoneString);
        }

        // Date of the last update
        if (TextUtils.isEmpty(dateOfTheLastUpdateString)) {
            hrDateOfTheLastUpdateTextView.setError("please write to date of the last update");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE, dateOfTheLastUpdateString);
        }

        // Patient id
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PATIENT_ID, mPatientId);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.HealthRecordEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_health_record_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_health_record_successful), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveCurrentAndPastMedications() {
        String medicamentNameString = capmMedicamentNameEditText.getText().toString().trim();
        String medicationsPhysicianString = capmMedicationsPhysicianEditText.getText().toString().trim();
        String PurposeString = capmPurposeEditText.getText().toString().trim();
        String FreqString = capmFreqEditText.getText().toString().trim();
        String DosageString = capmDosageEditText.getText().toString().trim();
        String MedicationStartDateString = capmMedicationStartDateTextView.getText().toString().trim();
        String MedicationEndDateString = capmMedicationEndDateTextView.getText().toString().trim();

        if (TextUtils.isEmpty(medicamentNameString)
                && TextUtils.isEmpty(medicationsPhysicianString)
                && TextUtils.isEmpty(PurposeString) &&
                TextUtils.isEmpty(FreqString) &&
                TextUtils.isEmpty(MedicationEndDateString) &&
                TextUtils.isEmpty(MedicationStartDateString) &&
                TextUtils.isEmpty(DosageString)

        ) {
            capmMedicamentNameEditText.setError("please write to name");
            capmMedicationsPhysicianEditText.setError("please write to name");
            capmPurposeEditText.setError("please write to name");
            return;
        }
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(MedicationStartDateString)) {
            capmMedicationStartDateTextView.setError("please return medication start date");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_START_DATE, MedicationStartDateString);
        }

        if (TextUtils.isEmpty(MedicationEndDateString)) {
            capmMedicationEndDateTextView.setError("please return medication end date");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_END_DATE, MedicationEndDateString);
        }

        if (TextUtils.isEmpty(medicamentNameString)) {
            capmMedicamentNameEditText.setError("please return medicament name");
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME, medicamentNameString);
        }

        if (TextUtils.isEmpty(medicationsPhysicianString)) {
            capmMedicationsPhysicianEditText.setError("please return medications physician");
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN, medicationsPhysicianString);
        }
        if (TextUtils.isEmpty(PurposeString)) {
            capmPurposeEditText.setError("please return write purpose");
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PURPOSE, PurposeString);
        }
        if (TextUtils.isEmpty(FreqString)) {
            capmFreqEditText.setError("please return write freq");
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_FREQ, FreqString);
        }
        if (TextUtils.isEmpty(DosageString)) {
            capmDosageEditText.setError("please return write dosage");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_DOSAGE, DosageString);
        }
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;

        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID, mPatientId);
        }
        Uri newUri =
                getContext().getContentResolver().insert(ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_Current_And_Past_Medications_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_Current_And_Past_Medications_successful), Toast.LENGTH_SHORT).show();
        }

    }

    // Add major illnesses
    private void addMajorIllnesses() {
        String illnessString = miIllnessEditText.getText().toString().trim();
        String physicianString = miPhysicianEditText.getText().toString().trim();
        String treatmentNotesString = miTreatmentNotesEditText.getText().toString().trim();
        String startDateString = miStartDateTextView.getText().toString().trim();
        String endDateString = miEndDateTextView.getText().toString().trim();

        ContentValues values = new ContentValues();

        // Illness
        if (TextUtils.isEmpty(illnessString)) {
            hrCurrentPhysicianNameEditText.setError("please write to illness");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS, illnessString);
        }

        // Physician
        if (TextUtils.isEmpty(physicianString)) {
            hrCurrentPhysicianNameEditText.setError("please write to physician");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN, physicianString);
        }

        // Treatment notes
        if (TextUtils.isEmpty(treatmentNotesString)) {
            hrCurrentPhysicianNameEditText.setError("please write to treatment notes");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_TREATMENT_NOTES, treatmentNotesString);
        }

        // Start date
        if (TextUtils.isEmpty(startDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write to start date");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_START_DATE, startDateString);
        }

        // End date
        if (TextUtils.isEmpty(endDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write to end date");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_END_DATE, endDateString);
        }

        // Patient id
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PATIENT_ID, mPatientId);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.MajorIllnessesEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_add_major_illnesses_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_add_major_illnesses_successful), Toast.LENGTH_SHORT).show();
        }
    }

    private void addSurgicalProcedures() {
        String ProcedureString = spProcedureEditText.getText().toString().trim();
        String PhysicianString = spPhysicianEditText.getText().toString().trim();
        String HospitalString = spHospitalEditText.getText().toString().trim();
        String NotesString = spNotesEditText.getText().toString().trim();
        String dateString = spDateTextView.getText().toString().trim();
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty(ProcedureString)) {
            spProcedureEditText.setError("please return write Procedure");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PROCEDURE, ProcedureString);

        }
        if (TextUtils.isEmpty(PhysicianString)) {
            spPhysicianEditText.setError("please return write Physician");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PHYSICIAN, PhysicianString);
        }
        if (TextUtils.isEmpty(HospitalString)) {
            spHospitalEditText.setError("please return write Hospital");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_HOSPITAL, HospitalString);
        }
        if (TextUtils.isEmpty(NotesString)) {
            spNotesEditText.setError("please return write note");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_NOTES, NotesString);
        }
        if (TextUtils.isEmpty(dateString)) {
            spDateTextView.setError("please return write date");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES, dateString);
        }
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PATIENT_ID, mPatientId);
        }
        Uri newUri = getContext().getContentResolver().insert(ImsContract.SurgicalProceduresEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_add_major_illnesses_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_add_major_illnesses_successful), Toast.LENGTH_SHORT).show();
        }


    }

    private void addPatientVaccines() {
        Spinner vaccinesSpinner;

        String vaccinesTETANUSString = pvTetanusTextView.getText().toString().trim();
        String vaccinesINFLUENZAVACCINEString = pvInfluenzaVaccineTextView.getText().toString().trim();
        String vaccinesZOSTAVAXString = pvZostavaxTextView.getText().toString().trim();
        String vaccinesMENINGITISString = pvMeningitisTextView.getText().toString().trim();
        String vaccinesYELLOWFEVERString = pvYellowFeverTextView.getText().toString().trim();
        String vaccinesPOLIOString = pvPolioTextView.getText().toString().trim();

        String vaccinesHistoryString = pvHistoryOfVaccinationTextView.getText().toString().trim();

/*
        ContentValues values = new ContentValues();
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }
        if (TextUtils.isEmpty()) {
            return; } else { values.put(ImsContract.PatientVaccinesEntry. , ); }*/

    }

    // initialization
    private void init() {
        // Health record
        hrPatientNameTextView = view.findViewById(R.id.text_hr_patient_name);
        hrCurrentPhysicianNameEditText = view.findViewById(R.id.edit_hr_current_physician_name);
        hrCurrentPharmacyNameEditText = view.findViewById(R.id.edit_hr_current_pharmacy_name);
        hrDateOfTheLastUpdateTextView = view.findViewById(R.id.text_hr_date_of_the_last_update);
        hrDoctorsPhoneEditText = view.findViewById(R.id.edit_hr_doctors_phone);
        hrPharmacyPhoneEditText = view.findViewById(R.id.edit_hr_pharmacy_phone);
        hrSaveButton = view.findViewById(R.id.button_hr_save);

        // Current and past medications
        capmMedicamentNameEditText = view.findViewById(R.id.edit_capm_medicament_name);
        capmDosageEditText = view.findViewById(R.id.edit_capm_dosage);
        capmFreqEditText = view.findViewById(R.id.edit_capm_freq);
        capmMedicationStartDateTextView = view.findViewById(R.id.text_capm_medications_start_date);
        capmMedicationEndDateTextView = view.findViewById(R.id.text_capm_medications_end_date);
        capmMedicationsPhysicianEditText = view.findViewById(R.id.edit_capm_medications_physician);
        capmPurposeEditText = view.findViewById(R.id.edit_capm_purpose);
        capmMedicationSaveButton = view.findViewById(R.id.button_capm_medications_save);

        // Major illnesses
        miIllnessEditText = view.findViewById(R.id.edit_mi_illness);
        miStartDateTextView = view.findViewById(R.id.text_mi_start_date);
        miEndDateTextView = view.findViewById(R.id.text_mi_end_date);
        miPhysicianEditText = view.findViewById(R.id.edit_mi_physician);
        miTreatmentNotesEditText = view.findViewById(R.id.edit_mi_treatment_notes);
        miAddButton = view.findViewById(R.id.button_mi_add);
        majorIllnessesListView = view.findViewById(R.id.list_major_illnesses);

        // Surgical procedures
        spProcedureEditText = view.findViewById(R.id.edit_sp_procedure);
        spPhysicianEditText = view.findViewById(R.id.edit_sp_physician);
        spHospitalEditText = view.findViewById(R.id.edit_sp_hospital);
        spDateTextView = view.findViewById(R.id.text_sp_date);
        spNotesEditText = view.findViewById(R.id.edit_sp_notes);
        spAddButton = view.findViewById(R.id.button_sp_add);
        surgicalProceduresListView = view.findViewById(R.id.list_surgical_procedures);

        // Patient vaccines
        pvTetanusTextView = view.findViewById(R.id.text_pv_tetanus);
        pvInfluenzaVaccineTextView = view.findViewById(R.id.text_pv_influenza_vaccine);
        pvZostavaxTextView = view.findViewById(R.id.text_pv_zostavax);
        pvMeningitisTextView = view.findViewById(R.id.text_pv_meningitis);
        pvYellowFeverTextView = view.findViewById(R.id.text_pv_yellow_fever);
        pvPolioTextView = view.findViewById(R.id.text_pv_polio);
        pvTetanusSpinner = view.findViewById(R.id.spinner_pv_tetanus);
        pvHistoryOfVaccinationTextView = view.findViewById(R.id.text_pv_history_of_vaccination);
        pvAddButton = view.findViewById(R.id.button_pv_add);
        patientVaccinesListView = view.findViewById(R.id.list_patient_vaccines);
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

        // initialization
        init();

        hrDateOfTheLastUpdateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        hrDateOfTheLastUpdateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        miStartDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        miStartDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        miEndDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        miEndDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        hrSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHealthRecord();
            }
        });

        miAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMajorIllnesses();
            }
        });

        capmMedicationStartDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        capmMedicationStartDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });
        capmMedicationEndDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        capmMedicationEndDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        capmMedicationSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCurrentAndPastMedications();

            }
        });

        spDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        spDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);

            }
        });
        spAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSurgicalProcedures();
            }
        });
        return view;
    }
}
