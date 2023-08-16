import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

import { API } from '../constants';

export interface PendingModel{
  id: string,
  userId: string,
  bookId: string,
  borrowDate: string,
  status: string,
  fineAmount: Number
}

@Injectable({
  providedIn: 'root'
})
export class PendingService {

  pendingBorrows: PendingModel[] = []
  pendingReturns: PendingModel[] = []
  pendingCount: Number = 0

  constructor(private http: HttpClient, private authService: AuthService) {
    console.log('IN PENDING CONSTRUCTOR ==== ');
    this.getAllPendings().subscribe((res: any) => {
      console.log('Gotcha : ' , res);
      this.pendingBorrows = res.borrowings
      this.pendingReturns = res.returns
      this.pendingCount = this.pendingBorrows.length + this.pendingReturns.length
    }, (err: any) => {
      if(err.status === 200){
        // console.log('Gotcha');
      }
    })
  }


  getAllPendings(){
    return this.http.get(API + '/api/admin/pending', {
      headers: this.authService.getHeaders()
    })
  }

  getAllPending(){
    let data = this.getAllPendings()
      .toPromise()
      .then((res: any) => {
      return res;
    })
    return data
  }

  approvePenndingBorrow(recordId: string){
    let params = new HttpParams();
    params = params.append('recordId', recordId)
    return this.http.post(API + '/api/admin/borrow/approve', null, {
      params: params,
      headers: this.authService.getHeaders()
    })
  }

  rejectPendingBorrow(recordId: string){
    let params = new HttpParams();
    params = params.append('recordId', recordId)
    return this.http.post(API + '/api/admin/borrow/reject', null, {
      params: params,
      headers: this.authService.getHeaders()
    })
  }

  approvePendingReturn(recordId: string){
    let params = new HttpParams();
    params = params.append('recordId', recordId)
    return this.http.post(API + '/api/admin/return/approve', null, {
      params: params,
      headers: this.authService.getHeaders()
    })
  }

  getTotalCount(){
    return this.pendingBorrows.length + this.pendingReturns.length
  }

  getBorrowsCount(){
    return this.pendingBorrows.length
  }

  getReturnsCount(){
    return this.pendingReturns.length
  }


}
