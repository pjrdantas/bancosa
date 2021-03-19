package br.com.meuBanco.dto;

public class ExtratoDTO {

	
	private int    idMovimentacao;	
	private String movimentacaoCredito;		
	private String movimentacaoData;	
	private String movimentacaoDebito;
	private String movimentacaoSaldo;
	private int    movimentacaoIdConta;
	private int    contaNumero;
	private String contaDigito;
	
	
	public ExtratoDTO() {
	}


	public int getIdMovimentacao() {
		return idMovimentacao;
	}


	public void setIdMovimentacao(int idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}


	public String getMovimentacaoCredito() {
		return movimentacaoCredito;
	}


	public void setMovimentacaoCredito(String movimentacaoCredito) {
		this.movimentacaoCredito = movimentacaoCredito;
	}


	public String getMovimentacaoData() {
		return movimentacaoData;
	}


	public void setMovimentacaoData(String movimentacaoData) {
		this.movimentacaoData = movimentacaoData;
	}


	public String getMovimentacaoDebito() {
		return movimentacaoDebito;
	}


	public void setMovimentacaoDebito(String movimentacaoDebito) {
		this.movimentacaoDebito = movimentacaoDebito;
	}


	public String getMovimentacaoSaldo() {
		return movimentacaoSaldo;
	}


	public void setMovimentacaoSaldo(String movimentacaoSaldo) {
		this.movimentacaoSaldo = movimentacaoSaldo;
	}


	public int getMovimentacaoIdConta() {
		return movimentacaoIdConta;
	}


	public void setMovimentacaoIdConta(int movimentacaoIdConta) {
		this.movimentacaoIdConta = movimentacaoIdConta;
	}


	public int getContaNumero() {
		return contaNumero;
	}


	public void setContaNumero(int contaNumero) {
		this.contaNumero = contaNumero;
	}


	public String getContaDigito() {
		return contaDigito;
	}


	public void setContaDigito(String contaDigito) {
		this.contaDigito = contaDigito;
	}



}