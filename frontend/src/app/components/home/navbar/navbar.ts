import { Component } from '@angular/core';
import { Playlist } from './playlist/playlist';
import { Service } from '../../service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  imports: [Playlist],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss'
})
export class Navbar {
 
  constructor(public service:Service,private router:Router){}
  openPlaylist(){
    
    this.service.toogle_playlist()
  }
  dashboard(){
    this.router.navigate(['dashboard'])
  }
}
