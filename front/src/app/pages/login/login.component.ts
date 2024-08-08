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
  public onError = false;

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
    console.log("Valeurs du formulaire :", this.form.value); // Log des valeurs du formulaire
    if (this.form.valid) {
      const loginRequest = this.form.value as LoginRequest;
      console.log("Requête de connexion :", loginRequest); // Log de la requête de connexion
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

  private nameOrEmailValidator(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    const isValidEmail = Validators.email(control) === null;
    const isValidName = Validators.pattern(/^[a-zA-Z ]*$/)(control) === null;
    console.log("Validation du champ nameOrEmail :", isValidEmail || isValidName ? "Valide" : "Invalide"); // Log du résultat de la validation
    return isValidEmail || isValidName ? null : { nameOrEmail: true };
  }
}
