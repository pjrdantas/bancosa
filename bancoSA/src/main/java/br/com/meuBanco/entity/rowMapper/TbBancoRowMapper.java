package br.com.meuBanco.entity.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.meuBanco.entity.TbBanco;

public class TbBancoRowMapper implements RowMapper<TbBanco> {

	@Override
	public TbBanco mapRow(ResultSet row, int rowNum) throws SQLException {
		
		TbBanco tbBanco = new TbBanco();
		tbBanco.setIdBanco(row.getInt("id_banco"));
		tbBanco.setTbBancoCodigo(row.getInt("tb_banco_codigo"));
		tbBanco.setTbBancoNome(row.getString("tb_banco_nome"));
		
		return tbBanco;
	}

}
