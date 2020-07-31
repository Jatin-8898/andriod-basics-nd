package com.example.tourguideapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class RestaurantsFragment extends Fragment {


    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.res_place_1), getString(R.string.res_desc_1), getString(R.string.res_location_1), getString(R.string.res_number_1), R.drawable.cascal));
        places.add(new Place(getString(R.string.res_place_2), getString(R.string.res_desc_2), getString(R.string.res_location_2), getString(R.string.res_number_2), R.drawable.vive_sol));
        places.add(new Place(getString(R.string.res_place_3), getString(R.string.res_desc_3), getString(R.string.res_location_3), getString(R.string.res_number_3), R.drawable.oren));
        places.add(new Place(getString(R.string.res_place_4), getString(R.string.res_desc_4), getString(R.string.res_location_4), getString(R.string.res_number_4), R.drawable.xanh));

        PlaceAdapter adapter = new PlaceAdapter(Objects.requireNonNull(getActivity()), places);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
        return rootView;
    }
}