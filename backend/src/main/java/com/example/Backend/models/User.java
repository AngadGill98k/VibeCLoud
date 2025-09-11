package com.example.Backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String mail;
    private String pass;
    private ArrayList<String> albums=new ArrayList<>();
    private ArrayList<String> playlists=new ArrayList<>();

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPaas(String paas) {
        this.pass = paas;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAlbums(String albums) {
        this.albums.add(albums);
    }

    public void setPlaylists(String playlists) {
        this.playlists.add(playlists);
    }
}

