package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbContaDAO;
import br.com.meuBanco.entity.dto.TbContaDTO;




@Transactional
@Repository
public class TbContaDAO implements ItbContaDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_conta (");
		sql.append( "  id_conta, ");
		sql.append( "  tb_conta_digito, ");
		sql.append( "  tb_conta_numero, ");
		sql.append( "  tb_conta_tipo, ");
		sql.append( "  tb_agencia_id_agencia, ");
		sql.append( "  tb_cliente_id_cliente) ");
		sql.append( "  values (:idConta, :tbContaDigito, :tbContaNumero, :tbContaTipo, :tbAgencia, :tbCliente)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idConta", tbContaDTO.getIdConta())
				.addValue("tbContaDigito", tbContaDTO.getContaDigito())
				.addValue("tbContaNumero", tbContaDTO.getContaNumero())
				.addValue("tbContaTipo", tbContaDTO.getContaTipo())
				.addValue("tbAgencia", tbContaDTO.getContaIdAgencia())
				.addValue("tbCliente", tbContaDTO.getContaIdCliente());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DA CONTA-------------------------------" + e.toString());	        
	     }	
	}
	
	
	

	@Override
	public void updateTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_conta ");
		sql.append(" SET  ");
		sql.append(" tb_conta_digito = :tbContaDigito, ");
		sql.append(" tb_conta_numero = :tbContaNumero, ");
		sql.append(" tb_conta_tipo = :tbContaTipo, ");
		sql.append(" tb_agencia_id_agencia = :tbAgencia, ");
		sql.append(" tb_cliente_id_cliente = :tbCliente ");
		sql.append(" WHERE id_conta = :idConta");
		
		SqlParameterSource params = new MapSqlParameterSource()				
				.addValue("idConta", tbContaDTO.getIdConta())
				.addValue("tbContaDigito", tbContaDTO.getContaDigito())
				.addValue("tbContaNumero", tbContaDTO.getContaNumero())
				.addValue("tbContaTipo", tbContaDTO.getContaTipo())
				.addValue("tbAgencia", tbContaDTO.getContaIdAgencia())
				.addValue("tbCliente", tbContaDTO.getContaIdCliente());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO UPDATE DA CONTA-------------------------------" + e.toString());
	        
	     }	
	}
	

	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_conta")
			.append("  ,c.tb_conta_digito")
			.append("  ,c.tb_conta_numero")
			.append("  ,c.tb_conta_tipo")
			.append("  ,i.id_agencia")
			.append("  ,i.tb_agencia_codigo")
			.append("  ,i.tb_agencia_digito")
			.append("  ,a.id_cliente")
			.append("  ,a.tb_cliente_nome")
			.append("  ,a.tb_cliente_senha")
			.append("  FROM tb_conta c INNER JOIN tb_agencia i ON i.id_agencia = c.tb_agencia_id_agencia  ")
	        .append("                  INNER JOIN tb_cliente a ON a.id_cliente = c.tb_cliente_id_cliente  ");
		
	private List<TbContaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbContaDTO tbContaDTO = new TbContaDTO();

			tbContaDTO.setIdConta(rs.getInt("c.id_conta"));
			tbContaDTO.setContaDigito(rs.getInt("c.tb_conta_digito"));
			tbContaDTO.setContaNumero(rs.getInt("c.tb_conta_numero"));
			tbContaDTO.setContaTipo(rs.getInt("c.tb_conta_tipo"));
			tbContaDTO.setContaIdAgencia(rs.getInt("i.id_agencia"));
			tbContaDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			tbContaDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			tbContaDTO.setContaIdCliente(rs.getInt("a.id_cliente"));
			tbContaDTO.setClienteNome(rs.getString("a.tb_cliente_nome"));
			tbContaDTO.setClienteSenha(rs.getInt("a.tb_cliente_senha"));
	
	return tbContaDTO;
	 
		});
	}
	
	
	@Override
	public List<TbContaDTO> getAllTbContas()  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ORDER BY c.tb_cliente_id_cliente  ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbContaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			TbContaDTO tbContaDTO = new TbContaDTO();

			tbContaDTO.setIdConta(rs.getInt("c.id_conta"));
			tbContaDTO.setContaDigito(rs.getInt("c.tb_conta_digito"));
			tbContaDTO.setContaNumero(rs.getInt("c.tb_conta_numero"));
			tbContaDTO.setContaTipo(rs.getInt("c.tb_conta_tipo"));
			tbContaDTO.setContaIdAgencia(rs.getInt("i.id_agencia"));
			tbContaDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			tbContaDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			tbContaDTO.setContaIdCliente(rs.getInt("a.id_cliente"));
			tbContaDTO.setClienteNome(rs.getString("a.tb_cliente_nome"));
			tbContaDTO.setClienteSenha(rs.getInt("a.tb_cliente_senha"));
						
			return tbContaDTO;

		});
	}
		
	 
	public TbContaDTO getTbContaById(int id)  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  WHERE c.id_conta = :idConta ");
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbConta(int id)  throws Exception, Throwable {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_conta "); 
	    sql.append(" WHERE id_conta = :idConta");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", id);

		try{
		    jdbcTemplate.update(sql.toString(), params);
		         
		}catch (Exception e){
		    System.out.println("-----------------ERRO NO DELETE DO CLIENTE-------------------------------" + e.toString());
		        
		}	
	}
		


	
}
