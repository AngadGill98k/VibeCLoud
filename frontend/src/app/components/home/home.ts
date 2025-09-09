import { Component, OnInit } from '@angular/core';
import { Navbar } from './navbar/navbar';
import { Main } from "./main/main";
import { Service } from '../service';

@Component({
  selector: 'app-home',
  imports: [Navbar, Main],
  templateUrl: './home.html',
  styleUrl: './home.scss'
})
export class Home implements OnInit {

  ngOnInit(): void {
    this.service.refresh_token()
  }
  constructor(private service:Service) { }
}
