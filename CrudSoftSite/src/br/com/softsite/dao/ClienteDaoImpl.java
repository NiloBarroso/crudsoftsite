package br.com.softsite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import br.com.softsite.domain.Cliente;


/**
 * ClienteDaoImpl.java
 * 
 * Classe de acesso ao banco de dados para entidade Cliente.
 * @author Nilo Jorge Andrade Barroso.
 *
 */
public class ClienteDaoImpl implements ClienteDao {

	private Connection dbcon;
	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.dao.ClienteDao#criaTabelas()
	 */
	@Override
	public void criaTabelas() throws SQLException{
		String sql = "";
		dbcon = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "crudsoftsite.db"));
		Statement st = dbcon.createStatement();
		sql = "create table if not exists cliente (id_cliente integer not null primary key autoincrement, nome varchar ";
		sql += "not null, cpf integer not null unique, logradouro varchar not null, telefone integer not null, estado_civil "; 
		sql += "varchar not null check (estado_civil in ('S', 'C', 'D', 'V')), sexo varchar not null check (sexo in ('M', 'F')))";
		st.execute(sql);
		st.close();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.dao.ClienteDao#inclui(br.com.softsite.domain.Cliente)
	 */
	@Override
	public void inclui(Cliente cliente) throws SQLException{
		String sql = "";
		dbcon = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "crudsoftsite.db"));
		Statement st = dbcon.createStatement();
		sql = "insert into cliente (nome, cpf, logradouro, telefone, estado_civil, sexo)";
		sql += "values('"+cliente.getNome()+"',"+cliente.getCpf()+",'"+cliente.getLogradouro();
		sql += "',"+cliente.getTelefone()+",'"+cliente.getEstadoCivil()+"','"+cliente.getSexo()+"')";
		st.executeUpdate(sql);
		st.close();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.dao.ClienteDao#pesquisaPorCPF(br.com.softsite.domain.Cliente)
	 */
	@Override
	public Cliente pesquisaPorCPF(Cliente cliente) throws SQLException {
		Cliente entidadeRetorno = null;
		String sql = "";
		ResultSet resultSet = null;
		dbcon = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "crudsoftsite.db"));
		Statement st = dbcon.createStatement();
		sql = "select * from cliente where cpf = "+cliente.getCpf();
		resultSet = st.executeQuery(sql);
		
		
		if (resultSet != null) {
			while (resultSet.next()) {
				entidadeRetorno = new Cliente();
				entidadeRetorno.setIdCliente(resultSet.getInt(1));
				entidadeRetorno.setNome(resultSet.getString(2));
				entidadeRetorno.setCpf(resultSet.getLong(3));
				entidadeRetorno.setLogradouro(resultSet.getString(4));
				entidadeRetorno.setTelefone(resultSet.getLong(5));
				entidadeRetorno.setEstadoCivil(resultSet.getString(6));
				entidadeRetorno.setSexo(resultSet.getString(7));
			}
		}
		st.close();
		return entidadeRetorno;
	}

	
	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.dao.ClienteDao#altera(br.com.softsite.domain.Cliente)
	 */
	@Override
	public void altera(Cliente cliente) throws SQLException {
		String sql = "";
		dbcon = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "crudsoftsite.db"));
		Statement st = dbcon.createStatement();
		
		sql = "update cliente set nome = '"+cliente.getNome()+"', logradouro = '"+cliente.getLogradouro()+"'";
		sql += ", telefone = "+cliente.getTelefone()+", estado_civil = '"+cliente.getEstadoCivil()+"', sexo = '"+cliente.getSexo()+"'";
		sql += "where cpf = "+cliente.getCpf();
		st.executeUpdate(sql);
		st.close();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.dao.ClienteDao#listaTodos()
	 */
	@Override
	public List<Cliente> listaTodos() throws SQLException {
		Cliente clienteRetorno = null;
		List<Cliente> listaClienteRetorno = null;
		String sql = "";
		ResultSet resultSet = null;
		dbcon = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "crudsoftsite.db"));
		Statement st = dbcon.createStatement();
		sql = "select * from cliente";
		resultSet = st.executeQuery(sql);
		
		
		if (resultSet != null) {
			listaClienteRetorno = new ArrayList<Cliente>();
			while (resultSet.next()) {
				clienteRetorno = new Cliente();
				clienteRetorno.setIdCliente(resultSet.getInt(1));
				clienteRetorno.setNome(resultSet.getString(2));
				clienteRetorno.setCpf(resultSet.getLong(3));
				clienteRetorno.setLogradouro(resultSet.getString(4));
				clienteRetorno.setTelefone(resultSet.getLong(5));
				clienteRetorno.setEstadoCivil(resultSet.getString(6));
				clienteRetorno.setSexo(resultSet.getString(7));
				listaClienteRetorno.add(clienteRetorno);
			}
		}
		st.close();
		return listaClienteRetorno;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.softsite.dao.ClienteDao#exclui(br.com.softsite.domain.Cliente)
	 */
	@Override
	public void exclui(Cliente cliente) throws SQLException {
		String sql = "";
		dbcon = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "crudsoftsite.db"));
		Statement st = dbcon.createStatement();
		
		sql = "delete from cliente where id_cliente = "+cliente.getIdCliente();
		st.executeUpdate(sql);
		st.close();
	}
}
