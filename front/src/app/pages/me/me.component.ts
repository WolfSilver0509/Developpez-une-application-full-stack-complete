import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionService } from "../../services/session.service";
import { MeService } from "../../services/me.service";
import { User } from "../../interfaces/user.interface";
import {PasswordValidator} from "../../validators/password.validator";

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
      password: ['', [Validators.required, PasswordValidator.strongPassword()]]
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
    console.log("Form Validity: ", this.profileForm.valid);
    console.log("Form Value: ", this.profileForm.value);
    console.log("Form Errors: ", this.profileForm.errors);
    if (this.profileForm.invalid) {
      return;
    }

    const formData = new FormData();

    // Only update fields that have changed
    if (this.profileForm.get('name')!.value !== this.userOld!.name) {
      formData.append("name", this.profileForm.get('name')!.value);
    }

    if (this.profileForm.get('email')!.value !== this.userOld!.email) {
      formData.append("email", this.profileForm.get('email')!.value);
    }

    if (this.profileForm.get('password')!.value) {
      formData.append("password", this.profileForm.get('password')!.value);
    }

    this.meService.updateUser(formData).subscribe({
      next: (updatedUserData: User) => {
        console.log('Données utilisateur mises à jour avec succès', updatedUserData);
        this.user = updatedUserData;

        this.sessionService.logIn(updatedUserData, this.sessionService.getToken()!);
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
