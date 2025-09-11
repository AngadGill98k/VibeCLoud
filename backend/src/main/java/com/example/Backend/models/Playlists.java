package com.example.Backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Playlist")
public class Playlists {
    @Id
    private String id;
    private String playlistName;
    private String playlistImage;
    private ArrayList<Song> song=new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getPlaylistName() {
        return playlistName;
    }
    public String getPlaylistImage() {
        return playlistImage;
    }
    public ArrayList<Song> getSong() {
        return song;
    }

    public void setPlaylistImage(String playlistImage) {
        this.playlistImage = playlistImage;
    }
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
    public void setSong(Song song) {
        this.song.add(song);
    }
}
