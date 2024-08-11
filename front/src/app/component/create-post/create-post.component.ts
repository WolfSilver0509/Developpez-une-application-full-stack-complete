import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TopicService } from '../../services/topic.service';
import { PostService } from '../../services/post.service';
import {Topic} from "../../interfaces/topic.interface";
import {MatSnackBar} from "@angular/material/snack-bar";



@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {
  public postForm: FormGroup;
  public topics: Topic[] = [];

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
    this.topicService.getAllTopics().subscribe({
      next: (response: any) => {
        this.topics = response.topics;
      },
      error: (error: any) => {
        console.error('Erreur lors du chargement des topics', error);
      },
    });
  }


  public onSubmit(): void {
    // Vérification si le formulaire est valide
    if (this.postForm.valid) {
      const formData = {
        topic_id: Number(this.postForm.value.topic_id),
        title: this.postForm.value.title,
        description: this.postForm.value.description
      };

      console.log('Form Data Submitted:', formData); // Vérifier les données envoyées garce au log debuggage pour le probléme des données envoyés

      // Appel au service pour créer le post
      this.postService.createPost(formData).subscribe({
        next: () => {
          this.showSuccessMessage('Post created successfully!');
          this.router.navigate(['/posts']);
        },
        error: (error: any) => {
          console.error('Erreur lors de la création du post', error);
          this.showErrorMessage('Failed to create post!');
        }
      });
    } else {
      console.warn('Form is invalid');
    }
  }

  private showSuccessMessage(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['snackbar-success']
    });
  }

  private showErrorMessage(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 3000,
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['snackbar-error']
    });
  }

}
