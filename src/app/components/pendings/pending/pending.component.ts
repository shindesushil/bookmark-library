import { Component, OnInit } from '@angular/core';
import { PendingModel, PendingService } from 'src/app/services/pending.service';

@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html',
  styleUrls: ['./pending.component.scss']
})
export class PendingComponent implements OnInit {

  pendingBorrows: PendingModel[] = []
  pendingReturns: PendingModel[] = []

  constructor(public pendingService: PendingService) { }

  ngOnInit(): void {
    this.pendingService.getAllPendings().subscribe((res: any) => {
      console.log('Pendings: ', res);
      this.pendingBorrows = res.borrowings
      this.pendingReturns = res.returns
      this.pendingService.pendingBorrows = this.pendingBorrows
      this.pendingService.pendingReturns = this.pendingReturns
    }, (err: any) => {
      if(err.status === 200){
        console.log('OK');
      }
      else if(err.status === 401){
        alert('Inavlid credentials')
      }
      else
      {
        alert('Something went wrong...')
      }
    })
  }

}
