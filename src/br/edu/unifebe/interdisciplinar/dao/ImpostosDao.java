package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.edu.unifebe.interdisciplinar.conexao.Conexao;
import br.edu.unifebe.interdisciplinar.model.CadImposto;

public class ImpostosDao implements IDao<CadImposto>{
	
	private Connection conexao = null;
	
	public ImpostosDao() throws SQLException {
		conexao = Conexao.getConection();
	}

	@Override
	public boolean setIncluir(CadImposto e) throws SQLException {
		return false;
	}

	@Override
	public void setEditar(CadImposto e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CadImposto> getListar(String e) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setExcluir(CadImposto e) throws SQLException {
		return false;
	}

}
