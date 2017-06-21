package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifebe.interdisciplinar.Produto;
import br.edu.unifebe.interdisciplinar.Usuario;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadUsuario;

public class UsuarioDao implements IDao<CadUsuario>{
	
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	public UsuarioDao() throws SQLException {
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}

	@Override
	public boolean setIncluir(CadUsuario e) throws SQLException {
		String sql = "INSERT INTO `usuario`(`usu_nome`, `usu_login`, `usu_senha`, `usu_perfil_id`,`usu_status`) "
				+ "VALUES (?,?,?,?,1)";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeUser());
			prmt.setString(2, e.getLogin());
			prmt.setString(3, e.getSenha());
			prmt.setInt(4, Usuario.getIntUsuarioTipo(e.getPermissao()));
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public void setEditar(CadUsuario e) throws SQLException {
		String sql = "UPDATE usuario SET `usu_nome`=?,`usu_senha`=?,`usu_perfil_id`=?"
				+ " WHERE usu_login = '"+e.getLogin()+"';";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeUser());
			prmt.setString(2, e.getSenha());
			prmt.setInt(3, Usuario.getIntUsuarioTipo(e.getPermissao()));
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
	public List<CadUsuario> getListar() throws SQLException {
		CadUsuario cadUsuario;
		List<CadUsuario> listProdutos = new ArrayList<CadUsuario>();
		String sql = "SELECT * FROM usuario ORDER BY usu_status DESC;";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		
		rs = prmt.executeQuery();
			
		while(rs.next()){
			cadUsuario = new CadUsuario();
			cadUsuario.setIdUser(rs.getInt("usu_id"));
			cadUsuario.setLogin(rs.getString("usu_login"));
			cadUsuario.setNomeUser(rs.getString("usu_nome"));
			cadUsuario.setSenha(rs.getString("usu_senha"));
			cadUsuario.setPermissao(Usuario.getStringUsuarioTipo(rs.getInt("usu_perfil_id")));
			cadUsuario.setStatus(Usuario.getStringUsuarioStatus(rs.getInt("usu_status")));
			listProdutos.add(cadUsuario);
		}
			
		return listProdutos;
	}

	@Override
	public boolean setExcluir(CadUsuario e) throws SQLException {
		String sql;
		if(verificarStatus(e.getLogin())){
			sql = "UPDATE `usuario` SET `usu_status`= 0 WHERE usu_login = '"+ e.getLogin() +"'";
		}
		else{
			sql = "UPDATE `usuario` SET `usu_status`= 1 WHERE usu_login = '"+ e.getLogin() +"'";
		}
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
	
	public boolean verificaUsuario(String login) throws SQLException{
		String sql = "SELECT * FROM `usuario` WHERE usu_login = '" + login + "'";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		
		rs = prmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	public boolean verificarStatus(String login) throws SQLException{
		String sql = "SELECT `usu_status` FROM `usuario` WHERE usu_login = '" + login + "'";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		
		rs = prmt.executeQuery();
		if(rs.next()){
			if(rs.getInt("usu_status") == 0){
				return false;
			}
			else{
				return true;
			}
		}
		return false;
	}
}
