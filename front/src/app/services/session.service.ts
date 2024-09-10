import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { AuthValid } from '../features/auth/interfaces/authValid.interface';
import { MeService } from './me.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private token: AuthValid | undefined;
  private storageKey = 'loggedUser';
  public isLogged = this.getIsLoggedFromStorage();
  public user: User | undefined = this.getUserFromStorage();
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  constructor(private meService: MeService, private cookieService: CookieService) { }

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: User): void {
    this.user = user;
    this.isLogged = true;

    // Sauvegarder l'utilisateur et le token dans le localStorage
    this.saveToStorage();
    this.next();
  }

  public setToken(token :string): void {
    // localStorage.setItem("token", token);
    this.cookieService.set('token', token, { secure: true, sameSite: 'Strict' });

  }

  public getToken(): string {
    return this.cookieService.get('token')
  }

  public logout(): void {
    this.cookieService.deleteAll('token');
    localStorage.removeItem('loggedUser');
  }

  private saveToStorage(): void {
    localStorage.setItem(this.storageKey, JSON.stringify({ user: this.user, token: this.token }));
  }

  private removeFromStorage(): void {
    localStorage.removeItem(this.storageKey);
  }

  private getIsLoggedFromStorage(): boolean {
    const data = localStorage.getItem(this.storageKey);
    return data !== null;
  }

  public getUserFromStorage(): User | undefined {
    const data = localStorage.getItem(this.storageKey);
    if (data) {
      try {
        const parsedData = JSON.parse(data);
        return parsedData?.user;
      } catch (error) {
        console.error('Error parsing logged user data:', error);
        return undefined;
      }
    }
    return undefined;
  }

  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
