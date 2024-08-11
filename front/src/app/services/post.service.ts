import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, map, tap} from 'rxjs';
import { Post } from '../interfaces/post.interface';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private readonly basePath = 'http://localhost:5656/api/posts';

  constructor(private httpClient: HttpClient) {
  }

  public getAllPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.basePath).pipe(
      map(posts => posts.map(post => ({
        id: post.id,
        title: post.title,
        description: post.description,
        owner_id: post.owner_id,
        created_at: post.created_at,

      })))
    );
  }

  public createPost(postData: { topic_id: number; title: string; description: string }): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  return this.httpClient.post(this.basePath, postData, { headers });
  }



}
