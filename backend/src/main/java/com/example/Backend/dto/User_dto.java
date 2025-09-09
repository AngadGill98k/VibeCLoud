package com.example.Backend.dto;

import java.util.ArrayList;

public class User_dto {
    private String id;
    private String name;
    private String mail;
    private ArrayList<String> albums;
    private ArrayList<String> playlists;


    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getId(){
        return id;
    }

    public ArrayList<String> getAlbums() {
        return albums;
    }

    public ArrayList<String> getPlaylists() {
        return playlists;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAlbums(String albums) {
        this.albums.add(albums);
    }

    public void setPlaylists(ArrayList<String> playlists) {
        this.playlists = playlists;
    }
}
