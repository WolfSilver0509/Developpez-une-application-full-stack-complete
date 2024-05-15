import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import {AuthValid} from "../../features/auth/interfaces/authValid.interface";
import {AuthService} from "../../features/auth/services/auth.service";
import {LoginRequest} from "../../features/auth/interfaces/loginRequest.interface";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent  {
  public hide = true;
  public onError = false;

  public form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.min(3)]]
  });

  constructor(private authService: AuthService,
              private fb: FormBuilder,
              private router: Router,
              private sessionService: SessionService) { }

  // public login(): void {
  //   console.log("Bouton de connexion cliqué !");
  //   const loginRequest = this.form.value as LoginRequest;
  //   this.authService.login(loginRequest).subscribe(
  //     (response: AuthValid) => {
  //       localStorage.setItem('token', response.token);
  //       console.log("token =" + response.token)
  //       this.authService.me().subscribe((user: User) => {
  //         this.sessionService.logIn(user);
  //         this.router.navigate(['/'])
  //       });
  //       this.router.navigate(['/'])
  //     },
  //     error => this.onError = true
  //   );
  // }
  public login(): void {
    console.log("Bouton de connexion cliqué !");
    const loginRequest = this.form.value as LoginRequest;
    this.authService.login(loginRequest).subscribe(
      (response: AuthValid) => {
        console.log("Réponse du serveur :", response);
        localStorage.setItem('token', response.token);
        // this.authService.me().subscribe((user: User) => {
        //   console.log("Utilisateur connecté :", user);
        //   this.sessionService.logIn(user);
        //   this.router.navigate(['/']);
        // });
        this.router.navigate(['/']);
      },
      error => {
        console.error("Erreur lors de la connexion :", error);
        this.onError = true;
      }
    );
  }

}
