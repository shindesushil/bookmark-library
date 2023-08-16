import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/models/UserModel';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {

  username: String;
  password: String;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  handleLogin(){
    let body = {
      username: this.username,
      password: this.password
    }

    this.userService.login(body).subscribe((res: any) => {
      localStorage.setItem("user",JSON.stringify(res))
      this.userService.setUserDetails(res)
      if(this.userService.isLoggedIn()){
        this.userService.getBooksByUser(this.userService.getUserDetails().id).subscribe((res: any) => {
          this.userService.setMyBooks(res)
        })
      }
      this.router.navigate(['/'])
    }, (error: any) => {
      if(error)
        alert('Something went wrong!')
      console.log("Error: ", error);
    })

    // this.username = this.password = ''
  }

}
