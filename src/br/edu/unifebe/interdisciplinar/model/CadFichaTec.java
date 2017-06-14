package br.edu.unifebe.interdisciplinar.model;

public class CadFichaTec {
	private int idFicha;
	private String nomeFicha;
	//private int codProduto;
	private int qtdProdutos;
	private String dtCriacao;
	private String dtAlteracao;
	private double totalCusto;
	//private int idUsuCriacao;
	//private int idUsuAlteracao;
	public int getIdFicha() {
		return idFicha;
	}
	public void setIdFicha(int idFicha) {
		this.idFicha = idFicha;
	}
	public String getNomeFicha() {
		return nomeFicha;
	}
	public void setNomeFicha(String nomeFicha) {
		this.nomeFicha = nomeFicha;
	}
	public int getQtdProdutos() {
		return qtdProdutos;
	}
	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}
	public String getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(String dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public String getDtAlteracao() {
		return dtAlteracao;
	}
	public void setDtAlteracao(String dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}
	public double getTotalCusto() {
		return totalCusto;
	}
	public void setTotalCusto(double totalCusto) {
		this.totalCusto = totalCusto;
	}
}
