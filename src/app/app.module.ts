import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { BooksComponent } from './components/books/books.component';
import { SubscriptionsComponent } from './components/subscriptions/subscriptions.component';
import { PendingComponent } from './components/pendings/pending/pending.component';
import { BookCardComponent } from './components/books/book-card/book-card.component';
import { AddBookComponent } from './components/books/add-book/add-book.component';
import { AllBooksComponent } from './components/books/all-books/all-books.component';
import { PendingCardComponent } from './components/pendings/pending-card/pending-card.component';
import { PendingReturnComponent } from './components/pendings/pending-return/pending-return.component';
import { PendingBorrowComponent } from './components/pendings/pending-borrow/pending-borrow.component';
import { AllSubscriptionsComponent } from './components/subscriptions/all-subscriptions/all-subscriptions.component';
import { AddSubscriptionComponent } from './components/subscriptions/add-subscription/add-subscription.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    NavbarComponent,
    BooksComponent,
    SubscriptionsComponent,
    PendingComponent,
    BookCardComponent,
    AddBookComponent,
    AllBooksComponent,
    PendingCardComponent,
    PendingReturnComponent,
    PendingBorrowComponent,
    AllSubscriptionsComponent,
    AddSubscriptionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
