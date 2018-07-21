package br.com.meuBanco.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuBanco.dao.impl.ItbMovimentacaoDAO;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbMovimentacaoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class MovimentacaoController {

	@Autowired
	private ITbMovimentacaoService movimentacaoService;

	@Autowired
	private ItbMovimentacaoDAO tbMovimentacaoDAO;

	boolean cont;
	TbMovimentacaoDTO obj;
	BigDecimal[] resultado;
	int codigo;
	String mensagem;

	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 * 
	 * @param tbMovimentacaoDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/movimentacao", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbMovimentacaoDTO tbMovimentacaoDTO)
			throws Exception, Throwable {

		try {
			codigo = 1;
			mensagem = null;
			// VERIFICA EXISTENCIA DE MOVIMENTO NA CONTA
			existeMovimentoPorConta(tbMovimentacaoDTO.getMovimentacaoIdConta());
			// EXISTE MOVIMENTO
			if (cont) {
				getTbMovimentacaoByConta(tbMovimentacaoDTO.getMovimentacaoIdConta());
				// É UMA MOVIMENTAÇÃO DE CREDITO
				if (tbMovimentacaoDTO.getMovimentacaoDebito() == null) {
					tbMovimentacaoDTO.setMovimentacaoSaldo(
							tbMovimentacaoDTO.getMovimentacaoCredito().add(obj.getMovimentacaoSaldo()));
					mensagem = null;
					// É UMA MOVIMENTAÇÃO DE DEBITO
				} else {
					// VERIFICA SE SALDO MAIOR QUE DEBITO
					testaSaldo(obj.getMovimentacaoSaldo(), tbMovimentacaoDTO.getMovimentacaoDebito());
					// SALDO MAIOR QUE DEBITO
					if (mensagem == null) {
						// VERFICA SE DEBITO É MULTIPLO DE 10
						debitoMultiploDeDez(tbMovimentacaoDTO.getMovimentacaoDebito());
						// VALOR DO DEBITO NÃO É MULTIPLO DE 10
						if (!resultado[1].equals(BigDecimal.ZERO)) {
							mensagem = "VALOR NÃO É POSSIVEL PARA SAQUE";
							// VALOR DO DEBITO É MULTIPLO DE 10
						} else {
							tbMovimentacaoDTO.setMovimentacaoSaldo(
									obj.getMovimentacaoSaldo().subtract(tbMovimentacaoDTO.getMovimentacaoDebito()));
						}
					}
				}
				// NÃO EXISTE MOVIMENTO
			} else {
				if (tbMovimentacaoDTO.getMovimentacaoDebito() == null) {
					tbMovimentacaoDTO.setMovimentacaoSaldo(
							tbMovimentacaoDTO.getMovimentacaoCredito().add(obj.getMovimentacaoSaldo()));
					mensagem = null;
					// É UMA MOVIMENTAÇÃO DE DEBITO
				} else {
					mensagem = "-----SALDO INSUFICIENTE PARA SAQUE";
				}
			}
			if (mensagem == null) {
				mensagem = "saldo salvo com sucesso!";
				this.movimentacaoService.addTbMovimentacaoDTO(tbMovimentacaoDTO);
			}
			obj = null;
			return new ResponseModel(codigo, mensagem);
		} catch (Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * TESTA SE TEM SALDO SUFICIENTE
	 * 
	 * @param saldo
	 * @param debito
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public String testaSaldo(BigDecimal saldo, BigDecimal debito) throws Exception, Throwable {
		mensagem = null;
		if (saldo.compareTo(debito) == 1) {
			mensagem = null;
			// SALDO É MENOR QUE DEBITO
		} else if (debito.compareTo(saldo) == 1) {
			mensagem = "-----SALDO INSUFICIENTE PARA SAQUE";
		}
		return mensagem;
	}

	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/movimentacao/{idConta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbMovimentacaoDTO> consultar(@PathVariable("idConta") String idConta)
			throws Exception, Throwable {
		int id = Integer.parseInt(idConta);
		return this.movimentacaoService.consultar(id);
	}

	/**
	 * VERIFICA SE O SAQUE É MULTIPLO DE 10
	 * 
	 * @param debito
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public BigDecimal[] debitoMultiploDeDez(BigDecimal debito) throws Exception, Throwable {
		BigDecimal dez = new BigDecimal("10.0");
		resultado = debito.divideAndRemainder(dez);

		return resultado;
	}

	/**
	 * VERIFICA SE EXISTE MOVIMENTO POR CONTA
	 * 
	 * @param idConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public boolean existeMovimentoPorConta(int idConta) throws Exception, Throwable {
		cont = tbMovimentacaoDAO.existeMovimentoPorConta(idConta);
		return cont;
	}

	/**
	 * BUSCA O SALDO DA CONTA
	 * 
	 * @param idConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public TbMovimentacaoDTO getTbMovimentacaoByConta(int idConta) throws Exception, Throwable {
		obj = tbMovimentacaoDAO.getTbMovimentacaoByConta(idConta);
		return obj;
	}

}
