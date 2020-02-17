package com.vladimir.products.api.service;

import java.util.List;


import com.vladimir.products.api.model.Product;

public interface ProductService {
	Product saveProduct(Product product);
	List<Product> getallProducts();
	Product getProductById(String id);
	void deleteProductById(String id);
	Product toWithdrawal(String id, String amountToWithdrawal);
	Product toDeposit(String id, String amountToDeposit);
}
