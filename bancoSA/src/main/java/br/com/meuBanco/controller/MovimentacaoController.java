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

import br.com.meuBanco.entity.TbMovimentacao;
import br.com.meuBanco.entity.dto.TbMovimentacaoDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbMovimentacaoService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class MovimentacaoController {
	
	
	
	@Autowired
	private ITbMovimentacaoService movimentacaoService;


	/**
	 * 
	 * @param tbMovimentacao
	 * @return
	 */
	@RequestMapping(value="/movimentacao", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbMovimentacao tbMovimentacao){
      
	System.out.println("-------------------------------------------------------------------"+tbMovimentacao.getIdMovimentacao()+" ");
	
		try {			 
			this.movimentacaoService.addTbMovimentacao(tbMovimentacao); 
			return new ResponseModel(1,"Registro salvo com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	/**
	 * 
	 * @param tbMovimentacao
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/movimentacao", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbMovimentacao tbMovimentacao)  throws Exception, Throwable {

		try {			 
			this.movimentacaoService.updateTbMovimentacao(tbMovimentacao);		
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/movimentacao", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbMovimentacaoDTO> consultar(){
				
		return this.movimentacaoService.consultar();			
	}
	

	/**
	 * 
	 * @param idMovimentacao
	 * @return
	 */
	@RequestMapping(value="/movimentacao/{idMovimentacao}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbMovimentacaoDTO buscar(@PathVariable("idMovimentacao") String idMovimentacao){
		
		int id = Integer.parseInt(idMovimentacao);
		return this.movimentacaoService.getTbMovimentacaoById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idMovimentacao
	 * @return
	 */
	@RequestMapping(value="/movimentacao/{idMovimentacao}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idMovimentacao") String idMovimentacao){
		
		int id = Integer.parseInt(idMovimentacao);
		 
		try { 
			movimentacaoService.deleteTbMovimentacao(id); 
			return new ResponseModel(1, "Registro excluido com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
