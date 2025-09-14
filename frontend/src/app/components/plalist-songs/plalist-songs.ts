import { Component, OnInit } from '@angular/core';
import { Main } from "../home/main/main";
import { Navbar } from "../home/navbar/navbar";
import { CPlaylist } from "../c-playlist/c-playlist";
import { Service } from '../service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plalist-songs',
  imports: [Navbar, CPlaylist],
  templateUrl: './plalist-songs.html',
  styleUrl: './plalist-songs.scss'
})
export class PlalistSongs implements OnInit {
  playlist: any;

  constructor(private router: Router, protected service: Service) { }
  ngOnInit(): void {
    this.playlist = history.state.playlist;
    console.log(this.service.access_token);
    fetch(`http://localhost:8080/get_song_playlist?playlistid=${this.playlist.id}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${this.service.access_token}`,
        'X-CSRF-Token': this.service.get_csrf() ?? ""
      },
      credentials: 'include'
    })
    .then(res=>res.json())
    .then(data=>{
      console.log(data)
      if(data.msg){
        
      }
    })
    .catch(error => {
      console.error(error);
    });
  }
}
