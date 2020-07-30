package com.example.musicalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton rockPlaylist  =  findViewById(R.id.rock_playlist);
        rockPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rockPlaylistIntent = new Intent(MainActivity.this, RockPlaylist.class);
                startActivity(rockPlaylistIntent);
            }
        });

        ImageButton popPlaylist  =  findViewById(R.id.pop_playlist);
        popPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popPlaylistIntent = new Intent(MainActivity.this, PopPlaylist.class);
                startActivity(popPlaylistIntent);
            }
        });
        ImageButton rapPlaylist  =  findViewById(R.id.rap_playlist);
        rapPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rapPlaylistIntent = new Intent(MainActivity.this, RapPlaylist.class);
                startActivity(rapPlaylistIntent);
            }
        });
    }
}