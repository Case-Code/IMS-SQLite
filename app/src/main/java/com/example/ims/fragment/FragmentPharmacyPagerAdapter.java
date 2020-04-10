package com.example.ims.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentPharmacyPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    private int numsoftabs;

    public FragmentPharmacyPagerAdapter( @NonNull FragmentManager fm,int numoftabs) {
        super(fm);
        this.numsoftabs = numoftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentMedicineRegistry();
            case 1:
                return new FragmentSalesRecord();
            case 2:
                return new FragmentSale();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numsoftabs;
    }
}