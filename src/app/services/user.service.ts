import { Injectable } from '@angular/core';
import { API } from '../constants';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { UserModel } from '../models/UserModel';
import { Router } from '@angular/router';
import { SubscriptionModel } from '../models/SubscriptionModel';
import { BooksService } from './book.service';
import { BookModel } from '../models/BookModel';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  api: String = API

  private userDetails: UserModel | any;
  private token: String;
  private subscriptionDetails: SubscriptionModel | any
  private myBooks: any[]

  constructor(private http: HttpClient, 
    private router: Router) { 
    
    let json = JSON.parse(localStorage.getItem("user") || '{}')
    console.log('JSON: ' + json.user);

    if(json.user)
    {
      this.userDetails = json.user
      this.token = json.token
      this.subscriptionDetails = this.getSubscriptionDetails()
    }

  }

  register(body:any){
    console.log(body);
    
    return this.http.post(API + '/api/auth/register', body)
  }

  login(body: any){
    return this.http.post<UserModel>(API + '/api/auth/login', body, {
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    })
  }

  logout(){
    localStorage.removeItem("user")
    this.userDetails = null
    this.subscriptionDetails = null
    this.myBooks = []
    this.token = ''
    this.router.navigate(['/'])
  }

  isLoggedIn(){
    return localStorage.getItem("user") !== null;
  }

  setUserDetails(body: any){
    this.userDetails = body.user
    this.token = body.token
  }

  updateUserDetails(user: any){
    this.userDetails = user
    let newDetails = {
      user: this.userDetails,
      subscription: this.subscriptionDetails,
      token: this.token,
      tokenType: 'Bearer '
    }

    localStorage.setItem("user", JSON.stringify(newDetails))

  }

  getUserDetails(){
    // console.log("User Details: ", this.userDetails);
    return this.userDetails
  }

  getToken(){
    return this.token
  }

  setSubscriptionDetails(subDetails: SubscriptionModel){
    this.subscriptionDetails = subDetails
    let data = {
      user: this.userDetails,
      subscription: this.subscriptionDetails,
      token: this.token
    }

    localStorage.setItem("user", JSON.stringify(data));

  }

  getSubscriptionDetails(){
    let json = JSON.parse(localStorage.getItem("user") || '{}')
    if(json.subscription){
      this.subscriptionDetails = json.subscription
      return this.subscriptionDetails
    }
    return null
  }

  hasSubscription(){
    // console.log('hasSub: ', this.subscriptionDetails !== null);
    
    if(this.subscriptionDetails !== null)
      return true
    return false
  }

  getDP(){
    // console.log("----- : " , this.userDetails);
    
    if(this.userDetails !== undefined)
      return 'data:image/jpeg;base64,' + this.userDetails.image.data
    return false
  }

  updateDP(img: any){

    let data: FormData = new FormData();
    data.append("userId", this.userDetails.id)
    data.append("photo", img)

    let headers:HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.getToken()); 

    return this.http.post(API + '/api/user/update-dp', data, {
      headers: headers
    })
  }

  setMyBooks(books: any){
    this.myBooks = books
  }
  getMyBooks(){
    return this.myBooks
  }
  addToMyBooks(book:any){
    if(this.myBooks === undefined){
      this.myBooks = [book]
    }else{
      this.myBooks.push(book)
    }
  }
  deleteFromMyBook(bookId:string){
    this.myBooks = this.myBooks.filter(val => val.details.id !== bookId)
  }

  getBooksByUser(userId: string){
    let params: HttpParams = new HttpParams();
    params = params.set("userId", userId);

    let headers:HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.getToken());

    return this.http.get<BookModel[]>(API + "/api/user/books", {
      params: params,
      headers: headers
    })
  }

  returnBook(bookId: string){
    let params: HttpParams = new HttpParams();
    params = params.set("userId", this.userDetails.id);
    params = params.set("bookId", bookId);

    let headers:HttpHeaders = new HttpHeaders();
    headers = headers.set('Authorization', 'Bearer ' + this.getToken());

    return this.http.post(API + "/api/user/book/return", null, {
      params: params,
      headers: headers
    })

  }
  

}
