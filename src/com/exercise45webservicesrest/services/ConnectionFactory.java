package com.exercise45webservicesrest.services;

public class ConnectionFactory {

	//Se implementa el singleton
	
	
	//paso2 crear una instancia de la clase
	private static ConnectionFactory connection=null;
	
	//paso1 el metodo constructor privado
	private void ConnectionFactory()
	{
	}
	
	//paso3 crear el metodo getInstance
	public ConnectionFactory getInstance()
	{
		if(connection == null)
		{
			connection= new ConnectionFactory();
		}
		return connection;
	}
	
	
}
