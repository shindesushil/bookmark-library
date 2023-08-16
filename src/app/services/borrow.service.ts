import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { API } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class BorrowService {

  constructor(private http: HttpClient, private userService: UserService) { }

  borrowBook(bookId: any){
    let userId = this.userService.getUserDetails().id;
    console.log(bookId + " " + userId);

    let params: HttpParams = new HttpParams();
    params = params.set("userId", userId);
    params = params.set("bookId", bookId);

    let headers:HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.userService.getToken());

    return this.http.get(API + "/api/user/borrow", {
      params: params,
      headers: headers
    })

  }

}
