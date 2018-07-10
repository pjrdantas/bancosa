package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbMovimentacaoDAO;
import br.com.meuBanco.entity.TbMovimentacao;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;
import br.com.meuBanco.service.impl.ITbMovimentacaoService;







@Service
public class TbMovimentacaoService  implements ITbMovimentacaoService {
	
	
	@Autowired
	private ItbMovimentacaoDAO tbMovimentacaoDAO;


	@Override
	public void addTbMovimentacao(TbMovimentacao tbMovimentacao) {
		tbMovimentacaoDAO.addTbMovimentacao(tbMovimentacao);
		
	}
	

	 
	@Override
	public void updateTbMovimentacao(TbMovimentacao tbMovimentacao) {
		tbMovimentacaoDAO.updateTbMovimentacao(tbMovimentacao);
	}
	

	@Override
	public List<TbMovimentacaoDTO> consultar(){
		return tbMovimentacaoDAO.getAllTbMovimentacaos();
	}

	
	
	@Override
	public TbMovimentacaoDTO getTbMovimentacaoById(int id) {
		TbMovimentacaoDTO obj = tbMovimentacaoDAO.getTbMovimentacaoById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbMovimentacao(int id) {
		tbMovimentacaoDAO.deleteTbMovimentacao(id);
	}

  

}
