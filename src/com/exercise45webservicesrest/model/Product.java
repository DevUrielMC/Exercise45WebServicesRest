package com.exercise45webservicesrest.model;

public class Product {

	private int idProduct;
	private String nameProduct;
	private double priceProduct;
	
	public Product() {
		
	}

	public Product(int idProduct, String nameProduct, double priceProduct) {
		super();
		this.idProduct = idProduct;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public double getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(double priceProduct) {
		this.priceProduct = priceProduct;
	}

	@Override
	public String toString() {
		return "Id Product:" + this.idProduct+ ", Name Product:"+this.nameProduct+", Price Product:"+this.priceProduct;
	}
	
	
	
}
