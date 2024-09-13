import { ChangeDetectorRef, Component, OnInit, OnDestroy } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../interfaces/user.interface';
import { SessionService } from '../../services/session.service';
import { TopicService } from '../../services/topic.service';
import { AuthService } from '../../features/auth/services/auth.service';
import { TopicWithSubscriptionStatus } from '../../interfaces/topicWithSubscriptionStatus.interface';
import { HttpErrorResponse } from "@angular/common/http";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss'],
})
export class ListTopicComponent implements OnInit, OnDestroy {
  public topics: TopicWithSubscriptionStatus[] = [];

  // Propriétés publiques pour stocker les abonnements
  public topicSubscription!: Subscription;
  public userSubscription!: Subscription;
  public topicUpdateSubscription!: Subscription;

  constructor(
    private sessionService: SessionService,
    private topicService: TopicService,
    private changeDetector: ChangeDetectorRef,
    private authService: AuthService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadTopics();
    this.subscribeToTopicUpdates();
  }

  private loadTopics(): void {
    // Stocker l'abonnement dans une propriété publique
    this.topicSubscription = this.topicService.getAllTopics().subscribe({
      next: (response: { topics: TopicWithSubscriptionStatus[] }) => {
        this.topics = response.topics.map((topic: TopicWithSubscriptionStatus) => ({
          ...topic,
          isSubscribed: false,
        }));
        this.checkUserSubscriptions();
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors du chargement des topics', error);
      },
    });
  }

  private checkUserSubscriptions(): void {
    // Stocker l'abonnement dans une propriété publique
    this.userSubscription = this.authService.me().subscribe({
      next: (user: User) => {
        this.topics.forEach((topic: TopicWithSubscriptionStatus) => {
          topic.isSubscribed = user.topics.some((subscribedTopic) => subscribedTopic.id === topic.id);
        });
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors de la récupération des abonnements utilisateur', error);
      },
    });
  }

  private subscribeToTopicUpdates(): void {
    // Stocker l'abonnement dans une propriété publique
    this.topicUpdateSubscription = this.topicService.getSubscriptionState().subscribe((state: { topicId: number; isSubscribed: boolean } | null) => {
      if (state !== null) {
        const topic = this.topics.find((t) => t.id === state.topicId);
        if (topic) {
          topic.isSubscribed = state.isSubscribed;
          this.changeDetector.detectChanges();
        }
      }
    });
  }

  public subscribeToTopic(topicId: number): void {
    this.topicService.subscribeToTopic(topicId.toString()).subscribe({
      next: (responseMessage: string) => {
        const topic = this.topics.find((t) => t.id === topicId);
        if (topic) {
          topic.isSubscribed = true;
          this.changeDetector.detectChanges();
          this.snackBar.open(responseMessage, 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
          });
        }
      },
      error: (error: HttpErrorResponse) => {
        console.error('Erreur lors de l\'abonnement au topic', error);
      },
    });
  }

  // Désabonnement des subscriptions lorsque le composant est détruit
  ngOnDestroy(): void {
    if (this.topicSubscription) {
      this.topicSubscription.unsubscribe();
    }
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
    if (this.topicUpdateSubscription) {
      this.topicUpdateSubscription.unsubscribe();
    }
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}
