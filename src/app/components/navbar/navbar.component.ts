import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean = false;

  constructor(public userService: UserService) { 
    this.isLoggedIn = userService.isLoggedIn();
    console.log(this.isLoggedIn);
    
  }

  ngOnInit(): void {
  }

  handleLogout(){
    this.userService.logout()
    this.isLoggedIn = false
  }

}
