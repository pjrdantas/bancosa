package br.com.meuBanco.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.meuBanco.dto.BancoDTO;
import br.com.meuBanco.repository.BancoRepository;
import br.com.meuBanco.service.BancoService;







@Service
public class BancoServiceImpl  implements BancoService {
	
	
	@Autowired
	private BancoRepository bancoRepository;


	@Override
	public void addBanco(BancoDTO bancoDTO)  throws Exception, Throwable {
		bancoRepository.addBanco(bancoDTO);
		
	}
	

	 
	@Override
	public void updateBanco(BancoDTO bancoDTO)  throws Exception, Throwable {
		bancoRepository.updateBanco(bancoDTO);
	}
	



	@Override
	public List<BancoDTO> getAllBancos()  throws Exception, Throwable{
		return bancoRepository.getAllBancos();
	}

	
	
	@Override
	public BancoDTO getBancoById(int id)  throws Exception, Throwable {
		BancoDTO obj = bancoRepository.getBancoById(id);
		return obj;
	}	
	
	 
	
	@Override
	public void deleteBanco(int id)  throws Exception, Throwable {
		bancoRepository.deleteBanco(id);
	}

  

}
