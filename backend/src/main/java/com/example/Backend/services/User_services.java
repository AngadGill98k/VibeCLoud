package com.example.Backend.services;

import com.example.Backend.dto.Response;
import com.example.Backend.dto.User_dto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class User_services {
    public Response<User_dto> create_album(MultipartFile[] files,String artist_name,String album_name,String genre){
        Response<User_dto> res=new Response<>();
        return res;
    }

}
