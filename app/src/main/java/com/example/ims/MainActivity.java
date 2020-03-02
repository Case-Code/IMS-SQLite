package com.example.ims;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CardView receptionCardView,
            theDoctorCardView,
            analysisLabCardView,
            radiologyLaboratoryCardView,
            thePharmacyCardView,
            financialAccountsCardView,
            personnelCardView;

    private ImageButton actionMenuImageButton;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        actionMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        // Reception
        receptionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To reception
                Intent intent = new Intent(MainActivity.this, ReceptionActivity.class);
                startActivity(intent);
            }
        });

        // The doctor
        theDoctorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To the doctor
                Intent intent = new Intent(MainActivity.this, TheDoctorActivity.class);
                startActivity(intent);
            }
        });

        // Analysis lab
        analysisLabCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To analysis lab
                Intent intent = new Intent(MainActivity.this, AnalysisLabActivity.class);
                startActivity(intent);
            }
        });

        // Radiology laboratory
        radiologyLaboratoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To radiology laboratory
                Intent intent = new Intent(MainActivity.this, RadiologyLaboratoryActivity.class);
                startActivity(intent);
            }
        });

        // The Pharmacy
        thePharmacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To the Pharmacy
                Intent intent = new Intent(MainActivity.this, ThePharmacyActivity.class);
                startActivity(intent);
            }
        });

        // Financial accounts
        financialAccountsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To financial accounts
                Intent intent = new Intent(MainActivity.this, FinancialAccountsActivity.class);
                startActivity(intent);
            }
        });

        // Personnel
        personnelCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To personnel
                Intent intent = new Intent(MainActivity.this, PersonnelActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.menu_item_home:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_reception:
                intent = new Intent(this, ReceptionActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_the_doctor:
                intent = new Intent(this, TheDoctorActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_analysis_lab:
                intent = new Intent(this, AnalysisLabActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_radiology_laboratory:
                intent = new Intent(this, RadiologyLaboratoryActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_the_pharmacy:
                intent = new Intent(this, ThePharmacyActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_financial_accounts:
                intent = new Intent(this, FinancialAccountsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_item_personnel:
                intent = new Intent(this, PersonnelActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // initial
    private void init() {

        drawerLayout = findViewById(R.id.activity_main);
        navigationView = findViewById(R.id.navigation_view);

        actionMenuImageButton = findViewById(R.id.image_button_action_menu);

        receptionCardView = findViewById(R.id.card_view_reception);
        theDoctorCardView = findViewById(R.id.card_view_the_doctor);
        analysisLabCardView = findViewById(R.id.card_view_analysis_lab);
        radiologyLaboratoryCardView = findViewById(R.id.card_view_radiology_laboratory);
        thePharmacyCardView = findViewById(R.id.card_view_the_pharmacy);
        financialAccountsCardView = findViewById(R.id.card_view_financial_accounts);
        personnelCardView = findViewById(R.id.card_view_personnel);
    }
}


