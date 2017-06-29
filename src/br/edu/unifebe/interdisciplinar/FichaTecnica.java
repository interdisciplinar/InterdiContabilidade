package br.edu.unifebe.interdisciplinar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.edu.unifebe.interdisciplinar.dao.FichaTecDao;
import br.edu.unifebe.interdisciplinar.dao.ProdFichaDao;
import br.edu.unifebe.interdisciplinar.dao.ProdutosDao;
import br.edu.unifebe.interdisciplinar.model.CadFichaTec;
import br.edu.unifebe.interdisciplinar.model.CadProdutoFicha;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;
import br.edu.unifebe.interdisciplinar.model.ValidaErros;

@ManagedBean
@SessionScoped
public class FichaTecnica {
	private String nomeFicha;
	private String codProdutoFicha;
	private String nomeProduto;
	private List<String> nomeProdutoFicha;
	private List<String> nomeFichas;
	private List<CadProdutoFicha> listProdutos = new ArrayList<CadProdutoFicha>();
	private double custoProdutoFicha;
	private int qtdProdutoFicha;
	private ProdutosDao produtosDao;
	private ProdFichaDao prodFichaDao;
	private FichaTecDao fichaTecDao;
	private CadProdutos cadProdutos;
	private CadFichaTec cadFichaTec;
	private CadProdutoFicha cadProdutoFicha;
	private boolean bloqueiaCampo = false;
	private boolean bloqueiaCombobox = false;
	private String btnName = "Salvar";
	
	
	public FichaTecnica() throws SQLException {
		
	}

	@PostConstruct
	public void init() {
		listNomeProdutos();
		listNomeFichas();
		try {
			getProdutoInfo(produtosDao.getPrimeiroNomeProduto());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getNomeFicha() {
		return nomeFicha;
	}


	public void setNomeFicha(String nomeFicha) {
		this.nomeFicha = nomeFicha;
	}


	public String getCodProdutoFicha() {
		return codProdutoFicha;
	}


	public void setCodProdutoFicha(String codProdutoFicha) {
		this.codProdutoFicha = codProdutoFicha;
	}


	public List<String> getNomeProdutoFicha() {
		return nomeProdutoFicha;
	}


	public void setNomeProdutoFicha(List<String> nomeProdutoFicha) {
		this.nomeProdutoFicha = nomeProdutoFicha;
	}


	public double getCustoProdutoFicha() {
		return custoProdutoFicha;
	}


	public void setCustoProdutoFicha(double custoProdutoFicha) {
		this.custoProdutoFicha = custoProdutoFicha;
	}


	public int getQtdProdutoFicha() {
		return qtdProdutoFicha;
	}


	public void setQtdProdutoFicha(int qtdProdutoFicha) {
		this.qtdProdutoFicha = qtdProdutoFicha;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public boolean isBloqueiaCampo() {
		return bloqueiaCampo;
	}

	public void setBloqueiaCampo(boolean bloqueiaCampo) {
		this.bloqueiaCampo = bloqueiaCampo;
	}

	public boolean isBloqueiaCombobox() {
		return bloqueiaCombobox;
	}

	public void setBloqueiaCombobox(boolean bloqueiaCombobox) {
		this.bloqueiaCombobox = bloqueiaCombobox;
	}

	public List<CadProdutoFicha> getListProdutos() {
		return listProdutos;
	}

	public void setListProdutos(List<CadProdutoFicha> listProdutos) {
		this.listProdutos = listProdutos;
	}
	
	public List<String> getNomeFichas() {
		return nomeFichas;
	}

	public void setNomeFichas(List<String> nomeFichas) {
		this.nomeFichas = nomeFichas;
	}

	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}

	public void buttonSalvar(ActionEvent actionEvent) throws SQLException {
        cadFichaTec = new CadFichaTec();
        cadFichaTec.setNomeFicha(nomeFicha);
        cadFichaTec.setTotalCusto(custoProdutoFicha);
        if(listProdutos.size() > 0){
        	finish();
        	btnName = "Salvar";
        }
        else{
        	ValidaErros validaErros = new ValidaErros(nomeFicha, qtdProdutoFicha);
        	if(validaErros.validaFicha()){
	        	cadProdutoFicha = new CadProdutoFicha();
	        	prodFichaDao = new ProdFichaDao();
	        	cadProdutoFicha.setNomeFicha(nomeFicha);
	        	cadProdutoFicha.setCodProdFicha(codProdutoFicha);
	        	cadProdutoFicha.setCustoProdFicha(custoProdutoFicha);
	        	cadProdutoFicha.setNomeProdFicha(nomeProduto);
	        	cadProdutoFicha.setQtdProdFicha(qtdProdutoFicha);
	        	fichaTecDao.setIncluir(cadFichaTec);
	        	prodFichaDao.setIncluir(cadProdutoFicha);
	        	listProdutosFicha();
	        	listNomeFichas();
	        	btnName = "Concluir";
	        	qtdProdutoFicha = 1;
        	}
        }
    }
	
	public void buttonIncluir(ActionEvent actionEvent) throws SQLException {
		fichaTecDao = new FichaTecDao();
		if(fichaTecDao.verificaNomeFicha(nomeFicha)){
			System.out.println(getQtdProdutoFicha());
			prodFichaDao = new ProdFichaDao();
        	cadProdutoFicha.setNomeFicha(nomeFicha);
        	cadProdutoFicha.setCodProdFicha(codProdutoFicha);
        	cadProdutoFicha.setCustoProdFicha(custoProdutoFicha);
        	cadProdutoFicha.setNomeProdFicha(nomeProduto);
        	cadProdutoFicha.setQtdProdFicha(qtdProdutoFicha);
        	prodFichaDao.setIncluir(cadProdutoFicha);
			listProdutosFicha();
			
			qtdProdutoFicha = 1;
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "� necess�rio salvar a ficha antes de incluir produtos nela", "� necess�rio salvar a ficha antes de incluir produtos nela"));
		}
    }
	
