package br.com.meuBanco.entity.dto;

import java.math.BigDecimal;
import java.util.Date;


public class TbMovimentacaoDTO {

	
	private int idMovimentacao;	
	private BigDecimal tbMovimentacaoCredito;		
	private Date tbMovimentacaoData;	
	private BigDecimal tbMovimentacaoDebito;
	private int contaNumero;
	private String contaDigito;
	
	
	public TbMovimentacaoDTO() {
		
	}


	public int getIdMovimentacao() {
		return idMovimentacao;
	}
	public void setIdMovimentacao(int idMovimentacao) {
		this.idMovimentacao = idMovimentacao;
	}
	public BigDecimal getTbMovimentacaoCredito() {
		return tbMovimentacaoCredito;
	}
	public void setTbMovimentacaoCredito(BigDecimal tbMovimentaCredito) {
		this.tbMovimentacaoCredito = tbMovimentaCredito;
	}
	public Date getTbMovimentacaoData() {
		return tbMovimentacaoData;
	}
	public void setTbMovimentacaoData(Date tbMovimentacaoData) {
		this.tbMovimentacaoData = tbMovimentacaoData;
	}
	public BigDecimal getTbMovimentacaoDebito() {
		return tbMovimentacaoDebito;
	}
	public void setTbMovimentacaoDebito(BigDecimal tbMovimentacaoDebito) {
		this.tbMovimentacaoDebito = tbMovimentacaoDebito;
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