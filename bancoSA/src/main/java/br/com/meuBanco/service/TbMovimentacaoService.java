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


	@Override
	public void addTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)   throws Exception, Throwable {
		tbMovimentacaoDAO.addTbMovimentacaoDTO(tbMovimentacaoDTO);
		
	}
	

	 
	@Override
	public void updateTbMovimentacaoDTO(TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable  {
		tbMovimentacaoDAO.updateTbMovimentacaoDTO(tbMovimentacaoDTO);
	}
	

	@Override
	public List<TbMovimentacaoDTO> consultar()  throws Exception, Throwable {
		return tbMovimentacaoDAO.getAllTbMovimentacaos();
	}

	
	
	@Override
	public TbMovimentacaoDTO getTbMovimentacaoById(int id)  throws Exception, Throwable  {
		TbMovimentacaoDTO obj = tbMovimentacaoDAO.getTbMovimentacaoById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbMovimentacao(int id)  throws Exception, Throwable  {
		tbMovimentacaoDAO.deleteTbMovimentacao(id);
	}

  

}
