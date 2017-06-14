package br.edu.unifebe.interdisciplinar;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import br.edu.unifebe.interdisciplinar.dao.ProdutosDao;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

@ManagedBean
public class Produto{
	private String codProduto;
	private String nomeProduto;
	private double custoProduto;
	private String tipoProduto = "Serviço";
	private List<String> tipoProdutos;
	private List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
	private CadProdutos cadProdutos;
	private CadProdutos selectedProduto;
	private ProdutosDao produtosDao;
	private String btnName = "Salvar";
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
	public void init() {
		getListaProdutos();
		tipoProdutos = new ArrayList<String>();
		tipoProdutos.add("Produto");
		tipoProdutos.add("Serviço");
	}
	
	public List<String> getTipoProdutos() {
		return tipoProdutos;
	}

	public void setTipoProdutos(List<String> tipoProdutos) {
		this.tipoProdutos = tipoProdutos;
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
        cadProdutos = new CadProdutos();
        cadProdutos.setCodProduto(codProduto);
        cadProdutos.setNomeProduto(nomeProduto);
        cadProdutos.setCusto(custoProduto);
        cadProdutos.setServico(tipoProduto);
        if(produtosDao.setIncluir(cadProdutos)){
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Produto Adicionado!"));
        	getListaProdutos();
            codProduto = "";
            nomeProduto = "";
            custoProduto = 0;
            tipoProduto = "Produto";
        }
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
        }
    }
	
	public void buttonActionTeste(CadProdutos cadProdutos) throws SQLException {
		if(cadProdutos != null){
			codProduto = cadProdutos.getCodProduto();
			nomeProduto = cadProdutos.getNomeProduto();
			custoProduto = cadProdutos.getCusto();
			tipoProduto = cadProdutos.getServico();
			teste = true;
			
			btnName = "Incluir";
		}
		else{
			System.out.println("falhou");
		}
    }

	public boolean isTeste() {
		return teste;
	}

	public void setTeste(boolean teste) {
		this.teste = teste;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public CadProdutos getSelectedProduto() {
		return selectedProduto;
	}

	public void setSelectedProduto(CadProdutos selectedProduto) {
		this.selectedProduto = selectedProduto;
	}

	public void getListaProdutos(){
		listProdutos.clear();
		try {
			produtosDao = new ProdutosDao();
		
			for(CadProdutos cp : produtosDao.getListar()){
				listProdutos.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static String getStringProdutoTipo(int tipo){
		
		if(tipo == 0){
			return "Produto";
		}
		return "Serviço";
	}
	
	public static int getIntProdutoTipo(String tipo){
		
		if(tipo.equals("Produto")){
			return 0;
		}
		return 1;
	}
	
	/*public void onRowSelect(SelectEvent event) {
		codProduto = ((CadProdutos) event.getObject()).getCodProduto();
		nomeProduto = ((CadProdutos) event.getObject()).getNomeProduto();
		custoProduto = ((CadProdutos) event.getObject()).getCusto();
    }*/
	
}
