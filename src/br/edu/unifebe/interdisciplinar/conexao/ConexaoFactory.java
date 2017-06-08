package br.edu.unifebe.interdisciplinar.conexao;

import java.sql.Connection;

public interface ConexaoFactory {
	public Connection getConection();
}
