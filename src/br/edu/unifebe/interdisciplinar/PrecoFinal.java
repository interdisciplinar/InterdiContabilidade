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

@ManagedBean
@SessionScoped
public class PrecoFinal {
	private double percent = 0;
	private double custoProdutoFicha = 0;
	private double custoTotalFT = 0;
	private double valorImpo = 8;
	private String nomeFicha;
	private ProdFichaDao prodFichaDao;
	private FichaTecDao fichaTecDao;
	private String tipoFiscal;
	private String faixa;
	private List<String> faixas;

	
	@PostConstruct
	public void init(){
		percent = 0;
		valorImpo = 8;
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
	public double getCustoProdutoFicha() {
		return custoProdutoFicha;
	}
	public String getTipoFiscal() {
		return tipoFiscal;
	}
	public void setTipoFiscal(String tipoFiscal) {
		this.tipoFiscal = tipoFiscal;
	}
	
	public double getValorImpo() {
		return valorImpo;
	}
	public void setValorImpo(double valorImp) {
		this.valorImpo = valorImp;
	}

	public String getFaixa() {
		return faixa;
	}
	public void setFaixa(String faixa) {
		this.faixa = faixa;
	}
	public List<String> getFaixas() {
		return faixas;
	}
	public void setFaixas(List<String> faixas) {
		this.faixas = faixas;
	}
	
	public void getListFaixas(){
		if(tipoFiscal.equals("Simples")){
			faixas = new ArrayList<String>();
			faixas.add("Até 180.000,00");
			faixas.add("De 180.000,01 até 360.000,00");
			faixas.add("De 360.000,01 até 540.000,00");
			faixas.add("De 540.000,01 até 720.000,00");
		}
		else{
			faixas = new ArrayList<String>();
			faixas.add("");
		}
	}
	
	public void getFichaInfo(){
		try {
			prodFichaDao = new ProdFichaDao();
			custoProdutoFicha = prodFichaDao.custoFichaProduto(nomeFicha);
			calculaPrecoProduto();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void calculaPrecoProduto(){
		if(percent >= 0){
			custoTotalFT = ((custoProdutoFicha * (percent/100)) + custoProdutoFicha + (custoProdutoFicha * (valorImpo/100)));
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Lucro(%) deve ser maior ou igual a zero",
					"Lucro(%) deve ser maior ou igual a zero"));
		}
	}
	
	public void getValor(){
		if(tipoFiscal.equals("Presumido")){
			valorImpo = 8;
			getListFaixas();
		}
		else{
			getListFaixas();
			valorImpo = 6;
			switch(faixas.indexOf(faixa)){
				case 0:
					valorImpo = 6;
					break;
				case 1:
					valorImpo = 8.21;
					break;
				case 2:
					valorImpo = 10.26;
					break;
				case 3:
					valorImpo = 11.31;
					break;
			}
		}
		calculaPrecoProduto();
	}
	
	public void buttonSalvarCustoFicha(ActionEvent actionEvent) {
        try {
			fichaTecDao = new FichaTecDao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if(tipoFiscal.equals("Presumido")){
        	fichaTecDao.insereCustoFinal(custoTotalFT, nomeFicha, percent, 0);
        }
        else{
        	fichaTecDao.insereCustoFinal(custoTotalFT, nomeFicha, percent, 1);
        }
    }
}
