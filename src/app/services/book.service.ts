import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  allBooks: any[] = []

  constructor(private http: HttpClient) { 

    

  }

  getAllBooks(){
    return this.http.get(API + "/api/fetch/books")
  }


}
