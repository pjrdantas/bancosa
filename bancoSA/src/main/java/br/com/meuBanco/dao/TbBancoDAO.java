package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbBancoDAO;
import br.com.meuBanco.entity.dto.TbBancoDTO;



@Transactional
@Repository
public class TbBancoDAO implements ItbBancoDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbBancoDTO(TbBancoDTO tbBancoDTO)   throws Exception, Throwable {
						
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_banco (");
		sql.append( "  id_banco, ");
		sql.append( "  tb_banco_codigo, ");
		sql.append( "  tb_banco_nome) ");
		sql.append( "  values (:idBanco, :tbBancoCodigo, :tbBancoNome)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idBanco", tbBancoDTO.getIdBanco())
				.addValue("tbBancoCodigo", tbBancoDTO.getBancoCodigo())
				.addValue("tbBancoNome", tbBancoDTO.getBancoNome());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DO BANCO-------------------------------" + e.toString());
	        
	     }	

	}

	
		
	@Override
	public void updateTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable {
				
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_banco ");
		sql.append(" SET  ");
		sql.append(" tb_banco_codigo = :tbBancoCodigo, ");
		sql.append(" tb_banco_nome = :tbBancoNome ");
		sql.append(" WHERE id_banco = :idBanco");
		
		SqlParameterSource params = new MapSqlParameterSource()				
				.addValue("tbBancoCodigo", tbBancoDTO.getBancoCodigo())
				.addValue("tbBancoNome", tbBancoDTO.getBancoNome())
				.addValue("idBanco", tbBancoDTO.getIdBanco());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO UPDATE DO BANCO-------------------------------" + e.toString());
	        
	     }	

		
	}
	

	final static StringBuilder sqlSelectPrincipal = new StringBuilder()
			.append("  SELECT ")
			.append("  id_banco ")
			.append("  ,tb_banco_codigo")
			.append("  ,tb_banco_nome")
			.append("  FROM tb_banco ");
					
	private List<TbBancoDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbBancoDTO tbBancoDTO = new TbBancoDTO();

			tbBancoDTO.setIdBanco(rs.getInt("id_banco"));
			tbBancoDTO.setBancoCodigo(rs.getInt("tb_banco_codigo"));
			tbBancoDTO.setBancoNome(rs.getString("tb_banco_nome"));
	
	return tbBancoDTO;
	 
		});
	}
	
	
	@Override
	public List<TbBancoDTO> getAllTbBancos()  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ORDER BY  tb_banco_codigo ");
		
		return devolveListaObjetos(sql, null);
	}
	
	
	
	private TbBancoDTO devolveObjeto(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			TbBancoDTO tbBancoDTO = new TbBancoDTO();

			tbBancoDTO.setIdBanco(rs.getInt("id_banco"));
			tbBancoDTO.setBancoCodigo(rs.getInt("tb_banco_codigo"));
			tbBancoDTO.setBancoNome(rs.getString("tb_banco_nome"));
									
			return tbBancoDTO;

		});
	}
		
	@Override 
	public TbBancoDTO getTbBancoById(int id)  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  WHERE id_banco = :idBanco ");
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("idBanco", id);
		
		return devolveObjeto(sql, params);
		
	}

	
	
		
	@Override
	public void deleteTbBanco(int id)  throws Exception, Throwable {
				
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_banco "); 
	    sql.append(" WHERE id_banco = :idBanco");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idBanco", id);

		try{
		    jdbcTemplate.update(sql.toString(), params);
		         
		}catch (Exception e){
		    System.out.println("-----------------ERRO NO DELETE DA NOTA-------------------------------" + e.toString());
		        
		}	

	}



	
}
