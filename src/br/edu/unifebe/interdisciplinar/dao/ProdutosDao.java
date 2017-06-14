package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifebe.interdisciplinar.Produto;
import br.edu.unifebe.interdisciplinar.conexao.Conexao;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

public class ProdutosDao implements IDao<CadProdutos>{
	
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	public ProdutosDao() throws SQLException {
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}

	@Override
	public boolean setIncluir(CadProdutos e) {
		String sql;
			//se for primeira vez, ou seja, datacriação not null
			sql = "INSERT INTO produtos(`prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`,"
					+ " `prod_dt_alteracao`, `prod_usu_id_criacao`, `prod_usu_id_alteracao`) "
					+ "VALUES (?,?,?,?,CURDATE(),?,?,?)";
			
//			sql = "INSERT INTO produtos(`prod_cod`, `prod_nome`, `prod_servico`, `prod_custo`, `prod_dt_criacao`,"
//					+ " `prod_usu_id_criacao`) VALUES (?,?,?,?,NOW(),?);";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getCodProduto());
			prmt.setString(2, e.getNomeProduto());
			prmt.setInt(3, Produto.getIntProdutoTipo(e.getServico()));
			prmt.setDouble(4, e.getCusto());
			prmt.setDate(5, null);
			prmt.setInt(6, 1); //Verificar metodo para salvar usuario
			prmt.setString(7, null);
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void setEditar(CadProdutos e) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<CadProdutos> getListar() throws SQLException {
		CadProdutos p;
		List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
		String sql = "SELECT * FROM produtos;";
		System.out.println("siefjseo");
		
			PreparedStatement  prmt = conexao.prepareStatement(sql);
			ResultSet rs;
			
				rs = prmt.executeQuery();
					
				while(rs.next()){
					p = new CadProdutos();
					p.setCodProduto(rs.getString("prod_cod"));
					p.setNomeProduto(rs.getString("prod_nome"));
					p.setCusto(rs.getDouble("prod_custo"));
					p.setServico(Produto.getStringProdutoTipo(rs.getInt("prod_servico")));
					listProdutos.add(p);
				}
			
		return listProdutos;
	}

	@Override
	public void setExcluir(CadProdutos e) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public boolean getProdutoRegister() throws SQLException{
		CadProdutos P = new CadProdutos();
		List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
		String sql = "SELECT * from produtos LIMIT 1;";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
				
		if(rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
}
