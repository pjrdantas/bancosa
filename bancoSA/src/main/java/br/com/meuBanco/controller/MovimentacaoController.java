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

import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbMovimentacaoService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class MovimentacaoController {
	
	
	
	@Autowired
	private ITbMovimentacaoService movimentacaoService;

	int codigo = 1;
    String mensagem = "saldo salvo com sucesso!";
    
    
	/**
	 * 
	 * @param tbMovimentacaoDTO
	 * @return 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/movimentacao", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody  ResponseModel salvar(@RequestBody TbMovimentacaoDTO tbMovimentacaoDTO)  throws Exception, Throwable {
      		
		try {							
			this.movimentacaoService.addTbMovimentacaoDTO(tbMovimentacaoDTO);									
			return new ResponseModel(codigo,mensagem); 
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
	@RequestMapping(value="/movimentacao", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbMovimentacaoDTO> consultar()  throws Exception, Throwable {
				
		return this.movimentacaoService.consultar();			
	}
	

	/**
	 * 
	 * @param idMovimentacao
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/movimentacao/{idMovimentacao}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbMovimentacaoDTO buscar(@PathVariable("idMovimentacao") String idMovimentacao)  throws Exception, Throwable {
		
		int id = Integer.parseInt(idMovimentacao);
		return this.movimentacaoService.getTbMovimentacaoById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idMovimentacao
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/movimentacao/{idMovimentacao}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idMovimentacao") String idMovimentacao)  throws Exception, Throwable {
		
		int id = Integer.parseInt(idMovimentacao);
		 
		try { 
			movimentacaoService.deleteTbMovimentacao(id); 
			return new ResponseModel(1, "Registro excluido com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
