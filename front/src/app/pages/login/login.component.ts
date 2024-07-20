import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
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
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(3)]]
  });

  constructor(
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
    private sessionService: SessionService
  ) { }

  public login(): void {
    console.log("Bouton de connexion cliqué !");
    if (this.form.valid) {
      const loginRequest = this.form.value as LoginRequest;
      this.authService.login(loginRequest).subscribe(
        (response: AuthValid) => {
          console.log("Réponse du serveur :", response);
          localStorage.setItem('token', response.token);
          this.authService.me().subscribe((user: User) => {
            console.log("Utilisateur connecté :", user);
            this.sessionService.logIn(user, response);
            this.router.navigate(['/topics']);
          });
        },
        error => {
          console.error("Erreur lors de la connexion :", error);
          this.onError = true;
        }
      );
    } else {
      console.error("Formulaire invalide");
    }
  }
}
