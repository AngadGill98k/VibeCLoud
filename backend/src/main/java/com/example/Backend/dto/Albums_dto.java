package com.example.Backend.dto;

import java.util.ArrayList;
import com.example.Backend.models.Song;

public class Albums_dto {


    private String id;
    private String album_name;
    private String artist_name;
    private String artist_id;
    private String genre;
    private String album_path;
    private ArrayList<Song> songs;

    private String getId(){
        return id;
    }
    private String getAlbum_name(){
        return album_name;
    }
    private String getArtist_name(){
        return artist_name;
    }
    private String getArtist_id(){
        return artist_id;
    }
    private String getGenre(){
        return genre;
    }
    private String getAlbum_path(){
        return album_path;
    }
    private ArrayList<Song> getSongs(){
        return songs;
    }

    private void setAlbum_name(String album_name){
        this.album_name = album_name;
    }
    private void setArtist_name(String artist_name){
        this.artist_name = artist_name;
    }
    private void setArtist_id(String artist_id){
        this.artist_id = artist_id;
    }
    private void setGenre(String genre){
        this.genre = genre;
    }
    private void setAlbum_path(String album_path){
        this.album_path = album_path;
    }
    private void setSongs(Song song){
        this.songs.add(song);
    }

}
