package br.com.meuBanco.dto;

public class BancoDTO {
	
	private int idBanco;	
	private int bancoCodigo;
	private String bancoNome;	
		
	public BancoDTO() {
		
	}

	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
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