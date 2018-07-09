package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbAgenciaDAO;
import br.com.meuBanco.entity.TbAgencia;
import br.com.meuBanco.entity.dto.TbAgenciaDTO;





@Transactional
@Repository
public class TbAgenciaDAO implements ItbAgenciaDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbAgencia(TbAgencia tbAgencia) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_agencia (");
		sql.append( "  id_agencia, ");
		sql.append( "  tb_agencia_codigo, ");
		sql.append( "  tb_agencia_digito, ");
		sql.append( "  tb_banco_id_banco) ");
		sql.append( "  values (:idAgencia, :tbAgenciaCodigo, :tbAgenciaDigito, :tbBanco)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idAgencia", tbAgencia.getIdAgencia())
				.addValue("tbAgenciaCodigo", tbAgencia.getTbAgenciaCodigo())
				.addValue("tbAgenciaDigito", tbAgencia.getTbAgenciaDigito())
				.addValue("tbBanco", tbAgencia.getTbBanco().getIdBanco());
		
		jdbcTemplate.update(sql.toString(), params);
								
	}
	
	

	@Override
	public void updateTbAgencia(TbAgencia tbAgencia) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_agencia ");
		sql.append(" SET  ");
		sql.append(" tb_agencia_codigo = :tbAgenciaCodigo, ");
		sql.append(" tb_agencia_digito = UPPER(:tbAgenciaDigito), ");
		sql.append(" tb_banco_id_banco = :tbBanco ");
		sql.append(" WHERE id_agencia = :idAgencia");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("tbAgenciaCodigo", tbAgencia.getTbAgenciaCodigo())
				.addValue("tbAgenciaDigito", tbAgencia.getTbAgenciaCodigo())
				.addValue("tbBanco", tbAgencia.getTbBanco().getIdBanco())
				.addValue("idAgencia", tbAgencia.getIdAgencia());
		
		jdbcTemplate.update(sql.toString(), params);
	}

	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_agencia")
			.append("  ,c.tb_agencia_codigo")
			.append("  ,c.tb_agencia_digito")
			.append("  ,i.tb_banco_codigo")
			.append("  ,i.tb_banco_nome")
			.append("  FROM tb_agencia c INNER JOIN tb_banco i");
			
		
	private List<TbAgenciaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbAgenciaDTO tbAgenciaDTO = new TbAgenciaDTO();

			tbAgenciaDTO.setIdAgencia(rs.getInt("c.id_agencia"));
			tbAgenciaDTO.setAgenciaCodigo(rs.getInt("c.tb_agencia_codigo"));
			tbAgenciaDTO.setAgenciaDigito(rs.getString("c.tb_agencia_digito"));
			tbAgenciaDTO.setBancoCodigo(rs.getInt("i.tb_banco_codigo"));
			tbAgenciaDTO.setBancoNome(rs.getString("i.tb_banco_nome"));
	
	return tbAgenciaDTO;
	 
		});
	}
	
	
	@Override
	public List<TbAgenciaDTO> getAllTbAgencias() {
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ON i.id_banco = c.tb_banco_id_banco order by c.tb_agencia_codigo ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbAgenciaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			
			
			TbAgenciaDTO tbAgenciaDTO = new TbAgenciaDTO();

			tbAgenciaDTO.setIdAgencia(rs.getInt("c.id_agencia"));
			tbAgenciaDTO.setAgenciaCodigo(rs.getInt("c.tb_agencia_codigo"));
			tbAgenciaDTO.setAgenciaDigito(rs.getString("c.tb_agencia_digito"));
			tbAgenciaDTO.setBancoCodigo(rs.getInt("i.tb_banco_codigo"));
			tbAgenciaDTO.setBancoNome(rs.getString("i.tb_banco_nome"));
			
			
			return tbAgenciaDTO;

		});
	}
		
	 
	public TbAgenciaDTO getTbAgenciaById(int id) {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  ON i.id_banco = c.tb_banco_id_banco  ")
		.append(" WHERE c.id_agencia = :idAgencia ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idAgencia", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbAgencia(int id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_agencia "); 
	    sql.append(" WHERE id_agencia = :idAgencia");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idAgencia", id);

	     jdbcTemplate.update(sql.toString(), params);
	}


	
}
