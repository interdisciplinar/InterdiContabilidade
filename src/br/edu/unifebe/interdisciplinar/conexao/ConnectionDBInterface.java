package br.edu.unifebe.interdisciplinar.conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface ConnectionDBInterface {
	public void iniDB() throws SQLException;
	public void closeDB() throws SQLException;
	public ResultSet getRs();
	public Statement getS();
	public Connection getConn();
	
}
