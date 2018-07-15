package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbNotaDAO;
import br.com.meuBanco.entity.dto.TbNotaDTO;
import br.com.meuBanco.service.impl.ITbNotaService;







@Service
public class TbNotaService  implements ITbNotaService {
	
	
	@Autowired
	private ItbNotaDAO tbNotaDAO;


	@Override
	public void addTbNotaDTO(TbNotaDTO tbNotaDTO)   throws Exception, Throwable {
		tbNotaDAO.addTbNotaDTO(tbNotaDTO);
		
	}
	

	 
	@Override
	public void updateTbNotaDTO(TbNotaDTO tbNotaDTO)   throws Exception, Throwable {
		tbNotaDAO.updateTbNotaDTO(tbNotaDTO);
	}
	

	@Override
	public List<TbNotaDTO> consultar()   throws Exception, Throwable {
		return getTbNotas();
	}



	@Override
	public List<TbNotaDTO> getTbNotas()   throws Exception, Throwable {
		return tbNotaDAO.getAllTbNotas();
	}

	
	
	@Override
	public TbNotaDTO getTbNotaById(int id)   throws Exception, Throwable {
		TbNotaDTO obj = tbNotaDAO.getTbNotaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbNota(int id)   throws Exception, Throwable {
		tbNotaDAO.deleteTbNota(id);
	}

  

}
