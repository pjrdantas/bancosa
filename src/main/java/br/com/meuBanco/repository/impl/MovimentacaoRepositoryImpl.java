package br.com.meuBanco.repository.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dto.ExtratoDTO;
import br.com.meuBanco.dto.MovimentacaoDTO;
import br.com.meuBanco.repository.MovimentacaoRepository;

@Transactional
@Repository
public class MovimentacaoRepositoryImpl implements MovimentacaoRepository {

	private static final Logger log = LoggerFactory.getLogger(MovimentacaoRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	Date data;

	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 */
	@Override
	public void addMovimentacaoDTO(MovimentacaoDTO movimentacaoDTO) throws Exception, Throwable {
		log.info("----> MovimentacaoRepositoryImpl.addMovimentacaoDTO");

		StringBuilder sql = new StringBuilder();
		sql.append("  INSERT INTO ");
		sql.append("  tb_movimentacao (");
		sql.append("  id_movimentacao, ");
		sql.append("  tb_movimentacao_credito, ");
		sql.append("  tb_movimentacao_data, ");
		sql.append("  tb_movimentacao_debito, ");
		sql.append("  tb_movimentacao_saldo, ");
		sql.append("  tb_conta_id_conta) ");
		sql.append(
				"  values (:idMovimentacao, :tbMovimentacaoCredito, :tbMovimentacaoData, :tbMovimentacaoDebito, :tbMovimentacaoSaldo, :tbConta)");
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("idMovimentacao", movimentacaoDTO.getIdMovimentacao())
				.addValue("tbMovimentacaoCredito", movimentacaoDTO.getMovimentacaoCredito())
				.addValue("tbMovimentacaoData", new Date())
				.addValue("tbMovimentacaoDebito", movimentacaoDTO.getMovimentacaoDebito())
				.addValue("tbMovimentacaoSaldo", movimentacaoDTO.getMovimentacaoSaldo())
				.addValue("tbConta", movimentacaoDTO.getMovimentacaoIdConta());
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO INSERT DO MOVIMENTO", e.toString());
		}
	}

	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append("  SELECT DISTINCT ")
			.append("  c.id_movimentacao").append("  ,c.tb_movimentacao_credito").append("  ,c.tb_movimentacao_data")
			.append("  ,c.tb_movimentacao_debito").append("  ,c.tb_movimentacao_saldo").append("  ,i.id_conta")
			.append("  ,i.tb_conta_digito").append("  ,i.tb_conta_numero").append("  FROM tb_movimentacao c ");

	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	private List<ExtratoDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)
			throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {
			ExtratoDTO extratoDTO = new ExtratoDTO();
			extratoDTO.setIdMovimentacao(rs.getInt("c.id_movimentacao"));
			if (rs.getBigDecimal("c.tb_movimentacao_credito") != null) {
				extratoDTO.setMovimentacaoCredito(
						"R$ " + (rs.getBigDecimal("c.tb_movimentacao_credito")).toString().replace(".", ","));
			}
			data = rs.getTimestamp("c.tb_movimentacao_data");
			formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			extratoDTO.setMovimentacaoData(formato.format(data));
			if (rs.getBigDecimal("c.tb_movimentacao_debito") != null) {
				extratoDTO.setMovimentacaoDebito(
						"R$ " + (rs.getBigDecimal("c.tb_movimentacao_debito")).toString().replace(".", ","));
			}
			extratoDTO.setMovimentacaoSaldo(
					"R$ " + (rs.getBigDecimal("c.tb_movimentacao_saldo")).toString().replace(".", ","));
			extratoDTO.setMovimentacaoIdConta(rs.getInt("i.id_conta"));
			extratoDTO.setContaDigito(rs.getString("i.tb_conta_digito"));
			extratoDTO.setContaNumero(rs.getInt("i.tb_conta_numero"));
			return extratoDTO;
		});
	}

	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 */
	@Override
	public List<ExtratoDTO> getAllMovimentacaos(int idConta) throws Exception, Throwable {
		log.info("----> MovimentacaoRepositoryImpl.getAllMovimentacaos");

		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)
				.append(" INNER JOIN tb_conta i ON i.id_conta = c.tb_conta_id_conta ")
				.append(" WHERE c.tb_conta_id_conta = :idConta ")
				.append(" ORDER BY i.tb_conta_numero, i.tb_conta_digito, c.tb_movimentacao_data ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", idConta);
		try {
			return devolveListaObjetos(sql, params);
		} catch (Exception e) {
			log.error("ERRO AO LISTAR O EXTRATO POR CONTA", e.toString());
			return devolveListaObjetos(sql, params);
		}
	}

	/**
	 * BUSCA O SALDO DA CONTA
	 */
	final static StringBuilder sqlSelectSaldo = new StringBuilder()

			.append("  SELECT ").append("   id_movimentacao, tb_movimentacao_saldo,  tb_conta_id_conta  ")
			.append("  FROM tb_movimentacao ");

	/**
	 * BUSCA O SALDO DA CONTA
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	private MovimentacaoDTO devolveSaldo(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			MovimentacaoDTO tovimentacaoDTO = new MovimentacaoDTO();
			tovimentacaoDTO.setIdMovimentacao(rs.getInt("id_movimentacao"));
			tovimentacaoDTO.setMovimentacaoSaldo(rs.getBigDecimal("tb_movimentacao_saldo"));
			tovimentacaoDTO.setMovimentacaoIdConta(rs.getInt("tb_conta_id_conta"));
			return tovimentacaoDTO;
		});
	}

	/**
	 * BUSCA O SALDO DA CONTA
	 */
	public MovimentacaoDTO getMovimentacaoByConta(int idConta) throws Exception, Throwable {
		log.info("----> MovimentacaoRepositoryImpl.getMovimentacaoByConta");

		StringBuilder sql = new StringBuilder(sqlSelectSaldo);
		sql.append(" WHERE tb_conta_id_conta = :idConta ").append(" ORDER BY id_movimentacao DESC limit 1");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", idConta);
		try {
			return devolveSaldo(sql, params);
		} catch (Exception e) {
			log.error("ERRO NA COLSULTA DO SALDO DO MOVIMENTO", e.toString());
			return devolveSaldo(sql, params);
		}
	}

	/**
	 * VERIFICA SE EXISTE MOVIMENTO POR CONTA
	 */
	public boolean existeMovimentoPorConta(int idConta) {
		log.info("----> MovimentacaoRepositoryImpl.existeMovimentoPorConta");

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(*) AS id_movimentacao");
		sql.append(" FROM tb_movimentacao ");
		sql.append(" WHERE tb_conta_id_conta = :idConta ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", idConta);
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {
			MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
			movimentacaoDTO.setIdMovimentacao(rs.getInt("id_movimentacao"));
			Boolean qtdeMovimento;
			if (movimentacaoDTO.getIdMovimentacao() <= 0) {
				qtdeMovimento = rs.wasNull();
			} else {
				qtdeMovimento = true;
			}
			try {
				return qtdeMovimento;
			} catch (Exception e) {
				log.error("ERRO AO VERIFICAR SE EXISTE MOVIMENTO", e.toString());
				return qtdeMovimento;
			}
		});
	}

}
