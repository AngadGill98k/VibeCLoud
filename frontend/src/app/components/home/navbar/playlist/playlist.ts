import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CPlaylist } from '../../../c-playlist/c-playlist';

@Component({
  selector: 'app-playlist',
  imports: [],
  templateUrl: './playlist.html',
  styleUrl: './playlist.scss'
})
export class Playlist {
  protected playlist:any[]=[]

  constructor(private modal:MatDialog){}
  createPlaylist(){
      this.modal.open(CPlaylist,{width:"400px",height:"400px"})
      console.log("create playlist")
    }
}
