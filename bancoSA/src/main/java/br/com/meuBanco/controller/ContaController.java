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

import br.com.meuBanco.entity.TbConta;
import br.com.meuBanco.entity.dto.TbContaDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbContaService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ContaController {
	
	
	
	@Autowired
	private ITbContaService contaService;


	/**
	 * 
	 * @param tbConta
	 * @return
	 */
	@RequestMapping(value="/conta", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbConta tbConta){
      
	System.out.println("-------------------------------------------------------------------"+tbConta.getIdConta()+" ");
	
		try {			 
			this.contaService.addTbConta(tbConta); 
			return new ResponseModel(1,"Registro salvo com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	/**
	 * 
	 * @param tbConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/conta", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbConta tbConta)  throws Exception, Throwable {

		try {			 
			this.contaService.updateTbConta(tbConta);		
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/conta", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbContaDTO> consultar(){
				
		return this.contaService.consultar();			
	}
	

	/**
	 * 
	 * @param idConta
	 * @return
	 */
	@RequestMapping(value="/conta/{idConta}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbContaDTO buscar(@PathVariable("idConta") String idConta){
		
		int id = Integer.parseInt(idConta);
		return this.contaService.getTbContaById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idConta
	 * @return
	 */
	@RequestMapping(value="/conta/{idConta}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idConta") String idConta){
		
		int id = Integer.parseInt(idConta);
		 
		try { 
			contaService.deleteTbConta(id); 
			return new ResponseModel(1, "Registro excluido com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
