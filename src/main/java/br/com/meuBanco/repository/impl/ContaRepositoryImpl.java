package br.com.meuBanco.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.meuBanco.dto.ContaDTO;
import br.com.meuBanco.repository.ContaRepository;

@Transactional
@Repository
public class ContaRepositoryImpl implements ContaRepository {

	private static final Logger log = LoggerFactory.getLogger(ContaRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void addConta(ContaDTO contaDTO) throws Exception, Throwable {
		log.info("----> ContaRepositoryImpl.addConta");

		StringBuilder sql = new StringBuilder();
		sql.append("  INSERT INTO ");
		sql.append("  tb_conta (");
		sql.append("  id_conta, ");
		sql.append("  tb_conta_digito, ");
		sql.append("  tb_conta_numero, ");
		sql.append("  tb_agencia_id_agencia, ");
		sql.append("  tb_cliente_id_cliente) ");
		sql.append("  values (:idConta, :tbContaDigito, :tbContaNumero, :tbAgencia, :tbCliente)");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", contaDTO.getIdConta())
				.addValue("tbContaDigito", contaDTO.getContaDigito())
				.addValue("tbContaNumero", contaDTO.getContaNumero())
				.addValue("tbAgencia", contaDTO.getContaIdAgencia())
				.addValue("tbCliente", contaDTO.getContaIdCliente());
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO INSERT DA CONTA", e.toString());
		}
	}

	@Override
	public void updateConta(ContaDTO contaDTO) throws Exception, Throwable {
		log.info("----> ContaRepositoryImpl.updateConta");

		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE tb_conta ");
		sql.append(" SET  ");
		sql.append(" tb_conta_digito = :tbContaDigito, ");
		sql.append(" tb_conta_numero = :tbContaNumero, ");
		sql.append(" tb_agencia_id_agencia = :tbAgencia, ");
		sql.append(" tb_cliente_id_cliente = :tbCliente ");
		sql.append(" WHERE id_conta = :idConta");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", contaDTO.getIdConta())
				.addValue("tbContaDigito", contaDTO.getContaDigito())
				.addValue("tbContaNumero", contaDTO.getContaNumero())
				.addValue("tbAgencia", contaDTO.getContaIdAgencia())
				.addValue("tbCliente", contaDTO.getContaIdCliente());
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO UPDATE DA CONTA", e.toString());
		}
	}

	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append("  SELECT DISTINCT ")
			.append("  c.id_conta").append("  ,c.tb_conta_digito").append("  ,c.tb_conta_numero")
			.append("  ,i.id_agencia").append("  ,i.tb_agencia_codigo").append("  ,i.tb_agencia_digito")
			.append("  ,a.id_cliente").append("  ,a.tb_cliente_nome")
			.append("  FROM tb_conta c INNER JOIN tb_agencia i ON i.id_agencia = c.tb_agencia_id_agencia  ")
			.append("                  INNER JOIN tb_cliente a ON a.id_cliente = c.tb_cliente_id_cliente  ");

	private List<ContaDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)
			throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {

			ContaDTO contaDTO = new ContaDTO();
			contaDTO.setIdConta(rs.getInt("c.id_conta"));
			contaDTO.setContaDigito(rs.getInt("c.tb_conta_digito"));
			contaDTO.setContaNumero(rs.getInt("c.tb_conta_numero"));
			contaDTO.setContaIdAgencia(rs.getInt("i.id_agencia"));
			contaDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			contaDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			contaDTO.setContaIdCliente(rs.getInt("a.id_cliente"));
			contaDTO.setClienteNome(rs.getString("a.tb_cliente_nome"));
			return contaDTO;

		});
	}

	@Override
	public List<ContaDTO> getAllContas() throws Exception, Throwable {
		log.info("----> ContaRepositoryImpl.getAllContas");
		
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal).append("  ORDER BY c.tb_cliente_id_cliente  ");
		return devolveListaObjetos(sql, null);
	}

	private ContaDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {

			ContaDTO contaDTO = new ContaDTO();
			contaDTO.setIdConta(rs.getInt("c.id_conta"));
			contaDTO.setContaDigito(rs.getInt("c.tb_conta_digito"));
			contaDTO.setContaNumero(rs.getInt("c.tb_conta_numero"));
			contaDTO.setContaIdAgencia(rs.getInt("i.id_agencia"));
			contaDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			contaDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			contaDTO.setContaIdCliente(rs.getInt("a.id_cliente"));
			contaDTO.setClienteNome(rs.getString("a.tb_cliente_nome"));
			return contaDTO;
		});
	}

	public ContaDTO getContaById(int id) throws Exception, Throwable {
		log.info("----> ContaRepositoryImpl.getContaById");

		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
		sql.append("  WHERE c.id_conta = :idConta ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", id);
		return devolveObjeto(sql, params);
	}

	public ContaDTO getContaByCliente(int id) throws Exception, Throwable {

		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
		sql.append("  WHERE a.id_cliente = :contaIdCliente ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("contaIdCliente", id);
		return devolveObjeto(sql, params);
	}

	@Override
	public void deleteConta(int id) throws Exception, Throwable {
		log.info("----> ContaRepositoryImpl.deleteConta");

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM ");
		sql.append(" tb_conta ");
		sql.append(" WHERE id_conta = :idConta");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idConta", id);
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO DELETE DA CONTA", e.toString());
		}
	}

}
