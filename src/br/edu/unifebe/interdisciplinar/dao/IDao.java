package br.edu.unifebe.interdisciplinar.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<E> {
	boolean setIncluir(E e) throws SQLException;
	void setEditar(E e) throws SQLException;
	List<E> getListar(String e) throws SQLException;
	boolean setExcluir(E e) throws SQLException;	
}
