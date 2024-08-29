import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from "../../services/session.service";
import { MeService } from "../../services/me.service";
import { User } from "../../interfaces/user.interface";
import {PasswordValidator} from "../../validators/password.validator";
import { AuthValid } from '../../features/auth/interfaces/authValid.interface';


@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {
  user: User;
  profileForm: FormGroup;
  userOld: Partial<User> | null = null;
  public onError = false;
  public updateSuccess = false;

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
    this.meService.getUser().subscribe(user => {
      this.user = user;
      this.userOld = { ...user }; // Store initial values

      // Initialize the form with the user data
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
      this.onError = true;  // Affichez un message d'erreur dans le template
      return;
    }

    this.meService.updateUser(formData).subscribe({
      next: (updatedUserData: User) => {
        console.log('Données utilisateur mises à jour avec succès', updatedUserData);
        this.user = updatedUserData;

        // Mettre à jour la session avec les nouvelles données utilisateur
        this.sessionService.logIn(updatedUserData, this.sessionService.getToken()!);
        this.updateSuccess = true;  // Affichez un message de succès dans le template
        setTimeout(() => {
          this.updateSuccess = false;
        }, 5000);
      },
      error: (err: any) => {
        console.error('Erreur lors de la mise à jour de l\'utilisateur:', err);
      }
    });
  }



  onLogout(): void {
    this.sessionService.logOut();
    this.router.navigate(['/home']);
  }
}
