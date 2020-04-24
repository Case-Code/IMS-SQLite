package com.example.ims.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.ims.R;
import com.example.ims.data.ImsContract;

public  class PatientVaccinesCursorAdapter extends CursorAdapter {

    public PatientVaccinesCursorAdapter(Context context, Cursor c) {
        super(context, c,13);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_patient_vaccines, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameOfVaccinationTextView  =view.findViewById(R.id.text_item_pv_name_of_vaccination);
        TextView  historyOfVaccinationTextView=view.findViewById(R.id.text_item_pv_history_of_vaccination);


        int patientVacIdColumnIndex=cursor.getColumnIndex(ImsContract.PatientVaccinesEntry._ID);
        int nameOfVaccinationColumnIndex=cursor.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_NAME_OF_VACCINATION);
        int historyOfVaccinationColumnIndex=cursor.getColumnIndex(ImsContract.PatientVaccinesEntry.COLUMN_HISTORY_OF_VACCINATION);

        String  nameOfVaccination=cursor.getString(nameOfVaccinationColumnIndex);
        String  historyOfVaccination=cursor.getString(historyOfVaccinationColumnIndex);


        int resultName =Integer.valueOf(nameOfVaccination);
                    //result int to if equals  show name Of Vaccination
            if(resultName==ImsContract.PatientVaccinesEntry.VACCINATION_D_T){
                nameOfVaccinationTextView.setText("Diphtheria and tetanus (DT) vaccines");
            }else if (resultName==ImsContract.PatientVaccinesEntry.VACCINATION_D_T_A_P){
                nameOfVaccinationTextView.setText("Diphtheria, tetanus, and pertussis (DTaP) vaccines");
            }else if(resultName==ImsContract.PatientVaccinesEntry.VACCINATION_T_D){
                nameOfVaccinationTextView.setText("Tetanus and diphtheria (Td) vaccines");
            }else if(resultName==ImsContract.PatientVaccinesEntry.VACCINATION_TETANUS_T_DAP){
                nameOfVaccinationTextView.setText("  Tetanus, diphtheria, and pertussis (Tdap) vaccines");
            }else if(resultName==ImsContract.PatientVaccinesEntry.VACCINATION_TETANUS){
                nameOfVaccinationTextView.setText("Tetanus");
            }else if(resultName==ImsContract.PatientVaccinesEntry.VACCINATION_INFLUENZA_VACCINE){
                nameOfVaccinationTextView.setText("Influenza vaccine");
            }else if (resultName==ImsContract.PatientVaccinesEntry.VACCINATION_ZOSTAVAX){
                nameOfVaccinationTextView.setText("ZOSTAVAX");
            }
            else if (resultName==ImsContract.PatientVaccinesEntry.VACCINATION_MENINGITIS){
                nameOfVaccinationTextView.setText("Meningitis");
            }
            else if (resultName==ImsContract.PatientVaccinesEntry.VACCINATION_YELLOW_FEVER){
                nameOfVaccinationTextView.setText("Yellow fever");
            }
            else if (resultName==ImsContract.PatientVaccinesEntry.VACCINATION_POLIO){
                nameOfVaccinationTextView.setText("Polio");
            }
            else if (resultName==ImsContract.PatientVaccinesEntry.VACCINATION_UNKNOWN){
                nameOfVaccinationTextView.setText("VACCINATION UNKNOWN");
            }


        historyOfVaccinationTextView.setText(historyOfVaccination);



    }
}
