import { Binary } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { BookModel } from 'src/app/models/BookModel';
import { BooksService } from 'src/app/services/book.service';
import { BorrowService } from 'src/app/services/borrow.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {

  allBooks: BookModel[]

  myBooks: any[]

  constructor(private bookService: BooksService, 
    private sanitizer: DomSanitizer,
    public userService: UserService,
    private borrowService: BorrowService
    ) { }

  ngOnInit(): void {
    this.bookService.getBooks().subscribe((res: BookModel[]) => {
      this.allBooks = res
      this.myBooks = this.userService.getMyBooks()
      console.log('Books: ', this.allBooks);
    }, (err: any) => {
      if(err.status !== 200)
      {
        alert('Error while fetching books.')
        console.log('While fetching books: ', err);
      }
    })
  }

  getbase64(img: any){
    return img.data;
  }

  handleBorrow(book: any){
   this.borrowService.borrowBook(book.id).subscribe((res: any) => {
    console.log(res);
   }, (err: any) => {
    if(err.status === 404){
      alert('Book Not Available')
    }else if(err.status === 200){
      alert("Book Borrow Successfull. Request pending.")
      this.userService.addToMyBooks({details: book, status: 'Pending Approval'})
      this.myBooks = this.userService.getMyBooks()
    }else{
      alert('Something went wrong')
    }
   })
  }

  isInMyBooks(bookId: String){
    if(this.myBooks !== undefined){
      for(let i =0; i<this.myBooks.length;i++){
        if(bookId === this.myBooks[i].details.id)
        {
          return true;
        }
      }
    }
    return false;
  }

}
