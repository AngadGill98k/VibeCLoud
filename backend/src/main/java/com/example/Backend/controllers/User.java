package com.example.Backend.controllers;


import com.example.Backend.dto.Response;
import com.example.Backend.dto.User_dto;
import com.example.Backend.services.User_services;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class User {
    private User_services user_sevices;
    public User(User_services User_services) {
        user_sevices = User_services;
    }

    @PostMapping("/create_album")
    public Response<User_dto> create_album(){
        Response<User_dto> res= user_sevices.create_album();

        return res;
    }

}
