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

import br.com.meuBanco.entity.TbNota;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbNotaService;





@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class NotaController {
	
	
	
	@Autowired
	private ITbNotaService notaService;

	
	@RequestMapping(value="/nota", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbNota tbNota){
      
		System.out.println("-------------------------------------------------------------------"+tbNota.getIdNotas()+" "+tbNota.getTbNotasValor());	
		
		try {
			 
			this.notaService.addTbNota(tbNota);
 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	
	@RequestMapping(value="/nota", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbNota tbNota){
		System.out.println("-------------------------------------------------------------------"+tbNota.getIdNotas()+" "+tbNota.getTbNotasValor());
		try {
			 
			this.notaService.updateTbNota(tbNota);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	 
	
	@RequestMapping(value="/nota", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbNota> consultar(){
		
		
		return this.notaService.consultar();
		
			
	}
	
	
	
	@RequestMapping(value="/nota/{idNota}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbNota buscar(@PathVariable("idNota") String idNota){
		System.out.println("-------------------------------------------------------------------"+idNota);
		int id = Integer.parseInt(idNota);
		
		return this.notaService.getTbNotaById(id);
		
	}
	
	
	 
	
	@RequestMapping(value="/nota/{idNota}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idNota") String idNota){
		
		System.out.println("-------------------------------------------------------------------"+idNota);
		int id = Integer.parseInt(idNota);
		//long id = Long.parseDouble(idNota);
		 
		try {
 
			notaService.deleteTbNota(id);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}

	}	
		
} 
