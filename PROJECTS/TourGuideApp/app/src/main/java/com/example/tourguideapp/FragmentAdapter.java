package com.example.tourguideapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends  FragmentStatePagerAdapter{

    private Context appContext;

    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.appContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HQFragment();
        } else if (position == 1) {
            return new ParksFragment();
        } else if (position == 2) {
            return new EventsFragment();
        } else {
            return new RestaurantsFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return appContext.getString(R.string.hq_title);
        } else if (position == 1) {
            return appContext.getString(R.string.parks_title);
        } else if (position == 2) {
            return appContext.getString(R.string.events_title);
        } else {
            return appContext.getString(R.string.restaurants_title);
        }
    }
}