package br.edu.unifebe.interdisciplinar.model;

import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

public class CadProdutos{
	private String codProduto;
	private String nomeProduto;
	private String servico;
	private double custo;
	/*private String dataCriacao;
	private String dataAlteracao;
	private int idUsuCriacao;
	private int idUsuAlteracao;*/
	
	/*public List<CadProdutos> getListProduto() {
		CadProdutos cadProdutos;
		List<CadProdutos>listProdutos = new ArrayList<CadProdutos>();
		for(int i=0;i<=5;i++){
			cadProdutos = new CadProdutos();
			cadProdutos.setCodProduto(i);
			cadProdutos.setCusto(2.0);
			cadProdutos.setNomeProduto("teste");
			cadProdutos.setServico(i);
			listProdutos.add(cadProdutos);
		}
		return listProdutos;
	}*/
	
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
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
}
