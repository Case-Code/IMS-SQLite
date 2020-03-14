package com.example.ims;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CardView mReceptionCardView,
            mTheDoctorCardView,
            mAnalysisLabCardView,
            mRadiologyLaboratoryCardView,
            mThePharmacyCardView,
            mFinancialAccountsCardView,
            mPersonnelCardView;

    private ImageButton mActionMenuImageButton;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mActionMenuImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });


        // Reception
        mReceptionCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To reception
                Intent intent = new Intent(MainActivity.this, ReceptionActivity.class);
                startActivity(intent);
            }
        });

        // The doctor
        mTheDoctorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To the doctor
                Intent intent = new Intent(MainActivity.this, TheDoctorActivity.class);
                startActivity(intent);
            }
        });

        // Analysis lab
        mAnalysisLabCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To analysis lab
                Intent intent = new Intent(MainActivity.this, AnalysisLabActivity.class);
                startActivity(intent);
            }
        });

        // Radiology laboratory
        mRadiologyLaboratoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To radiology laboratory
                Intent intent = new Intent(MainActivity.this, RadiologyLaboratoryActivity.class);
                startActivity(intent);
            }
        });

        // The Pharmacy
        mThePharmacyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To the Pharmacy
                Intent intent = new Intent(MainActivity.this, ThePharmacyActivity.class);
                startActivity(intent);
            }
        });

        // Financial accounts
        mFinancialAccountsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // To financial accounts
                Intent intent = new Intent(MainActivity.this, FinancialAccountsActivity.class);
                startActivity(intent);
            }
        });

        // Personnel
        mPersonnelCardView.setOnClickListener(new View.OnClickListener() {
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
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // initial
    private void init() {
        mDrawerLayout = findViewById(R.id.activity_main);
        mNavigationView = findViewById(R.id.navigation_view);
        mActionMenuImageButton = findViewById(R.id.image_button_action_menu);
        mReceptionCardView = findViewById(R.id.card_view_reception);
        mTheDoctorCardView = findViewById(R.id.card_view_the_doctor);
        mAnalysisLabCardView = findViewById(R.id.card_view_analysis_lab);
        mRadiologyLaboratoryCardView = findViewById(R.id.card_view_radiology_laboratory);
        mThePharmacyCardView = findViewById(R.id.card_view_the_pharmacy);
        mFinancialAccountsCardView = findViewById(R.id.card_view_financial_accounts);
        mPersonnelCardView = findViewById(R.id.card_view_personnel);
    }
}


