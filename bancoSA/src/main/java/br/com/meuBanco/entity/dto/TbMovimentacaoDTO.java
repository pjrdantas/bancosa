package br.com.meuBanco.entity.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.meuBanco.utils.LocalDateTimeDeserializer;
import br.com.meuBanco.utils.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TbMovimentacaoDTO {

	
	private int idMovimentacao;	
	private BigDecimal tbMovimentaCredito;	
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDate tbMovimentacaoData;
	
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


	public BigDecimal getTbMovimentaCredito() {
		return tbMovimentaCredito;
	}


	public void setTbMovimentaCredito(BigDecimal tbMovimentaCredito) {
		this.tbMovimentaCredito = tbMovimentaCredito;
	}


	public LocalDate getTbMovimentacaoData() {
		return tbMovimentacaoData;
	}


	public void setTbMovimentacaoData(LocalDate tbMovimentacaoData) {
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