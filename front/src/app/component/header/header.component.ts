import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  showNav = false;

  toggleNav() {
    this.showNav = !this.showNav;
  }

  constructor() { }

  ngOnInit(): void {



  }

}
