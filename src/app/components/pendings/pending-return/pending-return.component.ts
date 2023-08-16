import { Component, OnInit } from '@angular/core';
import { PendingModel, PendingService } from 'src/app/services/pending.service';

@Component({
  selector: 'app-pending-return',
  templateUrl: './pending-return.component.html',
  styleUrls: ['./pending-return.component.scss']
})
export class PendingReturnComponent implements OnInit {

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

  handleApprove(recordId: string){
    this.pendingService.approvePendingReturn(recordId).subscribe((res: any) => {
      
    }, (err: any) => {
      if(err.status === 200){
        this.pendingReturns = this.pendingReturns.filter((item:any) => item.recordDetails.id !== recordId)
        this.pendingService.pendingReturns = this.pendingReturns
        console.log('Pending Borrows: ', this.pendingReturns);
      }else{
        alert('something went wrong')
      }
    })
  }

}
