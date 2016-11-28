package br.com.softsite.core;

import java.sql.SQLException;
import java.util.List;

import br.com.softsite.dao.ClienteDaoImpl;
import br.com.softsite.domain.Cliente;

public class ClienteBusinessImpl implements ClienteBusiness {

	private ClienteDaoImpl clienteDaoImpl;
	
	public ClienteBusinessImpl() {
		clienteDaoImpl = new ClienteDaoImpl();
	}
	
	@Override
	public void criaTabelas() throws SQLException {
		clienteDaoImpl.criaTabelas();
	}

	@Override
	public void inclui(Cliente cliente) throws SQLException {
		clienteDaoImpl.inclui(cliente);
	}

	@Override
	public Cliente pesquisaPorCPF(Cliente cliente) throws SQLException {
		return clienteDaoImpl.pesquisaPorCPF(cliente);
	}

	@Override
	public void altera(Cliente cliente) throws SQLException {
		clienteDaoImpl.altera(cliente);
	}

	@Override
	public List<Cliente> listaTodos() throws SQLException {
		return clienteDaoImpl.listaTodos();
	}

	@Override
	public void exclui(Cliente cliente) throws SQLException {
		clienteDaoImpl.exclui(cliente);
	}
}
