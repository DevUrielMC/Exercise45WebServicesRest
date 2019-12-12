package com.exercise45webservicesrest.services;

import java.util.ArrayList;

import com.exercise45webservicesrest.model.Product;

public interface DAOProducts {

	public boolean SaveProduct(Product myProduct);
	public boolean UpdateProduct(int idProduct);
	public boolean DeleteProduct(int idProduct);
	public Product ReadProduct(int idProduct);
	public ArrayList<Product> ReadAllProduct();
	
}
