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
	public String getDtCria��o() {
		return dtCriacao;
	}
	public void setDtCria��o(String dtCria��o) {
		this.dtCriacao = dtCria��o;
	}
	public String getDtAltera��o() {
		return dtAlteracao;
	}
	public void setDtAltera��o(String dtAltera��o) {
		this.dtAlteracao = dtAltera��o;
	}
	public double getTotalCusto() {
		return totalCusto;
	}
	public void setTotalCusto(double totalCusto) {
		this.totalCusto = totalCusto;
	}
}
