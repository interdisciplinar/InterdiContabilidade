package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.edu.unifebe.interdisciplinar.conexao.Conexao;
import br.edu.unifebe.interdisciplinar.model.CalcPrecoFinal;

public class PrecoFinalDao implements IDao<CalcPrecoFinal>{
	
	private Connection conexao = null;
	
	public PrecoFinalDao() throws SQLException {
		conexao = Conexao.getConection();
	}

	@Override
	public boolean setIncluir(CalcPrecoFinal e) throws SQLException {
		return false;
	}

	@Override
	public void setEditar(CalcPrecoFinal e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CalcPrecoFinal> getListar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExcluir(CalcPrecoFinal e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
