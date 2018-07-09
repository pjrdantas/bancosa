package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbBancoDAO;
import br.com.meuBanco.entity.TbBanco;
import br.com.meuBanco.entity.rowMapper.TbBancoRowMapper;



@Transactional
@Repository
public class TbBancoDAO implements ItbBancoDAO {
	
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbBanco(TbBanco tbBanco) {
		
		//Add tbBanco
		String sql = "INSERT INTO "
				+ "tb_banco ("
				+ "id_banco, "
				+ "tb_banco_codigo, "
				+ "tb_banco_nome) "
				+ "values (?, ?, ?)";
		jdbcTemplate.update(sql, 
				tbBanco.getIdBanco(), 
				tbBanco.getTbBancoCodigo(), 
				tbBanco.getTbBancoNome());
		
		//Fetch tbBanco id
		sql = "SELECT "
				+ "id_banco "
				+ "FROM tb_banco "
				+ "WHERE "
				+ "tb_banco_codigo = ? and "
				+ "tb_banco_nome=?";
		int idBanco = jdbcTemplate.queryForObject(sql, Integer.class, 
				tbBanco.getTbBancoCodigo(), 
				tbBanco.getTbBancoNome());
		
		//Set tbBanco id 
		tbBanco.setIdBanco(idBanco);
	}

	
		
	@Override
	public void updateTbBanco(TbBanco tbBanco) {
		
		String sql = "UPDATE tb_banco "
				+ "SET  "
				+ "tb_banco_codigo=?, "
				+ "tb_banco_nome=? "
				+ "WHERE id_banco=?";
		jdbcTemplate.update(sql, 
				tbBanco.getTbBancoCodigo(), 
				tbBanco.getTbBancoNome(), 
				tbBanco.getIdBanco());
	}
	

		
	@Override
	public List<TbBanco> getAllTbBancos() {
		String sql = "SELECT "
				+ "id_banco, "
				+ "tb_banco_codigo, "
				+ "tb_banco_nome "
				+ "FROM tb_banco "
				+ "order by tb_banco_codigo";
        //RowMapper<TbBanco> rowMapper = new BeanPropertyRowMapper<TbBanco>(TbBanco.class);
		RowMapper<TbBanco> rowMapper = new TbBancoRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}	
	
	
	
	
	 
	
	
	@Override
	public TbBanco getTbBancoById(int id) {
		String sql = "SELECT "
				+ "tb_banco_codigo, "
				+ "tb_banco_nome "
				+ "FROM tb_banco "
				+ "WHERE id_banco = ?";
		RowMapper<TbBanco> rowMapper = new BeanPropertyRowMapper<TbBanco>(TbBanco.class);
		TbBanco tbBanco = jdbcTemplate.queryForObject(sql, rowMapper, id);
		tbBanco.setIdBanco(id);
		System.out.println("-------------------------------------------------------------------"+tbBanco.getIdBanco());
		return tbBanco;
	}
	
	
		
	
	 
	
	
	@Override
	public void deleteTbBanco(int id) {
		String sql = "DELETE FROM tb_banco WHERE id_banco=?";
		jdbcTemplate.update(sql, id);
	}
	
	
	 
	
	
}
