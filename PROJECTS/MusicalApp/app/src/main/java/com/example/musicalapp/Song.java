package com.example.musicalapp;

public class Song {
    private String songName;
    private String artistName;
    private int image;

    public Song(int image, String songName, String artistName) {
        this.image = image;
        this.songName = songName;
        this.artistName = artistName;

    }

    public String getSongName() {
        return songName;
    }
    public String getArtistName() {
        return artistName;
    }
    public int getImage(){ return image; }
}
