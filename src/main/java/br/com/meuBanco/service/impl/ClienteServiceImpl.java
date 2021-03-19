package br.com.meuBanco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dto.ClienteDTO;
import br.com.meuBanco.repository.ClienteRepository;
import br.com.meuBanco.service.ClienteService;




@Service
public class ClienteServiceImpl  implements ClienteService {
	
	
	@Autowired
	private ClienteRepository clienteRepository;


	@Override
	public void addCliente(ClienteDTO clienteDTO)   throws Exception, Throwable {
		clienteRepository.addCliente(clienteDTO);
		
	}
	
	 
	@Override
	public void updateCliente(ClienteDTO clienteDTO)   throws Exception, Throwable {
		clienteRepository.updateCliente(clienteDTO);
	}
	

	@Override
	public List<ClienteDTO> getAllClientes()  throws Exception, Throwable {
		return clienteRepository.getAllClientes();
	}

		
	@Override
	public ClienteDTO getClienteById(int id)  throws Exception, Throwable  {
		ClienteDTO obj = clienteRepository.getClienteById(id);
		return obj;
	}	
	

	
	@Override
	public void deleteCliente(int id)  throws Exception, Throwable  {
		clienteRepository.deleteCliente(id);
	}

  

}
