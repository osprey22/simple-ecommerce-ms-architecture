package com.mpk.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mpk.orderservice.model.Cart;

public interface CartRepository extends MongoRepository<Cart, Long> {

}
