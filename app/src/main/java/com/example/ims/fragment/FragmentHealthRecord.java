package com.example.ims.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ims.R;


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

    Button patientSaveButton;
    Button medicationSaveButton;
    Button majorAddButton;
    Button proceduresAddButton;
    Button vaccinesAddButton;


    public EditText patientPhysicianNameEditText;
    public EditText patientPharmacyNameEditText;
    public EditText patientPharmacyPhoneEditText;
    public EditText patientDoctorPhoneEditText;
    TextView patientDateLastUpdateTextView;
    TextView patientNameTextView;



    public EditText medicationMedicamentNameEditText;
    public EditText medicationPhysicianEditText;
    public EditText medicationPurposeEditText;
    public EditText medicationFREQEditText;
    public EditText medicationDosageEditText;
    TextView medicationStartDateTextView;
    TextView medicationEndDateTextView;


    public EditText majorIllnessEditText;
    public EditText majorPhysicianEditText;
    public EditText majorTreatmentNotesEditText;
    TextView majorStartDateTextView;
    TextView majorEndDateTextView;
    ListView majorListView;


    public EditText proceduresEditText;
    public EditText proceduresPhysicianEditText;
    public EditText proceduresHospitalEditText;
    public EditText proceduresNotesEditText;
    TextView proceduresDateSurgicalTextView;
    ListView proceduresListView;

    TextView    vaccinesTETANUSTextView;
    TextView    vaccinesINFLUENZAVACCINETextView;
    TextView    vaccinesZOSTAVAXTextView;
    TextView    vaccinesMENINGITISTextView;
    TextView    vaccinesYELLOWFEVERTextView;
    TextView    vaccinesPOLIOTextView;
    TextView    vaccinesHistoryTextView;
    Spinner vaccinesSpinner;
    ListView vaccinesListView;

    View view;
    public void init(){

         patientSaveButton=view.findViewById(R.id.button_health_record_patient_save);
         medicationSaveButton=view.findViewById(R.id.button_health_record_medications_save);
         majorAddButton=view.findViewById(R.id.button_health_record_major);
         proceduresAddButton=view.findViewById(R.id.button_health_record_procedures_add);
        vaccinesAddButton=view.findViewById(R.id.button_health_record_vaccines_add);


        patientPhysicianNameEditText=view.findViewById(R.id.edit_health_record_patient_physiscian_name);
         patientPharmacyNameEditText=view.findViewById(R.id.edit_health_record_patient_pharmacy_name);
         patientPharmacyPhoneEditText=view.findViewById(R.id.edit_health_record_patient_Pharmacy_phone);
        patientDoctorPhoneEditText.findViewById(R.id.edit_health_record_patient_doctor_phone);
         patientDateLastUpdateTextView=view.findViewById(R.id.text_health_record_patient_date);
         patientNameTextView=view.findViewById(R.id.text_health_record_patient_name);



          medicationMedicamentNameEditText=view.findViewById(R.id.edit_health_record__medications_medicament_name);
        medicationPhysicianEditText=view.findViewById(R.id.edit_health_record_medications_physiscian);
          medicationPurposeEditText=view.findViewById(R.id.edit_health_record_medications_purpose);
          medicationFREQEditText=view.findViewById(R.id.edit_health_record_medications_freq);
          medicationDosageEditText=view.findViewById(R.id.edit_health_record_medications_dosage);
         medicationStartDateTextView=view.findViewById(R.id.text_health_record_medications_start_date);
         medicationEndDateTextView=view.findViewById(R.id.text_health_record_medications_end_date);

          majorIllnessEditText=view.findViewById(R.id.edit_health_record_Major_illness);
          majorPhysicianEditText=view.findViewById(R.id.edit_health_record_Major_physician);
          majorTreatmentNotesEditText=view.findViewById(R.id.edit_health_record_major_notes);
         majorStartDateTextView=view.findViewById(R.id.text_health_record_Major_start_date);
         majorEndDateTextView=view.findViewById(R.id.text_health_record_Major_end_date);
         majorListView=view.findViewById(R.id.list_health_record_major);


          proceduresEditText=view.findViewById(R.id.edit_health_record_procedure_e);
          proceduresPhysicianEditText=view.findViewById(R.id.edit_health_record_procedure_physician);
          proceduresHospitalEditText=view.findViewById(R.id.edit_health_record_procedure_hospital);
          proceduresNotesEditText=view.findViewById(R.id.edit_health_record_procedure_note);
         proceduresDateSurgicalTextView=view.findViewById(R.id.text_health_record_procedure_date);
         proceduresListView=view.findViewById(R.id.list_helath_record_procedure);

            vaccinesTETANUSTextView=view.findViewById(R.id.text_health_record_vaccines_tetanus);
            vaccinesINFLUENZAVACCINETextView=view.findViewById(R.id.text_health_record_vaccines_vaccine);
            vaccinesZOSTAVAXTextView=view.findViewById(R.id.text_health_record_vaccines_zostavax);
            vaccinesMENINGITISTextView=view.findViewById(R.id.text_health_record_vaccines_meningitis);
            vaccinesYELLOWFEVERTextView=view.findViewById(R.id.text_health_record_vaccines_yellow_fever);
            vaccinesPOLIOTextView=view.findViewById(R.id.text_health_record_vaccines_polio);
            vaccinesHistoryTextView=view.findViewById(R.id.text_health_record_vaccines_history);
            vaccinesSpinner =view.findViewById(R.id.spinner_health_record_vaccines);

         vaccinesListView=view.findViewById(R.id.list_health_record_vaccines);


    }










    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHealthRecord() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health_record  , container, false);
    }
}
