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
import br.com.meuBanco.entity.dto.ExtratoDTO;
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
	int resultado;
	int codigo;
	String mensagem;
	String mensagemNota = "";

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
			if (cont == true) {
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
						if (resultado > 0) {
							mensagem = "VALOR NÃO É POSSIVEL PARA SAQUE";
							// VALOR DO DEBITO É MULTIPLO DE 10
						} else {
							tbMovimentacaoDTO.setMovimentacaoSaldo(
									obj.getMovimentacaoSaldo().subtract(tbMovimentacaoDTO.getMovimentacaoDebito()));
							contaNotas(tbMovimentacaoDTO.getMovimentacaoDebito());
						}
					}
				}
				// NÃO EXISTE MOVIMENTO
			} else if (cont == false) {
				if (tbMovimentacaoDTO.getMovimentacaoDebito() == null) {
					tbMovimentacaoDTO.setMovimentacaoSaldo(
							tbMovimentacaoDTO.getMovimentacaoCredito());
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
	 * 
	 * @param debito
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	public String contaNotas(BigDecimal debito) throws Exception, Throwable {
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
				
				mensagemNota = mensagemNota + "Entregar " + c100 +" notas de R$100,00";
			} else {				
				mensagemNota = mensagemNota + "Entregar " + c100 +" nota  de R$100,00";
			}			
		}
		if (c50 > 0) {
			if (c50 > 1) {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c50+" notas de R$50,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c50+" notas de R$50,00";
				}				
			} else {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c50+" nota de R$50,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c50+" nota de R$50,00";
				}
				
			}			
		}
		if (c20 > 0) {
			if (c20 > 1) {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c20+" notas de R$20,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c20+" notas de R$20,00";
				}				
			} else {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c20+" nota de R$20,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c20+" nota de R$20,00";
				}				
			}			
		}
		if (c10 > 0) {
			if (c10 > 1) {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c10+" notas de R$10,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c10+" notas de R$10,00";
				}				
			}else {
				if (mensagemNota != "") {
					mensagemNota = mensagemNota + ", " + c10+" nota de R$10,00";
				} else {
					mensagemNota = mensagemNota + "Entregar " + c10+" nota de R$10,00";
				}				
			}			
		}
		

			
		 
		return mensagemNota;
	}

	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/movimentacao/{idConta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ExtratoDTO> consultar(@PathVariable("idConta") String idConta)
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
	public int debitoMultiploDeDez(BigDecimal debito) throws Exception, Throwable {
		int entrada = Integer.valueOf(debito.toString());
		int divisor =10;

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
