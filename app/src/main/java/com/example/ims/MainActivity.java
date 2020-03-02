package com.example.ims;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView receptionCardView,
            theDoctorCardView,
            analysisLabCardView,
            radiologyLaboratoryCardView,
            thePharmacyCardView,
            financialAccountsCardView,
            personnelCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        // Reception
        receptionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Reception", Toast.LENGTH_SHORT).show();
            }
        });

        // The doctor
        theDoctorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The doctor", Toast.LENGTH_SHORT).show();
            }
        });

        // Analysis lab
        analysisLabCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Analysis lab", Toast.LENGTH_SHORT).show();
            }
        });

        // Radiology laboratory
        radiologyLaboratoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Radiology laboratory", Toast.LENGTH_SHORT).show();
            }
        });

        // The Pharmacy
        thePharmacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "The Pharmacy", Toast.LENGTH_SHORT).show();
            }
        });

        // Financial accounts
        financialAccountsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Financial accounts", Toast.LENGTH_SHORT).show();
            }
        });

        // Personnel
        personnelCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Personnel", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // initial
    private void init() {
        receptionCardView = findViewById(R.id.card_view_reception);
        theDoctorCardView = findViewById(R.id.card_view_the_doctor);
        analysisLabCardView = findViewById(R.id.card_view_analysis_lab);
        radiologyLaboratoryCardView = findViewById(R.id.card_view_radiology_laboratory);
        thePharmacyCardView = findViewById(R.id.card_view_the_pharmacy);
        financialAccountsCardView = findViewById(R.id.card_view_financial_accounts);
        personnelCardView = findViewById(R.id.card_view_personnel);
    }
}
