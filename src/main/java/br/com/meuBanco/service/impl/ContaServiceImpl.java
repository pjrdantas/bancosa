package br.com.meuBanco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dto.ContaDTO;
import br.com.meuBanco.repository.ContaRepository;
import br.com.meuBanco.service.ContaService;







@Service
public class ContaServiceImpl  implements ContaService {
	
	
	@Autowired
	private ContaRepository contaRepository;


	@Override
	public void addConta(ContaDTO contaDTO)  throws Exception, Throwable {
		contaRepository.addConta(contaDTO);
		
	}
	

	 
	@Override
	public void updateConta(ContaDTO contaDTO)  throws Exception, Throwable {
		contaRepository.updateConta(contaDTO);
	}
	

	@Override
	public List<ContaDTO> getAllContas()  throws Exception, Throwable {
		return contaRepository.getAllContas();
	}

	
	
	@Override
	public ContaDTO getContaById(int id)  throws Exception, Throwable {
		ContaDTO obj = contaRepository.getContaById(id);
		return obj;
	}	
	
	@Override
	public ContaDTO getContaByCliente(int id)  throws Exception, Throwable {
		ContaDTO obj = contaRepository.getContaByCliente(id);
		return obj;
	}
	
	@Override
	public void deleteConta(int id)  throws Exception, Throwable {
		contaRepository.deleteConta(id);
	}

  

}
