import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookModel } from '../models/BookModel';
import { UserService } from './user.service';
import { API } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  constructor(private http: HttpClient) { 
   
  }

  getBooks(){
    return this.http.get<BookModel[]>( API + '/api/fetch/books')
  }

  

}
