package br.com.meuBanco.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbMovimentacaoDAO;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;
import br.com.meuBanco.service.impl.ITbMovimentacaoService;







@Service
public class TbMovimentacaoService  implements ITbMovimentacaoService {
	
	
	@Autowired
	private ItbMovimentacaoDAO tbMovimentacaoDAO;
	private TbMovimentacaoDTO obj;
	
	boolean cont;	
	String mensagem;
	int movimentacaoIdConta;
	String teste;

	@Override
	public void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)   throws Exception, Throwable {
		
		mensagem = null;				
		movimentacaoIdConta = tbMovimentacaoDTO.getIdMovimentacao();
		BigDecimal movimentacaoCredito = tbMovimentacaoDTO.getMovimentacaoCredito();
		BigDecimal movimentacaoDebito = tbMovimentacaoDTO.getMovimentacaoDebito();		
		BigDecimal movimentacaoSaldo;				
		// VERIFICA EXISTENCIA DE MOVIMENTO NA CONTA
		existeMovimentoPorConta(tbMovimentacaoDTO.getMovimentacaoIdConta());						
		// EXISTE MOVIMENTO
		if (cont) {						
			getTbMovimentacaoByConta(tbMovimentacaoDTO.getMovimentacaoIdConta());
			movimentacaoSaldo = obj.getMovimentacaoSaldo();			
			// É UMA MOVIMENTAÇÃO DE CREDITO
			if (  tbMovimentacaoDTO.getMovimentacaoDebito() == null  ) {										
				tbMovimentacaoDTO.setMovimentacaoSaldo(movimentacaoCredito.add(obj.getMovimentacaoSaldo()));	
				mensagem = null;				
			} else {									
				// VERIFICA SE SALDO MAIOR QUE DEBITO
				testaSaldo(movimentacaoSaldo,movimentacaoDebito);												
				// SALDO MAIOR QUE DEBITO
				if (mensagem == null) {					
					// VERFICA SE DEBITO É MULTIPLO DE 10
					debitoMultiploDeDez(movimentacaoDebito);					
					if(!teste.equals("0.0")) {
						mensagem = "VALOR NÃO É POSSIVEL PARA SAQUE";
					} else {
						tbMovimentacaoDTO.setMovimentacaoSaldo(movimentacaoSaldo.subtract(tbMovimentacaoDTO.getMovimentacaoDebito()));
					}					
				// SALDO MENOR QUE DEBITO
				} else {
					System.out.println("-----------------MENSAGEM DIFERENTE DE null");								
				}				
			}				
			movimentacaoIdConta = 0;
			movimentacaoCredito = BigDecimal.ZERO;
			movimentacaoDebito = BigDecimal.ZERO;
			movimentacaoSaldo = BigDecimal.ZERO; 			 			
			if (mensagem == null) {				
				tbMovimentacaoDAO.addTbMovimentacaoDTO(tbMovimentacaoDTO);	
			}			
		} 			
	}
	
	
	
	// TESTA SE TEM SALDO SUFICIENTE
	public String testaSaldo(BigDecimal saldo, BigDecimal debito) throws Exception, Throwable {
		mensagem = null;
		if (saldo.compareTo(debito) == 1){					
			mensagem = null;									
		} else if (debito.compareTo(saldo) == 1){						
			mensagem = "-----SALDO INSUFICIENTE PARA SAQUE";
		}				
		return mensagem;		
	}

		
	// VERIFICA SE O SAQUE É MULTIPLO DE 10
	public String debitoMultiploDeDez(BigDecimal debito) {
		BigDecimal dez = new BigDecimal("10.0");
		BigDecimal[] resultado = debito.divideAndRemainder(dez);
		teste = resultado[1].toString();
		return teste;		
	}
	
	
	
	
	
	
	
	

	

	@Override
	public List<TbMovimentacaoDTO> consultar()  throws Exception, Throwable {
		return tbMovimentacaoDAO.getAllTbMovimentacaos();
	}

	
	@Override
	public TbMovimentacaoDTO getTbMovimentacaoById(int id)  throws Exception, Throwable  {
		obj = tbMovimentacaoDAO.getTbMovimentacaoById(id);
		return obj;
	}	
	

	@Override
	public TbMovimentacaoDTO getTbMovimentacaoByConta(int idConta) throws Exception, Throwable {
		obj = tbMovimentacaoDAO.getTbMovimentacaoByConta(idConta);
		return obj;
	}
	
	

	
	@Override
	public void deleteTbMovimentacao(int id)  throws Exception, Throwable  {
		tbMovimentacaoDAO.deleteTbMovimentacao(id);
	}



	@Override
	public boolean existeMovimentoPorConta(int idConta) throws Exception, Throwable {		
		cont = tbMovimentacaoDAO.existeMovimentoPorConta(idConta);		
		return cont;
	}






}
