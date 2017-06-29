package br.edu.unifebe.interdisciplinar;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.unifebe.interdisciplinar.dao.UsuarioDao;

@ManagedBean
@SessionScoped
public class Inicial {
	private UsuarioDao usuarioDao;
	
	@PostConstruct
	public void init(){
		
	}
	
	public boolean getPermissao(){
		try {
			usuarioDao = new UsuarioDao();
			return usuarioDao.verificarPermissao(Login.userLogin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
