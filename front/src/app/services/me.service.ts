import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class MeService {

  private apiUrl = 'http://localhost:5656/api/me';

  constructor(private http: HttpClient) { }

  getUser(): Observable<User> {
    return this.http.get<User>(this.apiUrl);
  }

  updateUser(user: Partial<User>): Observable<User> {
    return this.http.put<User>(this.apiUrl, user);
  }
}
