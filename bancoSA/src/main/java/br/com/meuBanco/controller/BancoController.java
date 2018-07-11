package br.com.meuBanco.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuBanco.entity.TbBanco;
import br.com.meuBanco.entity.dto.TbBancoDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbBancoService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class BancoController {
	
	
	
	@Autowired
	private ITbBancoService bancoService;

	
	@RequestMapping(value="/banco", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbBanco tbBanco){
      
	System.out.println("-------------------------------------------------------------------"+tbBanco.getIdBanco()+" "+tbBanco.getTbBancoCodigo()+" "+tbBanco.getTbBancoNome());
		
		
		try {
			 
			this.bancoService.addTbBanco(tbBanco);
 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	
	@RequestMapping(value="/banco", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbBanco tbBanco){
		System.out.println("-------------------------------------------------------------------"+tbBanco.getIdBanco()+" "+tbBanco.getTbBancoCodigo()+" "+tbBanco.getTbBancoNome());
		try {
			 
			this.bancoService.updateTbBanco(tbBanco);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	 
	
	@RequestMapping(value="/banco", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbBancoDTO> consultar(){
		
		
		return this.bancoService.consultar();
		
			
	}
	
	
	
	@RequestMapping(value="/banco/{idBanco}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbBancoDTO buscar(@PathVariable("idBanco") String idBanco){
		System.out.println("-------------------------------------------------------------------"+idBanco);
		int id = Integer.parseInt(idBanco);
		
		return this.bancoService.getTbBancoById(id);
		
	}
	
	
	 
	
	@RequestMapping(value="/banco/{idBanco}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idBanco") String idBanco){
		
		System.out.println("-------------------------------------------------------------------"+idBanco);
		int id = Integer.parseInt(idBanco);
		//long id = Long.parseDouble(idBanco);
		 
		try {
 
			bancoService.deleteTbBanco(id);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}

	}	
		
} 
