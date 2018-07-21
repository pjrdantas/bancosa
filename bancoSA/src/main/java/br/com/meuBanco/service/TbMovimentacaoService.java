package br.com.meuBanco.service;

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
	
	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 */
	@Override
	public void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)   throws Exception, Throwable {		
		tbMovimentacaoDAO.addTbMovimentacaoDTO(tbMovimentacaoDTO);	
	}
	
	/**
	 * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
	 */
	@Override
	public List<TbMovimentacaoDTO> consultar(int idConta)  throws Exception, Throwable {
		return tbMovimentacaoDAO.getAllTbMovimentacaos(idConta);
	}


	/**
	 * BUSCA O SALDO DA CONTA
	 */
	@Override
	public TbMovimentacaoDTO getTbMovimentacaoByConta(int idConta) throws Exception, Throwable {
		obj = tbMovimentacaoDAO.getTbMovimentacaoByConta(idConta);
		return obj;
	}
	

	/**
	 * VERIFICA SE EXISTE MOVIMENTO POR CONTA
	 */
	@Override
	public boolean existeMovimentoPorConta(int idConta) throws Exception, Throwable {		
		cont = tbMovimentacaoDAO.existeMovimentoPorConta(idConta);		
		return cont;
	}






}
