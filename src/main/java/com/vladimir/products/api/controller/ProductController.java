package com.vladimir.products.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.products.api.model.Product;
import com.vladimir.products.api.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private Environment env;
	
	@Autowired
    private ProductService productService;

	
	@GetMapping("/status")
	public String status() {
		return "server running on port " + env.getProperty("local.server.port");
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = productService.getallProducts();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id){
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/w/{amountToWithdrawal}")
	public ResponseEntity<Product> toWithdrawal(@PathVariable("id") String id, @PathVariable("amountToWithdrawal") String amountToWithdrawal){
		Product updatedProduct = productService.toWithdrawal(id, amountToWithdrawal);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/d/{amountToDeposit}")
	public ResponseEntity<Product> toDeposit(@PathVariable("id") String id, @PathVariable("amountToDeposit") String amountToDeposit){
		Product updatedProduct = productService.toDeposit(id, amountToDeposit);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") String id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
}
