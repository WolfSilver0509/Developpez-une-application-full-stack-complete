import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TopicService } from '../../services/topic.service';
import { PostService } from '../../services/post.service';
import { Topic } from "../../interfaces/topic.interface";
import { MatSnackBar } from "@angular/material/snack-bar";
import { TopicResponse } from "../../interfaces/topicResponse.interface";
import { HttpErrorResponse } from "@angular/common/http";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit, OnDestroy {
  public postForm: FormGroup;
  public topics: Topic[] = [];

  // Propriétés publiques pour stocker les abonnements
  public topicsSubscription!: Subscription;
  public postSubscription!: Subscription;

  constructor(
    private fb: FormBuilder,
    private topicService: TopicService,
    private postService: PostService,
    private router: Router,
    private snackBar: MatSnackBar
  ) {
    this.postForm = this.fb.group({
      topic_id: [null, Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.loadTopics();
  }

  private loadTopics(): void {
    // Stocker l'abonnement pour charger les topics
    this.topicsSubscription = this.topicService.getAllTopics().subscribe({
      next: (response: TopicResponse) => {
        this.topics = response.topics;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors du chargement des topics', error);
      },
    });
  }

  public onSubmit(): void {
    if (this.postForm.valid) {
      const formData = new FormData();
      formData.append("topic_id", Number(this.postForm.value.topic_id) + "");
      formData.append("title", this.postForm.value.title);
      formData.append("description", this.postForm.value.description);

      // Stocker l'abonnement pour créer un post
      this.postSubscription = this.postService.createPost(formData).subscribe({
        next: () => {
          this.showSuccessMessage('Post créé avec succès!');
          this.router.navigate(['/posts']);
        },
        error: (error: HttpErrorResponse) => {
          console.error('Erreur lors de la création du post', error);
          this.showErrorMessage('Échec de la création du post!');
        }
      });
    } else {
      console.warn('Le formulaire est invalide');
    }
  }

  private showSuccessMessage(message: string): void {
    this.snackBar.open(message, 'Fermer', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['snackbar-success']
    });
  }

  private showErrorMessage(message: string): void {
    this.snackBar.open(message, 'Fermer', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['snackbar-error']
    });
  }

  // Désabonnement dans ngOnDestroy
  ngOnDestroy(): void {
    if (this.topicsSubscription) {
      this.topicsSubscription.unsubscribe();
    }
    if (this.postSubscription) {
      this.postSubscription.unsubscribe();
    }
  }
}
