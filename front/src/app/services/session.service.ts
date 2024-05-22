import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from '../features/auth/services/auth.service';
import { User } from '../interfaces/user.interface';
import { AuthValid } from '../features/auth/interfaces/authValid.interface'

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  // Propriété pour stocker le token JWT
  private token: AuthValid | undefined; // Déclarer token comme AuthValid


  // Propriétés pour gérer l'état de connexion
  public isLogged = false;
  public user: User | undefined;

  // Observable pour notifier les changements d'état de connexion
  private isLoggedSubject = new BehaviorSubject<boolean>(this.isLogged);

  // Accesseur pour l'observable `isLogged`
  public $isLogged(): Observable<boolean> {
    return this.isLoggedSubject.asObservable();
  }

  // Fonction de connexion
  public logIn(user: User, authValid: AuthValid = { token: '' }): void {
    this.token = authValid;  // Stocker le token JWT
    this.user = user; // Définir les informations de l'utilisateur
    this.isLogged = true; // Marquer l'utilisateur comme connecté
    this.next(); // Notifier les abonnés du changement d'état
  }

  // Fonction de déconnexion
  public logOut(): void {
    this.token = undefined; // Supprimer le token JWT
    this.user = undefined; // Supprimer les informations de l'utilisateur
    this.isLogged = false; // Marquer l'utilisateur comme déconnecté
    this.next(); // Notifier les abonnés du changement d'état
  }

  // Fonction pour notifier les abonnés du changement d'état de connexion
  private next(): void {
    this.isLoggedSubject.next(this.isLogged);
  }
}
