package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbNotaDAO;
import br.com.meuBanco.entity.TbNota;
import br.com.meuBanco.service.impl.ITbNotaService;



@Service
public class TbNotaService  implements ITbNotaService {
	
	
	@Autowired
	private ItbNotaDAO tbNotaDAO;

	
	@Override
	public void addTbNota(TbNota tbNota) {
		tbNotaDAO.addTbNota(tbNota);
		
	}
	
	
	 
	@Override
	public void updateTbNota(TbNota tbNota) {
		tbNotaDAO.updateTbNota(tbNota);
	}
	
	 
	@Override
	public List<TbNota> consultar(){
		return tbNotaDAO.getAllTbNotas();
	}

	
		
	@Override
	public TbNota getTbNotaById(int id) {
		TbNota obj = tbNotaDAO.getTbNotaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbNota(int id) {
		tbNotaDAO.deleteTbNota(id);
	}

 

}
