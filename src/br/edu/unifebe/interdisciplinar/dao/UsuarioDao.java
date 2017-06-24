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
		String sql = "CALL InserirUsuario (?,?,?,?)";
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
		String sql = "CALL EditaUsuario (?,?,?,?)";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeUser());
			prmt.setString(2, e.getLogin());
			prmt.setString(3, e.getSenha());
			prmt.setInt(4, Usuario.getIntUsuarioTipo(e.getPermissao()));
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
	public List<CadUsuario> getListar(String e) throws SQLException {
		CadUsuario cadUsuario;
		List<CadUsuario> listProdutos = new ArrayList<CadUsuario>();
		String sql = "CALL ListaUsuarios ()";
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
		String sql, usu_login;
		PreparedStatement prmt;
		if(verificarStatus(e.getLogin())){
			sql = "CALL AlteraStatusUsuario (?,0)";
			usu_login = e.getLogin();
		}
		else{
			sql = "CALL AlteraStatusUsuario (?,1)";
			usu_login = e.getLogin();
		}
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, usu_login);
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public boolean verificaUsuario(String login) throws SQLException{
		String sql = "CALL VerificaUsuario ('" + login + "')";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		
		rs = prmt.executeQuery();
		if(rs.next()){
			return true;
		}
		return false;
	}
	
	public boolean verificarStatus(String login) throws SQLException{
		String sql = "CALL VerificaStatus ('" + login + "')";
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
