package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;



public interface ItbMovimentacaoDAO {
	
    
    void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable ;
    List<TbMovimentacaoDTO> getAllTbMovimentacaos()  throws Exception, Throwable ;
    TbMovimentacaoDTO getTbMovimentacaoById(int id)  throws Exception, Throwable ;
    TbMovimentacaoDTO getTbMovimentacaoByConta(int idConta)  throws Exception, Throwable;
    boolean existeMovimentoPorConta(int idConta)  throws Exception, Throwable;
    void deleteTbMovimentacao(int id)  throws Exception, Throwable ;
    
    
   
}
 