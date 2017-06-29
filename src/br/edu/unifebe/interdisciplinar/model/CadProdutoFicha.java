package br.edu.unifebe.interdisciplinar.model;

public class CadProdutoFicha {
	public String nomeFicha;
	public String nomeProdFicha;
	public String codProdFicha;
	public double custoProdFicha;
	public double custoTotalFT;
	public int qtdProdFicha; 
	
	public double getCustoTotalFT() {
		return custoTotalFT;
	}
	public void setCustoTotalFT(double custoTotalFT) {
		this.custoTotalFT = custoTotalFT;
	}
	public String getNomeFicha() {
		return nomeFicha;
	}
	public void setNomeFicha(String nomeFicha) {
		this.nomeFicha = nomeFicha;
	}
	public String getNomeProdFicha() {
		return nomeProdFicha;
	}
	public void setNomeProdFicha(String nomeProdFicha) {
		this.nomeProdFicha = nomeProdFicha;
	}
	public String getCodProdFicha() {
		return codProdFicha;
	}
	public void setCodProdFicha(String nomeCodProdFicha) {
		this.codProdFicha = nomeCodProdFicha;
	}
	public double getCustoProdFicha() {
		return custoProdFicha;
	}
	public void setCustoProdFicha(double custoProdFicha) {
		this.custoProdFicha = custoProdFicha;
	}
	public int getQtdProdFicha() {
		return qtdProdFicha;
	}
	public void setQtdProdFicha(int qtdProdFicha) {
		this.qtdProdFicha = qtdProdFicha;
	}
	
	
	
}
