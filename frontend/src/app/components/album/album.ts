import { Component, OnInit } from '@angular/core';
import { Playlist } from "../home/navbar/playlist/playlist";
import { Router } from '@angular/router';
import { Navbar } from "../home/navbar/navbar";
import { MatDialog } from '@angular/material/dialog';
import { ASongs } from '../a-songs/a-songs';
import { Service } from '../service';

@Component({
  selector: 'app-album',
  imports: [Playlist, Navbar],
  templateUrl: './album.html',
  styleUrl: './album.scss'
})
export class Album implements OnInit{
  album:any;

  constructor(private router:Router,private modal:MatDialog,private service:Service){}
  ngOnInit(): void {
      this.album=history.state.album;
      console.log(this.album);
      fetch(`http://localhost:8080/get_album_song?albumid=${this.album.id}`,{
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


  addsong(){
    this.modal.open(ASongs,{width:"400px",height:"400px",data:{album: this.album}});
  }
}
