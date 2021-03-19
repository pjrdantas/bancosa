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

import br.com.meuBanco.dto.AgenciaDTO;
import br.com.meuBanco.response.ResponseMessage;
import br.com.meuBanco.service.AgenciaService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class AgenciaController {

	private static final Logger log = LoggerFactory.getLogger(AgenciaController.class);

	@Autowired
	private AgenciaService agenciaService;

	/**
	 * 
	 * @param tbAgenciaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/agencia", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseMessage addAgencia(@RequestBody AgenciaDTO agenciaDTO) throws Exception, Throwable {
		log.info("----> AgenciaController.addAgencia");

		try {
			this.agenciaService.addAgencia(agenciaDTO);
			return new ResponseMessage(1, "Registro salvo com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO INSERT DO AGENCIA", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @param tbAgenciaDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/agencia", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseMessage udateAgencia(@RequestBody AgenciaDTO agenciaDTO) throws Exception, Throwable {
		log.info("----> AgenciaController.udateAgencia");

		try {
			this.agenciaService.updateAgencia(agenciaDTO);
			return new ResponseMessage(1, "Registro atualizado com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO UPDATE DA AGENCIA", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/agencia", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<AgenciaDTO> getAllAgencias() throws Exception, Throwable {
		log.info("----> AgenciaController.getAllAgencias");

		return this.agenciaService.getAllAgencias();
	}

	/**
	 * 
	 * @param idAgencia
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/agencia/{idAgencia}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AgenciaDTO getAgencia(@PathVariable("idAgencia") String idAgencia)
			throws Exception, Throwable {
		log.info("----> AgenciaController.getAgencia");

		int id = Integer.parseInt(idAgencia);
		return this.agenciaService.getAgenciaById(id);
	}

	/**
	 * 
	 * @param idAgencia
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/agencia/{idAgencia}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseMessage delete(@PathVariable("idAgencia") String idAgencia)
			throws Exception, Throwable {
		log.info("----> AgenciaController.delete");

		int id = Integer.parseInt(idAgencia);
		try {
			agenciaService.deleteAgencia(id);
			return new ResponseMessage(1, "Registro excluido com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO DELETE DA AGENCIA", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}
}
