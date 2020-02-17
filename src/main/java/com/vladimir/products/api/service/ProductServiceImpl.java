package com.vladimir.products.api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vladimir.products.api.model.Product;
import com.vladimir.products.api.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub
		product.setItemNO(UUID.randomUUID().toString());
		return productRepository.save(product);
	}

	@Override
	public List<Product> getallProducts() {
		// TODO Auto-generated method stub
		return (List<Product>) productRepository.findAll();
	}

	@Override
	public Product getProductById(String id) {
		// TODO Auto-generated method stub
		return productRepository.findByItemNo(id);
	}

	@Override
	public void deleteProductById(String id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(Long.parseLong(id));
	}

	@Override
	public Product toWithdrawal(String id, String amountToWithdrawal) {
		// TODO Auto-generated method stub
		Product productFromDb = productRepository.findById(Long.parseLong(id)).get();
		Integer finalAmount = Integer.parseInt(productFromDb.getAmount()) - Integer.parseInt(amountToWithdrawal);
		productFromDb.setAmount(finalAmount.toString());
		return productRepository.save(productFromDb);
	}

	@Override
	public Product toDeposit(String id, String amountToDeposit) {
		// TODO Auto-generated method stub
		Product productFromDb = productRepository.findById(Long.parseLong(id)).get();
		Integer finalAmount = Integer.parseInt(productFromDb.getAmount()) + Integer.parseInt(amountToDeposit);
		productFromDb.setAmount(finalAmount.toString());
		return productRepository.save(productFromDb);
	}

	

	

	
}
