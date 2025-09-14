import { ChangeDetectorRef, Component, OnInit, Signal } from '@angular/core';
import { Navbar } from "../home/navbar/navbar";
import { Service } from '../service';
import { MatDialog } from '@angular/material/dialog';
import { CAlbum } from '../c-album/c-album';
import { CPlaylist } from '../c-playlist/c-playlist';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dashboard',
  imports: [Navbar],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard implements OnInit {
  protected albums:any[]=[]
  protected playlist:any[]=[]
  

  ngOnInit(): void {
       fetch(`http://localhost:8080/get_user_albums`,{
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        },
        credentials: 'include'
      })
      .then(res=>res.json())
      .then(data=>{
        console.log(data.data);
        if(data.msg){
          this.albums.push(...data.data);
          this.cd.detectChanges();
        }
      })
      .catch(error => {
        console.error(error);
      });

      fetch(`http://localhost:8080/get_user_playlist`,{
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        },
        credentials: 'include'
      })
      .then(res=>res.json())
      .then(data=>{
        console.log(data.data);
        if(data.msg){
          this.playlist.push(...data.data);
          this.cd.detectChanges();
        }
      })
      .catch(error => {
        console.error(error);
      });
  }
  constructor(protected service:Service,private modal:MatDialog,private cd:ChangeDetectorRef,private router:Router){}
  createAlbum(){
    console.log("create album")
    this.modal.open(CAlbum,{width:"400px",height:"400px"})
    // this.service.toogle_c_album()
  }
  createPlaylist(){
    this.modal.open(CPlaylist,{width:"400px",height:"400px"})
    console.log("create playlist")
  }
  openAlbum(album:any){
    console.log(album);
    this.router.navigate(['album'],{state:{album: album}});
  }
  openPlaylist(playlist:any){
    console.log(playlist);
    this.router.navigate(['playlist'],{state:{playlist: playlist}});
  }
}
