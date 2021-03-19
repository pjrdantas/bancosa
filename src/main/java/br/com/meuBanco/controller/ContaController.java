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

import br.com.meuBanco.dto.ContaDTO;
import br.com.meuBanco.response.ResponseMessage;
import br.com.meuBanco.service.ContaService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class ContaController {

	private static final Logger log = LoggerFactory.getLogger(ContaController.class);

	@Autowired
	private ContaService contaService;

	/**
	 * 
	 * @param tbContaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/conta", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseMessage addContaDTO(@RequestBody ContaDTO contaDTO) throws Exception, Throwable {
		log.info("----> AgenciaController.addContaDTO");

		try {
			this.contaService.addConta(contaDTO);
			return new ResponseMessage(1, "Registro salvo com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO INSERT DA CONTA", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @param tbConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/conta", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseMessage updateConta(@RequestBody ContaDTO contaDTO) throws Exception, Throwable {
		log.info("----> AgenciaController.updateConta");

		try {
			this.contaService.updateConta(contaDTO);
			return new ResponseMessage(1, "Registro atualizado com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO UPDATE DA CONTA", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/conta", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ContaDTO> getAllContas() throws Exception, Throwable {
		log.info("----> AgenciaController.getAllContas");
		return this.contaService.getAllContas();
	}

	/**
	 * 
	 * @param idConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/conta/{idConta}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ContaDTO getContaById(@PathVariable("idConta") String idConta) throws Exception, Throwable {
		log.info("----> AgenciaController.getContaById");
		int id = Integer.parseInt(idConta);
		return this.contaService.getContaById(id);
	}

	/**
	 * 
	 * @param idConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/contaCliente/{contaIdCliente}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ContaDTO getContaByCliente(@PathVariable("contaIdCliente") String contaIdCliente)
			throws Exception, Throwable {
		log.info("----> AgenciaController.getContaByCliente");
		int id = Integer.parseInt(contaIdCliente);
		return this.contaService.getContaByCliente(id);
	}

	/**
	 * 
	 * @param idConta
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/conta/{idConta}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseMessage deleteConta(@PathVariable("idConta") String idConta)
			throws Exception, Throwable {
		log.info("----> AgenciaController.deleteConta");
		int id = Integer.parseInt(idConta);

		try {
			contaService.deleteConta(id);
			return new ResponseMessage(1, "Registro excluido com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO DELETE DA CONTA", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}
}
