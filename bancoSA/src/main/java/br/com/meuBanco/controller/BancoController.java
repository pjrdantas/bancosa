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

import br.com.meuBanco.entity.dto.TbBancoDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbBancoService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class BancoController {
	
	
	
	@Autowired
	private ITbBancoService bancoService;

	/**
	 * 
	 * @param tbBancoDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/banco", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbBancoDTO tbBancoDTO)   throws Exception, Throwable {
      
		
		try {		 
			this.bancoService.addTbBancoDTO(tbBancoDTO); 
			return new ResponseModel(1,"Registro salvo com sucesso!");
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

		
	/**
	 * 
	 * @param tbBancoDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/banco", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbBancoDTO tbBancoDTO) throws Exception, Throwable {

		try {	 
			this.bancoService.updateTbBancoDTO(tbBancoDTO);		
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
	@RequestMapping(value="/banco", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbBancoDTO> getTbBancos()   throws Exception, Throwable{
		
		
		return this.bancoService.getAllTbBancos();
		
			
	}
	
	
	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/banco/{idBanco}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbBancoDTO buscar(@PathVariable("idBanco") String idBanco)   throws Exception, Throwable{

		int id = Integer.parseInt(idBanco);
		
		return this.bancoService.getTbBancoById(id);
		
	}
	
	
	 
	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/banco/{idBanco}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idBanco") String idBanco)   throws Exception, Throwable{
		

		int id = Integer.parseInt(idBanco);
		
		 
		try {
 
			bancoService.deleteTbBanco(id);
 
			return new ResponseModel(1, "Registro excluido com sucesso!");
 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}

	}	
		
} 
