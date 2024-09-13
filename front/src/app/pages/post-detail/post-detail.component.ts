import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from '../../services/post.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PostDetail } from '../../interfaces/post-detail.interface';
import {CommentResponse} from "../../interfaces/comment.interface";
import {HttpErrorResponse} from "@angular/common/http";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.scss']
})
export class PostDetailComponent implements OnInit, OnDestroy {
  public post?: PostDetail;
  public newComment: string = '';
  public subscriptionService!: Subscription;

  constructor(
    private route: ActivatedRoute,
    private postService: PostService,
    private snackBar: MatSnackBar
  ) {}


  ngOnInit(): void {
    const postId = Number(this.route.snapshot.paramMap.get('id'));
    if (postId) {
      this.postService.getPostById(postId).subscribe({
        next: (post: PostDetail) => {
          this.post = post;
        },
        error: (error: HttpErrorResponse) => {
          console.error('Erreur lors du chargement du post', error);
        },
      });
    }
  }

  public submitComment(): void {
    if (this.post && this.newComment ) {
      const formData = new FormData();
      formData.append("message",this.newComment);
      formData.append("post_id",Number(this.post.id)+"");

      this.subscriptionService = this.postService.createComment(formData).subscribe({
        next: (response: CommentResponse) => {
          this.snackBar.open(response.message, 'Fermer', { duration: 3000 });
          this.newComment = '';
          this.ngOnInit();
        },
        error: (error: HttpErrorResponse) => {
          console.error('Erreur lors de l\'ajout du commentaire', error);
        },
      });
    }

  }

  ngOnDestroy(): void {
    if (this.subscriptionService){
    this.subscriptionService.unsubscribe();
    }
  }

}
