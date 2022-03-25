import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    return this.http
      .post<any>(`http://localhost:8080/login`, {
        userName: username,
        password: password,
      })
      .pipe(
        map((user) => {
          console.log('AuthenticationService - user: ', user);
          return user;
        })
      );
  }

  logout() {
    console.log('Logout. Coming Soon!');
  }
}
