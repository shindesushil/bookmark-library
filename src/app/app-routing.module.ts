import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BooksComponent } from './components/books/books.component';
import { AuthorsComponent } from './components/authors/authors.component';
import { MyBooksComponent } from './components/my-books/my-books.component';
import { SigninComponent } from './components/signin/signin.component';
import { RegisterComponent } from './components/register/register.component';
import { PricingComponent } from './components/pricing/pricing.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MyPlanComponent } from './components/my-plan/my-plan.component';

const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "books", component: BooksComponent},
  {path: "authors", component: AuthorsComponent},
  {path: "my-books", component: MyBooksComponent},
  {path: "login", component: SigninComponent},
  {path: "register", component: RegisterComponent},
  {path: "pricing", component: PricingComponent},
  {path: "profile", component: ProfileComponent, children:[
    {path:'my-books', component:MyBooksComponent},
    {path:'my-plan', component: MyPlanComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
