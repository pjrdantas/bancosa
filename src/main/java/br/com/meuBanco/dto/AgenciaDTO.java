package br.com.meuBanco.dto;

public class AgenciaDTO {

	
	private int idAgencia;	
	private int agenciaCodigo;	
	private String agenciaDigito;
	private int idbancoAgencia;
	private int bancoCodigo;
	private String bancoNome;
	
	
	public AgenciaDTO() {
		
	}


	public int getIdAgencia() {
		return idAgencia;
	}


	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
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


	public int getIdbancoAgencia() {
		return idbancoAgencia;
	}


	public void setIdbancoAgencia(int idbancoAgencia) {
		this.idbancoAgencia = idbancoAgencia;
	}


	public int getBancoCodigo() {
		return bancoCodigo;
	}


	public void setBancoCodigo(int bancoCodigo) {
		this.bancoCodigo = bancoCodigo;
	}


	public String getBancoNome() {
		return bancoNome;
	}


	public void setBancoNome(String bancoNome) {
		this.bancoNome = bancoNome;
	}

}