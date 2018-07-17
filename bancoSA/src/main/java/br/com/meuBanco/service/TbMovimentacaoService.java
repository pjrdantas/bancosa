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

	private boolean cont;
	
	private String mensagem;
	
	BigDecimal movimentacaoCredito;
	BigDecimal movimentacaoDebito;
	BigDecimal movimentacaoSaldo;
	int movimentacaoIdConta;

	@Override
	public void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)   throws Exception, Throwable {
		
	
		int idConta = tbMovimentacaoDTO.getMovimentacaoIdConta();
		movimentacaoIdConta = tbMovimentacaoDTO.getIdMovimentacao();
		movimentacaoCredito = tbMovimentacaoDTO.getMovimentacaoCredito();
		movimentacaoDebito = tbMovimentacaoDTO.getMovimentacaoDebito();		
		
		
		movimetaConta(idConta, movimentacaoCredito, movimentacaoDebito);
		

	}
	
	
	
	public void movimetaConta(int idConta, BigDecimal movimentacaoCredito, BigDecimal movimentacaoDebito ) throws Exception, Throwable {
		
		existeMovimentoPorConta(idConta);
		
		// SALDO EXISTE
		if (cont == true) {			
			System.out.println("-----------------SALDO EXISTE");			
			System.out.println("-----------------CREDITO: "+movimentacaoCredito);
			System.out.println("-----------------DEBITO : "+movimentacaoDebito);
			movimentacaoSaldo = obj.getMovimentacaoSaldo();
			System.out.println("-----------------SALDO  : "+movimentacaoSaldo);
			// É UMA MOVIMENTAÇÃO DE CREDITO
			if (movimentacaoCredito != null ) {
				System.out.println("-----------------É UMA MOVIMENTAÇÃO DE CREDITO");
				movimentacaoSaldo = movimentacaoCredito.add(obj.getMovimentacaoSaldo());
				
			// É UMA MOVIMENTAÇÃO DE DÉBITO
			} 
			if (movimentacaoDebito != null ) {
				System.out.println("-----------------É UMA MOVIMENTAÇÃO DE DÉBITO");
				movimentacaoSaldo = movimentacaoDebito.subtract(obj.getMovimentacaoSaldo());
			}
		// SALDO NÃO EXITE
		} else {
			System.out.println("-----------------SALDO NÃO EXITE");
			movimentacaoSaldo = BigDecimal.ZERO;
		}
		
/**
 * 
 * 
 * 
 * 
 * 
 * 	int        idConta        = tbMovimentacaoDTO.getMovimentacaoIdConta();
		int        idMovimentacao = tbMovimentacaoDTO.getIdMovimentacao();
		
		existeMovimentoPorConta(idConta);
		
		if (cont == false) {
			System.out.println("-----------------NÃO EXISTE MOVIMENTO");
		} else {
			System.out.println("-----------------EXISTE MOVIMENTO");
		}
		
		
 		
		getTbMovimentacaoByConta(idConta);		
		
		tbMovimentacaoDTO.setIdMovimentacao(idMovimentacao);
				
		if (tbMovimentacaoDTO.getMovimentacaoCredito() != null || tbMovimentacaoDTO.getMovimentacaoCredito() 
) {
			tbMovimentacaoDTO.setMovimentacaoSaldo(tbMovimentacaoDTO.getMovimentacaoCredito().add(obj.getMovimentacaoSaldo()));	
		} else {		
			tbMovimentacaoDTO.setMovimentacaoSaldo(obj.getMovimentacaoSaldo().subtract(tbMovimentacaoDTO.getMovimentacaoDebito()));
		}		
		
		tbMovimentacaoDAO.addTbMovimentacaoDTO(tbMovimentacaoDTO);		
		 
	}
	
	
	
		@Override
	public String testaSaldo(BigDecimal saldo, BigDecimal debito) throws Exception, Throwable {
		
		if (saldo.compareTo(debito) == 1){
					
			System.out.println("-----SALDO é maior");
			mensagem = null;
									
		} else if(debito.compareTo(saldo) == 1){
						
			System.out.println("-----SALDO INSUFICIENTE PARA SAQUE");
			mensagem = "-----SALDO INSUFICIENTE PARA SAQUE";
		}
				
		return mensagem;
		
		
	}
	
	
	
	
	
	
	
	
	
				if (tbMovimentacaoDTO.getMovimentacaoCredito() != null ) {
				System.out.println("-----ENTREI NESTA MERDA CREDITO:"+tbMovimentacaoDTO.getMovimentacaoCredito());
				tbMovimentacaoDTO.setMovimentacaoSaldo(tbMovimentacaoDTO.getMovimentacaoCredito().add(obj.getMovimentacaoSaldo()));	
				tbMovimentacaoDAO.addTbMovimentacaoDTO(tbMovimentacaoDTO);	
			} 
			
			if (tbMovimentacaoDTO.getMovimentacaoDebito() != null ) {
				System.out.println("-----ENTREI NESTA MERDA DEBITO :"+tbMovimentacaoDTO.getMovimentacaoDebito());				
				tbMovimentacaoDTO.setMovimentacaoSaldo(obj.getMovimentacaoSaldo().subtract(tbMovimentacaoDTO.getMovimentacaoDebito()));
				tbMovimentacaoDAO.addTbMovimentacaoDTO(tbMovimentacaoDTO);												
			}
				

 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
		
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


	
	
	
	
	
	public String getMensagem() {
		return mensagem;
	}



}
