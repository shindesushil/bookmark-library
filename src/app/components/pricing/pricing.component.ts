import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SubscriptionModel } from 'src/app/models/SubscriptionModel';
import { PaymentService } from 'src/app/services/payment.service';
import { SubscriptionService } from 'src/app/services/subscription.service';
import { UserService } from 'src/app/services/user.service';
import { WindowrefService } from 'src/app/services/windowref.service';


@Component({
  selector: 'app-pricing',
  templateUrl: './pricing.component.html',
  styleUrls: ['./pricing.component.scss']
})
export class PricingComponent implements OnInit {

  allSubs: SubscriptionModel[];

  constructor(
    private subscriptionService: SubscriptionService, 
    private userService: UserService,
    private paymentService: PaymentService,
    private windowRef: WindowrefService,
    private router: Router
    ) { 
  }

  ngOnInit(): void {

    this.subscriptionService.getSubscriptions().subscribe((res) => {
      this.allSubs = res
    }, (err) => {
      if(err.status === 200){
        console.log('Subscriptions Loaded');
      }else{
        console.log('Error while getting subscriptions!');
      }
    })
    
  }

  handlePayment(subId: string){
    let userId = this.userService.getUserDetails().id
    console.log(subId, ' ', userId);

    this.paymentService.createTransaction(userId, subId).subscribe((res: any) => {
      
      console.log("Resp: ", res);
      if(res){
        console.log('hre');
        
        this.openPaymentModal(res, subId)
      }
      
    }, (err: any) => {
      if(err.status === 200){
        console.log("all ok!");
      }else if(err.status === 401){
        // alert('Session Expired')
        console.log('Session Expired');
        
        this.userService.logout();
      }
      else{
        console.log(err);
      }
    })
    
  }

  openPaymentModal(response: any, subId: String){
    let options = {
      order_id: response.orderId,
      key_id: response.key,
      amount: response.amount,
      currency: response.currency,
      name: "The Bookmar Library",
      description: "Membership Payment",
      image: 'https://img.freepik.com/free-vector/hand-drawn-flat-design-stack-books_23-2149342941.jpg',
      handler: (response: any) => {
        this.processPayent(response, subId)
      },
      prefill: {
        name: this.userService.getUserDetails().firstName + " " + this.userService.getUserDetails().lastName,
        email: this.userService.getUserDetails().username
      },
      notes: {
        address: 'Pune'
      },
      theme:{
        color: '#FFB6B9'
      }
    }


    let _window = this.windowRef.nativeWindow;
    let razorpay = new _window.Razorpay(options);
    razorpay.open()

  }

  processPayent(resp: any, subId: String){
    console.log(resp);
    let payment = {
      orderId: resp.razorpay_order_id,
      paymentId: resp.razorpay_payment_id,
      userId: this.userService.getUserDetails().id,
      subscriptionId: subId
    }

    this.paymentService.completeTransaction(payment).subscribe((res: SubscriptionModel) => {
      this.userService.setSubscriptionDetails(res)
      alert('Plan purchase successful...')
      this.router.navigate(['/']).then(() => {
        this.windowRef.nativeWindow.location.reload()
      })
      
    })

  }

}
