package br.com.meuBanco.entity;


import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_cliente database table.
 * 
 */
@Entity
@Table(name="tb_cliente")
public class TbCliente {


	@Id
	@Column(name="id_cliente", unique=true, nullable=false)
	private int idCliente;

	@Column(name="tb_cliente_nome", nullable=false, length=45)
	private String tbClienteNome;

	@Column(name="tb_cliente_senha", nullable=false)
	private int tbClienteSenha;

	//bi-directional many-to-one association to TbAgencia
	@ManyToOne
	@JoinColumn(name="tb_agencia_id_agencia", nullable=false)
	private TbAgencia tbAgencia;

	//bi-directional many-to-one association to TbConta
	@OneToMany(mappedBy="tbCliente")
	private List<TbConta> tbContas;

	public TbCliente() {
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getTbClienteNome() {
		return this.tbClienteNome;
	}

	public void setTbClienteNome(String tbClienteNome) {
		this.tbClienteNome = tbClienteNome;
	}

	public int getTbClienteSenha() {
		return this.tbClienteSenha;
	}

	public void setTbClienteSenha(int tbClienteSenha) {
		this.tbClienteSenha = tbClienteSenha;
	}

	public TbAgencia getTbAgencia() {
		return this.tbAgencia;
	}

	public void setTbAgencia(TbAgencia tbAgencia) {
		this.tbAgencia = tbAgencia;
	}

	public List<TbConta> getTbContas() {
		return this.tbContas;
	}

	public void setTbContas(List<TbConta> tbContas) {
		this.tbContas = tbContas;
	}

	public TbConta addTbConta(TbConta tbConta) {
		getTbContas().add(tbConta);
		tbConta.setTbCliente(this);

		return tbConta;
	}

	public TbConta removeTbConta(TbConta tbConta) {
		getTbContas().remove(tbConta);
		tbConta.setTbCliente(null);

		return tbConta;
	}

}