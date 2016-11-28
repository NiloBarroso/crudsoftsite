package br.com.softsite.controller;

import java.sql.SQLException;
import java.util.List;

import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.ui.Edit;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.Spacer;
import totalcross.ui.Toast;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import br.com.softsite.core.ClienteBusinessImpl;
import br.com.softsite.domain.Cliente;


/**
 * ClienteController.java
 * 
 * Classe responsável por fazer a camada de visao e recepção das variáveis inseridas pelo usuário, no cadastro de Clientes.
 * @author Nilo Jorge Andrade Barroso.
 *
 */
public class ClienteController extends MainWindow{

	private ClienteBusinessImpl clienteBusinessImpl;
	private Cliente cliente;
	private Cliente clientePesquisa;
	private List<Cliente> listaCliente;
	
	String [] opcoesEstadoCivil = {"Solteiro","Casado","Divorciado","Viúvo"};
	
	private String mensagemValidacao;
	
	private Button btCadastrar;
	private Button btPesquisar;
	private Button btIniciaPesquisar;
	private Button btConfirmarCadastro;
	private Button btConfirmarAlteracao;
	private Button btInicio;
	private Button btListar;
	private Button btExcluir;
	private Edit edNome;
	private Edit edCPF;
	private Edit edLogradouro;
	private Edit edTelefone;
	private ComboBox cbEstadoCivil;
	private RadioGroupController rgSexo;
	
	public ClienteController() {
		super("Cadastro de Clientes", totalcross.ui.Window.ROUND_BORDER);
		setUIStyle(Settings.Android);
		Settings.uiAdjustmentsBasedOnFontHeight = true;
		setBackColor(0xDCDCDC);
		instanciaObjetos();
	}
	
	private void instanciaObjetos() {
		clienteBusinessImpl = new ClienteBusinessImpl();
		cliente = new Cliente();
		clientePesquisa = new Cliente();
	}
	
	public void initUI() {
		Spacer sp = new Spacer(0,0);
	    add(sp, CENTER, TOP, PARENTSIZE+1, PREFERRED);
		add(btCadastrar = new Button("Cadastrar"), BEFORE, SAME, PARENTSIZE+40, PREFERRED,sp);
		add(btPesquisar  = new Button("Pesquisar"),  AFTER,  SAME, PARENTSIZE+40, PREFERRED,sp);
		
		try {
			clienteBusinessImpl.criaTabelas();
		} catch (SQLException e) {
			MessageBox.showException(e, true);
			exit(0);
		}
		
		Toast.posY = CENTER;
	}
	
	public void onEvent(Event ev) {
		try {
			switch (ev.type) {
				case ControlEvent.PRESSED:
	               if (ev.target == btCadastrar) {
	            	   exibeTelaCadastro();
	               } else if (ev.target == btPesquisar) {
	            	   exibeTelaPesquisa();
	               } else if (ev.target == btInicio) {
	            	   exibeTelaInicio();
	               } else if (ev.target == btConfirmarCadastro) {
	            	   inclui();
	               } else if (ev.target == btIniciaPesquisar) {
	            	   pesquisaPorCPF();
	               } else if (ev.target == btConfirmarAlteracao) {
	            	   alteraRegistro();
	               } else if (ev.target == btListar) {
	            	   exibeTelaComLista();
	               } else if (ev.target == btExcluir) {
	            	   excluiRegistro();
	               }
	               break;
			}
		} catch (Exception e) {
			MessageBox.showException(e,true);
		}
	}
	
	private void exibeTelaInicio() {
		removeAll();
		
		Spacer sp = new Spacer(0,0);
	    add(sp, CENTER, TOP, PARENTSIZE+1, PREFERRED);
		add(btCadastrar = new Button("Cadastrar"), BEFORE, SAME, PARENTSIZE+40, PREFERRED,sp);
		add(btPesquisar  = new Button("Pesquisar"),  AFTER,  SAME, PARENTSIZE+40, PREFERRED,sp);
		
		cliente = new Cliente();
	}
	
