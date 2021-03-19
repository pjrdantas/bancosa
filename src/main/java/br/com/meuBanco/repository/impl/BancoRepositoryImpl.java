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

import br.com.meuBanco.dto.BancoDTO;
import br.com.meuBanco.repository.BancoRepository;

@Transactional
@Repository
public class BancoRepositoryImpl implements BancoRepository {

	private static final Logger log = LoggerFactory.getLogger(BancoRepositoryImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public void addBanco(BancoDTO bancoDTO) throws Exception, Throwable {
		log.info("----> BancoRepositoryImpl.addBanco");

		StringBuilder sql = new StringBuilder();
		sql.append("  INSERT INTO ");
		sql.append("  tb_banco (");
		sql.append("  id_banco, ");
		sql.append("  tb_banco_codigo, ");
		sql.append("  tb_banco_nome) ");
		sql.append("  values (:idBanco, :tbBancoCodigo, :tbBancoNome)");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idBanco", bancoDTO.getIdBanco())
				.addValue("tbBancoCodigo", bancoDTO.getBancoCodigo()).addValue("tbBancoNome", bancoDTO.getBancoNome());
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO INSERT DO BANCO", e.toString());
		}
	}

	@Override
	public void updateBanco(BancoDTO bancoDTO) throws Exception, Throwable {
		log.info("----> BancoRepositoryImpl.updateBanco");

		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE tb_banco ");
		sql.append(" SET  ");
		sql.append(" tb_banco_codigo = :tbBancoCodigo, ");
		sql.append(" tb_banco_nome = :tbBancoNome ");
		sql.append(" WHERE id_banco = :idBanco");
		SqlParameterSource params = new MapSqlParameterSource().addValue("tbBancoCodigo", bancoDTO.getBancoCodigo())
				.addValue("tbBancoNome", bancoDTO.getBancoNome()).addValue("idBanco", bancoDTO.getIdBanco());
		try {
			jdbcTemplate.update(sql.toString(), params);

		} catch (Exception e) {
			log.error("ERRO NO UPDATE DO BANCO", e.toString());
		}
	}

	final static StringBuilder sqlSelectPrincipal = new StringBuilder().append("  SELECT ").append("  id_banco ")
			.append("  ,tb_banco_codigo").append("  ,tb_banco_nome").append("  FROM tb_banco ");

	private List<BancoDTO> devolveListaObjetos(StringBuilder sql, SqlParameterSource params)
			throws Exception, Throwable {
		return jdbcTemplate.query(sql.toString(), params, (rs, i) -> {

			BancoDTO bancoDTO = new BancoDTO();
			bancoDTO.setIdBanco(rs.getInt("id_banco"));
			bancoDTO.setBancoCodigo(rs.getInt("tb_banco_codigo"));
			bancoDTO.setBancoNome(rs.getString("tb_banco_nome"));
			return bancoDTO;
		});
	}

	@Override
	public List<BancoDTO> getAllBancos() throws Exception, Throwable {
		log.info("----> BancoRepositoryImpl.getAllBancos");

		StringBuilder sql = new StringBuilder(sqlSelectPrincipal).append("  ORDER BY  tb_banco_codigo ");
		return devolveListaObjetos(sql, null);
	}

	private BancoDTO devolveObjeto(StringBuilder sql, SqlParameterSource params) throws Exception, Throwable {
		return jdbcTemplate.queryForObject(sql.toString(), params, (rs, i) -> {

			BancoDTO bancoDTO = new BancoDTO();
			bancoDTO.setIdBanco(rs.getInt("id_banco"));
			bancoDTO.setBancoCodigo(rs.getInt("tb_banco_codigo"));
			bancoDTO.setBancoNome(rs.getString("tb_banco_nome"));
			return bancoDTO;
		});
	}

	@Override
	public BancoDTO getBancoById(int id) throws Exception, Throwable {
		log.info("----> BancoRepositoryImpl.getBancoById");

		StringBuilder sql = new StringBuilder(sqlSelectPrincipal);
		sql.append("  WHERE id_banco = :idBanco ");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idBanco", id);
		return devolveObjeto(sql, params);
	}

	@Override
	public void deleteBanco(int id) throws Exception, Throwable {
		log.info("----> BancoRepositoryImpl.deleteBanco");

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM ");
		sql.append(" tb_banco ");
		sql.append(" WHERE id_banco = :idBanco");
		SqlParameterSource params = new MapSqlParameterSource().addValue("idBanco", id);
		try {
			jdbcTemplate.update(sql.toString(), params);
		} catch (Exception e) {
			log.error("ERRO NO DELETE DO BANCO", e.toString());
		}
	}

}
