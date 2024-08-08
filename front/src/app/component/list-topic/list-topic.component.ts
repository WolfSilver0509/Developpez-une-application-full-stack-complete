import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../interfaces/user.interface';
import { SessionService } from '../../services/session.service';
import { TopicService } from '../../services/topic.service';
import { AuthService } from '../../features/auth/services/auth.service';
import { TopicWithSubscriptionStatus } from '../../interfaces/topicWithSubscriptionStatus.interface';

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss'],
})
export class ListTopicComponent implements OnInit {
  public topics: TopicWithSubscriptionStatus[] = [];

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
    this.topicService.getAllTopics().subscribe({
      next: (response: { topics: TopicWithSubscriptionStatus[] }) => {
        this.topics = response.topics.map((topic: TopicWithSubscriptionStatus) => ({
          ...topic,
          isSubscribed: false,
        }));
        this.checkUserSubscriptions();
      },
      error: (error: any) => {
        console.error('Erreur lors du chargement des topics', error);
      },
    });
  }

  private checkUserSubscriptions(): void {
    this.authService.me().subscribe({
      next: (user: User) => {
        this.topics.forEach((topic: TopicWithSubscriptionStatus) => {
          topic.isSubscribed = user.topics.some((subscribedTopic) => subscribedTopic.id === topic.id);
        });
      },
      error: (error: any) => {
        console.error('Erreur lors de la récupération des abonnements utilisateur', error);
      },
    });
  }

  private subscribeToTopicUpdates(): void {
    this.topicService.getSubscriptionState().subscribe((state: { topicId: number; isSubscribed: boolean } | null) => {
      if (state !== null) {
        const topic = this.topics.find((t) => t.id === state.topicId);
        if (topic) {
          topic.isSubscribed = state.isSubscribed;
          this.changeDetector.detectChanges();
        }
      }
    });
  }

  public handleSubscription(topicId: number): void {
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
      error: (error: any) => {
        console.error('Erreur lors de l\'abonnement au topic', error);
      },
    });
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}
