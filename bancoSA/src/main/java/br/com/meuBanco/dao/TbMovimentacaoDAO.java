package br.com.meuBanco.dao;

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
	

		
		StringBuilder sql = new StringBuilder();
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_movimentacao (");
		sql.append( "  id_movimentacao, ");
		sql.append( "  tb_movimentacao_credito, ");
		sql.append( "  tb_movimentacao_data, ");
		sql.append( "  tb_movimentacao_debito, ");
		sql.append( "  tb_movimentacao_saldo, ");
		sql.append( "  tb_conta_id_conta) ");
		sql.append( "  values (:idMovimentacao, :tbMovimentacaoCredito, :tbMovimentacaoData, :tbMovimentacaoDebito, :tbMovimentacaoSaldo, :tbConta)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idMovimentacao", tbMovimentacaoDTO.getIdMovimentacao())
				.addValue("tbMovimentacaoCredito", tbMovimentacaoDTO.getMovimentacaoCredito())
				.addValue("tbMovimentacaoData", new Date())
				.addValue("tbMovimentacaoDebito", tbMovimentacaoDTO.getMovimentacaoDebito())
				.addValue("tbMovimentacaoSaldo", tbMovimentacaoDTO.getMovimentacaoSaldo())
				.addValue("tbConta", tbMovimentacaoDTO.getMovimentacaoIdConta());
		
		try{
	    	 jdbcTemplate.update(sql.toString(), params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DO MOVIMENTO-------------------------------" + e.toString());	
	    	 jdbcTemplate.update(sql.toString(), params);
	     }
								
	}
	
	


	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_movimentacao")
			.append("  ,c.tb_movimentacao_credito")
			.append("  ,c.tb_movimentacao_data")
			.append("  ,c.tb_movimentacao_debito")
			.append("  ,c.tb_movimentacao_saldo")
			.append("  ,i.id_conta")
			.append("  ,i.tb_conta_digito")
			.append("  ,i.tb_conta_numero")
			.append("  FROM tb_movimentacao c ");
					
	private List<TbMovimentacaoDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();

			tbMovimentacaoDTO.setIdMovimentacao(rs.getInt("c.id_movimentacao"));
			tbMovimentacaoDTO.setMovimentacaoCredito(rs.getBigDecimal("c.tb_movimentacao_credito"));
			data = rs.getTimestamp("c.tb_movimentacao_data");
			formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			tbMovimentacaoDTO.setMovimentacaoData(formato.format(data));
			tbMovimentacaoDTO.setMovimentacaoDebito(rs.getBigDecimal("c.tb_movimentacao_debito"));
			tbMovimentacaoDTO.setMovimentacaoSaldo(rs.getBigDecimal("c.tb_movimentacao_saldo"));
			tbMovimentacaoDTO.setMovimentacaoIdConta(rs.getInt("i.id_conta"));
			tbMovimentacaoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			tbMovimentacaoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
	
	return tbMovimentacaoDTO;
	 
		});
	}
	
	
	@Override
	public List<TbMovimentacaoDTO> getAllTbMovimentacaos()  throws Exception, Throwable  {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append(" INNER JOIN tb_conta i ON i.id_conta = c.tb_conta_id_conta order by i.tb_conta_numero, i.tb_conta_digito, c.tb_movimentacao_data ");
		
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
			tbMovimentacaoDTO.setMovimentacaoSaldo(rs.getBigDecimal("c.tb_movimentacao_saldo"));
			tbMovimentacaoDTO.setMovimentacaoIdConta(rs.getInt("i.id_conta"));
			tbMovimentacaoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			tbMovimentacaoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
						
			return tbMovimentacaoDTO;

		});
	}
		
	 
	public TbMovimentacaoDTO getTbMovimentacaoById(int id)  throws Exception, Throwable  {
				
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append(" INNER JOIN tb_conta i ON i.id_conta = c.tb_conta_id_conta  ")
		.append(" WHERE c.id_movimentacao = :idMovimentacao ");
		
		SqlParameterSource params = new MapSqlParameterSource().addValue("idMovimentacao", id);
				
		try{
			return devolveObjeto(sql, params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NO INSERT DO MOVIMENTO-------------------------------" + e.toString());	
	    	 return devolveObjeto(sql, params);
	     }		
	}
	
	
	
	
	final static StringBuilder sqlSelectSaldo = new StringBuilder()
			
			.append("  SELECT ")
			.append("  MAX(tb_movimentacao_saldo) ,tb_conta_id_conta ")	
			.append("  FROM tb_movimentacao ");
	
	private TbMovimentacaoDTO devolveSaldo(StringBuilder sql, SqlParameterSource params)  throws Exception, Throwable  {
				
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
						
			TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();

			tbMovimentacaoDTO.setMovimentacaoSaldo(rs.getBigDecimal("MAX(tb_movimentacao_saldo)"));
			tbMovimentacaoDTO.setMovimentacaoIdConta(rs.getInt("tb_conta_id_conta"));
						
			return tbMovimentacaoDTO;

		});
	}
	
	
	
	
	public TbMovimentacaoDTO getTbMovimentacaoByConta(int idConta)  throws Exception, Throwable  {
				
		StringBuilder sql = new StringBuilder(sqlSelectSaldo);	
		sql.append(" WHERE tb_conta_id_conta = :idConta ")
		   .append(" GROUP BY tb_conta_id_conta");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", idConta);
		
		try{
			return devolveSaldo(sql, params);	         
	     }catch (Exception e){
	    	 System.out.println("-----------------ERRO NA COLSULTA DO SALDO DO MOVIMENTO-------------------------------" + e.toString());	
	    	 return devolveSaldo(sql, params);
	     }		
	}
	
	 

	
	
	
    public boolean existeMovimentoPorConta(int idConta) {

        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT COUNT(*) AS id_movimentacao");
        sql.append(" FROM tb_movimentacao ");	        
        sql.append(" WHERE tb_conta_id_conta = :idConta ");	        

        SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", idConta);

        return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
        	
        	TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();
        	
        	tbMovimentacaoDTO.setIdMovimentacao(rs.getInt("id_movimentacao"));

        	Boolean qtdeMovimento;
        	
        	if (tbMovimentacaoDTO.getIdMovimentacao() <= 0) {
        		qtdeMovimento = rs.wasNull();
        	} else {
        		qtdeMovimento = true;
        	}
        	
        	
        	try {
        		return qtdeMovimento;
        	} catch (Exception e){
        	
        	return qtdeMovimento;
        	}
        });
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
		    	 jdbcTemplate.update(sql.toString(), params);
		     }
	}


	
}
