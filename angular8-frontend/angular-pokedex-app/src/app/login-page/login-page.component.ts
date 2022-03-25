import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { FormGroup, FormControl } from '@angular/forms';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent implements OnInit {
  user: any;

  loginForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private http: HttpClient
  ) {}

  ngOnInit() {}

  onSubmit() {
    this.user = this.authService
      .login(this.loginForm.value.username, this.loginForm.value.password)
      .subscribe((returnedUser: any) => {
        console.log('onSubmit this.user: ', returnedUser);
        this.router.navigate(['/home']);
      });
  }
}
