package br.com.meuBanco.entity.dto;

public class TbContaDTO {

	
	private int idConta;	
	private int contaDigito;	
	private int contaNumero;
	private int contaTipo;
	private int contaIdAgencia;
	private int agenciaCodigo;
	private String agenciaDigito;
	private int contaIdCliente;
	private String clienteNome;
	private int clienteSenha;
	
	public TbContaDTO() {		
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

	public int getContaTipo() {
		return contaTipo;
	}

	public void setContaTipo(int contaTipo) {
		this.contaTipo = contaTipo;
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