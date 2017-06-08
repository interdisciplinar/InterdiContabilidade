package br.edu.unifebe.interdisciplinar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class SelectTipo {

	private String console;

	@PostConstruct
	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}
}
