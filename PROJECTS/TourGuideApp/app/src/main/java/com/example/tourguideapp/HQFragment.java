package com.example.tourguideapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;


public class HQFragment extends Fragment {

    public HQFragment() {}      // Required empty public constructor

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.hq_place_1), getString(R.string.hq_desc_1), getString(R.string.hq_location_1), getString(R.string.hq_number_1), R.drawable.google));
        places.add(new Place(getString(R.string.hq_place_2), getString(R.string.hq_desc_2), getString(R.string.hq_location_2), getString(R.string.hq_number_2), R.drawable.linkedin));
        places.add(new Place(getString(R.string.hq_place_3), getString(R.string.hq_desc_3), getString(R.string.hq_location_3), getString(R.string.hq_number_3), R.drawable.coursera));
        places.add(new Place(getString(R.string.hq_place_4), getString(R.string.hq_desc_4), getString(R.string.hq_location_4), getString(R.string.hq_number_4), R.drawable.mozilla));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), places);
        ListView listView = rootView.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        return rootView;
    }
}