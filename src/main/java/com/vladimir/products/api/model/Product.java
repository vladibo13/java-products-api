package com.vladimir.products.api.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="products")
public class Product {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, unique=true)
	@Size(min=1, message="name cannot be less than 1 character")
	@ApiModelProperty(notes="itemNo generated automaticaly by using UUID libary")
	private String itemNO;
	
	@NotNull(message="name cannot be null")
	@Size(min=1, max=20, message="name cannot be less than 1  and bigger than 20")
	@Column(nullable=false, length=50)
	@ApiModelProperty(notes="The product name")
	private String name;
	
	@NotNull(message="amount cannot be null")
	@Size(min=1, message="amount cannot be less than 1 character")
	@Column(nullable=false)
	@ApiModelProperty(notes="Amount of products")
	private String amount;
	
	@NotNull(message="name cannot be null")
	@Size(min=1, message="inventoryCode cannot be less than 1 character")
	@Column(nullable=false, unique=true)
	@ApiModelProperty(notes="Product inventoryCode")
	private String inventoryCode;

	public String getItemNO() {
		return itemNO;
	}

	public void setItemNO(String itemNO) {
		this.itemNO = itemNO;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}

	
	
	
	
	
}
