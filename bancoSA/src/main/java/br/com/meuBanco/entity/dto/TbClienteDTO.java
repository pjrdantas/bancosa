package br.com.meuBanco.entity.dto;

public class TbClienteDTO {
	
	private int idCliente;	
	private String clienteNome;	
	private int clienteSenha;
	private int agenciaCodigo;
	private String agenciaDigito;
		
	public TbClienteDTO() {
		
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

	public int getClienteSenha() {
		return clienteSenha;
	}

	public void setClienteSenha(int clienteSenha) {
		this.clienteSenha = clienteSenha;
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