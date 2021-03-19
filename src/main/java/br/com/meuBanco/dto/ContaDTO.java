package br.com.meuBanco.dto;

public class ContaDTO {

	
	private int idConta;	
	private int contaDigito;	
	private int contaNumero;
	private int contaIdAgencia;
	private int agenciaCodigo;
	private String agenciaDigito;
	private int contaIdCliente;
	private String clienteNome;
	private int clienteSenha;
	
	public ContaDTO() {		
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}

	public int getContaDigito() {
		return contaDigito;
	}

	public void setContaDigito(int contaDigito) {
		this.contaDigito = contaDigito;
	}

	public int getContaNumero() {
		return contaNumero;
	}

	public void setContaNumero(int contaNumero) {
		this.contaNumero = contaNumero;
	}


	public int getContaIdAgencia() {
		return contaIdAgencia;
	}

	public void setContaIdAgencia(int contaIdAgencia) {
		this.contaIdAgencia = contaIdAgencia;
	}

	public int getAgenciaCodigo() {
		return agenciaCodigo;
	}

	public void setAgenciaCodigo(int agenciaCodigo) {
		this.agenciaCodigo = agenciaCodigo;
	}

	public String getAgenciaDigito() {
		return agenciaDigito;
	}

	public void setAgenciaDigito(String agenciaDigito) {
		this.agenciaDigito = agenciaDigito;
	}

	public int getContaIdCliente() {
		return contaIdCliente;
	}

	public void setContaIdCliente(int contaIdCliente) {
		this.contaIdCliente = contaIdCliente;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public int getClienteSenha() {
		return clienteSenha;
	}

	public void setClienteSenha(int clienteSenha) {
		this.clienteSenha = clienteSenha;
	}
	
}