package br.com.meuBanco.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuBanco.dto.ExtratoDTO;
import br.com.meuBanco.dto.MovimentacaoDTO;
import br.com.meuBanco.repository.MovimentacaoRepository;
import br.com.meuBanco.response.ResponseMessage;
import br.com.meuBanco.service.MovimentacaoService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class MovimentacaoController {

	private static final Logger log = LoggerFactory.getLogger(MovimentacaoController.class);

	@Autowired
	private MovimentacaoService movimentacaoService;

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	boolean cont;
	MovimentacaoDTO obj;
	int resultado;
	int codigo;
	String mensagem;
	String mensagemNota = "";

	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 * 
	 * @param movimentacaoDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/movimentacao", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseMessage addMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO)
			throws Exception, Throwable {
		log.info("----> MovimentacaoController.addMovimentacao");
		try {
			codigo = 1;
			mensagem = null;
			// VERIFICA EXISTENCIA DE MOVIMENTO NA CONTA
			existeMovimentoPorConta(movimentacaoDTO.getMovimentacaoIdConta());
			// EXISTE MOVIMENTO
			if (cont == true) {
				getMovimentacaoByConta(movimentacaoDTO.getMovimentacaoIdConta());
				// É UMA MOVIMENTAÇÃO DE CREDITO
				if (movimentacaoDTO.getMovimentacaoDebito() == null) {
					movimentacaoDTO.setMovimentacaoSaldo(
							movimentacaoDTO.getMovimentacaoCredito().add(obj.getMovimentacaoSaldo()));
					mensagem = null;
					// É UMA MOVIMENTAÇÃO DE DEBITO
				} else {
					// VERIFICA SE SALDO MAIOR QUE DEBITO
					testaSaldo(obj.getMovimentacaoSaldo(), movimentacaoDTO.getMovimentacaoDebito());
					// SALDO MAIOR QUE DEBITO
					if (mensagem == null) {
						// VERFICA SE DEBITO É MULTIPLO DE 10
						debitoMultiploDeDez(movimentacaoDTO.getMovimentacaoDebito());

						// VALOR DO DEBITO NÃO É MULTIPLO DE 10
						if (resultado > 0) {
							mensagem = "VALOR NÃO É POSSIVEL PARA SAQUE";
							// VALOR DO DEBITO É MULTIPLO DE 10
						} else {
							movimentacaoDTO.setMovimentacaoSaldo(
									obj.getMovimentacaoSaldo().subtract(movimentacaoDTO.getMovimentacaoDebito()));
							contaNotas(movimentacaoDTO.getMovimentacaoDebito());
						}
					}
				}
				// NÃO EXISTE MOVIMENTO
			} else if (cont == false) {
				if (movimentacaoDTO.getMovimentacaoDebito() == null) {
					movimentacaoDTO.setMovimentacaoSaldo(movimentacaoDTO.getMovimentacaoCredito());
					mensagem = null;

					// É UMA MOVIMENTAÇÃO DE DEBITO
				} else {
					mensagem = "SALDO INSUFICIENTE PARA SAQUE";
				}
			}
			if (mensagem == null) {
				mensagem = "Operação realizada com sucesso! ";
				if (!mensagemNota.equals("")) {
					mensagem = mensagem + mensagemNota;
					mensagemNota = "";
				}
				this.movimentacaoService.addMovimentacao(movimentacaoDTO);
			}
			obj = null;
			return new ResponseMessage(codigo, mensagem);
		} catch (Exception e) {
			log.error("ERRO NO INSERT DE MOVIMENTAÇÕES", e.toString());
			return new ResponseMessage(0, e.getMessage());
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
		log.info("----> MovimentacaoController.testaSaldo");
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
	 * 
	 * @param debito
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public String contaNotas(BigDecimal debito) throws Exception, Throwable {
		log.info("----> MovimentacaoController.contaNotas");

		mensagemNota = "";
		boolean ok = true;
		BigDecimal valor100 = new BigDecimal("100");
		BigDecimal valor50 = new BigDecimal("50");
		BigDecimal valor20 = new BigDecimal("20");
		BigDecimal valor10 = new BigDecimal("10");
		int c100 = 0;
		int c50 = 0;
		int c20 = 0;
		int c10 = 0;
		while (ok) {

			if (valor100.compareTo(debito) != 1) {
				debito = debito.subtract(valor100);
				c100++;
			} else {
				if (valor50.compareTo(debito) != 1) {
					debito = debito.subtract(valor50);
					c50++;
				} else {
					if (valor20.compareTo(debito) != 1) {
						debito = debito.subtract(valor20);
						c20++;
					} else {
						if (valor10.compareTo(debito) != 1) {
							debito = debito.subtract(valor10);
							c10++;
						} else {
							ok = false;
						}
					}
				}
			}
		}
		if (c100 > 0) {
			if (c100 > 1) {
				mensagemNota = mensagemNota + "Entregar " + c100 + " notas de R$100,00";
			} else {
				mensagemNota = mensagemNota + "Entregar " + c100 + " nota  de R$100,00";
			}
		}
		if (c50 > 0) {
			if (c50 > 1) {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c50 + " notas de R$50,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c50 + " notas de R$50,00";
				}
			} else {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c50 + " nota de R$50,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c50 + " nota de R$50,00";
				}
			}
		}
		if (c20 > 0) {
			if (c20 > 1) {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c20 + " notas de R$20,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c20 + " notas de R$20,00";
				}
			} else {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c20 + " nota de R$20,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c20 + " nota de R$20,00";
				}
			}
		}
		if (c10 > 0) {
			if (c10 > 1) {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c10 + " notas de R$10,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c10 + " notas de R$10,00";
				}
			} else {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c10 + " nota de R$10,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c10 + " nota de R$10,00";
				}
			}
		}
		return mensagemNota;
	}

	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 * 
	 * @param idConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/movimentacao/{idConta}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ExtratoDTO> getContaById(@PathVariable("idConta") String idConta)
			throws Exception, Throwable {
		log.info("----> MovimentacaoController.getContaById");
		int id = Integer.parseInt(idConta);
		return this.movimentacaoService.getContaById(id);
	}

	/**
	 * VERIFICA SE O SAQUE É MULTIPLO DE 10
	 * 
	 * @param debito
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public int debitoMultiploDeDez(BigDecimal debito) throws Exception, Throwable {
		log.info("----> MovimentacaoController.debitoMultiploDeDez");
		int entrada = Integer.valueOf(debito.toString());
		int divisor = 10;
		resultado = (entrada % divisor);
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
		log.info("----> MovimentacaoController.existeMovimentoPorConta");
		cont = movimentacaoRepository.existeMovimentoPorConta(idConta);
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
	public MovimentacaoDTO getMovimentacaoByConta(int idConta) throws Exception, Throwable {
		log.info("----> MovimentacaoController.getMovimentacaoByConta");
		obj = movimentacaoRepository.getMovimentacaoByConta(idConta);
		return obj;
	}

}
