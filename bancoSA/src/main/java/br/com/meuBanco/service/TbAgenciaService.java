package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbAgenciaDAO;
import br.com.meuBanco.entity.dto.TbAgenciaDTO;
import br.com.meuBanco.service.impl.ITbAgenciaService;







@Service
public class TbAgenciaService  implements ITbAgenciaService {
	
	
	@Autowired
	private ItbAgenciaDAO tbAgenciaDAO;


	@Override
	public void addTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)   throws Exception, Throwable {
		tbAgenciaDAO.addTbAgenciaDTO(tbAgenciaDTO);
		
	}
	

	 
	@Override
	public void updateTbAgenciaDTO(TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable {
		tbAgenciaDAO.updateTbAgenciaDTO(tbAgenciaDTO);
	}
	

	@Override
	public List<TbAgenciaDTO> consultar()   throws Exception, Throwable {
		return tbAgenciaDAO.getAllTbAgencias();
	}

	
	
	@Override
	public TbAgenciaDTO getTbAgenciaById(int id)  throws Exception, Throwable {
		TbAgenciaDTO obj = tbAgenciaDAO.getTbAgenciaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbAgencia(int id)  throws Exception, Throwable {
		tbAgenciaDAO.deleteTbAgencia(id);
	}

  

}
