package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbContaDAO;
import br.com.meuBanco.entity.dto.TbContaDTO;
import br.com.meuBanco.service.impl.ITbContaService;







@Service
public class TbContaService  implements ITbContaService {
	
	
	@Autowired
	private ItbContaDAO tbContaDAO;


	@Override
	public void addTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable {
		tbContaDAO.addTbContaDTO(tbContaDTO);
		
	}
	

	 
	@Override
	public void updateTbContaDTO(TbContaDTO tbContaDTO)  throws Exception, Throwable {
		tbContaDAO.updateTbContaDTO(tbContaDTO);
	}
	

	@Override
	public List<TbContaDTO> consultar()  throws Exception, Throwable {
		return tbContaDAO.getAllTbContas();
	}

	
	
	@Override
	public TbContaDTO getTbContaById(int id)  throws Exception, Throwable {
		TbContaDTO obj = tbContaDAO.getTbContaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbConta(int id)  throws Exception, Throwable {
		tbContaDAO.deleteTbConta(id);
	}

  

}
