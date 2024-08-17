import { Component, OnInit } from '@angular/core';
import {MeService} from "../../services/me.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  constructor(private meService: MeService, private router : Router) { }

  ngOnInit(): void {
  }

  onLogout(): void {
    this.meService.logout();
    this.router.navigate(['']);
  }

}
