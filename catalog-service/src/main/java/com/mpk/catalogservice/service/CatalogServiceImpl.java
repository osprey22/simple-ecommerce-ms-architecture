package com.mpk.catalogservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpk.catalogservice.exception.ProductNotFoundException;
import com.mpk.catalogservice.model.Product;
import com.mpk.catalogservice.repository.ProductRepository;

@Service
public class CatalogServiceImpl implements CatalogService {

	@Autowired
	private ProductRepository repository;

	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogServiceImpl.class);

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = repository.findAll();
		if (products.isEmpty()) {
			LOGGER.debug("No products found.");
			throw new ProductNotFoundException();
		} else
			return products;
	}

	@Override
	public Product getProductById(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public Product getProductByDesc(String desc) {
		return repository.findByDescription(desc);
	}

	@Override
	public List<Product> getProductByCategory(String catDesc) {
		return repository.findByCategoryDescription(catDesc);
	}

	@Override
	public BigDecimal getPriceForProduct(Long productId) {
		Product product = repository.findById(productId).orElseThrow(ProductNotFoundException::new);
		return product.getPrice();
	}

}
