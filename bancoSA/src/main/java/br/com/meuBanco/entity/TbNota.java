package br.com.meuBanco.entity;


import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tb_notas database table.
 * 
 */
@Entity
@Table(name="tb_notas")
public class TbNota {


	@Id
	@Column(name="id_notas", unique=true, nullable=false)
	private int idNotas;

	@Column(name="tb_notas_valor", nullable=false, precision=10, scale=2)
	private BigDecimal tbNotasValor;

	public TbNota() {
	}

	public int getIdNotas() {
		return this.idNotas;
	}

	public void setIdNotas(int idNotas) {
		this.idNotas = idNotas;
	}

	public BigDecimal getTbNotasValor() {
		return this.tbNotasValor;
	}

	public void setTbNotasValor(BigDecimal tbNotasValor) {
		this.tbNotasValor = tbNotasValor;
	}

}