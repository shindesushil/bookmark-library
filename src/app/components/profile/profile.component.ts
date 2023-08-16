import { Component, OnInit } from '@angular/core';
import { Route } from '@angular/router';
import { UserModel } from 'src/app/models/UserModel';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  userDetails: UserModel;

  constructor(public userService: UserService) { }

  file: BinaryType

  ngOnInit(): void {
    this.userDetails = this.userService.getUserDetails();
  }

  handleDpChange(file: any){
    this.userService.updateDP(file.srcElement.files[0])
    .subscribe((res: any) => {
      // console.log("res : ", res);
      this.userService.updateUserDetails(res)
      this.userDetails = res
    }, (err: any) => {
      if(err.status !== 200)
      {
        alert('Something went wrong!')
        console.log('Err: ', err);
      }else{
        alert('Profile picture updated...')
      }
    })
  }

}
