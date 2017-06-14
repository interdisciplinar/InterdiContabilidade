package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadFichaTec;

public class FichaTecDao implements IDao<CadFichaTec>{
	
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	public FichaTecDao() throws SQLException {
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}
	
	@Override
	public boolean setIncluir(CadFichaTec e) throws SQLException {
		return false;
	}

	@Override
	public void setEditar(CadFichaTec e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CadFichaTec> getListar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExcluir(CadFichaTec e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
