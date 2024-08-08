import { Component, OnInit } from '@angular/core';
import { User} from "../../interfaces/user.interface";
import { SessionService} from "../../services/session.service";
import {TopicService} from "../../services/topic.service";
import {Observable} from "rxjs";
import {Topic} from "../../interfaces/topic.interface";

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss']
})
export class ListTopicComponent implements OnInit {

  public  topics$ = this.topicService.all();

  constructor(
    private sessionService: SessionService,
  private topicService: TopicService)
  {  }

  ngOnInit(): void {
    console.log('Initialisation du composant ListTopicComponent');
    console.log(this.topics$)
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}
