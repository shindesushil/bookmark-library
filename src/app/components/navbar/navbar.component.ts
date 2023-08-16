import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { PendingService } from 'src/app/services/pending.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  pendingCount: Number = 0

  constructor(private authService: AuthService, public pendingService: PendingService) { }

  ngOnInit(): void {
  // this.pendingService.getTotalPendingCount().then((res : any) => {
  //  this.pendingCount = res.borrowings.length + res.returns.length
  //  this.pendingService.pendingBorrows = res.borrowings
  //  this.pendingService.pendingReturns = res.returns
  // })
  }

  handleLogout(){
    this.authService.logout()
  }

}
