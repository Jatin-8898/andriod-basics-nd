package com.example.tourguideapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PlaceAdapter extends ArrayAdapter<Place> {

    public PlaceAdapter(@NonNull Context context, ArrayList<Place> places) {
        super(context, 0, places);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_contents, parent, false);
        }

        Place places = getItem(position);
        assert places != null;

        TextView placeName = listView.findViewById(R.id.place_name);
        placeName.setText(places.getPlaceName());

        TextView placeDesc = listView.findViewById(R.id.place_desc);
        placeDesc.setText(places.getPlaceDesc());

        TextView placeLocation = listView.findViewById(R.id.place_location);
        placeLocation.setText(places.getPlaceLocation());

        TextView placeNumber = listView.findViewById(R.id.place_number);
        placeNumber.setText(places.getPlaceNumber());

        ImageView imageView = listView.findViewById(R.id.image);
        if (places.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(places.getPlaceImg());
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }

        return listView;
    }
}
