package com.example.ims.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class FragmentPharmacyPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    private Context mContext;
    private String[] tabTitles = new String[]{"Medicine registry", "Sales record", "Sale"};

    public FragmentPharmacyPagerAdapter(@NonNull Context context, @NonNull FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentMedicineRegistry();
            case 1:
                return new FragmentSalesRecord();
            default:
                return new FragmentSale();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        return tabTitles[position];
    }
}