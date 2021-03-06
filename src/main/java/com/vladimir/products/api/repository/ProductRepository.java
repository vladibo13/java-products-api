package com.vladimir.products.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vladimir.products.api.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@Query(value = "SELECT * FROM products  where itemNO = :id ", nativeQuery = true)
	public Product findByItemNo(@Param("id") String id);	
}
