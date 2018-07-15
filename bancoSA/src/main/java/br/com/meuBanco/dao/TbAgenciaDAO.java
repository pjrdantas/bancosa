package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbAgenciaDAO;
import br.com.meuBanco.entity.dto.TbAgenciaDTO;


@Transactional
@Repository
public class TbAgenciaDAO implements ItbAgenciaDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_agencia (");
		sql.append( "  id_agencia, ");
		sql.append( "  tb_agencia_codigo, ");
		sql.append( "  tb_agencia_digito, ");
		sql.append( "  tb_banco_id_banco) ");
		sql.append( "  values (:idAgencia, :tbAgenciaCodigo, :tbAgenciaDigito, :tbBanco)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idAgencia", tbAgenciaDTO.getIdAgencia())
				.addValue("tbAgenciaCodigo", tbAgenciaDTO.getAgenciaCodigo())
				.addValue("tbAgenciaDigito", tbAgenciaDTO.getAgenciaDigito())
				.addValue("tbBanco", tbAgenciaDTO.getIdbancoAgencia());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DA AGENCIA-------------------------------" + e.toString());
	        
	     }
	}
	
	

	@Override
	public void updateTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_agencia ");
		sql.append(" SET  ");
		sql.append(" tb_agencia_codigo = :tbAgenciaCodigo, ");
		sql.append(" tb_agencia_digito = UPPER(:tbAgenciaDigito), ");
		sql.append(" tb_banco_id_banco = :tbBanco ");
		sql.append(" WHERE id_agencia = :idAgencia");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("tbAgenciaCodigo", tbAgenciaDTO.getAgenciaCodigo())
				.addValue("tbAgenciaDigito", tbAgenciaDTO.getAgenciaDigito())
				.addValue("tbBanco", tbAgenciaDTO.getIdbancoAgencia())
				.addValue("idAgencia", tbAgenciaDTO.getIdAgencia());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO UPDATE DA AGENCIA-------------------------------" + e.toString());
	        
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
			
		
	private List<TbAgenciaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbAgenciaDTO tbAgenciaDTO = new TbAgenciaDTO();

			tbAgenciaDTO.setIdAgencia(rs.getInt("c.id_agencia"));
			tbAgenciaDTO.setAgenciaCodigo(rs.getInt("c.tb_agencia_codigo"));
			tbAgenciaDTO.setAgenciaDigito(rs.getString("c.tb_agencia_digito"));
			tbAgenciaDTO.setIdbancoAgencia(rs.getInt("i.id_banco"));
			tbAgenciaDTO.setBancoCodigo(rs.getInt("i.tb_banco_codigo"));
			tbAgenciaDTO.setBancoNome(rs.getString("i.tb_banco_nome"));
	
	return tbAgenciaDTO;
	 
		});
	}
	
	
	@Override
	public List<TbAgenciaDTO> getAllTbAgencias()   throws Exception, Throwable {
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append(" order by i.tb_banco_codigo, c.tb_agencia_codigo ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbAgenciaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			
			
			TbAgenciaDTO tbAgenciaDTO = new TbAgenciaDTO();

			tbAgenciaDTO.setIdAgencia(rs.getInt("c.id_agencia"));
			tbAgenciaDTO.setAgenciaCodigo(rs.getInt("c.tb_agencia_codigo"));
			tbAgenciaDTO.setAgenciaDigito(rs.getString("c.tb_agencia_digito"));
			tbAgenciaDTO.setIdbancoAgencia(rs.getInt("i.id_banco"));
			tbAgenciaDTO.setBancoCodigo(rs.getInt("i.tb_banco_codigo"));
			tbAgenciaDTO.setBancoNome(rs.getString("i.tb_banco_nome"));
			
			
			return tbAgenciaDTO;

		});
	}
		
	 
	public TbAgenciaDTO getTbAgenciaById(int id)   throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append(" WHERE c.id_agencia = :idAgencia ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idAgencia", id);
		
		System.out.println("-----------------RETORNO AGENCIA-------------------------------" + params);
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbAgencia(int id)   throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_agencia "); 
	    sql.append(" WHERE id_agencia = :idAgencia");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idAgencia", id);

			try{
		    	 jdbcTemplate.update(sql.toString(), params);		         
		     }catch (Exception e){
		    	 System.out.println("-----------------ERRO NO DELETE DA AGENCIA-------------------------------" + e.toString());
		        
		     }
	}


	
}
