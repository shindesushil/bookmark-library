import { Component, OnInit } from '@angular/core';
import { PendingModel, PendingService } from 'src/app/services/pending.service';

@Component({
  selector: 'app-pending-borrow',
  templateUrl: './pending-borrow.component.html',
  styleUrls: ['./pending-borrow.component.scss']
})
export class PendingBorrowComponent implements OnInit {

  pendingBorrows: PendingModel[] = []
  pendingReturns: PendingModel[] = []

  constructor(private pendingService: PendingService) { }

  ngOnInit(): void {
    this.pendingService.getAllPendings().subscribe((res: any) => {
      this.pendingBorrows = res.borrowings
      this.pendingReturns = res.returns
    }, (err: any) => {
      if(err.status === 200){
        // console.log('Gotcha');
      }
    })
  }

  handleApprove(recordId: any){
    console.log('record: ', recordId);
    this.pendingService.approvePenndingBorrow(recordId).subscribe((res: any) => {
      
    }, (err: any) => {
      if(err.status === 200){
        this.pendingBorrows = this.pendingBorrows.filter((item:any) => item.recordDetails.id !== recordId)
        this.pendingService.pendingBorrows = this.pendingBorrows
        console.log('Pending Borrows: ', this.pendingBorrows);
      }else{
        alert('something went wrong')
      }
    })
  }

  handleReject(recordId: any){
    console.log('Rejected : ', recordId);
    this.pendingService.rejectPendingBorrow(recordId).subscribe((res: any) => {

    }, (err: any) => {
      if(err.status === 200){
        this.pendingBorrows = this.pendingBorrows.filter((item:any) => item.recordDetails.id !== recordId)
        this.pendingService.pendingBorrows = this.pendingBorrows
        console.log('Pending Borrows: ', this.pendingBorrows);
      }else{
        alert('something went wrong')
      }
    })
  }

}
