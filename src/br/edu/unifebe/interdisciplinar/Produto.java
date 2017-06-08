package br.edu.unifebe.interdisciplinar;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.edu.unifebe.interdisciplinar.dao.ProdutosDao;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

@ManagedBean
public class Produto{
	private String codProduto;
	private String nomeProduto;
	private double custoProduto;
	private String tipoProduto;
	private List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
	private CadProdutos cadProdutos;
	private ProdutosDao produtosDao;
	private boolean teste = false;

	public Produto() {
//		CadProdutos cadProdutos = new CadProdutos();
//		listProdutos = new ArrayList<CadProdutos>();
//		for(int i=0;i<=5;i++){
//			cadProdutos.setCodProduto(i);
//			cadProdutos.setCusto(2.0);
//			cadProdutos.setNomeProduto("teste");
//			cadProdutos.setServico(i);
//			listProdutos.add(cadProdutos);
//		}
	}
	
	@PostConstruct
	public void init(){		
		Random r = new Random();
		for(int i=0;i<=5;i++){
			cadProdutos = new CadProdutos();
			cadProdutos.setCodProduto(r.nextInt(30));
			cadProdutos.setCusto(2.0);
			cadProdutos.setNomeProduto("teste" + r.nextInt(30) );
			cadProdutos.setServico(r.nextInt(30));
			listProdutos.add(cadProdutos);
		}
	}
	
	public List<CadProdutos> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<CadProdutos> listProdutos) {
		this.listProdutos = listProdutos;
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
	
	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ TESTE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public boolean isTeste() {
		return teste;
	}

	public void setTeste(boolean teste) {
		this.teste = teste;
	}

	public void buttonAction(ActionEvent actionEvent) throws SQLException {
		System.out.println("O teste funcionou");
        produtosDao = new ProdutosDao();
        cadProdutos = new CadProdutos();
        cadProdutos.setCodProduto(Integer.parseInt(codProduto));
        cadProdutos.setNomeProduto(nomeProduto);
        cadProdutos.setCusto(custoProduto);
        cadProdutos.setServico(1);
        produtosDao.setIncluir(cadProdutos);
    }
	
	public void buttonActionTeste(ActionEvent actionEvent) throws SQLException {
		if(!teste){
			teste = true;
		}
		else{
			teste = false;
		}
    }

	
}
