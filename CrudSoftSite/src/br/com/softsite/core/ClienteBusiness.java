package br.com.softsite.core;

import java.sql.SQLException;
import java.util.List;

import br.com.softsite.domain.Cliente;

/**
 * ClienteBusiness.java
 * 
 * Interface de negócio para entidade Cliente.
 * @author Nilo Jorge Andrade Barroso.
 *
 */
public interface ClienteBusiness {

	/**
	 * Responsável por criar as tabelas de banco de dados.
	 * @throws SQLException
	 */
	void criaTabelas() throws SQLException;
	
	/**
	 * Responsável por incluir um cliente na base de dados.
	 * @param cliente
	 * @throws SQLException
	 */
	void inclui(Cliente cliente) throws SQLException;
	
	/**
	 * Responsável por pesquisar um cliente, dado o CPF como paramêtro.
	 * @param cliente
	 * @return
	 * @throws SQLException
	 */
	Cliente pesquisaPorCPF(Cliente cliente) throws SQLException;
	
	/**
	 * Responsável por alterar os dados de um cliente na base de dados.
	 * @param cliente
	 * @throws SQLException
	 */
	void altera(Cliente cliente) throws SQLException;
	
	/**
	 * Responsável por listar todos os clientes da base de dados.
	 * @return
	 * @throws SQLException
	 */
	List<Cliente> listaTodos() throws SQLException;
	
	/**
	 * Responsável por excluir um cliente da base de dados.
	 * @param cliente
	 * @throws SQLException
	 */
	void exclui(Cliente cliente) throws SQLException;
}
