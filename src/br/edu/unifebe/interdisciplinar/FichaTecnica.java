package br.edu.unifebe.interdisciplinar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.dao.ProdutosDao;
import br.edu.unifebe.interdisciplinar.model.CadProdutos;

@ManagedBean
public class FichaTecnica {
	private Connection conn;
	private ConnectionDB connDB;
	private String nomeFichaTecnica;
	private String idProduto;
	private String nomeProduto;
	private List<String> idProdutos;
	private List<String> nomeProdutos;
	
	public FichaTecnica() throws SQLException {
		System.out.println("mas que merda é essa");
		connDB = new ConnectionDB();
		conn = connDB.getConn();
		String sql = "SELECT * FROM produtos";
		PreparedStatement prmt = conn.prepareStatement(sql);
		ResultSet rs = prmt.executeQuery();
		idProdutos = new ArrayList<String>();
		nomeProdutos = new ArrayList<String>();
		while(rs.next()){
			idProdutos.add(String.valueOf(rs.getInt("prod_id")));
			nomeProdutos.add(rs.getString("prod_nome"));
		}
		rs.close();
	}
	
	@PostConstruct
	public List<String> getIdProdutos() {
		return idProdutos;
	}

	
	public void setIdProdutos(List<String> idProdutos) {
		this.idProdutos = idProdutos;
	}

	public List<String> getNomeProdutos() {
		return nomeProdutos;
	}

	public void setNomeProdutos(List<String> nomeProdutos) {
		this.nomeProdutos = nomeProdutos;
	}

	public String getNomeFichaTecnica() {
		return nomeFichaTecnica;
	}

	public void setNomeFichaTecnica(String nomeFichaTecnica) {
		this.nomeFichaTecnica = nomeFichaTecnica;
	}
	
	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public void buttonAction(ActionEvent actionEvent) throws SQLException {
        System.out.println("teste");
    }
}
