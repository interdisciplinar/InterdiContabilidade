package br.edu.unifebe.interdisciplinar.model;

public class CadImposto {
	private int idImposto;
	private String nomeImposto;
	private int pICMS;
	private double pMVA;
	private int pICMSST;
	private int pIPI;
	
	public int getIdImposto() {
		return idImposto;
	}
	public void setIdImposto(int idImposto) {
		this.idImposto = idImposto;
	}
	public String getNomeImposto() {
		return nomeImposto;
	}
	public void setNomeImposto(String nomeImposto) {
		this.nomeImposto = nomeImposto;
	}
	public int getpICMS() {
		return pICMS;
	}
	public void setpICMS(int pICMS) {
		this.pICMS = pICMS;
	}
	public double getpMVA() {
		return pMVA;
	}
	public void setpMVA(double pMVA) {
		this.pMVA = pMVA;
	}
	public int getpICMSST() {
		return pICMSST;
	}
	public void setpICMSST(int pICMSST) {
		this.pICMSST = pICMSST;
	}
	public int getpIPI() {
		return pIPI;
	}
	public void setpIPI(int pIPI) {
		this.pIPI = pIPI;
	}
	
}
