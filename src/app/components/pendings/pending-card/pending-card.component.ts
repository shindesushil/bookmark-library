import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-pending-card',
  templateUrl: './pending-card.component.html',
  styleUrls: ['./pending-card.component.scss']
})
export class PendingCardComponent implements OnInit {

  @Input('source') data: any;
  @Output('onApprove') onApprove = new EventEmitter<string>();
  @Output('onReject') onReject = new EventEmitter<string>();

  constructor(private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    console.log(this.data);
    
  }

  getImageData(img: any){
    if(!img){
      return '../../../../assets/default-user.png'
    }
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/jpeg;base64,' + img.data)
  }

  handleApprove(recordId: string){
    this.onApprove.emit(recordId)
  }

  handleReject(recordId: string){
    this.onReject.emit(recordId)
  }


}
