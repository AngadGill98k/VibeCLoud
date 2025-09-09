package com.example.Backend.models;

public class Song {
    private String song_name;
    private String artist;
    private String album;
    private String image_path;
    private String audio_path;

    private String getSong_name(){
        return song_name;
    }
    private String getArtist(){
        return artist;
    }
    private String getAlbum(){
        return album;
    }
    private String getImage_path(){
        return image_path;
    }
    private String getAudio_path(){
        return audio_path;
    }

    private void setSong_name(String song_name){
        this.song_name = song_name;
    }
    private void setArtist(String artist){
        this.artist = artist;
    }
    private void setAlbum(String album){
        this.album = album;
    }
    private void setImage_path(String image_path){
        this.image_path = image_path;
    }
    private void setAudio_path(String audio_path){
        this.audio_path = audio_path;
    }




}
