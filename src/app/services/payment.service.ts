import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { SubscriptionModel } from '../models/SubscriptionModel';
import { API } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient, private userService: UserService) { }

  

  createTransaction(userId: string, subId: string){

    let headers:HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.userService.getToken()); 
    // headers = headers.set("Access-control-allow-origin", "*")

    let params: HttpParams = new HttpParams();
    params = params.set("subscriptionId", subId);
    params = params.set("userId", userId);

    return this.http.post(API + "/api/payment", null, {
      headers: headers,
      params: params
    });
  }

  completeTransaction(body: any){
    let headers:HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.userService.getToken()); 

    return this.http.post<SubscriptionModel>(API + "/api/payment/complete", body, {
      headers: headers
    });
  }

}
