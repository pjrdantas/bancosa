package br.com.meuBanco.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuBanco.dto.ClienteDTO;
import br.com.meuBanco.response.ResponseMessage;
import br.com.meuBanco.service.ClienteService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	/**
	 * 
	 * @param tbCliente
	 * @return
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseMessage addTbCliente(@RequestBody ClienteDTO clienteDTO) throws Exception, Throwable {
		log.info("----> ClienteController.addTbCliente");

		try {
			this.clienteService.addCliente(clienteDTO);
			return new ResponseMessage(1, "Registro salvo com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO INSERT DO CLIENTE", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @param tbCliente
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseMessage updateCliente(@RequestBody ClienteDTO clienteDTO) throws Exception, Throwable {
		log.info("----> ClienteController.updateCliente");

		try {
			this.clienteService.updateCliente(clienteDTO);
			return new ResponseMessage(1, "Registro atualizado com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO UPDATE DO CLIENTE", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/cliente", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ClienteDTO> getAllClientes() throws Exception, Throwable {
		log.info("----> ClienteController.getAllClientes");
		return this.clienteService.getAllClientes();
	}

	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	@RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ClienteDTO getClienteById(@PathVariable("idCliente") String idCliente)
			throws Exception, Throwable {
		log.info("----> ClienteController.getClienteById");

		int id = Integer.parseInt(idCliente);
		return this.clienteService.getClienteById(id);
	}

	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	@RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseMessage deleteCliente(@PathVariable("idCliente") String idCliente)
			throws Exception, Throwable {
		log.info("----> ClienteController.deleteCliente");

		int id = Integer.parseInt(idCliente);
		try {
			clienteService.deleteCliente(id);
			return new ResponseMessage(1, "Registro excluido com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO DELETE DO CLIENTE", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}
}
