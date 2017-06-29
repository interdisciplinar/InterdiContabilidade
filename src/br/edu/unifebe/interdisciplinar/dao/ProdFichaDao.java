package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifebe.interdisciplinar.Produto;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadProdutoFicha;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

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
		sql = "CALL InserirProdutoFicha (?,?,?,?,?)";
		
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeFicha());
			prmt.setString(2, e.getNomeProdFicha());
			prmt.setString(3, e.getCodProdFicha());
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
		String sql="UPDATE `produtosficha` SET `ft_prod_qtd`=? WHERE ft_nome = '"+e.getNomeFicha()+"' "
				+ " AND ft_prod_cod = '"+ e.getCodProdFicha() +"'"; //vai ser removido

		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setInt(1, e.getQtdProdFicha());
			prmt.executeUpdate();
			//return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println(" not beleza");
			//return false;
		}
		
	}

	@Override
	public List<CadProdutoFicha> getListar(String ft_nome) throws SQLException {
		String sql = "CALL ListaProdutosFicha (?)";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		prmt.setString(1, ft_nome);
		ResultSet rs;
		CadProdutoFicha pf;
		List<CadProdutoFicha> listProdutosFicha = new ArrayList<CadProdutoFicha>();
		rs = prmt.executeQuery();
			
		while(rs.next()){
			pf = new CadProdutoFicha();
			pf.setNomeFicha(rs.getString("ft_nome"));
			pf.setNomeProdFicha(rs.getString("ft_prod_nome"));
			pf.setCodProdFicha(rs.getString("ft_prod_cod"));
			pf.setQtdProdFicha(rs.getInt("ft_prod_qtd"));
			pf.setCustoProdFicha(rs.getDouble("ft_prod_custo"));
			listProdutosFicha.add(pf);
		}
		
		return listProdutosFicha;
	}

	@Override
	public boolean setExcluir(CadProdutoFicha e) throws SQLException {
		String sql = "CALL DeletaProdutoFicha (?,?)";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeFicha());
			prmt.setString(2, e.getCodProdFicha());
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
		
}
