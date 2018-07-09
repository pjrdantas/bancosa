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

import br.com.meuBanco.entity.TbAgencia;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbAgenciaService;





@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class AgenciaController {
	
	
	
	@Autowired
	private ITbAgenciaService agenciaService;

	
	@RequestMapping(value="/agencia", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbAgencia tbAgencia){
      
	System.out.println("-------------------------------------------------------------------"+tbAgencia.getIdAgencia()+" "+tbAgencia.getTbAgenciaCodigo()+" "+tbAgencia.getTbAgenciaDigito()+" "+tbAgencia.getTbBanco().getIdBanco());
		
		
		try {
			 
			this.agenciaService.addTbAgencia(tbAgencia);
 
			return new ResponseModel(1,"Registro salvo com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage()+"deu merda");			
		}
		
	}

	
 
	
	
	@RequestMapping(value="/agencia", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbAgencia tbAgencia){
		System.out.println("-------------------------------------------------------------------"+tbAgencia.getIdAgencia()+" "+tbAgencia.getTbAgenciaCodigo()+" "+tbAgencia.getTbAgenciaDigito());
		try {
			 
			this.agenciaService.updateTbAgencia(tbAgencia);		
 
			return new ResponseModel(1,"Registro atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	 
	
	@RequestMapping(value="/agencia", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbAgencia> consultar(){
		
		
		return this.agenciaService.consultar();
		
			
	}
	
	
	
	@RequestMapping(value="/agencia/{idAgencia}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbAgencia buscar(@PathVariable("idAgencia") String idAgencia){
		System.out.println("-------------------------------------------------------------------"+idAgencia);
		int id = Integer.parseInt(idAgencia);
		
		return this.agenciaService.getTbAgenciaById(id);
		
	}
	
	
	 
	
	@RequestMapping(value="/agencia/{idAgencia}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idAgencia") String idAgencia){
		
		System.out.println("-------------------------------------------------------------------"+idAgencia);
		int id = Integer.parseInt(idAgencia);
		//long id = Long.parseDouble(idAgencia);
		 
		try {
 
			agenciaService.deleteTbAgencia(id);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}

	}	
		
} 
