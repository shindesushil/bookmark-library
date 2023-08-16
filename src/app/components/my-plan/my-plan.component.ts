import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-plan',
  templateUrl: './my-plan.component.html',
  styleUrls: ['./my-plan.component.scss']
})
export class MyPlanComponent implements OnInit {

  subscription: any 

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.subscription = this.userService.getSubscriptionDetails()
    console.log('My Sub: ', this.subscription);
  }

  getFormatedDate(){
    if(this.subscription !== undefined){
      let date = this.subscription.expiresOn.split(" ")
      date = date[1]+" " + date[2]+ "," + date[5]
      return date
    }
    return ""
  }

}
