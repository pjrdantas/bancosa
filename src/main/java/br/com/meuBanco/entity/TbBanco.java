package br.com.meuBanco.entity;


import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_banco database table.
 * 
 */
@Entity
@Table(name="tb_banco")
public class TbBanco {


	@Id
	@Column(name="id_banco", unique=true, nullable=false)
	private int idBanco;

	@Column(name="tb_banco_codigo", nullable=false)
	private int tbBancoCodigo;

	@Column(name="tb_banco_nome", nullable=false, length=100)
	private String tbBancoNome;

	//bi-directional many-to-one association to TbAgencia
	@OneToMany(mappedBy="tbBanco")
	private List<TbAgencia> tbAgencias;

	public TbBanco() {
	}

	public int getIdBanco() {
		return this.idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public int getTbBancoCodigo() {
		return this.tbBancoCodigo;
	}

	public void setTbBancoCodigo(int tbBancoCodigo) {
		this.tbBancoCodigo = tbBancoCodigo;
	}

	public String getTbBancoNome() {
		return this.tbBancoNome;
	}

	public void setTbBancoNome(String tbBancoNome) {
		this.tbBancoNome = tbBancoNome;
	}

	public List<TbAgencia> getTbAgencias() {
		return this.tbAgencias;
	}

	public void setTbAgencias(List<TbAgencia> tbAgencias) {
		this.tbAgencias = tbAgencias;
	}

	public TbAgencia addTbAgencia(TbAgencia tbAgencia) {
		getTbAgencias().add(tbAgencia);
		tbAgencia.setTbBanco(this);

		return tbAgencia;
	}

	public TbAgencia removeTbAgencia(TbAgencia tbAgencia) {
		getTbAgencias().remove(tbAgencia);
		tbAgencia.setTbBanco(null);

		return tbAgencia;
	}

}