package com.mpk.catalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mpk.catalogservice.model.Product;
import com.mpk.catalogservice.service.CatalogService;

@RestController
@RequestMapping("/products")
public class CatalogController {

	@Autowired
	private CatalogService service;

	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = service.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = service.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<Product> getProductByDesc(@RequestParam String desc) {
		Product product = service.getProductByDesc(desc);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String catDesc) {
		List<Product> products = service.getProductByCategory(catDesc);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{productId}/price")
	public ResponseEntity<String> getPriceForProduct(@PathVariable String productId) {
		return new ResponseEntity<>(service.getPriceForProduct(Long.valueOf(productId)).toString(), HttpStatus.OK);
	}

}
