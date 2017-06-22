package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.unifebe.interdisciplinar.Produto;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadProdutoFicha;

public class ProdFichaDao implements IDao<CadProdutoFicha> {
	
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	public ProdFichaDao() throws SQLException {
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}
	
	@Override
	public boolean setIncluir(CadProdutoFicha e) throws SQLException {
		String sql;
		//se for primeira vez, ou seja, datacriação not null
		sql = "INSERT INTO `produtosficha`(`ft_nome`, `ft_prod_nome`, `ft_prod_cod`, `ft_prod_custo`, `ft_prod_qtd`)"
				+ " VALUES (?,?,?,?,?)";
		
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeFicha());
			prmt.setString(2, e.getNomeProdFicha());
			prmt.setString(3, e.getNomeCodProdFicha());
			prmt.setDouble(4, e.getCustoProdFicha());
			prmt.setInt(5, e.getQtdProdFicha());
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public void setEditar(CadProdutoFicha e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CadProdutoFicha> getListar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setExcluir(CadProdutoFicha e) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
