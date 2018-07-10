package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbMovimentacao;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;





public interface ITbMovimentacaoService {
	
    void addTbMovimentacao(TbMovimentacao tbMovimentacao);
    void updateTbMovimentacao(TbMovimentacao tbMovimentacao);	
    List<TbMovimentacaoDTO> consultar();
    TbMovimentacaoDTO getTbMovimentacaoById(int id);
    void deleteTbMovimentacao(int id);
}
