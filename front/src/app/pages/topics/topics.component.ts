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
    this.sessionService.$isLogged().subscribe(isLogged => {
    });
  }

}
