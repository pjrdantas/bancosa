package br.com.meuBanco.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tb_movimentacao database table.
 * 
 */
@Entity
@Table(name="tb_movimentacao")
public class TbMovimentacao {


	@Id
	@Column(name="id_movimentacao", unique=true, nullable=false)
	private int idMovimentacao;

	@Column(name="tb_movimentacao_credito", nullable=true, precision=10, scale=2)
	private BigDecimal tbMovimentacaoCredito;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="tb_movimentacao_data", nullable=true)
	private Date tbMovimentacaoData;

	@Column(name="tb_movimentacao_debito", nullable=true, precision=10, scale=2)
	private BigDecimal tbMovimentacaoDebito;
	
	@Column(name="tb_movimentacao_saldo", nullable=true, precision=10, scale=2)
	private BigDecimal tbMovimentacaoSaldo;

	//bi-directional many-to-one association to TbConta
	@ManyToOne
	@JoinColumn(name="tb_conta_id_conta", nullable=false)
	private TbConta tbConta;

	public TbMovimentacao() {
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

	public void setTbMovimentacaoCredito(BigDecimal tbMovimentacaoCredito) {
		this.tbMovimentacaoCredito = tbMovimentacaoCredito;
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

	public BigDecimal getTbMovimentacaoSaldo() {
		return tbMovimentacaoSaldo;
	}

	public void setTbMovimentacaoSaldo(BigDecimal tbMovimentacaoSaldo) {
		this.tbMovimentacaoSaldo = tbMovimentacaoSaldo;
	}

	public TbConta getTbConta() {
		return tbConta;
	}

	public void setTbConta(TbConta tbConta) {
		this.tbConta = tbConta;
	}


}