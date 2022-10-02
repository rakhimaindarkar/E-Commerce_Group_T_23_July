package com.ecomm.Order;

import com.ecomm.Product.Products;

public class Orders {
	int ItemNo;
	Products product;
	int NoOfProduct;
	
	public int getItemNo() {
		return ItemNo;
	}
	public void setItemNo(int itemNo) {
		ItemNo = itemNo;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public int getNoOfProduct() {
		return NoOfProduct;
	}
	public void setNoOfProduct(int noOfProduct) {
		NoOfProduct = noOfProduct;
	}
	public Orders() {
		super();
		
	}
	public Orders(int itemNo, Products product, int noOfProduct) {
		super();
		ItemNo = itemNo;
		this.product = product;
		NoOfProduct = noOfProduct;
	}
	
	

}
