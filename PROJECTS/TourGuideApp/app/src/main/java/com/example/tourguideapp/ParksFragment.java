package com.example.tourguideapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


public class ParksFragment extends Fragment {

    public ParksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.park_place_1), getString(R.string.park_desc_1), getString(R.string.park_location_1), getString(R.string.park_number_1), R.drawable.cuesta_park_mountain));
        places.add(new Place(getString(R.string.park_place_2), getString(R.string.park_desc_2), getString(R.string.park_location_2), getString(R.string.park_number_2), R.drawable.sylvan_park));
        places.add(new Place(getString(R.string.park_place_3), getString(R.string.park_desc_3), getString(R.string.park_location_3), getString(R.string.park_number_3), R.drawable.shoup_park));
        places.add(new Place(getString(R.string.park_place_4), getString(R.string.park_desc_4), getString(R.string.park_location_4), getString(R.string.park_number_4), R.drawable.ortega_park));

        PlaceAdapter adapter = new PlaceAdapter(Objects.requireNonNull(getActivity()), places);
        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
        return rootView;
    }
}