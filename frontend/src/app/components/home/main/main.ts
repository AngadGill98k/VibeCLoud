import { CommonModule } from '@angular/common';
import { FetchBackend } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Service } from '../../service';
import { Playlist } from '../navbar/playlist/playlist';

@Component({
  selector: 'app-main',
  imports: [CommonModule,FormsModule,Playlist],
  templateUrl: './main.html',
  styleUrl: './main.scss'
})


export class Main {
  protected search:String="";
  protected songs:any[]=[]
  constructor(private router:Router,protected service:Service){

  }

  searchSong($event:Event){
    let value = ($event.target as HTMLInputElement).value;
    if (value === '\n') {
    console.log('Enter pressed ,serachng for: ',value);
    this.searchSongButton();
    }
  }
  searchSongButton(){
    console.log("serachng for:",this.search);
    fetch(`?search=${this.search}`,{
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(res=>res.json())
    .then(data=>{
      console.log(data);
      if(data.msg){
        //this.router.navigate(['search'],{state:{song: data.songs}});
      }
    })
    .catch(error => {
      console.error(error);
    });
  }
  
  handleSong(song:any){
    console.log("clicked on song: ",song);
    //this.router.navigate(['player'],{state:{song: song}});
  }
}
