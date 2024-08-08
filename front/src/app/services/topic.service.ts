import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic} from "../interfaces/topic.interface";
import {TopicResponse} from "../interfaces/topicResponse.interface";



@Injectable({
  providedIn: 'root'
})
export class TopicService {

  private basePath = 'http://localhost:5656';
  private pathService = 'api/topics';

  constructor(private httpClient: HttpClient) { }

  public all(): Observable<TopicResponse> {
    return this.httpClient.get<TopicResponse>(`${this.basePath}/${this.pathService}`);
  }

}
