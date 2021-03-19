package br.com.meuBanco.dto;

public class ClienteDTO {
	
	private int idCliente;	
	private String clienteNome;	
	private int clienteIAgencia;
	private int agenciaCodigo;
	private String agenciaDigito;
		
	public ClienteDTO() {
		
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getClienteNome() {
		return clienteNome;
	}

	public void setClienteNome(String clienteNome) {
		this.clienteNome = clienteNome;
	}

	public int getClienteIAgencia() {
		return clienteIAgencia;
	}

	public void setClienteIAgencia(int clienteIAgencia) {
		this.clienteIAgencia = clienteIAgencia;
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

}