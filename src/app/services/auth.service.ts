import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { API } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  token: string

  constructor(private http: HttpClient, private router: Router) { }

  login(body: any){
    this.http.post(API + '/api/auth/admin/login',body).subscribe((res: any) => {
      // console.log(res);
      if(res.role !== '[ADMIN]'){
        alert('Invalid Credentials')
      }else{
        this.token = res.token
        localStorage.setItem('admin-token', this.token)
        alert('Login Success')
        this.router.navigate(['/'])
      }
    }, (err: any) => {
      if(err.status === 401){
        console.log(err);
        alert('Invalid Credentials')
      }else if(err.status === 200){
        console.log('OK');
      }
    })
  }

  isLoggedIn(){
    if(localStorage.getItem('admin-token')){
      return true
    }
    return false;
  }

  logout(){
    localStorage.removeItem('admin-token')
    this.token = ''
    this.router.navigate(['/login'])
  }

  getToken(){
    return localStorage.getItem('admin-token');
  }

  getHeaders(){
    let headers = new HttpHeaders()
    headers = headers.append('Authorization', 'Bearer ' + this.getToken())
    return headers
  }

}
