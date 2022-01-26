package com.mpk.catalogservice.service;

import java.math.BigDecimal;
import java.util.List;

import com.mpk.catalogservice.model.Product;

public interface CatalogService {

	List<Product> getAllProducts();

	Product getProductById(Long id);

	Product getProductByDesc(String desc);

	List<Product> getProductByCategory(String category);

	BigDecimal getPriceForProduct(Long productId);

}
