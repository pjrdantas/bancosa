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
import br.com.meuBanco.entity.dto.TbNotaDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbNotaService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class NotaController {
	
	
	
	@Autowired
	private ITbNotaService notaService;


	/**
	 * 
	 * @param tbNota
	 * @return
	 */
	@RequestMapping(value="/notas", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbNota tbNota){
      
	System.out.println("-------------------------------------------------------------------"+tbNota.getIdNotas()+" "+tbNota.getTbNotasValor());
	
		try {			 
			this.notaService.addTbNota(tbNota); 
			return new ResponseModel(1,"Registro salvo com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	/**
	 * 
	 * @param tbNota
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/notas", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbNota tbNota)  throws Exception, Throwable {

		try {			 
			this.notaService.updateTbNota(tbNota);		
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/notas", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbNotaDTO> consultar(){
				
		return this.notaService.consultar();			
	}
	

	/**
	 * 
	 * @param idNota
	 * @return
	 */
	@RequestMapping(value="/notas/{idNota}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbNotaDTO buscar(@PathVariable("idNota") String idNota){
		
		int id = Integer.parseInt(idNota);
		return this.notaService.getTbNotaById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idNota
	 * @return
	 */
	@RequestMapping(value="/notas/{idNota}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idNota") String idNota){
		
		int id = Integer.parseInt(idNota);
		 
		try { 
			notaService.deleteTbNota(id); 
			return new ResponseModel(1, "Registro excluido com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
