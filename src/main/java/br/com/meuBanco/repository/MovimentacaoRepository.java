package br.com.meuBanco.repository;


import java.util.List;

import br.com.meuBanco.dto.ExtratoDTO;
import br.com.meuBanco.dto.MovimentacaoDTO;





public interface MovimentacaoRepository {
	
    /**
     * GRAVA MOVIMENTAÇÃO POR CONTA
     * @param tbMovimentacaoDTO
     * @throws Exception
     * @throws Throwable
     */
    void addMovimentacaoDTO(MovimentacaoDTO vovimentacaoDTO)  throws Exception, Throwable ;
    
    /**
     * LISTA MOVIMENTAÇÃO POR CONTA (EXTRATO POR CONTA)
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<ExtratoDTO> getAllMovimentacaos(int idConta)  throws Exception, Throwable ;
    
    
    /**
     * BUSCA O SALDO DA CONTA
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    MovimentacaoDTO getMovimentacaoByConta(int idConta)  throws Exception, Throwable;
    
    /**
     * VERIFICA SE EXISTE MOVIMENTO POR CONTA
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    boolean existeMovimentoPorConta(int idConta)  throws Exception, Throwable;
   
    
    
   
}
 