	public void buttonExcluir(CadProdutoFicha cadProdutoFicha) throws SQLException {
		prodFichaDao = new ProdFichaDao();
		prodFichaDao.setExcluir(cadProdutoFicha);
    	listProdutosFicha();
    }

	public void produtoInfo(){
		getProdutoInfo(nomeProduto);
	}
	
	public void listNomeProdutos(){
		try {
			produtosDao = new ProdutosDao();
			nomeProdutoFicha = produtosDao.getNomeProduto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void listProdutosFicha(){
		try {
			prodFichaDao = new ProdFichaDao();
			List<CadProdutoFicha> listPf = prodFichaDao.getListar(nomeFicha);
			if(listPf.size() > 0){
				listProdutos = listPf;
			}
			else{
				listProdutos = new ArrayList<CadProdutoFicha>();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void listNomeFichas(){
		try {
			fichaTecDao = new FichaTecDao();
			nomeFichas = new ArrayList<String>();
			for(CadFichaTec ft : fichaTecDao.getListar("")){
				nomeFichas.add(ft.getNomeFicha());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void validaFicha(){
		try {
			System.out.println(nomeFicha);
			if(fichaTecDao.verificaNomeFicha(nomeFicha)){
				btnName = "Concluir";
				listProdutosFicha();
			}
			else{
				finish();
				listProdutosFicha();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getProdutoInfo(String nomeProduto){
		try {
			System.out.println(nomeProduto);
			cadProdutos = new CadProdutos();
			cadProdutos = produtosDao.getProdutoInfo(nomeProduto);
			if(cadProdutos != null){
				codProdutoFicha = cadProdutos.getCodProduto();
				custoProdutoFicha = cadProdutos.getCusto();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeDecimal(){
		qtdProdutoFicha = (int) Math.floor(qtdProdutoFicha);
	}
	
	public void finish(){
		btnName = "Salvar";
		listProdutos.clear();
		nomeFicha = "";
		qtdProdutoFicha = 1;
	}
}
