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
	public void init() {
		getListaProdutos();
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

	public void buttonAction(ActionEvent actionEvent) throws SQLException {
		System.out.println(custoProduto);
        cadProdutos = new CadProdutos();
        cadProdutos.setCodProduto(codProduto);
        cadProdutos.setNomeProduto(nomeProduto);
        cadProdutos.setCusto(custoProduto);
        cadProdutos.setServico(1);
        produtosDao.setIncluir(cadProdutos);
        getListaProdutos();
    }
	
	public void buttonActionTeste(ActionEvent actionEvent) throws SQLException {
		
    }

	public void getListaProdutos(){
		listProdutos.clear();
		try {
			produtosDao = new ProdutosDao();
		
			for(CadProdutos cp : produtosDao.getListar()){
				listProdutos.add(cp);
				System.out.println("teste");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}	
	}
	
}
