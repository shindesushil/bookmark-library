import { Component, OnInit, Input } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-book-card',
  templateUrl: './book-card.component.html',
  styleUrls: ['./book-card.component.scss']
})
export class BookCardComponent implements OnInit {

  @Input('book') book: any;

  constructor(private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
  }

  getImageData(img: any){
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpeg;base64,' + img.data)
  }

}
