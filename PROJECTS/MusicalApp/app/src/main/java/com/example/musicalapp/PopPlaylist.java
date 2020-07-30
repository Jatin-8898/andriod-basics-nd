package com.example.musicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PopPlaylist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_playlist);

        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(R.drawable.end_of_time, "End of Time", "Alan Walker"));
        songs.add(new Song(R.drawable.unforgettable_, "Unforgettable", "Swa Lee"));
        songs.add(new Song(R.drawable.this_girl, "This Girl", "Laza Morgan"));
        songs.add(new Song(R.drawable.thunder, "Thunder", "Imagine Dragons"));
        songs.add(new Song(R.drawable.drake_over, "Over", "Drake"));
        songs.add(new Song(R.drawable.trevor_daniel_falling, "Falling", "Trevor Daniel"));
        songs.add(new Song(R.drawable.instagram, "Instagram", "Dimitri Vegas"));

        SongAdapter adapter = new SongAdapter(this, songs);
        ListView listView = findViewById(R.id.pop_playlist_list);
        listView.setAdapter(adapter);
    }
}