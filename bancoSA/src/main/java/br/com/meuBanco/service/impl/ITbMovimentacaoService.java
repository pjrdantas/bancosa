package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;





public interface ITbMovimentacaoService {
	
    void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable ;
    void updateTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable ;	
    List<TbMovimentacaoDTO> consultar()  throws Exception, Throwable ;
    TbMovimentacaoDTO getTbMovimentacaoById(int id)  throws Exception, Throwable ;
    void deleteTbMovimentacao(int id)  throws Exception, Throwable ;
}
