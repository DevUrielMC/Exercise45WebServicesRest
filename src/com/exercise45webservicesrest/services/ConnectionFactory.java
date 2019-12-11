package com.exercise45webservicesrest.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	//declaracion de objetos
	static Properties props= new Properties(); 		//objeto properties
	static InputStream in= null;	//obtenemos el dao properties
	
	//declaro variables
	static String driver="";
	static String urlServer="";
	static String username="";
	static String password="";
	
	//paso2 crear una instancia de la clase
	
	private static ConnectionFactory connection =null;
	public static Connection conn=null;
	
	
	public static Connection getConnection() {
		
		in= connection.getClass().getClassLoader().getResourceAsStream("dao.properties");	
		
		try {
			props.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(props!=null) 
		{
			driver= props.getProperty("driver");
			urlServer= props.getProperty("urlServer");
			username= props.getProperty("username");
			password= props.getProperty("password");
			
		}
		
		try {
			Class.forName(driver);
			conn= DriverManager.getConnection(urlServer, username, password);
			
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//paso1 el metodo constructor privado
	//Se implementa el singleton
	private void ConnectionFactory()
	{
	}
	
	//paso3 crear el metodo getInstance
	public static ConnectionFactory getInstance()
	{
		if(connection == null)
		{
			connection= new ConnectionFactory();
		}
		return connection;
	}
	
	
}
