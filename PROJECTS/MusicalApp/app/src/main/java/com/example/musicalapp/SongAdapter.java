package com.example.musicalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View ListView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (ListView == null) {
            ListView = LayoutInflater.from(getContext()).inflate(R.layout.songs_list, parent, false);
        }

        Song currentSong = getItem(position);
        assert currentSong != null;

        /*ImageView imageView = ListView.findViewById(R.id.image);
        imageView.setImageResource(currentSong.getImage());*/

        TextView songName = ListView.findViewById(R.id.song_name);
        songName.setText(currentSong.getSongName());

        TextView artistName = ListView.findViewById(R.id.artist_name);
        artistName.setText(currentSong.getArtistName());

        return ListView;
    }
}
