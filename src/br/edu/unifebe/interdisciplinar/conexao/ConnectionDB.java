package br.edu.unifebe.interdisciplinar.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB implements ConnectionDBInterface{
	private String database = "intercontabilidade"; //nome da base de dados
	private ResultSet rs;
	private Statement s;
	private Connection conn;
	private String JDBC_DRIVER;  
    private String DB_URL;
    private String USER;
    private String PASS;
	
	public ConnectionDB() {
		
	        JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	        
//	        Conexão com o Banco para servidor Unifebe
	        DB_URL = "jdbc:mysql://localhost/scpps?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        USER = "scpps";
	        PASS = "6t_N6hxOtB-lZO}l";
	        
//	        Conexão com o Banco para máquina local
//	        DB_URL = "jdbc:mysql://localhost/intercontabilidade?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	        USER = "root";
//	        PASS = "";
	        conn = null;
	        	        
	        try {
				/*Conectando ao Servidor de Banco de Dados*/
				Class.forName(JDBC_DRIVER);
				if(s == null) {
					System.out.print("Connecting to a selected database... ");
		            conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            System.out.println("Success!");
				}
			}
			catch(Exception e) {
		 		e.printStackTrace();
			}
	}
	
	public Connection getConnetion() {
		return conn;
	}
	
	
	@Override
	public void iniDB() throws SQLException {
		if(conn == null) {
			try {
				//Conectando ao Servidor de Banco de Dados
				Class.forName(JDBC_DRIVER);
				if(s == null) {
					//System.out.print("Connecting to a selected database... ");
		            conn = DriverManager.getConnection(DB_URL, USER, PASS);
		            System.out.println("Success!");
				}
			}
			catch(Exception e) {
		 		e.printStackTrace();
			}
		}
	}
	
	@Override
	public ResultSet getRs() {
		return rs;
	}
	@Override
	public Statement getS() {
		return s;
	}

	@Override
	public void closeDB() throws SQLException {
		s.close();
		conn.close();
	}
	
	@Override
	public Connection getConn() {
		return conn;
	}
}
