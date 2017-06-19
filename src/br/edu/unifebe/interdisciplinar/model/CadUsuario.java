package br.edu.unifebe.interdisciplinar.model;

public class CadUsuario {
	private int idUser;
	private String NomeUser;
	private String login;
	private String senha;
	private String permissao;
	private String status;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNomeUser() {
		return NomeUser;
	}
	public void setNomeUser(String nomeUser) {
		NomeUser = nomeUser;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPermissao() {
		return permissao;
	}
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
