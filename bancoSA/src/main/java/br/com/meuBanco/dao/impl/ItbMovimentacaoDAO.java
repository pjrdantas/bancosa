package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;



public interface ItbMovimentacaoDAO {
	
    /**
     * GRAVA MOVIMENTAÇÃO POR CONTA
     * @param tbMovimentacaoDTO
     * @throws Exception
     * @throws Throwable
     */
    void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable ;
    
    /**
     * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<TbMovimentacaoDTO> getAllTbMovimentacaos(int idConta)  throws Exception, Throwable ;
    
    
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
   
    
    
   
}
 