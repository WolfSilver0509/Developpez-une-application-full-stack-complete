import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, map, tap} from 'rxjs';
import { Post } from '../interfaces/post.interface';
import {PostDetail} from "../interfaces/post-detail.interface";
import {CommentResponse} from "../interfaces/comment.interface";

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private readonly basePath = 'http://localhost:5656/api/posts';
  private readonly commentsPath = 'http://localhost:5656/api/comments';

  constructor(private httpClient: HttpClient) {
  }

  public getAllPosts(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.basePath).pipe(
      map(posts => posts.map(post => ({
        id: post.id,
        title: post.title,
        description: post.description,
        owner_id: post.owner_id,
        author: post.author,
        created_at: post.created_at,
      })))
    );
  }

  public createPost(postData: FormData): Observable<string> {
  return this.httpClient.post<string>(this.basePath, postData );
  }

  public getPostById(id: number): Observable<PostDetail> {
    return this.httpClient.get<PostDetail>(`${this.basePath}/${id}`);
  }

  // public createComment(postId: number, message: string): Observable<any> {
  //   const commentsPath = 'http://localhost:5656/api/comments';
  //   return this.httpClient.post<any>(commentsPath, {
  //     post_id: postId,
  //     message: message,
  //   });
  // }

  public createComment(commentary : FormData): Observable<CommentResponse> {
  return this.httpClient.post<CommentResponse>(this.commentsPath, commentary)
  }

}
