import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-subscribe-button',
  templateUrl: './subscribe-button-component.component.html',
  styleUrls: ['./subscribe-button-component.component.scss']
})
export class SubscribeButtonComponent {

  // Propriété @Input() correctement déclarée
  @Input() isSubscribed: boolean = false;

  // Propriété @Input() topicId pour passer l'ID du topic
  @Input() topicId!: number;

  // Événement @Output() pour émettre l'ID du topic lorsqu'un abonnement est demandé
  @Output() onSubscribe: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }

  // Méthode subscribe() qui émet l'événement
  subscribe() {
    this.onSubscribe.emit(this.topicId);
  }
}
