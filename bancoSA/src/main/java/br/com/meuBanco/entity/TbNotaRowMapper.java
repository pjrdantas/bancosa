package br.com.meuBanco.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TbNotaRowMapper implements RowMapper<TbNota> {

	@Override
	public TbNota mapRow(ResultSet row, int rowNum) throws SQLException {
		
		TbNota tbNota = new TbNota();
		tbNota.setIdNotas(row.getInt("id_notas"));
		tbNota.setTbNotasValor(row.getBigDecimal("tb_notas_valor"));
		
		
		return tbNota;
	}

}
