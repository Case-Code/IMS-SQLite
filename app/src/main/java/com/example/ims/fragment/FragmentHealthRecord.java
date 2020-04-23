package com.example.ims.fragment;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.ims.R;
import com.example.ims.data.ImsContract;
import com.example.logutil.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHealthRecord#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHealthRecord extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Uri mCurrentHealthRecordUri;

    private EditText hrCurrentPhysicianNameEditText;
    private EditText hrCurrentPharmacyNameEditText;
    private EditText hrPharmacyPhoneEditText;
    private EditText capmMedicamentNameEditText;
    private EditText capmPhysicianEditText;
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
    private Button capmSaveButton;
    private Button miAddButton;
    private Button spAddButton;
    private Button pvAddButton;

    private TextView hrDateOfTheLastUpdateTextView;
    private TextView hrPatientNameTextView;
    private TextView capmStartDateTextView;
    private TextView capmEndDateTextView;
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

    private Spinner pvNamesOfVaccinationSpinner;
    public static int pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_UNKNOWN;

    private static final int HR_LOADER = 120;
    private static final int CAPM_LOADER = 121;
    private static final int MI_LOADER = 122;
    private static final int PV_LOADER = 123;
    private static final int SP_LOADER = 124;

    private Uri mHealthRecordUri;
    private Uri mCurrentAndPastMedicationsUri;
    private Uri mMajorIllnessesUri;
    private Uri mPatientVaccinesUri;
    private Uri mSurgicalProceduresUri;

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

    // Save health record
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
            hrCurrentPhysicianNameEditText.setError("please write current physician name");
            hrCurrentPharmacyNameEditText.setError("please write current pharmacy name");
            hrPharmacyPhoneEditText.setError("please write pharmacy phone");
            hrDoctorsPhoneEditText.setError("please write doctors phone");
            hrDateOfTheLastUpdateTextView.setError("please write date of the last update");
            return;
        }

        ContentValues values = new ContentValues();

        // Current physician name
        if (TextUtils.isEmpty(currentPhysicianNameString)) {
            hrCurrentPhysicianNameEditText.setError("please write current physician name");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME, currentPhysicianNameString);
        }

        // Current pharmacy name
        if (TextUtils.isEmpty(currentPharmacyNameString)) {
            hrCurrentPharmacyNameEditText.setError("please write current pharmacy name");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME, currentPharmacyNameString);
        }

        // Pharmacy phone
        if (TextUtils.isEmpty(pharmacyPhoneString)) {
            hrPharmacyPhoneEditText.setError("please write pharmacy phone");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_PHARMACY_PHONE, pharmacyPhoneString);
        }

        // Doctors phone
        if (TextUtils.isEmpty(doctorsPhoneString)) {
            hrDateOfTheLastUpdateTextView.setError("please write doctors phone");
            return;
        } else {
            values.put(ImsContract.HealthRecordEntry.COLUMN_DOCTORS_PHONE, doctorsPhoneString);
        }

        // Date of the last update
        if (TextUtils.isEmpty(dateOfTheLastUpdateString)) {
            hrDateOfTheLastUpdateTextView.setError("please write date of the last update");
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

    // Save current and past medications
    private void saveCurrentAndPastMedications() {
        String medicamentNameString = capmMedicamentNameEditText.getText().toString().trim();
        String dosageString = capmDosageEditText.getText().toString().trim();
        String freqString = capmFreqEditText.getText().toString().trim();
        String startDateString = capmStartDateTextView.getText().toString().trim();
        String endDateString = capmEndDateTextView.getText().toString().trim();
        String physicianString = capmPhysicianEditText.getText().toString().trim();
        String purposeString = capmPurposeEditText.getText().toString().trim();

        if (TextUtils.isEmpty(medicamentNameString)
                && TextUtils.isEmpty(dosageString)
                && TextUtils.isEmpty(freqString)
                && TextUtils.isEmpty(startDateString)
                && TextUtils.isEmpty(endDateString)
                && TextUtils.isEmpty(physicianString)
                && TextUtils.isEmpty(purposeString)
        ) {
            capmMedicamentNameEditText.setError("please write medicament name");
            capmDosageEditText.setError("please write dosage");
            capmFreqEditText.setError("please write FREQ.");
            capmStartDateTextView.setError("please write start date");
            capmEndDateTextView.setError("please write end date");
            capmPhysicianEditText.setError("please write physician");
            capmPurposeEditText.setError("please write purpose");
            return;
        }

        ContentValues values = new ContentValues();

        // Medicament name
        if (TextUtils.isEmpty(medicamentNameString)) {
            capmMedicamentNameEditText.setError("please write medicament name");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME, medicamentNameString);
        }

        // Dosage
        if (TextUtils.isEmpty(dosageString)) {
            capmDosageEditText.setError("please write dosage");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_DOSAGE, dosageString);
        }

        // FREQ.
        if (TextUtils.isEmpty(freqString)) {
            capmFreqEditText.setError("please write FREQ.");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_FREQ, freqString);
        }

        // Start date
        if (TextUtils.isEmpty(startDateString)) {
            capmStartDateTextView.setError("please write start date");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_START_DATE, startDateString);
        }

        // End date
        if (TextUtils.isEmpty(endDateString)) {
            capmEndDateTextView.setError("please write end date");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_END_DATE, endDateString);
        }

        // Physician
        if (TextUtils.isEmpty(physicianString)) {
            capmPhysicianEditText.setError("please write physician");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN, physicianString);
        }

        // Purpose
        if (TextUtils.isEmpty(purposeString)) {
            capmPurposeEditText.setError("please write purpose");
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PURPOSE, purposeString);
        }

        // Patient id
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID, mPatientId);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI, values);
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

        if (TextUtils.isEmpty(illnessString)
                && TextUtils.isEmpty(physicianString)
                && TextUtils.isEmpty(treatmentNotesString)
                && TextUtils.isEmpty(startDateString)
                && TextUtils.isEmpty(endDateString)
        ) {
            miIllnessEditText.setError("please write illness");
            miPhysicianEditText.setError("please write physician");
            miTreatmentNotesEditText.setError("please write treatment notes");
            miStartDateTextView.setError("please write start date");
            miEndDateTextView.setError("please write end date");
            return;
        }

        ContentValues values = new ContentValues();

        // Illness
        if (TextUtils.isEmpty(illnessString)) {
            hrCurrentPhysicianNameEditText.setError("please write illness");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS, illnessString);
        }

        // Physician
        if (TextUtils.isEmpty(physicianString)) {
            hrCurrentPhysicianNameEditText.setError("please write physician");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN, physicianString);
        }

        // Treatment notes
        if (TextUtils.isEmpty(treatmentNotesString)) {
            hrCurrentPhysicianNameEditText.setError("please write treatment notes");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_TREATMENT_NOTES, treatmentNotesString);
        }

        // Start date
        if (TextUtils.isEmpty(startDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write start date");
            return;
        } else {
            values.put(ImsContract.MajorIllnessesEntry.COLUMN_START_DATE, startDateString);
        }

        // End date
        if (TextUtils.isEmpty(endDateString)) {
            hrCurrentPhysicianNameEditText.setError("please write end date");
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

    // Add surgical procedures
    private void addSurgicalProcedures() {
        String procedureString = spProcedureEditText.getText().toString().trim();
        String physicianString = spPhysicianEditText.getText().toString().trim();
        String hospitalString = spHospitalEditText.getText().toString().trim();
        String dateString = spDateTextView.getText().toString().trim();
        String notesString = spNotesEditText.getText().toString().trim();

        if (TextUtils.isEmpty(procedureString)
                && TextUtils.isEmpty(physicianString)
                && TextUtils.isEmpty(hospitalString)
                && TextUtils.isEmpty(dateString)
                && TextUtils.isEmpty(notesString)
        ) {
            spProcedureEditText.setError("please write Procedure");
            spPhysicianEditText.setError("please write physician");
            spHospitalEditText.setError("please write hospital");
            spDateTextView.setError("please write date");
            spNotesEditText.setError("please write note");
            return;
        }

        ContentValues values = new ContentValues();

        // Procedure
        if (TextUtils.isEmpty(procedureString)) {
            spProcedureEditText.setError("please write Procedure");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PROCEDURE, procedureString);
        }

        // physician
        if (TextUtils.isEmpty(physicianString)) {
            spPhysicianEditText.setError("please write physician");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PHYSICIAN, physicianString);
        }

        // Hospital
        if (TextUtils.isEmpty(hospitalString)) {
            spHospitalEditText.setError("please write hospital");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_HOSPITAL, hospitalString);
        }

        // Date
        if (TextUtils.isEmpty(dateString)) {
            spDateTextView.setError("please write date");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES, dateString);
        }

        // Note
        if (TextUtils.isEmpty(notesString)) {
            spNotesEditText.setError("please write note");
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_NOTES, notesString);
        }

        // Patient id
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.SurgicalProceduresEntry.COLUMN_PATIENT_ID, mPatientId);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.SurgicalProceduresEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_Surgical_Procedures_record_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_Surgical_Procedures_successful), Toast.LENGTH_SHORT).show();
        }
    }

    // Add basic patient vaccines
    private void addBasicPatientVaccines() {
        String tetanusString = pvTetanusTextView.getText().toString().trim();
        String influenzaVaccineString = pvInfluenzaVaccineTextView.getText().toString().trim();
        String zostavaxString = pvZostavaxTextView.getText().toString().trim();
        String meningitisString = pvMeningitisTextView.getText().toString().trim();
        String yellowFeverString = pvYellowFeverTextView.getText().toString().trim();
        String polioString = pvPolioTextView.getText().toString().trim();
        String historyOfVaccinationString = pvHistoryOfVaccinationTextView.getText().toString().trim();

        if (TextUtils.isEmpty(historyOfVaccinationString)) {
            pvHistoryOfVaccinationTextView.setError("please write history Of Vaccination");
            return;
        }

        ContentValues values = new ContentValues();

        // Tetanus
        values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_TETANUS, tetanusString);

        // Influenza vaccine
        values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_INFLUENZA_VACCINE, influenzaVaccineString);

        // ZOSTAVAX
        values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_ZOSTAVAX, zostavaxString);

        // Meningitis
        values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_MENINGITIS, meningitisString);

        // Yellow fever
        values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_YELLOW_FEVER, yellowFeverString);

        // Polio
        values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_POLIO, polioString);

        // Patient id
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.BasicPatientVaccinesEntry.COLUMN_PATIENT_ID, mPatientId);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.BasicPatientVaccinesEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_basic_patient_vaccines_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_basic_patient_vaccines_successful), Toast.LENGTH_SHORT).show();
        }
    }

    // Add other patient vaccines
    private void addOtherPatientVaccines() {
        String historyOfVaccinationString = pvHistoryOfVaccinationTextView.getText().toString().trim();

        if (TextUtils.isEmpty(historyOfVaccinationString)) {
            pvHistoryOfVaccinationTextView.setError("please write history Of Vaccination");
            return;
        }

        ContentValues values = new ContentValues();

        // Name of vaccination
        if (ImsContract.OtherPatientVaccinesEntry.isValidNamesOfVaccination(pvNamesOfVaccination)) {
            Log.i(getTag(), "Names of vaccination: " + pvNamesOfVaccination);
            Log.e("mahmoud iin ","yes sure man");
            values.put(ImsContract.OtherPatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION, pvNamesOfVaccination);

        } else {
            pvPolioTextView.setError("Choose name of vaccination");
            return;
        }

        // History of vaccination
        if (TextUtils.isEmpty(historyOfVaccinationString)) {
            pvHistoryOfVaccinationTextView.setError("please write history Of Vaccination");
            return;
        } else {
            Log.i(getTag(), "History of vaccination: " + historyOfVaccinationString);
            values.put(ImsContract.OtherPatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION, historyOfVaccinationString);
        }

        // Patient id
        if (TextUtils.isEmpty(String.valueOf(mPatientId))) {
            return;
        } else {
            values.put(ImsContract.OtherPatientVaccinesEntry.COLUMN_PATIENT_ID, mPatientId);
        }

        Uri newUri = getContext().getContentResolver().insert(ImsContract.OtherPatientVaccinesEntry.CONTENT_URI, values);
        if (newUri == null) {
            Toast.makeText(getContext(), getString(R.string.editor_insert_other_patient_vaccines_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.editor_insert_other_patient_vaccines_successful), Toast.LENGTH_SHORT).show();
        }
    }

    // Setup spinner name of vaccination
    public void setupSpinnerNameOfVaccination(Context context) {
        ArrayAdapter nameOfVaccinationSpinnerAdapter = ArrayAdapter.createFromResource(
                context,
                R.array.array_name_of_vaccination_options,
                android.R.layout.simple_spinner_item);

        nameOfVaccinationSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        pvNamesOfVaccinationSpinner.setAdapter(nameOfVaccinationSpinnerAdapter);
        pvNamesOfVaccinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Diphtheria and tetanus (DT) vaccines")) { // TODO chane the text
                        pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_D_T;
                    } else if (selection.equals("Diphtheria, tetanus, and pertussis (DTaP) vaccines")) {
                        pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_D_T_A_P;
                    } else if (selection.equals("Tetanus and diphtheria (Td) vaccines")) {
                        pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_T_D;
                    } else if (selection.equals("Tetanus, diphtheria, and pertussis (Tdap) vaccines")) {
                        pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_T_DAP;
                    } else {
                        pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_UNKNOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pvNamesOfVaccination = ImsContract.OtherPatientVaccinesEntry.TETANUS_UNKNOWN;
            }
        });
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
        capmStartDateTextView = view.findViewById(R.id.text_capm_start_date);
        capmEndDateTextView = view.findViewById(R.id.text_capm_end_date);
        capmPhysicianEditText = view.findViewById(R.id.edit_capm_physician);
        capmPurposeEditText = view.findViewById(R.id.edit_capm_purpose);
        capmSaveButton = view.findViewById(R.id.button_capm_save);

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
        pvNamesOfVaccinationSpinner = view.findViewById(R.id.spinner_pv_names_of_vaccination);
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

        // H.R. date of the last update
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

        // M.I. start date
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

        // M.I. end date
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

        // H.R. save
        hrSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveHealthRecord();
            }
        });

        // M.I. add
        miAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMajorIllnesses();
            }
        });

        // C.A.P.M. start date
        capmStartDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        capmStartDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // C.A.P.M. End date
        capmEndDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        capmEndDateTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // C.A.P.M. save
        capmSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCurrentAndPastMedications();

            }
        });

        // S.P date
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

        // S.P. add
        spAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSurgicalProcedures();
            }
        });

        // P.V. tetanus: date in patient vaccines
        pvTetanusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvTetanusTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // P.V. influenza vaccine: date in patient vaccines
        pvInfluenzaVaccineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvInfluenzaVaccineTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // P.V. ZOSTAVAX: date in patient vaccines
        pvZostavaxTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvZostavaxTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // P.V. meningitis: date in patient vaccines
        pvMeningitisTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvMeningitisTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // P.V. yellow fever: date in patient vaccines
        pvYellowFeverTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvYellowFeverTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);

            }
        });

        // P.V. polio: date in patient vaccines
        pvPolioTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvPolioTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // P.V. history of vaccination: date in patient vaccines
        pvHistoryOfVaccinationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month += 1;
                        String date = month + "/" + dayOfMonth + "/" + year;
                        pvHistoryOfVaccinationTextView.setText(date);
                    }
                };
                Utils.showDatePicker(getContext(), dateSetListener);
            }
        });

        // P.V. add
        pvAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOtherPatientVaccines();
            }
        });

        setupSpinnerNameOfVaccination(getContext());
        return view;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
