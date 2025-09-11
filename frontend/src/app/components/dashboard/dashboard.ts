import { Component, OnInit } from '@angular/core';
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
export class Dashboard implements OnInit {
  protected albums:any[]=[]
  protected playlist:any[]=[]


  async ngOnInit(): Promise<void> {
      await fetch(`http://localhost:8080/get_user_albums`,{
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
          this.albums=data.data;
        }
      })
      .catch(error => {
        console.error(error);
      });
  }
  constructor(protected service:Service,private modal:MatDialog){}
  createAlbum(){
    console.log("create album")
    this.modal.open(CAlbum,{width:"400px",height:"400px"})
    // this.service.toogle_c_album()
  }
}
