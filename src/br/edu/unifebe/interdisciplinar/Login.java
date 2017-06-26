package br.edu.unifebe.interdisciplinar;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadFichaTec;
import br.edu.unifebe.interdisciplinar.model.SessionUtils;

@ManagedBean
@SessionScoped
public class Login implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String userLogin;
	public String userSenha;
	public static int userId;
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	@PostConstruct
	public void init(){
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserSenha() {
		return userSenha;
	}

	public void setUserSenha(String userSenha) {
		this.userSenha = userSenha;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String buttonLogar() {
		userId = validaUsuario();
		if (userId > 0) {
			if(validaUsuarioStatus(userId) > 0){
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("userid", userId);
				session.setAttribute("userLogin", userLogin);
				return "index";
			}
			else{
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Seu usuário está desativado, verifique com o administrador do sistema",
								null));
				return "login";
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Usuário ou senha incorreta",
							null));
			return "login";
		}
	}
	
	public int validaUsuarioStatus(int userId){
		String sql = "SELECT usu_status FROM `usuario` WHERE usu_id = '" +  userId + "'";
		try {
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		rs = prmt.executeQuery();
		while(rs.next()){
			return rs.getInt("usu_status");
		}
		return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public int validaUsuario(){
		String sql = "SELECT usu_id FROM `usuario` WHERE usu_login = '" + userLogin + "' AND usu_senha = '" + userSenha + "'";
		try {
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		rs = prmt.executeQuery();
			
		while(rs.next()){
			return rs.getInt("usu_id");			
		}
		
		return 0;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
