import { Component, OnInit } from '@angular/core';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-all-books',
  templateUrl: './all-books.component.html',
  styleUrls: ['./all-books.component.scss']
})
export class AllBooksComponent implements OnInit {

  allBooks: any[] = []

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe((res: any) => {
      // console.log('books: ', res);
      this.allBooks = res
      return this.allBooks
      
    }, (err: any) => {
      if(err.status !== 200){
        alert('Something went wrong while fetching books')
        console.log(err);
      }
    });
    console.log(this.allBooks);
    
  }

}
