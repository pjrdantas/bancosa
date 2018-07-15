package br.com.meuBanco.entity.dto;

import java.math.BigDecimal;


public class TbMovimentacaoDTO {

	
	private int        idMovimentacao;	
	private BigDecimal movimentacaoCredito;		
	private String     movimentacaoData;	
	private BigDecimal movimentacaoDebito;
	
	private int    movimentacaoIdConta;
	private int    contaNumero;
	private String contaDigito;
	
	
	public TbMovimentacaoDTO() {
	}


	public int getIdMovimentacao() {
		return idMovimentacao;
	}


	public void setIdMovimentacao(int idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}


	public BigDecimal getMovimentacaoCredito() {
		return movimentacaoCredito;
	}


	public void setMovimentacaoCredito(BigDecimal movimentacaoCredito) {
		this.movimentacaoCredito = movimentacaoCredito;
	}


	public String getMovimentacaoData() {
		return movimentacaoData;
	}


	public void setMovimentacaoData(String movimentacaoData) {
		this.movimentacaoData = movimentacaoData;
	}


	public BigDecimal getMovimentacaoDebito() {
		return movimentacaoDebito;
	}


	public void setMovimentacaoDebito(BigDecimal movimentacaoDebito) {
		this.movimentacaoDebito = movimentacaoDebito;
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