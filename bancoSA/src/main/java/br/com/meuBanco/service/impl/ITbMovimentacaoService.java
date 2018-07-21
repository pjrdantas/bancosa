package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;





public interface ITbMovimentacaoService {
	
	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 * @param tbMovimentacaoDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable ;    
    
    /**
     * BUSCA O SALDO DA CONTA
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    TbMovimentacaoDTO getTbMovimentacaoByConta(int idConta)  throws Exception, Throwable;
    
    /**
     * VERIFICA SE EXISTE MOVIMENTO POR CONTA
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    boolean existeMovimentoPorConta(int idConta)  throws Exception, Throwable;
    
    /**
     * 
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<TbMovimentacaoDTO> consultar(int idConta)  throws Exception, Throwable ;

}
