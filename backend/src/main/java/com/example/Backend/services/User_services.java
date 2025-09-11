package com.example.Backend.services;

import com.example.Backend.config.Log;
import com.example.Backend.dto.Response;
import com.example.Backend.models.Albums;
import com.example.Backend.models.Playlists;
import com.example.Backend.models.User;
import com.example.Backend.repository.Album_repo;
import com.example.Backend.repository.Playlist_repo;
import com.example.Backend.repository.User_repo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class User_services {
    private final Album_repo album_repo;
    private User_repo user_repo;
    private Playlist_repo playlist_repo;

    User_services(User_repo user_repo, Album_repo album_repo,Playlist_repo playlist_repo) {
        this.user_repo = user_repo;
        this.album_repo = album_repo;
        this.playlist_repo = playlist_repo;
    }


    public Response<Albums> create_album(MultipartFile[] files, String artist_name, String album_name, String genre, String userid){
        Response<Albums> res = new Response<>();
        try {

            Optional<User> user = user_repo.findById(userid);
            Log.log.info("(services.User.create_album) userid {}", userid);
            Albums album=new Albums();
            album.setArtist_name(artist_name);
            album.setAlbum_name(album_name);
            album.setGenre(genre);
            album.setArtist_id(user.get().getId());

            Path project_dir= Paths.get("").toAbsolutePath();
            Path uploads=project_dir.resolve("uploads");
            File upload=uploads.toFile();
            if(!upload.exists()){
                upload.mkdir();
            }
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                Log.log.info("(services.User.create_album) files rec {}", file.getOriginalFilename());
                File destination=new File(upload,file.getOriginalFilename());
                file.transferTo(destination);
                album.setImage_path(file.getOriginalFilename());
            }

            album_repo.save(album);
            user.get().setAlbums(album.getId());
            user_repo.save(user.get());

            res.setMsg(true);
            res.setMessage("saved album and updated user");
            res.setData(album);
            return res;
        }catch (Exception e){
            res.setMsg(false);
            res.setMessage(e.getMessage());
            return res;
        }
    }

    public Response<ArrayList<Albums>> get_user_albums(String userid){
        Response<ArrayList<Albums>> res = new Response<>();
        Log.log.info("req recived at (servic.USer_services.get_user_albums)");
        try {
            Log.log.info("req recived at (servic.USer_services.get_user_albums)");
            User user = user_repo.findById(userid).get();

            ArrayList<String> albums_id =user.getAlbums();
            ArrayList<Albums> albums=new ArrayList<>();
            for (int i = 0; i < albums_id.size(); i++) {
                String id = albums_id.get(i);
                Optional<Albums> albumOpt = album_repo.findById(id);
                albumOpt.ifPresent(albums::add); //
            }
            res.setMsg(true);
            res.setData(albums);
            res.setMessage("albums found");
            return res;
        }catch (Exception e){
            res.setMsg(false);
            res.setMessage(e.getMessage());
            e.printStackTrace();
            return res;
        }
    }

    public Response<Playlists> create_playlist(String userid,String playlistName,MultipartFile[] files){
        Response<Playlists> res = new Response<>();
        try {
            User user = user_repo.findById(userid).get();
            Playlists playlist=new Playlists();
            playlist.setPlaylistName(playlistName);
//            playlist.setPlaylistImage();
            Path project_dir= Paths.get("").toAbsolutePath();
            Path uploads=project_dir.resolve("uploads");
            File upload=uploads.toFile();
            if(!upload.exists()){
                upload.mkdir();
            }
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                Log.log.info("(services.User.create_album) files rec {}", file.getOriginalFilename());
                File destination=new File(upload,file.getOriginalFilename());
                file.transferTo(destination);
                playlist.setPlaylistImage(file.getOriginalFilename());
            }


            playlist_repo.save(playlist);
            user.setPlaylists(playlist.getId());
            user_repo.save(user);
            res.setMsg(true);
            res.setMessage("playlist created");
            res.setData(playlist);
            return res;
        }catch (Exception e){
            res.setMsg(false);
            res.setMessage(e.getMessage());
            return res;
        }
    }

    public Response<ArrayList<Playlists>> get_user_playlists(String userid){
        Response<ArrayList<Playlists>> res = new Response<>();
        Log.log.info("req recived at (servic.USer_services.get_user_playlist)");
        try {
            User user = user_repo.findById(userid).get();

            ArrayList<String> playlists_id =user.getPlaylists();
            ArrayList<Playlists> playlist =new ArrayList<>();
            for (int i = 0; i < playlists_id.size(); i++) {
                String id = playlists_id.get(i);
                Optional<Playlists> playlistsOpt = playlist_repo.findById(id);
                playlistsOpt.ifPresent(playlist::add); //
            }
            res.setMsg(true);
            res.setData(playlist);
            res.setMessage("Playlists found");
            return res;
        }catch (Exception e){
            res.setMsg(false);
            res.setMessage(e.getMessage());
            e.printStackTrace();
            return res;
        }
    }

}
