package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbNotaDAO;
import br.com.meuBanco.entity.dto.TbNotaDTO;



@Transactional
@Repository
public class TbNotaDAO implements ItbNotaDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbNotaDTO(TbNotaDTO tbNotaDTO)  throws Exception, Throwable {
		
				
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_notas (");
		sql.append( "  id_notas, ");
		sql.append( "  tb_notas_valor) ");
		sql.append( "  values (:idNotas, :tbNotasValor)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idNotas", tbNotaDTO.getIdNotas())
				.addValue("tbNotasValor", tbNotaDTO.getNotasValor());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DA NOTA-------------------------------" + e.toString());
	        
	     }	

	}

	
		
	@Override
	public void updateTbNotaDTO(TbNotaDTO tbNotaDTO)  throws Exception, Throwable {
		
		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_notas ");
		sql.append(" SET  ");
		sql.append(" tb_notas_valor = :tbNotasValor ");
		sql.append(" WHERE id_notas = :idNotas");
		
		SqlParameterSource params = new MapSqlParameterSource()				
				.addValue("tbNotasValor", tbNotaDTO.getNotasValor())
				.addValue("idNotas", tbNotaDTO.getIdNotas());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO UPDATE DA NOTA-------------------------------" + e.toString());
	        
	     }	

		
	}
	

	final static StringBuilder sqlSelectPrincipal = new StringBuilder()
			.append("  SELECT ")
			.append("  id_notas ")
			.append("  ,tb_notas_valor")
			.append("  FROM tb_notas ");
					
	private List<TbNotaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbNotaDTO tbNotaDTO = new TbNotaDTO();

			tbNotaDTO.setIdNotas(rs.getInt("id_notas"));
			tbNotaDTO.setNotasValor(rs.getInt("tb_notas_valor"));
	
	return tbNotaDTO;
	 
		});
	}
	
	
	@Override
	public List<TbNotaDTO> getAllTbNotas()  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ORDER BY  tb_notas_valor ");
		
		return devolveListaObjetos(sql, null);
	}
	
	
	
	private TbNotaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			TbNotaDTO tbNotaDTO = new TbNotaDTO();

			tbNotaDTO.setIdNotas(rs.getInt("id_notas"));
			tbNotaDTO.setNotasValor(rs.getInt("tb_notas_valor"));
			
		try {							
			return tbNotaDTO;			
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO CONSULTA DA NOTA-------------------------------" + e.toString());
	    	 return null;
	     }	

		});
	}
		
	@Override 
	public TbNotaDTO getTbNotaById(int id)  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  WHERE id_notas = :idNotas ");
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("idNotas", id);
		
		return devolveObjeto(sql, params);
		
	}

	
	
		
	@Override
	public void deleteTbNota(int id)  throws Exception, Throwable {
				
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_notas "); 
	    sql.append(" WHERE id_notas = :idNotas");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idNotas", id);

		try{
		    jdbcTemplate.update(sql.toString(), params);
		         
		}catch (Exception e){
		    System.out.println("-----------------ERRO NO DELETE DA NOTA-------------------------------" + e.toString());
		        
		}	

	}



	
}
