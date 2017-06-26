package br.edu.unifebe.interdisciplinar.model;

public class ValidaErros {
	/*------------Produtos--------------*/
	private String codProduto;
	private String nomeProduto;
	private double custoProduto;
	
	/*------------Ficha Tecnica--------------*/
	private String nomeFichaTecnica;
	private int qtdProduto;
	
	/*------------Preço Final--------------*/
	private double porcentagem;
	
	/*------------Usuario--------------*/
	private String nomeUsuario;
	private String loginUsuario;
	private String senhaUsuario;
	
	
	/*----------------------------------Produtos-------------------------------------------------*/
	public ValidaErros(String codProduto,String nomeProduto, double custoProduto) {
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.custoProduto = custoProduto;
	}

	public String getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getCustoProduto() {
		return custoProduto;
	}

	public void setCustoProduto(double custoProduto) {
		this.custoProduto = custoProduto;
	}
	
	public boolean validaProduto(){
		if(codProduto != null || nomeProduto != null){
			if(codProduto.equals("") || nomeProduto.equals("") || custoProduto == 0){
				return false;
			}
		}
		return true;
	}
	
	/*----------------------------------Ficha Tecnica-------------------------------------------------*/
	public ValidaErros(String nomeFichaTecnica, int qtdProduto) {
		this.nomeFichaTecnica = nomeFichaTecnica;
		this.qtdProduto = qtdProduto;
	}
	
	public String getNomeFichaTecnica() {
		return nomeFichaTecnica;
	}

	public void setNomeFichaTecnica(String nomeFichaTecnica) {
		this.nomeFichaTecnica = nomeFichaTecnica;
	}
	
	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public boolean validaFicha(){
		if(nomeFichaTecnica != null){
			if(nomeFichaTecnica.equals("") || qtdProduto <= 0){
				return false;
			}
		}
		return true;
	}
	
	/*----------------------------------Preço Final-------------------------------------------------*/
	public ValidaErros(double porcentagem) {
		this.porcentagem = porcentagem;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}
	
	public boolean validaPrecoFinal(){
		if(porcentagem == 0){
			return false;
		}
		
		return true;
	}
	
	/*----------------------------------Usuario-------------------------------------------------*/
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
	
	public ValidaErros(String nomeUsuario, String loginUsuario, String senhaUsuario) {
		this.nomeUsuario = nomeUsuario;
		this.loginUsuario = loginUsuario;
		this.senhaUsuario = senhaUsuario;
	}
	
	public boolean validaUsuario(){
		if(nomeUsuario != null || loginUsuario != null || senhaUsuario != null){
			if(nomeUsuario.equals("") || loginUsuario.equals("") || senhaUsuario.equals("")){
				return false;
			}
		}
		return true;
	}
}
