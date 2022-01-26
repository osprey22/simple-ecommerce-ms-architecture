package com.mpk.catalogservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpk.catalogservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByDescription(String desc);

	List<Product> findByCategoryDescription(String catDesc);

}
