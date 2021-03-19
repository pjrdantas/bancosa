package br.com.meuBanco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dto.AgenciaDTO;
import br.com.meuBanco.repository.AgenciaRepository;
import br.com.meuBanco.service.AgenciaService;


@Service
public class AgenciaServiceImpl  implements AgenciaService {
	
	
	@Autowired
	private AgenciaRepository agenciaRepository;


	@Override
	public void addAgencia(AgenciaDTO agenciaDTO)   throws Exception, Throwable {
		agenciaRepository.addAgencia(agenciaDTO);
		
	}
	

	 
	@Override
	public void updateAgencia(AgenciaDTO agenciaDTO)  throws Exception, Throwable {
		agenciaRepository.updateAgencia(agenciaDTO);
	}
	

	@Override
	public List<AgenciaDTO> getAllAgencias()   throws Exception, Throwable {
		return agenciaRepository.getAllAgencias();
	}

	
	
	@Override
	public AgenciaDTO getAgenciaById(int id)  throws Exception, Throwable {
		AgenciaDTO obj = agenciaRepository.getAgenciaById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteAgencia(int id)  throws Exception, Throwable {
		agenciaRepository.deleteAgencia(id);
	}

  

}
