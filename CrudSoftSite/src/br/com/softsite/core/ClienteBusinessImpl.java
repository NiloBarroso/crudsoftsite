package br.com.softsite.core;

import java.sql.SQLException;
import java.util.List;

import br.com.softsite.dao.ClienteDaoImpl;
import br.com.softsite.domain.Cliente;


/**
 * ClienteBusinessImpl.java
 * 
 * Classe de negócio para entidade Cliente.
 * @author Nilo Jorge Andrade Barroso.
 *
 */
public class ClienteBusinessImpl implements ClienteBusiness {

	private ClienteDaoImpl clienteDaoImpl;
	
	public ClienteBusinessImpl() {
		clienteDaoImpl = new ClienteDaoImpl();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.core.ClienteBusiness#criaTabelas()
	 */
	@Override
	public void criaTabelas() throws SQLException {
		clienteDaoImpl.criaTabelas();
	}

	
	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.core.ClienteBusiness#inclui(br.com.softsite.domain.Cliente)
	 */
	@Override
	public void inclui(Cliente cliente) throws SQLException {
		clienteDaoImpl.inclui(cliente);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.core.ClienteBusiness#pesquisaPorCPF(br.com.softsite.domain.Cliente)
	 */
	@Override
	public Cliente pesquisaPorCPF(Cliente cliente) throws SQLException {
		return clienteDaoImpl.pesquisaPorCPF(cliente);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.core.ClienteBusiness#altera(br.com.softsite.domain.Cliente)
	 */
	@Override
	public void altera(Cliente cliente) throws SQLException {
		clienteDaoImpl.altera(cliente);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.core.ClienteBusiness#listaTodos()
	 */
	@Override
	public List<Cliente> listaTodos() throws SQLException {
		return clienteDaoImpl.listaTodos();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.core.ClienteBusiness#exclui(br.com.softsite.domain.Cliente)
	 */
	@Override
	public void exclui(Cliente cliente) throws SQLException {
		clienteDaoImpl.exclui(cliente);
	}
}
