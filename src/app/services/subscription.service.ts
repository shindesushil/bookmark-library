import { Injectable, OnInit } from '@angular/core';
import { SubscriptionModel } from '../models/SubscriptionModel';
import { HttpClient } from '@angular/common/http';
import { API } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService implements OnInit {

  constructor(private http: HttpClient) {
    
  }

  ngOnInit(): void {
  }

  getSubscriptions(){
    return this.http.get<SubscriptionModel[]>(API + "/api/fetch/subscriptions")
  }

}
