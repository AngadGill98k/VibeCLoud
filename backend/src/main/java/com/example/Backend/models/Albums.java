package com.example.Backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Albums")
public class Albums {

    @Id
    private String id;
    private String album_name;
    private String artist_name;
    private String artist_id;
    private String genre;
    private String image_path;
    private ArrayList<Song> songs=new ArrayList<>();

    public String getId(){
        return id;
    }
    public String getAlbum_name(){
        return album_name;
    }
    public String getArtist_name(){
        return artist_name;
    }
    public String getArtist_id(){
        return artist_id;
    }
    public String getGenre(){
        return genre;
    }
    public String getImage_path(){
        return image_path;
    }
    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void setAlbum_name(String album_name){
        this.album_name = album_name;
    }
    public void setArtist_name(String artist_name){
        this.artist_name = artist_name;
    }
    public void setArtist_id(String artist_id){
        this.artist_id = artist_id;
    }
    public void setGenre(String genre){
        this.genre = genre;
    }
    public void setImage_path(String image_path){
        this.image_path = image_path;
    }
    public void setSongs(Song song){
        this.songs.add(song);
    }

}
