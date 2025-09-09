import { Injectable, signal } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class Service {
    public show_playlist = signal<boolean>(false)
    toogle_playlist() {
        console.log("open playlist", this.show_playlist());
        this.show_playlist.set(!this.show_playlist())
    }


    public c_album = signal<boolean>(false)
    toogle_c_album() {
        console.log("open create album", this.c_album());
        this.c_album.set(!this.c_album())
    }

    async csrf_token() {
        fetch(`http://localhost:8080/csrf-token`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error(error);
            });
    }

    async refresh_token() {
        fetch(`http://localhost:8080/refresh`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error(error);
            });
    }
}