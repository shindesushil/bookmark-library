import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BooksComponent } from './components/books/books.component';
import { SubscriptionsComponent } from './components/subscriptions/subscriptions.component';
import { PendingComponent } from './components/pendings/pending/pending.component';
import { AllBooksComponent } from './components/books/all-books/all-books.component';
import { AddBookComponent } from './components/books/add-book/add-book.component';
import { PendingBorrowComponent } from './components/pendings/pending-borrow/pending-borrow.component';
import { PendingReturnComponent } from './components/pendings/pending-return/pending-return.component';
import { AllSubscriptionsComponent } from './components/subscriptions/all-subscriptions/all-subscriptions.component';
import { AddSubscriptionComponent } from './components/subscriptions/add-subscription/add-subscription.component';

const routes: Routes = [
  {path:'', component: DashboardComponent},
  {path:'login', component: LoginComponent},
  {path:'books', component: BooksComponent, children: [
    {path: '', component: AllBooksComponent},
    {path: 'add', component: AddBookComponent}
  ]},
  {path:'subscriptions', component: SubscriptionsComponent, children: [
    {path: '', component: AllSubscriptionsComponent},
    {path: 'add', component: AddSubscriptionComponent}
  ]},
  {path:'pending', component: PendingComponent, children: [
    {path: '', component: PendingBorrowComponent},
    {path: 'return', component: PendingReturnComponent}
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
