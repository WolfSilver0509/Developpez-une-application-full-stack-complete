import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { AuthValid } from '../features/auth/interfaces/authValid.interface';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private token: AuthValid | undefined;
  private storageKey = 'loggedUser'; // Key for localStorage

  public isLogged = this.getIsLoggedFromStorage();
  public user: User | undefined = this.getUserFromStorage();
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  public logIn(user: User, authValid: AuthValid): void {
    this.token = authValid;
    this.user = user;
    this.isLogged = true;
    this.saveToStorage();
    this.next();
  }

  public logOut(): void {
    this.token = undefined;
    this.user = undefined;
    this.isLogged = false;
    this.removeFromStorage();
    this.next();
  }

  private saveToStorage(): void {
    localStorage.setItem(this.storageKey, JSON.stringify({ user: this.user, token: this.token }));
  }

  private removeFromStorage(): void {
    localStorage.removeItem(this.storageKey);
  }

  private getIsLoggedFromStorage(): boolean {
    const data = localStorage.getItem(this.storageKey);
    if (data) {
      try {
        const parsedData = JSON.parse(data);
        return parsedData !== null;
      } catch (error) {
        console.error('Error parsing logged user data:', error);
        return false;
      }
    }
    return false;
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
