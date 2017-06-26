package br.edu.unifebe.interdisciplinar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PrecoFinal {
	private double percent;
	
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
}
