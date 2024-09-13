
import { Injectable } from '@angular/core';
import {User} from "../interfaces/user.interface";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class MeService {

  private apiUrl = '/api/me';

  constructor(private http: HttpClient) { }

  getUser(): Observable<User> {
    return this.http.get<User>(this.apiUrl);
  }

  updateUser(user: FormData): Observable<User> {
    return this.http.put<User>(this.apiUrl, user);
  }


}
