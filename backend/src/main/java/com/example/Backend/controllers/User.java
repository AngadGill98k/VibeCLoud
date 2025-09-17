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
    private final User_services user_services;
    private User_services user_sevices;
    public User(User_services User_services, User_services user_services) {
        user_sevices = User_services;
        this.user_services = user_services;
    }

    @GetMapping("/get_main_page")
    publiv Response<Object> get_main_page(){
        Log.log.info("req recieve at (controller.get_main_page)");
        Reponse res=user_services.get_main_page();
        return res;
    }
    
    @GetMapping("/get_albums")
    public Response get_albums(){
        Log.log.info("sending albums at (controller.get_albums)");
        Response res = new Response();
        return res;
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

    @GetMapping("/get_album_song")
    public Response<Albums> get_album_song(@RequestParam("albumid")String albumid){
        Response<Albums>res=user_services.get_songs_album(albumid);
        return res;
    }

    @PostMapping("/create_playlist")
    public Response<Playlists> create_playlist(@RequestParam("image") MultipartFile[] files,
                                               @RequestParam("playlist_name")String playlist_name,
                                               HttpServletRequest req){
        String userid=req.getAttribute("userid").toString();
        Log.log.info("data recived (User.create_playlist) {} {} {}", playlist_name,userid,files);
        Response<Playlists> res= user_sevices.create_playlist(playlist_name,userid,files);
        return res;
    }

    @GetMapping("/get_user_playlist")
    public Response<ArrayList<Playlists>> get_user_playlist(HttpServletRequest req){
        String userid=req.getAttribute("userid").toString();
        Log.log.info("data recived (User.get_user_playlist {})", userid);
        Response<ArrayList<Playlists>> res= user_sevices.get_user_playlists(userid);
        return res;
    }

    @GetMapping("/get_song_playlist")
    public Response<Playlists> get_songs_playlist(@RequestParam("playlistid") String playlistid) {
        Response<Playlists> res=user_services.get_songs_playlist(playlistid);
        return res;
    }

    @PostMapping("/add_song")
    public Response<Albums> add_song_album(HttpServletRequest req,
                                   @RequestParam("image") MultipartFile[] files,
                                   @RequestParam("song_name")String song_name,
                                   @RequestParam("genre") String genre,
                                   @RequestParam("album_id") String album_id,
                                   @RequestParam("audio") MultipartFile[] audio,
                                   @RequestParam("album_name")String album_name)
    {
        String userid=req.getAttribute("userid").toString();
        Log.log.info("req rec (controller.User.add_song_album)");
        Response<Albums> res=user_sevices.add_song_album(album_name,userid,song_name,genre,album_id,files,audio);
        return res;
    }


}
