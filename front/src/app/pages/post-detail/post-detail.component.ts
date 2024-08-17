import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/post.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostDetail } from '../../interfaces/post-detail.interface';  // Remplacez 'Post' par 'PostDetail'

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit {
  public post?: PostDetail;  // Remplacez 'Post' par 'PostDetail'
  public newComment: string = '';

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    const postId = Number(this.route.snapshot.paramMap.get('id'));
    if (postId) {
      this.postService.getPostById(postId).subscribe({
        next: (post: PostDetail) => {  // Remplacez 'Post' par 'PostDetail'
          this.post = post;
        },
        error: (error: any) => {
          console.error('Erreur lors du chargement du post', error);
        },
      });
    }
  }

  public submitComment(): void {
    if (this.post) {
      this.postService.createComment(this.post.id, this.newComment).subscribe({
        next: (response: any) => {
          this.snackBar.open(response.message, 'Fermer', { duration: 3000 });
          this.newComment = '';
          this.ngOnInit(); // Recharger les commentaires après l'ajout
        },
        error: (error: any) => {
          console.error('Erreur lors de l\'ajout du commentaire', error);
        },
      });
    }
  }
}
