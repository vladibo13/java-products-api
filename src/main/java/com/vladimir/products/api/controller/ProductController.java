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

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private Environment env;
	
	@Autowired
    private ProductService productService;

	
	@GetMapping("/status")
	@ApiOperation(value="Server Status")
	public String status() {
		return "server running on port " + env.getProperty("local.server.port");
	}
	
	@ApiOperation(value="List of the products items")
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> productList = productService.getallProducts();
		return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	@ApiOperation(value="Read product details by itemNo", notes="Provide an id to look for specific product")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id){
		Product product = productService.getProductById(id);
		if(product == null) {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@ApiOperation(value="Add product to stock", notes="Provide json, itemNo generated automatically(no need to pass it in json, other fields mandatory)")
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);
		return new ResponseEntity<Product>(savedProduct, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/products/{id}/w/{amountToWithdrawal}")
	@ApiOperation(value="Withdrawal quantity of a specific product from stock", notes="Provide an id and amount to withdrawal")
	public ResponseEntity<Product> toWithdrawal(@PathVariable("id") String id, @PathVariable("amountToWithdrawal") String amountToWithdrawal){
		Product updatedProduct = productService.toWithdrawal(id, amountToWithdrawal);
		if(updatedProduct == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@PutMapping("/products/{id}/d/{amountToDeposit}")
	@ApiOperation(value="Deposit quantity of a specific product to stock", notes="Provide an id and amount to deposit")
	public ResponseEntity<Product> toDeposit(@PathVariable("id") String id, @PathVariable("amountToDeposit") String amountToDeposit){
		Product updatedProduct = productService.toDeposit(id, amountToDeposit);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/products/{id}")
	@ApiOperation(value="Delete specific product from stock", notes="Provide an id to look for specific product")
	public ResponseEntity<String> deleteProductById(@PathVariable("id") String id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}
}
