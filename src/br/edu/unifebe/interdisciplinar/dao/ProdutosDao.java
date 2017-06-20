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
	public void setEditar(CadProdutos e) {
		String sql = "UPDATE produtos SET `prod_servico`=?,`prod_custo`=?,"
				+ "`prod_dt_alteracao`=NOW(),`prod_usu_id_alteracao`=? WHERE prod_cod = '" + e.getCodProduto() +"'";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setInt(1, Produto.getIntProdutoTipo(e.getServico()));
			prmt.setDouble(2, e.getCusto());
			prmt.setInt(3, 1);
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
	public List<CadProdutos> getListar() throws SQLException {
		CadProdutos p;
		List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
		String sql = "SELECT * FROM produtos ORDER BY prod_nome;";
		
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
	public boolean setExcluir(CadProdutos e) throws SQLException {
		String sql;
		//se for primeira vez, ou seja, datacriação not null
		sql = "DELETE FROM `produtos` WHERE prod_cod = '" + e.getCodProduto() +"'";
		
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
	}
	
	public boolean verificaProduto(String codProduto) throws SQLException{
		String sql = "SELECT * from produtos where prod_cod ='" + codProduto +"'";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		while(rs.next()){
			return true;
		}
		return false;
	}
	
	public List<String> getNomeProduto() throws SQLException{
		List<String> listProdutos = new ArrayList<String>();
		String sql = "SELECT prod_nome from produtos ORDER BY prod_nome;";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
				
		while(rs.next()){
			listProdutos.add(rs.getString("prod_nome"));
		}
		return listProdutos;
	}
	
	public String getPrimeiroNomeProduto() throws SQLException{
		String sql = "SELECT prod_nome from produtos ORDER BY prod_nome LIMIT 1;";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		if(rs.next()){
			String s = rs.getString("prod_nome");
			return s;
		}
		return "";
	}
	
	public CadProdutos getProdutoInfo(String nomeProduto) throws SQLException{
		if(nomeProduto != null){
			if(!nomeProduto.equals("")){
				CadProdutos cp = new CadProdutos();
				String sql = "SELECT prod_cod, prod_custo from produtos WHERE prod_nome = '"+ nomeProduto + "';";
				PreparedStatement  prmt = conexao.prepareStatement(sql);
				ResultSet rs = prmt.executeQuery();
				if(rs.next()){
					cp.setCodProduto(rs.getString("prod_cod"));	
					cp.setCusto(rs.getDouble("prod_custo"));
				}
				return cp;
			}
		}
		return null;
	}
	
	public boolean validaCodProduto(String codProduto) throws SQLException{
		if(codProduto != null){
			if(!codProduto.equals("")){
				String sql = "SELECT * from produtos WHERE prod_cod = '"+ codProduto + "';";
				PreparedStatement  prmt = conexao.prepareStatement(sql);
				ResultSet rs = prmt.executeQuery();
				if(rs.next()){
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public boolean validaNomeProduto(String nomeProduto) throws SQLException{
		if(nomeProduto != null){
			if(!nomeProduto.equals("")){
				String sql = "SELECT * from produtos WHERE prod_nome = '"+ nomeProduto + "';";
				PreparedStatement  prmt = conexao.prepareStatement(sql);
				ResultSet rs = prmt.executeQuery();
				if(rs.next()){
					return true;
				}
				return false;
			}
		}
		return false;
	}
}
