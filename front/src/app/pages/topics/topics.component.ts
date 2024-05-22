import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../services/session.service';
import { User } from '../../interfaces/user.interface';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit {

  public user: User | undefined;

  constructor(private sessionService: SessionService) { }

  ngOnInit(): void {
    console.log('Initialisation du composant TopicsComponent');
    this.sessionService.$isLogged().subscribe(isLogged => {
      if (isLogged && this.sessionService.user) {
        this.user = this.sessionService.user;
        console.log('Logged-in user:', this.user.name);
      }
    });
  }
}
