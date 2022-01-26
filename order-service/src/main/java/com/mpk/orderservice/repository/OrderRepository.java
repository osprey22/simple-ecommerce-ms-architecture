package com.mpk.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mpk.orderservice.model.Order;

public interface OrderRepository extends MongoRepository<Order, Long>{

}
