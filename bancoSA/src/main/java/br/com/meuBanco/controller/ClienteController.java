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

import br.com.meuBanco.entity.TbCliente;
import br.com.meuBanco.entity.dto.TbClienteDTO;
import br.com.meuBanco.response.ResponseModel;
import br.com.meuBanco.service.impl.ITbClienteService;




@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ClienteController {
	
	
	
	@Autowired
	private ITbClienteService clienteService;


	/**
	 * 
	 * @param tbCliente
	 * @return
	 */
	@RequestMapping(value="/cliente", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody TbCliente tbCliente){
      
	System.out.println("------------------------INCLUIR-----------------------------"+tbCliente.getIdCliente()+" "+tbCliente.getTbClienteNome()+" "+tbCliente.getTbClienteSenha());
	
		try {			 
			this.clienteService.addTbCliente(tbCliente); 
			return new ResponseModel(1,"Registro salvo com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0,e.getMessage());			
		}
		
	}

	
 
	
	/**
	 * 
	 * @param tbCliente
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value="/cliente", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody TbCliente tbCliente)  throws Exception, Throwable {

		System.out.println("-------------------------ATUALIZAR-------------------------"+tbCliente.getIdCliente()+" "+tbCliente.getTbClienteNome()+" "+tbCliente.getTbClienteSenha());

		
		try {			 
			this.clienteService.updateTbCliente(tbCliente);		
			return new ResponseModel(1,"Registro atualizado com sucesso!"); 
		}catch(Exception e) { 
			return new ResponseModel(0,e.getMessage());
		}
		
	}
	

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/cliente", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<TbClienteDTO> consultar(){
				
		return this.clienteService.consultar();			
	}
	

	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	@RequestMapping(value="/cliente/{idCliente}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody TbClienteDTO buscar(@PathVariable("idCliente") String idCliente){
		
		System.out.println("------------------------CONSULTA POR ID -----------------------------"+idCliente);
		
		int id = Integer.parseInt(idCliente);
		return this.clienteService.getTbClienteById(id);
		
	}
	
	 
	 
	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	@RequestMapping(value="/cliente/{idCliente}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("idCliente") String idCliente){

		System.out.println("------------------------EXCLUIR-----------------------------"+idCliente);

		int id = Integer.parseInt(idCliente);
		 
		try { 
			clienteService.deleteTbCliente(id); 
			return new ResponseModel(1, "Registro excluido com sucesso!"); 
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}	
		 
} 
