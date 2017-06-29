package br.edu.unifebe.interdisciplinar;

import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.unifebe.interdisciplinar.dao.ProdFichaDao;
import br.edu.unifebe.interdisciplinar.model.CadProdutoFicha;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

@ManagedBean
@SessionScoped
public class PrecoFinal {
	private double percent;
	private double custoProdutoFicha;
	private double custoTotalFT;
	private CadProdutoFicha cadProdutoFicha;
	private String nomeFicha;
	
	
	@PostConstruct
	public void init(){
		percent = 0;
	}
	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	public double getCustoTotalProdutoFicha() {
		return custoProdutoFicha;
	}
	
	public void setCustoProdutoFicha(double custoProdutoFicha) {
		this.custoProdutoFicha = custoProdutoFicha;
	}
	
}
