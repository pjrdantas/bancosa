package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbAgenciaDAO;
import br.com.meuBanco.entity.TbAgencia;
import br.com.meuBanco.entity.TbAgenciaRowMapper;





@Transactional
@Repository
public class TbAgenciaDAO implements ItbAgenciaDAO {
	
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbAgencia(TbAgencia tbAgencia) {
		
		//Add tbAgencia
		String sql = "INSERT INTO "
				+ "tb_agencia ("
				+ "id_agencia, "
				+ "tb_agencia_codigo, "
				+ "tb_agencia_digito, "
				+ "tb_banco_id_banco) "
				+ "values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, 
				tbAgencia.getIdAgencia(), 
				tbAgencia.getTbAgenciaCodigo(), 
				tbAgencia.getTbAgenciaDigito(), 
				tbAgencia.getTbBanco());
		
		//Fetch tbAgencia id
		sql = "SELECT "
				+ "id_agencia "
				+ "FROM tb_agencia "
				+ "WHERE "
				+ "tb_agencia_codigo = ? and "
				+ "tb_agencia_digito=? and "
				+ "tb_banco_id_banco=?";
		int idAgencia = jdbcTemplate.queryForObject(sql, Integer.class, 
				tbAgencia.getTbAgenciaCodigo(), 
				tbAgencia.getTbAgenciaDigito(), 
				tbAgencia.getTbBanco());
		
		//Set tbAgencia id 
		tbAgencia.setIdAgencia(idAgencia);
	}

	
		
	@Override
	public void updateTbAgencia(TbAgencia tbAgencia) {
		
		String sql = "UPDATE tb_agencia "
				+ "SET  "
				+ "tb_agencia_codigo=?, "
				+ "tb_agencia_digito=?, "
				+ "tb_banco_id_banco=? "
				+ "WHERE id_agencia=?";
		jdbcTemplate.update(sql, 
				tbAgencia.getTbAgenciaCodigo(), 
				tbAgencia.getTbAgenciaDigito(), 
				tbAgencia.getTbBanco(), 
				tbAgencia.getIdAgencia());
	}
	

		
	@Override
	public List<TbAgencia> getAllTbAgencias() {
		String sql = "SELECT "
				+ "id_agencia, "
				+ "tb_agencia_codigo, "
				+ "tb_agencia_digito, "
				+ "tb_banco_id_banco "
				+ "FROM tb_agencia "
				+ "order by tb_agencia_codigo";
        //RowMapper<TbAgencia> rowMapper = new BeanPropertyRowMapper<TbAgencia>(TbAgencia.class);
		RowMapper<TbAgencia> rowMapper = new TbAgenciaRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}	
	
	
	
	
	 
	
	
	@Override
	public TbAgencia getTbAgenciaById(int id) {
		String sql = "SELECT "
				+ "id_agencia, "
				+ "tb_agencia_codigo, "
				+ "tb_agencia_digito, "
				+ "tb_banco_id_banco "
				+ "FROM tb_agencia "
				+ "WHERE id_agencia = ?";
		RowMapper<TbAgencia> rowMapper = new BeanPropertyRowMapper<TbAgencia>(TbAgencia.class);
		TbAgencia tbAgencia = jdbcTemplate.queryForObject(sql, rowMapper, id);
		tbAgencia.setIdAgencia(id);
		System.out.println("-------------------------------------------------------------------"+tbAgencia.getIdAgencia());
		return tbAgencia;
	}
	
	
		
	
	 
	
	
	@Override
	public void deleteTbAgencia(int id) {
		String sql = "DELETE FROM tb_agencia WHERE id_agencia=?";
		jdbcTemplate.update(sql, id);
	}
	
	
	 
	
	
}
