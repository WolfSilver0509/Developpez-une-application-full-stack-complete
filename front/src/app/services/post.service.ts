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

  public createPost(postData: FormData): Observable<string> {
  return this.httpClient.post<string>(this.basePath, postData );
  }




}
