package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbAgenciaDAO;
import br.com.meuBanco.entity.TbAgencia;
import br.com.meuBanco.service.impl.ITbAgenciaService;







@Service
public class TbAgenciaService  implements ITbAgenciaService {
	
	
	@Autowired
	private ItbAgenciaDAO tbAgenciaDAO;

	
	@Override
	public void addTbAgencia(TbAgencia tbAgencia) {
		tbAgenciaDAO.addTbAgencia(tbAgencia);
		
	}
	
	
	 
	@Override
	public void updateTbAgencia(TbAgencia tbAgencia) {
		tbAgenciaDAO.updateTbAgencia(tbAgencia);
	}
	
	 
	@Override
	public List<TbAgencia> consultar(){
		return tbAgenciaDAO.getAllTbAgencias();
	}

	
		
	@Override
	public TbAgencia getTbAgenciaById(int id) {
		TbAgencia obj = tbAgenciaDAO.getTbAgenciaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbAgencia(int id) {
		tbAgenciaDAO.deleteTbAgencia(id);
	}

 

}
