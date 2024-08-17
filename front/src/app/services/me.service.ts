
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MeService {
  constructor() {}

  public logout(): void {
      localStorage.removeItem('token');
      localStorage.removeItem('loggedUser');
  }
}
