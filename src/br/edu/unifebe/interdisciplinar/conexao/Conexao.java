package br.edu.unifebe.interdisciplinar.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	//agora iremos implementar dentro da fabrica o padr�o singleton
	private static Connection conexao = null;
	
	public Conexao() {
		
	}
	
	public static Connection getConection() throws SQLException {
		if(conexao == null){
			//cria conex�o
			//DriverManager.registerDriver(new org.sqlite.JDBC());
			String url = "jdbc:sqlite:"
					+ "C:/Users/Vinicius Cavaco/Desktop/Trabalhos da Unifebe/5 Fase/Programa��o III/agenda/agenda.db";
			conexao = DriverManager.getConnection(url);
		}
		return conexao;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}
