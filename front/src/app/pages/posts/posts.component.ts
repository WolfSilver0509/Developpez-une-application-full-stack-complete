import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { User } from '../../interfaces/user.interface';
import { SessionService } from '../../services/session.service';
import { Post } from "../../interfaces/post.interface";
import { Router } from '@angular/router';
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {
  public posts: Post[] = [];
  sortedPosts: Post[] = [];
  sortOrder: 'asc' | 'desc' = 'asc'; // Ordre de tri : ascendant ou descendant

  constructor(
    private postService: PostService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadPosts();
  }

  private loadPosts(): void {
    this.postService.getAllPosts().subscribe({
      next: (posts: Post[]) => {
        this.posts = posts;
        this.sortPosts(); // Trier les articles après le chargement
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors du chargement des posts', error);
      },
    });
  }

  // Fonction pour inverser l'ordre de tri et trier les articles
  toggleSortOrder(): void {
    this.sortOrder = this.sortOrder === 'asc' ? 'desc' : 'asc';
    this.sortPosts();
  }

  // Fonction de tri des articles
  private sortPosts(): void {
    this.sortedPosts = this.posts.slice().sort((a, b) => {
      const dateA = new Date(a.created_at).getTime();
      const dateB = new Date(b.created_at).getTime();

      return this.sortOrder === 'asc' ? dateA - dateB : dateB - dateA;
    });
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }

  navigateToCreatePost(): void {
    this.router.navigate(['/create-post']);
  }

  navigateToPostDetail(postId: number): void {
    this.router.navigate([`/post-detail/${postId}`]);
  }
}
