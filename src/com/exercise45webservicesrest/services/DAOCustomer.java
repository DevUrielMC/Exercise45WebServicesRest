package com.exercise45webservicesrest.services;
import java.util.ArrayList;
import com.exercise45webservicesrest.model.Customer;

public interface DAOCustomer {

	
	public void SaveCustomer(Customer myCustomer);
	public boolean UpdateCostomer(Customer myCustomer);
	public boolean DeleteCustomer(int idCustommer);
	public Customer ReadCustomer(int idCustomer);
	public ArrayList<Customer> ReadCustomer();
}
