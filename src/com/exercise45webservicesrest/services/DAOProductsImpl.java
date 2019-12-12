package com.exercise45webservicesrest.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.exercise45webservicesrest.model.Product;


@Path("/Products")
public class DAOProductsImpl implements DAOProducts {
	
	Connection conn=null;
	PreparedStatement pstmnt = null;
	ResultSet rs=null;
	
	int rowsAffected=0;
	String SQLSentence="";
	Properties props= new Properties();
	InputStream in= this.getClass().getClassLoader().getResourceAsStream("dao.properties");
	
	public Connection getConnection()
	{
		Connection conn;
		conn= ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean SaveProduct(Product myProduct) {
		conn = getConnection();
		
		try {
			props.load(in);
			if(props!=null)
			{
				SQLSentence=props.getProperty("SQLSaveProduct");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			pstmnt=conn.prepareStatement(SQLSentence);
			pstmnt.setString(1, myProduct.getNameProduct());
			pstmnt.setDouble(2, myProduct.getPriceProduct());
			rowsAffected= pstmnt.executeUpdate();
			System.out.println("Products ADDED TO DATABASE");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return (rowsAffected>0)?true:false;
		}
	}

	@Override
	public boolean UpdateProduct(int idProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@DELETE
	@Path("/{idProduct}")
	public boolean DeleteProduct(@PathParam("idProduct")int idProduct) {
		conn = getConnection();
		
		try {
			props.load(in);
			if(props!=null)
			{
				SQLSentence=props.getProperty("SQLDeleteProduct");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			pstmnt=conn.prepareStatement(SQLSentence);
			pstmnt.setInt(1, idProduct);
			
			rowsAffected= pstmnt.executeUpdate();
			System.out.println("Products DELETED TO DATABASE");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return (rowsAffected>0)?true:false;
		}
	}

	@Override
	public Product ReadProduct(int idProduct) {
		conn = getConnection();
		Product myProduct = new Product();
		
		try {
			props.load(in);
			if(props!=null)
			{
				SQLSentence=props.getProperty("SQLReadProduct");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			pstmnt = conn.prepareStatement(SQLSentence);
			pstmnt.setInt(1, idProduct);
			
			rs= pstmnt.executeQuery();
			//System.out.println("Products DELETED TO DATABASE");
			rs.next();
			myProduct.setIdProduct(rs.getInt(1));
			myProduct.setNameProduct(rs.getString(2));
			myProduct.setPriceProduct(rs.getDouble(3));
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				pstmnt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return myProduct;
		}
	}

	@Override
	public ArrayList<Product> ReadAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

}
