package com.librarymgmnt.lms.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.librarymgmnt.lms.models.OrderModel;

public interface OrderRepo extends MongoRepository<OrderModel, String> {

}
