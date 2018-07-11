package br.com.meuBanco.entity.dto;

public class TbBancoDTO {
	
	private int idBanco;	
	private int bancoCodigo;
	private String bancoNome;	
		
	public TbBancoDTO() {
		
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