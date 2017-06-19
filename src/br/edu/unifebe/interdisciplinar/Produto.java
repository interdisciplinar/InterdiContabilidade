package br.edu.unifebe.interdisciplinar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import br.edu.unifebe.interdisciplinar.dao.ProdutosDao;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;
import br.edu.unifebe.interdisciplinar.model.ValidaErros;

@ManagedBean
public class Produto{
	private String codProduto;
	private String nomeProduto;
	private double custoProduto;
	private String tipoProduto = "Produto";
	private List<String> tipoProdutos;
	private List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
	private CadProdutos cadProdutos;
	private CadProdutos selectedProduto;
	private ProdutosDao produtosDao;
	private String btnName = "Limpar";
	private boolean bloqueiaCampo = false;
	private boolean bloqueiaAlterar = true;
	private ValidaErros validaErros;

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
		tipoProdutos.add("Servi�o");
		cadProdutos = new CadProdutos();
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

	public boolean isBloqueiaCampo() {
		return bloqueiaCampo;
	}

	public void setBloqueiaCampo(boolean teste) {
		this.bloqueiaCampo = teste;
	}

	public CadProdutos getSelectedProduto() {
		return selectedProduto;
	}

	public void setSelectedProduto(CadProdutos selectedProduto) {
		this.selectedProduto = selectedProduto;
	}
	
	public String getbtnName() {
		return btnName;
	}

	public void setbtnName(String btnName) {
		this.btnName = btnName;
	}

	public boolean isBloqueiaAlterar() {
		return bloqueiaAlterar;
	}

	public void setBloqueiaAlterar(boolean bloqueiaAlterar) {
		this.bloqueiaAlterar = bloqueiaAlterar;
	}

	public void buttonSalvar(ActionEvent actionEvent) throws SQLException {
        cadProdutos.setCodProduto(codProduto);
        cadProdutos.setNomeProduto(nomeProduto);
        cadProdutos.setCusto(custoProduto);
        cadProdutos.setServico(tipoProduto);
        validaErros = new ValidaErros(codProduto, nomeProduto, custoProduto);
        if(validaErros.validaProduto()){
	        if(produtosDao.setIncluir(cadProdutos)){
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Produto Adicionado!"));
	        	getListaProdutos();
	        	refresh();
	        }
	        else{
	        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
	        }
        }
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Verifique se todos os campos foram preenchidos!", "Verifique se todos os campos foram preenchidos!"));
        }
    }
	
	public void buttonSalvarAlteracao(ActionEvent actionEvent) throws SQLException {
        cadProdutos.setCodProduto(codProduto);
        cadProdutos.setNomeProduto(nomeProduto);
        cadProdutos.setCusto(custoProduto);
        cadProdutos.setServico(tipoProduto);
        validaErros = new ValidaErros(codProduto, nomeProduto, custoProduto);
        if(validaErros.validaProduto()){
	    	produtosDao.setEditar(cadProdutos);
	    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Produto Alterado!"));
	    	getListaProdutos();
	    	refresh();
        }
        else{
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: Verifique se todos os campos foram preenchidos!", "Verifique se todos os campos foram preenchidos!"));
        }
	}
	
	public void buttonActionAlterar(CadProdutos cadProdutos) throws SQLException {
		if(cadProdutos != null){
			codProduto = cadProdutos.getCodProduto();
			nomeProduto = cadProdutos.getNomeProduto();
			custoProduto = cadProdutos.getCusto();
			tipoProduto = cadProdutos.getServico();
			bloqueiaCampo = true;
	    	bloqueiaAlterar = false;
			btnName = "Cancelar";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
		}
    }
	
	public void buttonActionCancelar() throws SQLException {
		refresh();
    }
	
	public void buttonActionExcluir(CadProdutos cadProdutos) throws SQLException {
		if(cadProdutos != null){
			produtosDao.setExcluir(cadProdutos);
			getListaProdutos();
		}
		else{
			System.out.println("falhou");
		}
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
		return "Servi�o";
	}
	
	public static int getIntProdutoTipo(String tipo){
		if(tipo.equals("Produto")){
			return 0;
		}
		return 1;
	}
	
	public void refresh(){
        codProduto = "";
        nomeProduto = "";
        custoProduto = 0;
        tipoProduto = "Produto";
    	bloqueiaCampo = false;
    	bloqueiaAlterar = true;
	}
	
	public void validaCampo() {
		if(codProduto.equals("sssss")){
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
			nomeProduto = "teste1111";
		}
    }
	
}
