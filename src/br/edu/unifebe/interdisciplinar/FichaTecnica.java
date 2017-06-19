package br.edu.unifebe.interdisciplinar;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import br.edu.unifebe.interdisciplinar.dao.ProdutosDao;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

@ManagedBean
public class FichaTecnica {
	private String nomeFicha;
	private String codProdutoFicha;
	private String nomeProduto;
	private List<String> nomeProdutoFicha;
	private double custoProdutoFicha;
	private int qtdProdutoFicha;
	private ProdutosDao produtosDao;
	private CadProdutos cadProdutos;
	
	
	public FichaTecnica() throws SQLException {
		
	}

	@PostConstruct
	public void init() {
		getListNomeProdutos();
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
	
	public void produtoInfo(){
		getProdutoInfo(nomeProduto);
	}
	
	public void getListNomeProdutos(){
		try {
			produtosDao = new ProdutosDao();
			nomeProdutoFicha = produtosDao.getNomeProduto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getProdutoInfo(String nomeProduto){
		try {
			cadProdutos = new CadProdutos();
			cadProdutos = produtosDao.getProdutoInfo(nomeProduto);
			codProdutoFicha = cadProdutos.getCodProduto();
			custoProdutoFicha = cadProdutos.getCusto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
