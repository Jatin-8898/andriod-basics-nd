package com.example.tourguideapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;


public class EventsFragment extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.events_place_1), getString(R.string.events_desc_1), getString(R.string.events_location_1), getString(R.string.events_number_1)));
        places.add(new Place(getString(R.string.events_place_2), getString(R.string.events_desc_2), getString(R.string.events_location_2), getString(R.string.events_number_2)));
        places.add(new Place(getString(R.string.events_place_3), getString(R.string.events_desc_3), getString(R.string.events_location_3), getString(R.string.events_number_3)));
        places.add(new Place(getString(R.string.events_place_4), getString(R.string.events_desc_4), getString(R.string.events_location_4), getString(R.string.events_number_4)));

        PlaceAdapter adapter = new PlaceAdapter(Objects.requireNonNull(getActivity()), places);
        ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        
        return rootView;
    }
}