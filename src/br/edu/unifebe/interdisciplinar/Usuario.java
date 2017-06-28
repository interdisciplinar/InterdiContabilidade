package br.edu.unifebe.interdisciplinar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.edu.unifebe.interdisciplinar.dao.UsuarioDao;
import br.edu.unifebe.interdisciplinar.model.CadUsuario;
import br.edu.unifebe.interdisciplinar.model.ValidaErros;

@ManagedBean
@SessionScoped
public class Usuario {
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	private String tipoUsuario;
	private List<CadUsuario> listUsuarios;
	private UsuarioDao usuarioDao;
	private CadUsuario cadUsuario;
	private ValidaErros validaErros;
	private boolean bloqueiacampo = false;
	private String btnName = "Salvar";
	private String btnName2 = "Limpar";
	
	@PostConstruct
	public void init(){
		getUsuarios();
		cadUsuario = new CadUsuario();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public List<CadUsuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<CadUsuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}

	public boolean isBloqueiacampo() {
		return bloqueiacampo;
	}

	public void setBloqueiacampo(boolean bloqueiacampo) {
		this.bloqueiacampo = bloqueiacampo;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public String getBtnName2() {
		return btnName2;
	}

	public void setBtnName2(String btnName2) {
		this.btnName2 = btnName2;
	}

	public static String getStringUsuarioTipo(int tipoUsuario){
		if(tipoUsuario == 0){
			return "Usuario";
		}
		return "Administrador";
	}
	
	public static int getIntUsuarioTipo(String tipoUsuario){
		if(tipoUsuario.equals("Usuario")){
			return 0;
		}
		return 1;
	}
	public static String getStringUsuarioStatus(int tipoUsuario){
		if(tipoUsuario == 0){
			return "Desativado";
		}
		return "Ativado";
	}
	
	public static int getIntUsuarioStatus(String tipoUsuario){
		if(tipoUsuario.equals("Desativado")){
			return 0;
		}
		return 1;
	}
	
	public void buttonSalvar(ActionEvent actionEvent) throws SQLException {
		cadUsuario.setNomeUser(nomeUsuario);
		cadUsuario.setLogin(loginUsuario.toLowerCase());
		cadUsuario.setSenha(senhaUsuario);
		cadUsuario.setPermissao(tipoUsuario);
        validaErros = new ValidaErros(nomeUsuario, loginUsuario, senhaUsuario);
        if(validaErros.validaUsuario()){
        	if(usuarioDao.verificaUsuario(loginUsuario)){
        		if(bloqueiacampo){
	        		usuarioDao.setEditar(cadUsuario);
	        		getUsuarios();
	        		refresh();
        		}
        		else{
        			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario já foi cadastrado", "Usuario já foi cadastrado"));
        		}
        	}
        	else{
		        if(usuarioDao.setIncluir(cadUsuario)){
		        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario Adicionado!"));
		        	getUsuarios();
		        	refresh();
		        }
		        else{
		        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
		        }
        	}
        }
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Verifique se todos os campos foram preenchidos!", "Verifique se todos os campos foram preenchidos!"));
        }
    }
	
	public void buttonZerar(ActionEvent actionEvent) throws SQLException {
		refresh();
    }
	
	public void buttonAlterar(CadUsuario cadUsuario){
		if(cadUsuario != null){
			btnName = "Alterar";
			bloqueiacampo = true;
			btnName2 = "Cancelar";
			nomeUsuario = cadUsuario.getNomeUser();
			loginUsuario = cadUsuario.getLogin();
			System.out.println(cadUsuario.getSenha());
			senhaUsuario = cadUsuario.getSenha();
			tipoUsuario = cadUsuario.getPermissao();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
		}
	}
	
	public void buttonDesativar(CadUsuario cadUsuario){
		if(cadUsuario != null){
			try {
				usuarioDao.setExcluir(cadUsuario);
				getUsuarios();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
		}
	}
	
	public void getUsuarios(){
		try {
			usuarioDao = new UsuarioDao();
			listUsuarios = usuarioDao.getListar("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void refresh(){
		nomeUsuario = "";
		loginUsuario = "";
		senhaUsuario = "";
		tipoUsuario = "Usuario";
		btnName = "Salvar";
		btnName2 = "Limpar";
		bloqueiacampo = false;
	}
}
