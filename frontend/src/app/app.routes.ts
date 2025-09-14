import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Home } from './components/home/home';
import { Dashboard } from './components/dashboard/dashboard';
import { Playlist } from './components/home/navbar/playlist/playlist';
import { PlalistSongs } from './components/plalist-songs/plalist-songs';
import { Album } from './components/album/album';

export const routes: Routes = [
    {path:'',component:Login},
    {path:'home',component:Home},
    {path:'dashboard',component:Dashboard},
    {path:"album",component:Album},
    {path:"playlist",component:PlalistSongs}
];
