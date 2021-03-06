package br.com.meuBanco.dto;

import java.math.BigDecimal;


public class MovimentacaoDTO {

	
	private int        idMovimentacao;	
	private BigDecimal movimentacaoCredito;		
	private String     movimentacaoData;	
	private BigDecimal movimentacaoDebito;
	private BigDecimal movimentacaoSaldo;
	private int        movimentacaoIdConta;
	private int        contaNumero;
	private String     contaDigito;
	
	
	public MovimentacaoDTO() {
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

	public BigDecimal getMovimentacaoSaldo() {
		return movimentacaoSaldo;
	}

	public void setMovimentacaoSaldo(BigDecimal movimentacaoSaldo) {
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