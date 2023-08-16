import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { BookModel } from 'src/app/models/BookModel';
import { UserModel } from 'src/app/models/UserModel';
import { BooksService } from 'src/app/services/book.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.scss']
})
export class MyBooksComponent implements OnInit {

  constructor(private booksService: BooksService, 
    private userService: UserService, 
    public route: Router) { }

  myBooks: any[] = []

  ngOnInit(): void {
    this.userService.getBooksByUser(this.userService.getUserDetails().id).subscribe((res: BookModel[]) => {
      this.myBooks = res
      this.userService.setMyBooks(res)
      console.log(res);
    }, (err: any) => {
      if(err.status === 200){
        console.log('All Ok');
      }
      else if(err.status === 401){
        console.log('Unauthorized===');
      }else{
        alert('Something went wrong')
      }
    })
  }

  getbase64(img: any){
    return img.data;
  }

  handleReturn(book: any){
    this.userService.returnBook(book.details.id).subscribe((res : any) => {
      console.log(res);
    }, (err: any) => {
      if(err.status === 200){

        if(book.status === 'Pending Approval'){
          this.userService.deleteFromMyBook(book.details.id)
          this.myBooks = this.myBooks.filter((item: any) => item.details.id !== book.details.id)
        } else if(book.status === 'Approved Borrow'){
          this.userService.deleteFromMyBook(book.details.id)
          book.status = 'Pending Return'
          this.userService.addToMyBooks(book)
          this.myBooks = this.userService.getMyBooks()
        }

        alert("Book Return Success...")
      } else if(err.status === 404){
        alert("Invalid Record...")
      }else{
        alert("Something went wrong...")
      }
    })
  }

  toggleHelpText(id: string){
    console.log(id);
    
    let elm = document.getElementById("help-"+id)
    elm?.classList.toggle("show")
    console.log(elm?.classList);
  }

}
