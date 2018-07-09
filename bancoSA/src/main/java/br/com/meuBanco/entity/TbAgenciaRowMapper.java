package br.com.meuBanco.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TbAgenciaRowMapper implements RowMapper<TbAgencia> {

	@Override
	public TbAgencia mapRow(ResultSet row, int rowNum) throws SQLException {
		
		TbAgencia tbAgencia = new TbAgencia();
		tbAgencia.setIdAgencia(row.getInt("id_agencia"));
		tbAgencia.setTbAgenciaCodigo(row.getInt("tb_agencia_codigo"));
		tbAgencia.setTbAgenciaDigito(row.getString("tb_agencia_digito"));
		tbAgencia.setTbBanco(null);
		
		return tbAgencia;
	}

}
