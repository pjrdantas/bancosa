package br.com.meuBanco.service;

import java.util.List;

import br.com.meuBanco.dto.ExtratoDTO;
import br.com.meuBanco.dto.MovimentacaoDTO;





public interface MovimentacaoService {
	
	/**
	 * GRAVA MOVIMENTAÇÃO POR CONTA
	 * @param tbMovimentacaoDTO
	 * @throws Exception
	 * @throws Throwable
	 */
    void addMovimentacao(MovimentacaoDTO movimentacaoDTO)  throws Exception, Throwable ;    
    
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
    
    /**
     * 
     * @param idConta
     * @return
     * @throws Exception
     * @throws Throwable
     */
    List<ExtratoDTO> getContaById(int idConta)  throws Exception, Throwable ;

}
