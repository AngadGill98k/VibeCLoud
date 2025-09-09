import { Component } from '@angular/core';
import { Navbar } from "../home/navbar/navbar";
import { Service } from '../service';
import { MatDialog } from '@angular/material/dialog';
import { CAlbum } from '../c-album/c-album';
@Component({
  selector: 'app-dashboard',
  imports: [Navbar],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss'
})
export class Dashboard {
  protected albums:any[]=[]
  protected playlist:any[]=[]

  constructor(protected service:Service,private modal:MatDialog){}
  createAlbum(){
    console.log("create album")
    this.modal.open(CAlbum,{width:"400px",height:"400px"})
    // this.service.toogle_c_album()
  }
}
