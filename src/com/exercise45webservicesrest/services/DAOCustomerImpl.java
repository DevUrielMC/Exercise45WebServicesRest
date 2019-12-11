package com.exercise45webservicesrest.services;
import com.exercise45webservicesrest.services.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.exercise45webservicesrest.model.Customer;

@Path("/Customer")
public class DAOCustomerImpl implements DAOCustomer {
	
	Connection conn = null;
	PreparedStatement pstmnt= null;
	ResultSet rs= null;
	String sentenciaSQL="";
	int rowsAffected=0;
	
	private Connection getConnection() {
		Connection conn;
		conn= ConnectionFactory.getInstance().getConnection();
		//conn= ConnectionFactory.getConnection();
		return conn;
	}
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)		//Serializacion cuando . serializacion cuando 
	public void SaveCustomer(Customer myCustomer) {
		// TODO Auto-generated method stub
		String sentenciaSQL="INSERT INTO customer (nameCustomer,addressCustomer,ageCustomer,heightCustomer, weightCustomer,isSingle) VALUES(?,?,?,?,?,?)";
		conn = getConnection(); 			//Conexion a base de datos
		try {
			pstmnt =conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myCustomer.getNameCustomer());
			pstmnt.setString(2, myCustomer.getAddressCustomer());
			pstmnt.setInt(3, myCustomer.getAgeCustomer());
			pstmnt.setDouble(4, myCustomer.getHeightCustomer());
			pstmnt.setDouble(5, myCustomer.getWeightCustomer());
			pstmnt.setBoolean(6, myCustomer.getIsSingle());
			
			pstmnt.executeUpdate();
			System.out.println("RECORD ADDED TO DATABASE");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean UpdateCustomer(Customer myCustomer) {
		conn=getConnection(); //se optiene la conexion
		Properties props =new  Properties();
		InputStream in= this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		try {
			props.load(in);
			if(props!=null)
			{
				sentenciaSQL=props.getProperty("SQLUpdateCustomer");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmnt= conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myCustomer.getNameCustomer());
			pstmnt.setString(2, myCustomer.getAddressCustomer());
			pstmnt.setInt(3, myCustomer.getAgeCustomer());
			pstmnt.setDouble(4, myCustomer.getHeightCustomer());
			pstmnt.setDouble(5, myCustomer.getWeightCustomer());
			pstmnt.setBoolean(6, myCustomer.getIsSingle());
			pstmnt.setInt(7, myCustomer.getIdCustomer());
			rowsAffected = pstmnt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally {
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (rowsAffected!=0)?true:false;
	}

	@Override
	public boolean DeleteCustomer(int idCustommer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer ReadCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Customer> ReadCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
