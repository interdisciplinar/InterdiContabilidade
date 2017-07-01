package br.edu.unifebe.interdisciplinar;

import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.edu.unifebe.interdisciplinar.dao.FichaTecDao;
import br.edu.unifebe.interdisciplinar.dao.ProdFichaDao;
import br.edu.unifebe.interdisciplinar.model.ValidaErros;

@ManagedBean
@SessionScoped
public class PrecoFinal {
	private double percent = 0;
	private double custoProdutoFicha = 0;
	private double custoTotalFT = 0;
	private String nomeFicha;
	private ProdFichaDao prodFichaDao;
	private FichaTecDao fichaTecDao;
	private String tipoFiscal;
	private double valorImpo = 0;
	
	
	@PostConstruct
	public void init(){
		percent = 0;
		valorImpo = 0;
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
			custoTotalFT = ((custoProdutoFicha * (percent/100)) + custoProdutoFicha);
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Lucro(%) deve ser maior ou igual a zero",
					"Lucro(%) deve ser maior ou igual a zero"));
		}
	}
	
	public void getValor(){
		if(tipoFiscal.equals("Lucro Presumido")){
			valorImpo = 8;
		}
		else{
			valorImpo = 0;
		}
	}
	
	public void buttonSalvarCustoFicha(ActionEvent actionEvent) {
        try {
			fichaTecDao = new FichaTecDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(tipoFiscal.equals("Simples Nacional")){
        	fichaTecDao.insereCustoFinal(custoTotalFT, nomeFicha, percent, 0);
        }
        else{
        	fichaTecDao.insereCustoFinal(custoTotalFT, nomeFicha, percent, 1);
        }
    }
}
