import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SchoolClass } from './model/school-class';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiEndPoint:string = environment.apiEndPoint
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };
  
  constructor(private httpClient: HttpClient) { }

  login(username:string, password:string):Observable<LoginResponse>{
    

    this.httpOptions.headers = this.httpOptions.headers.set('Authorization', 'Basic ' + btoa(username+':'+password));
    return this.httpClient.post<LoginResponse>(this.apiEndPoint +"/auth",{},this.httpOptions)
  }

  isUserLoggedIn():Observable<SchoolClass[]>{
    this.httpOptions.headers = this.httpOptions.headers.set('Authorization', 'Bearer ' + localStorage.getItem('jwt_token'));
    return this.httpClient.get<SchoolClass[]>( this.apiEndPoint + "/school-class",this.httpOptions )
      
      
  }
}

export class LoginResponse{
  success!: boolean
  jwt!: string
}