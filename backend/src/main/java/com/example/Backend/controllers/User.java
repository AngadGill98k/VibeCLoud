package com.example.Backend.controllers;


import com.example.Backend.config.Log;
import com.example.Backend.dto.Response;
import com.example.Backend.dto.User_dto;
import com.example.Backend.services.User_services;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class User {
    private User_services user_sevices;
    public User(User_services User_services) {
        user_sevices = User_services;
    }

    @PostMapping("/create_album")
    public Response<User_dto> create_album(@RequestParam("image") MultipartFile[] files,
                                           @RequestParam("album_name") String album_name,
                                           @RequestParam("artist_name")String artist_name,
                                           @RequestParam("genre")String genre){
        //ake a mddleware in springboot to extact eh token auto tehn send it here
        Log.log.info("data recived (User.create_album) {} {} {}",album_name,artist_name,genre);
        Response<User_dto> res= user_sevices.create_album(files,album_name,artist_name,genre);

        return res;
    }

}
