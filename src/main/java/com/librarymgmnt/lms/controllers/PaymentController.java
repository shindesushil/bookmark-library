package com.librarymgmnt.lms.controllers;


import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmnt.lms.dtos.CompleteTransactionRequestDto;
import com.librarymgmnt.lms.dtos.CompleteTransactionResponseDto;
import com.librarymgmnt.lms.dtos.TransactionDetailsResponseDto;
import com.librarymgmnt.lms.models.OrderModel;
import com.librarymgmnt.lms.models.SubscriptionModel;
import com.librarymgmnt.lms.models.UserSubscriptionsModel;
import com.librarymgmnt.lms.repositories.OrderRepo;
import com.librarymgmnt.lms.repositories.SubscriptionRepo;
import com.librarymgmnt.lms.repositories.UserSubscriptionRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {
	
	@Autowired
	private SubscriptionRepo subscriptionRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private UserSubscriptionRepo userSubRepo;
	
	
	private final String KEY = "rzp_test_4K38OBBFKLB4F6";
	private final String SECRET = "bPvTtTgX1xa7bhDOFG2UnFn4";
	private final String CURRENCY = "INR";

	@PostMapping("")
	private TransactionDetailsResponseDto createOrder(
			@RequestParam("subscriptionId") String subscriptionId,
			@RequestParam("userId") String userId
			) throws RazorpayException {
		
		String amount = getOrderAmount(subscriptionId);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", Integer.parseInt(amount) * 100);
		jsonObject.put("currency", CURRENCY);
		
		RazorpayClient razorpayClient = new RazorpayClient(KEY, SECRET);
		
		Order order = razorpayClient.orders.create(jsonObject);
		
		// Adding order ; status = 'processing'
		OrderModel newOrder = new OrderModel(order.get("id"), userId, amount,"");
		orderRepo.save(newOrder);
		
		TransactionDetailsResponseDto transDetailsResponseDto = prepareTransaction(order);
		
		return transDetailsResponseDto;
	}
	
	private TransactionDetailsResponseDto prepareTransaction(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		TransactionDetailsResponseDto transactionDetails = new TransactionDetailsResponseDto(orderId, amount, currency, KEY);
		
		return transactionDetails;
	}
	
	private String getOrderAmount(String subscriptionId) {
		Optional<SubscriptionModel> subscription = subscriptionRepo.findById(subscriptionId);
		return subscription.get().getPrice();
	}
	
	@PostMapping("complete")
	private ResponseEntity<CompleteTransactionResponseDto> completeTransaction(@RequestBody CompleteTransactionRequestDto payment){
		
		
		
		
		SubscriptionModel subscription = subscriptionRepo.findById(payment.getSubscriptionId()).get();
		int duration = subscription.getDuration();
		String expiresOn = DateUtils.addMonths(new Date(), duration).toString();
		
		UserSubscriptionsModel userSubsrciption = new UserSubscriptionsModel(
				UUID.randomUUID().toString(), payment.getUserId(), payment.getSubscriptionId(), expiresOn);
		
		userSubRepo.save(userSubsrciption);
		
		// Updating order ; status = 'complete'
		Optional<OrderModel> order = orderRepo.findById(payment.getOrderId());
		order.get().setPaymentId(payment.getPaymentId());
		order.get().setStatus("complete");
		orderRepo.save(order.get());
		
		CompleteTransactionResponseDto response = new CompleteTransactionResponseDto(
				subscription.getId(), subscription.getDuration(), subscription.getPrice(), expiresOn);
		
		return new ResponseEntity<CompleteTransactionResponseDto>(response, HttpStatus.OK);
	}
	
}
