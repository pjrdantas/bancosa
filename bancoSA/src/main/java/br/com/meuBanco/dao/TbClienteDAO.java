package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbClienteDAO;
import br.com.meuBanco.entity.TbCliente;
import br.com.meuBanco.entity.dto.TbClienteDTO;





@Transactional
@Repository
public class TbClienteDAO implements ItbClienteDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbCliente(TbCliente tbCliente) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_cliente (");
		sql.append( "  id_cliente, ");
		sql.append( "  tb_cliente_nome, ");
		sql.append( "  tb_cliente_senha, ");
		sql.append( "  tb_agencia_id_agencia) ");
		sql.append( "  values (:idCliente, :tbClienteNome, :tbClienteSenha, :tbAgencia)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idCliente", tbCliente.getIdCliente())
				.addValue("tbClienteNome", tbCliente.getTbClienteNome())
				.addValue("tbClienteSenha", tbCliente.getTbClienteSenha())
				.addValue("tbAgencia", tbCliente.getTbAgencia().getIdAgencia());
		
		jdbcTemplate.update(sql.toString(), params);
								
	}
	
	

	@Override
	public void updateTbCliente(TbCliente tbCliente) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_cliente ");
		sql.append(" SET  ");
		sql.append(" tb_cliente_nome = :tbClienteNome, ");
		sql.append(" tb_cliente_senha = :tbClienteSenha, ");
		sql.append(" tb_agencia_id_agencia = :tbAgencia ");
		sql.append(" WHERE id_cliente = :idCliente");
		
		SqlParameterSource params = new MapSqlParameterSource()
				
				.addValue("tbClienteNome", tbCliente.getTbClienteNome())
				.addValue("tbClienteSenha", tbCliente.getTbClienteSenha())
				.addValue("tbAgencia", tbCliente.getTbAgencia().getIdAgencia())
				.addValue("idCliente", tbCliente.getIdCliente());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);
	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO UPDATE DO CLIENTE-------------------------------" + e.toString());
	        
	     }
	}

	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_cliente")
			.append("  ,c.tb_cliente_nome")
			.append("  ,c.tb_cliente_senha")
			.append("  ,i.tb_agencia_codigo")
			.append("  ,i.tb_agencia_digito")
			.append("  FROM tb_cliente c INNER JOIN tb_agencia i ");
			
		
	private List<TbClienteDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbClienteDTO tbClienteDTO = new TbClienteDTO();

			tbClienteDTO.setIdCliente(rs.getInt("c.id_cliente"));
			tbClienteDTO.setClienteNome(rs.getString("c.tb_cliente_nome"));
			tbClienteDTO.setClienteSenha(rs.getInt("c.tb_cliente_senha"));
			tbClienteDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			tbClienteDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
	
	return tbClienteDTO;
	 
		});
	}
	
	
	@Override
	public List<TbClienteDTO> getAllTbClientes() {
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ON i.id_agencia = c.tb_agencia_id_agencia order by c.tb_cliente_nome ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbClienteDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			
			
			TbClienteDTO tbClienteDTO = new TbClienteDTO();

			tbClienteDTO.setIdCliente(rs.getInt("c.id_cliente"));
			tbClienteDTO.setClienteNome(rs.getString("c.tb_cliente_nome"));
			tbClienteDTO.setClienteSenha(rs.getInt("c.tb_cliente_senha"));
			tbClienteDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			tbClienteDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
						
			return tbClienteDTO;

		});
	}
		
	 
	public TbClienteDTO getTbClienteById(int id) {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  ON i.id_agencia = c.tb_agencia_id_agencia  ")
		.append(" WHERE c.id_cliente = :idCliente ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idCliente", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbCliente(int id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_cliente "); 
	    sql.append(" WHERE id_cliente = :idCliente");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idCliente", id);

			try{
		    	 jdbcTemplate.update(sql.toString(), params);
		         
		     }catch (Exception e){
		    	 System.out.println("-----------------ERRO NO DELETE DO CLIENTE-------------------------------" + e.toString());
		        
		     }
	}


	
}
