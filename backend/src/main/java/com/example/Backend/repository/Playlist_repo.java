package com.example.Backend.repository;

import com.example.Backend.models.Playlists;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Playlist_repo extends MongoRepository<Playlists,String> {
}
