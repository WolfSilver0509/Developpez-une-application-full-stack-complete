import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TopicService {
  private readonly basePath = 'http://localhost:5656/api/topics';
  private readonly topicSubscriptionState = new BehaviorSubject<{ topicId: number; isSubscribed: boolean } | null>(null);

  constructor(private httpClient: HttpClient) {}

  public getAllTopics(): Observable<any> {
    return this.httpClient.get<any>(this.basePath);
  }

  public subscribeToTopic(topicId: string): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post(`${this.basePath}/${topicId}/like`, {}, { headers, responseType: 'text' }).pipe(
      tap(() => {
        this.topicSubscriptionState.next({ topicId: Number(topicId), isSubscribed: true });
      })
    );
  }

  // Méthode publique pour accéder à topicSubscriptionState
  public getSubscriptionState(): Observable<{ topicId: number; isSubscribed: boolean } | null> {
    return this.topicSubscriptionState.asObservable();
  }

  /**
   * Méthode pour se désabonner d'un sujet
   * @param topicId
   */
// topic.service.ts
  public unsubscribeFromTopic(topicId: string): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.httpClient.post(`${this.basePath}/${topicId}/unlike`, {}, { headers, responseType: 'text' }).pipe(
      tap(() => {
        this.topicSubscriptionState.next({ topicId: Number(topicId), isSubscribed: false });
      })
    );
  }
}
