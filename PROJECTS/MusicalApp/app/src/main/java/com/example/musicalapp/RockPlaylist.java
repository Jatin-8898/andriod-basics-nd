package com.example.musicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RockPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_playlist);

        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(R.drawable.thunder, "Thunder", "Imagine Dragons"));
        songs.add(new Song(R.drawable.drake_over, "Over", "Drake"));
        songs.add(new Song(R.drawable.trevor_daniel_falling, "Falling", "Trevor Daniel"));
        songs.add(new Song(R.drawable.instagram, "Instagram", "Dimitri Vegas"));
        songs.add(new Song(R.drawable.desires, "Desires", "Drake"));
        songs.add(new Song(R.drawable.powfu_death_bed, "Death Bed", "Powfu"));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = findViewById(R.id.rock_playlist_list);
        listView.setAdapter(adapter);
    }
}