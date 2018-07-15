package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbBancoDAO;
import br.com.meuBanco.entity.dto.TbBancoDTO;
import br.com.meuBanco.service.impl.ITbBancoService;







@Service
public class TbbancoService  implements ITbBancoService {
	
	
	@Autowired
	private ItbBancoDAO tbBancoDAO;


	@Override
	public void addTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable {
		tbBancoDAO.addTbBancoDTO(tbBancoDTO);
		
	}
	

	 
	@Override
	public void updateTbBancoDTO(TbBancoDTO tbBancoDTO)  throws Exception, Throwable {
		tbBancoDAO.updateTbBancoDTO(tbBancoDTO);
	}
	



	@Override
	public List<TbBancoDTO> getAllTbBancos()  throws Exception, Throwable{
		return tbBancoDAO.getAllTbBancos();
	}

	
	
	@Override
	public TbBancoDTO getTbBancoById(int id)  throws Exception, Throwable {
		TbBancoDTO obj = tbBancoDAO.getTbBancoById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbBanco(int id)  throws Exception, Throwable {
		tbBancoDAO.deleteTbBanco(id);
	}

  

}
