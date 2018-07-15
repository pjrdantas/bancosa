package br.com.meuBanco.controller;

import java.util.Date;
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
	 * @param tbNotaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/nota", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbNotaDTO tbNotaDTO)  throws Exception, Throwable {
      
	System.out.println("-------------------------------------------------------------------"+tbNotaDTO.getIdNotas()+" "+tbNotaDTO.getNotasValor());
	
		try {			 
			this.notaService.addTbNotaDTO(tbNotaDTO); 
			return new ResponseModel(1,"Novo valor de NOTA adicionada com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	/**
	 * 
	 * @param tbNotaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/nota", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbNotaDTO tbNotaDTO)  throws Exception, Throwable {

		System.out.println("-------------------------------------------------------------------"+tbNotaDTO.getIdNotas()+" "+tbNotaDTO.getNotasValor());
		
		try {			 
			this.notaService.updateTbNotaDTO(tbNotaDTO);		
			return new ResponseModel(1,"Valor da NOTA atualizado com sucesso!"); 
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
	@RequestMapping(value="/nota", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbNotaDTO> getTbBancos()  throws Exception, Throwable {
		
		System.out.println("-------------------------------------------------------------------"+ new Date());
				
		return this.notaService.getTbNotas();			
	}
	

	/**
	 * 
	 * @param idNotas
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/nota/{idNotas}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbNotaDTO buscar(@PathVariable("idNotas") String idNotas)  throws Exception, Throwable {
		
		System.out.println("-------------------------------------------------------------------"+idNotas);
		
		int id = Integer.parseInt(idNotas);
		return this.notaService.getTbNotaById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idNotas
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/nota/{idNotas}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idNotas") String idNotas)  throws Exception, Throwable {
		
		int id = Integer.parseInt(idNotas);
		 
		try { 
			notaService.deleteTbNota(id); 
			return new ResponseModel(1, "Valor da NOTA excluida com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
