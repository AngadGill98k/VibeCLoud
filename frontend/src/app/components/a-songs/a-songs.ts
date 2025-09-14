import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Service } from '../service';

@Component({
  selector: 'app-a-songs',
  imports: [CommonModule,FormsModule],
  templateUrl: './a-songs.html',
  styleUrl: './a-songs.scss'
})
export class ASongs {
  protected song_name:string=""
  protected image:File|null=null
  protected selectedGenre:string=""
  protected audio:File|null=null
  
  constructor(private model:MatDialogRef<ASongs>,private service:Service,@Inject(MAT_DIALOG_DATA) public data: any ){}

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.image = input.files[0];
    }
  }
  onAudioSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.audio = input.files[0];
    }
  }
  close(){
    this.model.close()
  }

  AddSong(){
    let formdata = new FormData();
    console.log(this.data.album)
    formdata.append('album_name', this.data.album.album_name)
    formdata.append('song_name', this.song_name)
    formdata.append('image', this.image as Blob)
    formdata.append('genre', this.selectedGenre)
    formdata.append('album_id', this.data.album.id)
    formdata.append('audio', this.audio as Blob)
    fetch("http://localhost:8080/add_song",{
      method: 'POST',
      body: formdata,
      headers: {
        'Authorization': `Bearer ${this.service.access_token}`,
        'X-CSRF-Token': this.service.get_csrf() ?? ""
      },
      credentials: 'include'
    })
    .then(res=>res.json())
    .then(data=>console.log(data))
    .catch(error => {
      console.error(error);
    });
  }
  }

