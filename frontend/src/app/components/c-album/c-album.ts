import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

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


  constructor(private modal: MatDialogRef<CAlbum>) { }

  close() {
    this.modal.close()
  }
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.image = input.files[0];
    }
  }

  createAlbum() {
    let formdata = new FormData();
    formdata.append('album_name', this.album_name)
    formdata.append('artist_name', this.artist_name)
    formdata.append('genre', this.selectedGenre)
    formdata.append('image', this.image as Blob)
    console.log(formdata)
    fetch(``,{
      method: 'POST',
      body: formdata,
      headers:{
        'Content-Type': 'multipart/form-data'
      }
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
