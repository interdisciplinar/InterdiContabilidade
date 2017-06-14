package br.edu.unifebe.interdisciplinar.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<E> {
	boolean setIncluir(E e) throws SQLException;
	void setEditar(E e) throws SQLException;
	List<E> getListar() throws SQLException;
	void setExcluir(E e) throws SQLException;
}
