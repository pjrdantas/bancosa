package br.com.meuBanco.entity;


import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_agencia database table.
 * 
 */
@Entity
@Table(name="tb_agencia")
public class TbAgencia {


	@Id
	@Column(name="id_agencia", unique=true, nullable=false)
	private int idAgencia;

	@Column(name="tb_agencia_codigo", nullable=false)
	private int tbAgenciaCodigo;

	@Column(name="tb_agencia_digito", nullable=false, length=1)
	private String tbAgenciaDigito;

	//bi-directional many-to-one association to TbBanco
	@ManyToOne
	@JoinColumn(name="tb_banco_id_banco", nullable=false)
	private TbBanco tbBanco;

	//bi-directional many-to-one association to TbCliente
	@OneToMany(mappedBy="tbAgencia")
	private List<TbCliente> tbClientes;

	//bi-directional many-to-one association to TbConta
	@OneToMany(mappedBy="tbAgencia")
	private List<TbConta> tbContas;

	public TbAgencia() {
	}

	public int getIdAgencia() {
		return this.idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
		this.idAgencia = idAgencia;
	}

	public int getTbAgenciaCodigo() {
		return this.tbAgenciaCodigo;
	}

	public void setTbAgenciaCodigo(int tbAgenciaCodigo) {
		this.tbAgenciaCodigo = tbAgenciaCodigo;
	}

	public String getTbAgenciaDigito() {
		return this.tbAgenciaDigito;
	}

	public void setTbAgenciaDigito(String tbAgenciaDigito) {
		this.tbAgenciaDigito = tbAgenciaDigito;
	}

	public TbBanco getTbBanco() {
		return this.tbBanco;
	}

	public void setTbBanco(TbBanco tbBanco) {
		this.tbBanco = tbBanco;
	}

	public List<TbCliente> getTbClientes() {
		return this.tbClientes;
	}

	public void setTbClientes(List<TbCliente> tbClientes) {
		this.tbClientes = tbClientes;
	}

	public TbCliente addTbCliente(TbCliente tbCliente) {
		getTbClientes().add(tbCliente);
		tbCliente.setTbAgencia(this);

		return tbCliente;
	}

	public TbCliente removeTbCliente(TbCliente tbCliente) {
		getTbClientes().remove(tbCliente);
		tbCliente.setTbAgencia(null);

		return tbCliente;
	}

	public List<TbConta> getTbContas() {
		return this.tbContas;
	}

	public void setTbContas(List<TbConta> tbContas) {
		this.tbContas = tbContas;
	}

	public TbConta addTbConta(TbConta tbConta) {
		getTbContas().add(tbConta);
		tbConta.setTbAgencia(this);

		return tbConta;
	}

	public TbConta removeTbConta(TbConta tbConta) {
		getTbContas().remove(tbConta);
		tbConta.setTbAgencia(null);

		return tbConta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAgencia;
		result = prime * result + tbAgenciaCodigo;
		result = prime * result + ((tbAgenciaDigito == null) ? 0 : tbAgenciaDigito.hashCode());
		result = prime * result + ((tbBanco == null) ? 0 : tbBanco.hashCode());
		result = prime * result + ((tbClientes == null) ? 0 : tbClientes.hashCode());
		result = prime * result + ((tbContas == null) ? 0 : tbContas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbAgencia other = (TbAgencia) obj;
		if (idAgencia != other.idAgencia)
			return false;
		if (tbAgenciaCodigo != other.tbAgenciaCodigo)
			return false;
		if (tbAgenciaDigito == null) {
			if (other.tbAgenciaDigito != null)
				return false;
		} else if (!tbAgenciaDigito.equals(other.tbAgenciaDigito))
			return false;
		if (tbBanco == null) {
			if (other.tbBanco != null)
				return false;
		} else if (!tbBanco.equals(other.tbBanco))
			return false;
		if (tbClientes == null) {
			if (other.tbClientes != null)
				return false;
		} else if (!tbClientes.equals(other.tbClientes))
			return false;
		if (tbContas == null) {
			if (other.tbContas != null)
				return false;
		} else if (!tbContas.equals(other.tbContas))
			return false;
		return true;
	}

	
}