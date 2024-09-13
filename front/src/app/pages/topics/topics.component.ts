import { Component, OnInit, OnDestroy } from '@angular/core';
import { SessionService } from '../../services/session.service';
import { User } from '../../interfaces/user.interface';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  styleUrls: ['./topics.component.scss']
})
export class TopicsComponent implements OnInit, OnDestroy {

  public user: User | undefined;

  // Propriété publique pour stocker l'abonnement
  public isLoggedSubscription!: Subscription;

  constructor(private sessionService: SessionService) { }

  ngOnInit(): void {
    // Stocker l'abonnement dans la propriété publique
    this.isLoggedSubscription = this.sessionService.$isLogged()
      .subscribe(isLogged => {

      });
  }

  // Désabonnement dans ngOnDestroy
  ngOnDestroy(): void {
    if (this.isLoggedSubscription) {
      this.isLoggedSubscription.unsubscribe();
    }
  }

}
