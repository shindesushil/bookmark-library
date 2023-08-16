import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  handleLogin(){
    if(this.username.length === 0 || this.password.length === 0){
      alert('Email and password required...')
      return
    }
    let body = {
      username: this.username,
      password: this.password
    }
    this.authService.login(body)
  }

}
