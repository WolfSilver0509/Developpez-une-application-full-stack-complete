import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../features/auth/services/auth.service';
import { TopicWithSubscriptionStatus } from '../../interfaces/topicWithSubscriptionStatus.interface';

@Component({
  selector: 'app-subscribe-list-topic',
  templateUrl: './subscribe-list-topic.component.html',
  styleUrls: ['./subscribe-list-topic.component.scss'],
})
export class SubscribeListTopicComponent implements OnInit {
  public subscribedTopics: TopicWithSubscriptionStatus[] = [];

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.me().subscribe({
      next: (user) => {
        this.subscribedTopics = user.topics;
      },
      error: (error: any) => {
        console.error('Erreur lors de la récupération des sujets abonnés', error);
      },
    });
  }
}
