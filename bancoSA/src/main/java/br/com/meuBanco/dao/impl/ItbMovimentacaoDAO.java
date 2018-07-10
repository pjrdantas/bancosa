package br.com.meuBanco.dao.impl;


import java.util.List;

import br.com.meuBanco.entity.TbMovimentacao;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;



public interface ItbMovimentacaoDAO {
	
    
    void addTbMovimentacao(TbMovimentacao tbMovimentacao);
    void updateTbMovimentacao(TbMovimentacao tbMovimentacao);
    List<TbMovimentacaoDTO> getAllTbMovimentacaos();
    TbMovimentacaoDTO getTbMovimentacaoById(int id);
    void deleteTbMovimentacao(int id);
    
    
   
}
 