	private void exibeTelaCadastro() {
		removeAll();
		
		add(new Label("Nome: "), LEFT,AFTER+1);
	    add(edNome = new Edit(),LEFT,AFTER);
	    
	    add(new Label("CPF: "), LEFT,AFTER+100);
	    add(edCPF = new Edit(),LEFT,AFTER);
	    edCPF.setKeyboard(Edit.KBD_NUMERIC);
	    
		add(new Label("Logradouro: "), LEFT,AFTER+100);
	    add(edLogradouro = new Edit(),LEFT,AFTER);
	    
		add(new Label("Telefone: "), LEFT,AFTER+100);
	    add(edTelefone = new Edit(),LEFT,AFTER);
	    edTelefone.setKeyboard(Edit.KBD_NUMERIC);
	    
	    add(new Label("Estado Civil: "), LEFT,AFTER+100);
	    cbEstadoCivil = new ComboBox(opcoesEstadoCivil);
	    add(cbEstadoCivil,LEFT,AFTER);
	    
	    add(new Label("Sexo: "), LEFT,AFTER+100);
	    rgSexo = new RadioGroupController();
	    add(new Radio("Masculino", rgSexo),LEFT,AFTER);
	    add(new Radio("Feminino", rgSexo),AFTER+2, SAME);
	    
	    Spacer sp = new Spacer(0,0);
	    add(sp, CENTER,BOTTOM-300, PARENTSIZE+10, PREFERRED);
	    add(btConfirmarCadastro  = new Button("Confirmar"),  BEFORE,  SAME, PARENTSIZE+40, PREFERRED,sp);
	    add(btInicio  = new Button("Início"),  AFTER,  SAME, PARENTSIZE+40, PREFERRED,sp);
	    
	    cliente = new Cliente();
	}
	
	private void exibeTelaPesquisa() {
		removeAll();
		
	    add(new Label("CPF: "), LEFT,AFTER+1);
	    add(edCPF = new Edit(),LEFT,AFTER);
	    edCPF.setKeyboard(Edit.KBD_NUMERIC);
	    
	    Spacer sp = new Spacer(0,0);
	    add(sp, CENTER,BOTTOM-300, PARENTSIZE+10, PREFERRED);
	    add(btIniciaPesquisar  = new Button("Pesquisar"),  BEFORE,  SAME, PARENTSIZE+40, PREFERRED,sp);
	    add(btInicio  = new Button("Início"),  AFTER,  SAME, PARENTSIZE+40, PREFERRED,sp);
	    
	    add(btListar = new Button("Listar Todos"), CENTER,AFTER+1,PARENTSIZE+40,PREFERRED);
	    
	    clientePesquisa = new Cliente();
	    cliente = new Cliente();
	}
	
	private void inclui() {
		try {
			if (validaCamposObrigatorios()) {
				populaEntidadeCliente();
				clienteBusinessImpl.inclui(cliente);
				Toast.show("Cadastro realizado com sucesso!",2000);
				clear();
				cliente = new Cliente();
			} else {
				Toast.show(mensagemValidacao,2000);
			}
		} catch (SQLException e) {
			Toast.show(e.getMessage(),2000);
			e.printStackTrace();
		}
	}
	
	private boolean validaCamposObrigatorios() {
		mensagemValidacao = "";
		
		if (edNome.getLength() == 0) {  
			mensagemValidacao = "Nome é obrigátorio.";
		}
		if (edCPF.getLength() == 0) {  
			mensagemValidacao += "\nCPF é obrigátorio.";
		}
		if (edLogradouro.getLength() == 0) {  
			mensagemValidacao += "\nLogradouro é obrigátorio.";
		}
		if (edTelefone.getLength() == 0) {  
			mensagemValidacao += "\nTelefone é obrigátorio.";
		}
		if (cbEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("")) {  
			mensagemValidacao += "\nEstado Civil é obrigátorio.";
		}
		if (rgSexo.getSelectedIndex() == -1) {
			mensagemValidacao += "\nSexo é obrigátorio.";
		}
		
		if (mensagemValidacao != "") {
			return false;
		}
		return true;
	}
	
	private void populaEntidadeCliente() {
		cliente.setNome(edNome.getText());
		cliente.setCpf(Long.parseLong(edCPF.getText()));
		cliente.setLogradouro(edLogradouro.getText());
		cliente.setTelefone(Long.parseLong(edTelefone.getText()));
		
		if (cbEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("solteiro")) {
			cliente.setEstadoCivil("S");
		} else if (cbEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("casado")) {
			cliente.setEstadoCivil("C");
		} else if (cbEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("divorciado")) {
			cliente.setEstadoCivil("D");
		} else if (cbEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("viúvo")) {
			cliente.setEstadoCivil("V");
		}
		
		if (rgSexo.getSelectedItem().getText().equalsIgnoreCase("masculino")) {
			cliente.setSexo("M");
		} else if (rgSexo.getSelectedItem().getText().equalsIgnoreCase("feminino")) {
			cliente.setSexo("F");
		}
	}
	
	private void pesquisaPorCPF() {
		try {
			if (validaCPFPesquisa()) {
				clientePesquisa.setCpf(Long.parseLong(edCPF.getText()));
				cliente = clienteBusinessImpl.pesquisaPorCPF(clientePesquisa);
				if (cliente != null) {
					exibeTelaParaAlteracao();
				} else {
					Toast.show("Cliente não encontrado!",2000);
				}
			} else {
				Toast.show(mensagemValidacao,2000);
			}
		} catch (SQLException e) {
			Toast.show(e.getMessage(),2000);
			e.printStackTrace();
		}
	}
	