//        CursorLoader c = null;
//
//        if (id == HR_LOADER) {
//            String[] projection = {
//                    ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME,
//                    ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME,
//                    ImsContract.HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE,
//                    ImsContract.HealthRecordEntry.COLUMN_DOCTORS_PHONE,
//                    ImsContract.HealthRecordEntry.COLUMN_PHARMACY_PHONE
//            };
//
//            if (mPatientId > 0) {
//                return new CursorLoader(
//                        this.getActivity(),
//                        ImsContract.HealthRecordEntry.CONTENT_URI,
//                        projection,
//                        ImsContract.HealthRecordEntry.COLUMN_PATIENT_ID + " =" + mPatientId,
//                        null, null
//                );
//            } else {
//                return new CursorLoader(this.getActivity(),
//                        mHealthRecordUri,
//                        projection,
//                        null,
//                        null, null
//                );
//            }
//
//        } else if (id == CAPM_LOADER) {
//            String[] projectionCAPM = {
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME,
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN,
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_DOSAGE,
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_FREQ,
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PURPOSE,
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_START_DATE,
//                    ImsContract.CurrentAndPastMedicationsEntry.COLUMN_END_DATE
//            };
//
//            if (mPatientId > 0) {
//
//
//                return new CursorLoader(
//                        this.getActivity(),
//                        ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI,
//                        projectionCAPM,
//                        ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PATIENT_ID + " =" + mPatientId,
//                        null, null
//
//                );
//            } else {
//                return new CursorLoader(this.getActivity(),
//                        mHealthRecordUri,
//                        projectionCAPM,
//                        null,
//                        null, null
//                );
//            }
//        } else if (id == MI_LOADER) {
//
//            String[] projection = {
//                    ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS,
//                    ImsContract.MajorIllnessesEntry.COLUMN_START_DATE,
//                    ImsContract.MajorIllnessesEntry.COLUMN_END_DATE,
//                    ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN,
//                    ImsContract.MajorIllnessesEntry.COLUMN_TREATMENT_NOTES,};
//
//            if (mPatientId > 0) {
//
//
//                return new CursorLoader(
//                        this.getActivity(),
//                        ImsContract.MajorIllnessesEntry.CONTENT_URI,
//                        projection,
//                        ImsContract.MajorIllnessesEntry.COLUMN_PATIENT_ID + " =" + mPatientId,
//                        null, null
//
//                );
//            } else {
//
//                return new CursorLoader(this.getActivity(),
//                        mMajorIllnessesUri,
//                        projection,
//                        null,
//                        null, null
//                );
//
//            }
//
//
//        } else if (id == SP_LOADER) {
//            String[] projection = {ImsContract.SurgicalProceduresEntry.COLUMN_PROCEDURE,
//                    ImsContract.SurgicalProceduresEntry.COLUMN_PHYSICIAN,
//                    ImsContract.SurgicalProceduresEntry.COLUMN_HOSPITAL,
//                    ImsContract.SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES,
//                    ImsContract.SurgicalProceduresEntry.COLUMN_NOTES};
//
//            if (mPatientId > 0) {
//
//
//                return new CursorLoader(
//                        this.getActivity(),
//                        ImsContract.SurgicalProceduresEntry.CONTENT_URI,
//                        projection,
//                        ImsContract.SurgicalProceduresEntry.COLUMN_PATIENT_ID + " =" + mPatientId,
//                        null, null
//
//                );
//            } else {
//
//                return new CursorLoader(this.getActivity(),
//                        mSurgicalProceduresUri,
//                        projection,
//                        null,
//                        null, null
//                );
//
//            }
//
//        } else if (id == PV_LOADER) {
//            String[] projection = {
//                    ImsContract.PatientVaccinesEntry.COLUMN_TETANUS,
//                    ImsContract.PatientVaccinesEntry.COLUMN_INFLUENZA_VACCINE,
//                    ImsContract.PatientVaccinesEntry.COLUMN_ZOSTAVAX,
//                    ImsContract.PatientVaccinesEntry.COLUMN_MENINGITIS,
//                    ImsContract.PatientVaccinesEntry.COLUMN_YELLOW_FEVER,
//                    ImsContract.PatientVaccinesEntry.COLUMN_POLIO,
//                    ImsContract.PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION,
//                    ImsContract.PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION
//
//            };
//            if (mPatientId > 0) {
//
//
//                return new CursorLoader(
//                        this.getActivity(),
//                        ImsContract.PatientVaccinesEntry.CONTENT_URI,
//                        projection,
//                        ImsContract.PatientVaccinesEntry.COLUMN_PATIENT_ID + " =" + mPatientId,
//                        null, null
//
//                );
//            } else {
//
//                return new CursorLoader(this.getActivity(),
//                        mPatientVaccinesUri,
//                        projection,
//                        null,
//                        null, null
//                );
//
//            }
//
//        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {



        int id = loader.getId();
        if (id == CAPM_LOADER) {
            if (mCurrentAndPastMedicationsUri == null) {
                if (mPatientId == 0) {

                }


            } else {
                if (data == null || data.getCount() < 1) {
                    return;
                }
                if (data.moveToFirst()) {
                    //get row Current and past medications
                    int medicamentNameColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_MEDICAMENT_NAME);
                    int dosageColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_DOSAGE);
                    int freqColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_FREQ);
                    int startDateColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_START_DATE);
                    int endDateColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_END_DATE);
                    int physicianColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PHYSICIAN);
                    int purposeColumnIndex = data.getColumnIndex(ImsContract.CurrentAndPastMedicationsEntry.COLUMN_PURPOSE);

                    String medicamentName = data.getString(medicamentNameColumnIndex);
                    String dosage = data.getString(dosageColumnIndex);
                    String freq = data.getString(freqColumnIndex);
                    String startDate = data.getString(startDateColumnIndex);
                    String endDate = data.getString(endDateColumnIndex);
                    String physician = data.getString(physicianColumnIndex);
                    String purpose = data.getString(purposeColumnIndex);

                    capmMedicamentNameEditText.setText(medicamentName);
                    capmDosageEditText.setText(dosage);
                    capmFreqEditText.setText(freq);
                    capmStartDateTextView.setText(startDate);
                    capmEndDateTextView.setText(endDate);
                    capmPhysicianEditText.setText(physician);
                    capmPurposeEditText.setText(purpose);
                }
            }
        } else if (id == HR_LOADER) {
            if (mHealthRecordUri == null) {
                if (mPatientId == 0) {

                }
            } else {
                if (data == null || data.getCount() < 1) {
                    return;
                }

                if (data.moveToFirst()) {
                    //get data all

                    //get row health record
                    int physicianNameColumnIndex = data.getColumnIndex(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHYSICIAN_NAME);
                    int pharmacyNameColumnIndex = data.getColumnIndex(ImsContract.HealthRecordEntry.COLUMN_CURRENT_PHARMACY_NAME);
                    int pharmacyPhoneColumnIndex = data.getColumnIndex(ImsContract.HealthRecordEntry.COLUMN_PHARMACY_PHONE);
                    int doctorPhoneColumnIndex = data.getColumnIndex(ImsContract.HealthRecordEntry.COLUMN_DOCTORS_PHONE);
                    int dateOfLastUpdateColumnIndex = data.getColumnIndex(ImsContract.HealthRecordEntry.COLUMN_DATE_OF_THE_LAST_UPDATE);

                    String physicianName = data.getString(physicianNameColumnIndex);
                    String pharmacyName = data.getString(pharmacyNameColumnIndex);
                    String pharmacyPhone = data.getString(pharmacyPhoneColumnIndex);
                    String doctorPhone = data.getString(doctorPhoneColumnIndex);
                    String dateOfLastUpdate = data.getString(dateOfLastUpdateColumnIndex);

                    hrCurrentPhysicianNameEditText.setText(physicianName);
                    hrCurrentPharmacyNameEditText.setText(pharmacyName);
                    hrPharmacyPhoneEditText.setText(pharmacyPhone);
                    hrDoctorsPhoneEditText.setText(doctorPhone);
                    hrDateOfTheLastUpdateTextView.setText(dateOfLastUpdate);
                }
            }

        } else if (id == MI_LOADER) {

            if (mMajorIllnessesUri == null) {
                if (mPatientId == 0) {

                }
            } else {
                if (data == null || data.getCount() < 1) {
                    return;
                }
                if (data.moveToFirst()) {
                    //get data all
                }

                //get row  Major illnesses
                int miIllnessColumnIndex = data.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_ILLNESS);
                int miStartDateColumnIndex = data.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_START_DATE);
                int miEndDateColumnIndex = data.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_END_DATE);
                int miPhysicianColumnIndex = data.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_PHYSICIAN);
                int miTreatmentNotesColumnIndex = data.getColumnIndex(ImsContract.MajorIllnessesEntry.COLUMN_TREATMENT_NOTES);

                String miIllness = data.getString(miIllnessColumnIndex);
                String miStartDate = data.getString(miStartDateColumnIndex);
                String miEndDate = data.getString(miEndDateColumnIndex);
                String miPhysician = data.getString(miPhysicianColumnIndex);
                String miTreatmentNotes = data.getString(miTreatmentNotesColumnIndex);


                miIllnessEditText.setText(miIllness);
                miStartDateTextView.setText(miStartDate);
                miEndDateTextView.setText(miEndDate);
                miPhysicianEditText.setText(miPhysician);
                miTreatmentNotesEditText.setText(miTreatmentNotes);
            }


        } else if (id == SP_LOADER) {
            if (mSurgicalProceduresUri == null) {
                if (mPatientId == 0) {

                }
            } else {
                if (data == null || data.getCount() < 1) {
                    return;
                }

                if (data.moveToFirst()) {
                    //get data all
                }

                //get row Surgical procedures
                int spProcedureColumnIndex = data.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_PROCEDURE);
                int spPhysicianColumnIndex = data.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_PHYSICIAN);
                int spHospitalColumnIndex = data.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_HOSPITAL);
                int spDateColumnIndex = data.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_DATE_SURGICAL_PROCEDURES);
                int spNotesEColumnIndex = data.getColumnIndex(ImsContract.SurgicalProceduresEntry.COLUMN_NOTES);

                String spProcedure = data.getString(spProcedureColumnIndex);
                String spPhysician = data.getString(spPhysicianColumnIndex);
                String spHospital = data.getString(spHospitalColumnIndex);
                String spDate = data.getString(spDateColumnIndex);
                String spNotesE = data.getString(spNotesEColumnIndex);

                spProcedureEditText.setText(spProcedure);
                spPhysicianEditText.setText(spPhysician);
                spHospitalEditText.setText(spHospital);
                spDateTextView.setText(spDate);
                spNotesEditText.setText(spNotesE);
            }

        } else if (id == PV_LOADER) {


            if (mPatientVaccinesUri == null) {
                if (mPatientId == 0) {

                }
            } else {
                if (data == null || data.getCount() < 1) {
                    return;
                }

                if (data.moveToFirst()) {
                    //get data all
                }

                //get row Patient vaccines
                int pvTetanusColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_TETANUS);
                int pvInfluenzaVaccineColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_INFLUENZA_VACCINE);
                int pvZostavaxColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_ZOSTAVAX);
                int pvMeningitisColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_MENINGITIS);
                int pvYellowFeverColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_YELLOW_FEVER);
                int pvPolioColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_POLIO);
                //   int pvTetanusTypeColumnIndex=data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION);
                int pvHistoryOfVaccinationColumnIndex = data.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION);

                String pvTetanus = data.getString(pvTetanusColumnIndex);
                String pvInfluenzaVaccine = data.getString(pvInfluenzaVaccineColumnIndex);
                String pvZostavax = data.getString(pvZostavaxColumnIndex);
                String pvMeningitis = data.getString(pvMeningitisColumnIndex);
                String pvYellowFever = data.getString(pvYellowFeverColumnIndex);
                String pvPolio = data.getString(pvPolioColumnIndex);
                // String pvTetanusType=data.getString(pvTetanusTypeColumnIndex);
                String pvHistoryOfVaccination = data.getString(pvHistoryOfVaccinationColumnIndex);

                pvTetanusTextView.setText(pvTetanus);
                pvInfluenzaVaccineTextView.setText(pvInfluenzaVaccine);
                pvZostavaxTextView.setText(pvZostavax);
                pvMeningitisTextView.setText(pvMeningitis);
                pvYellowFeverTextView.setText(pvYellowFever);
                pvPolioTextView.setText(pvPolio);

                switch (pvTypesOfVaccination) {
                    case ImsContract.PatientVaccinesEntry.TETANUS_D_T:
                        pvTypesOfVaccinationSpinner.setSelection(ImsContract.PatientVaccinesEntry.TETANUS_D_T);
                        break;
                    case ImsContract.PatientVaccinesEntry.TETANUS_D_T_A_P:
                        pvTypesOfVaccinationSpinner.setSelection(ImsContract.PatientVaccinesEntry.TETANUS_D_T_A_P);
                        break;
                    case ImsContract.PatientVaccinesEntry.TETANUS_T_D:
                        pvTypesOfVaccinationSpinner.setSelection(ImsContract.PatientVaccinesEntry.TETANUS_T_D);
                        break;
                    case ImsContract.PatientVaccinesEntry.TETANUS_T_DAP:
                        pvTypesOfVaccinationSpinner.setSelection(ImsContract.PatientVaccinesEntry.TETANUS_T_DAP);
                        break;
                    default:
                        pvTypesOfVaccinationSpinner.setSelection(ImsContract.PatientVaccinesEntry.TETANUS_UNKNOWN);
                }
                pvHistoryOfVaccinationTextView.setText(pvHistoryOfVaccination);
            }
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
//        int loaderId = loader.getId();
//        if (loaderId == HR_LOADER) {
//            if (mHealthRecordUri == null) {
//                loader.reset();
//            } else {
//                hrCurrentPhysicianNameEditText.setText("");
//                hrCurrentPharmacyNameEditText.setText("");
//                hrDoctorsPhoneEditText.setText("");
//                hrDoctorsPhoneEditText.setText("");
//                hrDateOfTheLastUpdateTextView.setText("");
//            }
//        } else if (loaderId == CAPM_LOADER) {
//            if (mCurrentAndPastMedicationsUri == null) {
//                loader.reset();
//            } else {
//
//            }
//        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        //add search with content column id
//        mHealthRecordUri = ContentUris.withAppendedId(ImsContract.HealthRecordEntry.CONTENT_URI, mPatientId);
//        mCurrentAndPastMedicationsUri = ContentUris.withAppendedId(ImsContract.CurrentAndPastMedicationsEntry.CONTENT_URI, mPatientId);
//        mMajorIllnessesUri = ContentUris.withAppendedId(ImsContract.MajorIllnessesEntry.CONTENT_URI, mPatientId);
//        mSurgicalProceduresUri = ContentUris.withAppendedId(ImsContract.SurgicalProceduresEntry.CONTENT_URI, mPatientId);
//        mPatientVaccinesUri = ContentUris.withAppendedId(ImsContract.PatientVaccinesEntry.CONTENT_URI, mPatientId);
//
//        //get data in loader activity
//        getLoaderManager().initLoader(HR_LOADER, null, this);
//        getLoaderManager().initLoader(CAPM_LOADER, null, this);
//        getLoaderManager().initLoader(MI_LOADER, null, this);
//        getLoaderManager().initLoader(SP_LOADER, null, this);
//        getLoaderManager().initLoader(PV_LOADER, null, this);
    }
}