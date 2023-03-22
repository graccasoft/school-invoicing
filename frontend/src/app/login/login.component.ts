import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { AuthService, LoginResponse } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username =""
  password =""
  signingIn = false
  loginError = false

  constructor(
    private authService: AuthService,
    private router: Router
    ){}

  doSignIn(){
    this.signingIn = true
    this.loginError = false
    this.authService.login(this.username, this.password)
    .pipe(
      catchError(error => {
          this.signingIn = false
          this.loginError = true
          return of([]);
      })
    )  
    .subscribe(data=>{
      let loginResponse = data as LoginResponse
      if(loginResponse.success){
        localStorage.setItem('jwt_token', loginResponse.jwt)
        document.body.classList.add('aside-enabled')
        this.router.navigate(['classes'] ).then(()=>{
          window.location.reload();
        })
      }
    })
  }
}
