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

import br.com.meuBanco.dto.ClienteDTO;
import br.com.meuBanco.repository.ClienteRepository;

@Transactional
@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

	private static final Logger log = LoggerFactory.getLogger(ClienteRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void addCliente(ClienteDTO clienteDTO) throws Exception, Throwable {
		log.info("----> ClienteRepositoryImpl.addCliente");

		StringBuilder sql = new StringBuilder();

		sql.append("  INSERT INTO ");
		sql.append("  tb_cliente (");
		sql.append("  id_cliente, ");
		sql.append("  tb_cliente_nome, ");
		sql.append("  tb_agencia_id_agencia) ");
		sql.append("  values (:idCliente, :tbClienteNome, :tbAgencia)");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idCliente", clienteDTO.getIdCliente())
				.addValue("tbClienteNome", clienteDTO.getClienteNome())
				.addValue("tbAgencia", clienteDTO.getClienteIAgencia());
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO INSERT DO CLIENTE", e.toString());
		}
	}

	@Override
	public void updateCliente(ClienteDTO clienteDTO) throws Exception, Throwable {
		log.info("----> ClienteRepositoryImpl.updateCliente");

		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE tb_cliente ");
		sql.append(" SET  ");
		sql.append(" tb_cliente_nome = :tbClienteNome, ");
		sql.append(" tb_agencia_id_agencia = :tbAgencia ");
		sql.append(" WHERE id_cliente = :idCliente");
		SqlParameterSource params = new MapSqlParameterSource().addValue("tbClienteNome", clienteDTO.getClienteNome())
				.addValue("tbAgencia", clienteDTO.getClienteIAgencia())
				.addValue("idCliente", clienteDTO.getIdCliente());
		try {
			jdbcTemplate.update(sql.toString(), params);

		} catch (Exception e) {
			log.error("ERRO NO UPDATE DO CLIENTE", e.toString());
		}
	}

	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append("  SELECT DISTINCT ")
			.append("  c.id_cliente").append("  ,c.tb_cliente_nome").append("  ,i.id_agencia")
			.append("  ,i.tb_agencia_codigo").append("  ,i.tb_agencia_digito")
			.append("  FROM tb_cliente c INNER JOIN tb_agencia i ");

	private List<ClienteDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)
			throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {

			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdCliente(rs.getInt("c.id_cliente"));
			clienteDTO.setClienteNome(rs.getString("c.tb_cliente_nome"));
			clienteDTO.setClienteIAgencia(rs.getInt("i.id_agencia"));
			clienteDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			clienteDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			return clienteDTO;
		});
	}

	@Override
	public List<ClienteDTO> getAllClientes() throws Exception, Throwable {
		log.info("----> ClienteRepositoryImpl.getAllClientes");
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal)
				.append("  ON i.id_agencia = c.tb_agencia_id_agencia order by c.tb_cliente_nome ");
		return devolveListaObjetos(sql, null);
	}

	private ClienteDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {

			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdCliente(rs.getInt("c.id_cliente"));
			clienteDTO.setClienteNome(rs.getString("c.tb_cliente_nome"));
			clienteDTO.setClienteIAgencia(rs.getInt("i.id_agencia"));
			clienteDTO.setAgenciaCodigo(rs.getInt("i.tb_agencia_codigo"));
			clienteDTO.setAgenciaDigito(rs.getString("i.tb_agencia_digito"));
			return clienteDTO;
		});
	}

	public ClienteDTO getClienteById(int id) throws Exception, Throwable {
		log.info("----> ClienteRepositoryImpl.getClienteById");
		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
		sql.append("  ON i.id_agencia = c.tb_agencia_id_agencia  ").append(" WHERE c.id_cliente = :idCliente ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idCliente", id);
		return devolveObjeto(sql, params);
	}

	@Override
	public void deleteCliente(int id) throws Exception, Throwable {
		log.info("----> ClienteRepositoryImpl.deleteCliente");
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM ");
		sql.append(" tb_cliente ");
		sql.append(" WHERE id_cliente = :idCliente");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idCliente", id);
		try {
			jdbcTemplate.update(sql.toString(), params);

		} catch (Exception e) {
			log.error("ERRO NO DELETE DO CLIENTE", e.toString());
		}
	}
}
