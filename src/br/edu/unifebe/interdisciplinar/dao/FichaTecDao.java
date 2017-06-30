package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifebe.interdisciplinar.Login;
import br.edu.unifebe.interdisciplinar.Produto;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadFichaTec;
import br.edu.unifebe.interdisciplinar.model.CadProdutoFicha;

public class FichaTecDao implements IDao<CadFichaTec>{
	
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	public FichaTecDao() throws SQLException {
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}
	
	@Override
	public boolean setIncluir(CadFichaTec e) throws SQLException {
		String sql = "CALL IncluirFicha (?,?)";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeFicha());
			prmt.setInt(2, Login.userId); 						
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
						
			e1.printStackTrace();
			System.out.println(e1.getErrorCode());
			System.out.println(e1.getMessage());
			return false;
		}
	}

	@Override
	public void setEditar(CadFichaTec e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CadFichaTec> getListar(String e) throws SQLException {
		String sql = "CALL ListaFichaTecnica ()";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		ResultSet rs;
		CadFichaTec ft;
		List<CadFichaTec> listNomeFicha = new ArrayList<CadFichaTec>();
		rs = prmt.executeQuery();
			
		while(rs.next()){
			ft = new CadFichaTec();
			ft.setNomeFicha(rs.getString("ft_nome"));
			listNomeFicha.add(ft);
		}
		
		return listNomeFicha;
	}

	@Override
	public boolean setExcluir(CadFichaTec e) throws SQLException {
		return false;
		
	}
	
	public boolean verificaNomeFicha(String nomeFicha) throws SQLException{
		String sql = "CALL VerificaFichaTecnica (?)";
		PreparedStatement  prmt = conexao.prepareStatement(sql);
		prmt.setString(1, nomeFicha);
		ResultSet rs;
		rs = prmt.executeQuery();
		while(rs.next()){
			return true;
		}
		
		return false;
	}
	
	public void insereCustoFinal(double custoFinal, String nomeFicha, double percent, int tipoFiscal){
		String sql = "UPDATE `fichatecnica` SET `ft_total_custo`= ?, `ft_percent`= ?, `ft_enquad_fiscal`= ?"
				+ " WHERE `ft_nome` = '"+nomeFicha+"'";
		System.out.println(nomeFicha);
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setDouble(1, custoFinal);
			prmt.setDouble(2, percent);
			prmt.setInt(3, tipoFiscal);
			prmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("ERRO PRAKCT");
			e1.printStackTrace();
		}
	}
	
	public void salvaUsuIdAlteracao(String nomeFicha){
		String sql = "UPDATE `fichatecnica` SET `ft_usu_id_alteracao`= ? WHERE `ft_nome`= '"+ nomeFicha +"'";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setInt(1, Login.userId);
			prmt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
