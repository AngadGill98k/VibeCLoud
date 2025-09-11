package com.example.Backend.repository;

import com.example.Backend.models.Albums;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Album_repo extends MongoRepository<Albums,String> {

}
