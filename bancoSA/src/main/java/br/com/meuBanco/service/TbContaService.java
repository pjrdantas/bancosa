package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbContaDAO;
import br.com.meuBanco.entity.TbConta;
import br.com.meuBanco.entity.dto.TbContaDTO;
import br.com.meuBanco.service.impl.ITbContaService;







@Service
public class TbContaService  implements ITbContaService {
	
	
	@Autowired
	private ItbContaDAO tbContaDAO;


	@Override
	public void addTbConta(TbConta tbConta) {
		tbContaDAO.addTbConta(tbConta);
		
	}
	

	 
	@Override
	public void updateTbConta(TbConta tbConta) {
		tbContaDAO.updateTbConta(tbConta);
	}
	

	@Override
	public List<TbContaDTO> consultar(){
		return tbContaDAO.getAllTbContas();
	}

	
	
	@Override
	public TbContaDTO getTbContaById(int id) {
		TbContaDTO obj = tbContaDAO.getTbContaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbConta(int id) {
		tbContaDAO.deleteTbConta(id);
	}

  

}
