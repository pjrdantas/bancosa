package br.com.meuBanco.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dto.AgenciaDTO;
import br.com.meuBanco.repository.AgenciaRepository;

@Transactional
@Repository
public class AgenciaRepositoryImpl implements AgenciaRepository {
	
	private static final Logger log = LoggerFactory.getLogger(AgenciaRepositoryImpl.class);
		
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
		
	@Override
	public void addAgencia(AgenciaDTO agenciaDTO)  throws Exception, Throwable  {
		log.info("----> AgenciaRepositoryImpl.addAgencia");
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_agencia (");
		sql.append( "  id_agencia, ");
		sql.append( "  tb_agencia_codigo, ");
		sql.append( "  tb_agencia_digito, ");
		sql.append( "  tb_banco_id_banco) ");
		sql.append( "  values (:idAgencia, :tbAgenciaCodigo, :tbAgenciaDigito, :tbBanco)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idAgencia", agenciaDTO.getIdAgencia())
				.addValue("tbAgenciaCodigo", agenciaDTO.getAgenciaCodigo())
				.addValue("tbAgenciaDigito", agenciaDTO.getAgenciaDigito())
				.addValue("tbBanco", agenciaDTO.getIdbancoAgencia());		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);	         
	     }catch (Exception e){
	    	 log.error("ERRO NO INSERT DA AGENCIA", e.toString());	        
	     }
	}
	
	@Override
	public void updateAgencia(AgenciaDTO agenciaDTO)  throws Exception, Throwable  {
		log.info("----> AgenciaRepositoryImpl.updateAgencia");
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_agencia ");
		sql.append(" SET  ");
		sql.append(" tb_agencia_codigo = :tbAgenciaCodigo, ");
		sql.append(" tb_agencia_digito = UPPER(:tbAgenciaDigito), ");
		sql.append(" tb_banco_id_banco = :tbBanco ");
		sql.append(" WHERE id_agencia = :idAgencia");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("tbAgenciaCodigo", agenciaDTO.getAgenciaCodigo())
				.addValue("tbAgenciaDigito", agenciaDTO.getAgenciaDigito())
				.addValue("tbBanco", agenciaDTO.getIdbancoAgencia())
				.addValue("idAgencia", agenciaDTO.getIdAgencia());		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 log.error("ERRO NO UPDATE DA AGENCIA", e.toString());
	        
	     }	
	}
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_agencia")
			.append("  ,c.tb_agencia_codigo")
			.append("  ,c.tb_agencia_digito")
			.append("  ,i.id_banco")
			.append("  ,i.tb_banco_codigo")
			.append("  ,i.tb_banco_nome")
			.append("  FROM tb_agencia c INNER JOIN tb_banco i ON i.id_banco = c.tb_banco_id_banco");
					
	private List<AgenciaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			AgenciaDTO agenciaDTO = new AgenciaDTO();

			agenciaDTO.setIdAgencia(rs.getInt("c.id_agencia"));
			agenciaDTO.setAgenciaCodigo(rs.getInt("c.tb_agencia_codigo"));
			agenciaDTO.setAgenciaDigito(rs.getString("c.tb_agencia_digito"));
			agenciaDTO.setIdbancoAgencia(rs.getInt("i.id_banco"));
			agenciaDTO.setBancoCodigo(rs.getInt("i.tb_banco_codigo"));
			agenciaDTO.setBancoNome(rs.getString("i.tb_banco_nome"));	
	return agenciaDTO;	 
		});
	}
	
	
	@Override
	public List<AgenciaDTO> getAllAgencias()   throws Exception, Throwable {
		log.info("----> AgenciaRepositoryImpl.getAllAgencias");
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append(" order by i.tb_banco_codigo, c.tb_agencia_codigo ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private AgenciaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			AgenciaDTO agenciaDTO = new AgenciaDTO();

			agenciaDTO.setIdAgencia(rs.getInt("c.id_agencia"));
			agenciaDTO.setAgenciaCodigo(rs.getInt("c.tb_agencia_codigo"));
			agenciaDTO.setAgenciaDigito(rs.getString("c.tb_agencia_digito"));
			agenciaDTO.setIdbancoAgencia(rs.getInt("i.id_banco"));
			agenciaDTO.setBancoCodigo(rs.getInt("i.tb_banco_codigo"));
			agenciaDTO.setBancoNome(rs.getString("i.tb_banco_nome"));
				
			return agenciaDTO;
		});
	}
		
	 
	public AgenciaDTO getAgenciaById(int id)   throws Exception, Throwable {
		log.info("----> AgenciaRepositoryImpl.getAgenciaById");
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append(" WHERE c.id_agencia = :idAgencia ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idAgencia", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteAgencia(int id)   throws Exception, Throwable {
		log.info("----> AgenciaRepositoryImpl.deleteAgencia");
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_agencia "); 
	    sql.append(" WHERE id_agencia = :idAgencia");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idAgencia", id);

			try{
		    	 jdbcTemplate.update(sql.toString(), params);		         
		     }catch (Exception e){
		    	 log.error("ERRO NO DELETE DA AGENCIA", e.toString());
		        
		     }
	}



	


	
}
