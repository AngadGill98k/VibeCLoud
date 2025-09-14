package com.example.Backend.services;

import com.example.Backend.config.Log;
import com.example.Backend.dto.Response;
import com.example.Backend.models.Albums;
import com.example.Backend.models.Playlists;
import com.example.Backend.models.Song;
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

    public Response get_albums(){
        Response res = new Response();


        return res;
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

                // 1. Get original filename
                String originalName = file.getOriginalFilename();

                // 2. Sanitize: replace spaces and remove unsafe characters
                String safeName = originalName.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");

                // 3. Add timestamp prefix to avoid duplicates
                String finalName = System.currentTimeMillis() + "_" + safeName;

                // 4. Save file
                File destination = new File(upload, finalName);
                file.transferTo(destination);

                // 5. Save safe filename in DB
                album.setImage_path(finalName);
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

    public Response<Playlists> create_playlist(String playlistName,String userid,MultipartFile[] files){
        Response<Playlists> res = new Response<>();

        try {Log.log.info("{}",userid);
            User user = user_repo.findById(userid).get();

            Playlists playlist=new Playlists();
            playlist.setPlaylistName(playlistName);
            Log.log.info("{}",playlist);
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
                String originalName = file.getOriginalFilename();

// Sanitize: replace spaces with underscores, remove special chars if needed
                String safeName = originalName.replaceAll("\\s+", "_");

// Optional: prepend timestamp to avoid duplicate filenames
                safeName = System.currentTimeMillis() + "_" + safeName;

// Save file with safe name
                File destination = new File(upload, safeName);
                file.transferTo(destination);

// Store safe name in DB
                playlist.setPlaylistImage(safeName);
            }


            playlist_repo.save(playlist);
            user.setPlaylists(playlist.getId());
            user_repo.save(user);
            res.setMsg(true);
            res.setMessage("playlist created");
            res.setData(playlist);
            return res;
        }catch (Exception e){
            Log.log.error(e.getMessage());
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

    public Response<Albums> add_song_album(String album_name,String userid,String song_name,String genre,String album_id,MultipartFile[] files,MultipartFile[] audios){
        Response<Albums> res=new Response();
        try {
            Optional<Albums> albums = album_repo.findById(album_id);
            Albums album=albums.get();
            Song song=new Song();
            song.setArtistId(userid);
            song.setSong_name(song_name);
            song.setArtistName(album.getArtist_name());
            song.setAlbum(album_name);

            Path project_dir= Paths.get("").toAbsolutePath();
            Path uploads=project_dir.resolve("uploads");
            File upload=uploads.toFile();
            if(!upload.exists()){
                upload.mkdir();
            }
            for (MultipartFile audio : audios) {
                // 1. Get original filename
                String originalName = audio.getOriginalFilename();

                // 2. Sanitize: replace spaces and remove unsafe characters
                String safeName = originalName.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");

                // 3. Add timestamp prefix to avoid duplicates
                String finalName = System.currentTimeMillis() + "_" + safeName;

                // 4. Save file
                File destination = new File(upload, finalName);
                audio.transferTo(destination);


                song.setAudio_path(finalName);
            }
            for (MultipartFile file : files) {
                // 1. Get original filename
                String originalName = file.getOriginalFilename();

                // 2. Sanitize: replace spaces and remove unsafe characters
                String safeName = originalName.replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");

                // 3. Add timestamp prefix to avoid duplicates
                String finalName = System.currentTimeMillis() + "_" + safeName;

                // 4. Save file
                File destination = new File(upload, finalName);
                file.transferTo(destination);


                song.setImage_path(finalName);
            }
            album.setSongs(song);
            album_repo.save(album);
            res.setMsg(true);
            res.setMessage("saved song in album");
            res.setData(album);
            return res;
        }catch(Exception e){

            res.setMsg(false);
            res.setMessage(e.getMessage());
            Log.log.error(e.getMessage());
            return res;
        }
    }

    public Response<Albums> get_songs_album(String albumid){
        Response<Albums> res= new Response();
        try{
            Log.log.info("req cam in (service.User.get_songs_album) ");
            Optional<Albums> albums=album_repo.findById(albumid);
            Albums album=albums.get();
            res.setMessage("found th album");
            res.setMsg(true);
            res.setData(album);
            return res;
        }catch(Exception e){
            res.setMsg(false);
            res.setMessage(e.getMessage());
            Log.log.error(e.getMessage());
            return res;
        }

    }

    public Response<Playlists> get_songs_playlist(String playlistid){
        Response<Playlists> res=new Response<Playlists>();
        try{
            Optional<Playlists>playlists=playlist_repo.findById(playlistid);
            Playlists playlist =playlists.get();
            res.setMessage("found the songs int eh playlist");
            res.setMsg(true);
            res.setData(playlist);
            return res;
        }catch(Exception e){
            res.setMsg(true);
            res.setMessage(e.getMessage());
            Log.log.error(e.getMessage());
            return res;
        }
    }

}