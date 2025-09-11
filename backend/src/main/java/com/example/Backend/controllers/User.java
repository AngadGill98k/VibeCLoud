package com.example.Backend.controllers;


import com.example.Backend.config.Log;
import com.example.Backend.dto.Response;
import com.example.Backend.models.Albums;
import com.example.Backend.models.Playlists;
import com.example.Backend.services.User_services;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class User {
    private User_services user_sevices;
    public User(User_services User_services) {
        user_sevices = User_services;
    }

    @PostMapping("/create_album")
    public Response<Albums> create_album(@RequestParam("image") MultipartFile[] files,
                                           @RequestParam("album_name") String album_name,
                                           @RequestParam("artist_name")String artist_name,
                                           @RequestParam("genre")String genre,
                                            HttpServletRequest req){
        String userid=req.getAttribute("userid").toString();
        Log.log.info("data recived (User.create_album) {} {} {} {}",album_name,artist_name,genre,userid);
        Response<Albums> res= user_sevices.create_album(files,album_name,artist_name,genre,userid);
        return res;
    }

    @GetMapping("/get_user_albums")
    public Response<ArrayList<Albums>> get_user_albums(HttpServletRequest req){
        String userid=req.getAttribute("userid").toString();
        Response<ArrayList<Albums>> res= user_sevices.get_user_albums(userid);
        return res;
    }

    @PostMapping("/create_playlist")
    public Response<Playlists> create_playlist(@RequestParam("image") MultipartFile[] files,
                                               @RequestParam("playlist_name")String playlist_name,
                                               HttpServletRequest req){
        String userid=req.getAttribute("userid").toString();
        Log.log.info("data recived (User.create_playlist) {} {}", playlist_name,userid);
        Response<Playlists> res= user_sevices.create_playlist(playlist_name,userid,files);
        return res;
    }

    @GetMapping("/get_user_playlist")
    public Response<ArrayList<Playlists>> get_user_playlist(HttpServletRequest req){
        String userid=req.getAttribute("userid").toString();
        Response<ArrayList<Playlists>> res= user_sevices.get_user_playlists(userid);
        return res;
    }

}
