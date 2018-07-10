package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbContaDAO;
import br.com.meuBanco.entity.TbConta;
import br.com.meuBanco.entity.dto.TbContaDTO;





@Transactional
@Repository
public class TbContaDAO implements ItbContaDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbConta(TbConta tbConta) {
		
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
				.addValue("idConta", tbConta.getIdConta())
				.addValue("tbContaDigito", tbConta.getTbContaDigito())
				.addValue("tbContaNumero", tbConta.getTbContaNumero())
				.addValue("tbContaTipo", tbConta.getTbContaTipo())
				.addValue("tbAgencia", tbConta.getTbAgencia().getIdAgencia())
				.addValue("tbCliente", tbConta.getTbCliente().getIdCliente());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DA CONTA-------------------------------" + e.toString());
	        
	     }	
	}
	
	
	

	@Override
	public void updateTbConta(TbConta tbConta) {
		
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
				.addValue("tbContaDigito", tbConta.getTbContaDigito())
				.addValue("tbContaNumero", tbConta.getTbContaNumero())
				.addValue("tbContaTipo", tbConta.getTbContaTipo())
				.addValue("tbAgencia", tbConta.getTbAgencia().getIdAgencia())
				.addValue("tbCliente", tbConta.getTbCliente().getIdCliente())
				.addValue("idConta", tbConta.getIdConta());
		
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
			.append("  ,i.tb_agencia_codigo")
			.append("  ,i.tb_agencia_digito")
			.append("  ,a.tb_cliente_nome")
			.append("  ,a.tb_cliente_senha")
			.append("  FROM tb_conta c INNER JOIN tb_agencia i");
			
		
	private List<TbContaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbContaDTO tbContaDTO = new TbContaDTO();

			tbContaDTO.setIdConta(rs.getInt("c.id_conta"));
			tbContaDTO.setContaDigito(rs.getInt("c.tb_conta_digito"));
			tbContaDTO.setContaNumero(rs.getInt("c.tb_conta_numero"));
			tbContaDTO.setContaTipo(rs.getInt("c.tb_conta_tipo"));
			tbContaDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			tbContaDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			tbContaDTO.setClienteNome(rs.getString("a.tb_cliente_nome"));
			tbContaDTO.setClienteSenha(rs.getInt("a.tb_cliente_senha"));
	
	return tbContaDTO;
	 
		});
	}
	
	
	@Override
	public List<TbContaDTO> getAllTbContas() {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ON i.id_agencia = c.tb_agencia_id_agencia  ")
		.append("  INNER JOIN tb_cliente a ON a.id_cliente = c.tb_cliente_id_cliente  ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbContaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			TbContaDTO tbContaDTO = new TbContaDTO();

			tbContaDTO.setIdConta(rs.getInt("c.id_conta"));
			tbContaDTO.setContaDigito(rs.getInt("c.tb_conta_digito"));
			tbContaDTO.setContaNumero(rs.getInt("c.tb_conta_numero"));
			tbContaDTO.setContaTipo(rs.getInt("c.tb_conta_tipo"));
			tbContaDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			tbContaDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			tbContaDTO.setClienteNome(rs.getString("a.tb_cliente_nome"));
			tbContaDTO.setClienteSenha(rs.getInt("a.tb_cliente_senha"));
						
			return tbContaDTO;

		});
	}
		
	 
	public TbContaDTO getTbContaById(int id) {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  ON i.id_agencia = c.tb_agencia_id_agencia  ")
		   .append("  INNER JOIN tb_cliente a ON a.id_cliente = c.tb_cliente_id_cliente  ")
		   .append("  WHERE c.id_conta = :idConta ");
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbConta(int id) {
		
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
