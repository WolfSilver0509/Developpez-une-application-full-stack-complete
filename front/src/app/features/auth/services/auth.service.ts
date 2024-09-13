import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interfaces/loginRequest.interface';
import { RegisterRequest } from "../interfaces/registerRequest.interface";
import { AuthValid } from '../interfaces/authValid.interface';
import { User } from 'src/app/interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private pathServiceAuth = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  public login(loginRequest: LoginRequest): Observable<AuthValid> {
    return this.httpClient.post<AuthValid>(`${this.pathServiceAuth}/login`, loginRequest);
  }

  public register(registerRequest: RegisterRequest): Observable<AuthValid> {
    return this.httpClient.post<AuthValid>(`${this.pathServiceAuth}/register`, registerRequest);
  }

  public me(): Observable<User> {
    return this.httpClient.get<User>(`api/me`);
  }
}
