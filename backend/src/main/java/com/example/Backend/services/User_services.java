package com.example.Backend.services;

import com.example.Backend.dto.Response;
import com.example.Backend.dto.User_dto;
import org.springframework.stereotype.Service;

@Service
public class User_services {
    public Response<User_dto> create_album(){
        Response<User_dto> res=new Response<>();
        return res;
    }

}
