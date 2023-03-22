import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  isLoggedIn = false

  constructor(
    private authService: AuthService,
    private router: Router
    ){}

  ngOnInit(){
    document.body.classList.remove('aside-enabled')
    if( localStorage.getItem('jwt_token') == null ){
      this.router.navigate(['login'])
      return;
    }
    
    this.authService.isUserLoggedIn().subscribe(response=>{
      this.isLoggedIn = true
      document.body.classList.add('aside-enabled')
    })
    
  }

  logout(){
    document.body.classList.remove('aside-enabled')
    localStorage.removeItem('jwt_token')
    this.isLoggedIn = false
    this.router.navigate(['login'])
  }
}
