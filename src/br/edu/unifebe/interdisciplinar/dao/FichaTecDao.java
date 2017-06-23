package br.edu.unifebe.interdisciplinar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.unifebe.interdisciplinar.Produto;
import br.edu.unifebe.interdisciplinar.conexao.ConnectionDB;
import br.edu.unifebe.interdisciplinar.model.CadFichaTec;

public class FichaTecDao implements IDao<CadFichaTec>{
	
	private Connection conexao = null;
	private ConnectionDB conexaoDB;
	
	public FichaTecDao() throws SQLException {
		conexaoDB = new ConnectionDB();
		conexao = conexaoDB.getConn();
	}
	
	@Override
	public boolean setIncluir(CadFichaTec e) throws SQLException {
		String sql = "INSERT INTO `fichatecnica`(`ft_nome`, `ft_dt_criacao`, `ft_total_custo`, `ft_usu_id_criacao`) "
				+ "VALUES (?,CURDATE(),?,?)";
		PreparedStatement prmt;
		try {
			prmt = conexao.prepareStatement(sql);
			prmt.setString(1, e.getNomeFicha());
			prmt.setDouble(2, e.getTotalCusto());
			prmt.setInt(3, 1);
			prmt.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	@Override
	public void setEditar(CadFichaTec e) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CadFichaTec> getListar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setExcluir(CadFichaTec e) throws SQLException {
		return false;
		
	}

}
