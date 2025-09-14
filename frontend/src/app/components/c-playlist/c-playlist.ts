import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Service } from '../service';

@Component({
  selector: 'app-c-playlist',
  imports: [CommonModule,FormsModule],
  templateUrl: './c-playlist.html',
  styleUrl: './c-playlist.scss'
})
export class CPlaylist {
  protected playlist_name: string  = ""
  protected image: File | null = null

  constructor(private modal: MatDialogRef<CPlaylist>,private service:Service) { }

  close() {
    this.modal.close()
  }
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.image = input.files[0];
    }
  }
  createPlaylist() {
    let formdata = new FormData();
    formdata.append('playlist_name', this.playlist_name)
    formdata.append('image', this.image as Blob)

    fetch(`http://localhost:8080/create_playlist`,{
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
