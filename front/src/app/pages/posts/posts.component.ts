import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { User } from '../../interfaces/user.interface';
import { SessionService } from '../../services/session.service';
import {Post} from "../../interfaces/post.interface";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {
  public posts: Post[] = [];

  constructor(
    private postService: PostService,
    private sessionService: SessionService
  ) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  private loadPosts(): void {
    this.postService.getAllPosts().subscribe({
      next: (posts: Post[]) => {
        this.posts = posts;
      },
      error: (error: any) => {
        console.error('Erreur lors du chargement des posts', error);
      },
    });
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}
