import { Binary } from "@angular/compiler";

export interface BookModel{
    id: String;
    name: String;
    author: String;
    price: String;
    genre: String;
    pages: Number;
    description: String;
    image: BinaryType,
    copiesAvailable: Number
}