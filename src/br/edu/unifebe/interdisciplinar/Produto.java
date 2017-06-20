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
	private String codProduto = "";
	private String nomeProduto;
	private double custoProduto;
	private String tipoProduto = "Produto";
	private List<String> tipoProdutos;
	private List<CadProdutos> listProdutos = new ArrayList<CadProdutos>();
	private CadProdutos cadProdutos;
	private CadProdutos selectedProduto;
	private ProdutosDao produtosDao;
	private String btnName = "Limpar";
	private String btnName2 = "Salvar";
	private boolean bloqueiaCampo = false;
	private boolean blockBtn = false;
	private ValidaErros validaErros;

	public Produto() {

	}
	
	@PostConstruct
	public void init() {
		getListaProdutos();
		tipoProdutos = new ArrayList<String>();
		tipoProdutos.add("Produto");
		tipoProdutos.add("Serviço");
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

	public String getBtnName2() {
		return btnName2;
	}

	public void setBtnName2(String btnName2) {
		this.btnName2 = btnName2;
	}

	public void buttonSalvar(ActionEvent actionEvent) throws SQLException {
        cadProdutos.setCodProduto(codProduto);
        cadProdutos.setNomeProduto(nomeProduto);
        cadProdutos.setCusto(custoProduto);
        cadProdutos.setServico(tipoProduto);
        validaErros = new ValidaErros(codProduto, nomeProduto, custoProduto);
        if(validaErros.validaProduto()){
        	if(produtosDao.verificaProduto(codProduto)){
        		produtosDao.setEditar(cadProdutos);
        		getListaProdutos();
	        	refresh();
        	}
        	else{
		        if(produtosDao.setIncluir(cadProdutos)){
		        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Produto Adicionado!"));
		        	getListaProdutos();
		        	refresh();
		        }
		        else{
		        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Ocorreu um Erro Fale com o Suporte!"));
		        }
        	}
        	
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
			btnName = "Cancelar";
			btnName2 = "Alterar";
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
		return "Serviço";
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
    	blockBtn = false;
    	btnName2="Salvar";
	}
	
	public boolean validaCampoCodProdutos() {
		try {
			produtosDao = new ProdutosDao();
			if(produtosDao.validaCodProduto(codProduto)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Codigo do Produto já está cadastrado!", null));
				blockBtn = true;
				return false;
			}
			else{
				blockBtn = false;
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
	
	public boolean validaCampoNomeProdutos() {
		try {
			produtosDao = new ProdutosDao();
			if(produtosDao.validaNomeProduto(nomeProduto)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome do Produto já está cadastrado!", null));
				blockBtn = true;
				return false;
			}
			else{
				blockBtn = false;
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }

	public boolean isBlockBtn() {
		return blockBtn;
	}

	public void setBlockBtn(boolean blockBtn) {
		this.blockBtn = blockBtn;
	}
}
