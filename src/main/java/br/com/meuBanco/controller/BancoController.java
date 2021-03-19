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

import br.com.meuBanco.dto.BancoDTO;
import br.com.meuBanco.response.ResponseMessage;
import br.com.meuBanco.service.BancoService;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/service")
public class BancoController {

	private static final Logger log = LoggerFactory.getLogger(BancoController.class);

	@Autowired
	private BancoService bancoService;

	/**
	 * 
	 * @param tbBancoDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/banco", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseMessage addBanco(@RequestBody BancoDTO bancoDTO) throws Exception, Throwable {
		log.info("----> AgenciaController.addBanco");

		try {
			this.bancoService.addBanco(bancoDTO);
			return new ResponseMessage(1, "Registro salvo com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NA INCLUSÃO DO BANCO", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @param tbBancoDTO
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/banco", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody ResponseMessage updateBanco(@RequestBody BancoDTO bancoDTO) throws Exception, Throwable {
		log.info("----> AgenciaController.updateBanco");

		try {
			this.bancoService.updateBanco(bancoDTO);
			return new ResponseMessage(1, "Registro atualizado com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO UPDATE DO BANCO", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/banco", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<BancoDTO> getAllBancos() throws Exception, Throwable {
		log.info("----> AgenciaController.getAllBancos");

		return this.bancoService.getAllBancos();
	}

	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/banco/{idBanco}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BancoDTO getBancoById(@PathVariable("idBanco") String idBanco) throws Exception, Throwable {
		log.info("----> AgenciaController.getBancoById");

		int id = Integer.parseInt(idBanco);
		return this.bancoService.getBancoById(id);
	}

	/**
	 * 
	 * @param idBanco
	 * @return
	 * @throws Exception
	 * @throws Throwable
	 */
	@RequestMapping(value = "/banco/{idBanco}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody ResponseMessage deleteBanco(@PathVariable("idBanco") String idBanco)
			throws Exception, Throwable {
		log.info("----> AgenciaController.deleteBanco");

		int id = Integer.parseInt(idBanco);
		try {
			bancoService.deleteBanco(id);
			return new ResponseMessage(1, "Registro excluido com sucesso!");
		} catch (Exception e) {
			log.error("ERRO NO DELETE DO BANCO", e.toString());
			return new ResponseMessage(0, e.getMessage());
		}
	}
}
