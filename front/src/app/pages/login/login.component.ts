import { Component } from '@angular/core';
import { FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../features/auth/services/auth.service';
import { SessionService } from '../../services/session.service';
import { LoginRequest } from '../../features/auth/interfaces/loginRequest.interface';
import { AuthValid } from '../../features/auth/interfaces/authValid.interface';
import { User } from '../../interfaces/user.interface';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public hide = true;
  public onError = false;  // Variable pour gérer l'affichage du message d'erreur

  public form = this.fb.group({
    nameOrEmail: ['', [Validators.required, this.nameOrEmailValidator]],
    password: ['', [Validators.required, Validators.minLength(3)]]
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) { }

  public login(): void {
    if (this.form.valid) {
      const loginRequest = this.form.value as LoginRequest;
      this.authService.login(loginRequest).subscribe(
        (response: AuthValid) => {
          // Réinitialiser onError si la connexion réussit
          this.onError = false;
          this.sessionService.setToken(response.token)
          this.authService.me().subscribe((user: User) => {
            this.sessionService.logIn(user);
            this.router.navigate(['/posts']);
          });
        },
        error => {
          console.error("Erreur lors de la connexion :", error);
          this.onError = true;  // Affiche le message d'erreur en cas de connexion échouée
        }
      );
    } else {
      console.error("Formulaire invalide");
    }
  }

  private nameOrEmailValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    const isValidEmail = Validators.email(control) === null;
    const isValidName = Validators.pattern(/^[a-zA-Z ]*$/)(control) === null;
    return isValidEmail || isValidName ? null : { nameOrEmail: true };
  }
}
