package br.com.meuBanco.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dao.impl.ItbMovimentacaoDAO;
import br.com.meuBanco.entity.TbMovimentacao;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;





@Transactional
@Repository
public class TbMovimentacaoDAO implements ItbMovimentacaoDAO {
	
	
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	@Override
	public void addTbMovimentacao(TbMovimentacao tbMovimentacao) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(	"  INSERT INTO ");
		sql.append( "  tb_movimentacao (");
		sql.append( "  id_movimentacao, ");
		sql.append( "  tb_movimenta_credito, ");
		sql.append( "  tb_movimentacao_data, ");
		sql.append( "  tb_movimentacao_debito, ");
		sql.append( "  tb_conta_id_conta) ");
		sql.append( "  values (:idMovimentacao, :tbMovimentaCredito, :tbMovimentacaoData, :tbMovimentacaoDebito, :tbConta)");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idMovimentacao", tbMovimentacao.getIdMovimentacao())
				.addValue("tbMovimentaCredito", tbMovimentacao.getTbMovimentaCredito())
				.addValue("tbMovimentacaoData", tbMovimentacao.getTbMovimentacaoData())
				.addValue("tbMovimentacaoDebito", tbMovimentacao.getTbMovimentacaoDebito())
				.addValue("tbConta", tbMovimentacao.getTbConta().getIdConta());
		
		jdbcTemplate.update(sql.toString(), params);
								
	}
	
	

	@Override
	public void updateTbMovimentacao(TbMovimentacao tbMovimentacao) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" UPDATE tb_movimentacao ");
		sql.append(" SET  ");
		sql.append(" tb_movimenta_credito = :tbMovimentaCredito, ");
		sql.append(" tb_movimentacao_data = :tbMovimentacaoData, ");
		sql.append(" tbMovimentacaoDebito = :tbMovimentacaoDebito, ");
		sql.append(" tb_conta_id_conta = :tbConta ");
		sql.append(" WHERE id_movimentacao = :idMovimentacao");
		
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("tbMovimentaCredito", tbMovimentacao.getTbMovimentaCredito())
				.addValue("tbMovimentacaoData", tbMovimentacao.getTbMovimentacaoData())
				.addValue("tbMovimentacaoDebito", tbMovimentacao.getTbMovimentacaoDebito())
				.addValue("tbConta", tbMovimentacao.getTbConta().getIdConta());
		
		jdbcTemplate.update(sql.toString(), params);
	}

	
	
	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append(
			"  SELECT DISTINCT ")
			.append("  c.id_movimentacao")
			.append("  ,c.tb_movimenta_credito")
			.append("  ,c.tb_movimentacao_data")
			.append("  ,c.tb_movimentacao_debito")
			.append("  ,i.tb_conta_digito")
			.append("  ,i.tb_conta_numero")
			.append("  FROM tb_movimentacao c INNER JOIN tbConta i");
			
		
	private List<TbMovimentacaoDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
		
			TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();

			tbMovimentacaoDTO.setIdMovimentacao(rs.getInt("c.id_movimentacao"));
			tbMovimentacaoDTO.setTbMovimentaCredito(rs.getBigDecimal("c.tb_movimenta_credito"));
			tbMovimentacaoDTO.setTbMovimentacaoData(rs.getDate("c.tb_movimentacao_data") != null ? rs.getDate("c.tb_movimentacao_data").toLocalDate() : null );
			tbMovimentacaoDTO.setTbMovimentacaoDebito(rs.getBigDecimal("c.tb_movimentacao_debito"));
			tbMovimentacaoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			tbMovimentacaoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
	
	return tbMovimentacaoDTO;
	 
		});
	}
	
	
	@Override
	public List<TbMovimentacaoDTO> getAllTbMovimentacaos() {
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)		
		.append("  ON i.id_conta = c.tb_conta_id_conta order by c.tb_movimentacao_data ");
		
		return devolveListaObjetos(sql, null);
	}
	

	
	
	private TbMovimentacaoDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			
			
			TbMovimentacaoDTO tbMovimentacaoDTO = new TbMovimentacaoDTO();

			tbMovimentacaoDTO.setIdMovimentacao(rs.getInt("c.id_movimentacao"));
			tbMovimentacaoDTO.setTbMovimentaCredito(rs.getBigDecimal("c.tb_movimenta_credito"));
			tbMovimentacaoDTO.setTbMovimentacaoData(rs.getDate("c.tb_movimentacao_data") != null ? rs.getDate("c.tb_movimentacao_data").toLocalDate() : null );
			tbMovimentacaoDTO.setTbMovimentacaoDebito(rs.getBigDecimal("c.tb_movimentacao_debito"));
			tbMovimentacaoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			tbMovimentacaoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
			
			
			return tbMovimentacaoDTO;

		});
	}
		
	 
	public TbMovimentacaoDTO getTbMovimentacaoById(int id) {
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);		
		sql.append("  ON i.id_conta = c.tb_conta_id_conta  ")
		.append(" WHERE c.id_movimentacao = :idMovimentacao ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idMovimentacao", id);
		
		return devolveObjeto(sql, params);
		
	}
	
	 



	@Override
	public void deleteTbMovimentacao(int id) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" DELETE FROM ");
	    sql.append(" tb_movimentacao "); 
	    sql.append(" WHERE id_movimentacao = :idMovimentacao");	        

	     SqlParameterSource params = new MapSqlParameterSource().addValue("idMovimentacao", id);

	     jdbcTemplate.update(sql.toString(), params);
	}


	
}
