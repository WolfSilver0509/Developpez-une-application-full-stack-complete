import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import {SessionService} from "../../services/session.service";
import {MeService} from "../../services/me.service";
import {User} from "../../interfaces/user.interface";


@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {
  user: User = {
    id: 0,
    name: '',
    email: '',
    created_at: new Date(),
    updated_at: new Date(),
    topics: []
  };

  password: string = '';

  constructor(private sessionService: SessionService, private meService: MeService, private router: Router) { }

  ngOnInit(): void {
    // Chargez les données utilisateur depuis le backend
    this.meService.getUser().subscribe(user => {
      this.user = user; // Remplit les champs de formulaire avec les valeurs de l'utilisateur
    });
  }

  // onSaveProfile(): void {
  //   // Créez un objet avec les valeurs actuelles du formulaire
  //   const updatedUser: Partial<User> = {
  //     name: this.user.name,
  //     email: this.user.email
  //   };
  //
  //   // Si un mot de passe est fourni, ajoutez-le
  //   if (this.password) {
  //     (updatedUser as any).password = this.password;
  //   }
  //
  //   // Envoyez la requête de mise à jour uniquement si des modifications ont été apportées
  //   this.sessionService.updateUser(updatedUser).subscribe({
  //     next: (updatedUserData: User) => {
  //       console.log('Données utilisateur mises à jour avec succès', updatedUserData);
  //       // Mettez à jour l'état local de l'utilisateur avec les nouvelles données reçues
  //       this.user = updatedUserData;
  //     },
  //     error: (err: any) => {
  //       console.error('Erreur lors de la mise à jour de l\'utilisateur:', err);
  //     }
  //   });
  // }

  onSaveProfile(): void {
    const updatedUser: Partial<User> = {
      name: this.user.name,
      email: this.user.email
    };

    if (this.password) {
      (updatedUser as any).password = this.password;
    }

    this.sessionService.updateUser(updatedUser).subscribe({
      next: (updatedUserData: User) => {
        console.log('Données utilisateur mises à jour avec succès', updatedUserData);
        this.user = updatedUserData;

        if (this.sessionService.getToken()) {
          this.sessionService.logIn(updatedUserData, this.sessionService.getToken()!);
        } else {
          console.error("Le token est indéfini, impossible de se reconnecter.");
        }
      },
      error: (err: any) => {
        console.error('Erreur lors de la mise à jour de l\'utilisateur:', err);
      }
    });
  }





  onLogout(): void {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }
}
