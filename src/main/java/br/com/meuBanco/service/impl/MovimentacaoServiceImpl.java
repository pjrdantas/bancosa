package br.com.meuBanco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dto.ExtratoDTO;
import br.com.meuBanco.dto.MovimentacaoDTO;
import br.com.meuBanco.repository.MovimentacaoRepository;
import br.com.meuBanco.service.MovimentacaoService;



@Service
public class MovimentacaoServiceImpl  implements MovimentacaoService {
	
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	private MovimentacaoDTO obj;
	private boolean cont;	
	
	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 */
	@Override
	public void addMovimentacao(MovimentacaoDTO movimentacaoDTO)   throws Exception, Throwable {		
		movimentacaoRepository.addMovimentacaoDTO(movimentacaoDTO);	
	}
	
	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 */
	@Override
	public List<ExtratoDTO> getContaById(int idConta)  throws Exception, Throwable {
		return movimentacaoRepository.getAllMovimentacaos(idConta);
	}


	/**
	 * BUSCA O SALDO DA CONTA
	 */
	@Override
	public MovimentacaoDTO getMovimentacaoByConta(int idConta) throws Exception, Throwable {
		obj = movimentacaoRepository.getMovimentacaoByConta(idConta);
		return obj;
	}
	

	/**
	 * VERIFICA SE EXISTE MOVIMENTO POR CONTA
	 */
	@Override
	public boolean existeMovimentoPorConta(int idConta) throws Exception, Throwable {		
		cont = movimentacaoRepository.existeMovimentoPorConta(idConta);		
		return cont;
	}






}
