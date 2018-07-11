package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbBancoDAO;
import br.com.meuBanco.entity.TbBanco;
import br.com.meuBanco.entity.dto.TbBancoDTO;
import br.com.meuBanco.service.impl.ITbBancoService;







@Service
public class TbbancoService  implements ITbBancoService {
	
	
	@Autowired
	private ItbBancoDAO tbBancoDAO;


	@Override
	public void addTbBanco(TbBanco tbBanco) {
		tbBancoDAO.addTbBanco(tbBanco);
		
	}
	

	 
	@Override
	public void updateTbBanco(TbBanco tbBanco) {
		tbBancoDAO.updateTbBanco(tbBanco);
	}
	

	@Override
	public List<TbBancoDTO> consultar(){
		return tbBancoDAO.getAllTbBancos();
	}

	
	
	@Override
	public TbBancoDTO getTbBancoById(int id) {
		TbBancoDTO obj = tbBancoDAO.getTbBancoById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbBanco(int id) {
		tbBancoDAO.deleteTbBanco(id);
	}

  

}
