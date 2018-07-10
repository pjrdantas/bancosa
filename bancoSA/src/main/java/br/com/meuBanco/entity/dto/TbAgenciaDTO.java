package br.com.meuBanco.entity.dto;

public class TbAgenciaDTO {

	
	private int idAgencia;	
	private int agenciaCodigo;	
	private String agenciaDigito;
	private int bancoCodigo;
	private String bancoNome;
	
	
	public TbAgenciaDTO() {
		
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