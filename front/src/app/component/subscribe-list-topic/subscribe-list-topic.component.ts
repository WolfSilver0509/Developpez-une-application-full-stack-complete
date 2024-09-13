import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../features/auth/services/auth.service';
import { TopicService } from '../../services/topic.service';
import { TopicWithSubscriptionStatus } from '../../interfaces/topicWithSubscriptionStatus.interface';
import { HttpErrorResponse } from "@angular/common/http";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-subscribe-list-topic',
  templateUrl: './subscribe-list-topic.component.html',
  styleUrls: ['./subscribe-list-topic.component.scss'],
})
export class SubscribeListTopicComponent implements OnInit, OnDestroy {
  public subscribedTopics: TopicWithSubscriptionStatus[] = [];

  // Propriétés publiques pour stocker les abonnements
  public userSubscription!: Subscription;
  public unsubscribeSubscription!: Subscription;

  constructor(
    private authService: AuthService,
    private topicService: TopicService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    // Stocker l'abonnement pour récupérer les topics abonnés de l'utilisateur
    this.userSubscription = this.authService.me().subscribe({
      next: (user) => {
        this.subscribedTopics = user.topics;
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors de la récupération des sujets abonnés', error);
      },
    });
  }

  public unsubscribeFromTopic(topicId: number): void {
    // Stocker l'abonnement pour le désabonnement
    this.unsubscribeSubscription = this.topicService.unsubscribeFromTopic(topicId.toString()).subscribe({
      next: (responseMessage: string) => {
        const topic = this.subscribedTopics.find((t) => t.id === topicId);
        if (topic) {
          const index = this.subscribedTopics.indexOf(topic);
          if (index > -1) {
            this.subscribedTopics.splice(index, 1);
          }
          this.snackBar.open(responseMessage, 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
          });
        }
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors du désabonnement du topic', error);
      },
    });
  }

  // Désabonnement dans ngOnDestroy
  ngOnDestroy(): void {
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
    if (this.unsubscribeSubscription) {
      this.unsubscribeSubscription.unsubscribe();
    }
  }
}
