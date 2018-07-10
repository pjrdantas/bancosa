package br.com.meuBanco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dao.impl.ItbClienteDAO;
import br.com.meuBanco.entity.TbCliente;
import br.com.meuBanco.entity.dto.TbClienteDTO;
import br.com.meuBanco.service.impl.ITbClienteService;







@Service
public class TbClienteService  implements ITbClienteService {
	
	
	@Autowired
	private ItbClienteDAO tbClienteDAO;


	@Override
	public void addTbCliente(TbCliente tbCliente) {
		tbClienteDAO.addTbCliente(tbCliente);
		
	}
	

	 
	@Override
	public void updateTbCliente(TbCliente tbCliente) {
		tbClienteDAO.updateTbCliente(tbCliente);
	}
	

	@Override
	public List<TbClienteDTO> consultar(){
		return tbClienteDAO.getAllTbClientes();
	}

	
	
	@Override
	public TbClienteDTO getTbClienteById(int id) {
		TbClienteDTO obj = tbClienteDAO.getTbClienteById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteTbCliente(int id) {
		tbClienteDAO.deleteTbCliente(id);
	}

  

}
