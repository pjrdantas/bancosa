package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbNotaDAO;
import br.com.meuBanco.entity.TbNota;
import br.com.meuBanco.entity.TbNotaRowMapper;



@Transactional
@Repository
public class TbNotaDAO implements ItbNotaDAO {
	
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbNota(TbNota tbNota) {
		
		//Add tbNota
		String sql = "INSERT INTO tb_notas (id_notas, tb_notas_valor) values (?, ?)";
		jdbcTemplate.update(sql, tbNota.getIdNotas(), tbNota.getTbNotasValor());		
		
		//Fetch tbNota id
		sql = "SELECT id_notas FROM tb_notas WHERE tb_notas_valor=?";
		int idNota = jdbcTemplate.queryForObject(sql, Integer.class,  tbNota.getTbNotasValor()); 
		
		//Set tbNota id
		tbNota.setIdNotas(idNota);
	}

	
		
	@Override
	public void updateTbNota(TbNota tbNota) {
		
		String sql = "UPDATE tb_notas SET  tb_notas_valor=? WHERE id_notas=?";
		jdbcTemplate.update(sql, tbNota.getTbNotasValor(), tbNota.getIdNotas());
	}
	

		
	@Override
	public List<TbNota> getAllTbNotas() {
		
		String sql = "SELECT id_notas, tb_notas_valor FROM tb_notas order by tb_notas_valor";
		RowMapper<TbNota> rowMapper = new TbNotaRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}	
	
	
	@Override
	public TbNota getTbNotaById(int id) {
		
		String sql = "SELECT tb_notas_valor FROM tb_notas WHERE id_notas = ?";
		RowMapper<TbNota> rowMapper = new BeanPropertyRowMapper<TbNota>(TbNota.class);
		TbNota tbNota = jdbcTemplate.queryForObject(sql, rowMapper, id);
		tbNota.setIdNotas(id);
		System.out.println("-------------------------------------------------------------------"+tbNota.getIdNotas());
		return tbNota;
	}
	
		
	@Override
	public void deleteTbNota(int id) {
		
		String sql = "DELETE FROM tb_notas WHERE id_notas=?";
		jdbcTemplate.update(sql, id);
	}
		
}
