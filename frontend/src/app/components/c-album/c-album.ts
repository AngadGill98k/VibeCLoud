import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Service } from '../service';

@Component({
  selector: 'app-c-album',
  imports: [CommonModule, FormsModule],
  templateUrl: './c-album.html',
  styleUrl: './c-album.scss'
})
export class CAlbum {
  protected album_name: string  = ""
  protected artist_name: string  = ""
  protected selectedGenre: string  = ""
  protected image: File | null = null


  constructor(private modal: MatDialogRef<CAlbum>,private service:Service) { }

  close() {
    this.modal.close()
  }
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.image = input.files[0];
    }
  }

  async createAlbum() {
    let formdata = new FormData();
    console.log(this.album_name, this.artist_name, this.selectedGenre)
    formdata.append('album_name', this.album_name)
    formdata.append('artist_name', this.artist_name)
    formdata.append('genre', this.selectedGenre)
    formdata.append('image', this.image as Blob)
    // console.log(this.service.access_token,this.service.get_csrf());
    fetch(`http://localhost:8080/create_album`,{
      method: 'POST',
      body: formdata,
      headers:{
        
        'Authorization': `Bearer ${this.service.access_token}`,
        'X-CSRF-Token': this.service.get_csrf() ?? ""
      },
      credentials: 'include'
    })
    .then(res=>res.json())
    .then(data=>{
      console.log(data);
      if(data.msg){
        
      }
    })
    .catch(error => {
      console.error(error);
    });
  }
}