	private boolean validaCPFPesquisa() {
		mensagemValidacao = "";
		
		if (edCPF.getLength() == 0) {  
			mensagemValidacao = "CPF é obrigátorio.";
		}
		if (mensagemValidacao != "") {
			return false;
		}
		return true;
	}
	
	private void exibeTelaParaAlteracao() {
		removeAll();
		
		add(new Label("Nome: "), LEFT,BEFORE-2120);
	    add(edNome = new Edit(),LEFT,AFTER);
	    edNome.setText(cliente.getNome());
	    
	    add(new Label("CPF: "), LEFT,AFTER+100);
	    add(edCPF = new Edit(),LEFT,AFTER);
	    edCPF.setText(preencheZerosAEsquerda(cliente.getCpf().toString(), 11));
	    edCPF.setEnabled(false);
	    
		add(new Label("Logradouro: "), LEFT,AFTER+100);
	    add(edLogradouro = new Edit(),LEFT,AFTER);
	    edLogradouro.setText(cliente.getLogradouro());
	    
		add(new Label("Telefone: "), LEFT,AFTER+100);
	    add(edTelefone = new Edit(),LEFT,AFTER);
	    edTelefone.setKeyboard(Edit.KBD_NUMERIC);
	    edTelefone.setText(cliente.getTelefone().toString());
	    
	    add(new Label("Estado Civil: "), LEFT,AFTER+100);
	    cbEstadoCivil = new ComboBox(opcoesEstadoCivil);
	    add(cbEstadoCivil,LEFT,AFTER);
	    preencheCampoEstadoCivil();
	    
	    add(new Label("Sexo: "), LEFT,AFTER+100);
	    rgSexo = new RadioGroupController();
	    add(new Radio("Masculino", rgSexo),LEFT,AFTER);
	    add(new Radio("Feminino", rgSexo),AFTER+2, SAME);
	    preencheCampoSexo();
	    
	    Spacer sp = new Spacer(0,0);
	    add(sp, CENTER,BOTTOM-300, PARENTSIZE+10, PREFERRED);
	    add(btConfirmarAlteracao  = new Button("Alterar"),  BEFORE,  SAME, PARENTSIZE+40, PREFERRED,sp);
	    add(btInicio  = new Button("Início"),  AFTER,  SAME, PARENTSIZE+40, PREFERRED,sp);
	    
	    add(btExcluir = new Button("Excluir Cliente"), CENTER,AFTER+1,PARENTSIZE+40,PREFERRED);
	}
	
	private void preencheCampoEstadoCivil() {
		if (cliente.getEstadoCivil() != null) {
			if (cliente.getEstadoCivil().equalsIgnoreCase("S")) {
				cbEstadoCivil.setSelectedItem("Solteiro");
			} else if (cliente.getEstadoCivil().equalsIgnoreCase("C")) {
				cbEstadoCivil.setSelectedItem("Casado");
			} else if (cliente.getEstadoCivil().equalsIgnoreCase("D")) {
				cbEstadoCivil.setSelectedItem("Divorciado");
			} else if (cliente.getEstadoCivil().equalsIgnoreCase("V")) {
				cbEstadoCivil.setSelectedItem("Viúvo");
			}
		}
	}
	
	private void preencheCampoSexo() {
		if (cliente.getSexo() != null) {
			if (cliente.getSexo().equalsIgnoreCase("M")) {
				rgSexo.setSelectedItem("Masculino");
			} else if (cliente.getSexo().equalsIgnoreCase("F")) {
				rgSexo.setSelectedItem("Feminino");
			}
		}
	}
	
	private String preencheZerosAEsquerda(String valor, Integer tamanhoRetorno) {
		while (valor.length() < tamanhoRetorno) {
    		valor = "0" + valor;
    	}
    	return valor;
	}
	
	private void alteraRegistro() {
		try {
			if (validaCamposObrigatorios()) {
				populaEntidadeCliente();
				clienteBusinessImpl.altera(cliente);
				Toast.show("Alteração realizada com sucesso!",2000);
			} else {
				Toast.show(mensagemValidacao,2000);
			}
		} catch (SQLException e) {
			Toast.show(e.getMessage(),2000);
			e.printStackTrace();
		}
	}
	
	private void exibeTelaComLista() {
		try {
			listaCliente = clienteBusinessImpl.listaTodos();
			if (listaCliente != null) {
				
			}
		} catch (SQLException e) {
			Toast.show(e.getMessage(),2000);
			e.printStackTrace();
		}
	}
	
	private void excluiRegistro() {
		try {
			clienteBusinessImpl.exclui(cliente);
			clear();
			cliente = new Cliente();
			Toast.show("Exclusão realizada com sucesso!",2000);
		} catch (SQLException e) {
			Toast.show(e.getMessage(),2000);
			e.printStackTrace();
		}
	}
}
