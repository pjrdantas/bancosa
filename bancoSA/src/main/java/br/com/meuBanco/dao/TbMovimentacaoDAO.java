package br.com.meuBanco.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbMovimentacaoDAO;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;


@Transactional
@Repository
public class TbMovimentacaoDAO implements ItbMovimentacaoDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	Date data;
	
	@Override
	public void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable  {
	
		System.out.println("-------------------------------------------------------------------"
				+tbMovimentacaoDTO.getIdMovimentacao()+" "
				+tbMovimentacaoDTO.getMovimentacaoCredito()+" "
				+tbMovimentacaoDTO.getMovimentacaoData()+" "
				+tbMovimentacaoDTO.getMovimentacaoDebito()+" "
				+tbMovimentacaoDTO.getMovimentacaoIdConta());

		
		StringBuilder sql = new StringBuilder();
        if (tbMovimentacaoDTO.getMovimentacaoCredito() == null) {
        	tbMovimentacaoDTO.setMovimentacaoCredito(BigDecimal.ZERO);       	
        }
        
        if (tbMovimentacaoDTO.getMovimentacaoDebito() == null) {
        	tbMovimentacaoDTO.setMovimentacaoDebito(BigDecimal.ZERO);
        }
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_movimentacao (");
		sql.append( "  id_movimentacao, ");
		sql.append( "  tb_movimentacao_credito, ");
		sql.append( "  tb_movimentacao_data, ");
		sql.append( "  tb_movimentacao_debito, ");
		sql.append( "  tb_conta_id_conta) ");
		sql.append( "  values (:idMovimentacao, :tbMovimentacaoCredito, :tbMovimentacaoData, :tbMovimentacaoDebito, :tbConta)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idMovimentacao", tbMovimentacaoDTO.getIdMovimentacao())
				.addValue("tbMovimentacaoCredito", tbMovimentacaoDTO.getMovimentacaoCredito())
				.addValue("tbMovimentacaoData", new Date())
				.addValue("tbMovimentacaoDebito", tbMovimentacaoDTO.getMovimentacaoDebito())
				.addValue("tbConta", tbMovimentacaoDTO.getMovimentacaoIdConta());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DO MOVIMENTO-------------------------------" + e.toString());
	        
	     }
								
	}
	
	

	@Override
	public void updateTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_movimentacao ");
		sql.append(" SET  ");
		sql.append(" tb_movimentacao_credito = :tbMovimentacaoCredito, ");
		sql.append(" tb_movimentacao_debito = :tbMovimentacaoDebito, ");
		sql.append(" tb_conta_id_conta = :tbConta ");
		sql.append(" WHERE id_movimentacao = :idMovimentacao");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idMovimentacao", tbMovimentacaoDTO.getIdMovimentacao())
				.addValue("tbMovimentacaoCredito", tbMovimentacaoDTO.getMovimentacaoCredito())
				.addValue("tbMovimentacaoData", new Date())
				.addValue("tbMovimentacaoDebito", tbMovimentacaoDTO.getMovimentacaoDebito())
				.addValue("tbConta", tbMovimentacaoDTO.getMovimentacaoIdConta());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO UPDATE DO MOVIMENTO-------------------------------" + e.toString());
	        
	     }
	}

	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_movimentacao")
			.append("  ,c.tb_movimentacao_credito")
			.append("  ,c.tb_movimentacao_data")
			.append("  ,c.tb_movimentacao_debito")
			.append("  ,i.id_conta")
			.append("  ,i.tb_conta_digito")
			.append("  ,i.tb_conta_numero")
			.append("  FROM tb_movimentacao c INNER JOIN tb_conta i");
			
		
	private List<TbMovimentacaoDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();

			tbMovimentacaoDTO.setIdMovimentacao(rs.getInt("c.id_movimentacao"));
			tbMovimentacaoDTO.setMovimentacaoCredito(rs.getBigDecimal("c.tb_movimentacao_credito"));
			data = rs.getTimestamp("c.tb_movimentacao_data");
			formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			tbMovimentacaoDTO.setMovimentacaoData(formato.format(data));
			tbMovimentacaoDTO.setMovimentacaoDebito(rs.getBigDecimal("c.tb_movimentacao_debito"));
			tbMovimentacaoDTO.setMovimentacaoIdConta(rs.getInt("i.id_conta"));
			tbMovimentacaoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			tbMovimentacaoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
	
	return tbMovimentacaoDTO;
	 
		});
	}
	
	
	@Override
	public List<TbMovimentacaoDTO> getAllTbMovimentacaos()  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ON i.id_conta = c.tb_conta_id_conta order by i.tb_conta_numero, i.tb_conta_digito, c.tb_movimentacao_data ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbMovimentacaoDTO devolveObjeto(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();

			tbMovimentacaoDTO.setIdMovimentacao(rs.getInt("c.id_movimentacao"));
			tbMovimentacaoDTO.setMovimentacaoCredito(rs.getBigDecimal("c.tb_movimentacao_credito"));
			data = rs.getTimestamp("c.tb_movimentacao_data");
			formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			tbMovimentacaoDTO.setMovimentacaoData(formato.format(data));
			tbMovimentacaoDTO.setMovimentacaoDebito(rs.getBigDecimal("c.tb_movimentacao_debito"));
			tbMovimentacaoDTO.setMovimentacaoIdConta(rs.getInt("c.id_conta"));
			tbMovimentacaoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			tbMovimentacaoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
			
			
			return tbMovimentacaoDTO;

		});
	}
		
	 
	public TbMovimentacaoDTO getTbMovimentacaoById(int id)  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  ON i.id_conta = c.tb_conta_id_conta  ")
		.append(" WHERE c.id_movimentacao = :idMovimentacao ");
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("idMovimentacao", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbMovimentacao(int id)  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_movimentacao "); 
	    sql.append(" WHERE id_movimentacao = :idMovimentacao");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idMovimentacao", id);

			try{
		    	 jdbcTemplate.update(sql.toString(), params);		         
		     }catch (Exception e){
		    	 System.out.println("-----------------ERRO NO DELETE DO MOVIMENTO-------------------------------" + e.toString());
		        
		     }
	}


	
}
