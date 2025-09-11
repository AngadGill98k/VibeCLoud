package com.example.Backend.models;

public class Song {
    private String song_name;
    private String artistName;
    private String artistId;
    private String album;
    private String image_path;
    private String audio_path;

    public String getSong_name(){
        return song_name;
    }
    public String getArtistName(){
        return artistName;
    }
    public String getArtistId(){return artistId;}
    public String getAlbum(){
        return album;
    }
    public String getImage_path(){
        return image_path;
    }
    public String getAudio_path(){
        return audio_path;
    }

    public void setSong_name(String song_name){
        this.song_name = song_name;
    }
    public void setArtistName(String artistName){
        this.artistName = artistName;
    }
    public void setArtistId(String artistId){this.artistId = artistId;}
    public void setAlbum(String album){
        this.album = album;
    }
    public void setImage_path(String image_path){
        this.image_path = image_path;
    }
    public void setAudio_path(String audio_path){
        this.audio_path = audio_path;
    }




}
