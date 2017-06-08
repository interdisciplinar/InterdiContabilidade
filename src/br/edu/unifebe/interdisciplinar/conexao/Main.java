package br.edu.unifebe.interdisciplinar.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		//Connection connection = Conexao.getConection();
		ConnectionDB connectionDB = new ConnectionDB();
		System.out.println("Conexão realizada com sucesso!");
	}
}
