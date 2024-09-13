import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from "../../services/session.service";
import { MeService } from "../../services/me.service";
import { User } from "../../interfaces/user.interface";
import { PasswordValidator } from "../../validators/password.validator";
import { HttpErrorResponse } from "@angular/common/http";
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit, OnDestroy {
  user: User;
  profileForm: FormGroup;
  userOld: Partial<User> | null = null;
  public onError = false;
  public updateSuccess = false;

  // Variables pour stocker chaque abonnement
  public userSubscription!: Subscription;
  public updateSubscription!: Subscription;

  constructor(
    private fb: FormBuilder,
    private sessionService: SessionService,
    private meService: MeService,
    private router: Router
  ) {
    this.user = {
      id: 0,
      name: '',
      email: '',
      created_at: new Date(),
      updated_at: new Date(),
      topics: []
    };

    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [PasswordValidator.strongPassword()]]
    });
  }

  ngOnInit(): void {
    // Abonnement pour obtenir l'utilisateur
    this.userSubscription = this.meService.getUser().subscribe(user => {
      this.user = user;
      this.userOld = { ...user }; // Stocke les valeurs initiales

      // Initialise le formulaire avec les données utilisateur
      this.profileForm.patchValue({
        name: user.name,
        email: user.email,
        password: ''
      });
    });
  }

  onSaveProfile(): void {
    const formData = new FormData();

    // Ne met à jour que les champs modifiés
    if (this.profileForm.get('name')!.value !== this.userOld!.name) {
      formData.append("name", this.profileForm.get('name')!.value);
    }

    if (this.profileForm.get('email')!.value !== this.userOld!.email) {
      formData.append("email", this.profileForm.get('email')!.value);
    }

    // Si le champ password est renseigné et passe la validation
    if (this.profileForm.get('password')!.value && this.profileForm.get('password')!.valid) {
      formData.append("password", this.profileForm.get('password')!.value);
    }

    // Si le formulaire est invalide à cause d'une erreur de mot de passe
    if (this.profileForm.get('password')!.value && !this.profileForm.get('password')!.valid) {
      console.error("Le mot de passe ne respecte pas les critères de validation.");
      this.onError = true;  // Affiche un message d'erreur dans le template
      return;
    }

    // Abonnement pour mettre à jour l'utilisateur
    this.updateSubscription = this.meService.updateUser(formData).subscribe({
      next: (updatedUserData: User) => {
        this.user = updatedUserData;

        // Met à jour la session avec les nouvelles données utilisateur
        this.sessionService.logIn(updatedUserData);
        this.sessionService.setToken(updatedUserData.jwtToken!);
        this.updateSuccess = true;  // Affiche un message de succès dans le template
        setTimeout(() => {
          this.updateSuccess = false;
        }, 5000);
      },
      error: (err: HttpErrorResponse) => {
        console.error('Erreur lors de la mise à jour de l\'utilisateur:', err);
      }
    });
  }

  onLogout(): void {
    this.sessionService.logout();
    this.router.navigate(['']);
  }

  // Désabonnement dans ngOnDestroy
  ngOnDestroy(): void {
    // Désabonne-toi de chaque subscription si elle existe
    if (this.userSubscription) {
      this.userSubscription.unsubscribe();
    }
    if (this.updateSubscription) {
      this.updateSubscription.unsubscribe();
    }
  }
}
