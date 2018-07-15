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

import br.com.meuBanco.entity.dto.TbAgenciaDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbAgenciaService;



@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class AgenciaController {
	
	
	
	@Autowired
	private ITbAgenciaService agenciaService;


	/**
	 * 
	 * @param tbAgenciaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/agencia", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable {      

		try {			 
			this.agenciaService.addTbAgenciaDTO(tbAgenciaDTO); 
			return new ResponseModel(1,"Registro salvo com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
		
	}
 
	
	/**
	 * 
	 * @param tbAgenciaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/agencia", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbAgenciaDTO tbAgenciaDTO)  throws Exception, Throwable {

		try {			 
			this.agenciaService.updateTbAgenciaDTO(tbAgenciaDTO);		
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	
	/**
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/agencia", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbAgenciaDTO> consultar()  throws Exception, Throwable {
				
		return this.agenciaService.consultar();			
	}
	
	
	

	/**
	 * 
	 * @param idAgencia
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/agencia/{idAgencia}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbAgenciaDTO buscar(@PathVariable("idAgencia") String idAgencia)  throws Exception, Throwable {
		
		int id = Integer.parseInt(idAgencia);
		return this.agenciaService.getTbAgenciaById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idAgencia
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/agencia/{idAgencia}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idAgencia") String idAgencia)  throws Exception, Throwable {
		
		int id = Integer.parseInt(idAgencia);
		 
		try { 
			agenciaService.deleteTbAgencia(id); 
			return new ResponseModel(1, "Registro excluido com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
