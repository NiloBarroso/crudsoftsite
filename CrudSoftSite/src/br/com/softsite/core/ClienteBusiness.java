package br.com.softsite.core;

import java.sql.SQLException;
import java.util.List;

import br.com.softsite.domain.Cliente;

public interface ClienteBusiness {

	void criaTabelas() throws SQLException;
	void inclui(Cliente cliente) throws SQLException;
	Cliente pesquisaPorCPF(Cliente cliente) throws SQLException;
	void altera(Cliente cliente) throws SQLException;
	List<Cliente> listaTodos() throws SQLException;
	void exclui(Cliente cliente) throws SQLException;
}
